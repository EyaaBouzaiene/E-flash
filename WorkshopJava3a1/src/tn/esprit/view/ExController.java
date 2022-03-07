/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;
import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ResourceBundle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Rating;
import tn.esprit.service.Mail;
import tn.esprit.service.PDF;
import tn.esprit.service.SendMail;
import static tn.esprit.service.SendMail.send;
import tn.esprit.service.sms;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Khadija
 */
public class ExController implements Initializable {
 
  sms s=new sms();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }




    private void envoyer(ActionEvent event) {
        
//         Connection connexion;
//            connexion = MyDB.getInstance().getConnexion();
//            String req = "SELECT partenaires.mailP FROM `stock`,`partenaires`  WHERE stock.nomPartenaireS=partenaires.nomMarqueP AND stock.qteS <=3";
//            
//
//            PreparedStatement stm;
//        try {
//            stm = connexion.prepareStatement(req);
//       
//    ResultSet rst = stm.executeQuery(req);
//          
//            while (rst.next()) {
//                
//                  SendMail.send(
//           
//            );
//                
//            }
//           }
//    catch (SQLException ex) {
//          
//      
//    }
//    }
    
    

   

 
}

//    @FXML
//    private void SendMail(ActionEvent event) {
//         Mail.send(
//   "khanfirkhadija66@gmail.com",
//    "bebe@@##123,",
//     "khanfirkhadija66@gmail.com",
//    "Bienvenu ",
//    "mail de test!"
//  ); 
//    }
PDF p=new PDF();
Mail m=new Mail();
    @FXML
    private void SendMail(ActionEvent event) {
    m. sendMail("test",  "khanfirkhadija66@gmail.com");
        
    } 
    

    @FXML
    private void pdf(ActionEvent event) {
    /*    
          Connection connexion2;
        connexion2 = MyDB.getInstance().getConnexion();
        
        

           
        String req = "SELECT nomPartenaireS,nomS,refS,categorieS,qteS,dateS,qualiteS from stock";
         stm = connexion2.createStatement();
        try {
       
            stm = connexion.prepareStatement(req);

            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
       p.add(file, N1, N2, N3, N4, N5, N6, N7);
    }
}
    */
    
    }

    @FXML
    private void SMS(ActionEvent event) {
        
    ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("khanfirkhadija66@gmail.com");
        defaultClient.setPassword("A3C54E82-A703-FD8C-9F9B-4318E86FC336");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("Registered successfully.Please verify your account using this code:");
        smsMessage.to("+21626060609");
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
}


    
        
    


