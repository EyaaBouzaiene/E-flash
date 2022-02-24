/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author Mayssa
 */
public class Commande {
     private int idCom;
    private String etat;
    private int prix;
    private String date;

    public Commande(int idCom, String etat, int prix, String date) {
        this.idCom = idCom;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
    }

    public Commande(String etat, int prix, String date) {
        this.etat = etat;
        this.prix = prix;
        this.date = date;
    }

    public int getIdCom() {
        return idCom;
    }

    public String getEtat() {
        return etat;
    }

    public int getPrix() {
        return prix;
    }

    public String getDate() {
        return date;
    }

    public void setIdCom(int idCom) {
        this.idCom = idCom;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCom=" + idCom + ", etat=" + etat + ", prix=" + prix + ", date=" + date + '}';
    }
    
    
}
