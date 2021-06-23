package com.gafahtec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.Mesa;

public interface IMesaService extends ICRUD<Mesa, Integer>{

	Page<Mesa> listarPageable(Pageable pageable);
}
