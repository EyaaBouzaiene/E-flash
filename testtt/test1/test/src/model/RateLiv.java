/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 21627
 */
public class RateLiv {
    int id,rating, idLivreur;

    public RateLiv(int rating, int idLivreur) {
        this.rating = rating;
        this.idLivreur = idLivreur;
    }

    public RateLiv(int id, int rating, int idLivreur) {
        this.id = id;
        this.rating = rating;
        this.idLivreur = idLivreur;
    }

    public RateLiv(int idl, RateLiv r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", rating=" + rating + ", idLivreur=" + idLivreur + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(int idLivreur) {
        this.idLivreur = idLivreur;
    }
    
    
}
