package com.gafahtec.repository;

import java.util.List;

import com.gafahtec.model.Pedido;
import com.gafahtec.model.PedidoDetalle;

public interface IPedidoDetalleRepository extends IGenericRepository<PedidoDetalle, Integer>{
	
	List<PedidoDetalle> findByPedido(Pedido pedido);

}
