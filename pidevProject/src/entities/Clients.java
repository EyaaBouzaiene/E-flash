/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author aders
 */
public class Clients {
 private int referenceC, quantiteA;
    
    private String nom,prenom,IDP;

    public Clients(int quantiteA, String nom, String prenom, String IDP) {
        this.quantiteA = quantiteA;
        this.nom = nom;
        this.prenom = prenom;
        this.IDP = IDP;
    }

    public Clients(int referenceC, int quantiteA, String nom, String prenom, String IDP) {
        this.referenceC = referenceC;
        this.quantiteA = quantiteA;
        this.nom = nom;
        this.prenom = prenom;
        this.IDP = IDP;
    }

    

    @Override
    public String toString() {
        return "Clients{" + "referenceC=" + referenceC + ", quantiteA=" + quantiteA + ", nom=" + nom + ", prenom=" + prenom + ", IDP=" + IDP + '}';
    }

    public String getIDP() {
        return IDP;
    }

    public void setIDP(String IDP) {
        this.IDP = IDP;
    }

    

    public Clients(int quantiteA, String nom, String prenom) {
        this.quantiteA = quantiteA;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getReferenceC() {
        return referenceC;
    }

    public void setReferenceC(int referenceC) {
        this.referenceC = referenceC;
    }

    public int getQuantiteA() {
        return quantiteA;
    }

    public void setQuantiteA(int quantiteA) {
        this.quantiteA = quantiteA;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
       
}

