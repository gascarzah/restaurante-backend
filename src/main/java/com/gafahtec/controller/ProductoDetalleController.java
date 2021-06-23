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
import com.gafahtec.model.Producto;
import com.gafahtec.model.ProductoDetalle;
import com.gafahtec.service.IProductoDetalleService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/productos-detalle")
@AllArgsConstructor
public class ProductoDetalleController {

	private IProductoDetalleService iProductoDetalleService;
	
	@GetMapping
	public ResponseEntity<List<ProductoDetalle>> listar() throws Exception{
		List<ProductoDetalle> lista = iProductoDetalleService.listar();
		return new ResponseEntity<List<ProductoDetalle>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ProductoDetalle>> listarPorId(@PathVariable("id") Integer id) throws Exception{
		var p = new Producto();
		p.setIdProducto(id);
		List<ProductoDetalle> obj = iProductoDetalleService.listarPorProductoId(p);
		
//		if(obj.getIdProductoDetalle() == null) {
//			throw new ModeloNotFoundException("Id no encontrado " + id );
//		}
		
		return new ResponseEntity<List<ProductoDetalle>>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProductoDetalle> registrar(@Valid @RequestBody ProductoDetalle p) throws Exception{
		ProductoDetalle obj = iProductoDetalleService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProductoDetalle()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<ProductoDetalle> modificar(@Valid @RequestBody ProductoDetalle p) throws Exception{
		ProductoDetalle obj = iProductoDetalleService.modificar(p);
		return new ResponseEntity<ProductoDetalle>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		ProductoDetalle obj = iProductoDetalleService.listarPorId(id);
		
		if(obj.getIdProductoDetalle() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iProductoDetalleService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
