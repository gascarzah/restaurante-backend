package com.gafahtec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.gafahtec.model.CategoriaProducto;
import com.gafahtec.service.ICategoriaProductoService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/categoria-productos")
@AllArgsConstructor
public class CategoriaProductoController {

	private ICategoriaProductoService iCategoriaProductoService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaProducto>> listar() throws Exception{
		List<CategoriaProducto> lista = iCategoriaProductoService.listar();
		return new ResponseEntity<List<CategoriaProducto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaProducto> listarPorId(@PathVariable("id") Integer id) throws Exception{
		CategoriaProducto obj = iCategoriaProductoService.listarPorId(id);
		
		if(obj.getIdCategoriaProducto() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<CategoriaProducto>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaProducto> registrar(@Valid @RequestBody CategoriaProducto p) throws Exception{
		CategoriaProducto obj = iCategoriaProductoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCategoriaProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<CategoriaProducto> modificar(@Valid @RequestBody CategoriaProducto p) throws Exception{
		CategoriaProducto obj = iCategoriaProductoService.modificar(p);
		return new ResponseEntity<CategoriaProducto>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		CategoriaProducto obj = iCategoriaProductoService.listarPorId(id);
		
		if(obj.getIdCategoriaProducto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iCategoriaProductoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<CategoriaProducto>> listarPageable(Pageable pageable) throws Exception{			
		Page<CategoriaProducto> paginas = iCategoriaProductoService.listarPageable(pageable);
		return new ResponseEntity<Page<CategoriaProducto>>(paginas, HttpStatus.OK);
	}
}
