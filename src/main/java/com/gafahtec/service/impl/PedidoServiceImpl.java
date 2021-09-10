package com.gafahtec.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.bean.PedidoBean;
import com.gafahtec.dto.PedidoDto;
import com.gafahtec.dto.PedidoMesaDto;
import com.gafahtec.model.Pedido;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IPedidoDetalleRepository;
import com.gafahtec.repository.IPedidoRepository;
import com.gafahtec.service.IMesaService;
import com.gafahtec.service.IPedidoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PedidoServiceImpl  extends CRUDImpl<Pedido, Integer>  implements IPedidoService {

	
	private IPedidoRepository repo;
	
	private IPedidoDetalleRepository detRepo;
	
	private IMesaService iMesaService;
	@Override
	protected IGenericRepository<Pedido, Integer> getRepo() {
		
		return repo;
	}
	
	
	public List<PedidoDto> listaPedidoConDetalle(){
		
		List<Pedido> pedidos = repo.findAll();
		
		List<PedidoDto> dtos = new ArrayList<>();
		pedidos.forEach( p -> {
			PedidoDto dto = new PedidoDto();
//			var pedido = p;
			var detalles = detRepo.findByPedido(p);
			dto.setPedido(p);
			dto.setPedidoDetalles(detalles);
			dtos.add(dto);
		});
		
		
		
		
		return null;
	}
	
	@Transactional
	@Override
	public Pedido registrarYObtener(@Valid PedidoDto dto) {
		//graba pedido
		String randomId = UUID.randomUUID().toString();
		dto.getPedido().setRandomId(randomId);
//		dto.getPedido().setMesas(dto.getMesas());
		
		repo.save(dto.getPedido());
		
		//graba detalle
		Pedido p = repo.findByRandomId(randomId).get(0);
//		System.out.println("antes");
//		System.out.println(dto.getPedido());
		dto.getPedidoDetalles().forEach(pd ->{
			pd.setPedido(p);
			detRepo.save(pd);
		});
//		System.out.println(p);
		
//		System.out.println(dto.getMesas());
//		dto.getMesas().forEach( m-> {
//		m.setOcupado(true);	
//		try {
//			iMesaService.registrar(m);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		});
		
		
		return p;
	}
	
	public List<PedidoMesaDto> getListaPedidoPorMesa(){

		List<PedidoMesaDto> lista = new ArrayList<>();
	
		
		
//		repo
//		.findAll().stream().filter(pmd -> !pmd.isPagado())
//		.forEach( entry -> {
//			lista.add(PedidoMesaDto
//					.builder()
//					.pedido(entry)
//					.idPedido(entry.getIdPedido())
//					.numMesa( entry.getMesas().stream().map(m-> m.getCodigo()).collect(Collectors.joining("-"))).build());
//			
//		});
		

		
		return lista;
	}
	
	@Override
	public Page<Pedido> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

	
	
	public Page<PedidoBean> listarPageableConDetalle(Pageable pageable) {
		 Page<Pedido> listaPedidoPaginado = repo.findAll(pageable);
		 Page<PedidoBean> pedidoDto = listaPedidoPaginado.map( pedido -> {
			
			PedidoBean pedidoBean = new PedidoBean();
				try {
					BeanUtils.copyProperties(pedidoBean,pedido);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				pedidoBean.setPedidosDetalle(detRepo.findByPedido(pedido));
			
			return pedidoBean;
		 });
		
		return pedidoDto;
	}
	
	@Transactional
	@Override
	public Pedido actualizarEstado(@Valid Pedido p) {
		Integer estado = 0;
		if(p.getEstado() == null) {
			p.setEstado(0);
		}
		estado = p.getEstado() + 1;
		p.setEstado(estado);
		repo.actualizar(p.getIdPedido(), p.getEstado() );
		return p;
	}
}
