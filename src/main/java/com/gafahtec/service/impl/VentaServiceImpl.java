package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Venta;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IVentaRepository;
import com.gafahtec.service.IVentaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class VentaServiceImpl  extends CRUDImpl<Venta, Integer>  implements IVentaService {

	
	private IVentaRepository repo;
	
	@Override
	protected IGenericRepo<Venta, Integer> getRepo() {
		
		return repo;
	}
}

