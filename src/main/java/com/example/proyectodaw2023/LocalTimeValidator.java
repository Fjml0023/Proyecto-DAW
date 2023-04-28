package com.example.proyectodaw2023;

import com.example.proyectodaw2023.clases.Reserva;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class LocalTimeValidator implements ConstraintValidator<Reserva.ValidLocalTime, LocalDateTime> {
    public void initialize(Reserva.ValidLocalTime constraint) {
    }

    public boolean isValid(LocalDateTime obj, ConstraintValidatorContext context) {
        if (obj == null) { // Si el valor es nulo siempre retornará verdadero
            return true;
        }
        int hora = obj.getHour();
        // Valida que la hora esté entre las 9:00 y las 21:00
        return hora >= 9 && hora <= 21;
    }
}
