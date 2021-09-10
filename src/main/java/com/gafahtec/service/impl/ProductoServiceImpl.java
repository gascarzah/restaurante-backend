package com.gafahtec.service.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gafahtec.dto.ProductoDto;
import com.gafahtec.model.Producto;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IProductoDetalleRepository;
import com.gafahtec.repository.IProductoRepository;
import com.gafahtec.service.IProductoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProductoServiceImpl  extends CRUDImpl<Producto, Integer>  implements IProductoService {

	
	private IProductoRepository repo;
	
	private IProductoDetalleRepository detRepo;
	
	@Override
	protected IGenericRepository<Producto, Integer> getRepo() {
		
		return repo;
	}

	@Override
	public Page<Producto> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public List<Producto> listarOrderNombre() {		
		return repo.findAll(Sort.by("nombre"));
	}
	
	public List<Producto> listarPorCategoria(Integer idCategoriaProducto) {		
		return repo.findByCategoriaProducto(idCategoriaProducto);
	}
	
	@Transactional
	@Override
	public Producto registrarYObtener(@Valid ProductoDto dto) {
		
		String randomId = UUID.randomUUID().toString();
		dto.getProducto().setRandomId(randomId);

		
		repo.save(dto.getProducto());
		
		Producto p = repo.findByRandomId(randomId).get(0);
//		System.out.println(dto.getProducto());
		dto.getProductoDetalles().forEach(pd ->{
			pd.setProducto(p);
			detRepo.save(pd);
		});
		
		return p;
	}
}
