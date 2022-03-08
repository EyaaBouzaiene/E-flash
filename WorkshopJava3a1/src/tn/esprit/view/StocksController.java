/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import com.gluonhq.charm.glisten.control.Rating;
import com.gluonhq.impl.charm.a.b.b.i;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import static javax.management.Query.value;
import javax.swing.JOptionPane;
import static sun.management.ConnectorAddressLink.export;
import tn.esprit.model.Stock;
import tn.esprit.service.Mail;
import tn.esprit.service.StockService;
import tn.esprit.utils.MyDB;
import tn.esprit.model.Stock;

/**
 * FXML Controller class
 *
 * @author Khadija
 */
public class StocksController implements Initializable {

    @FXML
    private TableView<Stock> tableviewS;
   @FXML
    private TableColumn<Stock, String> marque;
 
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
    private TableColumn<Stock,String> qualite;
    @FXML
    private TextField cpartenaireS;
    @FXML
    private TextField cnomS;
    private ComboBox<String> ccategorieS;
    @FXML
    private Spinner<Integer> cqteS;

     
    @FXML
    private TextField searchS1;
    @FXML
    private TextField mrqpartenaire;
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
     ObservableList<Stock> listS2 = FXCollections.observableArrayList();
    int index = -1;

   

    ////// pie chart
    @FXML
    private PieChart piechart;
    ObservableList<PieChart.Data> list1 = FXCollections.observableArrayList();

    ObservableList<XYChart.Data<String, Integer>> linechartList1 = FXCollections.observableArrayList();
    @FXML
    private Label Label1;

    /////barchart
    private BarChart<String, Number> linechart;
    ObservableList<PieChart.Data> list2 = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> list3 = FXCollections.observableArrayList();
    
    @FXML
    private Label Nom_Stock;
    @FXML
    private StackedBarChart<String, Integer> stackchart;
    @FXML
    private org.controlsfx.control.Rating rate;
    
@FXML
    private org.controlsfx.control.Rating rateA;
    @FXML
    private Button EXPORT;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EXPORT.setOnAction(event -> {
            pdf();
        });
        ObservableList<String> list = FXCollections.observableArrayList("Alimentaire", "Equipement", "Internet", "Securite");

        affiche();
    
