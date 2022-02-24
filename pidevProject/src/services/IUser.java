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
 * @author Mayssa
 */
public interface IUser<T> {
     public void ajouter_user(T u) throws SQLException;
    public List<T> afficher_user() throws SQLException;
    public void modifier_user(T u) throws SQLException;
    public void supprimer_user(T u) throws SQLException;
     public List<T> TrierUser(int i) throws SQLException;
     public List<T> recherch_User(String c) throws SQLException; 
     public String encrypt(String password);
     public String decrypt(String password);
    
}
