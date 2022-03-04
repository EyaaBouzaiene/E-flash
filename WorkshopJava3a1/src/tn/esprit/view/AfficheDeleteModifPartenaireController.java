/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.esprit.model.Partenaire;
import tn.esprit.service.PartenaireService;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Khadija
 */
public class AfficheDeleteModifPartenaireController implements Initializable {


    @FXML
    private ComboBox<String> categorie11;
    @FXML
    private DatePicker dateA1;
    @FXML
    private TextField search;
    @FXML
    private TextField email1;
    @FXML
    private TextField nom1;
    @FXML
    private TextField prenom1;

    @FXML
    private TableColumn<Partenaire, Integer> cid;
    @FXML
    private TableColumn<Partenaire, String> cnom;

    @FXML
    private TableColumn<Partenaire, String> cprenom;

    @FXML
    private TableColumn<Partenaire, Date> cdate;

    @FXML
    private TableColumn<Partenaire, String> ccategorie;
    @FXML
    private TableColumn<Partenaire, String> cmail;
    @FXML
    private TableView<Partenaire> tableview;

    @FXML
    private TextField mail;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;
    
    @FXML
    private TextField identifiant1;


    ObservableList<Partenaire> list1 = FXCollections.observableArrayList();

    int index = -1;
    
    /**
     * Initializes the controller class.
     */
    PartenaireService sp = new PartenaireService();

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<String> list = FXCollections.observableArrayList("Alimentaire", "Equipement", "Internet", "Securite");
       
        categorie11.setItems(list);
    //   iniLineChart();

        affiche();
    }

    void affiche() {
       try {
            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            String req = "SELECT * from partenaires";
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            list1.clear();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Partenaire p = new Partenaire(rst.getInt("idP"),
                        rst.getString("nomP"),
                        rst.getString("prenomP"),
                        rst.getString("mailP"),
                        rst.getString("categorieP"),
                        rst.getDate("dateAjout")
                );
                list1.add(p);

            }

            cid.setCellValueFactory(new PropertyValueFactory<>("idP"));
            cnom.setCellValueFactory(new PropertyValueFactory<>("nomP"));
            cprenom.setCellValueFactory(new PropertyValueFactory<>("prenomP"));
            cmail.setCellValueFactory(new PropertyValueFactory<>("mailP"));
            ccategorie.setCellValueFactory(new PropertyValueFactory<>("categorieP"));
            cdate.setCellValueFactory(new PropertyValueFactory<>("dateP"));

            tableview.setItems(list1);

            FilteredList<Partenaire> filtredData = new FilteredList<>(list1, b -> true);
            search.textProperty().addListener(((observable, oldValue, newValue) -> {

                filtredData.setPredicate(Partenaire -> {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    }
                    String searchword = newValue.toLowerCase();
                    if (Partenaire.getNomP().toLowerCase().indexOf(searchword) > -1) {
                        return true;
                    } else if (Partenaire.getPrenomP().toLowerCase().indexOf(searchword) > -1) {
                        return true;
                    } else if (Partenaire.getMailP().toLowerCase().indexOf(searchword) > -1) {
                        return true;
                    } else if (Partenaire.getCategorieP().toLowerCase().indexOf(searchword) > -1) {
                        return true;
                    } else {
                        return false;
                    }

                });
            })
            );
            SortedList<Partenaire> sortedData = new SortedList<>(filtredData);
            sortedData.comparatorProperty().bind(tableview.comparatorProperty());
            tableview.setItems(sortedData);

        } catch (SQLException ex) {
            Logger.getLogger(AfficheDeleteModifPartenaireController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    void close(ActionEvent event) {
System.exit(0);
       
    }
  void getSelect1(MouseEvent event) {

    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        nom.setText(cnom.getCellData(index));
        prenom.setText(cprenom.getCellData(index));
        mail.setText(cmail.getCellData(index));
         identifiant1.setText(cid.getCellData(index).toString());

    }

    @FXML
    void delete(ActionEvent event) {
       Connection connexion;
        connexion = MyDB.getInstance().getConnexion();
        String req = "delete from partenaires WHERE nomP=?";

        try {
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);
            stm.setString(1, nom.getText());
            stm.execute();

            Refresh();
            JOptionPane.showMessageDialog(null, "Delete");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void update(ActionEvent event) {

      //  Refresh();

        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();

        try {

            String value1 = nom.getText();
            String value2 = prenom.getText();
            String value3 = mail.getText();
           

            String req = "update partenaires set nomP= '" + value1 + "',prenomP= '" + value2 + "',mailP= '"
                    + value3 + "' where idP=' " + identifiant1.getText() + " ' ";
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);
            stm.execute();
            JOptionPane.showMessageDialog(null, "Update");
            Refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Refresh() {

        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();
        String req = "SELECT * from partenaires";
        try {
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            list1.clear();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Partenaire p = new Partenaire(rst.getInt("idP"),
                        rst.getString("nomP"),
                        rst.getString("prenomP"),
                        rst.getString("mailP"),
                        rst.getString("categorieP"),
                        rst.getDate("dateAjout")
                );
                list1.add(p);

            }

            cid.setCellValueFactory(new PropertyValueFactory<>("idP"));
            cnom.setCellValueFactory(new PropertyValueFactory<>("nomP"));
            cprenom.setCellValueFactory(new PropertyValueFactory<>("prenomP"));
            cmail.setCellValueFactory(new PropertyValueFactory<>("mailP"));
            ccategorie.setCellValueFactory(new PropertyValueFactory<>("categorieP"));
            cdate.setCellValueFactory(new PropertyValueFactory<>("dateP"));

            tableview.setItems(list1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    @FXML
    void ajouterP(ActionEvent event) {
        ObservableList<String> list = FXCollections.observableArrayList("Alimentaire", "Equipement", "Internet", "Securite");
        categorie11.setItems(list);

        PartenaireService sp = new PartenaireService();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dateA1.getValue());

        Partenaire p = new Partenaire(nom1.getText(), prenom1.getText(), email1.getText().toString().toLowerCase(), categorie11.getSelectionModel().getSelectedItem().toString(), gettedDatePickerDate);

       // if (nom.getText().isEmpty() || prenom.getText().isEmpty() || mail.getText().isEmpty() || categorie11.getSelectionModel().getSelectedItem().isEmpty() || dateA1.getValue().toString().isEmpty()) {
        //    Alert alert = new Alert(Alert.AlertType.ERROR);
        //    alert.setTitle("Error");
         //   alert.setContentText("les champs sont vides,Veuillez remplir tout les champs");
         //   alert.show();
      //  } else if (nom.getText().contains("0")) {
         //   Alert alert = new Alert(Alert.AlertType.ERROR);
         //   alert.setTitle("Error");
         //   alert.setContentText("les champs sont vides,Veuillez remplir tout les champs");
       //     alert.show();
      //  } else {
            try {
                sp.ajouterP(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Person is added successfully!");
                alert.show();
Refresh();
              nom1.setText("");
                prenom1.setText("");
                email1.setText("");
                dateA1.setValue(null);
                // categorie11.setValue("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    


    @FXML
    private void getSelect1(javafx.scene.input.MouseEvent event) {
    
        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        nom.setText(cnom.getCellData(index));
        prenom.setText((cprenom.getCellData(index)).toString());
        mail.setText(cmail.getCellData(index));
          mail.setText(cmail.getCellData(index));
    identifiant1.setText(cid.getCellData(index).toString());
    
    }
    }

           
  




