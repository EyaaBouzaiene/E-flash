/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 21627
 */

public interface ILivraison<T> {
    public void ajouterlivraison(T l) throws SQLException;
    public List<T> afficherlivraison() throws SQLException;
    public void modifierlivraison(T l,int v, int s) throws SQLException;
    public void supprimerlivraison(int i) throws SQLException;
     public List<T> Trierlivraison(int i) throws SQLException;
    public List<T> TrierlivraisonVille() throws SQLException;
    public List<T> TrierlivraisonPrix() throws SQLException;
    public List<T> RechercherlivraisonPrix_ID(int R) throws SQLException;
    public List<T> getcmd() throws SQLException;
     public List<T> Afficher2(int R1,int R2) throws SQLException;
    // public List<T> getcommande() throws SQLException;
     
    
    
        
    
}
