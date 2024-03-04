package com.quipux.prueba.quipux.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quipux.prueba.quipux.model.ListaReproduccion;
import com.quipux.prueba.quipux.response.ListaResponseRest;
import com.quipux.prueba.quipux.services.IListaService;

@RestController
@RequestMapping("/list")
public class ListaRestController {
	
	@Autowired
	private IListaService service;

	
	@GetMapping
	public ResponseEntity<ListaResponseRest> searchListas(){
		ResponseEntity<ListaResponseRest> response = service.search();
				return response;
	}
	
	@PostMapping
	public ResponseEntity<ListaResponseRest> save(@RequestBody ListaReproduccion lista){
		ResponseEntity<ListaResponseRest> response = service.save(lista);
				return response;
	}
	
	@GetMapping("/{listName}")
	public ResponseEntity<ListaResponseRest> searchCategoriesById(@PathVariable String listName){
		ResponseEntity<ListaResponseRest> response = service.searchByName(listName);
				return response;
	}
	
	@DeleteMapping("/{listName}")
	public ResponseEntity<ListaResponseRest> delete(@PathVariable String listName){
		Long id = service.DeleteByName(listName);
		ResponseEntity<ListaResponseRest> response = service.deleteById(id);
				return response;
	}




}
