package com.gafahtec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.Mesa;
import com.gafahtec.service.IMesaService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/mesas")
@AllArgsConstructor
public class MesaController {

	private IMesaService iMesaService;
	
	@GetMapping
	public ResponseEntity<List<Mesa>> listar() throws Exception{
		List<Mesa> lista = iMesaService.listarMesasDesocupadas();
//		System.out.println(lista);
		return new ResponseEntity<List<Mesa>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mesa> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Mesa obj = iMesaService.listarPorId(id);
		
		if(obj.getIdMesa() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Mesa>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Mesa> registrar(@Valid @RequestBody Mesa p) throws Exception{
		Mesa obj = iMesaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMesa()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Mesa> modificar(@Valid @RequestBody Mesa p) throws Exception{
		Mesa obj = iMesaService.modificar(p);
		return new ResponseEntity<Mesa>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Mesa obj = iMesaService.listarPorId(id);
		
		if(obj.getIdMesa() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iMesaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("/pageable")
	public ResponseEntity<Page<Mesa>> listarPageable(Pageable pageable) throws Exception{			
		Page<Mesa> paginas = iMesaService.listarPageable(pageable);
		return new ResponseEntity<Page<Mesa>>(paginas, HttpStatus.OK);
	}

}
