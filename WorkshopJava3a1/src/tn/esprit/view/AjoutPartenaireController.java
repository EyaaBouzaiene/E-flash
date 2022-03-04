/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import com.gluonhq.impl.charm.a.b.b.e;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import tn.esprit.model.Partenaire;
import tn.esprit.service.PartenaireService;

/**
 * FXML Controller class
 *
 * @author aders
 */
public class AjoutPartenaireController implements Initializable {

    
    @FXML
    private TextField nom;
    @FXML
    private Label D;
    @FXML
    private TextField prenom;
      @FXML
    private TextField email;
    @FXML
    private DatePicker dateA;
    @FXML
    private ComboBox<String> categorie1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Alimentaire","Equipement","Internet","Securite");
        categorie1.setItems(list);
    }    

     @FXML
    void ajoutP(ActionEvent event) {

   

    
              PartenaireService sp= new PartenaireService();
  java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dateA.getValue());
  
 
        
    
        Partenaire p = new Partenaire(nom.getText(),prenom.getText(),email.getText().toString().toLowerCase(),categorie1.getSelectionModel().getSelectedItem(),gettedDatePickerDate);
        if(nom.getText().trim().equals("") || prenom.getText().equals("")|| email.getText().equals("")|| categorie1.getSelectionModel().getSelectedItem().equals("")|| dateA.getValue().equals("")){
Alert alert = new Alert(Alert.AlertType.ERROR);
 alert.setTitle("Error");
            alert.setContentText("les champs sont vides");
            alert.show();
 }
        else 
        {
        try {
            sp.ajouterP(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
        
            nom.setText("");
            prenom.setText("");
            email.setText("");
            dateA.setValue(null);
            categorie1.setValue("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        }
  
        
    /*}*/
 }
}
    
    
    

