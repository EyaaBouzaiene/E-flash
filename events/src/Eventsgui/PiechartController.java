/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventsgui;

import Event.crud.Evenement;
import events.tools.Maconnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Zannou
 */
public class PiechartController implements Initializable {

    @FXML
    private PieChart piechart;
    private Statement st;
    private ResultSet rs;
    private Connection cnx;

    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    @FXML
    private Label txtPourcentage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx = Maconnexion.getInstance().getCnx();
        
        addData();
        
    }



//updates existing Data-Object if name matches
    public void addData() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        ////
        List<Evenement> EventList = new ArrayList<>();
        EventList = LoadDataEvent();
        for (int i = 0; i < EventList.size(); i++) {
            {
                Evenement ev = new Evenement();

                pieChartData.add(new PieChart.Data(EventList.get(i).getNom(), Double.parseDouble(EventList.get(i).getNombre_place())));
                piechart.setData(pieChartData);
            }
        }


        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                        double total = 0;
                        for (PieChart.Data d : piechart.getData()) {
                            total += d.getPieValue();
                        }
                        String text = String.format("%.1f%%", 100 * data.getPieValue() / total);
                        
                        txtPourcentage.setVisible(true);
                  txtPourcentage.setText("Pourcentage : "+text);
                    }
                    
            );
            
        }

    }

    private List<Evenement> LoadDataEvent() {

        List<Evenement> EventList = new ArrayList<>();
        try {
            String requete = "SELECT id,nom,description,duree,nombre_place,date_debut from evenement";
            Statement pst = Maconnexion.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Evenement r = new Evenement();
                r.setId(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setDescription(rs.getString(3));
                //
                r.setDuree(rs.getString(4));
                r.setNombre_place(rs.getString(5));
                r.setDate_debut(rs.getDate(6));
                EventList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return EventList;
    }
}
