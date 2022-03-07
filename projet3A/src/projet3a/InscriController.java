/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet3a;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import static entity.sms.ACCOUNT_SID;
import static entity.sms.AUTH_TOKEN;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;
import utils.JDBC;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class InscriController implements Initializable {

    UserService us = new UserService();
    private final Connection con;
    private Statement ste;
    User u = new User();

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField Email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirm;
    @FXML
    private TextField mobile;
    @FXML
    private ComboBox Role;

    // Email Regex java
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    // static Pattern object, since pattern is fixed
    private static Pattern pattern;

    // non-static Matcher object because it's created from the input String
    private Matcher matcher;

    public boolean ControlePRENOM(User u) {
        String str = lastname.getText();
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

    public boolean ControleNOM(User u) {
        String str = firstname.getText();
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

    public InscriController() {
        con = JDBC.getInstance().getConnexion();

        // initialize the Pattern object
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Role.getItems().addAll("Client", "Livreur");
        Role.getSelectionModel().select("Not assigned");
        /*   mobile.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                mobile.setText(newValue.replaceAll("[^\\d]", ""));
            }
            
        });*/
    }

    /**
     * This method validates the input email address with EMAIL_REGEX pattern
     *
     * @param email
     * @return boolean
     */
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @FXML
    private void signIn(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void signUp(ActionEvent event) throws SQLException, IOException {
        String fn = firstname.getText();
        String ln = lastname.getText();
        String username = fn + " " + ln;
        System.out.println(username);
        String email = Email.getText();
        String pass = password.getText();
        String passc = confirm.getText();
        String tel = mobile.getText();
        String role = (String) Role.getValue();

        if ((!"".equals(fn)) && (!"".equals(ln)) && (!"".equals(email)) && (!"".equals(pass)) && (!"".equals(tel))) {
            Integer telint = Integer.parseInt(tel);

            /*
         test on email existance 
             */
            if (us.afficher_email().contains(email) == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Email already exists");
                alert.showAndWait();
                Email.clear();
                password.clear();
                confirm.clear();
                return;
            }
            /*
             test on phone number existance 
             */
            if (us.afficher_mobile().contains(tel) == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Telephone number already exists");
                alert.showAndWait();
                mobile.clear();
                return;
            }

            /*
              *test on the email textfield with regex
             */
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

            /*
               test on password length
             */
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

            /*
                testing the match between the two passwords
             */
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
            /*
            *
            *test on tel number length
             */
            if (mobile.getText().length() < 8 || mobile.getText().length() > 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Invalid phone number");
                alert.showAndWait();
                mobile.clear();
                return;
            }
            /* adding user to database*/
            User user = new User(fn, ln, role, telint, email, pass);
            us.ajouter_user(user);
           /* Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            try {

                Message message = Message
                        .creator(new PhoneNumber("+21652456290"), 
                                new PhoneNumber("+21652456290"),
                                "Your account has been created")
                        .create();
                System.out.println(message.getSid());}
                catch (TwilioException e) {
        System.out.println("An error occured from twillio."+e.getMessage());
    }*/
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Your account has been created");
                alert.showAndWait();
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();

            }else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the required fields");
            alert.showAndWait();

            firstname.clear();
            lastname.clear();
            Email.clear();
            password.clear();
            confirm.clear();
            mobile.clear();

        }

        }
    }
