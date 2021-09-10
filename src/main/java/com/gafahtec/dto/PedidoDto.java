package com.gafahtec.dto;

import java.util.List;

import com.gafahtec.model.Pedido;
import com.gafahtec.model.PedidoDetalle;

import lombok.Data;
@Data
public class PedidoDto {

	private Pedido pedido;
	private List<PedidoDetalle> pedidoDetalles;
//	private List<Mesa> mesas;
}
