/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.model.Personne;
import tn.esprit.service.PersonneService;

/**
 * FXML Controller class
 *
 * @author macbook
 */
public class PersonnesFXMLController implements Initializable {

    @FXML
    private TextField lnom;
    @FXML
    private Label listP;
    @FXML
    private TextField lprenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddPerson(ActionEvent event) {
        PersonneService sp= new PersonneService();
        Personne p = new Personne(lnom.getText(),lprenom.getText());
        
        try {
            sp.ajouterp(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
            lnom.setText("");
            lprenom.setText("");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }

    @FXML
    private void ListPersons(ActionEvent event) {
        PersonneService sp= new PersonneService();
        try {
            listP.setText(sp.afficherpersonne().toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
