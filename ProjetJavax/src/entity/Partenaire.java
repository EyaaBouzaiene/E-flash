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
public class Partenaire {

    private int idP;
    private int MatriculeP;
    private String nomMarqueP;
    private String nomP;
    private String prenomP;
    private String mailP;
    private String categorieP;
    private Date dateP;
    

    public Partenaire(int idP, int MatriculeP,String nomMarqueP, String nomP, String prenomP, String mail, String categorieP, Date dateP) {
        this.idP = idP;
        this. MatriculeP=  MatriculeP;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.mailP = mail;
        this.categorieP = categorieP;
        this.dateP = dateP;
        this.nomMarqueP=nomMarqueP;
  
    }

    public Partenaire(int MatriculeP,String nomMarqueP,String nomP, String prenomP, String mailP, String categorieP, Date dateP) {
     this.MatriculeP=MatriculeP;
        this.nomMarqueP=nomMarqueP;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.mailP = mailP;
        this.categorieP = categorieP;
        this.dateP = dateP;
        
    }

    

  
    public Partenaire(int MatriculeP,String nomMarqueP,String nomP, String prenomP) {
      this.MatriculeP=MatriculeP;
        this.nomMarqueP=nomMarqueP;
        this.nomP = nomP;
        this.prenomP = prenomP;
   
    }

    public int getMatriculeP() {
        return MatriculeP;
    }

    public void setMatriculeP(int MatriculeP) {
        this.MatriculeP = MatriculeP;
    }

    public String getNomMarqueP() {
        return nomMarqueP;
    }

    public void setNomMarqueP(String nomMarqueP) {
        this.nomMarqueP = nomMarqueP;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idP;
        hash = 29 * hash + this.MatriculeP;
        hash = 29 * hash + Objects.hashCode(this.nomMarqueP);
        hash = 29 * hash + Objects.hashCode(this.nomP);
        hash = 29 * hash + Objects.hashCode(this.prenomP);
        hash = 29 * hash + Objects.hashCode(this.mailP);
        hash = 29 * hash + Objects.hashCode(this.categorieP);
        hash = 29 * hash + Objects.hashCode(this.dateP);
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
        if (this.MatriculeP != other.MatriculeP) {
            return false;
        }
        if (!Objects.equals(this.nomMarqueP, other.nomMarqueP)) {
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
        return "Partenaire{" + "idP=" + idP + ", MatriculeP=" + MatriculeP + ", nomMarqueP=" + nomMarqueP + ", nomP=" + nomP + ", prenomP=" + prenomP + ", mailP=" + mailP + ", categorieP=" + categorieP + ", dateP=" + dateP + '}';
    }

   

  
  

  

 

    


    
   


}
