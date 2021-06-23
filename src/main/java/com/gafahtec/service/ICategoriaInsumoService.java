package com.gafahtec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.CategoriaInsumo;

public interface ICategoriaInsumoService extends ICRUD<CategoriaInsumo, Integer>{

	Page<CategoriaInsumo> listarPageable(Pageable pageable);

}
