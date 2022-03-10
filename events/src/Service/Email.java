/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import javafx.util.Duration;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author esprit
 */
public class Email {

    public static void sendMail(String msg, String to) {
        try {
            System.out.println("Preparing to send email");
            Properties properties = new Properties();

            //Enable authentication
            properties.put("mail.smtp.auth", "true");
            //Set TLS encryption enabled
            properties.put("mail.smtp.starttls.enable", "true");
            //Set SMTP host
            properties.put("mail.smtp.host", "smtp.gmail.com");
            //Set smtp port
            properties.put("mail.smtp.port", "587");

            //Your gmail address
            String userName = "hadil.khemissi@esprit.tn";
            //Your gmail password
            String password = "hadil123";

            //Create a session with account credentials
            Session session;
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(userName, password);
                }
            }
            );

            Message message = prepareMessage(session, userName, msg , to);
            //Send mail
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
            ex.getMessage();
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String msg,String to) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            //////
           // String to = "yassin.daboussi@esprit.tn,dabyain@gmail.com,hadil.khemissi@esprit.tn";
            InternetAddress[] parse = InternetAddress.parse(to, true);
            message.setRecipients(javax.mail.Message.RecipientType.TO, parse);
            //////
            message.setSubject("Notification");
            String htmlCode = "<h3> Event </h3> <br/> <h2><b>" + msg + "</b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
        }
        return null;
    }

}
