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
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProveedor;
//	private String dni;
	private String ruc;
	private String razonSocial;
	private String direccion;
	private String telefono;
//	private String celular;
	private String email;
	private String cuenta01;
	private String cuenta02;
	private String observacion;
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalDateTime fechaRegistro;
	 @JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "proveedor", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Compra> compras = new ArrayList<>();;

}
