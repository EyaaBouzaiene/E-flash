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
public class Likes {
    
    private int id;
    private int id_publication_like;
    private int id_client;

    public Likes(int id_publication_like, int id_client) {
        this.id_publication_like = id_publication_like;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_publication_like() {
        return id_publication_like;
    }

    public void setId_publication_like(int id_publication_like) {
        this.id_publication_like = id_publication_like;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Likes{" + "id=" + id + ", id_publication_like=" + id_publication_like + ", id_client=" + id_client + '}';
    }
    
    
    
}
