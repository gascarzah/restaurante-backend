package com.gafahtec.dto;

import java.util.List;

import com.gafahtec.model.Compra;
import com.gafahtec.model.CompraDetalle;

import lombok.Data;
@Data
public class CompraDto {
	private Compra compra;
	private List<CompraDetalle> compraDetalles;
}
