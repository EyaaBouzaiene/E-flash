/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author EXTRA
 */
public interface IPublication<T> {
    
    public void ajouter_publication(T p) throws SQLException;
    public ObservableList<T> afficher_publication() throws SQLException;
    public void modifier_publication(String t,int id) throws SQLException;
    public void supprimer_publication(T l) throws SQLException;
    public ObservableList<T> rechercherP(int id_client_publication) throws SQLException;
    public List<T> triP() throws SQLException;
    
}