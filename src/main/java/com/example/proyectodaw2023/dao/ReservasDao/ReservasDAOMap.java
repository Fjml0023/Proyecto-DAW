package com.example.proyectodaw2023.dao.ReservasDao;

import com.example.proyectodaw2023.clases.Reserva;
import com.example.proyectodaw2023.clases.Sala;
import com.example.proyectodaw2023.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class ReservasDAOMap implements ReservasDAO, Serializable {
    private Map<Integer, Reserva> reservas;
    private Integer idReserva = 1;
    @Override
    public Reserva buscaId(Integer id) {
        Reserva localizada = reservas.get(id);
        if(localizada != null) localizada= new Reserva(localizada);
        return localizada;
    }

    @Override
    public List<Reserva> buscaTodos() {
        return reservas.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean crea(Reserva r) {
        Reserva nr=new Reserva(r);
        nr.setId(idReserva);
        reservas.put(idReserva, nr);
        r.setId(idReserva);
        idReserva++;
        return true;
    }

    @Override
    public boolean guarda(Reserva r) {
        boolean result=false;
        if (reservas.containsKey(r.getId())) {
            Reserva nr=new Reserva(r);
            reservas.replace(r.getId(),nr);
            result=true;
        }
        return result;
    }

    @Override
    public boolean borra(Integer id) {
        boolean result=false;
        if (reservas.containsKey(id)) {
            reservas.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public int numReservas() {
        return reservas.size();
    }

    public List<Reserva> reservasUser(Integer id){return null;}

    public List<Reserva> reservasEquipo(Integer id){return null;}

    public List<Reserva> reservasSala(Integer id){return null;}

}
