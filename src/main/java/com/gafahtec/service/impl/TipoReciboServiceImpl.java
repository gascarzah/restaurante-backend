package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.TipoRecibo;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.ITipoRecibo;
import com.gafahtec.service.ITipoReciboService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class TipoReciboServiceImpl  extends CRUDImpl<TipoRecibo, Integer>  implements ITipoReciboService {

	
	private ITipoRecibo repo;
	
	@Override
	protected IGenericRepository<TipoRecibo, Integer> getRepo() {
		
		return repo;
	}
}

