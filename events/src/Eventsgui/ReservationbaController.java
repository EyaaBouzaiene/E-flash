/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventsgui;

import Event.crud.reservation;
import events.tools.Maconnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author ideapadGAMING
 */
public class ReservationbaController implements Initializable {

    @FXML
    private TableColumn<reservation, Integer> id_rerservation;
    @FXML
    private TableColumn<reservation, String> gmail;
    @FXML
    private TableColumn<reservation, Integer> nombre_b;
    @FXML
    private TableColumn<reservation, Integer> nomb;
    @FXML
    private TableView<reservation> tablereser;
    public ObservableList<reservation> data = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
      showreservation(); 
        
    }    
    
public void showreservation()
{
        try {
            String sql="select * from reservation";
            PreparedStatement ste;
            Statement st;
            Connection cnx;
            cnx = Maconnexion.getInstance().getCnx();
            ste = cnx.prepareStatement(sql);
            
          
            ResultSet rs = ste.executeQuery(sql);
              while(rs.next())
              {
                 data.add(new reservation(rs.getInt("id_reservation"), rs.getString("gmail"), rs.getInt("nombre_billet"), rs.getInt("id_event"))) ;
              }
               
              
        } catch (SQLException ex) {
            Logger.getLogger(ReservationbaController.class.getName()).log(Level.SEVERE, null, ex);
        }
              id_rerservation.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_reservation"));
        gmail.setCellValueFactory(new PropertyValueFactory<reservation, String>("gmail"));
       nombre_b.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("nombre_billet"));
       nomb.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_event"));
        tablereser.setItems(data );
              
        

    
    
    
    
    
    
}
}



