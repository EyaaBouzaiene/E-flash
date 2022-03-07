/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aders
 * @param <T>
 */
public interface ICathegorie <T> {
     public void ajouterc(T c) throws SQLException;
    public List<T> affichercathegorie() throws SQLException;

    public void afficher_nom_cathegorie(int id) throws SQLException;
    public void supprimerC() throws SQLException;
    public void modifierC() throws SQLException;
    public void afficher_nom_cathegorie() throws SQLException;
    
    
}
