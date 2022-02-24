/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mayssa
 */
public class JDBC {
    final String url ="jdbc:mysql://localhost:3306/personne";
    final String login ="root";
    final String pwd="";
    private static JDBC instance;
    Connection connexion;
    
    
    private JDBC(){
        
        try {
            connexion =  DriverManager.getConnection(url, login, pwd);
              System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public static JDBC getInstance(){
    if (instance == null)
        instance = new JDBC();
    return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }
    
}
