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
public class PartenaireSecours {
     private int idPS;
    private String nomPS;
    private String prenomPS;
    private String mailPS;
    private String categoriePS;
    private Date datePS;
    private int qualitePS;

    public PartenaireSecours(int idPS, String nomPS, String prenomPS, String mailPS, String categoriePS, Date datePS, int qualitePS) {
        this.idPS = idPS;
        this.nomPS = nomPS;
        this.prenomPS = prenomPS;
        this.mailPS = mailPS;
        this.categoriePS = categoriePS;
        this.datePS = datePS;
        this.qualitePS = qualitePS;
    }

    public PartenaireSecours(String nomPS, String prenomPS, String mailPS, String categoriePS, Date datePS, int qualitePS) {
        this.nomPS = nomPS;
        this.prenomPS = prenomPS;
        this.mailPS = mailPS;
        this.categoriePS = categoriePS;
        this.datePS = datePS;
        this.qualitePS = qualitePS;
    }

    public int getIdPS() {
        return idPS;
    }

    public void setIdPS(int idPS) {
        this.idPS = idPS;
    }

    public String getNomPS() {
        return nomPS;
    }

    public void setNomPS(String nomPS) {
        this.nomPS = nomPS;
    }

    public String getPrenomPS() {
        return prenomPS;
    }

    public void setPrenomPS(String prenomPS) {
        this.prenomPS = prenomPS;
    }

    public String getMailPS() {
        return mailPS;
    }

    public void setMailPS(String mailPS) {
        this.mailPS = mailPS;
    }

    public String getCategoriePS() {
        return categoriePS;
    }

    public void setCategoriePS(String categoriePS) {
        this.categoriePS = categoriePS;
    }

    public Date getDatePS() {
        return datePS;
    }

    public void setDatePS(Date datePS) {
        this.datePS = datePS;
    }

    public int getQualitePS() {
        return qualitePS;
    }

    public void setQualitePS(int qualitePS) {
        this.qualitePS = qualitePS;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idPS;
        hash = 29 * hash + Objects.hashCode(this.nomPS);
        hash = 29 * hash + Objects.hashCode(this.prenomPS);
        hash = 29 * hash + Objects.hashCode(this.mailPS);
        hash = 29 * hash + Objects.hashCode(this.categoriePS);
        hash = 29 * hash + Objects.hashCode(this.datePS);
        hash = 29 * hash + this.qualitePS;
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
        final PartenaireSecours other = (PartenaireSecours) obj;
        if (this.idPS != other.idPS) {
            return false;
        }
        if (this.qualitePS != other.qualitePS) {
            return false;
        }
        if (!Objects.equals(this.nomPS, other.nomPS)) {
            return false;
        }
        if (!Objects.equals(this.prenomPS, other.prenomPS)) {
            return false;
        }
        if (!Objects.equals(this.mailPS, other.mailPS)) {
            return false;
        }
        if (!Objects.equals(this.categoriePS, other.categoriePS)) {
            return false;
        }
        if (!Objects.equals(this.datePS, other.datePS)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PartenaireSecours{" + "idPS=" + idPS + ", nomPS=" + nomPS + ", prenomPS=" + prenomPS + ", mailPS=" + mailPS + ", categoriePS=" + categoriePS + ", datePS=" + datePS + ", qualitePS=" + qualitePS + '}';
    }
    
    
}
