package com.gafahtec.model;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"compraDetalles" })
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCompra;

	private String codigoCompra;

	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name = "FK_compra_proveedor"))
	private Proveedor proveedor;
	@ManyToOne
	@JoinColumn(name = "id_tipo_recibo", nullable = false, foreignKey = @ForeignKey(name = "FK_compra_tipo_recibo"))
	private TipoRecibo tipoRecibo;
	
	private String numeroRecibo;
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalTime fecha;
	private String observacion;	
	private Float valor;
	private Float igv;
	private Float total;
	 private String randomId;
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "compra", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<CompraDetalle> compraDetalles = new ArrayList<>();;
}
