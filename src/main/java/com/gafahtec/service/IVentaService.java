package com.gafahtec.service;

import javax.validation.Valid;

import com.gafahtec.dto.VentaDto;
import com.gafahtec.model.Venta;

public interface IVentaService extends ICRUD<Venta, Integer>{

	Venta registrarTransaccion(@Valid VentaDto p);

}
