/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudcom;

import entities.Commande;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CommandeService;

/**
 *
 * @author Mayssa
 */
public class CrudCom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Commande c = new Commande("mayssa", 22334455, "22-03-2002");
        Commande c1 = new Commande(2,"hadil", 332, "22-03-2002");

        Commande c2 = new Commande(4,"eya", 112, "22-03-2002");
        Commande c3 = new Commande(6,"nour", 112, "22-03-2002");
        Commande c4 = new Commande(7,"coc", 112, "22-03-2002");


        CommandeService cs = new CommandeService();
        
        try {
            cs.ajouter_Commande(c);
               System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(CrudCom.class.getName()).log(Level.SEVERE, null, ex);
        }

     

      

        try {
            cs.modifier_Commande(c1);
            cs.modifier_Commande(c3);

             System.out.println("modifier avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(CrudCom.class.getName()).log(Level.SEVERE, null, ex);
        }

           
         
        try {
            System.out.println(cs.afficher_Commande());
        } catch (SQLException ex) {
            Logger.getLogger(CrudCom.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
           /* cs.supprimer_Commande(c1);
             cs.supprimer_Commande(c2);*/
             cs.supprimer_Commande(c3);
             cs.supprimer_Commande(c4);

            System.out.println("delete avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(CrudCom.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println(cs.afficher_Commande());
        } catch (SQLException ex) {
            Logger.getLogger(CrudCom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
