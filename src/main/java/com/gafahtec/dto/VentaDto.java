package com.gafahtec.dto;

import java.util.List;

import com.gafahtec.model.Pedido;
import com.gafahtec.model.PedidoDetalle;
import com.gafahtec.model.Producto;
import com.gafahtec.model.Venta;

import lombok.Data;

@Data
public class VentaDto {

	private Pedido pedido;
	private List<PedidoDetalle> pedidoDetalles; 
	private List<Producto> productos; 
	private Venta venta;
}
