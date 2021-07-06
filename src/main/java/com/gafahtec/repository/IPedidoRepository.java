package com.gafahtec.repository;

import java.util.List;

import com.gafahtec.model.Pedido;

public interface IPedidoRepository extends IGenericRepo<Pedido, Integer>{

	List<Pedido> findByRandomId(String randomId);
}
