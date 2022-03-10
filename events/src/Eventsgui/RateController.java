/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventsgui;
import Event.crud.rating;
import events.tools.Maconnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author ideapadGAMING
 */
public class RateController implements Initializable {

    @FXML
    private ComboBox<String> cbrate;
    @FXML
    private Rating rateev;
    @FXML
    private Button btnrate;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void remplirevent(MouseEvent event) {
        
        
        
        
        try {
            String sql="select nom from evenement ";
            
            
            List<String> nm =new ArrayList<String>();
            PreparedStatement ste;
            Statement st;
            Connection cnx;
            cnx = Maconnexion.getInstance().getCnx();
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                
                nm.add(rs.getString("nom"));
                cbrate.setItems(FXCollections.observableArrayList(nm));
            }
             Notifications notificationBuild = Notifications.create()
                        .title("RATE")
                        .text("thank u for Rating us")
                        //.hideAfter(Duration.Hours(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("click here");
                            }
                        });
                notificationBuild.showConfirm();
        } catch (SQLException ex) {
            Logger.getLogger(RateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 

    @FXML
    private void rateev(MouseEvent event) {
              
                   
                   PreparedStatement ste;
                   Statement st;
                   Connection cnx;
                   
                   cnx = Maconnexion.getInstance().getCnx();
                   
                   int rate=(int) rateev.getRating();
                     System.out.println("rating is :"+rateev.getRating());
                   
                   String nomev =  cbrate.getValue();
                   String sql1="select id from evenement where nom='"+nomev+"'";
                   
                   int id_event=0;
                   try {
                       
                       cnx = Maconnexion.getInstance().getCnx();
                       ste = cnx.prepareStatement(sql1);
                       ResultSet rs = ste.executeQuery(sql1);
                       
                       
                       
                       if(rs.next())
                       {
                           id_event =rs.getInt("id");
                           
                           
                           
                           
                       }
                   } catch (SQLException ex) {
                       Logger.getLogger(RateController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   String sql2="insert into rating (id_event,rate) values(?,?)";
                     try {
                   cnx = Maconnexion.getInstance().getCnx();
                   ste = cnx.prepareStatement(sql2);
                   
                   ste.setInt(1,id_event);
                   
                   ste.setInt(2, rate);
                   
                   ste.executeUpdate();
               } catch (SQLException ex) {
            Logger.getLogger(RateController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    }

    
    
}