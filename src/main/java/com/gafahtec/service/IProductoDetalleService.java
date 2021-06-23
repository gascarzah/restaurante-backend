package com.gafahtec.service;

import java.util.List;

import com.gafahtec.model.Producto;
import com.gafahtec.model.ProductoDetalle;

public interface IProductoDetalleService extends ICRUD<ProductoDetalle, Integer>{

	List<ProductoDetalle> listarPorProductoId(Producto producto);
}
