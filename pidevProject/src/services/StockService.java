/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Partenaire;
import entities.Stock;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.MyDB;

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
       
        String req = "INSERT INTO `stock`(`Partenaire`,`nomS`, `refS`, `categorieS`,`qteS`, `dateS`,`qualiteS`) "
          + "VALUES ('" + s.getPartenaire() + "','"+ s.getNomS() + "','" + s.getRefS() + "','" + s.getCategorieS() + "','" + s.getQteS()+ "','" + s.getDateS()+ "','" + s.getQualiteS()+ "')";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    
  
 
    @Override
    public List<Stock> afficherS() throws SQLException {

        List<Stock> stocks = new ArrayList<>();
        String req = "SELECT * , partenaires.nomP FROM `stock`,`partenaires` WHERE stock.Partenaire=partenaires.idP ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
     
        while (rst.next()) {
            Stock s = new Stock(rst.getInt("idS"),
                    rst.getString("nomP"),
                    rst.getString("nomS"),
                    rst.getInt("refS"),
                    rst.getString("categorieS"),
                      rst.getInt("qteS"),
                    rst.getDate("dateS"),
              rst.getInt("qualiteS"));
            
          stocks.add(s);
        }
    

        return stocks;
    }

    @Override
    public void modifierS() throws SQLException {

        String req = "UPDATE `stock` SET `nomS`='internet',`refS`='1000',`categorieS`='internet' WHERE idS='107 '";
       
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    
    @Override
    public void supprimerS() throws SQLException {
        String req = "DELETE FROM `stock` WHERE idS='107 ' ";
        //WHERE IdS=60
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Stock> rechercheS() throws SQLException {
      
        List<Stock> stocks = new ArrayList<>();
        Scanner Sc= new Scanner(System.in);
        System.out.println("**********Veuiller entrer le nom du stock**********");
        String name=Sc.next();
        String req = "SELECT * , partenaires.nomP FROM `stock`,`partenaires` WHERE stock.Partenaire=partenaires.idP AND nomS ='" +name.toLowerCase()  +"'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
      
        while (rst.next()) {
            Stock s = new Stock(rst.getInt("idS"),
                    rst.getString("nomP"),
                    rst.getString("nomS"),
                    rst.getInt("refS"),
                    rst.getString("categorieS"),
                      rst.getInt("qteS"),
                    rst.getDate("dateS"),
              rst.getInt("qualiteS"));
        
            stocks.add(s);
          
        }
     
        return stocks;
        
    }

     @Override
    public List<Stock> trie() throws SQLException {

        List<Stock> stocks = new ArrayList<>();
        String req = "SELECT * , partenaires.nomP FROM `stock`,`partenaires` WHERE stock.Partenaire=partenaires.idP ORDER BY categorieS";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
     
        while (rst.next()) {
            Stock s = new Stock(rst.getInt("idS"),
                    rst.getString("nomP"),
                    rst.getString("nomS"),
                    rst.getInt("refS"),
                    rst.getString("categorieS"),
                      rst.getInt("qteS"),
                    rst.getDate("dateS"),
              rst.getInt("qualiteS"));
            
          stocks.add(s);
        }
    

        return stocks;
    }

    
    @Override
    public void Traitement () throws SQLException{
        
            
        String req = "SELECT partenaires.idP FROM `partenaires`,`stock`  WHERE stock.Partenaire=partenaires.idP AND stock.qtes <=3";
      
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

      do {
            System.out.println("hey1");        
         String req1 = "SELECT `mailP` FROM partenaires";
      
        stm = connexion.createStatement();
             ResultSet rst1 = stm.executeQuery(req1);
           
          while (rst1.next()){
             
                System.out.println("hey2");  
                
            Mail.send(
            "khanfirkhadija66@gmail.com",
            "bebe@@##123,",
             rst1.getString("mailP"),
            "Bienvenue ",
            "mail de test!"
            );

      }
           
              
      
          System.out.println("hey5");
      
        String req2 = "SELECT partenaires.categorieP FROM `partenaires`,`partenairesecours` WHERE partenaire.categorieP =partenairesecours.categoriePS  ";
        //AND partenaire.categorieP=rst.getString("categorieP")
        System.out.println("hey6");
        stm = connexion.createStatement();
         
        ResultSet rst2 = stm.executeQuery(req2);
     
       do {
        
            String req3 = "SELECT `mailPS` FROM partenairesecours";
              
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst3 = stm.executeQuery(req3);
        while (rst2.next()) {
       Mail.send(
            "khanfirkhadija66@gmail.com",
            "bebe@@##123,",
            "khanfirkhadija66@gmail.com",
            "Bienvenu ",
            "mail de test!"
            );
       }
        
       }while (rst2.next());
       }  while (rst.next());
      }
}
 

          


          
    
         
    


     
       

   
