package pi_dev_project;

import entities.Partenaire;
import entities.Stock;
import services.StockService;
import services.PartenaireService;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.Mail;


/**
 *
 * @author Khadija
 */
public class PI_Dev_Project {

    public static void main(String[] args) {

       
      //  Stock S = new Stock(45,"kk", 123, "loc",4, new Date(System.currentTimeMillis()),5);
        Stock S= new Stock("48", "equipement", 123, "equipement", 5,new Date(System.currentTimeMillis()),8);
           Stock S2 = new Stock("48","Equipement", 123, "loc",4, new Date(System.currentTimeMillis()),5);
        StockService s = new StockService();
        Partenaire P = new Partenaire("m1", "p1", "Alimentaire","abdelkader.bouali@gmail.com", new Date(System.currentTimeMillis()),5);
        PartenaireService p = new PartenaireService();
        PartenaireService p2=new PartenaireService();
      
        
        /*
        
      System.err.println("*********************************Stocks*********************************");
      
      
        try {
              System.err.println("************Ajout Stocks************");
            s.ajouterS(S);
            System.out.println("ajout avec succes");     
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
        
        
        /*
        
        System.err.println("************Affichage Stocks************");
       try{
       System.out.println( s.afficherS());
}
       catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
       
          System.err.println("************Recherche Stocks************");
       try{
       System.out.println(s.rechercheS());
}
       catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
        
        System.err.println("************Modification Stocks************");
         try{
       s.modifierS();
         System.out.println("Modification éffectué");
           
}
         
       catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
      
        
         System.err.println("************Affichage Stocks************");
       try{
       System.out.println(s.afficherS());
}
       catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
       
       
           System.out.println("************Suppression Stocks************");
        try {
            System.out.println("Suppression réussie");
            s.supprimerS();
            
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.err.println("************Affichage Stocks************");
       try{
       System.out.println(s.afficherS());
}
       catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
       
       */
       
            /*
            
            System.err.println("*********************************Partenaire*********************************");
            
            try {
            p.ajouterP(P);
            System.out.println("ajout partenaire avec succes");
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            }
            
            try {
            
            System.out.println(p.afficherP());
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            }
            /*
            */
            /*
            
            System.err.println("************Modificaion Partenaire************");
            
            try {
            p.modifierP();
            System.out.println("Modification avec succes");
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            System.out.println("Affichage Partenaires");
            try {
            System.out.println(p.afficherP());
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            /*
            
            
            System.err.println("************Supprimer Partenaire************");
            try {
            p.supprimerP();
            System.out.println("Suppression partenaire éffectué avec succés");
            System.out.println(p.afficherP());
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            }
            
            */
            /*
            System.err.println("************Meilleur(s) Partenaire(s)************");
            try{
            System.out.println( p2.MeilleursP());
            }
            catch(SQLException ex){
            System.out.println(ex.getMessage());
            }
            
            */
            
            
          /*
            //from, password, to, subject, message
            Mail.send(
            "khanfirkhadija66@gmail.com",
            "bebe@@##123,",
            "khanfirkhadija66@gmail.com",
            "Bienvenu ",
            "mail de test!"
            );
            */
            
           
  System.err.println("************Traitement************");
            try{
            s.Traitement();
                System.out.println("Mail envoyé avec Succes");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }

   
 
        }
    }

