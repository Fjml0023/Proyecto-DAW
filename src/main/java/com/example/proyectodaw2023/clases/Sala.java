package com.example.proyectodaw2023.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Size(min=15,max=150, message="La longitud de la descripcion debe estar entre {min} y {max} caracteres")
    private String descripcion;
    @Size(min=5,max=150, message="La longitud de la ubicación debe estar entre {min} y {max} caracteres")
    private String ubicacion;
    @Size(min=5,max=20, message="La longitud del tamaño debe estar entre {min} y {max} caracteres")
    private String tamano;

    public Sala(Integer id, String descripcion, String ubicacion, String tamano) {
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.tamano = tamano;
    }

    public Sala(Sala sala) {
        this.id = sala.id;
        this.descripcion = sala.descripcion;
        this.ubicacion = sala.ubicacion;
        this.tamano = sala.tamano;
    }

    public Sala() {}

    public Integer getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public String getTamano() {
        return tamano;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }


}
