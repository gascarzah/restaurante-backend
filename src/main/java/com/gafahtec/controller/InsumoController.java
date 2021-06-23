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
import com.gafahtec.model.Insumo;
import com.gafahtec.service.IInsumoService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/insumos")
@AllArgsConstructor
public class InsumoController {

	private IInsumoService iInsumoService;
	
	@GetMapping
	public ResponseEntity<List<Insumo>> listar() throws Exception{
		List<Insumo> lista = iInsumoService.listar();
		return new ResponseEntity<List<Insumo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Insumo> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Insumo obj = iInsumoService.listarPorId(id);
		
		if(obj.getIdInsumo() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Insumo>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Insumo> registrar(@Valid @RequestBody Insumo p) throws Exception{
		Insumo obj = iInsumoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInsumo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Insumo> modificar(@Valid @RequestBody Insumo p) throws Exception{
		Insumo obj = iInsumoService.modificar(p);
		return new ResponseEntity<Insumo>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Insumo obj = iInsumoService.listarPorId(id);
		
		if(obj.getIdInsumo() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iInsumoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Insumo>> listarPageable(@PageableDefault(sort = "descripcion")Pageable pageable) throws Exception{			
		Page<Insumo> insumos = iInsumoService.listarPageable(pageable);
		return new ResponseEntity<Page<Insumo>>(insumos, HttpStatus.OK);
	}
}
