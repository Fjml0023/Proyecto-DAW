package com.example.proyectodaw2023.dao.UsuariosDao;

import com.example.proyectodaw2023.clases.Usuario;
import com.example.proyectodaw2023.qualifiers.DAOJpa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJpa
@Transactional
public class UsuariosDAOJpa implements UsuariosDAO, Serializable {

    private final Logger logger = Logger.getLogger(UsuariosDAOJpa.class.getName());
    @PersistenceContext
    private EntityManager em;
    public UsuariosDAOJpa(){}
    @Override
    public Usuario buscaId(Integer id) {
        Usuario c=null;
        try {
            c=em.find(Usuario.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return c;
    }

    @Override
    public List<Usuario> buscaTodos() {
        List<Usuario> u = null;
        try {
            Query q = em.createQuery("Select u from Usuario u", Usuario.class);
            u = (List<Usuario>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return u;
    }

    @Override
    public boolean crea(Usuario u) {
        boolean creado = false;
        try {
            em.persist(u);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Usuario u) {
        boolean guardado = false;
        try {
            u = em.merge(u);
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
            Usuario u = null;
            u = em.find(Usuario.class, id);
            em.remove(u);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public int numUsuarios() {
        int numUsuarios = 0;
        try {
            Query query = em.createQuery("SELECT COUNT(u) FROM Usuario u");
            numUsuarios = ((Long) query.getSingleResult()).intValue();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return numUsuarios;
    }

    @Override
    public Usuario buscaDni(String dni) {
        Usuario u = null;
        try {
            TypedQuery<Usuario> q = em.createQuery("Select u from Usuario u where u.dni=:dni",Usuario.class);
            q.setParameter("dni", dni);
            u = q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return u;
    }

    @Override
    public Usuario buscaemail(String email) {
        Usuario usuario=null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email",Usuario.class);
            query.setParameter("email", email);
            usuario = (Usuario)query.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return usuario;
    }
    public List<Usuario> buscanombre(String cadena) {
        List<Usuario> usuarioscadena = new ArrayList<>();
        // Si "cadena" está vacío, devolver todas las salas.
        if (cadena.isEmpty()) {
            try {
                Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :cadena");
                query.setParameter("cadena", "%" + cadena + "%");
                usuarioscadena = query.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } else {
            try {
                Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :cadena");
                query.setParameter("cadena", "%" + cadena + "%");
                usuarioscadena = query.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return usuarioscadena;
    }

}

