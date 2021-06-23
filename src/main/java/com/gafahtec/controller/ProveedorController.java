package com.gafahtec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.gafahtec.model.Proveedor;
import com.gafahtec.service.IProveedorService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/proveedores")
@AllArgsConstructor
public class ProveedorController {

	private IProveedorService iProveedorService;
	
	@GetMapping
	public ResponseEntity<List<Proveedor>> listar() throws Exception{
		List<Proveedor> lista = iProveedorService.listar();
		return new ResponseEntity<List<Proveedor>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Proveedor> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Proveedor obj = iProveedorService.listarPorId(id);
		
		if(obj.getIdProveedor() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Proveedor>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Proveedor> registrar(@Valid @RequestBody Proveedor p) throws Exception{
		Proveedor obj = iProveedorService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProveedor()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Proveedor> modificar(@Valid @RequestBody Proveedor p) throws Exception{
		Proveedor obj = iProveedorService.modificar(p);
		return new ResponseEntity<Proveedor>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Proveedor obj = iProveedorService.listarPorId(id);
		
		if(obj.getIdProveedor() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iProveedorService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Proveedor>> listarPageable(@PageableDefault(sort = "razonSocial")Pageable pageable) throws Exception{			
		Page<Proveedor> proveedores = iProveedorService.listarPageable(pageable);
		return new ResponseEntity<Page<Proveedor>>(proveedores, HttpStatus.OK);
	}
}
