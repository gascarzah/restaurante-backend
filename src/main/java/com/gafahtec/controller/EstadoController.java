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
import com.gafahtec.model.Estado;
import com.gafahtec.service.IEstadoService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

	private IEstadoService iEstadoService;
	
	@GetMapping
	public ResponseEntity<List<Estado>> listar() throws Exception{
		List<Estado> lista = iEstadoService.listar();
		return new ResponseEntity<List<Estado>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Estado obj = iEstadoService.listarPorId(id);
		
		if(obj.getIdEstado() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Estado>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Estado> registrar(@Valid @RequestBody Estado p) throws Exception{
		Estado obj = iEstadoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEstado()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Estado> modificar(@Valid @RequestBody Estado p) throws Exception{
		Estado obj = iEstadoService.modificar(p);
		return new ResponseEntity<Estado>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Estado obj = iEstadoService.listarPorId(id);
		
		if(obj.getIdEstado() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iEstadoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
