/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.crud;

/**
 *
 * @author ideapadGAMING
 */
public class reservation {
    int id_reservation;
    String nom;
    String prenom;
    String gmail;
    int nombre_billet;

    public reservation(int id_reservation, String nom, String prenom, String gmail, int nombre_billet) {
        this.id_reservation = id_reservation;
        this.nom = nom;
        this.prenom = prenom;
        this.gmail = gmail;
        this.nombre_billet = nombre_billet;
    }

    public reservation() {
    }

    public reservation(String nom, String prenom, String gmail, int nombre_billet) {
        this.nom = nom;
        this.prenom = prenom;
        this.gmail = gmail;
        this.nombre_billet = nombre_billet;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGmail() {
        return gmail;
    }

    public int getNombre_billet() {
        return nombre_billet;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setNombre_billet(int nombre_billet) {
        this.nombre_billet = nombre_billet;
    }

    @Override
    public String toString() {
        return "reservation{" + "id_reservation=" + id_reservation + ", nom=" + nom + ", prenom=" + prenom + ", gmail=" + gmail + ", nombre_billet=" + nombre_billet + '}';
    }
    
}
