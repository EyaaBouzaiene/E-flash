/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events.tools;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ideapadGAMING
 */
public class Maconnexion {
    public static Maconnexion ct;
     public String url = "jdbc:mysql://localhost:3306/e-flash";
    public String user="root";
    public String pwd ="";
    
    private Connection cnx;
    
    private Maconnexion()
    {
         try {
             cnx=DriverManager.getConnection(url,user,pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
            
             System.out.println(ex.getMessage());
         }
            }
    public static Maconnexion getInstance()
    {
    if(ct==null)
        ct=new Maconnexion();
    return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
