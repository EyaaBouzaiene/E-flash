/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Livraison;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author 21627
 */
public class LivraisonService implements ILivraison<Livraison> {
    Connection connexion;
    Statement stm;

    public LivraisonService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    @Override
    public void ajouterlivraison(Livraison l) throws SQLException {
         String req = "INSERT INTO `livraison` (`idCommande`, `idLivreur`,`prix`, `ville`, `etat`) VALUES ( '"
                + l.getIdCommande()+ "', '" + l.getIdLivreur()+ "', '" + l.getPrix()+ "', '" + l.getVille()+ "', '" + l.getEtat()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Livraison> afficherlivraison() throws SQLException {
        List<Livraison> livraisons = new ArrayList<>();
        String req = "select * from livraison";
        stm = connexion.createStatement();
        //ensemble de resultat
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
    public void modifierlivraison(Livraison l) throws SQLException {
         int id =28;
         String req = "UPDATE livraison set idCommande='" + l.getIdCommande()+ "',idLivreur='" + l.getIdLivreur()+ "',prix='" + l.getPrix()+ "',ville='" + l.getVille()+ "',etat='" + l.getEtat()+ "' where `idLivraison`='"+ id+ "' ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
       
         
    }

    @Override
    public void supprimerlivraison(Livraison l) throws SQLException {
         int id =28;
        String req = "delete from livraison where `idLivraison`='"+ id+ "' ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
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
    public List<Livraison> TrierlivraisonPrix() throws SQLException {
          stm = connexion.createStatement();
           String req = "select * from livraison order by prix ";
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
    public List<Livraison> RechercherlivraisonPrix_ID(int R) throws SQLException {
        stm = connexion.createStatement();
           String req = "select * from livraison where `idLivraison`='"+ R+ "' OR `idLivreur`='"+ R+ "' OR `prix`='"+ R+ "' ";
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

   
    
}
