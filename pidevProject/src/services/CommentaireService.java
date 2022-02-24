/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import java.sql.Connection;
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
public class CommentaireService implements ICommentaire<Commentaire>{

    Connection connexion;
    Statement stm;
    
      public CommentaireService() {
        connexion = MyDB.getInstance().getConnexion();
    }
      
    @Override
    public void ajouter_commentaire(Commentaire c) throws SQLException {
    String req = "INSERT INTO `commentaire` (`messages`,`date_commentaire`,`id_publication_commentaire`,`id_client`) VALUES ( '"
                + c.getMessages()+ "', '" + c.getDate()+ "','"
                + c.getId_publication_commentaire()+ "','" + c.getId_client()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Commentaire> afficher_commentaire() throws SQLException {
           List<Commentaire> commentaires = new ArrayList<>();
        String req = "select * FROM commentaire";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()){   
        Commentaire p = new Commentaire(
                rst.getInt("id_commentaire"),
                rst.getString("messages"),
                rst.getString("date_commentaire"),
                rst.getInt("id_publication_commentaire"),
                rst.getInt("id_client")
        );        
            commentaires.add(p);
        }
        return commentaires;
    }

    @Override
    public void modifier_commentaire(Commentaire c, int id) throws SQLException {
 String req = "UPDATE commentaire SET messages ='"+c.getMessages()+"',date_commentaire='"+ c.getDate()+"' where id_commentaire='"+id+"'";
        stm = connexion.createStatement();
        stm.executeUpdate(req);    }

  

    @Override
    public void supprimer_commentaire(Commentaire c) throws SQLException {
        int id=2;
        String req = "delete from commentaire where `id_commentaire` ='"+id+ "' ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);    }
    
}
