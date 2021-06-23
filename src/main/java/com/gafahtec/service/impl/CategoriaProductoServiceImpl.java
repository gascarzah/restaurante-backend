package com.gafahtec.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.model.CategoriaProducto;
import com.gafahtec.repository.ICategoriaProductoRepository;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.service.ICategoriaProductoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CategoriaProductoServiceImpl  extends CRUDImpl<CategoriaProducto, Integer>  implements ICategoriaProductoService {

	
	private ICategoriaProductoRepository repo;
	
	@Override
	protected IGenericRepo<CategoriaProducto, Integer> getRepo() {
		
		return repo;
	}

	@Override
	public Page<CategoriaProducto> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
}

