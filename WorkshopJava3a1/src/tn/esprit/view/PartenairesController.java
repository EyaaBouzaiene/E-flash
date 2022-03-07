/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tn.esprit.model.Partenaire;
import tn.esprit.service.PartenaireService;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Khadija
 */
public class PartenairesController implements Initializable {

    ////tableview
    @FXML
    private TableView<Partenaire> tableview;
   @FXML
    private TableColumn<Partenaire, Integer> cMatricule;
    @FXML
    private TableColumn<Partenaire, String> cnom;
    @FXML
    private TableColumn<Partenaire, String> cprenom;
    @FXML
    private TableColumn<Partenaire, String> cmail;
    @FXML
    private TableColumn<Partenaire, String> ccategorie;
    @FXML
    private TableColumn<Partenaire, Date> cdate;
    @FXML
    private TableColumn<Partenaire,String > cnomMarque;
    
  /////update 
    
      @FXML
    private TextField prenom;
  @FXML
    private TextField mail;

    @FXML
    private TextField nom;
    
    
    //ajout
    
     @FXML
    private ComboBox<String> categorie11;
    @FXML
    private TextField nom1;
      @FXML
    private TextField prenom1;
       @FXML
    private TextField mail1;
      @FXML
    private DatePicker dateA1;
@FXML
    private TextField identifiant;
@FXML
    private TextField nomMarque1;
@FXML
    private TextField Matricule1;

///modif
@FXML
    private TextField nomMarq;


    ////search
        @FXML
    private TextField search;
  
           ObservableList<Partenaire> list1 = FXCollections.observableArrayList();
    ////Statistic
//////barchart
    @FXML
    private Label Nom_Stock;
 
    @FXML
    private Button Load;
    @FXML
    private BarChart<String, Number> barchartp;
ObservableList<XYChart.Data<String,Number>>list11p = FXCollections.observableArrayList();
    ObservableList<XYChart.Data> list1ml= FXCollections.observableArrayList();
      ////satck
    
    
    int index = -1;
     PartenaireService sp = new PartenaireService();
       
   
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list = FXCollections.observableArrayList("Alimentaire", "Equipement", "Internet", "Securite");
       
        categorie11.setItems(list);

