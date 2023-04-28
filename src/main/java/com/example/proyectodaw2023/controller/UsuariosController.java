package com.example.proyectodaw2023.controller;

import com.example.proyectodaw2023.Preferencias;
import com.example.proyectodaw2023.clases.Usuario;
import com.example.proyectodaw2023.dao.UsuariosDao.UsuariosDAO;
import com.example.proyectodaw2023.qualifiers.DAOJpa;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;


@Named("ctrlUsuarios")
@ViewScoped
public class UsuariosController implements Serializable {
    @Inject
    HttpServletRequest request;
    public String logout() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        return "/index?faces-redirect=true"; //PRG
    }
    @Inject
    Preferencias preferencias;
    @Inject@DAOJpa
    private UsuariosDAO usuariosDAO;
    private Principal principal;

    FacesContext fc;
    private final Logger logger = Logger.getLogger(UsuariosController.class.getName() );
    private Usuario usuario;
    List<Usuario> usuarios;
    List<Usuario> usuariostemp;
    private String busca;
    public UsuariosController() {}
    @PostConstruct
    private void init() {
        usuario = new Usuario();
        usuarios = usuariosDAO.buscaTodos();
        usuariostemp = usuariosDAO.buscaTodos();
        busca = "";
    }
    public String getBusca() {return busca; }
    public void setBusca(String busca) {
        this.busca = busca;
    }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Usuario> getUsuarios() {
        logger.info("Obteniendo la lista de usuarios");
        return usuarios;
    }
    public List<Usuario> getUsuariostemp() {return usuariostemp;}

    public void recupera() {
        logger.info("Recuperando usuario "+usuario.getId());
        usuario = usuariosDAO.buscaId(usuario.getId());
        preferencias.setActualUsuarioid(usuario.getId()); // Practica8
        if (usuario == null){
            fc.addMessage(null, new FacesMessage("El usuario indicado no existe"));
        }
    }
    public void recupera(Principal principal) {
        logger.info("Recuperando usuario "+principal.getName());
        // todo: recuperar usuario a través de su nombre de usuario
        usuario = usuariosDAO.buscaemail(principal.getName());
        preferencias.setActualUsuarioid(usuario.getId());
        if (usuario == null){
            fc.addMessage(null, new FacesMessage("El usuario indicado no existe"));
        }
    }

    public void recupera(Integer id) {
        logger.info("Recuperando usuario con ID"+ id);
        usuario = usuariosDAO.buscaId(id);
        preferencias.setActualUsuarioid(usuario.getId());//Practica8
        if (usuario == null){
            fc.addMessage(null, new FacesMessage("El usuario indicado no existe"));
        }
    }
    public String borra(){
        System.out.println("Borrandose usuario");
        usuariosDAO.borra(usuario.getId());
        return "/paginaprincipalSinCuenta";
    }
   public String borra(Usuario usuario) {
       usuariosDAO.borra(usuario.getId());
       return "Listado";
   }
   /*public String borra(Principal principal)throws ServletException {
       request.logout();
       usuariosDAO.borra(principal.getName());

       return logout();
   }*/
    public String guarda(){
        logger.info("Guardando usuario");
        usuariosDAO.guarda(usuario);
        return "visualiza?faces-redirect=true&id="+usuario.getId();
    }
    public String crea() {
        logger.info("Creando usuario");
        usuariosDAO.crea(usuario);
//Post-Redirect-Get
        return "paginaprincipal?faces-redirect=true";
    }

    public int numUsuarios(){
        return this.usuariosDAO.numUsuarios();
    }
    public void reset(){ usuario.setId(0);}
    // falta el busca nif
    public void recuperaCadena(){
        usuariostemp = usuariosDAO.buscanombre(busca);
    }

}