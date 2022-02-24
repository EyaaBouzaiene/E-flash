/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aders
 */
public interface IProduits <T> {
     public void ajouterp(T p) throws SQLException;
    public List<T> afficherproduit() throws SQLException;
    public void supprimerP() throws SQLException;
    public void modifierP() throws SQLException;
     public List<T> recherche() throws SQLException;
     public void ajouterrate() throws SQLException;
    public List<T> afficher_meilleurs_produit() throws SQLException;
     public List<T> trieproduit() throws SQLException;
}
