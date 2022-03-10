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
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import tn.esprit.utils.MyDB;

/**
 *
 * @author aders
 */
public class ProduitsService implements IProduits<Produits> {

    Connection connexion;
    Statement stm;

    public ProduitsService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterp(Produits p) throws SQLException {
        String req = "INSERT INTO `produits`(`NOM`, `TYPE`, `quantite`, `DATEP`, `DESC`,`IMAGE`,`CATEGORIE`,`rate`,`quantiteR`) VALUES ('" + p.getNom() + "','" + p.getType() + "','" + p.getQuantite() + "','" + p.getDateP() + "','" + p.getDesc() + "','" + p.getIMAGE() + "','" + p.getCATEGORIE() + "','" +p.getRate()+"','" +p.getQuantiteR()+"')";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    

    public List<Produits> afficherproduit() throws SQLException {

        List<Produits> produit = new ArrayList<>();
         CathegorieService cp = new CathegorieService();
        String req = "SELECT *,categories.NOMCAT FROM `produits`, `categories`  WHERE produits.CATEGORIE=categories.CODE ORDER BY CATEGORIE";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produits s;
            s = new Produits(rst.getInt("reference"),
                    rst.getInt("quantite"),
                     rst.getInt("quantiteR"),
                    rst.getString("rate"),
                    rst.getString("nom"),
                    rst.getString("desc"),
                    rst.getString("type"),
                    rst.getDate("dateP"),
                    rst.getString("NOMCAT"),
                    rst.getString("IMAGE"),
                    rst.getDouble("Prix")
            );
            produit.add(s);
        }
        return produit;
    }

    @Override
    public void supprimerP() throws SQLException {
        String req = "DELETE FROM `produits` ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public void modifierP() throws SQLException {
        String req = "UPDATE `produits` SET `NOM`='Delice',`TYPE`='plat',`quantite`='5',`DESC`='Azerty',`quantite`='5' WHERE REFERENCE='10'";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Produits> recherche() throws SQLException {
       List<Produits> produit = new ArrayList<>();
       
        Scanner S = new Scanner(System.in);
         CathegorieService cp = new CathegorieService();
        System.out.println( " please enter name to search : " );
        String name=S.next();
        String req = "SELECT *,categories.NOMCAT FROM `produits`, `categories`  WHERE produits.CATEGORIE=categories.CODE AND NOM= '" +name + "'";
        stm = connexion.createStatement();
       
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produits s;
            s = new Produits(rst.getInt("reference"),
                    rst.getInt("quantite"),
                     rst.getInt("quantiteR"),
                    rst.getString("rate"),
                    rst.getString("nom"),
                    rst.getString("desc"),
                    rst.getString("type"),
                    rst.getDate("dateP"),
                    rst.getString("NOMCAT"),
                    rst.getString("IMAGE")
                    
                    
            );
            produit.add(s);
       
        }
        return produit;
    }
    
  @Override
    public void ajouterrate() throws SQLException {
        
        Scanner S2 = new Scanner(System.in);
        System.out.println( " vous voulez ajouter un rating sur un produit " );
        String name2=S2.next();
        if("oui".equals(name2))
        {
            Scanner S = new Scanner(System.in);
        System.out.println( " please nom produit pour evaluer " );
        String name=S.next();
        Scanner S3 = new Scanner(System.in);
       
          String req2="SELECT NOM FROM `produits` WHERE produits.NOM='"+name+"'";
            stm = connexion.createStatement();
       
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req2);
            if(rst.next())
            {
                 System.out.println( " please entrer note produit " );
                String name3=S3.next();
                String req = "UPDATE `produits` SET `rate`='"+name3+"' WHERE produits.NOM='"+name+"'";
                stm = connexion.createStatement();
                stm.executeUpdate(req);
                
            } else {
               
                 System.out.println( "nom produit introuvable " );
            }
            
        
        } else {
            System.out.println( " merci pour votre attention " );
        }
            
    }
    
    
    public List<Produits> afficher_meilleurs_produit() throws SQLException {

        List<Produits> produit = new ArrayList<>();
         
        String req = "SELECT NOM FROM `produits`  WHERE produits.quantiteR=(select MIN(quantiteR) from produits )";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produits s;
            s = new Produits(rst.getString("NOM")
                    
            );
            produit.add(s);
            System.out.println("Meilleur produit vendu est");
        }
        return produit;
    }
    

}
