package com.gafahtec.dto;

import java.util.List;

import com.gafahtec.model.Producto;
import com.gafahtec.model.ProductoDetalle;

import lombok.Data;

@Data
public class ProductoDto {

	private Producto producto;
	private List<ProductoDetalle> productoDetalles;
}
