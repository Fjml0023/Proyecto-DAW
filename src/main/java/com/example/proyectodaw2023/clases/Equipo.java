package com.example.proyectodaw2023.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min=4,max=50, message="La longitud del tipo debe estar entre {min} y {max} caracteres")
    private String tipo;
    @Size(min=15,max=150, message="La longitud de la descripcion debe estar entre {min} y {max} caracteres")
    private String descripcion;

    public Equipo(Integer id, String tipo, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    public Equipo(Equipo equipo){
        this.descripcion=equipo.getDescripcion();
        this.id=equipo.getId();
        this.tipo=equipo.getTipo();
    }
    public Equipo(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
