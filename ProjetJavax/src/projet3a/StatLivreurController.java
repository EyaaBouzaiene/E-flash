/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet3a;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import entity.Livraison;
import service.LivraisonService;
import utils.JDBC;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class StatLivreurController implements Initializable {
   Connection connexion=JDBC.getInstance().getConnexion();;
    Statement stm;
    
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private Button exit;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           // TODO
            
           int i =getidLivreur();
           //String n= getnomLivreur();
           String req="Select user.nom,COUNT(livraison.idLivreur) from livraison ,user  where livraison.idLivreur= user.id GROUP by user.nom";
           System.out.println("hiiiiiiiiiiii");
            
           
           XYChart.Series<String,Integer> series=new XYChart.Series<>();
           stm = connexion.createStatement();
           //ensemble de resultat
           ResultSet rst = stm.executeQuery(req);
           
           while(rst.next()){
           series.getData().add(new XYChart.Data<>(rst.getString(1), rst.getInt(2)));
           
           }
           barchart.getData().add(series);
          
           
         
       } catch (SQLException ex) {
           Logger.getLogger(StatLivreurController.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("erreur stat");
       }
    
    
    }  

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    int getidLivreur() throws SQLException
    {
        String req = "select id from user where role='livreur' ";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        int i = rs.getInt("id");
        return i;
    }
     String getnomLivreur() throws SQLException
    {
        String req = "select nom from user where role='livreur' ";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        String n = rs.getString("nom");
        return n;
    }

    /*@FXML
    private void load(ActionEvent event) throws SQLException {
          ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
           int i =getidLivreur();
        String req="Select user.nom,COUNT(livraison.idLivreur) from livraison ,user  where livraison.idLivreur= '"+i+"' GROUP by user.nom";
        stm = connexion.createStatement();
           //ensemble de resultat
           ResultSet rst = stm.executeQuery(req);
             while(rst.next()){
           
           list.add(new PieChart.Data(rst.getString("nom"),rst.getInt("idLivreur")));
           }
           
           piechart.setData(list);
    
    
    }*/
    
    
    
    
    }
    

