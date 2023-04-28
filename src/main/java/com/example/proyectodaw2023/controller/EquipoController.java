package com.example.proyectodaw2023.controller;
import com.example.proyectodaw2023.clases.Equipo;
import com.example.proyectodaw2023.qualifiers.DAOJpa;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.example.proyectodaw2023.dao.EquipoDao.EquipoDAO;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("ctrlEquipo")
@ViewScoped
public class EquipoController implements Serializable {
    @Inject @DAOJpa
    private EquipoDAO EquipoDAO;
    //View-Model (Vista-Modelo)
    private Equipo equipo;
    FacesContext fc;
    List<Equipo> equipos;
    List<Equipo> equipostemp;
    private String busca;
    public EquipoController() { }
    @PostConstruct
    private void init () {
        equipo = new Equipo();
        equipos = EquipoDAO.buscaTodos();
        equipostemp = EquipoDAO.buscaTodos();
        busca = "";
    }
    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) {
        //método no necesario, se usarán los set de com.example.proyectodaw2023.Equipo desde vista
        this.equipo = equipo; }
    public List<Equipo> getEquipos() {return EquipoDAO.buscaTodos();}

    //NO SE SI ES AQUI
    private Logger logger = Logger.getLogger(EquipoController.class.getName());

    public int numEquipo(){
        return this.EquipoDAO.numEquipos();
    }



    public String getBusca() {return busca; }
    public void setBusca(String busca) {
        this.busca = busca;
    }

    public List<Equipo> getEquipostemp() {return equipostemp;}

    public void recupera() {
        logger.info("Recuperando equipo "+equipo.getId());
        equipo = EquipoDAO.buscaId(equipo.getId());
        if (equipo == null){
            fc.addMessage(null, new FacesMessage("La equipo indicada no existe"));
        }
    }
    public void recupera(Integer id) {
        logger.info("Recuperando equipo con ID"+ id);
        equipo = EquipoDAO.buscaId(id);
        if (equipo == null){
            fc.addMessage(null, new FacesMessage("El equipo indicada no existe"));
        }
    }
    public String borra() {
        EquipoDAO.borra(equipo.getId());
        return "index";
    }
    public String borra(Equipo l) {
        EquipoDAO.borra(l.getId());
        return "index";
    }
    public String guarda(){
        EquipoDAO.guarda(equipo);
        return "index?faces-redirect=true&id="+equipo.getId();
    }
    public String crea() {
        logger.info("Guardando equipo");
        EquipoDAO.crea(equipo);
//Post-Redirect-Get
        return "index?faces-redirect=true";
    }
    public void reset(){ equipo.setId(0);}
    public void recuperaCadena(){
        equipostemp=EquipoDAO.buscaDescripcion(busca);
    }

}
