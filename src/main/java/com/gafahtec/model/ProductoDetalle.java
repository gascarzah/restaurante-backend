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
public class ProductoDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProductoDetalle;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_producto"))
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "id_insumo", nullable = false, foreignKey = @ForeignKey(name = "FK_detalle"))
	private Insumo insumo;
	
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name = "id_unidad", nullable = false, foreignKey = @ForeignKey(name = "FK_unidad"))
	private Unidad unidad;
}
