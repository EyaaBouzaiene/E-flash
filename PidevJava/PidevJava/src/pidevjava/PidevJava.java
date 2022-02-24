/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Livraison;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.LivraisonService;


/**
 *
 * @author macbook
 */
public class PidevJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         Livraison l = new Livraison(1,123,456,20,"zaghouen","NonValide");
         Livraison l1 = new Livraison(2,222,111,150,"Tunis","NonValide");
         Livraison l2 = new Livraison(3,333,222,200,"bizerte","NonValide");
          Livraison l3 = new Livraison(3,444,333,90,"nebeul","NonValide");
         LivraisonService sl = new LivraisonService();
          try {
            sl.ajouterlivraison(l);
            sl.ajouterlivraison(l1);
            sl.ajouterlivraison(l2);
            sl.ajouterlivraison(l3);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
             System.out.println("*****************Aff************************");
            System.out.println(sl.afficherlivraison());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
             System.out.println("****************Modif*************************");
            sl.modifierlivraison(l2);
             System.out.println("modif avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
             System.out.println("*****************Sup************************");
            sl.supprimerlivraison(l);
             System.out.println("supp avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
             System.out.println("*****************TriePrix************************");
            System.out.println(sl.TrierlivraisonPrix());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
             System.out.println("*******************TrieVille**********************");
            System.out.println(sl.TrierlivraisonVille());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
             System.out.println("*******************RechercheIDLivreur**********************");
            
            System.out.println(sl.RechercherlivraisonPrix_ID(333));
              System.out.println("*******************RecherchePrix**********************");
                System.out.println(sl.RechercherlivraisonPrix_ID(90));
              
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        


    }
    
    
}
