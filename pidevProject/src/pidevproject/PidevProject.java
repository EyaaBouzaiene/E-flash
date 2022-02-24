/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevproject;
import entities.Partenaire;
import entities.Stock;
import services.StockService;
import services.PartenaireService;
import static com.sun.org.apache.regexp.internal.RETest.test;
import services.EventService;
import services.ReservationService;
import events.tools.Maconnexion;
import entities.Cathegorie;
import entities.Clients;
import entities.Evenement;
import entities.reservation;
import entities.Produits;
import java.sql.SQLException;
//import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import services.ProduitsService;
import java.sql.Timestamp;
import java.text.DateFormat;
import services.CathegorieService;
import services.ClientsService;
import entities.Livraison;
import entities.User;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import service.CryptWithMD5;
import services.UserService;
import services.LivraisonService;
import services.Mail;
import utils.Translate;
import entities.Commentaire;
import entities.Publication;
import static java.awt.SystemColor.text;
import java.io.File;
import java.text.DateFormat;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.PublicationService;
import java.sql.Date;



import java.util.Scanner;
import services.CommentaireService;
import services.DislikesService;
import services.LikesService;

/**
 *
 * @author aders
 */
public class PidevProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, Exception {
      
         
        Cathegorie c = new Cathegorie("Alimentaire");
        Cathegorie c2 = new Cathegorie("Liquide");
        ProduitsService sp = new ProduitsService();
        CathegorieService cp = new CathegorieService();
        
        System.out.println();
        Produits p = new Produits(12,12,"0","Vitalait","Djeja2","lait", new Date(System.currentTimeMillis()),"1","hmahama");
         Produits p2 = new Produits(13,13,"0","Vitalait2","Djeja1","lait2",  new Date(System.currentTimeMillis()),"4","hmahama");
         Produits p3 = new Produits(13,13,"0","Vitalait8","Djeja8","lait5",  new Date(System.currentTimeMillis()),"4","love me ");
         
         Clients cl=new Clients(2, "Dhia", "Aderssa", "24");
         ClientsService clv=new ClientsService();
         System.err.println("*********************************Ajouter cathegorie *************************************");   
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
//try {
//            sp.ajouterp(p3);
//            System.out.println("ajout avec succes");
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
//        System.err.println("*********************************meilleur produit vendu *************************************");
//        try {
//        
//            System.out.println(sp.afficher_meilleurs_produit());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
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

//try {
//            System.out.println(sp.trieproduit());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } 

//System.err.println("*********************************client*************************************");   
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

Livraison l = new Livraison(1,123,456,20,"zaghouen","NonValide");
         Livraison l1 = new Livraison(2,222,111,150,"Tunis","NonValide");
         Livraison l2 = new Livraison(3,333,222,200,"bizerte","NonValide");
          Livraison l3 = new Livraison(3,444,333,90,"nebeul","NonValide");
         LivraisonService sl = new LivraisonService();
//          try {
//            sl.ajouterlivraison(l);
//            sl.ajouterlivraison(l1);
//            sl.ajouterlivraison(l2);
//            sl.ajouterlivraison(l3);
//            System.out.println("ajout avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//             System.out.println("*****************Aff************************");
//            System.out.println(sl.afficherlivraison());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//         try {
//             System.out.println("****************Modif*************************");
//            sl.modifierlivraison(l1);
//             System.out.println("modif avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//         try {
//             System.out.println("*****************Aff************************");
//            System.out.println(sl.afficherlivraison());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//         try {
//             System.out.println("*****************Sup************************");
//            sl.supprimerlivraison(l);
//             System.out.println("supp avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//         try {
//             System.out.println("*****************TriePrix************************");
//            System.out.println(sl.TrierlivraisonPrix());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//         try {
//             System.out.println("*******************TrieVille**********************");
//            System.out.println(sl.TrierlivraisonVille());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//         try {
//             System.out.println("*******************RechercheIDLivreur**********************");
//            
//            System.out.println(sl.RechercherlivraisonPrix_ID(333));
//              System.out.println("*******************RecherchePrix**********************");
//                System.out.println(sl.RechercherlivraisonPrix_ID(90));
//              
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

//System.err.println("*********************************reservation*************************************"); 
//
// Date d1=Date.valueOf("2022-06-11");
//        Maconnexion mc = Maconnexion.getInstance();
//        Evenement E = new Evenement("ggg", "description", 8, 9,d1 );
//        Evenement e =new Evenement(60, "happy Djeja", "happy hour", 5, 6, d1);
//        EventService ev = new EventService();
//     
//      //ev.ajouterEevenement(e);
////System.out.println(ev.TriS());
//     //ev.update(e);
////      ev.supprimer(e);
////        
//        try {
//            
//            System.out.println(ev.afficherEvenement());
//        } catch (SQLException ex) {
//            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
//        }
//System.err.println("*********************************reservation*************************************");
//       System.out.println(ev.RECHERCHE(27));
//        
////  resrvation
//  reservation  r = new reservation(4, "bbbb5", "prenom", "gmail", 7);
//    reservation  ra = new reservation("iiiiiiiiiii5", "prenom", "gmail", 77);
//        ReservationService rv = new ReservationService();
//        rv.ajouterReservation(r);
//          try {
//            
//            System.out.println(rv.afficherReservation());
//        } catch (SQLException ex) {
//            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//
//  rv.update(r);
//  rv.supprimer(r);
//    }

