package com.quipux.prueba.quipux;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.quipux.prueba.quipux.dao.ICancionDao;
import com.quipux.prueba.quipux.dao.IListaDao;
import com.quipux.prueba.quipux.model.ListaReproduccion;
import com.quipux.prueba.quipux.response.ListaResponseRest;
import com.quipux.prueba.quipux.services.ListaServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ListaServiceImplTest {

    @Mock
    private IListaDao listaDao;

    @Mock
    private ICancionDao cancionDao;

    @InjectMocks
    private ListaServiceImpl listaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSearch() {
        ListaReproduccion listaReproduccion = new ListaReproduccion();
        listaReproduccion.setId(1L);
        listaReproduccion.setNombre("Lista1");
        List<ListaReproduccion> listaReproduccionList = new ArrayList<>();
        listaReproduccionList.add(listaReproduccion);
        when(listaDao.findByCreated(false)).thenReturn(listaReproduccionList);
        ResponseEntity<ListaResponseRest> responseEntity = listaService.search();


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().getListaResponse().getLista().size());
    }
    
    @Test
    void testSearchByName_ListaEncontrada() {
        // Arrange
        String nombre = "Lista1";
        ListaReproduccion listaReproduccion = new ListaReproduccion();
        listaReproduccion.setId(1L);
        listaReproduccion.setNombre(nombre);
        Optional<ListaReproduccion> listaOptional = Optional.of(listaReproduccion);
        when(listaDao.findByName(nombre)).thenReturn(listaOptional);
        ResponseEntity<ListaResponseRest> responseEntity = listaService.searchByName(nombre);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testSearchByName_ListaNoEncontrada() {
        // Arrange
        String nombre = "Lista2";
        Optional<ListaReproduccion> listaOptional = Optional.empty();
        when(listaDao.findByName(nombre)).thenReturn(listaOptional);
        ResponseEntity<ListaResponseRest> responseEntity = listaService.searchByName(nombre);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testSearchByName_Excepcion() {
        String nombre = "Lista3";
        when(listaDao.findByName(nombre)).thenThrow(new RuntimeException("Error buscando lista"));
        ResponseEntity<ListaResponseRest> responseEntity = listaService.searchByName(nombre);
        assertEquals(HttpStatus. NOT_FOUND, responseEntity.getStatusCode());

    }
    
    
}