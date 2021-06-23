package com.gafahtec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.Proveedor;

public interface IProveedorService extends ICRUD<Proveedor, Integer>{

	Page<Proveedor> listarPageable(Pageable pageable);

}
