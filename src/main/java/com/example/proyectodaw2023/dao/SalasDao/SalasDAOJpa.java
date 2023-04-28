package com.example.proyectodaw2023.dao.SalasDao;

import com.example.proyectodaw2023.clases.Sala;
import com.example.proyectodaw2023.qualifiers.DAOJpa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJpa
@Transactional
public class SalasDAOJpa implements SalasDAO, Serializable {
    private final Logger logger = Logger.getLogger(SalasDAOJpa.class.getName());
    @PersistenceContext
    private EntityManager em;

    public SalasDAOJpa() {
    }

    @Override
    public Sala buscaId(Integer id) {
        Sala s = null;
        try {
            s = em.find(Sala.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return s;
    }

    @Override
    public List<Sala> buscaTodos() {
        List<Sala> s = null;
        try {
            s = em.createQuery("select s from Sala s", Sala.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar las salas", e);
        }
        return s;
    }

    @Override
    public boolean crea(Sala s) {
        boolean creado = false;
        try {
            em.persist(s);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Sala s) {
        boolean guardado = false;
        try {
            s = em.merge(s);
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
            Sala s = null;
            s = em.find(Sala.class, id);
            em.remove(s);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public int numSalas() {
        int numSalas = 0;
        try {
            Query query = em.createQuery("SELECT COUNT(s) FROM Sala s");
            numSalas = ((Long) query.getSingleResult()).intValue();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return numSalas;
    }

    @Override
    public List<Sala> buscaDescripcion(String cadena) {
        List<Sala> salascadena = new ArrayList<>();
        // Si "cadena" está vacío, devolver todas las salas.
        if (cadena.isEmpty()) {
            try {
                Query query = em.createQuery("SELECT s FROM Sala s WHERE s.descripcion LIKE :cadena");
                query.setParameter("cadena", "%" + cadena + "%");
                salascadena = query.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } else {
            try {
                Query query = em.createQuery("SELECT s FROM Sala s WHERE s.descripcion LIKE :cadena");
                query.setParameter("cadena", "%" + cadena + "%");
                salascadena = query.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return salascadena;
    }
}

