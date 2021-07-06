package com.gafahtec.service;

import javax.validation.Valid;

import com.gafahtec.dto.PedidoDto;
import com.gafahtec.model.Pedido;

public interface IPedidoService extends ICRUD<Pedido, Integer>{

	Pedido registrarYObtener(@Valid PedidoDto p);

}
