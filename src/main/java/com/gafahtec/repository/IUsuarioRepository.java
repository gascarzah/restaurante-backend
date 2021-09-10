package com.gafahtec.repository;

import com.gafahtec.model.Usuario;

public interface IUsuarioRepository extends IGenericRepository<Usuario, Integer>  {

	//select * from usuario where username = ?
	Usuario findOneByUsername(String username);	
}
