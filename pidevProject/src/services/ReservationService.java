/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.reservation;
import java.util.Date;

import events.tools.Maconnexion;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ideapadGAMING
 */
public class ReservationService {
     Connection cnx;
    PreparedStatement ste;
    Statement stm;
    public ReservationService() {
     cnx = Maconnexion.getInstance().getCnx();
     
    }
    public void ajouterReservation(reservation  r)
    {
         
        try {
            String sql="insert into reservation(nom,prenom,gmail,nombre_billet)"+"Values(?,?,?,?)";
            ste = cnx.prepareStatement(sql);
            ste.setString(1, r.getNom());
            ste.setString(2,r.getPrenom());
           ste.setString(3,r.getGmail());
            ste.setInt(4, r.getNombre_billet());
         
            ste.executeUpdate();
            System.out.println("resrvation Ajoutée");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }
    
   public List<reservation> afficherReservation() throws SQLException{
       List<reservation> lreservation= new ArrayList<>();
        
            String sql="select * from reservation";
            stm= cnx.createStatement();
           ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) { 
               reservation r = new reservation(
                rs.getInt("id_reservation"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("gmail"),
                rs.getInt("nombre_billet"));
                
                
                lreservation.add(r);
                
            
            }
      
     return lreservation;
   }
      public void update(reservation r) {
        
    String sql="update reservation  set  nom=?, prenom= ?, gmail=?, nombre_billet=? where id_reservation='"+r.getId_reservation()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, r.getNom());
            ste.setString(2, r.getPrenom());
            ste.setString(3, r.getGmail());
            ste.setInt(4, r.getNombre_billet());
            
            
           ste.executeUpdate();
            System.out.println("reservation Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
         public void supprimer(reservation r) {
        String sql = "DELETE FROM reservation WHERE id_reservation=?";
        try {
            
            PreparedStatement pst = Maconnexion.getInstance().getCnx().prepareStatement(sql);
            pst.setInt(1,r.getId_reservation());
            pst.executeUpdate();
            System.out.println("reservation supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
    }
    
    }
         
         
         
}
