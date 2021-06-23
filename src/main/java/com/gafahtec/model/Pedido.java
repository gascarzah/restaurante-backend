package com.gafahtec.model;
import java.util.ArrayList;
import java.time.LocalDateTime;
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
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedido;
	@ManyToOne
	@JoinColumn(name = "id_mesa", nullable = false, foreignKey = @ForeignKey(name = "FK_pedido_mesa"))
    private Mesa mesa;
	

	//private Empleado empleado;
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalDateTime fecha;
	 
	private boolean pagado ;
	
	@Builder.Default
	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Venta> ventas  = new ArrayList<>();;
}
