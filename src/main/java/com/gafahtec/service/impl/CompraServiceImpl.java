package com.gafahtec.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.gafahtec.dto.CompraDto;
import com.gafahtec.model.Compra;
import com.gafahtec.model.Insumo;
import com.gafahtec.repository.ICompraDetalleRepository;
import com.gafahtec.repository.ICompraRepository;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IInsumoRepository;
import com.gafahtec.service.ICompraService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CompraServiceImpl extends CRUDImpl<Compra, Integer> implements ICompraService {

	private ICompraRepository repo;
	private ICompraDetalleRepository detRepo;
	private IInsumoRepository repoInsumo;

	@Override
	protected IGenericRepository<Compra, Integer> getRepo() {

		return repo;
	}

	@Transactional
	@Override
	public Compra registrarTransaccional(@Valid CompraDto dto) {
		String randomId = UUID.randomUUID().toString();
		dto.getCompra().setRandomId(randomId);

		repo.save(dto.getCompra());

		Compra p = repo.findByRandomId(randomId).get(0);

		dto.getCompraDetalles().forEach(pd -> {

			pd.setCompra(p);
			detRepo.save(pd);

			Insumo insumo = pd.getInsumo();

			int stock = ObjectUtils.defaultIfNull(insumo.getStockMinimo(), 0) + pd.getCantidad();
//			System.out.println(stock);
			repoInsumo.actualizar(pd.getInsumo().getIdInsumo(), stock);

		});

		return p;
	}

}
