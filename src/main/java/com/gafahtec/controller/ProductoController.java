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

import com.gafahtec.dto.ProductoDto;
import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.Producto;
import com.gafahtec.service.IProductoService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

	
//	private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);
		
	private IProductoService iProductoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar() throws Exception{
//		logger.info("ingreso a listar");
		List<Producto> lista = iProductoService.listarOrderNombre();
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Producto obj = iProductoService.listarPorId(id);
		
		if(obj.getIdProducto() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Producto> registrar(@Valid @RequestBody Producto p) throws Exception{
//		Producto obj = iProductoService.registrarYObtener(p);
		Producto obj = iProductoService.registrar(p);
//		System.out.println("obj "+ obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto p) throws Exception{
		Producto obj = iProductoService.modificar(p);
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Producto obj = iProductoService.listarPorId(id);
		
		if(obj.getIdProducto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iProductoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/pageable")
	public ResponseEntity<Page<Producto>> listarPageable(@PageableDefault(sort = "nombre")Pageable pageable) throws Exception{			
		Page<Producto> paginas = iProductoService.listarPageable(pageable);
		return new ResponseEntity<Page<Producto>>(paginas, HttpStatus.OK);
	}
	
	@GetMapping("/categoria/{idCategoriaProducto}")
	public ResponseEntity<List<Producto>> listarPorCategoria(@PathVariable("idCategoriaProducto") Integer idCategoriaProducto) throws Exception{
//		logger.info("ingreso a listar");
		List<Producto> lista = iProductoService.listarPorCategoria(idCategoriaProducto);
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
}
