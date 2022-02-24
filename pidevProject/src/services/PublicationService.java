/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.MyDB;

/**
 *
 * @author EXTRA
 */
public class PublicationService implements IPublication<Publication>{
    
    Connection connexion;
    Statement stm;

    public PublicationService() {
        connexion = MyDB.getInstance().getConnexion();
        
    }

    @Override
    public void ajouter_publication(Publication p) throws SQLException {
        
           String req = "INSERT INTO `publication` (`titre`,`description`,`image_publication`,`date_publication`,`id_client_publication`) VALUES ( '"
                + p.getTitre() + "', '" + p.getDescription() + "','"
                + p.getImage_publication() + "','" + p.getDate() + "','" + p.getId_client()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Publication> afficher_publication() throws SQLException {
         List<Publication> publications = new ArrayList<>();
        String req = "select * FROM publication";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()){   
        Publication p = new Publication(
                rst.getInt("id_publication"),
                rst.getString("titre"),
                rst.getString("description"),
                rst.getString("image_publication"),
                rst.getString("date_publication"),
                rst.getInt("id_client_publication")
        );        
            publications.add(p);
        }
        return publications;
    }

    @Override
    public void modifier_publication(String t,int id) throws SQLException {
                String req = "UPDATE publication SET titre ='"+t+"'  where id_publication='"+id+"'";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void supprimer_publication(Publication p) throws SQLException {
        int id=38;
               String req = "delete from publication where `id_publication`='"+id+ "' ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
   }

    @Override
    public  List<Publication>  rechercherP(int id_client_publication) throws SQLException {
        
        List<Publication> publications = new ArrayList<>();
        String req = "SELECT * from publication  where id_client_publication='"+id_client_publication+"'";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()){   
        Publication p = new Publication(
                rst.getInt("id_publication"),
                rst.getString("titre"),
                rst.getString("description"),
                rst.getString("image_publication"),
                rst.getString("date_publication"),
                rst.getInt("id_client_publication")
        );        
            publications.add(p);
        }
        return publications;
        
    }

    @Override
    public List<Publication> triP() throws SQLException {
         
         List<Publication> publications = new ArrayList<>();
        String req = "select * FROM publication order by id_client_publication desc";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()){   
        Publication p = new Publication(
                rst.getInt("id_publication"),
                rst.getString("titre"),
                rst.getString("description"),
                rst.getString("image_publication"),
                rst.getString("date_publication"),
                rst.getInt("id_client_publication")
        );        
            publications.add(p);
        }
        return publications;
    }
    
    
    
    
    
 }

 

  
    
