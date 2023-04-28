package com.example.proyectodaw2023.dao.EquipoDao;

import com.example.proyectodaw2023.clases.Equipo;
import com.example.proyectodaw2023.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.List;

@ApplicationScoped
@DAOMap
public class EquipoDAOMap implements EquipoDAO, Serializable{

    @Override
    public Equipo buscaId(Integer id) {
        return null;
    }

    @Override
    public List<Equipo> buscaTodos() {
        return null;
    }

    @Override
    public boolean crea(Equipo uequipo) {
        return false;
    }

    @Override
    public boolean guarda(Equipo uequipo) {
        return false;
    }

    @Override
    public boolean borra(Integer id) {
        return false;
    }

    @Override
    public int numEquipos() {
        return 0;
    }

    @Override
    public List<Equipo> buscaDescripcion(String cadena) {
        return null;
    }

}
