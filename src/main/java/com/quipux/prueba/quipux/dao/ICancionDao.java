package com.quipux.prueba.quipux.dao;

import org.springframework.data.repository.CrudRepository;

import com.quipux.prueba.quipux.model.Cancion;

public interface ICancionDao extends CrudRepository<Cancion,Long>{

}
