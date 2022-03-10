/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Khadija
 */
public class Stock {

    private int idS;
    private String nomPartenaireS;
    private String nomS;
    private int refS;
    private String categorieS;
    private int qteS;
    private Date dateS;
    private String qualiteS ;
    

    public Stock(String nomPartenaireS, String nomS, int refS, String categorieS, int qteS, Date dateS, String qualiteS) {
        this.nomPartenaireS = nomPartenaireS;
        this.nomS = nomS;
        this.refS = refS;
        this.categorieS = categorieS;
        this.qteS = qteS;
        this.dateS = dateS;
        this.qualiteS = qualiteS;
    }

    

    
    public Stock(int idS, String nomPartenaireS, String nomS, int refS, String categorieS, int qteS, Date dateS, String qualiteS) {
        this.idS = idS;
        this.nomPartenaireS = nomPartenaireS;
        this.nomS = nomS;
        this.refS = refS;
        this.categorieS = categorieS;
        this.qteS = qteS;
        this.dateS = dateS;
        this.qualiteS = qualiteS;
    }

  

    public Stock(String nomPartenaireS, String nomS, int refS, String categorieS, int qteS, Date dateS) {

        this.nomPartenaireS = nomPartenaireS;
        this.nomS = nomS;
        this.refS = refS;
        this.categorieS = categorieS;
        this.qteS = qteS;
        this.dateS = dateS;
    }

    

    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    public String getNomPartenaireS() {
        return nomPartenaireS;
    }

    public void setNomPartenaireS(String Partenaire) {
        this.nomPartenaireS = nomPartenaireS;
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

    public String getQualiteS() {
        return qualiteS;
    }

    public void setQualiteS(String qualite) {
        this.qualiteS = qualiteS;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idS;
        hash = 97 * hash + Objects.hashCode(this.nomPartenaireS);
        hash = 97 * hash + Objects.hashCode(this.nomS);
        hash = 97 * hash + this.refS;
        hash = 97 * hash + Objects.hashCode(this.categorieS);
        hash = 97 * hash + this.qteS;
        hash = 97 * hash + Objects.hashCode(this.dateS);
        hash = 97 * hash + Objects.hashCode(this.qualiteS);
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
        if (!Objects.equals(this.nomPartenaireS, other.nomPartenaireS)) {
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
        return "Stock{" + "idS=" + idS + ", nomPartenaireS=" + nomPartenaireS + ", nomS=" + nomS + ", "
                + "refS=" + refS + ", categorieS=" + categorieS + ", qteS=" + qteS + ", dateS=" + dateS + ", qualiteS=" + qualiteS + '}';
    }

    
    
   

   

  
    


}
