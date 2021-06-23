package com.gafahtec.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.model.Empleado;
import com.gafahtec.repository.IEmpleadoRepository;
import com.gafahtec.repository.IGenericRepo;
import com.gafahtec.service.IEmpleadoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class EmpleadoServiceImpl  extends CRUDImpl<Empleado, Integer>  implements IEmpleadoService {

	
	private IEmpleadoRepository repo;
	
	@Override
	protected IGenericRepo<Empleado, Integer> getRepo() {
		
		return repo;
	}

	@Override
	public Page<Empleado> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
