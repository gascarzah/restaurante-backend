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
import com.gafahtec.model.Destino;
import com.gafahtec.service.IDestinoService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/destinos")
@AllArgsConstructor
public class DestinoController {

	private IDestinoService iDestinoService;
	
	@GetMapping
	public ResponseEntity<List<Destino>> listar() throws Exception{
		List<Destino> lista = iDestinoService.listar();
		return new ResponseEntity<List<Destino>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Destino> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Destino obj = iDestinoService.listarPorId(id);
		
		if(obj.getIdDestino() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Destino>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Destino> registrar(@Valid @RequestBody Destino p) throws Exception{
		Destino obj = iDestinoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdDestino()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Destino> modificar(@Valid @RequestBody Destino p) throws Exception{
		Destino obj = iDestinoService.modificar(p);
		return new ResponseEntity<Destino>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Destino obj = iDestinoService.listarPorId(id);
		
		if(obj.getIdDestino() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iDestinoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
