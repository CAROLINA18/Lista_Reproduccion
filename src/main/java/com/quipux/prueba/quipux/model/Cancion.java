package com.quipux.prueba.quipux.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
@Table(name = "cancion")
public class Cancion implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String titulo;
    private String artista;
    private String anno;
    private String genero;
    
    @JsonIgnore
	private Boolean deleted;
    
   
    @ManyToMany(mappedBy = "canciones")
    @JsonIgnore
    private List<ListaReproduccion> listaReproduccions;
    
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public List<ListaReproduccion> getListaReproduccions() {
		return listaReproduccions;
	}
	public void setListaReproduccions(List<ListaReproduccion> listaReproduccions) {
		this.listaReproduccions = listaReproduccions;
	}
	
	public Boolean getDeleted() {
			return deleted;
		}
		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}
}
