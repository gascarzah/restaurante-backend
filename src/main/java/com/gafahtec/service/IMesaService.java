package com.gafahtec.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.Mesa;

public interface IMesaService extends ICRUD<Mesa, Integer>{

	Page<Mesa> listarPageable(Pageable pageable);

	List<Mesa> listarMesasDesocupadas();
}
