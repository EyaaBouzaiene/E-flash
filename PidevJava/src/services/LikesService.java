/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Likes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.MyDB;

/**
 *
 * @author EXTRA
 */
public class LikesService implements ILikes<Likes>{
    
    Connection connexion;
    Statement stm;

    public LikesService() {
        connexion = MyDB.getInstance().getConnexion();
        
    }

    @Override
    public void ajouter_like(int id_p,int id_c) throws SQLException {
        
         
        String req = "SELECT * from likes  where id_publication_like='"+id_p+"' AND  id_client_like='"+id_c+"' ";
        stm = connexion.createStatement(); 
        
        String req_dislike = "DELETE  from dislikes  where id_publication_dislike='"+id_p+"' AND  id_client_dislike='"+id_c+"' ";
        stm.executeUpdate(req_dislike);
        
        ResultSet rst = stm.executeQuery(req);
        rst.last();
        int nbRow = rst.getRow();
        
       /* rst.last();
        int nbRow = rst.getRow();*/
       
        if(nbRow!=0){
            
          String req1 = "DELETE  from likes  where id_publication_like='"+id_p+"' AND  id_client_like='"+id_c+"' ";
          stm.executeUpdate(req1);
           }
        
       
        else{
            String req2 = "INSERT INTO `likes` (`id_publication_like`,`id_client_like`) VALUES ( '"
                + 1 + "','" + id_c + "') ";
            stm.executeUpdate(req2);
            }
        
    }

    @Override
    public void afficher_nombre_like(int id_publication) throws SQLException {
        
        String req = "SELECT * from likes  where id_publication_like='"+id_publication+"'";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        rst.last();
        int count = rst.getRow();
        System.out.println(count);
        
        
        
    }
    
    
    
    
}