       affiche();

       
       ///stat partenaire
            
     
            Connection connexion2;
        connexion2 = MyDB.getInstance().getConnexion();
        Statement stm;
        try {

            stm = connexion2.createStatement();

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            XYChart.Series<String, Number> series1 = new XYChart.Series();
            String req = "SELECT nomMarqueP , qualiteS from stock , partenaires WHERE partenaires.nomMarqueP=stock.nomPartenaireS  AND stock.qualiteS=5";
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {

             
                series.getData().add(new XYChart.Data<>(rst.getString("nomMarqueP"), rst.getInt("qualiteS")));
                // series2.getData().add(new XYChart.Data<>(rst.getString("NOM"),rst.getInt("quantite")));

            }

            
            barchartp.getData().add(series);

        } catch (SQLException ex) {

        }
  
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
                Partenaire p = new Partenaire(rst.getInt("MatriculeP"),
                        rst.getString("nomMarqueP"),
                        rst.getString("nomP"),
                        rst.getString("prenomP"),
                        rst.getString("mailP"),
                        rst.getString("categorieP"),
                        rst.getDate("dateAjout")
                );
                list1.add(p);

            }

            cMatricule.setCellValueFactory(new PropertyValueFactory<>("MatriculeP"));
            cnomMarque.setCellValueFactory(new PropertyValueFactory<>("nomMarqueP"));
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
                         } else if (Partenaire.getNomMarqueP().toLowerCase().indexOf(searchword) > -1) {
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
        

      
    }
  }
     
     public void Refresh() {

        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();
        String req = "SELECT MatriculeP,nomMarqueP,nomP,prenomP,mailP,categorieP,dateAjout from partenaires";
        try {
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            list1.clear();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Partenaire p = new Partenaire(rst.getInt("MatriculeP"),
                        rst.getString("nomMarqueP"),
                        rst.getString("nomP"),
                        rst.getString("prenomP"),
                        rst.getString("mailP"),
                        rst.getString("categorieP"),
                        rst.getDate("dateAjout")
                );
                list1.add(p);

            }

            cMatricule.setCellValueFactory(new PropertyValueFactory<>("MatriculeP"));
             cnomMarque.setCellValueFactory(new PropertyValueFactory<>("nomMarqueP"));
            cprenom.setCellValueFactory(new PropertyValueFactory<>("prenomP"));
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
    private void getSelect1(MouseEvent event) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        nom.setText(cnom.getCellData(index));
        prenom.setText(cprenom.getCellData(index));
        mail.setText(cmail.getCellData(index));
        nomMarq.setText(cnomMarque.getCellData(index));
        identifiant.setText(cMatricule.getCellData(index).toString());
      

    }

    @FXML
    private void delete(ActionEvent event) {
           Connection connexion;
        connexion = MyDB.getInstance().getConnexion();
          int i=JOptionPane.showConfirmDialog(null, "La suppression est irréversible. Êtes-vous sûr de vouloir continuer?",
                                    "Veuillez confirmer votre choix",
                                    JOptionPane.YES_NO_OPTION);
        if(i==0){
        String req = "delete from partenaires WHERE nomMarqueP= ?" ;

        try {
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);
            stm.setString(1, nomMarq.getText());
            stm.execute();

            Refresh();
            JOptionPane.showMessageDialog(null, "Delete");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
 else
            Refresh();
        
    }
        
    @FXML
    private void update(ActionEvent event) {
        
        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();

        try {
  String value1=nomMarq.getText();
            String value2 = nom.getText();
            String value3 = prenom.getText();
            String value4 = mail.getText();

String req = "UPDATE `partenaires` SET `nomMarqueP`='"+value1+"',`nomP`='"+value2+"',`prenomP`='"+value3+"',`mailP`='"+value4+"' where nomMarqueP='"+nomMarq.getText().toLowerCase()+"'";
                   
             
             PreparedStatement pst5 = connexion.prepareStatement(req);
              pst5.execute();
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Publication is updated successfully!");
            alert.show();
            Refresh();
              nom.clear();
            prenom.clear();
          mail.clear();
          nomMarq.clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void send(ActionEvent event) {
    }

    @FXML
    private void ajouterS(ActionEvent event) {
           ObservableList<String> list = FXCollections.observableArrayList("Alimentaire", "Equipement", "Internet", "Securite");
        categorie11.setItems(list);

        PartenaireService sp = new PartenaireService();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dateA1.getValue());

        Partenaire p = new Partenaire(Integer.valueOf(Matricule1.getText()),nomMarque1.getText(),nom1.getText(), prenom1.getText(), mail1.getText().toString().toLowerCase(), categorie11.getSelectionModel().getSelectedItem().toString(), gettedDatePickerDate);

     if (false==isValidEmail( mail1) || (categorie11.getSelectionModel().getSelectedItem().isEmpty()))  {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error mail");
                alert.setContentText("Veuillez entrer un amil valide!");
                alert.show();}
                else{
            try {
                sp.ajouterP(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Person is added successfully!");
                alert.show();
Refresh();
Matricule1.setText("");
     nomMarque1.setText("");
              nom1.setText("");
                prenom1.setText("");
                mail1.setText("");
                dateA1.setValue(null);
            categorie11.setValue("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
    }
    }

   public static boolean isValidEmail(TextField mail1){
        boolean b = false;
        String pattern = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        if(mail1.getText().matches(pattern))
            b = true;
        return b;
    }
   
  public static boolean textValid(String text){
        boolean b = false;
      if (!text.matches("[A-Za-z]"))
           b=true;
      
      return b;
      
  }

    @FXML
    private void LOAD(ActionEvent event) {
    }
    
    
     
    
    }

