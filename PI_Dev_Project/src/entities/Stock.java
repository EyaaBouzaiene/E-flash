/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Khadija
 */
public class Stock {

    private int idS;
    private String Partenaire;
    private String nomS;
    private int refS;
    private String categorieS;
    private int qteS;
    private Date dateS;
    private int qualiteS ;

    public Stock(int idS, String Partenaire, String nomS, int refS, String categorieS, int qteS, Date dateS, int qualiteS) {
        this.idS = idS;
        this.Partenaire = Partenaire;
        this.nomS = nomS;
        this.refS = refS;
        this.categorieS = categorieS;
        this.qteS = qteS;
        this.dateS = dateS;
        this.qualiteS = qualiteS;
    }

    public Stock(String Partenaire, String nomS, int refS, String categorieS, int qteS, Date dateS, int qualiteS) {
        this.Partenaire = Partenaire;
        this.nomS = nomS;
        this.refS = refS;
        this.categorieS = categorieS;
        this.qteS = qteS;
        this.dateS = dateS;
        this.qualiteS = qualiteS;
    }

    

    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    public String getPartenaire() {
        return Partenaire;
    }

    public void setPartenaire(String Partenaire) {
        this.Partenaire = Partenaire;
    }

    
    public String getNomS() {
        return nomS;
    }

    public void setNomS(String nomS) {
        this.nomS = nomS;
    }

    public int getRefS() {
        return refS;
    }

    public void setRefS(int refS) {
        this.refS = refS;
    }

    public String getCategorieS() {
        return categorieS;
    }

    public void setCategorieS(String categorieS) {
        this.categorieS = categorieS;
    }

    public int getQteS() {
        return qteS;
    }

    public void setQteS(int qteS) {
        this.qteS = qteS;
    }

    public Date getDateS() {
        return dateS;
    }

    public void setDateS(Date dateS) {
        this.dateS = dateS;
    }

    public int getQualiteS() {
        return qualiteS;
    }

    public void setQualiteS(int qualite) {
        this.qualiteS = qualiteS;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idS;
        hash = 89 * hash + Objects.hashCode(this.Partenaire);
        hash = 89 * hash + Objects.hashCode(this.nomS);
        hash = 89 * hash + this.refS;
        hash = 89 * hash + Objects.hashCode(this.categorieS);
        hash = 89 * hash + this.qteS;
        hash = 89 * hash + Objects.hashCode(this.dateS);
        hash = 89 * hash + this.qualiteS;
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
        final Stock other = (Stock) obj;
        if (this.idS != other.idS) {
            return false;
        }
        if (this.refS != other.refS) {
            return false;
        }
        if (this.qteS != other.qteS) {
            return false;
        }
        if (this.qualiteS != other.qualiteS) {
            return false;
        }
        if (!Objects.equals(this.Partenaire, other.Partenaire)) {
            return false;
        }
        if (!Objects.equals(this.nomS, other.nomS)) {
            return false;
        }
        if (!Objects.equals(this.categorieS, other.categorieS)) {
            return false;
        }
        if (!Objects.equals(this.dateS, other.dateS)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
             
          System.out.println("");
              return   "Stock{" + "idS=" + idS +""
                      + ", Partenaire=" + Partenaire + ", nomS=" 
                      + nomS + ", refS=" 
                      + refS + ", categorieS="
                      + categorieS + ", qteS=" 
                      + qteS + ", dateS=" 
                      + dateS + ", qualiteS=" 
                      + qualiteS + '}';
              
      
    }

   

  
    


}
