/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import java.util.Date;
import Event.crud.Evenement;
import events.tools.Maconnexion;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ideapadGAMING
 */
public class EventService {
    Connection cnx;
    PreparedStatement ste;
    Statement stm;
    public EventService() {
     cnx = Maconnexion.getInstance().getCnx();
     
    }
    public void ajouterEevenement(Evenement e)
    {
         
        try {
            String sql="insert into evenement(nom,description,duree,nombre_place,date_debut)"+"Values(?,?,?,?,?)";
            ste = cnx.prepareStatement(sql);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getDescription());
           ste.setInt(3,e.getDuree());
            ste.setInt(4, e.getNombre_place());
             ste.setDate(5, e.getDate_debut());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }
    
   public List<Evenement> afficherEvenement() throws SQLException{
       List<Evenement> levent= new ArrayList<>();
        
            String sql="select * from evenement";
            stm= cnx.createStatement();
           ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) { 
               Evenement e = new Evenement(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("description"),
                rs.getInt("duree"),
                rs.getInt("nombre_place"),
                rs.getDate("date_debut"));
                
                levent.add(e);
                
            
            }
      
     return levent;
   }
      public void update(Evenement e) {
        
    String sql="update evenement  set  nom=?, description= ?, duree=?, nombre_place=?, date_debut=? where id='"+e.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getDescription());
            ste.setInt(3, e.getDuree());
            ste.setInt(4, e.getNombre_place());
            ste.setDate(5, e.getDate_debut());
            
           ste.executeUpdate();
            System.out.println("evenement Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
         public void supprimer(Evenement e) {
        String sql = "DELETE FROM evenement WHERE id=?";
        try {
            
            PreparedStatement pst = Maconnexion.getInstance().getCnx().prepareStatement(sql);
            pst.setInt(1,e.getId());
            pst.executeUpdate();
            System.out.println("evenement supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
    }
    
    }
          public ObservableList<Evenement> RECHERCHE(int idf ) {
         ObservableList<Evenement> evenement = FXCollections.observableArrayList();
        String req = "SELECT id,nom,description,duree,nombre_place,date_debut FROM evenement where id LIKE '" + idf + "%'  ";
       

        ObservableList<Evenement> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              Evenement f=new Evenement(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getInt(5),rst.getDate(6));
               list.add(f);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
     }
          
             public ObservableList<Evenement> TriS() {
     
  
        
          String req = "SELECT id,nom,description,duree,nombre_place,date_debut FROM evenement order by duree DESC";

        ObservableList<Evenement> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
               Evenement e=new Evenement(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getInt(5),rst.getDate(6));
               list.add(e);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
       
     }
          
          
}
