/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.image.Image;

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
 private Image IMAGE2;
  private Double prix;
  private Blob IMAGE4;

    public Produits(int reference, int quantite, int quantiteR, String rate, String nom, String desc, String type, Date dateP, String CATEGORIE, String IMAGE, Image IMAGE2, Double prix, Blob IMAGE4) {
        this.reference = reference;
        this.quantite = quantite;
        this.quantiteR = quantiteR;
        this.rate = rate;
        this.nom = nom;
        this.desc = desc;
        this.type = type;
        this.dateP = dateP;
        this.CATEGORIE = CATEGORIE;
        this.IMAGE = IMAGE;
        this.IMAGE2 = IMAGE2;
        this.prix = prix;
        this.IMAGE4 = IMAGE4;
    }

    public Produits(int reference, int quantite, int quantiteR, String rate, String nom, String desc, String type, Date dateP, String CATEGORIE, String IMAGE, Double prix) {
        this.reference = reference;
        this.quantite = quantite;
        this.quantiteR = quantiteR;
        this.rate = rate;
        this.nom = nom;
        this.desc = desc;
        this.type = type;
        this.dateP = dateP;
        this.CATEGORIE = CATEGORIE;
        this.IMAGE = IMAGE;
        this.prix = prix;
    }

    public Produits(int quantite, int quantiteR, String rate, String nom, String desc, String type, Date dateP, String CATEGORIE, String IMAGE, Double prix) {
        this.quantite = quantite;
        this.quantiteR = quantiteR;
        this.rate = rate;
        this.nom = nom;
        this.desc = desc;
        this.type = type;
        this.dateP = dateP;
        this.CATEGORIE = CATEGORIE;
        this.IMAGE = IMAGE;
        this.prix = prix;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Produits(int reference, String nom, String desc, String type, String CATEGORIE, String IMAGE, Double prix) {
        this.reference = reference;
        this.nom = nom;
        this.desc = desc;
        this.type = type;
        this.CATEGORIE = CATEGORIE;
        this.IMAGE = IMAGE;
        this.prix = prix;
    }
  

    public Produits(int reference, int quantite, int quantiteR, String rate, String nom, String desc, String type, Date dateP, String CATEGORIE, Blob IMAGE4) {
        this.reference = reference;
        this.quantite = quantite;
        this.quantiteR = quantiteR;
        this.rate = rate;
        this.nom = nom;
        this.desc = desc;
        this.type = type;
        this.dateP = dateP;
        this.CATEGORIE = CATEGORIE;
        this.IMAGE4 = IMAGE4;
    }

    public Produits(Blob IMAGE4) {
        this.IMAGE4 = IMAGE4;
    }

    public Produits() {
    }

    public Image getIMAGE2() {
        return IMAGE2;
    }

    public void setIMAGE2(Image IMAGE2) {
        this.IMAGE2 = IMAGE2;
    }

    public Blob getIMAGE4() {
        return IMAGE4;
    }

    public void setIMAGE4(Blob IMAGE4) {
        this.IMAGE4 = IMAGE4;
    }
 
  
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

    public Produits(Image IMAGE2) {
        this.IMAGE2 = IMAGE2;
    }

    public Produits(String nom) {
        this.nom = nom;
    }

    public Produits(int quantite, int quantiteR, String rate, String nom, String desc, String type, Date dateP, String CATEGORIE) {
        this.quantite = quantite;
        this.quantiteR = quantiteR;
        this.rate = rate;
        this.nom = nom;
        this.desc = desc;
        this.type = type;
        this.dateP = dateP;
        this.CATEGORIE = CATEGORIE;
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
        this.reference= reference;
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
