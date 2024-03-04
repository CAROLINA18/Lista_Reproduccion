package com.quipux.prueba.quipux.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "lista_reproduccion")
@SQLDelete(sql = "UPDATE lista_reproduccion SET deleted=true WHERE id = ?")
public class ListaReproduccion implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nombre;
    private String descripcion;
    
    @JsonIgnore
	private Boolean deleted = false;
    
    
    @ManyToMany( fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST , CascadeType.MERGE ,CascadeType.REMOVE})
    @JoinTable(

            name = "lista_cancion" , joinColumns = @JoinColumn(name = "lista_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cancion_id" , referencedColumnName = "id")
    )
    private List<Cancion> canciones;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
    public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}

