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
import com.gafahtec.model.TipoRecibo;
import com.gafahtec.service.ITipoReciboService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/tipoRecibos")
@AllArgsConstructor
public class TipoReciboController {

	private ITipoReciboService iTipoReciboService;
	
	@GetMapping
	public ResponseEntity<List<TipoRecibo>> listar() throws Exception{
		List<TipoRecibo> lista = iTipoReciboService.listar();
		return new ResponseEntity<List<TipoRecibo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoRecibo> listarPorId(@PathVariable("id") Integer id) throws Exception{
		TipoRecibo obj = iTipoReciboService.listarPorId(id);
		
		if(obj.getIdTipoRecibo() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<TipoRecibo>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TipoRecibo> registrar(@Valid @RequestBody TipoRecibo p) throws Exception{
		TipoRecibo obj = iTipoReciboService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoRecibo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<TipoRecibo> modificar(@Valid @RequestBody TipoRecibo p) throws Exception{
		TipoRecibo obj = iTipoReciboService.modificar(p);
		return new ResponseEntity<TipoRecibo>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		TipoRecibo obj = iTipoReciboService.listarPorId(id);
		
		if(obj.getIdTipoRecibo() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iTipoReciboService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
