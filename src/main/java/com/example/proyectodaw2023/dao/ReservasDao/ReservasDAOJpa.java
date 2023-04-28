package com.example.proyectodaw2023.dao.ReservasDao;

import com.example.proyectodaw2023.clases.Reserva;
import com.example.proyectodaw2023.clases.Sala;
import com.example.proyectodaw2023.qualifiers.DAOJpa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
@RequestScoped
@DAOJpa
@Transactional
public class ReservasDAOJpa implements ReservasDAO, Serializable {

    private final Logger logger = Logger.getLogger(ReservasDAOJpa.class.getName());
    @PersistenceContext
    private EntityManager em;
    @Override
    public Reserva buscaId(Integer id) {
        Reserva r=null;
        try {
            r=em.find(Reserva.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return r;
    }

    @Override
    public List<Reserva> buscaTodos() {
        List<Reserva> r = null;
        try {
            r = em.createQuery("select r from Reserva r", Reserva.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar las reservas", e);
        }
        return r;
    }

    @Override
    public boolean crea(Reserva r) {
        boolean creado = false;
        try {
            em.persist(r);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Reserva r) {
        boolean guardado = false;
        try {
            r = em.merge(r);
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
            Reserva r = null;
            r = em.find(Reserva.class, id);
            em.remove(r);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public int numReservas() {
        int numReservas = 0;
        try {
            Query query = em.createQuery("SELECT COUNT(u) FROM Reserva u");
            numReservas = ((Long) query.getSingleResult()).intValue();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return numReservas;
    }
    public List<Reserva> reservasUser(Integer id){
        List<Reserva> r = null;
        try {
            Query q = em.createNativeQuery("Select e from PUBLIC.USUARIO u, PUBLIC.RESERVA e where e.idUsuario = u.id and u.ID = id");
            List<Objects[]> lt = q.getResultList();
            for (Object[] o : lt) {
                r.add((Reserva) o[0]);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return r;
    }

    public List<Reserva> reservasEquipo(Integer id){
        List<Reserva> r = null;
        try {
            Query q = em.createNativeQuery("Select e from PUBLIC.EQUIPO u, PUBLIC.RESERVA e where e.idEquipo = u.id and u.ID = id");
            List<Objects[]> lt = q.getResultList();
            for (Object[] o : lt) {
                r.add((Reserva) o[0]);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return r;

    }

    public List<Reserva> reservasSala(Integer id){
        List<Reserva> r = null;
        try {
            Query q = em.createNativeQuery("Select e from PUBLIC.SALA u, PUBLIC.RESERVA e where e.idSala = u.id and u.ID = id");
            List<Objects[]> lt = q.getResultList();
            for (Object[] o : lt) {
                r.add((Reserva) o[0]);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return r;

    }

}
