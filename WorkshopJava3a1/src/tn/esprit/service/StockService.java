/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import tn.esprit.model.Stock;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.stage.Window;
import tn.esprit.utils.MyDB;

/**
 *
 * @author Khadija
 */
public class StockService implements IStock<Stock> {

    Connection connexion;
    Statement stm;

    public StockService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterS(Stock s) throws SQLException {

        String req = "INSERT INTO `stock`(`nomPartenaireS`,`nomS`, `refS`, `categorieS`,`qteS`, `dateS`,`qualiteS`)"
                + "VALUES ('" + s.getNomPartenaireS() + "','" + s.getNomS() + "','" + s.getRefS() + "','" + s.getCategorieS() + "','" + s.getQteS() + "','" + s.getDateS() + "','" + s.getQualiteS() + "')";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public List<Stock> afficherS() throws SQLException {

        List<Stock> stocks = new ArrayList<>();
        String req = "SELECT * , partenaires.nomPartenaireS FROM `stock`,`partenaires` WHERE stock.nomPartenaireS=partenaires.nomMarqueP ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Stock s = new Stock(rst.getInt("idS"),
                    rst.getString("nomPartenaireS"),
                    rst.getString("nomS"),
                    rst.getString("refS"),
                    rst.getString("categorieS"),
                    rst.getInt("qteS"),
                    rst.getDate("dateS"),
                    rst.getString("qualiteS"));

            stocks.add(s);
        }

        return stocks;
    }

    @Override
    public void modifierS() throws SQLException {

        String req = "UPDATE `stock` SET `nomS`='internet',`refS`='1000',`categorieS`='internet' WHERE idS=? '";

        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void supprimerS() throws SQLException {
        String req = "DELETE FROM `stock` WHERE nomPartenaireS=?";
        //WHERE IdS=60
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Stock> rechercheS() throws SQLException {

        List<Stock> stocks = new ArrayList<>();
        Scanner Sc = new Scanner(System.in);
        System.out.println("**********Veuiller entrer le nom du stock**********");
        String name = Sc.next();
        String req = "SELECT * , partenaires.nomMarqueP FROM `stock`,`partenaires` WHERE stock.nomPartenaireS=partenaires.nomMarqueP AND nomS ='" + name.toLowerCase() + "'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Stock s = new Stock(rst.getInt("idS"),
                    rst.getString("nomP"),
                    rst.getString("nomS"),
                    rst.getString("refS"),
                    rst.getString("categorieS"),
                    rst.getInt("qteS"),
                    rst.getDate("dateS"),
                    rst.getString("qualiteS"));

            stocks.add(s);

        }

        return stocks;

    }

    @Override
    public List<Stock> trie() throws SQLException {

        List<Stock> stocks = new ArrayList<>();
        String req = "SELECT * , partenaires.nomMarqueP FROM `stock`,`partenaires` WHERE stock.PartenaireS=partenaires.nomPartenaireS ORDER BY categorieS";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Stock s = new Stock(rst.getInt("idS"),
                    rst.getString("nomPartenaireS"),
                    rst.getString("nomS"),
                    rst.getString("refS"),
                    rst.getString("categorieS"),
                    rst.getInt("qteS"),
                    rst.getDate("dateS"),
                    rst.getString("qualiteS"));

            stocks.add(s);
        }

        return stocks;
    }

    @Override
    public void TraitementF() throws SQLException {

//        String req = "SELECT partenaires.mailP, partenaires.categorieP FROM `partenaires`,`stock`  WHERE stock.Partenaire=partenaires.idP AND stock.qualiteS <=3";
//
//        stm = connexion.createStatement();
//        //ensemble de resultat
//        ResultSet rst = stm.executeQuery(req);
//
//        while (rst.next()) {
//
//            System.out.println("hey2");
//
//            Mail.send(
//                    "flashelectro06@gmail.com",
//                    "dhia1881",
//                    rst.getString("mailP"),
//                    "MAIL ANNULATION",
//                    "MAIL ANNULATION"
//            );
//
//            String req1 = "SELECT partenairesecours.mailPS FROM `partenairesecours`, `partenaires`  WHERE  partenairesecours.categoriePS=partenaires.categorieP ";
//
//            stm = connexion.createStatement();
//            //ensemble de resultat
//            ResultSet rst1 = stm.executeQuery(req1);
//            if (rst1.next()) {
//                System.out.println("hey3");
//                Mail.send(
//                        "flashelectro06@gmail.com",
//                        "dhia1881",
//                        rst.getString("mailP"),
//                        " Convention ",
//                        "Convention"
//                );
//
//                String req2 = "UPDATE `partenaires` INNER JOIN  `partenairesecours` ON  partenairesecours.categoriePS=partenaires.categorieP  SET nomP=`nomPS` , prenomP=`prenomPS`,mailP=`mailPS`,categorieP=`categoriePS`,dateAjout=CURRENT_TIMESTAMP ";
//                stm = connexion.createStatement();
//                //ensemble de resultat
//                stm.executeUpdate(req2);
//                String req3 = "DELETE FROM `partenairesecours`  WHERE partenairesecours.categoriePS='" + rst.getString("categorieP") + "' ";
//                stm = connexion.createStatement();
//                //ensemble de resultat
//                stm.executeUpdate(req3);
//            }
//        }

    }

//    @Override
//    public void TraitementS() throws SQLException {
//        String req = "SELECT partenaires.mailP FROM `stock`,`partenaires`  WHERE stock.Partenaire=partenaires.idP AND stock.qteS <=3";
//
//        stm = connexion.createStatement();
//        //ensemble de resultat
//        ResultSet rst = stm.executeQuery(req);
//
//        while (rst.next()) {
//            System.out.println(rst.getString("mailP"));
//Mail m =new Mail( "Besoin produit !", rst.getString("mailP"),  "Besoin produit dans 2 jours la livraison !");
//            Mail.sendMail();
//            
//
//        }
//
//    }
    
    
  
    
}
