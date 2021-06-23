package com.gafahtec.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.model.Insumo;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IInsumoRepository;
import com.gafahtec.service.IInsumoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class InsumoServiceImpl  extends CRUDImpl<Insumo, Integer>  implements IInsumoService {

	
	private IInsumoRepository repo;
	
	@Override
	protected IGenericRepo<Insumo, Integer> getRepo() {
		
		return repo;
	}

	@Override
	public Page<Insumo> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
