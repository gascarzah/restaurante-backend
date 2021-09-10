package com.gafahtec.service;

import javax.validation.Valid;

import com.gafahtec.dto.CompraDto;
import com.gafahtec.model.Compra;

public interface ICompraService extends ICRUD<Compra, Integer>{

	Compra registrarTransaccional(@Valid CompraDto dto);

}
