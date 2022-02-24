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
 * @author EXTRA
 */
public interface ICommentaire<T> {
    
    
    public void ajouter_commentaire(T p) throws SQLException;
    public List<T> afficher_commentaire() throws SQLException;
    public void modifier_commentaire(T l,int id) throws SQLException;
    public void supprimer_commentaire(T l) throws SQLException;
    

    
}
