package com.quipux.prueba.quipux.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.quipux.prueba.quipux.model.ListaReproduccion;

public interface IListaDao extends CrudRepository<ListaReproduccion,Long> {
	@Query("select l from ListaReproduccion l where l.deleted = false and  l.nombre = ?1")
	Optional<ListaReproduccion> findByName(String nombre);
	
	@Query("select id from ListaReproduccion l where l.nombre = ?1 and l.deleted = false")
	Long DeleteByName(String nombre);
	
	@Query("select l from ListaReproduccion l where l.deleted = ?1")
	List<ListaReproduccion> findByCreated(boolean estado);

}
