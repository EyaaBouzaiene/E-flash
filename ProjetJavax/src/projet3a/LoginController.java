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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.UserService;
import service.CryptWithMD5;
import utils.JDBC;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.print.DocFlavor;
import utils.Ticker;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class LoginController implements Initializable {
    
    UserService us = new UserService();
    CryptWithMD5 cr = new CryptWithMD5();
    Connection connexion;
    Statement stm;
    Timer timer = new Timer();
    private ResultSet rs;
    
    @FXML
    private TextField userLogin;
    @FXML
    private TextField userPass;
    @FXML
    private Button button1;
    @FXML
    private Label emailsent;
    @FXML
    private CheckBox RememeberMe;
    
    public LoginController() {
        connexion = JDBC.getInstance().getConnexion();
        
    }
    Preferences preferences;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preferences = preferences.userNodeForPackage(LoginController.class);
        if (preferences != null) {
            if (preferences.get("userLogin", null) != null && !preferences.get("userLogin", null).isEmpty()) {
                userLogin.setText(preferences.get("userLogin", null));
                userPass.setText(preferences.get("userpass", null));
                
            }
        }
        
    }
    
    public String generate(int id) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = id;
        Random random = new Random();
        
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        
        System.out.println("code:" + generatedString);
        
        return generatedString;
    }
    
    void sms(String rand) throws SQLException {
        int tel = 0;
        String req = "select telf  from user  where email = '" + userLogin.getText() + "'";
        
        rs = connexion.createStatement().executeQuery(req);
        
        while (rs.next()) {
            tel = rs.getInt(1);
        }
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("mayssa.bouzid@esprit.tn");
        defaultClient.setPassword("81F561D9-4A0A-0051-7418-F66C60FB9F44");
        SmsApi apiInstance = new SmsApi(defaultClient);
        rand = generate(7);
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("Registered successfully.Please verify your account using this code:" + rand);
        smsMessage.to("+216" + tel);
        smsMessage.source("sign up");
        
        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        String login = userLogin.getText();
        String pass = userPass.getText();
        String strcode = "";
        String rol = "";
        
        String strpass = "";
        ResultSet rst;
        rst = connexion.createStatement().executeQuery("select * from `user` where `email` = '" + login + "';");
        while (rst.next()) {
            strpass = rst.getString("password");
            strcode = rst.getString("code");
            rol=rst.getString("role");
            
            
        }
        
        System.out.println("All Logins: " + us.afficher_user());
        System.out.println("login: " + login);
        System.out.println("pass : " + pass);
        System.out.println("strcode: " + strcode);
        System.out.println(rol);
        System.out.println("==================="+us.afficher_role());
        if ((!us.afficher_email().contains(login))
                || (!(strpass.equals(cr.cryptWithMD5(pass)) || strcode.equals(pass)))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Wrong email or password!");
            alert.showAndWait();
            userLogin.clear();
            userPass.clear();
            
            
        } else if (rol.equals("Livreur") && us.afficher_email().contains(login) && strpass.equals(cr.cryptWithMD5(pass))) {
            us.SignIn(login, CryptWithMD5.cryptWithMD5(pass));
            
             rst = connexion.createStatement().executeQuery("select role from `user` where `email` = '" + login + "';");
        while (rst.next()) {
            String roll = rst.getString(1);
            System.out.println(roll);
        }
            
            if (RememeberMe.isSelected()) {
                
                preferences.put("userLogin", userLogin.getText());
                preferences.put("userPass", userPass.getText());
            } else {
                preferences.put("userLogin", "");
                preferences.put("userPass", "");
                
            }
            EspaceLivreurController.email=userLogin.getText();
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("EspaceLivreur.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            // Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage window = (Stage) RememeberMe.getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            //This line gets the Stage information

        }  else if (login.equals("eflash.esprit@gmail.com") && pass.equals("hello")) {
            System.out.println(login);
            us.SignIn(login, CryptWithMD5.cryptWithMD5(pass));
            if (RememeberMe.isSelected()) {
                
                preferences.put("userLogin", userLogin.getText());
                preferences.put("userPass", userPass.getText());
            } else {
                preferences.put("userLogin", "");
                preferences.put("userPass", "");
                
            }
            HomeUserController.em = "eflash.esprit@gmail.com";
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("homeUser.fxml"));//admin
            Scene tableViewScene = new Scene(tableViewParent);

            // Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage window = (Stage) RememeberMe.getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
            //This line gets the Stage information

        } else if (rol.equals("Client") && us.afficher_email().contains(login) && strpass.equals(cr.cryptWithMD5(pass))) {
             us.SignIn(login, CryptWithMD5.cryptWithMD5(pass));
            if (RememeberMe.isSelected()) {
                
                preferences.put("userLogin", userLogin.getText());
                preferences.put("userPass", userPass.getText());
            } else {
                preferences.put("userLogin", "");
                preferences.put("userPass", "");
            }
            HomeController.email = userLogin.getText();
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("home.fxml"));//user
            Scene tableViewScene = new Scene(tableViewParent);
            
            us.SignIn(login, CryptWithMD5.cryptWithMD5(pass));
            
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
            //This line gets the Stage information

        }//case where user entered the generated code
        else if (strcode.equals(pass)) {
            NewPasswordController.email = userLogin.getText();
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("newPassword.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            ///us.SignInWithCode(login, cr.cryptWithMD5(pass));
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        }
    }
    
    @FXML
    private void signup(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("inscri.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    @FXML
    private void forgot_password(ActionEvent event) throws SQLException {
        String login = userLogin.getText();
        String res = "";
        TextInputDialog dialog = new TextInputDialog(login);
        dialog.setTitle("Changing password");
        dialog.setHeaderText("Your password will be reset, confirm");
        dialog.setContentText("Please enter your email");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            res = result.get();
            
            if (us.afficher_email().contains(res)) {
                String rand = generate(7);
                SendingMail sm = new SendingMail("This is your account redemption code : " + rand, res, "Redeem your account");
                SendingMail.sendMail();
                //sms(rand);
                connexion.createStatement().execute("update `user` set `code` = '" + rand + "' where `email` = '" + res + "';");
                Ticker t = new Ticker(7200, res);
                emailsent.setTextFill(Color.web("#34ff2d"));
                emailsent.setText("Check your email.");
            } else {
                emailsent.setTextFill(Color.web("#ed0e0e"));
                emailsent.setText("Entered email does not exist.");
            }
            
        }
        
        userLogin.clear();
        userPass.clear();
        
    }
    
    public class Ticker {
        
        Timer timer;
        String login = userLogin.getText();
        
        public Ticker(int seconds, String s) {
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds * 1000);
            login = s;
        }
        
        class RemindTask extends TimerTask {
            
            @Override
            public void run() {
                
                timer.cancel(); //Terminate the timer thread
                try {
                    connexion.createStatement().execute("update `user` set `code` = NULL where `email` = '" + login + "';");
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("operation succeeded");
                
            }
        }
        
    }
    
}
