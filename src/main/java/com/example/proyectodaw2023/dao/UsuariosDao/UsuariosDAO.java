package com.example.proyectodaw2023.dao.UsuariosDao;

import com.example.proyectodaw2023.clases.Usuario;

import java.util.List;

public interface UsuariosDAO {
    public Usuario buscaId(Integer id);
    public List<Usuario> buscaTodos();
    public boolean crea(Usuario usuario);
    public boolean guarda(Usuario uusuario);
    public boolean borra(Integer id);

    public  int numUsuarios();
    public Usuario buscaDni(String dni);
    public Usuario buscaemail (String email);
    public List<Usuario> buscanombre (String nombre);
}
