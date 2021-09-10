package com.gafahtec.service;

import java.util.List;

import com.gafahtec.model.PedidoDetalle;

public interface IPedidoDetalleService extends ICRUD<PedidoDetalle, Integer>{

	List<PedidoDetalle> listarPedidoDetallePorPedido(Integer id);

}
