/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tn.esprit.utils.MyDB;

/**
 *
 * @author EXTRA
 */
public class DislikesService implements IDislikes{
    
    Connection connexion;
    Statement stm;

    public DislikesService() {
        connexion = MyDB.getInstance().getConnexion();
        
    }

    @Override
    public void ajouter_dislike(int id_p, int id_c) throws SQLException {

         String req = "SELECT * from dislikes  where id_publication_dislike='"+id_p+"' AND  id_client_dislike='"+id_c+"' ";
        stm = connexion.createStatement(); 
        
       
         String req_like = "DELETE  from likes  where id_publication_like='"+id_p+"' AND  id_client_like='"+id_c+"' ";
         stm.executeUpdate(req_like);
        
        
        ResultSet rst = stm.executeQuery(req);
        rst.last();
        int nbRow = rst.getRow();
        
       /* rst.last();
        int nbRow = rst.getRow();*/
       
        if(nbRow!=0){
            
          String req1 = "DELETE  from dislikes  where id_publication_dislike='"+id_p+"' AND  id_client_dislike='"+id_c+"' ";
          stm.executeUpdate(req1);
           }
        
       
        else{
            String req2 = "INSERT INTO `dislikes` (`id_publication_dislike`,`id_client_dislike`) VALUES ( '"
                + 1 + "','" + id_c + "') ";
            stm.executeUpdate(req2);
            }
        
    }

    @Override
    public void afficher_nombre_dislike(int id_publication) throws SQLException {

       String req = "SELECT * from dislikes  where id_publication_dislike='"+id_publication+"'";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        rst.last();
        int count = rst.getRow();
        System.out.println(count);
    }
    
}
