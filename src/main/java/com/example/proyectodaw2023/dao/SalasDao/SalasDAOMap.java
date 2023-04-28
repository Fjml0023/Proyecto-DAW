package com.example.proyectodaw2023.dao.SalasDao;

import com.example.proyectodaw2023.clases.Sala;
import com.example.proyectodaw2023.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class SalasDAOMap implements SalasDAO, Serializable {

    private Map<Integer, Sala> salas;
    private Integer idSala = 1;

    public SalasDAOMap() {
        salas = new HashMap<>();

        salas.put(idSala, new Sala(idSala++, "Sala grande de actos con una mesa redonda y 16 sillas","Planta 5","40 m cuadrados"));
        salas.put(idSala, new Sala(idSala++, "Sala pequeña con una mesa y 2 sillas","Planta 5","3 m cuadrados"));
    }
    @Override
    public Sala buscaId(Integer id) {
        Sala localizada = salas.get(id);
        if(localizada != null) localizada= new Sala(localizada);
        return localizada;
    }

    @Override
    public List<Sala> buscaTodos() {
        return salas.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean crea(Sala s) {
        Sala ns=new Sala(s);
        ns.setId(idSala);
        salas.put(idSala, ns);
        s.setId(idSala);
        idSala++;
        return true;
    }

    @Override
    public boolean guarda(Sala s) {
        boolean result=false;
        if (salas.containsKey(s.getId())) {
            Sala ns=new Sala(s);
            salas.replace(s.getId(),ns);
            result=true;
        }
        return result;
    }

    @Override
    public boolean borra(Integer id) {
        boolean result=false;
        if (salas.containsKey(id)) {
            salas.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public int numSalas() {
        return salas.size();
    }

    @Override
    public List<Sala> buscaDescripcion(String cadena) {
        List<Sala> salascadena = new ArrayList<>();
        // Si "cadena" está vacío, devolver todas las salas.
        if (cadena.isEmpty()) {
            for (Sala s : salas.values()) {
                salascadena.add(s);
            }
        } else {
            for (Sala s : salas.values()) {
                if (s.getDescripcion().contains(cadena)) {
                    salascadena.add(s);
                }
            }
        }
        return salascadena;
    }
}
