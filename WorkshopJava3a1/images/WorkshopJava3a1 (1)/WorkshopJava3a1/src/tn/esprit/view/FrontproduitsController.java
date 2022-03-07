/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author aders
 */
public class FrontproduitsController implements Initializable {

    @FXML
    private Menu categories;
 
    private MenuItem itm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            Statement stm;
            stm = connexion.createStatement();
            String req = "SELECT CODE,NOMCAT FROM  categories";
            ResultSet rst = stm.executeQuery(req);
            PreparedStatement statement=connexion.prepareStatement(req) ;
            ObservableList<String> list = FXCollections.observableArrayList();
            while (rst.next()) {
                String naam = rst.getString("NOMCAT");
                itm=new MenuItem(naam);
                categories.getItems().add(itm);
                
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrontproduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
