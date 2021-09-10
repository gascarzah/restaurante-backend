package com.gafahtec.repository;

import java.util.List;

import com.gafahtec.model.Producto;
import com.gafahtec.model.ProductoDetalle;

public interface IProductoDetalleRepository extends IGenericRepository<ProductoDetalle, Integer>{

	List<ProductoDetalle> findByProducto(Producto producto);
}