       categorie1S.setItems(list);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100);
      
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100);
        //valueFactory.setValue(1);
        cqteS.setValueFactory(valueFactory);
       
     // cqualiteS.setValueFactory(valueFactory);
        qte1S.setValueFactory(valueFactory2);

        //// Statistic
        
        Connection connexion2;
        connexion2 = MyDB.getInstance().getConnexion();
        Statement stm;
        try {

            stm = connexion2.createStatement();

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            XYChart.Series<String, Number> series1 = new XYChart.Series();
            String req = "SELECT nomPartenaireS,qteS from stock";
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {

                list1.add(new PieChart.Data(rst.getString("nomPartenaireS"), rst.getInt("qteS")));
                series.getData().add(new XYChart.Data<>(rst.getString("nomPartenaireS"), rst.getInt("qteS")));
                // series2.getData().add(new XYChart.Data<>(rst.getString("NOM"),rst.getInt("quantite")));

            }

            piechart.setData(list1);

            piechart.setLabelLineLength(10);
            piechart.setLegendSide(Side.LEFT);
            piechart.setClockwise(false);

            Label1.setTextFill(Color.BLACK);
            Label1.setStyle("-fx-font: 20 arial;");

            for (final PieChart.Data data : piechart.getData()) {
                data.getNode().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED,
                        new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent e) {
                        Label1.setTranslateX(e.getSceneX() - Label1.getLayoutX());
                        Label1.setTranslateY(e.getSceneY() - Label1.getLayoutY());
                        Label1.setText(String.valueOf(data.getPieValue()) + "%");

                        Bounds b1 = data.getNode().getBoundsInLocal();
                        double newX = (b1.getWidth()) / 2.0 + b1.getMinX();

                        System.out.println(b1.getMinX());
                        double newY = (b1.getHeight()) / 2.0 + b1.getMinY();

// Make sure pie wedge location is reset
                        data.getNode().setTranslateX(0);
                        data.getNode().setTranslateY(0);

                        TranslateTransition tt = new TranslateTransition(
                                Duration.millis(1500), data.getNode());

                        tt.setByX(newX);
                        tt.setByY(newY);

                        tt.setAutoReverse(true);
                        tt.setCycleCount(2);
                        tt.play();
                    }
                });

            }
         

        } catch (SQLException ex) {

        }

        Connection connexion3;
        connexion3 = MyDB.getInstance().getConnexion();
      
          XYChart.Series<String, Number> series2 = new XYChart.Series();
            PreparedStatement stm2;
            

        try {
            
          String req2 = "SELECT nomPartenaireS,qteS from stock";
           stm2 = connexion3.prepareStatement(req2);
            ResultSet rst = stm2.executeQuery();
     
            
            while (rst.next()) {
                list2.add(new PieChart.Data(rst.getString("nomS"), rst.getInt("qteS")));
                series2.getData().add(new XYChart.Data<>(rst.getString("nomS"), rst.getInt("qteS")));
                

            }
        } catch (SQLException ex) {
ex.getMessage();
        }
        
    }

    void affiche() {
        
       try {
            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            String req = "SELECT nomPartenaireS,nomS,refS,categorieS,qteS,dateS,qualiteS from stock";
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            listS1.clear();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Stock p = new Stock(
                        rst.getString("nomPartenaireS"),
                        rst.getString("nomS"),
                        rst.getInt("refS"),
                        rst.getString("categorieS"),
                        rst.getInt("qteS"),
                        rst.getDate("dateS"),
                        rst.getString("qualiteS")
                );
                listS1.add(p);

            }

            marque.setCellValueFactory(new PropertyValueFactory<>("nomPartenaireS"));
         
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
                    } else if (Stock.getNomPartenaireS().toLowerCase().indexOf(searchword) > -1) {
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
         
        }
    }

    public void Refresh() {

        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();
        String req = "SELECT nomPartenaireS,nomS,refS,categorieS,qteS,dateS,qualiteS from stock";
        try {
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            listS1.clear();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Stock p = new Stock(
                        rst.getString("nomPartenaireS"),
                        rst.getString("nomS"),
                        rst.getInt("refS"),
                        rst.getString("categorieS"),
                        rst.getInt("qteS"),
                        rst.getDate("dateS"),
                        rst.getString("qualiteS")
                );
                listS1.add(p);

            }

            marque.setCellValueFactory(new PropertyValueFactory<>("nomPartenaireS"));
         
            nom.setCellValueFactory(new PropertyValueFactory<>("nomS"));
            reference.setCellValueFactory(new PropertyValueFactory<>("refS"));
            categorie.setCellValueFactory(new PropertyValueFactory<>("categorieS"));
            quantite.setCellValueFactory(new PropertyValueFactory<>("qteS"));
            date.setCellValueFactory(new PropertyValueFactory<>("dateS"));
            qualite.setCellValueFactory(new PropertyValueFactory<>("qualiteS"));

            tableviewS.setItems(listS1);

        } catch (SQLException ex) {
         

        }

    }

    @FXML
    private void delete(ActionEvent event) {

        Connection connexion;
        connexion = MyDB.getInstance().getConnexion();
            int i=JOptionPane.showConfirmDialog(null, "La suppression est irréversible. Êtes-vous sûr de vouloir continuer?",
                                    "Veuillez confirmer votre choix",
                                    JOptionPane.YES_NO_OPTION);
        if(i==0){
        String req = "delete from stock WHERE nomPartenaireS=?";

        try {
            PreparedStatement stm;
            stm = connexion.prepareStatement(req);
            stm.setString(1, cpartenaireS.getText());
            stm.execute();

           Refresh();
            JOptionPane.showMessageDialog(null, "Delete");

        } catch (Exception e) {
            
        }
    }
          
 else
            Refresh();

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
        

        Stock s = new Stock(mrqpartenaire.getText(), nom1S.getText(), Integer.parseInt(reference1S.getText()), categorie1S.getSelectionModel().getSelectedItem(), qte1S.getValue(), gettedDatePickerDate,String.valueOf(rateA.getRating()));

        try {
         
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

            ss.ajouterS(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Stock is added successfully!");
            alert.show();
            mrqpartenaire.setText("");
     nom1S.setText("");
              reference1S.setText("");
              categorie1S.setValue("");
             qte1S.getValueFactory().setValue(1);
            dateA1S.setValue(null);
                
            Refresh();
            
            
            

        } catch (SQLException ex) {
           
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
            String value4 = String.valueOf(rateA.getRating());
                //    (rate.getRating());
         
            
           
String req = "UPDATE `stock` SET `nomPartenaireS`='"+value1+"',`nomS`='"+value2+"',`qteS`='"+value3+"',`qualiteS`='"+value4+"' WHERE nomPartenaireS=' "+cpartenaireS.getText().toLowerCase()+" ' ";
                   

             PreparedStatement pst6 = connexion.prepareStatement(req);
              pst6.execute();
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Stock is updated successfully!");
            alert.show();
            Refresh();
            
            cpartenaireS.clear();
            cnomS.clear();
            cqteS.getValueFactory().setValue(10);
            rate.setRotate(0);
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void getDisplaySelected(javafx.scene.input.MouseEvent event) {

        index = tableviewS.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        cpartenaireS.setText(marque.getCellData(index));
        cnomS.setText((nom.getCellData(index)).toString());
        cqteS.getValueFactory().setValue(quantite.getCellData(index));
      rate.setRating(Double.valueOf(qualite.getCellData(index)));
     

    }

    @FXML
    void send(ActionEvent event) {

        Mail m=new Mail();
m.sendMail("test","flashelectro06@gmail.com");
        
        /*
    
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
            }
         */

 /*
            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            String req = "SELECT partenaires.mailP FROM `stock`,`partenaires`  WHERE stock.Partenaire=partenaires.idP AND stock.qteS <=3";
            

            PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                
                  Mail.send(
            "khanfirkhadija66@gmail.com",
            "bebe@@##123,",
            "khanfirkhadija66@gmail.com",
            "Bienvenu ",
            "mail de test!"
            );
                
            }}
        
    catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
      
    }
    }
         */
    }

    

      @FXML
    void trie(ActionEvent event) {

    
        
        List<Stock> stocks = new ArrayList<>();

        try {
            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();

            String req = "SELECT nomPartenaireS,nomS,refS,categorieS,qteS,dateS,qualiteS FROM `stock` ORDER BY qualiteS";
                PreparedStatement stm;
            stm = connexion.prepareStatement(req);

            listS2.clear();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Stock p = new Stock(
                        rst.getString("nomPartenaireS"),
                        rst.getString("nomS"),
                        rst.getInt("refS"),
                        rst.getString("categorieS"),
                        rst.getInt("qteS"),
                        rst.getDate("dateS"),
                        rst.getString("qualiteS")
                );
                listS2.add(p);

            }

            marque.setCellValueFactory(new PropertyValueFactory<>("nomPartenaireS"));
         
            nom.setCellValueFactory(new PropertyValueFactory<>("nomS"));
            reference.setCellValueFactory(new PropertyValueFactory<>("refS"));
            categorie.setCellValueFactory(new PropertyValueFactory<>("categorieS"));
            quantite.setCellValueFactory(new PropertyValueFactory<>("qteS"));
            date.setCellValueFactory(new PropertyValueFactory<>("dateS"));
            qualite.setCellValueFactory(new PropertyValueFactory<>("qualiteS"));
            tableviewS.setItems(listS2);

        } catch (SQLException ex) {
            Logger.getLogger(StocksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void pdf(){
    System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);

            Node root = this.tableviewS;

            job.printPage(root);
            job.endJob();

        }
    }
    
    
  
}
