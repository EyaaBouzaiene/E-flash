/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sun.plugin2.message.transport.Transport;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class SendMailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea txt_message;

    @FXML
    private TextField txt_object;

    @FXML
    private TextField txt_to;
    @FXML
    private Button boutton_send;
    String from,password,to,host,sub,contenant; 
    @FXML
    private Button exit;
    @FXML
    private Button tolivraison;
    @FXML
    private Button lien;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendMail(ActionEvent event) {
        from = "e.flash2022esprit@gmail.com";
        password = "vwfsra0s";
        to = txt_to.getText();
        host= "localhost";
        sub =txt_object.getText();
        contenant = txt_message.getText();
        Properties p=new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        Session s = Session.getInstance(p,
                new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("e.flash2022esprit@gmail.com","vwfsra0s");
            }
        });
        try{
            MimeMessage m = new MimeMessage(s);
            m.setFrom(from);
            m.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub);
            m.setText(contenant);
             javax.mail.Transport.send(m);
            System.out.println("success");
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Mail sended successfully!");
            alert.show();
             /*javax.mail.Message message = new MimeMessage(s);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(contenant);

            javax.mail.Transport.send(message);

            System.out.println("Done");*/
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void tolivraison(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/livraison.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void lienForum(ActionEvent event) {
        txt_message.setText("https://docs.google.com/forms/d/e/1FAIpQLSeN8a2QNoDcTibqIyu7usNn980YCNlRrO4ErUfCEWTytVDnNw/viewform?usp=sf_link");
    }
    
}
