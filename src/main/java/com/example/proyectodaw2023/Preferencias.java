package com.example.proyectodaw2023;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.security.Principal;

@Named( value="prefs")
@SessionScoped
public class Preferencias implements Serializable {

    @Inject
    Principal usuario;
    public Preferencias () { };
    private String ultimoLibro = "";
    public String getUltimoLibro() { return ultimoLibro; }
    public void setUltimoLibro(String ultimoLibro) { this.ultimoLibro = ultimoLibro; }


    private Integer ActualUsuarioid = 0;

    public Integer getActualUsuarioid() {
        return ActualUsuarioid;
    }
    public void setActualUsuarioid(Integer ActualUsuarioid) {
        this.ActualUsuarioid = ActualUsuarioid;
    }


}
