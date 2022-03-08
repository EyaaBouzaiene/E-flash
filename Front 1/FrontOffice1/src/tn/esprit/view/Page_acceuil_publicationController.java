
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
/*import java.awt.event.MouseEvent;*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.model.Publication;
import tn.esprit.service.PublicationService;
import tn.esprit.utils.MyDB;
import javafx.scene.input.MouseEvent;
import tn.esprit.service.LikesService;
/**
 * FXML Controller class
 *
 * @author EXTRA
 */
public class Page_acceuil_publicationController implements Initializable {

     int nbr_like;
     int nbr_dislike;
     
     LikesService sl = new LikesService();
     
     PublicationService sp = new PublicationService();
     Image myImage = new Image(getClass().getResourceAsStream("3ija.jpg"));
     private Image image;
      private ImageView imageView = new ImageView();
     Connection connexion=MyDB.getInstance().getConnexion();
     Statement stm;
     Publication pd=null;
     
      private ObservableList<Publication> items = FXCollections.observableArrayList();
      private ObservableList<String> items1 = FXCollections.observableArrayList();


    @FXML
    private ListView<Publication>listP;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //listP.setOrientation(Orientation.HORIZONTAL);
         //imageView.setImage(myImage);
    
        try {
             // TODO
             
            String req = "select id_publication,titre,description,image_publication,date_publication,id_client_publication FROM publication";
             
             stm = connexion.createStatement();
             ResultSet rst = stm.executeQuery(req);
             while (rst.next()){
                 
                    Publication p = new Publication(
                    rst.getInt("id_publication"),       
                    rst.getString("titre"),
                    rst.getString("description"),
                    rst.getString("image_publication"),
                    rst.getString("date_publication"),
                    rst.getInt("id_client_publication")           
                              
                    );
                 
              items.add(p);
              
                   
             }
              
             listP.setItems(items);
        
             
                         
         listP.setCellFactory(param -> new ListCell<Publication>() {
            
            @Override
            public void updateItem(Publication name, boolean empty) {
                super.updateItem(name, empty);
         
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                  
                           
                           
                /*    try {
                       
                       
                    } catch (SQLException ex) {
                        Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                           
                           
                           FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.HAND_ALT_UP);
                           editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:45px;"
                                + "-fx-fill:#FFCCBC;"
                        );
                           
                           FontAwesomeIconView editIcon1 = new FontAwesomeIconView(FontAwesomeIcon.HAND_ALT_DOWN);
                           editIcon1.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:45px;"
                                + "-fx-fill:#FFCCBC;"
                                + "-fx-translate-y=5;"   
                                 
                        ); 
                           
                   
                           
        
                  /*  try {*/
                        Label label_nbr_like =new Label();
                        Label label_nbr_dislike =new Label();
                        ImageView imagev;
                        VBox hbox=new VBox();
                        HBox h1= new HBox();
                        HBox h2= new HBox();
                        
                         
                            Button btn = new Button("Commentaire");
                            Button btn1 = new Button("Plus");
                            
                            btn.setStyle("-fx-background-color: #42A5F5;");
                            btn1.setStyle("-fx-background-color: #42A5F5;");
                             
                            Label label =new Label();
                            Pane pane = new Pane();
                            Pane pane1 = new Pane();
                            Pane pane2 = new Pane();
                            Pane pane3 = new Pane();
                            Pane pane4=  new Pane();
                            Pane pane5=  new Pane();
                                pane1.setStyle("-fx-background-color: #BDBDBD;");
                            pane1.setMaxWidth(5);
                            pane2.setStyle("-fx-background-color: #NEVER;");
                            pane2.setMaxWidth(5);
                            pane3.setMaxWidth(20);
                            pane4.setMaxHeight(70);
                            
                            pane5.setStyle("-fx-background-color: #BDBDBD;");
                            pane5.setMaxHeight(20);
                            
                            
                            
                            btn.setMaxWidth(900);
                            btn.setMaxHeight(200);
                            btn1.setMaxWidth(1500);
                            btn1.setMaxHeight(200);
                    try {
                           hbox.setVgrow(pane,Priority.ALWAYS);
                           hbox.setVgrow(pane1,Priority.ALWAYS);
                           hbox.setVgrow(pane2,Priority.ALWAYS);
                           hbox.setVgrow(pane4,Priority.ALWAYS);
                           hbox.setVgrow(pane5,Priority.ALWAYS);
                           h1.setHgrow(pane3,Priority.ALWAYS);
                           
                           btn1.setOnAction((ActionEvent event) -> {
                            
                            
                          
                           pd=listP.getSelectionModel().getSelectedItem();
                           
                           FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Page_description_publication.fxml"));
                            try {
                                 loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            Page_description_publicationController Page = loader.getController();
                            
                               try {
                                   Page.setTextField(pd.getId(),pd.getTitre(),
                                           pd.getDescription(),pd.getDate(),showPublicationImage(pd.getTitre()),pd.getId_client());
                               } catch (SQLException ex) {
                                   Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
                               }
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });
                           
                           btn.setOnAction((ActionEvent event) -> {
                            
                            
                          
                           pd=listP.getSelectionModel().getSelectedItem();
                           //System.out.println(pd.getId());
                           FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Espace_commentaire.fxml"));
                            try {
                                 loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            Espace_commentaireController Page1 = loader.getController();
                            
                            //Page1.setTextField_Commentaire(pd.getId());
                            Page1.setTextField_Commentaire(pd.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });
                           
                           
                           
                           
                             
                            imagev = showPublicationImage(name.getTitre());
                            imagev.setFitWidth(680);
                            imagev.setFitHeight(245);
                            
                                   editIcon.setOnMouseClicked((MouseEvent event) -> {
                  
                            try {
                                 sl.ajouter_like(name.getId(),name.getId_client());
                                 System.out.println("ajout avec succes");
                                 nbr_like = sl.afficher_nombre_like(name.getId());
                                 String idS= String.valueOf(nbr_like);
                                 label_nbr_like.setText(idS);
                                 hbox.getChildren().addAll(imagev,label,pane,label_nbr_like,editIcon,btn,btn1);
                                 label.setText(name.getTitre());
                                 setGraphic(hbox);                            

                                
                                }catch (SQLException ex) {
                                       System.out.println(ex.getMessage());
                                }
                         
                        });
                                   
                                  
                            nbr_like = sl.afficher_nombre_like(name.getId());
                            String idS= String.valueOf(nbr_like);
                            label_nbr_like.setText(idS);
                            
                            nbr_dislike=sl.afficher_nombre_like(name.getId());
                            String idS1= String.valueOf(nbr_like);
                            label_nbr_dislike.setText(idS1);
                            
                            
                            //h2.setPadding(new Insets(0, 12, 15, 12)); 
                            h2.setSpacing(10);
                            
                            h1.getChildren().addAll(label_nbr_dislike,editIcon1,pane3,label_nbr_like,editIcon);
                            h2.getChildren().addAll(btn,btn1);
                            
                            
                            hbox.setMargin(label, new Insets(10, 10, 10, 10));  
                            hbox.setMargin(h1, new Insets(10,10,10,10)); 
                            hbox.setMargin(h2, new Insets(10,10, 10,10));
                            
                            
                            hbox.getChildren().addAll(label,pane4,imagev,pane1,h1,pane2,pane,pane5,h2);
                            
                            
                            label.setText(name.getTitre());
                             
                            
                                setGraphic(hbox);
                    } catch (SQLException ex) {
                        Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
                       
                        
                        
             
                    
                        
                        
                        
                        
                        
                    
                     
                     
                  
                   
                }
            }
            
            
            
            
        }
        );
                 
             
         
         
         
         
         
         
         
         
             } catch (SQLException ex) {
             Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    
    }    

    
    
    
       private ImageView  showPublicationImage(String titreP) throws SQLException{
        
        
                
          try {
               String query = "select image_publication from publication where titre=?";
               
               PreparedStatement pst = connexion.prepareStatement(query);
               pst.setString(1,titreP);
               ResultSet rst = pst.executeQuery();
               if(rst.next()){
                   
                   InputStream is =rst.getBinaryStream(1);
                   OutputStream os = new FileOutputStream(new File("photo.jpg"));
                   byte[] contents = new byte[1024];
                   int size=0;
                   while((size=is.read(contents))!=-1){
                       
                       os.write(contents,0,size);
                       
                   }
                   
                   image = new Image("file:photo.jpg",130,130,true,true);
                  // imageView.setImage(image);
                   imageView = new ImageView(image);
               }
          } catch (SQLException ex) {
              Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(Page_acceuil_publicationController.class.getName()).log(Level.SEVERE, null, ex);
          }
               
          
        return  imageView;
    }
    
}