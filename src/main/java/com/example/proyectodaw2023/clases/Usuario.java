package com.example.proyectodaw2023.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Size(min=5,max=40, message="La longitud del nombre debe estar entre {min} y {max} caracteres")
    private String nombre;
    @Size(min=8,max=10, message="La longitud de la contraseña debe estar entre {min} y {max} caracteres")
    private String contrasena;
    @Pattern(regexp = "\\d{7,8}(-?[a-zA-Z])?", message = "El DNI debe tener 8 digitos y una letra")
    private String dni;
    private String email;
    private LocalDate fnac;

    public Usuario() {
    }
    public Usuario(Integer id, String nombre, String contrasena, String dni, String email, LocalDate fnac) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.dni = dni;
        this.email = email;
        this.fnac = fnac;
    }
    public Usuario(Usuario c) {
        this.id = c.id;
        this.nombre = c.nombre;
        this.contrasena = c.contrasena;
        this.dni = c.dni;
        this.email = c.email;
        this.fnac = c.fnac;
    }
    public Integer getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getContrasena() {
        return contrasena;
    }
    public String getDni() {
        return dni;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getFnac() {
        return fnac;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFnac(LocalDate fnac) {
        this.fnac = fnac;
    }

    /*public boolean equals(Object object) {
        if (!(object instanceof com.example.proyectodaw2023.Usuarios.Usuario)) {
            return false;
        }
        com.example.proyectodaw2023.Usuarios.Usuario other = (com.example.proyectodaw2023.Usuarios.Usuario) object;
        return this.id == other.id;
    }*/
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }

    public String toString() {
        return "Usuario[ id=" + id + " ]";
    }
}

