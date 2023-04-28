package com.example.proyectodaw2023.controller;

import com.example.proyectodaw2023.clases.Sala;
import com.example.proyectodaw2023.dao.SalasDao.SalasDAO;
import com.example.proyectodaw2023.qualifiers.DAOJpa;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("ctrlSalas")
@ViewScoped
public class SalasController implements Serializable {

    @Inject @DAOJpa
    private SalasDAO salasDAO;
    //View-Model (Vista-Modelo)
    @Inject
    FacesContext fc;
    List<Sala> salas;
    List<Sala> salastemp;
    private Sala sala;
    private String busca;
    public SalasController() { }
    @PostConstruct
    private void init () {
        sala = new Sala();
        salas = salasDAO.buscaTodos();
        salastemp = salasDAO.buscaTodos();
        busca = "";
    }
    public String getBusca() {return busca; }
    public void setBusca(String busca) {
        this.busca = busca;
    }
    public Sala getSala() { return sala; }
    public void setSala(Sala sala) {
    //método no necesario, se usarán los set de Sala desde vista
        this.sala = sala; }
    public List<Sala> getSalas() {return salas;}
    public List<Sala> getSalastemp() {return salastemp;}
    private final Logger logger = Logger.getLogger(SalasController.class.getName());
    public void recupera() {
        logger.info("Recuperando sala "+sala.getId());
        sala = salasDAO.buscaId(sala.getId());
        if (sala == null){
            fc.addMessage(null, new FacesMessage("La sala indicada no existe"));
        }
    }
    public void recupera(Integer id) {
        logger.info("Recuperando sala con ID"+ id);
        sala = salasDAO.buscaId(id);
        if (sala == null){
            fc.addMessage(null, new FacesMessage("La sala indicada no existe"));
        }
    }
    public String borra() {
        salasDAO.borra(sala.getId());
        return "index";
    }
    public String borra(Sala l) {
        salasDAO.borra(l.getId());
        return "index";
    }
    public String guarda(){
        salasDAO.guarda(sala);
        return "index?faces-redirect=true&id="+sala.getId();
    }
    public String crea() {
        logger.info("Guardando sala");
        salasDAO.crea(sala);
//Post-Redirect-Get
        return "index?faces-redirect=true";
    }
    public int numSalas(){return this.salasDAO.numSalas();}
    public void reset(){ sala.setId(0);}
    public void recuperaCadena() {
            salastemp = salasDAO.buscaDescripcion(busca);
        }
}
