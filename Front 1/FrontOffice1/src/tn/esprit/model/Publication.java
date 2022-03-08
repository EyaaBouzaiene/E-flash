/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.model;



/**
 *
 * @author EXTRA
 */
public class Publication {
    
    private int id;
    private String titre;
    private String description;
    private String image_publication;
    private String date;
    private int id_client;

    public Publication(String titre, String description, String image_publication, String date, int id_client) {
        this.titre = titre;
        this.description = description;
        this.image_publication = image_publication;
        this.date = date;
        this.id_client = id_client;
    }

    public Publication(int id, String titre, String description, String image_publication, String date, int id_client) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image_publication = image_publication;
        this.date = date;
        this.id_client = id_client;
    }

    public Publication(String titre, String description, String image_publication, int id_client) {
        this.titre = titre;
        this.description = description;
        this.image_publication = image_publication;
        this.id_client = id_client;
    }

    public Publication(int id, String titre, String description, String date, int id_client) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.id_client = id_client;
    }

    public Publication(String titre, String description, String image_publication) {
        this.titre = titre;
        this.description = description;
        this.image_publication = image_publication;
    }

    public Publication(String titre, String image_publication) {
        this.titre = titre;
        this.image_publication = image_publication;
    }
 
    
     public Publication(String titre) {
        this.titre = titre;
    }

    public Publication(String titre, String description, String image_publication, String date) {
        this.titre = titre;
        this.description = description;
        this.image_publication = image_publication;
        this.date = date;
    }

  
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String  getImage_publication() {
        return image_publication;
    }

    public void setImage_publication(String image_publication) {
        this.image_publication = image_publication;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        //return "Publication{" + " titre=" + titre + ", image_publication=" + image_publication +  '}';
        return "Publication{" + " image_publication=" + image_publication + ", titre=" + titre +  '}';
    }

    

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    
    
    
    
    
}
