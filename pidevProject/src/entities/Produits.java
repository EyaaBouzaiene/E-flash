/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author aders
 */
public class Produits {

    private int reference, quantite,quantiteR;
    private String rate;
    private String nom, desc, type;
    private Date dateP;
    private String CATEGORIE , IMAGE;

    public Produits(int quantite, int quantiteR, String rate, String nom, String desc, String type, Date dateP, String CATEGORIE, String IMAGE) {
        this.quantite = quantite;
        this.quantiteR = quantiteR;
        this.rate = rate;
        this.nom = nom;
        this.desc = desc;
        this.type = type;
        this.dateP = dateP;
        this.CATEGORIE = CATEGORIE;
        this.IMAGE = IMAGE;
    }

    public Produits(String nom) {
        this.nom = nom;
    }

    public int getQuantiteR() {
        return quantiteR;
    }

    public void setQuantiteR(int quantiteR) {
        this.quantiteR = quantiteR;
    }

   

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Produits(InputStream in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Produits{" + "reference=" + reference + ", quantite=" + quantite + ", quantiteR=" + quantiteR + ", rate=" + rate + ", nom=" + nom + ", desc=" + desc + ", type=" + type + ", dateP=" + dateP + ", CATEGORIE=" + CATEGORIE + ", IMAGE=" + IMAGE + '}';
    }

    

    

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

   

  
    

    public Produits(Date date) {
        this.dateP = date;
    }

    public Produits(int reference, int quantite, int quantiteR, String rate, String nom, String desc, String type, Date dateP, String CATEGORIE, String IMAGE) {
        
        this.quantite = quantite;
        this.quantiteR = quantiteR;
        this.rate = rate;
        this.nom = nom;
        this.desc = desc;
        this.type = type;
        this.dateP = dateP;
        this.CATEGORIE = CATEGORIE;
        this.IMAGE = IMAGE;
    }

  

    public int getReference() {
        return reference;
    }

    public void setReference(int refrence) {
        this.reference = refrence;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateP() {
        return dateP;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }
  public String getCATEGORIE() {
        return CATEGORIE;
    }

    public void setCATEGORIE(String CATEGORIE) {
        this.CATEGORIE = CATEGORIE;
    }

}
