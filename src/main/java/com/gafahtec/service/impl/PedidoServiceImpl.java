package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Pedido;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IPedidoRepository;
import com.gafahtec.service.IPedidoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PedidoServiceImpl  extends CRUDImpl<Pedido, Integer>  implements IPedidoService {

	
	private IPedidoRepository repo;
	
	@Override
	protected IGenericRepo<Pedido, Integer> getRepo() {
		
		return repo;
	}
}
