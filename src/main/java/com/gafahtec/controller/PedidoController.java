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

import com.gafahtec.bean.PedidoBean;
import com.gafahtec.dto.PedidoDto;
import com.gafahtec.dto.PedidoMesaDto;
import com.gafahtec.dto.VentaDto;
import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.Mesa;
import com.gafahtec.model.Pedido;
import com.gafahtec.model.Venta;
import com.gafahtec.service.IPedidoService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

	private IPedidoService iPedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listar() throws Exception{
		List<Pedido> lista = iPedidoService.listar();
		
		return new ResponseEntity<List<Pedido>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Pedido obj = iPedidoService.listarPorId(id);
		
		if(obj.getIdPedido() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Pedido>(obj, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Pedido> registrar(@Valid @RequestBody PedidoDto p) throws Exception{
		Pedido obj = iPedidoService.registrarYObtener(p);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPedido()).toUri();
		return ResponseEntity.created(location).build();
	}
//	@PostMapping
//	public ResponseEntity<Pedido> registrar(@Valid @RequestBody Pedido p) throws Exception{
//		Pedido obj = iPedidoService.registrar(p);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPedido()).toUri();
//		return ResponseEntity.created(location).build();
//	}
	
	@PutMapping
	public ResponseEntity<Pedido> modificar(@Valid @RequestBody Pedido p) throws Exception{
		Pedido obj = iPedidoService.modificar(p);
		return new ResponseEntity<Pedido>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Pedido obj = iPedidoService.listarPorId(id);
		
		if(obj.getIdPedido() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iPedidoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	
	@GetMapping("/mesas")
	public ResponseEntity<List<PedidoMesaDto>> listarPedidoPorMesa() throws Exception{
		List<PedidoMesaDto> lista = iPedidoService.getListaPedidoPorMesa();
		return new ResponseEntity<List<PedidoMesaDto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<PedidoBean>> listarPageable(Pageable pageable) throws Exception{			
		Page<PedidoBean> paginas = iPedidoService.listarPageableConDetalle(pageable);
		System.out.println(paginas);
		return new ResponseEntity<Page<PedidoBean>>(paginas, HttpStatus.OK);
	}
	
//	@GetMapping("/pageable")
//	public ResponseEntity<Page<Pedido>> listarPageable(Pageable pageable) throws Exception{			
//		Page<Pedido> paginas = iPedidoService.listarPageable(pageable);
//		System.out.println(paginas);
//		return new ResponseEntity<Page<Pedido>>(paginas, HttpStatus.OK);
//	}
	
	@PostMapping("/actualizarEstado")
	public ResponseEntity<Void> registrar(@Valid @RequestBody Pedido p) throws Exception{
		Pedido obj = iPedidoService.actualizarEstado(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPedido()).toUri();
		return ResponseEntity.created(location).build();
	}

}
