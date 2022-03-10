package tn.esprit.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mayssa
 */
public interface ICom<T> {
      public void ajouter_Commande(T c) throws SQLException;
    public List<T> afficher_Commande() throws SQLException;
    public void modifier_Commande(T c) throws SQLException;
    public void supprimer_Commande(T c) throws SQLException;
    
    
}
