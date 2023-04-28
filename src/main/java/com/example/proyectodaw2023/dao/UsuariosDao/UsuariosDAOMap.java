package com.example.proyectodaw2023.dao.UsuariosDao;


import com.example.proyectodaw2023.clases.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsuariosDAOMap implements UsuariosDAO, Serializable {

    private Map<Integer, Usuario> usuarios;
    private Integer idUsuario = 1;
    public UsuariosDAOMap(){
        usuarios = new HashMap<>();
        usuarios.put(idUsuario, new Usuario(idUsuario++, "Juan","12345678", "77866512Y", "juan@gmail.com", LocalDate.parse("1996-02-07")));
        usuarios.put(idUsuario, new Usuario(idUsuario++, "Pepe","12345678", "99866512Y", "pepe@gmail.com", LocalDate.parse("1996-05-12")));
        usuarios.put(idUsuario, new Usuario(idUsuario++, "Maria","12345678", "11866512Y", "maria@gmail.com", LocalDate.parse("1996-10-17")));

    }
    @Override
    public Usuario buscaId(Integer id) {
        Usuario localizado = usuarios.get(id);
        if (localizado != null) localizado= new Usuario(localizado);
        return localizado;
    }

    @Override
    public List<Usuario> buscaTodos() {
        return usuarios.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean crea(Usuario u) {
        Usuario nc=new Usuario(u);
        nc.setId(idUsuario);
        usuarios.put(idUsuario, nc);
        u.setId(idUsuario);
        idUsuario++;
        return true;
    }

    @Override
    public boolean guarda(Usuario u) {
        boolean result=false;
        if (usuarios.containsKey(u.getId())) {
            Usuario nc=new Usuario(u);
            usuarios.replace(u.getId(),nc);
            result=true;
        }
        return result;
    }

    @Override
    public boolean borra(Integer id) {
        boolean result=false;
        if (usuarios.containsKey(id)) {
            usuarios.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public int numUsuarios() {
        return usuarios.size();
    }

    @Override
    public Usuario buscaDni(String dni) {
        Usuario localizado = null;
        for (Usuario u: usuarios.values()) {
            if (u.getDni().equals(dni)) {
                localizado=u;
                break;
            }
        }
        if (localizado!=null) localizado=new Usuario(localizado);
        return localizado;
        }

    @Override
    public Usuario buscaemail(String email) {
        Usuario localizado = null;
        for (Usuario u: usuarios.values()) {
            if (u.getDni().equals(email)) {
                localizado=u;
                break;
            }
        }
        if (localizado!=null) localizado=new Usuario(localizado);
        return localizado;
    }


    @Override
    public List<Usuario> buscanombre(String cadena) {
        List<Usuario> usuarioscadena = new ArrayList<>();
        // Si "cadena" está vacío, devolver todos los usuarios.
        if (cadena.isEmpty()) {
            for (Usuario u : usuarios.values()) {
                usuarioscadena.add(u);
            }
        } else {
            for (Usuario u : usuarios.values()) {
                if (u.getNombre().contains(cadena)) {
                    usuarioscadena.add(u);
                }
            }
        }
        return usuarioscadena;
    }
}
