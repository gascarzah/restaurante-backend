package com.gafahtec.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gafahtec.model.Insumo;

public interface IInsumoRepository extends IGenericRepository<Insumo, Integer>{

	@Modifying
//	@Query("update OrdineMacchina o set o.durataEsecuzione = o.durataEsecuzione + :incremento where o.idOrdineMacchina = :idOrdineMacchina")
	@Query(value = "update insumo  set stock_minimo = :stock where id_insumo = :idInsumo", nativeQuery = true)
	Integer actualizar(@Param("idInsumo") Integer idInsumo, @Param("stock") Integer stock);
}
