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
public class Partenaire {

    private int idP;
    private String nomP;
    private String prenomP;
    private String mailP;
    private String categorieP;
    private Date dateP;
    private int qualiteP;

    public Partenaire(int idP, String nomP, String prenomP, String mail, String categorieP, Date dateP, int qualiteP) {
        this.idP = idP;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.mailP = mailP;
        this.categorieP = categorieP;
        this.dateP = dateP;
        this.qualiteP = qualiteP;
    }

    public Partenaire(String nomP, String prenomP, String mail, String categorieP, Date dateP, int qualiteP) {
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.mailP = mailP;
        this.categorieP = categorieP;
        this.dateP = dateP;
        this.qualiteP = qualiteP;
    }

    

  
    public Partenaire(String nomP, String prenomP, int qualiteP) {
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.qualiteP = qualiteP;
    }

    public Partenaire(String nomP) {
        this.nomP = nomP;
    }

    
    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getPrenomP() {
        return prenomP;
    }

    public void setPrenomP(String prenomP) {
        this.prenomP = prenomP;
    }

    public String getCategorieP() {
        return categorieP;
    }

    public String getMailP() {
        return mailP;
    }

    public void setMailP(String mailP) {
        this.mailP = mailP;
    }

    public void setCategorieP(String categorieP) {
        this.categorieP = categorieP;
    }

    public Date getDateP() {
        return dateP;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }

    public int getQualiteP() {
        return qualiteP;
    }

    public void setQualiteP(int qualiteP) {
        this.qualiteP = qualiteP;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idP;
        hash = 29 * hash + Objects.hashCode(this.nomP);
        hash = 29 * hash + Objects.hashCode(this.prenomP);
        hash = 29 * hash + Objects.hashCode(this.mailP);
        hash = 29 * hash + Objects.hashCode(this.categorieP);
        hash = 29 * hash + Objects.hashCode(this.dateP);
        hash = 29 * hash + this.qualiteP;
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
        final Partenaire other = (Partenaire) obj;
        if (this.idP != other.idP) {
            return false;
        }
        if (this.qualiteP != other.qualiteP) {
            return false;
        }
        if (!Objects.equals(this.nomP, other.nomP)) {
            return false;
        }
        if (!Objects.equals(this.prenomP, other.prenomP)) {
            return false;
        }
        if (!Objects.equals(this.mailP, other.mailP)) {
            return false;
        }
        if (!Objects.equals(this.categorieP, other.categorieP)) {
            return false;
        }
        if (!Objects.equals(this.dateP, other.dateP)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Partenaire{" + "idP=" + idP + ", nomP=" + nomP + ", prenomP=" + prenomP + ", mailP=" + mailP + ", categorieP=" + categorieP + ", dateP=" + dateP + ", qualiteP=" + qualiteP + '}';
    }

    


    
   


}
