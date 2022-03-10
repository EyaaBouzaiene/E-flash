/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entity.Livraison;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.JDBC;

/**
 *
 * @author 21627
 */
public class LivraisonService implements ILivraison<Livraison> {
    Connection connexion;
    Statement stm;

    public LivraisonService() {
        connexion = JDBC.getInstance().getConnexion();
    }
    @Override
    public void ajouterlivraison(Livraison l) throws SQLException {
        
         String req = "INSERT INTO `livraison` (`idCommande`, `idLivreur`,`prix`, `ville`, `etat`) VALUES ( '"
                + l.getIdCommande()+ "', '" + l.getIdLivreur()+ "', '" + l.getPrix()+ "', '" + l.getVille()+ "', 'en Cours') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public ObservableList<Livraison> afficherlivraison() throws SQLException {
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
        String req = "select l.idLivraison,l.idCommande,l.idLivreur,l.prix,l.ville,l.etat,u.email from livraison l , user u where l.idLivreur=u.id ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Livraison l = new Livraison(rst.getInt("idLivraison"),//or rst.getInt(1)
                    
                    rst.getInt("idCommande"),
                    rst.getInt("idLivreur"),
                     
                    rst.getInt("prix"),
                    rst.getString("ville"),
                    rst.getString("etat"),
                    rst.getString("email")
            );
            livraisons.add(l);
        }
        return livraisons;
    }

    /*@Override
    public void modifierlivraison(Livraison l) throws SQLException {
         int id =9;
         String req = "UPDATE livraison set idCommande='" + l.getIdCommande()+ "',idLivreur='" + l.getIdLivreur()+ "',prix='" + l.getPrix()+ "',ville='" + l.getVille()+ "',etat='" + l.getEtat()+ "' where `idLivraison`='"+ id+ "' ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
       
         
    }*/
    @Override
    public void modifierlivraison(Livraison l,int v,int s) throws SQLException {
        
         String req = "UPDATE livraison set idLivreur='" + l.getIdLivreur()+ "',ville='" + l.getVille()+ "',etat='" + l.getEtat()+ "' where `idLivraison`="+v+"   ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
       
         
    }

    @Override
    public void supprimerlivraison(int i) throws SQLException {
         //int id =9;
        String req = "delete from livraison where  `idLivraison`='"+i+"'";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        //stm.setInt(3, i);
    }

    @Override
    public List<Livraison> TrierlivraisonVille() throws SQLException {
           stm = connexion.createStatement();
           String req = "select * from livraison order by ville ";
        List<Livraison> livraisons = new ArrayList<>();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Livraison l = new Livraison(rst.getInt("idLivraison"),//or rst.getInt(1)
                    rst.getInt("idCommande"),
                    rst.getInt("idLivreur"),
                    
                    rst.getInt("prix"),
                    rst.getString("ville"),
                    rst.getString("etat")
            );
            livraisons.add(l);
        }
        return livraisons;
        
    }

    @Override
    public ObservableList<Livraison> TrierlivraisonPrix() throws SQLException {
          stm = connexion.createStatement();
           String req = "select * from livraison order by prix ";
       ObservableList<Livraison> livraisons =FXCollections.observableArrayList();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Livraison l = new Livraison(rst.getInt("idLivraison"),//or rst.getInt(1)
                    rst.getInt("idCommande"),
                    rst.getInt("idLivreur"),
                    
                    rst.getInt("prix"),
                    rst.getString("ville"),
                    rst.getString("etat")
            );
            livraisons.add(l);
        }
        return livraisons;
    }

    @Override
    public   ObservableList<Livraison> RechercherlivraisonPrix_ID(int R) throws SQLException {
        stm = connexion.createStatement();
           String req = "select * from livraison where `idLivraison`='"+ R+ "' OR `idLivreur`='"+ R+ "' OR `prix`='"+ R+ "' ";
         ObservableList<Livraison> livraisons =FXCollections.observableArrayList();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Livraison l = new Livraison(rst.getInt("idLivraison"),//or rst.getInt(1)
                    rst.getInt("idCommande"),
                    rst.getInt("idLivreur"),
                    
                    rst.getInt("prix"),
                    rst.getString("ville"),
                    rst.getString("etat")
            );
            livraisons.add(l);
        }
        return livraisons;
    }

    @Override
    public List<Livraison> getcmd() throws SQLException {
        String req = "select idCom from commande";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        return null;
        
    }

    @Override
    public ObservableList<Livraison> Trierlivraison(int i) throws SQLException {
         stm = connexion.createStatement();
        String query = "";
        if (i==1) {
          query = "select * from `livraison` ORDER BY prix ASC";  
        }else if (i==2) {
           query = "select * from `livraison` ORDER BY ville ASC"; 
        }

        ResultSet rst = stm.executeQuery(query);
         ObservableList<Livraison> livraisons =FXCollections.observableArrayList();
        while (rst.next()) {
            
         Livraison l = new Livraison(rst.getInt("idLivraison"),//or rst.getInt(1)
                    rst.getInt("idCommande"),
                    rst.getInt("idLivreur"),
                 
                    rst.getInt("prix"),
                    rst.getString("ville"),
                    rst.getString("etat"));
            livraisons.add(l);
        }
     return livraisons;
    }

    @Override
    public List<Livraison> Afficher2(int R1, int R2) throws SQLException {
        stm = connexion.createStatement();
           String req = "select ref_cmd,email from user where `ref_cmd`='"+ R1+ "' AND`email`='"+ R2+ "'  ";
         ObservableList<Livraison> livraisons =FXCollections.observableArrayList();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Livraison l = new Livraison(rst.getInt("idLivraison"),//or rst.getInt(1)
                    rst.getInt("idCommande"),
                    rst.getInt("idLivreur"),
                    
                    rst.getInt("prix"),
                    rst.getString("ville"),
                    rst.getString("etat")
            );
            livraisons.add(l);
        }
        return livraisons;
        
    }

    /*@Override
    public List<int> getcommande() throws SQLException {
        List <int> lc = new ArrayList<>();
        String req = "select idCom from commande";
        stm = connexion.createStatement();
        
        ResultSet rst = stm.executeQuery(req);
        while(rst.next())
        {
            int cmd = rst.getInt("idCom");
            lc.add(cmd);
        }
        return lc;
    }*/
   
        
    

   
    
}
