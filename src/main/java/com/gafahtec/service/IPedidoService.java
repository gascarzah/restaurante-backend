package com.gafahtec.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.bean.PedidoBean;
import com.gafahtec.dto.PedidoDto;
import com.gafahtec.dto.PedidoMesaDto;
import com.gafahtec.model.Pedido;

public interface IPedidoService extends ICRUD<Pedido, Integer>{

	Pedido registrarYObtener(@Valid PedidoDto p);
	public List<PedidoMesaDto> getListaPedidoPorMesa();
	Page<Pedido> listarPageable(Pageable pageable);
	Pedido actualizarEstado(@Valid Pedido p);
	public Page<PedidoBean> listarPageableConDetalle(Pageable pageable);
	
//	public Page<Pedido> findByTipoVenta(Integer idTipoPedido, Pageable pageable)
}
