package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Caja;
import com.gafahtec.repository.ICajaRepository;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.service.ICajaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CajaServiceImpl extends CRUDImpl<Caja, Integer>  implements ICajaService {

	
	private ICajaRepository repo;
	
	@Override
	protected IGenericRepo<Caja, Integer> getRepo() {
		
		return repo;
	}
}
