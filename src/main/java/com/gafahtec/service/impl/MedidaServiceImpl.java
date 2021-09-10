package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Medida;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IMedidaRepository;
import com.gafahtec.service.IMedidaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class MedidaServiceImpl  extends CRUDImpl<Medida, Integer>  implements IMedidaService {

	
	private IMedidaRepository repo;
	
	@Override
	protected IGenericRepository<Medida, Integer> getRepo() {
		
		return repo;
	}
}
