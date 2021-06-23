package com.gafahtec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import com.gafahtec.model.Medida;
import com.gafahtec.service.IMedidaService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/medidas")
@AllArgsConstructor
public class MedidaController {

	private IMedidaService iMedidaService;
	
	@GetMapping
	public ResponseEntity<List<Medida>> listar() throws Exception{
		List<Medida> lista = iMedidaService.listar();
		return new ResponseEntity<List<Medida>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medida> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Medida obj = iMedidaService.listarPorId(id);
		
		if(obj.getIdMedida() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Medida>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Medida> registrar(@Valid @RequestBody Medida p) throws Exception{
		Medida obj = iMedidaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedida()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Medida> modificar(@Valid @RequestBody Medida p) throws Exception{
		Medida obj = iMedidaService.modificar(p);
		return new ResponseEntity<Medida>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Medida obj = iMedidaService.listarPorId(id);
		
		if(obj.getIdMedida() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iMedidaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
