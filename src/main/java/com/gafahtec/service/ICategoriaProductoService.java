package com.gafahtec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.CategoriaProducto;

public interface ICategoriaProductoService extends ICRUD<CategoriaProducto, Integer>{

	Page<CategoriaProducto> listarPageable(Pageable pageable);

}
