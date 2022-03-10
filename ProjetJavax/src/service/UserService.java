/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;
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
    public static User user = new User();

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

        String req = "INSERT INTO `user` (`id`,`nom`, `prenom`,`email`,`password`,`role`,`telf`,`code`,`date`) " + "VALUES ( NULL,'"
                + u.getNom() + "', '" + u.getPrenom() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getRole() + "','" + u.getTel() + "',NULL,NULL);";
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
                    rst.getString("password"),
                    rst.getDate("date"));
            user.add(u);
        }
        return user;
    }

    @Override
    public User afficher_user_byId(int id) throws SQLException {

        String req = "select * from user where `id`='" + id + "'";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        User u = new User();

        while (rst.next()) {
            u.setId(rst.getInt("id"));
            u.setNom(rst.getString("nom"));
            u.setPrenom(rst.getString("prenom"));
            //  u.setRole(rst.getString("role"));
            u.setTel(rst.getInt("telf"));
            u.setEmail(rst.getString("email"));
            u.setPassword(rst.getString("password"));

        }
        return u;
    }

    @Override
    public User afficher_user_byEmail(String e) throws SQLException {

        String req = "select * from user where `email`='" + e + "'";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        User u = new User();

        while (rst.next()) {
            u.setId(rst.getInt("id"));
            u.setNom(rst.getString("nom"));
            u.setPrenom(rst.getString("prenom"));
            //  u.setRole(rst.getString("role"));
            u.setTel(rst.getInt("telf"));
            u.setEmail(rst.getString("email"));
            u.setPassword(rst.getString("password"));

        }
        return u;
    }

    @Override
    public void modifier_user(User u) throws SQLException {
        /* String req = "update user set nom = ? , prenom = ?  ,telf= ? ,email= ? ,password= ?  where id = ?";

        try {
            ps = connexion.prepareStatement(req);
            ps.setString(1, ));
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getRole());
            ps.setInt(4, u.getTel());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());
            ps.setInt(7, u.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        String req = "update user set nom = '" + u.getNom() + "' , prenom = '" + u.getPrenom() + "'  ,telf= '" + u.getTel() + "' ,email= '" + u.getEmail() + "' ,password= '" + u.getPassword() + "'  where id = '" + u.getId() + "'";

        stm = connexion.createStatement();
        stm.executeUpdate(req);
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
    public String encrypt(String password) {
        String crypte = "";
        for (int i = 0; i < password.length(); i++) {
            int c = password.charAt(i) ^ 48;
            crypte = crypte + (char) c;
        }
        return crypte;
    }

    @Override
    public String decrypt(String password) {
        String aCrypter = "";
        for (int i = 0; i < password.length(); i++) {
            int c = password.charAt(i) ^ 48;
            aCrypter = aCrypter + (char) c;
        }
        return aCrypter;
    }

    @Override
    public void SignIn(String mail, String password) throws SQLException {
        stm = connexion.createStatement();
        String pass = encrypt(password);
        ResultSet rst = stm.executeQuery("SELECT * FROM user WHERE  email = '" + mail + "' and password = '" + pass + "'");
        User user = new User();
        if (rst != null) {
            while (rst.next()) {

                user.setId(rst.getInt("id"));
                user.setNom(rst.getString("nom"));
                user.setPrenom(rst.getString("prenom"));
                user.setRole(rst.getString("role"));
                user.setTel(rst.getInt("telf"));
                user.setEmail(rst.getString("email"));
                user.setPassword(rst.getString("password"));

            }
        }
    }

    @Override

    public void SignInWithCode(String mail, String code) throws SQLException {
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM user WHERE  email = '" + mail + "' and code = '" + code + "'");
        User user = new User();
        if (rst != null) {
            while (rst.next()) {
                user.setId(rst.getInt("id"));
                user.setNom(rst.getString("nom"));
                user.setPrenom(rst.getString("prenom"));
                user.setRole(rst.getString("role"));
                user.setTel(rst.getInt("telf"));
                user.setEmail(rst.getString("email"));
                user.setPassword(rst.getString("password"));
            }
        }
    }

    @Override

    public List<String> afficher_email() throws SQLException {
        List<String> arr = new ArrayList<>();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select * from user ;");
        while (rs.next()) {
            String email = "" + rs.getString("email");
            arr.add(email);
        }
        return arr;
    }

    @Override
    public String afficher_role() throws SQLException {
        
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select role from user ");
        String role = "";
        while (rs.next()) {
            role = rs.getString(1);
             return role;
        }
       
        return role;
    }

    @Override
    public List<String> afficher_mobile() throws SQLException {
        List<String> arr = new ArrayList<>();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select * from user ;");
        while (rs.next()) {
            String tels = rs.getString("telf");
            arr.add(tels);
        }
        return arr;
    }

    @Override

    public void SignOut() throws SQLException {
        User user = new User();
        user.setId(0);
        user.setNom("");
        user.setPrenom("");
        user.setRole("");
        user.setTel(0);
        user.setEmail("");
        user.setPassword("");
    }

    public int stat_User() throws SQLException {
        int total = 0;
        PreparedStatement pst;

        String req = "select count(*)  from user where months_between(sysdate,date)=1";
        pst = connexion.prepareStatement(req);
        ResultSet rst = pst.executeQuery(req);
        while (rst.next()) {
            total = rst.getInt(1);
        }

        return total;

    }

}