        System.out.println("**************************user******************");

 User u = new User(1, "mayssa", "bouzid", "admin", 22334455, "ddd.bb@esprit.tn", "22117734");
        User u1 = new User("nour", "hedg", "admin", 22334455, "nour.bouzid@esprit.tn", "bnj");

        User u2 = new User( "hadil", "hhd", "client", 112, "hadil.khemissi@esprit.tn", "lavieestbelle");

        User u3 = new User("eya", "bouzi", "client", 971, "mayssa.bouzid@esprit.tn", "mayssa");

        User u4 = new User( "bnh", "jje", "client", 331, "bnh.bouzid@esprit.tn", "aslemacv");
        User u5 = new User(70, "ccc", "jje", "client", 331, "hadil.kkk@esprit.tn", "hello");

        UserService us = new UserService();
//        String s= CryptWithMD5.cryptWithMD5("mayssa");
//        System.out.println(s);
        
//          Base64.Encoder enc = Base64.getEncoder();
//        String a = u.getPassword();
//        String b = enc.encodeToString(a.getBytes());
//        System.out.println(b);
//  try {
//          //  us.ajouter_user(u);
//           us.ajouter_user(u2);
//           us.ajouter_user(u1);
//
//              System.out.println("ajout avec succes");
//        } catch (SQLException ex) {
//            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        try {
//            System.out.println(us.afficher_user());
//        } catch (SQLException ex) {
//            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            
//
////            us.modifier_user(u2);
////            us.modifier_user(u4);
//            us.modifier_user(u5);
//
//            System.out.println("modifier avec succes");
//        } catch (SQLException ex) {
//            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
//        }

        /*try {
            System.out.println(us.afficher_user());
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }*/

//      try {
//
////            us.supprimer_user(u2);
////            us.supprimer_user(u4);
//            us.supprimer_user(u5);
//
//            System.out.println("delete avec succes");
//        } catch (SQLException ex) {
//            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
//        }

       /* try {
            System.out.println(us.afficher_user());
        } catch (SQLException ex) {
            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(us.TrierUser(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
        }*/
//        try {
//            System.out.println(us.recherch_User("mayssa"));
//        } catch (SQLException ex) {
//            Logger.getLogger(PidevProject.class.getName()).log(Level.SEVERE, null, ex);
//        }

System.out.println("**************************Khadijaaaaaaa******************");
Stock S= new Stock("48", "equipement", 123, "equipement", 5, new Date(System.currentTimeMillis()),8);
           Stock S2 = new Stock("48","Equipement", 123, "loc",4, new Date(System.currentTimeMillis()),5);
        StockService s = new StockService();
        Partenaire P = new Partenaire("m1", "p1", "Alimentaire","abdelkader.bouali@gmail.com", new Date(System.currentTimeMillis()),5);
        PartenaireService p1 = new PartenaireService();
        PartenaireService p4=new PartenaireService();
      
         
       
        
      System.err.println("*********************************Stocks*********************************");
      
      
//        try {
//              System.err.println("************Ajout Stocks************");
//            s.ajouterS(S);
//            System.out.println("ajout avec succes");     
//        } 
//        catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//       }
        
        
        
        
//        
//        System.err.println("************Affichage Stocks************");
//       try{
//       System.out.println( s.afficherS());
//}
//       catch(SQLException ex){
//           System.out.println(ex.getMessage());
//       }
       
         
        
//        System.err.println("************Modification Stocks************");
//         try{
//       s.modifierS();
//         System.out.println("Modification éffectué");
//           
//}
//         
//       catch(SQLException ex){
//           System.out.println(ex.getMessage());
//       }
//      
//        
//         System.err.println("************Affichage Stocks************");
//       try{
//       System.out.println(s.afficherS());
//}
//       catch(SQLException ex){
//           System.out.println(ex.getMessage());
//       }
       
       
       
//           System.out.println("************Suppression Stocks************");
//        try {
//            System.out.println("Suppression réussie");
//            s.supprimerS();
//            
//             
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//         System.err.println("************Affichage Stocks************");
//       try{
//       System.out.println(s.afficherS());
//}
//       catch(SQLException ex){
//           System.out.println(ex.getMessage());
//       }
       
        
        
        
        
        
//         System.err.println("************Recherche Stocks************");
//       try{
//       System.out.println(s.rechercheS());
//}
//       catch(SQLException ex){
//           System.out.println(ex.getMessage());
//       }
//      
        
        
//       System.err.println("************Trie Stocks************");
//       try{
//       System.out.println(s.trie());
//}
//       catch(SQLException ex){
//           System.out.println(ex.getMessage());
//       }
        
        
       
       
            
            
            System.err.println("*********************************Partenaire*********************************");
            
//            try {
//            p.ajouterP(P);
//            System.out.println("ajout partenaire avec succes");
//            } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            
//            }
//            
//            try {
//            
//            System.out.println(p.afficherP());
//            
//            } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            
//            }
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
            
