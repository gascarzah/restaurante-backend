package com.gafahtec.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Insumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInsumo;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_unidad", nullable = false, foreignKey = @ForeignKey(name = "FK_insumo_unidad"))
	private Unidad unidad;
	@ManyToOne
	@JoinColumn(name = "id_medida", nullable = false, foreignKey = @ForeignKey(name = "FK_insumo_medida"))
	private Medida medida;
	@ManyToOne
	@JoinColumn(name = "id_categoria_insumo", nullable = false, foreignKey = @ForeignKey(name = "FK_insumo_categoria"))
	private CategoriaInsumo categoriaInsumo;
	
	private Integer cantidadPorMedida;
	private Integer stockMinimo;
	private Float precioSugerido;

	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "insumo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<CompraDetalle> compraDetalles  = new ArrayList<>();
}
