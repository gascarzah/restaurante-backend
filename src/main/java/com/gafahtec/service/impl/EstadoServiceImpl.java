package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Estado;
import com.gafahtec.repository.IEstadoRepository;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.service.IEstadoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class EstadoServiceImpl  extends CRUDImpl<Estado, Integer>  implements IEstadoService {

	
	private IEstadoRepository repo;
	
	@Override
	protected IGenericRepo<Estado, Integer> getRepo() {
		
		return repo;
	}
}
