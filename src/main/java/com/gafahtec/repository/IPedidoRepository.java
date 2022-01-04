package com.gafahtec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gafahtec.model.Pedido;

public interface IPedidoRepository extends IGenericRepository<Pedido, Integer>{

	List<Pedido> findByRandomId(String randomId);
	
	@Modifying
	@Query(value = "update pedido  set pagado = true where id_pedido = :idPedido", nativeQuery = true)
	Integer actualizar(@Param("idPedido") Integer idPedido);
	
	@Modifying
	@Query(value = "update pedido  set estado = :estado where id_pedido = :idPedido", nativeQuery = true)
	Integer actualizar(@Param("idPedido") Integer idPedido,@Param("estado") Integer estado);
	
	@Query("select p from Pedido p where p.tipoPedido.idTipoPedido = :idTipoPedido")
	 public Page<Pedido> findByTipoVenta(@Param("idTipoPedido") Integer idTipoPedido, Pageable pageable);
//	@Query("select p from Pedido p inner join p.childs child where p = :parent")
//	  public Page<Child> findBy(@Param("parent") Parent parent, Pageable pageable);
//	@Query(value = "select u from Pedido p where p.pagado = :pagado")
//	List<Pedido> listarPedidoSinCancelar(@Param("pagado") boolean pagado);
}
