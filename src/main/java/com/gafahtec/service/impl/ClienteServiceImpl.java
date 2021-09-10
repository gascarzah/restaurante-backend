package com.gafahtec.service.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gafahtec.model.Cliente;
import com.gafahtec.repository.IClienteRepository;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.service.IClienteService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ClienteServiceImpl  extends CRUDImpl<Cliente, Integer>  implements IClienteService {

	
	private IClienteRepository repo;
	
	@Override
	protected IGenericRepository<Cliente, Integer> getRepo() {
		
		return repo;
	}
	
	@Override
	public Page<Cliente> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	
	@Override
	public List<Cliente> listarOrderNombre() {		
		return repo.findAll(Sort.by("apellidoPaterno"));
	}
	
	@Transactional
	@Override
	public Cliente registrarYObtener(@Valid Cliente oldCliente) {
		String randomId = UUID.randomUUID().toString();
		oldCliente.setRandomId(randomId);
//		System.out.println("oldCliente "+ oldCliente);
		repo.save(oldCliente);

		Cliente newCliente = repo.findByRandomId(randomId).get(0);

//		System.out.println("newCliente "+ newCliente);

		
		return newCliente;
	}
}

