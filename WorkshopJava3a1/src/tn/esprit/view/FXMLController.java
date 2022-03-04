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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tn.esprit.service.Mail;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Khadija
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoie(ActionEvent event) {
       
          try {
        Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
           
            String req = "SELECT partenaires.mailP FROM `stock`,`partenaires`  WHERE stock.Partenaire=partenaires.idP AND stock.qteS <=3";
            

            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                
                Mail.send(
                        "flashelectro06@gmail.com",
                        "dhia1881",
                        //rst.getString("mailP"),
                        "aderssadhia@gmail.com",
                        "Besoin produit dans 2 jours la livraison !",
                        "Besoin produit !"
                );
                
            
        } }
catch (SQLException ex) {
            Logger.getLogger(StocksController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    
    }
}
    

