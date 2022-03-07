/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet3a;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import entity.User;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.UserService;
import utils.JDBC;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class HomeUserController implements Initializable {

    UserService us = new UserService();
    private Statement ste;
    private Connection cnx;
    private ResultSet rs;
    public static String em;

    @FXML
    private Label hello;
    @FXML
    private TableView<User> tableview;
    ObservableList<User> listuser = FXCollections.observableArrayList();
    @FXML
    private Button delete;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> pass;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableColumn<User, Integer> telf;
    //private TableColumn<User, String> code;
    @FXML
    private TextField search;
    @FXML
    private Button export;
    @FXML
    private BarChart<String, Integer> bachart;
    @FXML
    private Button log;
    @FXML
    private TableColumn<User, java.sql.Date> date;

    public void Afficher() {
        try {
            cnx = JDBC.getInstance().getConnexion();
            ste = cnx.createStatement();
            listuser.clear();
            for (User u : us.afficher_user()) {
                listuser.add(u);
            }
        } catch (SQLException ex) {
        }
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        telf.setCellValueFactory(new PropertyValueFactory<>("tel"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableview.setItems(listuser);
        tableview.setEditable(true);

//        nom.setCellFactory(TextFieldTableCell.forTableColumn());
//        prenom.setCellFactory(TextFieldTableCell.forTableColumn());
//        email.setCellFactory(TextFieldTableCell.forTableColumn());
//        pass.setCellFactory(TextFieldTableCell.forTableColumn());
//        role.setCellFactory(TextFieldTableCell.forTableColumn());
//       // telf.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//      telf.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        //code.setCellFactory(TextFieldTableCell.forTableColumn());
//       //date.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Afficher();
        Recherche();
        export.setOnAction(event -> {
            pdf();
        });

    }

    @FXML
    private void trier(ActionEvent event) {
        
    }

    @FXML
    private void export(ActionEvent event) {
    }

    public void Recherche() {
        FilteredList<User> filteredData = new FilteredList<>(listuser, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (product.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(product.getEmail()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(product.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        tableview.setItems(sortedData);
    }

    void pdf() {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);

            Node root = this.tableview;

            job.printPage(root);
            job.endJob();

        }
    }

    @FXML
    private void update_role(TableColumn.CellEditEvent event) throws SQLException {
        User userselected = tableview.getSelectionModel().getSelectedItem();
        userselected.setRole(event.getNewValue().toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation update");
        alert.setHeaderText("this confirmation about update");
        alert.setContentText("are you sure to update??");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            us.modifier_user(userselected);
        } else {
            System.out.println("Cancel");
        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        tableview.setItems(listuser);

        ObservableList<User> allUsers, SingleDemandes;
        allUsers = tableview.getItems();
        SingleDemandes = tableview.getSelectionModel().getSelectedItems();
        User u = SingleDemandes.get(0);
        UserService us = new UserService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation delete");
        alert.setHeaderText("this confirmation about delet");
        alert.setContentText("are you sure to delete??");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            us.supprimer_user(u);
            SingleDemandes.forEach(allUsers::remove);
        } else {
            System.out.println("Cancel");
        }

        Recherche();
    }

    @FXML
    private void logout(ActionEvent event) throws SQLException, IOException {
        us.SignOut();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        /*   Window window = log.getScene().getWindow();

        if (window instanceof Stage) {
            ((Stage) window).close();
        }
         */
    }

    @FXML
    private void stat(ActionEvent event) throws SQLException {
        /*  XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("nombre des utilisateurs inscrit par mois");
        ;
        try {

            series.getData().add(new XYChart.Data<>("date", us.stat_User()));
            bachart.getData().addAll(series);

        } catch (SQLException ex) {
            Logger.getLogger(HomeUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        String req = "select date ,COUNT(*)  from user  GROUP BY role";

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            rs = cnx.createStatement().executeQuery(req);

            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));

            }
            bachart.getData().add(series);

        } catch (Exception ex) {
            Logger.getLogger(HomeUserController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void modif(ActionEvent event) throws IOException {
        // ModifiercompteController.id_user = tableview.getSelectionModel().getSelectedItem().getId();
        ModifiercompteController.email = "eflash.esprit@gmail.com";
        Parent root = FXMLLoader.load(getClass().getResource("modifiercompte.fxml"));
        Stage myWindow = (Stage) tableview.getScene().getWindow();
        Scene sc = new Scene(root);
        myWindow.setScene(sc);
        myWindow.show();

    }

    @FXML
    private void excel(ActionEvent event) {
        try {
            String filename = "C:\\Users\\Mayssa\\Documents\\NetBeansProjects\\projet3A\\data.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("id");
            rowhead.createCell((short) 1).setCellValue("nom");
            rowhead.createCell((short) 2).setCellValue("prenom");
            rowhead.createCell((short) 3).setCellValue("email");
            rowhead.createCell((short) 4).setCellValue("password");
            rowhead.createCell((short) 5).setCellValue("role");
            rowhead.createCell((short) 6).setCellValue("telf");
            rowhead.createCell((short) 7).setCellValue("date");

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from user");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
                row.createCell((short) 1).setCellValue(rs.getString("nom"));
                row.createCell((short) 2).setCellValue(rs.getString("prenom"));
                row.createCell((short) 3).setCellValue(rs.getString("email"));
                row.createCell((short) 4).setCellValue(rs.getString("password"));
                row.createCell((short) 5).setCellValue(rs.getString("role"));
                row.createCell((short) 6).setCellValue(Integer.toString(rs.getInt("telf")));
                row.createCell((short) 7).setCellValue(rs.getDate("date"));
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

        } catch (Exception ex) {
            System.out.println(ex);

        }

    }

}
