package com.gafahtec.repository;

import java.util.List;

import com.gafahtec.model.Compra;

public interface ICompraRepository extends IGenericRepository<Compra, Integer>{

	List<Compra> findByRandomId(String randomId);
	
	
}
