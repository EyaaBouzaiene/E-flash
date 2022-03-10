 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventsgui;

import events.tools.Maconnexion;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ideapadGAMING
 */
public class EventfrontController implements Initializable {

 PreparedStatement ste;
         public   Statement st;
          public  Connection cnx;
    @FXML
    private Button prcedent;
    @FXML
    private ImageView image_log;
    @FXML
    private Label txtnom;
    @FXML
    private Label txtdescription;
    @FXML
    private Label txtduree;
    @FXML
    private Button btreserver;

    @FXML
    private Label rate;
    @FXML
    private Button suiva;
    @FXML
    private Label labelid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cnx = Maconnexion.getInstance().getCnx();
         showEvent();
    }    

    @FXML
    private void showSuivant(MouseEvent event) {
        int position=0;
     try {
         String sql="select id from evenement "; 
         st= cnx.prepareStatement(sql);
                  ResultSet rs= st.executeQuery(sql);
                  if(rs.next())
                  {
                  position=rs.getInt("id");
                  
                  
                  }
         
     } catch (SQLException ex) {
         Logger.getLogger(EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
             Blob blob;
byte byteImg[];
     try {
         String sql="select id,nom,description,duree,nombre_place,date_debut,image from evenement  where id >'"+position+"'";
     
            cnx = Maconnexion.getInstance().getCnx();
            st= cnx.prepareStatement(sql); 
               ResultSet rs= st.executeQuery(sql);
            if(rs.next())
            {

                     txtnom.setText(rs.getString("nom"));
              txtdescription.setText(rs.getString("description")); 
        txtduree.setText(rs.getString("duree"));
        blob=rs.getBlob("image");
        byteImg=blob.getBytes(1, (int) blob.length());
           Image img=new Image(new ByteArrayInputStream(byteImg), image_log.getFitWidth(), image_log.getFitHeight(),true,true);
           image_log.setImage(img);
            }
     } catch (SQLException ex) {
         Logger.getLogger(EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @FXML
    private void showPrecedent(MouseEvent event) {
        
               int position=0;
     try {
         String sql="select id from evenement"; 
         st= cnx.prepareStatement(sql);
                  ResultSet rs= st.executeQuery(sql);
                  if(rs.next())
                  {
                      
                  position=rs.getInt("id");
                  
                  
                  }
         
     } catch (SQLException ex) {
         Logger.getLogger(EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
             Blob blob;
byte byteImg[];
     try {
         String sql="select id, nom,description,duree,nombre_place,date_debut,image from evenement  where id <='"+position+"'";
     
            cnx = Maconnexion.getInstance().getCnx();
            st= cnx.prepareStatement(sql); 
               ResultSet rs= st.executeQuery(sql);
            if(rs.next())
            {
                
                     txtnom.setText(rs.getString("nom"));
              txtdescription.setText(rs.getString("description")); 
        txtduree.setText(rs.getString("duree"));
        blob=rs.getBlob("image");
        byteImg=blob.getBytes(1, (int) blob.length());
           Image img=new Image(new ByteArrayInputStream(byteImg), image_log.getFitWidth(), image_log.getFitHeight(),true,true);
           image_log.setImage(img);
            }
     } catch (SQLException ex) {
         Logger.getLogger(EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
     } 
        
        
        
    }
    public void showEvent()
    {
        Blob blob;
byte byteImg[];
     try {
         String sql="select  nom,description,duree,nombre_place,date_debut,image from evenement ";
     
            cnx = Maconnexion.getInstance().getCnx();
            st= cnx.prepareStatement(sql); 
               ResultSet rs= st.executeQuery(sql);
            if(rs.next())
            {
            
                     txtnom.setText(rs.getString("nom"));
              txtdescription.setText(rs.getString("description")); 
        txtduree.setText(rs.getString("duree"));
        blob=rs.getBlob("image");
        byteImg=blob.getBytes(1, (int) blob.length());
           Image img=new Image(new ByteArrayInputStream(byteImg), image_log.getFitWidth(), image_log.getFitHeight(),true,true);
           image_log.setImage(img);
            }
     } catch (SQLException ex) {
         Logger.getLogger(EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @FXML
    private void reserver(MouseEvent event) throws IOException {
        
        
        
        
        
        
           Parent root = FXMLLoader.load(getClass().getResource("reservation.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
        
        
        
        
    }

    @FXML
    private void rateus(MouseEvent event) throws IOException {
    
      
    Parent root = FXMLLoader.load(getClass().getResource("rate.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    
    
    
    
    
    
}
}