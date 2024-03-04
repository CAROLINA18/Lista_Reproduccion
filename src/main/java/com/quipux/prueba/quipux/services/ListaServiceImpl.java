package com.quipux.prueba.quipux.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quipux.prueba.quipux.dao.ICancionDao;
import com.quipux.prueba.quipux.dao.IListaDao;
import com.quipux.prueba.quipux.model.Cancion;
import com.quipux.prueba.quipux.model.ListaReproduccion;
import com.quipux.prueba.quipux.response.ListaResponseRest;



@Service
public class ListaServiceImpl implements IListaService {
	
	@Autowired
	private IListaDao listaDao;
	@Autowired
	private ICancionDao cancionDao;

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ListaResponseRest> search() {
		ListaResponseRest response = new ListaResponseRest();
		Date fecha = new Date();
		try {
			   
			List<ListaReproduccion> lista = (List<ListaReproduccion>) listaDao.findByCreated(false);
			response.getListaResponse().setLista(lista);
			response.setMetadata("Exito", "200", fecha.toString()  );

		}catch(Exception e) {
			response.setMetadata("Respuesta Fallida", "500", fecha.toString());
			e.getStackTrace();
			return new ResponseEntity<ListaResponseRest>(response , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ListaResponseRest>(response , HttpStatus.OK);
		
	}

	@Transactional(readOnly = true)
	public ResponseEntity<ListaResponseRest> searchByName(String nombre) {
		ListaResponseRest response = new ListaResponseRest();
		List<ListaReproduccion> list = new ArrayList<>();
		try {
			Optional<ListaReproduccion> lista = listaDao.findByName(nombre);
			if(lista.isPresent()) {
				list.add(lista.get());
				response.getListaResponse().setLista(list);
				response.setMetadata("Respuesta Exitosa", "200", "lista encontrada");
			}else {
				response.setMetadata("Respuesta Fallida", "404", "No se encontro nada");
				return new ResponseEntity<ListaResponseRest>(response , HttpStatus.NOT_FOUND);
			}
			}catch(Exception e) {
				response.setMetadata("Respuesta No Corrects", "-1", "Error exception");
				e.getStackTrace();
				return new ResponseEntity<ListaResponseRest>(response , HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<ListaResponseRest>(response , HttpStatus.OK);

	}

	@Override
	@Transactional
	public ResponseEntity<ListaResponseRest> save(ListaReproduccion listaReproduccion) {
		ListaResponseRest response  = new ListaResponseRest();
		List<ListaReproduccion> list = new ArrayList<>();
		List<Cancion> listCanciones = new ArrayList<>();
		try {
			
			Optional<ListaReproduccion> listaNombre = listaDao.findByName(listaReproduccion.getNombre());
			
			if(!listaNombre.isPresent() ) {
				ListaReproduccion ListaSave= listaDao.save(listaReproduccion);
				Cancion cancionesSave = null;
			
			if( ListaSave.getNombre() != null && ListaSave != null  ) {
				list.add(ListaSave);
				for(int i = 0; i<listaReproduccion.getCanciones().size();i++) {
					cancionesSave= cancionDao.save(listaReproduccion.getCanciones().get(i));
					
				}			
				listCanciones.add(cancionesSave);				
				response.getListaResponse().setLista(list);
				response.setMetadata("Respuesta ok", "00", "categoria guardada ");
			}
		}else {
			
			return new ResponseEntity<ListaResponseRest>(response , HttpStatus.BAD_REQUEST);
		}	
		}catch(Exception e) {
			response.setMetadata("Respuesta Fallida", "500", "Error guardando");
			e.getStackTrace();
			return new ResponseEntity<ListaResponseRest>(response , HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ListaResponseRest>(response , HttpStatus.CREATED);

	}

	@Override
	@Transactional
	public ResponseEntity<ListaResponseRest> deleteById(Long id) {
		
		ListaResponseRest response  = new ListaResponseRest();
		try {
			if(id == null) {
				response.setMetadata("Respuesta Fallida", "404", "Error eliminando");
				return new ResponseEntity<ListaResponseRest>(response , HttpStatus.NOT_FOUND);
			}
			listaDao.deleteById(id);
			response.setMetadata("respuesta ok", "204", "Registro eliminado");
			
		}catch(Exception e) {
			response.setMetadata("Respuesta Fallida", "404", "Error eliminando");
			return new ResponseEntity<ListaResponseRest>(response , HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ListaResponseRest>(response , HttpStatus.NO_CONTENT);
		
		
	}

	@Override
	public Long DeleteByName(String nombre) {
		Long id = listaDao.DeleteByName(nombre);
		return id;
	}
	
	


}
