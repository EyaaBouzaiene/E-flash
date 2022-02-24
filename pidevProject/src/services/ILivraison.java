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
 * @author 21627
 */
public interface ILivraison<T> {
    public void ajouterlivraison(T l) throws SQLException;
    public List<T> afficherlivraison() throws SQLException;
    public void modifierlivraison(T l) throws SQLException;
    public void supprimerlivraison(T l) throws SQLException;
    public List<T> TrierlivraisonVille() throws SQLException;
    public List<T> TrierlivraisonPrix() throws SQLException;
    public List<T> RechercherlivraisonPrix_ID(int R) throws SQLException;
    
    
        
    
}
