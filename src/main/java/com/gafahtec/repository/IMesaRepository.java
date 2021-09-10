package com.gafahtec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gafahtec.model.Mesa;

public interface IMesaRepository extends IGenericRepository<Mesa, Integer>{

	@Modifying
	@Query(value = "update mesa  set ocupado = :ocupado where id_mesa = :idMesa", nativeQuery = true)
	Integer actualizar(@Param("idMesa") Integer idMesa,@Param("ocupado") boolean ocupado);

	@Query("SELECT u FROM Mesa u WHERE u.ocupado = :ocupado")
	List<Mesa> findByOcupado(@Param("ocupado") boolean ocupado);
}
