package com.gafahtec.model;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Unidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUnidad;
	private String nombre;
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "unidad", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Insumo> insumos = new ArrayList<>();
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "unidad", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<ProductoDetalle> productoDetalle  = new ArrayList<>();
}
