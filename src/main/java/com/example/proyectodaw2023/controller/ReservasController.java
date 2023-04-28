package com.example.proyectodaw2023.controller;

import com.example.proyectodaw2023.clases.Reserva;
import com.example.proyectodaw2023.clases.Sala;
import com.example.proyectodaw2023.clases.Usuario;
import com.example.proyectodaw2023.dao.ReservasDao.ReservasDAO;
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

@Named("ctrlReservas")
@ViewScoped
public class ReservasController implements Serializable {

    @Inject @DAOJpa
    private ReservasDAO reservasDAO;
    @Inject
    FacesContext fc;
    List<Reserva> reservas;
    List<Reserva> reservastemp;
    private Reserva reserva;
    private String busca;
    public ReservasController() {}
    @PostConstruct
    private void init() {
        reserva = new Reserva();
        reservas = reservasDAO.buscaTodos();
        reservastemp = reservasDAO.buscaTodos();
        busca = "";

    }
    public String getBusca() {return busca; }
    public void setBusca(String busca) {
        this.busca = busca;
    }
    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    public List<Reserva> getReservas() {
        logger.info("Obteniendo lista de reservas");
        return reservas;
    }
    public List<Reserva> getReservastemp() {return reservastemp;}
    private final Logger logger = Logger.getLogger(ReservasController.class.getName() );
    public void recupera() {
        logger.info("Recuperando reserva "+reserva.getId());
        reserva = reservasDAO.buscaId(reserva.getId());
        if (reserva == null){
            fc.addMessage(null, new FacesMessage("La reserva indicada no existe"));
        }
    }
    public void recupera(Integer id) {
        logger.info("Recuperando reserva con ID"+ id);
        reserva = reservasDAO.buscaId(id);
        if (reserva == null){
            fc.addMessage(null, new FacesMessage("La reserva indicada no existe"));
        }
    }
    public String borra(){
        System.out.println("Borrandose reserva");
        reservasDAO.borra(reserva.getId());
        return "index";
    }
    public String borra(Reserva r) {
        reservasDAO.borra(r.getId());
        return "index";
    }
    public String guarda(){
        logger.info("Guardando reserva");
        reservasDAO.guarda(reserva);
        return "index?faces-redirect=true&id="+reserva.getId();
    }
    public String crea() {
        logger.info("Creando reserva");
        reservasDAO.crea(reserva);
        return "index?faces-redirect=true";
    }

    public int numReservas(){
        return this.reservasDAO.numReservas();
    }
    public void reset(){ reserva.setId(0);}


}
