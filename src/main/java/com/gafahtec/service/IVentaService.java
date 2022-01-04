package com.gafahtec.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.dto.VentaDto;
import com.gafahtec.model.Venta;

public interface IVentaService extends ICRUD<Venta, Integer>{

	Venta registrarTransaccion(@Valid VentaDto p);
	Page<Venta> listarPageable(Pageable pageable);
}
