package com.gafahtec.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.model.Mesa;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IMesaRepository;
import com.gafahtec.service.IMesaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class MesaServiceImpl  extends CRUDImpl<Mesa, Integer>  implements IMesaService {

	
	private IMesaRepository repo;
	
	@Override
	protected IGenericRepo<Mesa, Integer> getRepo() {
		
		return repo;
	}

	@Override
	public Page<Mesa> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
