/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author EXTRA
 */
public class Page_description_publicationController implements Initializable {

    @FXML
    private TextArea textA_description;
    @FXML
    private Label label_titre;
    int PublicationId;
    @FXML
    private Label label_date;
    @FXML
    private ImageView imageViewD;
    @FXML
    private Label label_client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    
      void setTextField(int id, String titre,String descrip,String date,ImageView i,int id_c) {

        PublicationId = id;
        textA_description.setText(descrip);
        label_titre.setText(titre);
        label_date.setText(date);
        imageViewD.setImage(i.getImage());
         String idS= String.valueOf(id_c);
        label_client.setText(idS);

    }
    
}
