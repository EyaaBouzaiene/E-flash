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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static projet3a.ModifiercompteController.email;
import service.UserService;
import utils.JDBC;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class HomeController implements Initializable {

    UserService us = new UserService();
    public static String email;
    Connection connexion;
    Statement stm;
    User u = new User();

    @FXML
    private TextField search;
    @FXML
    private PasswordField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField Email;
    @FXML
    private TextField mobile;
    @FXML
    private PasswordField confirm;
    @FXML
    private Label hello;
    @FXML
    private Button log;

    // Email Regex java
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    // static Pattern object, since pattern is fixed
    private static Pattern pattern;

    // non-static Matcher object because it's created from the input String
    private Matcher matcher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connexion = JDBC.getInstance().getConnexion();
        UserService us = new UserService();
        User u = new User();

        try {
            u = us.afficher_user_byEmail(email);
        } catch (SQLException ex) {
            Logger.getLogger(ModifiercompteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        firstname.setText(u.getNom());
        lastname.setText(u.getPrenom());
        Email.setText(u.getEmail());
        mobile.setText(String.valueOf(u.getTel()));
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
public  boolean ControlePRENOM(User u) {
        String str =lastname.getText() ;
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }
 public  boolean ControleNOM(User u) {
        String str =firstname.getText() ;
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        int id = 0;
        PreparedStatement pst;

        String req = "select id from `user` where `email`='" + email + "'";
        pst = connexion.prepareStatement(req);
        ResultSet rst = pst.executeQuery(req);
        while (rst.next()) {
            id = rst.getInt(1);
        }
        System.out.println(email);
        System.out.println(id);

        UserService us = new UserService();
        User u1 = us.afficher_user_byId(id);

        // User u = us.afficher_user_byEmail(email);
        String fn = firstname.getText();
        String ln = lastname.getText();

        String emaill = Email.getText();
        String pass = password.getText();
        String passc = confirm.getText();
        String tel = mobile.getText();

        if ((!"".equals(fn)) && (!"".equals(ln)) && (!"".equals(emaill)) && (!"".equals(pass)) && (!"".equals(tel))) {

            if (!validateEmail(Email.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email pattern");
                alert.showAndWait();
                Email.clear();
                password.clear();
                confirm.clear();
                return;
            }
            if (password.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Password too short, minimum length 5 characters");
                alert.showAndWait();
                password.clear();
                confirm.clear();
                return;
            }
            if (!passc.equals(pass)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Passwords don't match");
                alert.showAndWait();
                password.clear();
                confirm.clear();
                return;
            }
            if (!ControlePRENOM(u)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("prenom invalide");
                alert.showAndWait();
                return;
            }
            if (!ControleNOM(u)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("nom invalide");
                alert.showAndWait();
                return;
            }
            if (mobile.getText().length() < 8 || mobile.getText().length()>8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Invalid phone number");
                alert.showAndWait();
                mobile.clear();
                return;
            }
            u1.setNom(firstname.getText());
            u1.setPrenom(lastname.getText());
            u1.setEmail(Email.getText());
            u1.setPassword(password.getText());
            u1.setTel(Integer.parseInt(mobile.getText()));

            us.modifier_user(u1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Resultat:");
            alert.setContentText("Modification effectués avec succés");

            alert.showAndWait();
        } else {
           
        
       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setHeaderText("Resultat:");
            alert.setContentText("Modification invalide");

            alert.showAndWait();
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException, SQLException {
        us.SignOut();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
