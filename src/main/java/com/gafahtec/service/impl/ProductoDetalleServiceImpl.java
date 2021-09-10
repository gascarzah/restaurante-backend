package com.gafahtec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Producto;
import com.gafahtec.model.ProductoDetalle;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IProductoDetalleRepository;
import com.gafahtec.service.IProductoDetalleService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProductoDetalleServiceImpl  extends CRUDImpl<ProductoDetalle, Integer>  implements IProductoDetalleService {

	
	private IProductoDetalleRepository repo;
	
	@Override
	protected IGenericRepository<ProductoDetalle, Integer> getRepo() {
		
		return repo;
	}

	@Override
	public List<ProductoDetalle> listarPorProductoId(Producto producto) {
		return repo.findByProducto(producto);
	}
}

