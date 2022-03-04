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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.esprit.model.Stock;
import tn.esprit.service.Mail;
import tn.esprit.service.StockService;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Khadija
 */
public class StocksController implements Initializable {

    @FXML
    private TableView<Stock> tableviewS;
    @FXML
    private TableColumn<Stock, Integer> id;
    @FXML
    private TableColumn<Stock, String> partenaire;
    @FXML
    private TableColumn<Stock, String> nom;
    @FXML
    private TableColumn<Stock, Integer> reference;
    @FXML
    private TableColumn<Stock, String> categorie;
    @FXML
    private TableColumn<Stock, Integer> quantite;
    @FXML
    private TableColumn<Stock, Date> date;
    @FXML
    private TableColumn<Stock, Integer> qualite;
    @FXML
    private TextField cpartenaireS;
    @FXML
    private TextField cnomS;
    private ComboBox<String> ccategorieS;
    @FXML
    private Spinner<Integer> cqteS;
    @FXML
    private Spinner<Integer> cqualiteS;
    @FXML
    private TextField searchS1;
    @FXML
    private TextField partenaire1;
    @FXML
    private TextField nom1S;
    @FXML
    private TextField reference1S;
    @FXML
    private ComboBox<String> categorie1S;
    @FXML
    private DatePicker dateA1S;
    @FXML
    private Spinner<Integer> qte1S;

    private TextField idt;
    ObservableList<Stock> listS1 = FXCollections.observableArrayList();
    int index = -1;
    @FXML
    private LineChart<?, ?> LineChart;
    @FXML
    private TextField identifiant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Alimentaire", "Equipement", "Internet", "Securite");

        categorie1S.setItems(list);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        //valueFactory.setValue(1);
        cqteS.setValueFactory(valueFactory);
        cqualiteS.setValueFactory(valueFactory1);
        qte1S.setValueFactory(valueFactory2);

        affiche();
    }

    void affiche() {
        try {
            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            String req = "SELECT * from stock";
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            listS1.clear();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Stock p = new Stock(rst.getInt("idS"),
                        rst.getString("Partenaire"),
                        rst.getString("nomS"),
                        rst.getInt("refS"),
                        rst.getString("categorieS"),
                        rst.getInt("qteS"),
                        rst.getDate("dateS"),
                        rst.getInt("qualiteS")
                );
                listS1.add(p);

            }

            id.setCellValueFactory(new PropertyValueFactory<>("idS"));
            partenaire.setCellValueFactory(new PropertyValueFactory<>("Partenaire"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nomS"));
            reference.setCellValueFactory(new PropertyValueFactory<>("refS"));
            categorie.setCellValueFactory(new PropertyValueFactory<>("categorieS"));
            quantite.setCellValueFactory(new PropertyValueFactory<>("qteS"));
            date.setCellValueFactory(new PropertyValueFactory<>("dateS"));
            qualite.setCellValueFactory(new PropertyValueFactory<>("qualiteS"));
            tableviewS.setItems(listS1);

            FilteredList<Stock> filtredData = new FilteredList<>(listS1, b -> true);
            searchS1.textProperty().addListener(((observable, oldValue, newValue) -> {

                filtredData.setPredicate(Stock -> {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    }
                    String searchword = newValue.toLowerCase();
                    if (Stock.getNomS().toLowerCase().indexOf(searchword) > -1) {
                        return true;
                    } else if (Stock.getPartenaire().toLowerCase().indexOf(searchword) > -1) {
                        return true;
                    } else if (Stock.getCategorieS().toLowerCase().indexOf(searchword) > -1) {
                        return true;
                    } else {
                        return false;
                    }

                });
            })
            );
            SortedList<Stock> sortedData = new SortedList<>(filtredData);
            sortedData.comparatorProperty().bind(tableviewS.comparatorProperty());
            tableviewS.setItems(sortedData);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void Refresh() {

        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();
        String req = "SELECT * from stock";
        try {
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            listS1.clear();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Stock p = new Stock(rst.getInt("idS"),
                        rst.getString("Partenaire"),
                        rst.getString("nomS"),
                        rst.getInt("refS"),
                        rst.getString("categorieS"),
                        rst.getInt("qteS"),
                        rst.getDate("dateS"),
                        rst.getInt("qualiteS")
                );
                listS1.add(p);

            }

            id.setCellValueFactory(new PropertyValueFactory<>("idS"));
            partenaire.setCellValueFactory(new PropertyValueFactory<>("Partenaire"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nomS"));
            reference.setCellValueFactory(new PropertyValueFactory<>("refS"));
            categorie.setCellValueFactory(new PropertyValueFactory<>("categorieS"));
            quantite.setCellValueFactory(new PropertyValueFactory<>("qteS"));
            date.setCellValueFactory(new PropertyValueFactory<>("dateS"));
            qualite.setCellValueFactory(new PropertyValueFactory<>("qualiteS"));

            tableviewS.setItems(listS1);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    @FXML
    private void delete(ActionEvent event) {

        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();
        String req = "delete from stock WHERE nomS=?";

        try {
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);
            stm.setString(1, cnomS.getText());
            stm.execute();

            Refresh();
            JOptionPane.showMessageDialog(null, "Delete");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void closeS1(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    private void ajouterS(ActionEvent event) {

        categorie1S.setItems(FXCollections.observableArrayList("Alimentaire", "Equipement", "Internet", "Securite"));

        StockService ss = new StockService();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dateA1S.getValue());

        Stock s = new Stock(partenaire1.getText(), nom1S.getText(), Integer.parseInt(reference1S.getText()), categorie1S.getSelectionModel().getSelectedItem(), qte1S.getValue(), gettedDatePickerDate);

        try {
            ss.ajouterS(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Stock is added successfully!");
            alert.show();
            Refresh();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @FXML
    void UPDATE(ActionEvent event) {

        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();

        try {

            String value1 = cpartenaireS.getText();
            String value2 = cnomS.getText();
            Integer value3 = cqteS.getValue();
            Integer value4 = cqualiteS.getValue();
            String req = "update stock set Partenaire= ' " + value1 + " ',nomS= ' " + value2 + " ',qteS= ' " + value3 + " ', qualiteS=' " + value4 + " ' where idS=' " + identifiant.getText() + " ' ";
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);
            stm.execute();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Publication is updated successfully!");
            alert.show();
            Refresh();
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void getDisplaySelected(javafx.scene.input.MouseEvent event) {


        index = tableviewS.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        cpartenaireS.setText(partenaire.getCellData(index));
        cnomS.setText((nom.getCellData(index)).toString());
        cqteS.getValueFactory().setValue(quantite.getCellData(index));
        cqualiteS.getValueFactory().setValue(qualite.getCellData(index));
         identifiant.setText(id.getCellData(index).toString());
    }

    @FXML
    private void SeuilStock(ActionEvent event) {
       /*  try {
            try {
            StockService s= new StockService();
            s.TraitementS();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Mail envoyé avec succée!");
            alert.show();
            } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            }
            }*/
            /*
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
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StocksController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      
    }

}
