/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventsgui;

import Event.crud.Evenement;
import events.tools.Maconnexion;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author ideapadGAMING
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField txtnom;
    private TextField txtdescription;
    @FXML
    private Button btnajout;

    @FXML
    private Label datetxt;
    private TextField txtnombre1;
    @FXML
    private DatePicker dateev;
    @FXML
    private TableView<Evenement> tableview;
      @FXML
    private TableColumn<Evenement, Integer> nombrebretable;
   
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, Integer> durèetable;
    @FXML
    private TableColumn<Evenement, Date> date_debuttable;
  public ObservableList<Evenement> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Evenement, Integer> idtable;
    @FXML
    private TableColumn<Evenement, String> nomtable;
    @FXML
    private TextField nombretable;
    @FXML
    private Button search;
    @FXML
    private TextField searchid;
    @FXML
    private TextField dureetable;
    @FXML
    private TextField descriptiontxt;
  

  public void showEvent()
  {
    String sql="select * from evenement";
   Statement st;
         Connection cnx;
        cnx = Maconnexion.getInstance().getCnx();
        try {
            st= cnx.prepareStatement(sql);
                 ResultSet rs= st.executeQuery(sql);
                 while(rs.next()){
                 data.add(new Evenement (rs.getInt("id"),rs.getString("nom"),rs.getString("description"),rs.getInt("duree"),rs.getInt("nombre_place"),rs.getDate("date_debut")));
                 }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idtable.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("id"));
           nomtable.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom"));
              description.setCellValueFactory(new PropertyValueFactory<Evenement,String>("description"));
                 durèetable.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("durée"));
                    nombrebretable.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("nombre_place"));
                         date_debuttable.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));
                    tableview.setItems(data);
                 
       
  
  }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        showEvent();
    }    

    @FXML
    private void addevent(ActionEvent event) {
  
        
    }

    @FXML
    private void search(ActionEvent event) {
    }
    
}
