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

import com.gafahtec.dto.CompraDto;
import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.Compra;
import com.gafahtec.service.ICompraService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/compras")
@AllArgsConstructor
public class CompraController {

	private ICompraService iCompraService;
	
	@GetMapping
	public ResponseEntity<List<Compra>> listar() throws Exception{
		List<Compra> lista = iCompraService.listar();
		return new ResponseEntity<List<Compra>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Compra> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Compra obj = iCompraService.listarPorId(id);
		
		if(obj.getIdCompra() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Compra>(obj, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Compra> registrar(@Valid @RequestBody CompraDto dto) throws Exception{
		Compra obj = iCompraService.registrarTransaccional(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCompra()).toUri();
		return ResponseEntity.created(location).build();
	}
//	@PostMapping
//	public ResponseEntity<Compra> registrar(@Valid @RequestBody Compra p) throws Exception{
//		Compra obj = iCompraService.registrar(p);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCompra()).toUri();
//		return ResponseEntity.created(location).build();
//	}
	
	@PutMapping
	public ResponseEntity<Compra> modificar(@Valid @RequestBody Compra p) throws Exception{
		Compra obj = iCompraService.modificar(p);
		return new ResponseEntity<Compra>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Compra obj = iCompraService.listarPorId(id);
		
		if(obj.getIdCompra() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iCompraService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
