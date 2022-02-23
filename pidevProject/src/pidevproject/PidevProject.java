/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevproject;

import entities.Cathegorie;
import entities.Clients;
import entities.Produits;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import services.ProduitsService;
import java.sql.Timestamp;
import java.text.DateFormat;
import services.CathegorieService;
import services.ClientsService;
/**
 *
 * @author aders
 */
public class PidevProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
      
         
        Cathegorie c = new Cathegorie("Alimentaire");
        Cathegorie c2 = new Cathegorie("Liquide");
        ProduitsService sp = new ProduitsService();
        CathegorieService cp = new CathegorieService();
        Produits p = new Produits(12,12,"0","Vitalait","Djeja","lait",new Date(System.currentTimeMillis()),"1","hmahama");
         Produits p2 = new Produits(13,13,"0","Vitalait2","Djeja1","lait2",new Date(System.currentTimeMillis()),"4","hmahama");
         
         Clients cl=new Clients(2, "Dhia", "Aderssa", "24");
         ClientsService clv=new ClientsService();
//         System.err.println("*********************************Ajouter cathegorie *************************************");   
//        try {
//            cp.ajouterc(c2);
//            System.out.println("ajout avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//            System.out.println(cp.affichercathegorie());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//         System.err.println("*********************************modifier cathegorie *************************************");   
//        try {
//            cp.modifierC();
//            System.out.println("modifie avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
//
//        try {
//            System.out.println(cp.affichercathegorie());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
// System.err.println("*********************************supprimer cathegorie *************************************");   
//        try {
//            cp.supprimerC();
//            System.out.println("Suppression réussie");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//          try {
//            System.out.println(cp.affichercathegorie());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }    
        
        
        
 System.err.println("*********************************produit*************************************");   
   
 System.err.println("*********************************Ajouter produit *************************************");
try {
            sp.ajouterp(p2);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
        
            System.out.println(sp.afficherproduit());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.err.println("*********************************meilleur produit vendu *************************************");
        try {
        
            System.out.println(sp.afficher_meilleurs_produit());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//        try {
//            sp.ajouterrate();
//            System.out.println("ajout rate avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//        
//            System.out.println(sp.afficherproduit());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
         
//        System.err.println("*********************************recherche produit *************************************");
//         try {
//            System.out.println(sp.recherche());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        System.err.println("*********************************modifier produit *************************************"); 
//          try {
//            sp.modifierP();
//            System.out.println("modifie avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//            System.out.println(sp.afficherproduit());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        System.err.println("*********************************supprimer produit *************************************");  
//        try {
//            sp.supprimerP();
//            System.out.println("Suppression réussie");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//          try {
//            System.out.println(sp.afficherproduit());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }       

//System.err.println("*********************************produit*************************************");   
//   
// System.err.println("*********************************Ajouter clients *************************************");
//  try {
//            clv.ajouterC(cl);
//            System.out.println("ajout rate avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//        
//            System.out.println(sp.afficherproduit());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }
//    
}
