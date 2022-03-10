/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;


import com.gluonhq.impl.charm.a.b.b.a;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.model.Clients;
import tn.esprit.model.Produits;
import tn.esprit.utils.MyDB;


/**
 *
 * @author aders
 */
public class ClientsService implements IClients<Clients> {

    Connection connexion;
    Statement stm;

    public ClientsService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    
    @Override
    public void ajouterC(Clients p) throws SQLException {
        
//        List<Produits> produit = new ArrayList<>();
//        
//        
//    String req = "INSERT INTO `clients`(`NOM`,`Prenom`, `QTA`, `IDP`,`DATEA`) VALUES ('" + p.getNom() + "','" + p.getPrenom()+ "','" + p.getQuantiteA()+ "','" + p.getIDP()+ "','"  + p.getDateA() + "') ";   
//        stm = connexion.createStatement();            
//        stm.executeUpdate(req); 
//       String req2=" select produits.quantiteR,clients.QTA from produits,clients where clients.IDP=produits.REFERENCE";
//        stm = connexion.createStatement();
//        //ensemble de resultat
//        ResultSet rst = stm.executeQuery(req2);
//       if (rst.next())
//       {
//           
//       
//        if(rst.getInt("quantiteR")>rst.getInt("QTA"))
//        {
//             String req3="UPDATE `produits` INNER JOIN  `clients` ON  produits.REFERENCE=clients.IDP  SET quantiteR=`quantiteR`-`QTA`";
//        stm = connexion.createStatement();
//        stm.executeUpdate(req3);
//        }
//        else if (rst.getInt("quantiteR")<rst.getInt("QTA"))
//        {
//       
//        String req5="UPDATE `clients` INNER JOIN  `produits` ON  produits.REFERENCE=clients.IDP  SET clients.QTA=produits.quantiteR";
//        stm = connexion.createStatement();
//        stm.executeUpdate(req5);
//        System.err.println("La quantite Restante est INSUFFISANTE ");
//         String req4="UPDATE `produits` INNER JOIN  `clients` ON  produits.REFERENCE=clients.IDP  SET quantiteR= produits.quantite ";
//        stm = connexion.createStatement();
//        stm.executeUpdate(req4);
//        
//         
//        } 
//        else {
//             
//           String req3="UPDATE `produits` INNER JOIN  `clients` ON  produits.REFERENCE=clients.IDP  SET quantiteR=`quantiteR`-`QTA`";
//        stm = connexion.createStatement();
//        stm.executeUpdate(req3); 
//        System.err.println("La quantite Restante est " +rst.getInt("quantiteR"));
//         String req4="UPDATE `produits` INNER JOIN  `clients` ON  produits.REFERENCE=clients.IDP  SET quantiteR=`quantite`";
//        stm = connexion.createStatement();
//        stm.executeUpdate(req4); 
//          
//        
//           
//        }
//       }
            
        
        }
        
            
        
         
        
        
       
        
    
        
        
            /*Mail.send(
            "khanfirkhadija66@gmail.com",
            "bebe@@##123,",
             "flashelectro06@gmail.com",
            "CONGRATULATION !!!!! ",
            "sorry you are now elected as VIP CLIENT"
            ); */
        
        

    

//    @Override
//    public List<Clients> afficherclient() throws SQLException {
////        List<Clients> client = new ArrayList<>();
////         
//////        String req = "SELECT * FROM `clients`";
//////        stm = connexion.createStatement();
//////        //ensemble de resultat
//////        ResultSet rst = stm.executeQuery(req);
//////
//////        while (rst.next()) {
//////            Clients s;
//////            s = new Clients (rst.getInt("REFC"),
//////                    rst.getInt("QTA"),
//////                    rst.getString("NOM"),
//////                    rst.getString("Prenom"),  
//////                    rst.getString("IDP"),
//////                    rst.getDate("DATEA")
//////                   
//////            );
//////            client.add(s);
//////        }
//////  
//  return a;
//    }

    @Override
    public void supprimerC() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierC() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Clients> recherche() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterrate() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public   void afficher_clientfidele() throws SQLException {
        
    
         
        String req = " SELECT * FROM `clients`,`commande` GROUP BY  commande.IDClient  HAVING COUNT(commande.IDClient)>3   AND TIMESTAMPDIFF(month,MIN(commande.date),MAX(commande.date))>=2";
        
        

        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        System.out.println("hello");
        while (rst.next()) {
            
           Mail.send(
            "khanfirkhadija66@gmail.com",
            "bebe@@##123,",
             "flashelectro06@gmail.com",
            "CONGRATULATION !!!!! ",
            "Congratulation "+rst.getString("NOM")+" you are now elected as VIP CLIENT"
            );
            
        }
        
        
    }
    }

  
  
    

