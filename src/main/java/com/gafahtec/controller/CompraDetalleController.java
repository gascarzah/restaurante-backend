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
import com.gafahtec.model.CompraDetalle;
import com.gafahtec.service.ICompraDetalleService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/compras-detalle")
@AllArgsConstructor
public class CompraDetalleController {

	private ICompraDetalleService iCompraDetalleService;
	
	@GetMapping
	public ResponseEntity<List<CompraDetalle>> listar() throws Exception{
		List<CompraDetalle> lista = iCompraDetalleService.listar();
		return new ResponseEntity<List<CompraDetalle>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompraDetalle> listarPorId(@PathVariable("id") Integer id) throws Exception{
		CompraDetalle obj = iCompraDetalleService.listarPorId(id);
		
		if(obj.getIdCompraDetalle() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<CompraDetalle>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CompraDetalle> registrar(@Valid @RequestBody CompraDetalle p) throws Exception{
		CompraDetalle obj = iCompraDetalleService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCompraDetalle()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<CompraDetalle> modificar(@Valid @RequestBody CompraDetalle p) throws Exception{
		CompraDetalle obj = iCompraDetalleService.modificar(p);
		return new ResponseEntity<CompraDetalle>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		CompraDetalle obj = iCompraDetalleService.listarPorId(id);
		
		if(obj.getIdCompraDetalle() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iCompraDetalleService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
