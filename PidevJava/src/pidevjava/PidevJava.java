/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import utils.Translate;
import entities.Commentaire;
import entities.Publication;
import java.io.File;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.PublicationService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import services.CommentaireService;
import services.DislikesService;
import services.LikesService;

/**
 *
 * @author macbook
 */
public class PidevJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        Scanner scan = new Scanner(System.in);
        
        
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(format.format(date));
        
          String fromLang = "fr";
          String toLang = "es";
          String text = "Dimanche";
          
          
          
          

        
        
        Publication p = new Publication(Translate.translate(fromLang, toLang, text),"bonjour le plat est tre bons","/c/projet.jpg",format.format(date),1);
        Publication p1 = new Publication("r","bonjour le plat est tre bons","/c/projet.jpg",format.format(date),1);
        
        
        PublicationService sp = new PublicationService();
        
      /* try {
            sp.ajouter_publication(p);
            System.out.println("ajout avec succes");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            System.out.println(sp.afficher_publication());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
        
       try {
             System.out.println("*****************************************");
     
            sp.modifier_publication(p1,3);
             System.out.println("modif avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
             System.out.println("*****************************************");
            sp.supprimer_publication(p);
             System.out.println("supp avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        */
       
        String msgs;      
        System.out.println("-------Partie Commentaire----------");
        
        Scanner scan_commentaire = new Scanner(System.in);
        System.out.println("Entrez votre mot");
        File fichier = new File("C:\\Users\\EXTRA\\Desktop\\PidevJava\\src\\utils\\mot.txt.txt");
        Scanner scanf = new Scanner(fichier);
        msgs = scan_commentaire.next();
          while(scanf.hasNext()){
           String mot = scanf.next();
           if(mot.equals(msgs))
           {
               System.out.println("vous devez enlever le mot :"+msgs);
           }
         
           
       }
        
        /*   Commentaire c = new Commentaire(msgs,format.format(date),1,5);
           Commentaire c1 = new Commentaire("cvvvv",format.format(date),1,4);
           
        
        CommentaireService sc = new CommentaireService();
        
       try {
            sc.ajouter_commentaire(c);
            System.out.println("ajout avec succes");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            System.out.println(sc.afficher_commentaire());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
        
       try {
             System.out.println("*****************************************");
            sc.modifier_commentaire(c1,3);
             System.out.println("modif avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
             System.out.println("*****************************************");
            sc.supprimer_commentaire(c);
             System.out.println("supp avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        sp.rechercherP(3);
                
        LikesService sl = new LikesService();
        
       int id_client=2;
       int id_publication=1;*/
       
        /*try {
            sl.ajouter_like(id_publication,id_client);
            System.out.println("ajout avec succes");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       
        sl.afficher_nombre_like(0);
        
        
        System.out.println("-----------------------------------------------------------------");
        
        DislikesService sdl = new DislikesService();
        
        
        try {
            sdl.ajouter_dislike(id_publication,id_client);
            System.out.println("ajout avec succes");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        sdl.afficher_nombre_dislike(1);*/
    }
    
    
    
    
}

 
