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
public class Livraison {
     private int idLivraison,idCommande,idLivreur,prix;
     private String ville,etat,emailLivreur;
     

    public int getIdLivraison() {
        return idLivraison;
    }

    public Livraison() {
    }

    public Livraison(int idLivraison, int idCommande, int idLivreur, int prix, String ville, String etat, String emailLivreur) {
        this.idLivraison = idLivraison;
        this.idCommande = idCommande;
        this.idLivreur = idLivreur;
        this.prix = prix;
        this.ville = ville;
        this.etat = etat;
        this.emailLivreur = emailLivreur;
    }

    public Livraison(int idCommande, int idLivreur, int prix, String ville, String etat, String emailLivreur) {
        this.idCommande = idCommande;
        this.idLivreur = idLivreur;
        this.prix = prix;
        this.ville = ville;
        this.etat = etat;
        this.emailLivreur = emailLivreur;
    }

    
    public Livraison(String emailLivreur) {
        this.emailLivreur = emailLivreur;
    }

    public Livraison(String ville, String etat) {
        this.ville = ville;
        this.etat = etat;
    }

    public Livraison(int idCommande,int idLivreur,  int prix, String ville, String etat) {
        this.idLivreur = idLivreur;
        this.idCommande = idCommande;
        this.prix = prix;
        this.ville = ville;
        this.etat = etat;
    }
    
    public Livraison(int idCommande,String emaildLivreur,  int prix, String ville, String etat) {
        this.emailLivreur = emaildLivreur;
        this.idCommande = idCommande;
        this.prix = prix;
        this.ville = ville;
        this.etat = etat;
    }

    public Livraison(int prix, String ville) {
        this.prix = prix;
        this.ville = ville;
    }

    public Livraison(int idCommande, int prix, String ville, String etat) {
        this.idCommande = idCommande;
        this.prix = prix;
        this.ville = ville;
        this.etat = etat;
    }
    
   public Livraison(  int idLivreur, String ville, String etat) {
       
        this.idLivreur = idLivreur;
        this.ville = ville;
        this.etat = etat;
    }
   

    public Livraison(int idLivraison, int idCommande,int idLivreur,  int prix, String ville, String etat) {
        this.idLivraison = idLivraison;
        this.idCommande = idCommande;
         this.idLivreur = idLivreur;
        this.prix = prix;
        this.ville = ville;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Livraison{" + "idLivraison=" + idLivraison + ",idCommande=" + idCommande + ", idLivreur=" + idLivreur + ",  prix=" + prix + ", ville=" + ville + ", etat=" + etat + '}';
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
