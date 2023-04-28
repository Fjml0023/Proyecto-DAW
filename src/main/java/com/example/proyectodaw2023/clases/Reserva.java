package com.example.proyectodaw2023.clases;


import com.example.proyectodaw2023.LocalTimeValidator;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @ValidLocalTime(message = "La hora de reserva debe estar entre las 9:00 y las 21:00")
    @Future(message = "La fecha debe estar en el futuro")
    private LocalDateTime freserva;
    @Min(value = 1, message = "La duración mínima es 1h")
    @Max(value = 3, message = "La duración máxima es 3h")
    private Integer duracion;

//@todo implimentando la relacion con sala
    @ManyToOne
    @JoinColumn(name="sala_id") // nombre de la columna que hace referencia al id de Sala
    private Sala sala;

    private Integer idEquipo;
    private Integer idUsuario;

    public Reserva() {
    }
    //@todo implimentando la relacion con sala
    public Reserva(Integer id, LocalDateTime freserva, Integer duracion,Integer idUser ,Sala sala) {
        this.id = id;
        this.freserva = freserva;
        this.duracion = duracion;
        this.idUsuario=idUser;
        this.idEquipo = null;
        this.sala = sala;
    }
    public Reserva(LocalDateTime freserva, Integer idUser,Integer duracion, Integer idEquipo, Integer idReserva) {
        this.id = idReserva;
        this.freserva = freserva;
        this.duracion = duracion;
        this.idUsuario=idUser;
        this.idEquipo=idEquipo;
    }
    public Reserva(Reserva r) {
        this.id = r.id;
        this.freserva = r.freserva;
        this.duracion = r.duracion;
        this.idEquipo = r.idEquipo;
    }

    public Integer getId() {return id;}
    public LocalDateTime getFreserva() {
        return freserva;
    }
    public Integer getDuracion() {
        return duracion;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setFreserva(LocalDateTime freserva) {
        this.freserva = freserva;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    //@todo implimentando la relacion con sala
    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    // para validar que la hora este entre las 9 y las 21 . utilizando la clase LocalTimeValidator:
    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = LocalTimeValidator.class)
    public @interface ValidLocalTime {
        String message() default "La hora debe estar entre las 9:00 y las 21:00";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }


}

