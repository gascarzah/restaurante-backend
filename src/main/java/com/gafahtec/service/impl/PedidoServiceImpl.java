package com.gafahtec.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.gafahtec.dto.PedidoDto;
import com.gafahtec.model.Pedido;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.repository.IPedidoDetalleRepository;
import com.gafahtec.repository.IPedidoRepository;
import com.gafahtec.service.IPedidoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PedidoServiceImpl  extends CRUDImpl<Pedido, Integer>  implements IPedidoService {

	
	private IPedidoRepository repo;
	
	private IPedidoDetalleRepository detRepo;
	@Override
	protected IGenericRepo<Pedido, Integer> getRepo() {
		
		return repo;
	}
	
	
	@Transactional
	@Override
	public Pedido registrarYObtener(@Valid PedidoDto dto) {
		
		String randomId = UUID.randomUUID().toString();
		dto.getPedido().setRandomId(randomId);
		dto.getPedido().setMesas(dto.getMesas());
		
		repo.save(dto.getPedido());
		
		Pedido p = repo.findByRandomId(randomId).get(0);
		System.out.println(dto.getPedido());
		dto.getPedidoDetalles().forEach(pd ->{
			pd.setPedido(p);
			detRepo.save(pd);
		});
		
		return p;
	}
}
