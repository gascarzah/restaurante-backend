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
import com.gafahtec.model.PedidoDetalle;
import com.gafahtec.service.IPedidoDetalleService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/pedidos-detalle")
@AllArgsConstructor
public class PedidoDetalleController {

	private IPedidoDetalleService iPedidoDetalleService;
	
	@GetMapping
	public ResponseEntity<List<PedidoDetalle>> listar() throws Exception{
		List<PedidoDetalle> lista = iPedidoDetalleService.listar();
		return new ResponseEntity<List<PedidoDetalle>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDetalle> listarPorId(@PathVariable("id") Integer id) throws Exception{
		PedidoDetalle obj = iPedidoDetalleService.listarPorId(id);
		
		if(obj.getIdPedidoDetalle() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<PedidoDetalle>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDetalle> registrar(@Valid @RequestBody PedidoDetalle p) throws Exception{
		PedidoDetalle obj = iPedidoDetalleService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPedidoDetalle()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<PedidoDetalle> modificar(@Valid @RequestBody PedidoDetalle p) throws Exception{
		PedidoDetalle obj = iPedidoDetalleService.modificar(p);
		return new ResponseEntity<PedidoDetalle>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		PedidoDetalle obj = iPedidoDetalleService.listarPorId(id);
		
		if(obj.getIdPedidoDetalle() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iPedidoDetalleService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pedido/{id}")
	public ResponseEntity<List<PedidoDetalle>> listar(@PathVariable("id") Integer id) throws Exception{
		List<PedidoDetalle> lista = iPedidoDetalleService.listarPedidoDetallePorPedido(id);
		return new ResponseEntity<List<PedidoDetalle>>(lista, HttpStatus.OK);
	}
}
