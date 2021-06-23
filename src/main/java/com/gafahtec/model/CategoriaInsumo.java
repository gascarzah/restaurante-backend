package com.gafahtec.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
public class CategoriaInsumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoriaInsumo;
	private String nombre;
	private String descripcion;
	private boolean activo;
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalDateTime fechaRegistro;
	 @JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "categoriaInsumo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private  List<Insumo> insumos = new ArrayList<>();
}
