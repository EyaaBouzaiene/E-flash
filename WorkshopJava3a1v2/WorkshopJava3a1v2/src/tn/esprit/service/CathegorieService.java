/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import tn.esprit.model.*;
import tn.esprit.model.Produits;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.utils.MyDB;

/**
 *
 * @author aders
 */
public class CathegorieService implements ICathegorie<Cathegorie> {

    Connection connexion;
    Statement stm;

    public CathegorieService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterc(Cathegorie c) throws SQLException {
        String req = "INSERT INTO `categories`(`NOMCAT`) VALUES ('" + c.getNomC() + "')";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Cathegorie> affichercathegorie() throws SQLException {
        List<Cathegorie> cathegorie = new ArrayList<>();
        String req = "SELECT * FROM `categories`";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Cathegorie s = new Cathegorie(rst.getInt("code"),
                    rst.getString("nomCAT")
            );
            cathegorie.add(s);
        }
        return cathegorie;
    }

    @Override
    public void supprimerC() throws SQLException {
        String req = "DELETE FROM `categories` ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public void modifierC() throws SQLException {
        String req = "UPDATE `categories` SET `NOMCAT`='plat72' WHERE CODE='31'";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void afficher_nom_cathegorie(int id) throws SQLException {
        String s = null;
        String req = "SELECT `NOMCAT` FROM `categories` WHERE CODE='"+id+"'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        
      
    }
    @Override
     public void afficher_nom_cathegorie() throws SQLException {
        String s = null;
        String req = "SELECT `NOMCAT` FROM `categories`";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while(rst.next())
        {
            rst.getString("NOMCAT");
        }

        
      
    }

    
    

}
