/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Clients;
import entities.Produits;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyDB;

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
    String req = "INSERT INTO `clients`(`NOM`,`Prenom`, `QTA`, `IDP`) VALUES ('" + p.getNom() + "','" + p.getPrenom()+ "','" + p.getQuantiteA()+ "','" + p.getIDP()+ "')";
        stm = connexion.createStatement();
        stm.executeUpdate(req); 
        String req2="UPDATE `produits` INNER JOIN  `clients` ON  produits.REFERENCE=clients.IDP  SET quantiteR=`quantiteR`-`QTA`";
        stm = connexion.createStatement();
        stm.executeUpdate(req2); 

    }

    @Override
    public List<Clients> afficherclient() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
    
}