           // ******************************************************************************************
            
//            System.err.println("************Meilleur(s) Partenaire(s)************");
//            try{
//            System.out.println( p1.MeilleursP());
//            }
//            catch(SQLException ex){
//            System.out.println(ex.getMessage());
//            }
            
            
            
            
          
//            //from, password, to, subject, message
//            Mail.send(
//            "khanfirkhadija66@gmail.com",
//            "bebe@@##123,",
//            "khanfirkhadija66@gmail.com",
//            "Bienvenu ",
//            "mail de test!"
//            );
           
            
           /*
  System.err.println("************Traitement************");
            try{
            s.Traitement();
                System.out.println("Mail envoyé avec Succes");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
*/

        System.out.println("*****************************omar***************************");    
        
          Scanner scan = new Scanner(System.in);
        
        
//        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        System.out.println(format.format(date));
        
          String fromLang = "fr";
          String toLang = "es";
          String text = "Dimanche";
          
          
          
         
       
        
          System.out.println("----------------------Partie Publication-------------------------");
          
//        Publication plb = new Publication("Dimanche","bonjour le plat est tre bons","/c/projet.jpg",format.format(date),3);
//        Publication p1b1 = new Publication("r","bonjour le plat est tre bons","/c/projet.jpg",format.format(date),1);
        
        
        PublicationService spb = new PublicationService();
        
//      try {
//            spb.ajouter_publication(plb);
//            System.out.println("ajout avec succes");
//        }catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//            System.out.println(spb.afficher_publication());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
            
        
//       try {
//             System.out.println("*****************************************");
//     
//            spb.modifier_publication(Translate.translate(fromLang, toLang, text),83);
//             System.out.println("modif avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
        
//        try {
//             System.out.println("*****************************************");
//            spb.supprimer_publication(plb);
//             System.out.println("supp avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//                
//         try {
//            System.out.println(spb.rechercherP(3));
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//          try {
//            System.out.println(spb.triP());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
        
        
        
       
//        String msgs;      
//        System.out.println("--------------------Partie Commentaire---------------------");
//        
//        Scanner scan_commentaire = new Scanner(System.in);
//        System.out.println("Entrez votre mot");
//        File fichier = new File("C:\\Users\\aders\\Documents\\NetBeansProjects\\pidevProject\\src\\utils\\mot.txt.txt");
//        Scanner scanf = new Scanner(fichier);
//        
//        msgs = scan_commentaire.next();
//        Commentaire cm = new Commentaire(msgs,format.format(date),1,5);
//        Commentaire c1 = new Commentaire("cvvvv",format.format(date),1,4);
//        CommentaireService sc = new CommentaireService();
//        
//        int i=0;
//          while(scanf.hasNext() && i==0){
//           String mot = scanf.next();
//           if(mot.equals(msgs))
//           {
//               i=1;
//               System.out.println("vous devez enlever le mot :"+msgs);
//               msgs="0";
//           }
//         
//           
//       }
//        
//           
//           
//        
//       
//       if(i!=1)
//       {     
//       try {
//            sc.ajouter_commentaire(cm);
//            System.out.println("ajout avec succes");
//        }catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//       }
//       
//        
//        try {
//            System.out.println(sc.afficher_commentaire());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//            
        
       /*try {
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
        }*/
        
        
       
//       System.out.println("----------------------Partie Like-------------------------");
//       LikesService s1 = new LikesService();
//        
//      int id_client=2;
//      int id_publication=1;
//       
//        try {
//            s1.ajouter_like(id_publication,id_client);
//            System.out.println("ajout avec succes");
//        }catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//       
//        s1.afficher_nombre_like(1);
        
        
//        System.out.println("-----------------------------------------------------------------");
//        
//       System.out.println("----------------------Partie Dislike-------------------------");
//        
//        DislikesService sdl = new DislikesService();
//        
//        
//      try {
//            sdl.ajouter_dislike(id_publication,id_client);
//            System.out.println("ajout avec succes");
//        }catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        
//        sdl.afficher_nombre_dislike(1);
//        
       


  }
    
}
