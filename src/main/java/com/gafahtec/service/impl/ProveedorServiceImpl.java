package com.gafahtec.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.model.Proveedor;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IProveedorRepository;
import com.gafahtec.service.IProveedorService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProveedorServiceImpl  extends CRUDImpl<Proveedor, Integer>  implements IProveedorService {

	
	private IProveedorRepository repo;
	
	@Override
	protected IGenericRepo<Proveedor, Integer> getRepo() {
		
		return repo;
	}
	
	@Override
	public Page<Proveedor> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
