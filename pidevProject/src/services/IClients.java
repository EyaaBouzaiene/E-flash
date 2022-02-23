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
public interface IClients <T> {
    
    public void ajouterC(T p) throws SQLException;
    public List<T> afficherclient() throws SQLException;
    public void supprimerC() throws SQLException;
    public void modifierC() throws SQLException;
    public List<T> recherche() throws SQLException;
    public void ajouterrate() throws SQLException;
    
    
}
