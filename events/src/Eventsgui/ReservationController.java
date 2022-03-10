/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventsgui;
import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import Event.crud.reservation;
import com.itextpdf.text.pdf.PdfWriter;
import events.tools.Maconnexion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ideapadGAMING
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField gmailtxt;
    @FXML
    private TextField nombrebillettxt;
    @FXML
    private Button btnreserver;
    @FXML
    private ComboBox<String> eventcb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
 
    
   
     
        
        
        
        
        
        
    }
       

    private boolean validateemail(){
Pattern p=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
Matcher m =p.matcher(gmailtxt.getText());
        if(m.find()&& m.group().equals(gmailtxt.getText())){
            return true;
}
        else{
        Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("validation email");
        alert.setHeaderText(null);
        alert.setContentText("Insérez un Email valide");
        alert.showAndWait();
        return false;}}
    
    private boolean validatenum(){
Pattern p=Pattern.compile("[0-9]+");
Matcher m =p.matcher(nombrebillettxt.getText());
        if(m.find()&& m.group().equals(nombrebillettxt.getText())){
            return true;
}
        else{
        Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("validation ID");
        alert.setHeaderText(null);
        alert.setContentText("Insérez un ID valide");
        alert.showAndWait();
        return false;}
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
    @FXML
    private void reserver(MouseEvent event) {
        String random=generate(7);
        sms(random);
            PreparedStatement ste;
            Statement st;
            Connection cnx;
            
            cnx = Maconnexion.getInstance().getCnx();  
                String gmail=gmailtxt.getText();
                String nombre=nombrebillettxt.getText();
                int nombre_billet=Integer.parseInt(nombre);
               
                String nomev =  eventcb.getValue();
                String sql1="select id from evenement where nom='"+nomev+"'";
                int id_event=0;
                  try {
                
                cnx = Maconnexion.getInstance().getCnx();
                ste = cnx.prepareStatement(sql1);
                ResultSet rs = ste.executeQuery(sql1);
                if(rs.next())
                {
                  id_event =rs.getInt("id");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  if(!validateemail()|| !validatenum()) {
                 
                  }       
        else{
            String sql2="insert into reservation (gmail,nombre_billet,id_event)values(?,?,?)";
            //alert.setHeaderText("null");
           
            try {
            cnx = Maconnexion.getInstance().getCnx();
            ste = cnx.prepareStatement(sql2);
           ste.setString(1,gmail);
              ste.setInt(2,nombre_billet);
              ste.setInt(3,id_event);
                 ste.executeUpdate();
                
            gmailtxt.setText("");
            nombrebillettxt.setText("");
            eventcb.setValue("event");
                
            Notifications notificationBuild = Notifications.create()
                
                      .title("Reservation")
                                      .text("Reservation avec suucèes")
                              
                                      //.hideAfter(Duration.Hours(5))
                                      .position(Pos.TOP_RIGHT)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }});
                  notificationBuild.showConfirm();
                          
                   
               
                
                
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
                }
    }   

    @FXML
    private void remplirevent(MouseEvent event) {
          
        try {
            String sql="select nom from evenement ";
            
            
            List<String> nm =new ArrayList<String>();
            PreparedStatement ste;
            Statement st;
            Connection cnx;
            cnx = Maconnexion.getInstance().getCnx();
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                
                nm.add(rs.getString("nom"));
                   eventcb.setItems(FXCollections.observableArrayList(nm));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    void sms(String random)
    {
       
        int num=29834203;
        
        
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("hadil.khemissi@esprit.tn");
        defaultClient.setPassword("3AAF6C73-CB1E-17D6-1347-062EE7E30F51");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("Registered successfully.this is your reservation code:" +random);
        smsMessage.to("+216"+num);
        smsMessage.source("reservation");
        

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

  
        
 
    
    
    
    
    
    
    


