package com.example.proyectodaw2023.dao.EquipoDao;
import com.example.proyectodaw2023.clases.Equipo;

import java.util.List;
public interface EquipoDAO {
    public Equipo buscaId(Integer id);
    public List<Equipo> buscaTodos();
    public boolean crea(Equipo uequipo);
    public boolean guarda(Equipo uequipo);
    public boolean borra(Integer id);

    public  int numEquipos();

    List<Equipo> buscaDescripcion(String cadena);
}
