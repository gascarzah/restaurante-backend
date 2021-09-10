package com.gafahtec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gafahtec.model.Producto;

public interface IProductoRepository extends IGenericRepository<Producto, Integer>{
	
//	 @Query("SELECT p FROM Producto p where p.randomId = :randomId")
//	    Producto findByRandomId(@Param("randomId")String randomId);
	List<Producto> findByRandomId(String randomId);
	
	@Query("SELECT p FROM Producto p where p.categoriaProducto.idCategoriaProducto = :idCategoriaProducto")
	List<Producto> findByCategoriaProducto(Integer idCategoriaProducto);
	@Modifying
	@Query(value = "update producto  set stock = :stock where id_producto = :idProducto", nativeQuery = true)
	Integer actualizar(@Param("idProducto") Integer idProducto, @Param("stock") Integer stock);
}
