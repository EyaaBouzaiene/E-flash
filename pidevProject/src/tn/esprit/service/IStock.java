/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import tn.esprit.model.Stock;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Khadija
 */
public interface IStock<T> {

    public void ajouterS(T s) throws SQLException;

    public List<T> afficherS() throws SQLException;

    public void modifierS() throws SQLException;

    public void supprimerS() throws SQLException;
    
   public List<T> rechercheS() throws SQLException;
   
   public List<Stock> trie() throws SQLException;
   
   public void TraitementF () throws SQLException;
   
   public void TraitementS() throws SQLException;

}
