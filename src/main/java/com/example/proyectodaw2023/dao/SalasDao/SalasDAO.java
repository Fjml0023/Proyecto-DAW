package com.example.proyectodaw2023.dao.SalasDao;

import com.example.proyectodaw2023.clases.Sala;

import java.util.List;

public interface SalasDAO {

    Sala buscaId(Integer id);
    List<Sala> buscaTodos();
    boolean crea(Sala sala);
    boolean guarda(Sala sala);
    boolean borra(Integer id);
    int numSalas();
    List<Sala> buscaDescripcion(String cadena);

}
