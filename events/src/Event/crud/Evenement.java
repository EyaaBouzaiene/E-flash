/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.crud;



import java.io.InputStream;
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
    private String duree;
    private  String nombre_place;
    private Date date_debut;
    private InputStream image;

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDuree() {
        return duree;
    }

    public String getNombre_place() {
        return nombre_place;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public InputStream getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setNombre_place(String nombre_place) {
        this.nombre_place = nombre_place;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public Evenement(String nom, String description, String duree, String nombre_place, Date date_debut, InputStream image) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.nombre_place = nombre_place;
        this.date_debut = date_debut;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", duree=" + duree + ", nombre_place=" + nombre_place + ", date_debut=" + date_debut + ", image=" + image + '}';
    }

    public Evenement(int id, String nom, String description, String duree, String nombre_place, Date date_debut, InputStream image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.nombre_place = nombre_place;
        this.date_debut = date_debut;
        this.image = image;
    }


    

  

    

    
    
    
}
