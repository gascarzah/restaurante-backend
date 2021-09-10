package com.gafahtec.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompraDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCompraDetalle;
	
	@ManyToOne
	@JoinColumn(name = "id_compra", nullable = false, foreignKey = @ForeignKey(name = "FK_detalle_compra"))
	private Compra compra;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_insumo", nullable = false, foreignKey = @ForeignKey(name = "FK_insumo_compra"))
	private Insumo insumo;
	private Integer cantidad;
	private Float precioUnidad;
//	private Integer stock;
	
}
