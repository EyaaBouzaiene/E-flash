/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Partenaire;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Khadija
 */
public interface IPartenaires<T> {

   public void ajouterP(T s) throws SQLException;

   public List<T> afficherP() throws SQLException;

    public void modifierP() throws SQLException;

    public void supprimerP() throws SQLException;
    
    public List<Partenaire> MeilleursP() throws SQLException ;
    
    
}
