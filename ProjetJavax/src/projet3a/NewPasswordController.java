/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet3a;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import service.CryptWithMD5;
import service.UserService;
import utils.JDBC;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class NewPasswordController implements Initializable {

    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField conf;

    public static String email;

    UserService us = new UserService();
    CryptWithMD5 cr = new CryptWithMD5();
    User u = new User();
    private Connection con;
    private Statement ste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public NewPasswordController() {
        con = JDBC.getInstance().getConnexion();
    }

    @FXML
    private void changepass(ActionEvent event) throws SQLException, IOException {
        String pas = pass.getText();
        String confirm = conf.getText();
        /*
            *
            *test on password length
            *
         */
        if (pas.length() < 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Password too short, minimum length 5 characters");
            alert.showAndWait();
            pass.clear();
            conf.clear();
            return;
        }
        /*
        *
        *testing the match between the two passwords
        *
         */
        if (!confirm.equals(pas)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Passwords don't match");
            alert.showAndWait();
            pass.clear();
            conf.clear();
            return;
        }
        System.out.println(email);
        System.out.println(pass.getText());
        try {
            ste = con.createStatement();
            ste.executeUpdate("UPDATE `user` SET `password` = '" + cr.cryptWithMD5(pass.getText()) + "' WHERE `email` = '" + email + "';");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succes");
            alert.setHeaderText(null);
            alert.setContentText("Password changed");
            alert.showAndWait();
            if (email.equals("eflash.esprit@gmail.com")) {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("homeUser.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            } else {
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("home.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            }
        } catch (Exception e) {
            System.out.println("erreur");
        }

    }

    @FXML
    private void back(ActionEvent event) throws IOException, SQLException {
        us.SignOut();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
