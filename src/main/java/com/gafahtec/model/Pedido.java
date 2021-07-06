package com.gafahtec.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@ToString(exclude = {"mesas","pedidosDetalle", "ventas" })
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedido;
//	@ManyToOne
//	@JoinColumn(name = "id_mesa", nullable = false, foreignKey = @ForeignKey(name = "FK_pedido_mesa"))
//    private Mesa mesa;
//	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mesa_pedido", joinColumns = @JoinColumn(name = "id_pedido", referencedColumnName = "idPedido"), inverseJoinColumns = @JoinColumn(name = "id_mesa", referencedColumnName = "idMesa"))
	private List<Mesa> mesas;

	
	//private Empleado empleado;
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalDateTime fecha;
	 private String randomId;
	private boolean pagado ;
	
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<PedidoDetalle> pedidosDetalle  = new ArrayList<>();;
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Venta> ventas  = new ArrayList<>();
}
