package tn.esprit.model;

import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Mayssa
 */
public class Commande {
     private int idCom;
    private String etat;
    private String prix;
    private String date;
  private int REFERENCEP;
  private int refcmd;
   private int refclient;
  String IMAGE;
  Double total;
   private int quantiteA;
    public Commande(int idCom, String etat, String prix, String date) {
        this.idCom = idCom;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getQuantiteA() {
        return quantiteA;
    }

    public void setQuantiteA(int quantiteA) {
        this.quantiteA = quantiteA;
    }

    
    
    
    public int getRefclient() {
        return refclient;
    }

    public void setRefclient(int refclient) {
        this.refclient = refclient;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.idCom;
        hash = 31 * hash + Objects.hashCode(this.etat);
        hash = 31 * hash + Objects.hashCode(this.prix);
        hash = 31 * hash + Objects.hashCode(this.date);
        hash = 31 * hash + this.REFERENCEP;
        hash = 31 * hash + this.refcmd;
        hash = 31 * hash + this.refclient;
        hash = 31 * hash + Objects.hashCode(this.IMAGE);
        hash = 31 * hash + this.quantiteA;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (this.idCom != other.idCom) {
            return false;
        }
        if (this.REFERENCEP != other.REFERENCEP) {
            return false;
        }
        if (this.refcmd != other.refcmd) {
            return false;
        }
        if (this.refclient != other.refclient) {
            return false;
        }
        if (this.quantiteA != other.quantiteA) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.IMAGE, other.IMAGE)) {
            return false;
        }
        return true;
    }

    public Commande(String prix, String date, int REFERENCEP, int refcmd, int refclient, String IMAGE, int quantiteA) {
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
        this.refclient = refclient;
        this.IMAGE = IMAGE;
        this.quantiteA = quantiteA;
    }

    public Commande(String prix, String date, int REFERENCEP, int refcmd, int refclient, String IMAGE, Double total, int quantiteA) {
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
        this.refclient = refclient;
        this.IMAGE = IMAGE;
        this.total = total;
        this.quantiteA = quantiteA;
    }
    
    
    

    public Commande(int idCom, String prix, String date, int REFERENCEP, int refcmd, int refclient, String IMAGE, int quantiteA) {
        this.idCom = idCom;
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
        this.refclient = refclient;
        this.IMAGE = IMAGE;
        this.quantiteA = quantiteA;
    }

    public Commande(int idCom, String prix, String date, int REFERENCEP, int refcmd, int refclient, String IMAGE) {
        this.idCom = idCom;
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
        this.refclient = refclient;
        this.IMAGE = IMAGE;
    }

    public Commande(int idCom, String prix, String date, int REFERENCEP, int refcmd, int refclient, String IMAGE, Double total, int quantiteA) {
        this.idCom = idCom;
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
        this.refclient = refclient;
        this.IMAGE = IMAGE;
        this.total = total;
        this.quantiteA = quantiteA;
    }

    public Commande(String prix, String date, int REFERENCEP, int refcmd, int refclient, String IMAGE) {
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
        this.refclient = refclient;
        this.IMAGE = IMAGE;
    }

    
    
    
    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public Commande(int idCom, String etat, String prix, String date, int REFERENCEP, int refcmd, String IMAGE) {
        this.idCom = idCom;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
        this.IMAGE = IMAGE;
    }

    public Commande(int idCom, String prix, String date, int REFERENCEP, int refcmd, String IMAGE) {
        this.idCom = idCom;
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
        this.IMAGE = IMAGE;
    }
    
    

    public int getRefcmd() {
        return refcmd;
    }

    public void setRefcmd(int refcmd) {
        this.refcmd = refcmd;
    }

    public Commande(int idCom, String etat, String prix, String date,int refcmd) {
        this.idCom = idCom;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
       
        this.refcmd = refcmd;
    }

    public Commande(String etat, String prix, String date, int REFERENCEP, int refcmd) {
        this.etat = etat;
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
        this.refcmd = refcmd;
    }

    
//    public Commande(int idCom, String etat,String prix, String date, int REFERENCEP) {
//        this.idCom = idCom;
//        this.etat = etat;
//        this.prix = prix;
//        this.date = date;
//        this.REFERENCEP = REFERENCEP;
//    }

    public Commande(String etat, String prix, String date, int REFERENCEP) {
        this.etat = etat;
        this.prix = prix;
        this.date = date;
        this.REFERENCEP = REFERENCEP;
    }



    public int getREFERENCEP() {
        return REFERENCEP;
    }

    public void setREFERENCEP(int REFERENCEP) {
        this.REFERENCEP = REFERENCEP;
    }
    
    

    public Commande(String etat,String prix, String date) {
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

    public String getPrix() {
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

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCom=" + idCom + ", etat=" + etat + ", prix=" + prix + ", date=" + date + ", REFERENCEP=" + REFERENCEP + ", refcmd=" + refcmd + ", refclient=" + refclient + ", IMAGE=" + IMAGE + ", quantiteA=" + quantiteA + '}';
    }

  
   
    
    
}
