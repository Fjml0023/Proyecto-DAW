package com.example.proyectodaw2023.dao.EquipoDao;

import com.example.proyectodaw2023.qualifiers.DAOJpa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import com.example.proyectodaw2023.clases.Equipo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJpa
@Transactional
public class EquipoDAOJpa implements EquipoDAO, Serializable {
    private final Logger logger = Logger.getLogger(EquipoDAOJpa.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Override
    public Equipo buscaId(Integer id) {
        Equipo e = null;
        try {
            e = em.find(Equipo.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return e;
    }

    @Override
    public List<Equipo> buscaTodos() {
        List<Equipo> e;
        try {
            e = em.createQuery("select e from Equipo e", Equipo.class).getResultList();
        } catch (Exception f) {
            logger.log(Level.SEVERE, "No se pueden recuperar los equipos", f);
            e = new ArrayList<>();
        }
        return e;
    }

    @Override
    public boolean crea(Equipo uequipo) {
        boolean creado = false;
        try {
            em.persist(uequipo);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Equipo uequipo) {
        boolean guardado = false;
        try {
            uequipo = em.merge(uequipo);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean borra(Integer id) {
        boolean borrado = false;
        try {
            Equipo e = null;
            e = em.find(Equipo.class, id);
            em.remove(e);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public int numEquipos() {
        int numEquipos = 0;
        try {
            Query query = em.createQuery("SELECT COUNT(e) FROM Equipo e");
            numEquipos = ((Long) query.getSingleResult()).intValue();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return numEquipos;
    }

    @Override
    public List<Equipo> buscaDescripcion(String cadena) {
        List<Equipo> equiposcadena = new ArrayList<>();
        if (cadena == "") {
            try {
                Query query = em.createQuery("SELECT e FROM Equipo e");
                query.setParameter("cadena", "%" + cadena + "%");
                equiposcadena = query.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } else {
            try {
                Query query = em.createQuery("SELECT e FROM Equipo e WHERE e.descripcion LIKE :cadena");
                query.setParameter("cadena", "%" + cadena + "%");
                equiposcadena = query.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return equiposcadena;
    }
}
