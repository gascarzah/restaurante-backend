package com.gafahtec.service;

import java.util.List;

import com.gafahtec.model.Menu;

public interface IMenuService extends ICRUD<Menu, Integer>{
	
	List<Menu> listarMenuPorUsuario(String nombre);

}
