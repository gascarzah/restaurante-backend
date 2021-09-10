package com.gafahtec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Pedido;
import com.gafahtec.model.PedidoDetalle;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IPedidoDetalleRepository;
import com.gafahtec.service.IPedidoDetalleService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PedidoDetalleServiceImpl  extends CRUDImpl<PedidoDetalle, Integer>  implements IPedidoDetalleService {

	
	private IPedidoDetalleRepository repo;
	
	@Override
	protected IGenericRepository<PedidoDetalle, Integer> getRepo() {
		
		return repo;
	}

	@Override
	public List<PedidoDetalle> listarPedidoDetallePorPedido(Integer id) {
		
		return repo.findByPedido(Pedido.builder().idPedido(id).build());
	}
}

