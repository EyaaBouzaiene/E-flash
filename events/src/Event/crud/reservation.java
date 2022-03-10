/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.crud;

import java.util.Objects;

/**
 *
 * @author ideapadGAMING
 */
public class reservation {
    int id_reservation;
    String gmail;
    int nombre_billet;
    int id_event;

    public reservation() {
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setNombre_billet(int nombre_billet) {
        this.nombre_billet = nombre_billet;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public String getGmail() {
        return gmail;
    }

    public int getNombre_billet() {
        return nombre_billet;
    }

    public int getId_event() {
        return id_event;
    }

    public reservation(String gmail, int nombre_billet, int id_event) {
        this.gmail = gmail;
        this.nombre_billet = nombre_billet;
        this.id_event = id_event;
    }

    public reservation(int id_reservation, String gmail, int nombre_billet, int id_event) {
        this.id_reservation = id_reservation;
        this.gmail = gmail;
        this.nombre_billet = nombre_billet;
        this.id_event = id_event;
    }

    @Override
    public String toString() {
        return "reservation{" + "id_reservation=" + id_reservation + ", gmail=" + gmail + ", nombre_billet=" + nombre_billet + ", id_event=" + id_event + '}';
    }

 
    
    
}
