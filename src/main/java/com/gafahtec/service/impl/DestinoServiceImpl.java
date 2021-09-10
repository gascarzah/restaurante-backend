package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Destino;
import com.gafahtec.repository.IDestinoRepository;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.service.IDestinoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class DestinoServiceImpl  extends CRUDImpl<Destino, Integer>  implements IDestinoService {

	
	private IDestinoRepository repo;
	
	@Override
	protected IGenericRepository<Destino, Integer> getRepo() {
		
		return repo;
	}
}
