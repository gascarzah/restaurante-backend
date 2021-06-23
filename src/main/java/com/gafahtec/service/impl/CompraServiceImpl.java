package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Compra;
import com.gafahtec.repository.ICompraRepository;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.service.ICompraService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CompraServiceImpl  extends CRUDImpl<Compra, Integer>  implements ICompraService {

	
	private ICompraRepository repo;
	
	@Override
	protected IGenericRepo<Compra, Integer> getRepo() {
		
		return repo;
	}
}
