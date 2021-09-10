package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Unidad;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IUnidadRepository;
import com.gafahtec.service.IUnidadService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UnidadServiceImpl  extends CRUDImpl<Unidad, Integer>  implements IUnidadService {

	
	private IUnidadRepository repo;
	
	@Override
	protected IGenericRepository<Unidad, Integer> getRepo() {
		
		return repo;
	}
}

