///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package tn.esprit.service;
//
import com.gluonhq.impl.charm.a.b.b.t;
import static com.sun.javafx.animation.TickCalculation.sub;
import static java.lang.ProcessBuilder.Redirect.to;
import static java.time.LocalDate.from;
import javax.mail.*; 
import javax.mail.internet.*; 
import java.util.Properties;  
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.MessagingException;
//
//
//
///**
// *
// * @author Khadija
// */

public class Mail {
    
        /*
    public static String mailUsername;
    public static String mailPassword;
    public static String contenu;
    public static String destination;
    public static String subject;
 
    public Mail(String contenu, String destination, String subject) {
        mailUsername = "khanfirkhadija66@gmail.com";
        mailPassword = "bebe@@##123,";
        this.contenu = contenu;
        this.destination = destination;
        this.subject = subject;
    }
*/

        /*
        
  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        System.out.println("Preparing to send email");
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.setProperty("mail.smtps.auth", "true");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailUsername));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));
            message.setSubject(subject);
            message.setText(contenu);
            //Transport.connect("smtp.gmail.com", mailUsername, mailPassword);
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
           e.printStackTrace();
        }

    }*/
        
/*
 public static void send(String from,String pwd,String to,String sub,String msg){
    //Propriétés
    Properties p = new Properties();
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.socketFactory.port", "465");
    p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.port", "465");
    //Session
    Session s = Session.getDefaultInstance(p,
      new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(from, pwd);
      }
    });
    //composer le message
    try {
      MimeMessage m = new MimeMessage(s);
      m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
      m.setSubject(sub);
      m.setText(msg);
      //envoyer le message
      Transport.send(m);
      System.out.println("Message envoyé avec succès");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}


    */
    

   
   
//    public static void send(String from,String pwd,String to,String sub,String msg){
//    //Propriétés
//    Properties p = new Properties();
//    p.put("mail.smtp.host", "smtp.gmail.com");
//    p.put("mail.smtp.socketFactory.port", "587");
//    p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//    p.put("mail.smtp.auth", "true");
//    p.put("mail.smtp.port", "587");
//    //Session
//    Session s = Session.getDefaultInstance(p,
//      new javax.mail.Authenticator() {
//      protected PasswordAuthentication getPasswordAuthentication() {
//         return new PasswordAuthentication(from, pwd);
//      }
//    });
//    //composer le message
//    try {
//      MimeMessage m = new MimeMessage(s);
//      m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//      m.setSubject(sub);
//      m.setText(msg);
//      //envoyer le message
//      Transport.send(m);
//      System.out.println("Message envoyé avec succès");
//    } catch (MessagingException e) {
//      e.printStackTrace();
//    }
//  }
//}
//    


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
            properties.put("mail.smtp.port", "465");
            System.out.println("ok");
            //Your gmail address
            String userName = "khanfirkhadija6@gmail.com";
            //Your gmail password
            String password = "bebe@@##123,";
System.out.println("ok2");
            //Create a session with account credentials
            Session session;
            System.out.println("ok3");
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    System.out.println("ok4");
                    return new javax.mail.PasswordAuthentication(userName, password);
                }
            }
            );
System.out.println("ok5");
            Message message = prepareMessage(session, userName, msg , to);
            //Send mail
               System.out.println("ok6");
            Transport.send(message);
             System.out.println("ok7");
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


