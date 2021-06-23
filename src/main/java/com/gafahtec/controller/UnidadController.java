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
import com.gafahtec.model.Unidad;
import com.gafahtec.service.IUnidadService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/unidades")
@AllArgsConstructor
public class UnidadController {

	private IUnidadService iUnidadService;
	
	@GetMapping
	public ResponseEntity<List<Unidad>> listar() throws Exception{
		List<Unidad> lista = iUnidadService.listar();
		return new ResponseEntity<List<Unidad>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Unidad> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Unidad obj = iUnidadService.listarPorId(id);
		
		if(obj.getIdUnidad() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Unidad>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Unidad> registrar(@Valid @RequestBody Unidad p) throws Exception{
		Unidad obj = iUnidadService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUnidad()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Unidad> modificar(@Valid @RequestBody Unidad p) throws Exception{
		Unidad obj = iUnidadService.modificar(p);
		return new ResponseEntity<Unidad>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Unidad obj = iUnidadService.listarPorId(id);
		
		if(obj.getIdUnidad() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iUnidadService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
