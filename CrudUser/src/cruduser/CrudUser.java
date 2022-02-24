/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruduser;

import entities.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CryptWithMD5;
import service.UserService;

/**
 *
 * @author Mayssa
 */
public class CrudUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        User u = new User(1, "mayssa", "bouzid", "admin", 22334455, "ddd.bb@esprit.tn", "22117734");
        User u1 = new User("nour", "hedg", "admin", 22334455, "nour.bouzid@esprit.tn", "bnj");

        User u2 = new User( "hadil", "hhd", "client", 112, "hadil.khemissi@esprit.tn", "lavieestbelle");

        User u3 = new User("eya", "bouzi", "client", 971, "mayssa.bouzid@esprit.tn", "mayssa");

        User u4 = new User( "bnh", "jje", "client", 331, "bnh.bouzid@esprit.tn", "aslemacv");
        User u5 = new User( "ccc", "jje", "client", 331, "hadil.kkk@esprit.tn", "hello");

        UserService us = new UserService();
        String s= CryptWithMD5.cryptWithMD5("mayssa");
        System.out.println(s);
        
        /*  Base64.Encoder enc = Base64.getEncoder();
        String a = u.getPassword();
        String b = enc.encodeToString(a.getBytes());
        System.out.println(b);*/
 /* try {
          //  us.ajouter_user(u);
           us.ajouter_user(u2);
           us.ajouter_user(u1);

              System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println(us.afficher_user());
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }
      /*  try {
            //u = new User(9, "hadil", "khemissi", "admin", 22334455, "hadil.khemissi@esprit.tn", 22117734);

            us.modifier_user(u2);
            us.modifier_user(u4);
            us.modifier_user(u5);

            System.out.println("modifier avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println(us.afficher_user());
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }

     /*   try {

            us.supprimer_user(u2);
            us.supprimer_user(u4);
            us.supprimer_user(u5);

            System.out.println("delete avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println(us.afficher_user());
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(us.TrierUser(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        try {
            System.out.println(us.recherch_User("mayssa"));
        } catch (SQLException ex) {
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
