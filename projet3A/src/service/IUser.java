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

    public List<String> afficher_email() throws SQLException;

    public List<String> afficher_mobile() throws SQLException;

    public void SignIn(String mail, String password) throws SQLException;

    public void SignInWithCode(String mail, String code) throws SQLException;
    
         public void SignOut() throws SQLException ;
         
       public T afficher_user_byId(int id) throws SQLException ;
       
        public T afficher_user_byEmail(String e) throws SQLException;
}
