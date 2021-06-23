package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.PedidoDetalle;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IPedidoDetalleRepository;
import com.gafahtec.service.IPedidoDetalleService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PedidoDetalleServiceImpl  extends CRUDImpl<PedidoDetalle, Integer>  implements IPedidoDetalleService {

	
	private IPedidoDetalleRepository repo;
	
	@Override
	protected IGenericRepo<PedidoDetalle, Integer> getRepo() {
		
		return repo;
	}
}

