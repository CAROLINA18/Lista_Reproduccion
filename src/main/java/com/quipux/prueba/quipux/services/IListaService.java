package com.quipux.prueba.quipux.services;

import org.springframework.http.ResponseEntity;

import com.quipux.prueba.quipux.model.ListaReproduccion;
import com.quipux.prueba.quipux.response.ListaResponseRest;

public interface IListaService {
	public ResponseEntity<ListaResponseRest> search();
	public ResponseEntity<ListaResponseRest> searchByName(String nombre);
	public Long DeleteByName(String nombre);
	public ResponseEntity<ListaResponseRest> save(ListaReproduccion listaReproduccion);
	public ResponseEntity<ListaResponseRest> deleteById(Long id);

}
