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
import com.gafahtec.model.CategoriaInsumo;
import com.gafahtec.service.ICategoriaInsumoService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/categoria-insumos")
@AllArgsConstructor
public class CategoriaInsumoController {

	private ICategoriaInsumoService iCategoriaInsumoService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaInsumo>> listar() throws Exception{
		List<CategoriaInsumo> lista = iCategoriaInsumoService.listar();
		return new ResponseEntity<List<CategoriaInsumo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaInsumo> listarPorId(@PathVariable("id") Integer id) throws Exception{
		CategoriaInsumo obj = iCategoriaInsumoService.listarPorId(id);
		
		if(obj.getIdCategoriaInsumo() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<CategoriaInsumo>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaInsumo> registrar(@Valid @RequestBody CategoriaInsumo p) throws Exception{
		CategoriaInsumo obj = iCategoriaInsumoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCategoriaInsumo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<CategoriaInsumo> modificar(@Valid @RequestBody CategoriaInsumo p) throws Exception{
		CategoriaInsumo obj = iCategoriaInsumoService.modificar(p);
		return new ResponseEntity<CategoriaInsumo>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		CategoriaInsumo obj = iCategoriaInsumoService.listarPorId(id);
		
		if(obj.getIdCategoriaInsumo() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iCategoriaInsumoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<CategoriaInsumo>> listarPageable(Pageable pageable) throws Exception{			
		Page<CategoriaInsumo> paginas = iCategoriaInsumoService.listarPageable(pageable);
		return new ResponseEntity<Page<CategoriaInsumo>>(paginas, HttpStatus.OK);
	}
}
