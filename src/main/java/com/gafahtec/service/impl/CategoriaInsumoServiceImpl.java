package com.gafahtec.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.model.CategoriaInsumo;
import com.gafahtec.repository.ICategoriaInsumoRepository;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.service.ICategoriaInsumoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CategoriaInsumoServiceImpl  extends CRUDImpl<CategoriaInsumo, Integer>  implements ICategoriaInsumoService {

	
	private ICategoriaInsumoRepository repo;
	
	@Override
	protected IGenericRepo<CategoriaInsumo, Integer> getRepo() {
		
		return repo;
	}

	@Override
	public Page<CategoriaInsumo> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
