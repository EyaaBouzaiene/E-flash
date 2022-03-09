/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;
import utils.MyDB;
import model.RateLiv;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class RateController implements Initializable {
  Connection connexion =MyDB.getInstance().getConnexion();
    Statement stm;
    @FXML
    private ComboBox<String> combo_email;
    @FXML
    private Button rateLivreur;
    @FXML
    private BarChart<String, Integer> ratebar;
    @FXML
    private Button boutton_mail;
    @FXML
    private Button exit;
    @FXML
    private Button boutton_stat;
    @FXML
    private Button rating;
    @FXML
    private Button stat;
    @FXML
    private org.controlsfx.control.Rating stars;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            stm = connexion.createStatement();
       
             String req1 = "SELECT email FROM  user where role='livreur' ";
            
            PreparedStatement statement1=connexion.prepareStatement(req1) ;
            ResultSet rst1 = statement1.executeQuery();
            ObservableList<String> list1 = FXCollections.observableArrayList();
             while (rst1.next()) {
           list1.add(rst1.getString("email"));
            combo_email.setItems(list1);
            }
            
            
             } catch (SQLException ex) {
            Logger.getLogger(livraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
          try {
           // TODO
           
           
         
           String req="Select rating.idLivreur,user.nom ,SUM(rating.rat) from rating ,user where rating.idLivreur=user.id GROUP by user.nom";
           
           
             //ArrayList<Integer> idLivreur = new ArrayList<Integer>();
             //ArrayList<Integer> sum = new ArrayList<Integer>();
           XYChart.Series<String,Integer> series=new XYChart.Series<>();
           stm = connexion.createStatement();
           //ensemble de resultat
           ResultSet rst = stm.executeQuery(req);
           
           while(rst.next()){
               //idLivreur.add(rst.getInt(2));
               //sum.add(rst.getInt(3));
           series.getData().add(new XYChart.Data<>(rst.getString(2), rst.getInt(3)));
        
           }
             CategoryAxis x= new CategoryAxis();
             x.setLabel("Livreur");
             NumberAxis y = new NumberAxis();
             y.setLabel("Rate");
           ratebar.getData().add(series);
          
           
         
       } catch (SQLException ex) {
           Logger.getLogger(StatLivreurController.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("erreur stat");
       }
         
         
         
     
         
         
        // TODO
    }    

    @FXML
    private void rating(ActionEvent event) throws SQLException {
         String v2 = combo_email.getSelectionModel().getSelectedItem();
         int idl=getidLivreur(v2);
         System.out.println(idl);
         System.out.println("rating is :"+stars.getRating());
         int s= (int) stars.getRating();
         System.out.println(s);
         RateLiv r =new RateLiv(s,idl);
          addRating(r);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rate");
            alert.setContentText("Thanks!");
            alert.show();
            String nom = getnomLivreur(v2);
            Notifications notificationbuilder =  Notifications.create()
                .title("Evaluation ")
                .text(" le livreur "+nom+" est évalue par " +s)
                 .graphic(null)
                 .hideAfter(Duration.seconds(20))
                 .position(Pos.TOP_RIGHT)
                  .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("click on notification");
                      }
                    }
                  );
       notificationbuilder.showConfirm();
         
    }
    void addRating(RateLiv r) throws SQLException
    {
        String req = "INSERT INTO `rating` (`rat`, `idLivreur`) VALUES ( '"
                + r.getRating()+ "', '" + r.getIdLivreur()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    int getidLivreur(String v) throws SQLException
    {
        String req = "select id from user where  email='"+v+"'";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        int i = rs.getInt("id");
        return i ;
    }
     String getnomLivreur(String v) throws SQLException
    {
        String req = "select nom from user where  email='"+v+"'";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        String i = rs.getString("nom");
        return i ;
    }

    @FXML
    private void tomail(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void gostat(ActionEvent event) {
    }

    @FXML
    private void torate(ActionEvent event) {
    }

   
}

