package com.gafahtec.repository;

import java.util.List;

import com.gafahtec.model.Producto;

public interface IProductoRepository extends IGenericRepo<Producto, Integer>{
	
//	 @Query("SELECT p FROM Producto p where p.randomId = :randomId")
//	    Producto findByRandomId(@Param("randomId")String randomId);
	List<Producto> findByRandomId(String randomId);
}
