package com.gafahtec.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class TipoRecibo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoRecibo;
	private String nombre;
	@Builder.Default
	@OneToMany(mappedBy = "tipoRecibo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Compra> compras = new ArrayList<>();;
	
	@Builder.Default
	@OneToMany(mappedBy = "tipoRecibo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Venta> ventas = new ArrayList<>();;
}
