/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import com.gluonhq.impl.charm.a.b.b.i;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import tn.esprit.model.Produits;
import tn.esprit.utils.MyDB;
import workshopjava3a1.MyListener;

/**
 * FXML Controller class
 *
 * @author aders
 */
public class JojoController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private HBox hb;
    @FXML
    private ImageView img;
 private Image image;
  private ImageView imageview;
    @FXML
    private Label prixl;
    /**
     * Initializes the controller class.
     */
    private MyListener myListener;
    private Produits fruit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
     @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(fruit);
    }
    
     private ImageView showPublicationImage(String titreP) throws IOException, SQLException {
       
       
               
          try {
              
               Connection connexion;
                String req = "SELECT IMAGE FROM produits where NOM=?";
            connexion = MyDB.getInstance().getConnexion();
            Statement stm;
            PreparedStatement pst = connexion.prepareStatement(req);
               pst.setString(1,titreP);
            stm = connexion.createStatement();
           
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                 InputStream is =rst.getBinaryStream(1);
                   OutputStream os = new FileOutputStream(new File("photo.jpg"));
                   byte[] contents = new byte[1024];
                   int size=0;
                   while((size=is.read(contents))!=-1){
                       
                       os.write(contents,0,size);
                       
                   }
                   
                   
                image = new Image("file:photo.jpg",131,211,true,true);
                   imageview=new ImageView(image);
            }
             
          } catch (SQLException ex) {
              Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        
          } catch (FileNotFoundException ex) {   
            Logger.getLogger(JojoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageview;
         
       
    }
     public void afficher2 (Produits p , MyListener myListener ) throws SQLException, IOException{
          this.fruit = p;
        this.myListener = myListener;
         ImageView imagev = new ImageView();
                imagev  = showPublicationImage(p.getNom());

   img.setImage(imagev.getImage());
        this.label.setText(p.getNom());
      
       prixl.setText(WorkshopJava3a1.CURRENCY + p.getPrix());
    }

    @FXML
    private void onClickListener(MouseEvent event) {
    }

  
  

    
}
