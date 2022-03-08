/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import tn.esprit.com.darkprograms.speech.translator.GoogleTranslate;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.model.Commentaire;
import tn.esprit.model.Publication;
import tn.esprit.service.CommentaireService;
import tn.esprit.utils.MyDB;
import tn.esprit.utils.Translate;

/**
 * FXML Controller class
 *
 * @author EXTRA
 */
public class Espace_commentaireController implements Initializable {

    
                              
    DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
     /*   System.out.println(format.format(date));*/
    
    private ObservableList<Commentaire> items = FXCollections.observableArrayList();
    Connection connexion=MyDB.getInstance().getConnexion();
    Statement stm;
    
    int PublicationID;
    
    CommentaireService sc = new CommentaireService();
    @FXML
    private ListView<Commentaire> listCommentaire;
    @FXML
    private TextArea TextA_commenter;
    private ComboBox<String> Combox_langue;
     
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       //int id=87;
        //System.out.println(PublicationID);
        
       
                      //    String langue = Select_langue();
                               //System.out.println("--------"+langue);
       Combox_langue.getItems().addAll("Francais","English","Arabic");
                            
        
        
    }    

    @FXML
    private void Button_commenter(ActionEvent event) {
        
        File fichier = new File("C:\\Users\\EXTRA\\Desktop\\Front 1\\FrontOffice1\\src\\tn\\esprit\\utils\\mot.txt.txt");
           
      try { 
            Commentaire c1 = new Commentaire(TextA_commenter.getText(),format.format(date),PublicationID,4);
            Scanner scanf = new Scanner(fichier);
            int Verif_mot=0;
            
            while(scanf.hasNext() && Verif_mot==0){
                
            String mot = scanf.next();
            String a= TextA_commenter.getText();
            
             String[] newStr = a.split("\\s+");
        for (int i = 0; i < newStr.length; i++) {
            //System.out.println(newStr[i]);
        
            
            if(mot.contains(newStr[i]))
            {
               System.out.println(mot); 
               Verif_mot=1;
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Alert");
               alert.setContentText("Vous devez enlever le mot : "+newStr[i]);
               alert.show();
               
               TextA_commenter.setText("");
           }
            
        }
    
          }
            
            
            
            
           
            
            if(Verif_mot!=1){
                
            sc.ajouter_commentaire(c1);
            Button_Refresh_Commentaire();
            TextA_commenter.setText("");      
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Espace_commentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (FileNotFoundException ex) {
            Logger.getLogger(Espace_commentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
      void setTextField_Commentaire(int id) {

        PublicationID = id;
        
    }

   
        
    
        
    

    @FXML
    private void Button_Refresh_Commentaire() {
        
         try {
             // TODO
             
            String req = "select id_commentaire,messages,date_commentaire,id_client FROM commentaire where id_publication_commentaire='"+PublicationID+"'  ";
             
             stm = connexion.createStatement();
             ResultSet rst = stm.executeQuery(req);
             while (rst.next()){
                 
                    Commentaire c = new Commentaire(
                    rst.getInt("id_commentaire"),
                    rst.getString("messages"),       
                    rst.getString("date_commentaire"),
                    rst.getInt("id_client")           
                    
                   );
                    
               if(items.contains(c))
               {
                   
               }
               else{
                     items.add(c);
               }  
              
              
                   
             }
              
             listCommentaire.setItems(items);
        
             
                         
      listCommentaire.setCellFactory(param -> new ListCell<Commentaire>() {
            
            @Override
            public void updateItem(Commentaire name, boolean empty) {
                super.updateItem(name, empty);
         
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    
                          Button button_traduire_english = new Button("English");
                          Button button_traduire_francais = new Button("Francais");
                          Button button_traduire_arabic = new Button("Arabic");
                          
                          
                           button_traduire_english.setMaxWidth(900);
                           button_traduire_arabic.setMaxWidth(900);
                     
                          Pane pane1 = new Pane();
                          
                          
                          VBox v1 = new VBox();
                          
                           
                          Pane pane = new Pane();
                          pane.setStyle("-fx-background-color: #B3E5FC");
                          pane.setMaxWidth(2);
                    
                          
                          
                          Label label_msg =new Label();
                          Label label_client =new Label();
                          
                          label_msg.setText(name.getMessages());
                          label_client.setText("Hama");
                          
                          
                          
                          HBox hbox=new HBox();
                          hbox.setHgrow(pane,Priority.ALWAYS);
                          hbox.setHgrow(pane1,Priority.ALWAYS);
                          hbox.setSpacing(8);
                          
                         /* comboBox.setOnAction((ActionEvent event) -> {
                                
                               String langue=comboBox.getSelectionModel().getSelectedItem().toString();
                               System.out.println(langue);
                        });*/
                          
                         
                             
                             
                           button_traduire_english.setOnAction((ActionEvent event) -> {
                                
                              try {         
                                label_msg.setText(GoogleTranslate.translate("en",label_msg.getText()));
                                        
                              } catch (IOException ex) {
                                  Logger.getLogger(Espace_commentaireController.class.getName()).log(Level.SEVERE, null, ex);
                              }
                        });
                           button_traduire_francais.setOnAction((ActionEvent event) -> {
                                
                              try {         
                                label_msg.setText(GoogleTranslate.translate("fr",label_msg.getText()));
                                        
                              } catch (IOException ex) {
                                  Logger.getLogger(Espace_commentaireController.class.getName()).log(Level.SEVERE, null, ex);
                              }
                        });
                           button_traduire_arabic.setOnAction((ActionEvent event) -> {
                                
                              try {         
                                label_msg.setText(GoogleTranslate.translate("ar",label_msg.getText()));
                                        
                              } catch (IOException ex) {
                                  Logger.getLogger(Espace_commentaireController.class.getName()).log(Level.SEVERE, null, ex);
                              }
                        });
                          
                          
                          
                         
                           
                           
                          v1.getChildren().addAll(button_traduire_english,button_traduire_francais,button_traduire_arabic);
 
                               
                           hbox.getChildren().addAll(label_client,pane,label_msg,pane1,v1);
                         
                           setGraphic(hbox); 
                           
                }
            }
                 
        }
        );
               
             
             } catch (SQLException ex) {
             Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        
        
        
        
        
            
    }

    private String Select_langue() {
        
         String langue_c=Combox_langue.getSelectionModel().getSelectedItem().toString();
         return langue_c;
    }


    
    
    
    
    
    
    
    
    
    
}
