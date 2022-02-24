/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author 21627
 */
public class Livraison {
     private int idLivraison,idLivreur,idCommande,prix;
     private String ville,etat;

    public int getIdLivraison() {
        return idLivraison;
    }

    public Livraison(int idLivraison, int idLivreur, int idCommande, int prix, String ville, String etat) {
        this.idLivreur = idLivreur;
        this.idCommande = idCommande;
        this.prix = prix;
        this.ville = ville;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Livraison{" + "idLivraison=" + idLivraison + ", idLivreur=" + idLivreur + ", idCommande=" + idCommande + ", prix=" + prix + ", ville=" + ville + ", etat=" + etat + '}';
    }


    public int getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(int idLivreur) {
        this.idLivreur = idLivreur;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
     

    
}
