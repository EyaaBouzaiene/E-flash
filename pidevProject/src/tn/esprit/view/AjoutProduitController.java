/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.service.*;
import tn.esprit.model.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author aders
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private Label lnom;
    @FXML
    private TextField nom;
    @FXML
    private Label ldesc;
    @FXML
    private Label ltype;
    @FXML
    private Label lqte;
    @FXML
    private Label ldate;
    @FXML
    private TextField desc;
    @FXML
    private TextField type;
    @FXML
    private Label lcategorie;
    @FXML
    private Spinner<Integer> qte;
    @FXML
    private DatePicker  date ;
    @FXML
    private ComboBox <String> categorie;
    @FXML
    private Label limage;
    @FXML
    private ImageView image ;
    @FXML
    private Label lqte1;
    @FXML
    private Spinner<Integer> qte1;
     @FXML
    private Button exit;
     @FXML
    private TextField search;
   final int initialValue = 0;
    Image myimage= new Image(getClass().getResourceAsStream("paysage.png"));
 
 
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, initialValue);
        qte.setValueFactory(valueFactory);
        qte1.setValueFactory(valueFactory);
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
            list.add(rst.getString("CODE"));
            categorie.setItems(list);
            }
             statement.close();
            
            Image myimage= new Image(getClass().getResourceAsStream("paysage.png"));
        } catch (SQLException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
  @FXML
    void exit(ActionEvent event) {
System.exit(0);
    }
    @FXML
    private void AddProduits(ActionEvent event) {
        ProduitsService sp =new ProduitsService();
        
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date.getValue());
        
        Produits p = new Produits(qte.getValue(),qte1.getValue(),"5", nom.getText(), desc.getText(), type.getText(),gettedDatePickerDate,categorie.getSelectionModel().getSelectedItem());
        try {
            sp.ajouterp(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
    }
   
        
    
}
