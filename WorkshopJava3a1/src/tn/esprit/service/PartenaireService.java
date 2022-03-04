/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import tn.esprit.model.Partenaire;
import tn.esprit.model.Stock;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import tn.esprit.utils.MyDB;

/**
 *
 * @author Khadija
 */
public class PartenaireService implements IPartenaires<Partenaire> {

    Connection connexion;
    Statement stm;

    public PartenaireService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterP(Partenaire p) throws SQLException {
        String req = "INSERT INTO `partenaires`(`nomP`, `prenomP`,`mailP`, `categorieP`, `dateAjout`) VALUES ('" + p.getNomP() + "','" + p.getPrenomP()  +"','" + p.getMailP()+ "','"+ p.getCategorieP() + "','" + p.getDateP() + "')";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Partenaire> afficherP() throws SQLException {

        List<Partenaire> partenaires = new ArrayList<>();
        String req = "SELECT * FROM `partenaires`";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Partenaire p = new Partenaire(rst.getInt("idP"),
                    rst.getString("nomP"),
                    rst.getString("prenomP"),
                    rst.getString("mailP"),
                    rst.getString("categorieP"),
                    rst.getDate("dateAjout")
                  );
            partenaires.add(p);

        }
        return partenaires;
    }

    @Override
    public void modifierP() throws SQLException {

        String req = "UPDATE `partenaires` SET `nomP`='abdelwaheb' , `prenomP`='sabr' , `mailP`='abdelwaheb.sabr@gmail.com' , `categorieP`='alimentaire' WHERE idP='50'  ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void supprimerP() throws SQLException {
        String req = "DELETE FROM `partenaires`WHERE nomP=? ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
   
    @Override
    public List<Partenaire> MeilleursP() throws SQLException {
      
        List<Partenaire> partenaires = new ArrayList<>();
        String req = "SELECT * FROM `partenaires`,`stock` WHERE qualiteS=5 AND partenaires.idP=stock.Partenaire ";
        //,prenomP,qualiteP
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        
int i=0;
          while (rst.next()) {
            Partenaire p = new Partenaire(rst.getInt("idP"),
                    rst.getString("nomP"),
                    rst.getString("prenomP"),
                     rst.getString("mailP"),
                    rst.getString("categorieP"),
                    rst.getDate("dateAjout")
                    );
            partenaires.add(p);
            i=i+1;
 
        }
        System.out.println("Le Nombre de meilleur Partenraire est "+i);
        return partenaires;
        
    }

  
}