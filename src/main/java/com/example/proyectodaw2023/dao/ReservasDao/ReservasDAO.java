package com.example.proyectodaw2023.dao.ReservasDao;

import com.example.proyectodaw2023.clases.Reserva;

import java.util.List;

public interface ReservasDAO {
    Reserva buscaId(Integer id);
    List<Reserva> buscaTodos();
    boolean crea(Reserva reserva);
    boolean guarda(Reserva reserva);
    boolean borra(Integer id);
    int numReservas();


    public List<Reserva> reservasUser(Integer id);

    public List<Reserva> reservasEquipo(Integer id);

    public List<Reserva> reservasSala(Integer id);

}
