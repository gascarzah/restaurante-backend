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
import com.gafahtec.model.Caja;
import com.gafahtec.service.ICajaService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/cajas")
@AllArgsConstructor
public class CajaController {

	private ICajaService iCajaService;
	
	@GetMapping
	public ResponseEntity<List<Caja>> listar() throws Exception{
		List<Caja> lista = iCajaService.listar();
		return new ResponseEntity<List<Caja>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Caja> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Caja obj = iCajaService.listarPorId(id);
		
		if(obj.getIdCaja() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Caja>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Caja> registrar(@Valid @RequestBody Caja p) throws Exception{
		Caja obj = iCajaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCaja()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Caja> modificar(@Valid @RequestBody Caja p) throws Exception{
		Caja obj = iCajaService.modificar(p);
		return new ResponseEntity<Caja>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Caja obj = iCajaService.listarPorId(id);
		
		if(obj.getIdCaja() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iCajaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
