/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.JDBC;

/**
 *
 * @author Mayssa
 */
public class UserService implements IUser<User> {

    Connection connexion;
    Statement stm;
    PreparedStatement ps;

    public UserService() {
        connexion = JDBC.getInstance().getConnexion();
    }

    @Override
    public void ajouter_user(User u) throws SQLException {
        /*   String req1 = "select id from user";
          stm = connexion.createStatement();

        ResultSet rst = stm.executeQuery(req1);
        
        int id=rst.getInt("id");
           String req = "INSERT INTO `user` (`id`,`nom`, `prenom`,`email`,`password`,`role`,`telf`) values (?,?,?,?,?,?,?)";

        try {
            ps = connexion.prepareStatement(req);
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(6, u.getRole());
            ps.setInt(7, u.getTel());
            ps.setString(4, u.getEmail());
            ps.setInt(5, u.getPassword());
               ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

         */

        String req = "INSERT INTO `user` (`nom`, `prenom`,`email`,`password`,`role`,`telf`) VALUES ( '"
                + u.getNom() + "', '" + u.getPrenom() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getRole() + "','" + u.getTel() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<User> afficher_user() throws SQLException {
        List<User> user = new ArrayList<>();
        String req = "select * from user";
        stm = connexion.createStatement();

        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User u = new User(rst.getInt("id"),
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getString("role"),
                    rst.getInt("telf"),
                    rst.getString("email"),
                    rst.getString("password"));
            user.add(u);
        }
        return user;
    }

    @Override
    public void modifier_user(User u) throws SQLException {
        String req = "update user set nom = ? , prenom = ? , role= ? ,telf= ? ,email= ? ,password= ?  where id = ?";

        try {
            ps = connexion.prepareStatement(req);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getRole());
            ps.setInt(4, u.getTel());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());
            ps.setInt(7, u.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer_user(User u) throws SQLException {

        String req = "delete from `user` where `id`='" + u.getId() + "'";

        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public List<User> TrierUser(int i) throws SQLException {
        Statement stm = connexion.createStatement();
        String query = "";
        if (i == 1) {
            query = "select * from `user` ORDER BY nom ASC";
        } else if (i == 2) {
            query = "select * from `action` ORDER BY role ASC";
        }

        ResultSet rst = stm.executeQuery(query);
        List<User> user = new ArrayList<>();
        while (rst.next()) {

            User a2 = new User(
                    rst.getInt("id"),
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getString("role"),
                    rst.getInt("telf"),
                    rst.getString("email"),
                    rst.getString("password"));

            user.add(a2);
        }
        return user;
    }

    @Override
    public List<User> recherch_User(String c) throws SQLException {
        Statement stm = connexion.createStatement();
        String req = "select * from user where nom  LIKE '%" + c + "%'";
        ResultSet rst = stm.executeQuery(req);
        List<User> user = new ArrayList<>();
        while (rst.next()) {
            User a2 = new User(
                    rst.getInt("id"),
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getString("role"),
                    rst.getInt("telf"),
                    rst.getString("email"),
                    rst.getString("password"));

            user.add(a2);
        }
        return user;
    }

    @Override
       public String encrypt(String password){
        String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            crypte=crypte+(char)c;
        }
        return crypte;
    }

    @Override
    public String decrypt(String password){
        String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            aCrypter=aCrypter+(char)c;
        }
        return aCrypter;
    }


    

}
