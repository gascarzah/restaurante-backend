package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.CompraDetalle;
import com.gafahtec.repository.ICompraDetalleRepository;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.service.ICompraDetalleService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CompraDetalleServiceImpl  extends CRUDImpl<CompraDetalle, Integer>  implements ICompraDetalleService {

	
	private ICompraDetalleRepository repo;
	
	@Override
	protected IGenericRepository<CompraDetalle, Integer> getRepo() {
		
		return repo;
	}
}
