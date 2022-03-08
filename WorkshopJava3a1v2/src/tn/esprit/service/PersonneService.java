/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.model.Personne;
import tn.esprit.utils.MyDB;

/**
 *
 * @author macbook
 */
public class PersonneService implements IService<Personne> {

    Connection connexion;
    Statement stm;
    ResultSet rs;
    
    public PersonneService() {
        connexion = MyDB.getInstance().getConnexion();
        try {
            stm = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterp(Personne p) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) VALUES ( '"
                + p.getNom() + "', '" + p.getPrenom() + "') ";
        
        stm.executeUpdate(req);

    }

    @Override
    public List<Personne> afficherpersonne() throws SQLException {
        List<Personne> presonnes = new ArrayList<>();
        String req = "select * from personne";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Personne p = new Personne(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"));
            presonnes.add(p);
        }
        return presonnes;
    }
    
}
