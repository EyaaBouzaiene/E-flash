/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.crud;



import java.sql.Date;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author ideapadGAMING
 */
public class Evenement {
    
    private int id;
    private String nom;
    private String description;
    private int duree;
    private int nombre_place;
    private Date date_debut;

    

    public Evenement() {
    }

    public Evenement(int id, String nom, String description, int duree, int nombre_place, Date date_debut) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.nombre_place = nombre_place;
        this.date_debut = date_debut;
    }

    public Evenement(String nom, String description, int duree, int nombre_place, Date date_debut) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.nombre_place = nombre_place;
        this.date_debut = date_debut;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNombre_place() {
        return nombre_place;
    }

    public void setNombre_place(int nombre_place) {
        this.nombre_place = nombre_place;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", duree=" + duree + ", nombre_place=" + nombre_place + ", date_debut=" + date_debut + '}';
    }

  
 

    

    
    
    
}
