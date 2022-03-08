/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.model.Produits;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author aders
 */
public class CartController implements Initializable {

    private VBox cartPane;
    @FXML
    private ListView<Produits> listp;
    Produits pd = null;
    public static int id;
    ImageView fruitImg;
    Image image;
    private ObservableList<Produits> produit = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            System.out.println(id);

            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            Statement stm;
            String req = "select *,categories.NOMCAT FROM `produits`, `categories`  WHERE produits.CATEGORIE=categories.CODE  AND  REFERENCE='" + id + "'";

            stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {

                Produits p = new Produits(
                        rst.getInt("REFERENCE"),
                        rst.getString("NOM"),
                        rst.getString("DESC"),
                        rst.getString("TYPE"),
                        rst.getString("NOMCAT"),
                        rst.getString("IMAGE"),
                        rst.getDouble("Prix")
                );

                produit.add(p);

            }

            listp.setItems(produit);

            listp.setCellFactory((ListView<Produits> param) -> new ListCell<Produits>() {

                @Override
                public void updateItem(Produits name, boolean empty) {
                    super.updateItem(name, empty);

                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {

                        //FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.HAND_ALT_UP);
                     FontAwesomeIconView return1 = new FontAwesomeIconView(FontAwesomeIcon.FAST_BACKWARD);
                     FontAwesomeIconView cancel = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
//                        editIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#00E676;"
//                        );
                        cancel.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:41px;"
                                + "-fx-fill:#00E676;"
                        );
                         return1.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:41px;"
                                + "-fx-fill:#00E676;"
                        );
                
                         listp.setStyle("-fx-control-inner-background: blue;");
                          
                         
                         cancel.setOnMouseClicked((MouseEvent event) -> {
                             // ici je suppose que retour est un composant (button, textField, etc) annoté avec @FXML
                             
                             listp.getItems().clear();
                        });
                        return1.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                // ici je suppose que retour est un composant (button, textField, etc) annoté avec @FXML
                                
                                FXMLLoader shoppingCartPage = new FXMLLoader(getClass().getResource("front.fxml"));
                                Parent FrontController = (Parent) shoppingCartPage.load();
                                Scene shoppingCartScene = new Scene(FrontController);
                                Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                                
                                
                                
                                window.setScene(shoppingCartScene); 
                                window.show();
                            } catch (IOException ex) {
                                Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        ImageView imagev;
                        HBox hbox = new HBox();
                        Button btn = new Button("Confirmer");
                        Button btn1 = new Button("Plus");
                        Label label = new Label();
                        Label label2 = new Label();
                        Label label3 = new Label();
                        Label label4 = new Label();
                           VBox v1 = new VBox();              
                        Pane pane = new Pane();
                               Pane pane1 = new Pane();
                          pane.setStyle("-fx-background-color: #B3E5FC");
                          pane.setMaxWidth(2);
//                        btn.setMaxWidth(100);
//                        btn.setMaxHeight(200);
//                        btn1.setMaxWidth(150);
                        btn1.setMaxHeight(200);
                        hbox.setHgrow(pane, Priority.ALWAYS);
                        label.setText(name.getNom());
                         label2.setText(name.getCATEGORIE());
                          label3.setText(name.getDesc());
                           label4.setText(name.getType());
                              
                           label.setFont(javafx.scene.text.Font.font("Arial", 24));
                           label2.setFont(javafx.scene.text.Font.font("Arial", 24));
                           label3.setFont(javafx.scene.text.Font.font("Arial", 24));
                           label4.setFont(javafx.scene.text.Font.font("Arial", 24));
                           
                          hbox.setHgrow(pane,Priority.ALWAYS);
                          hbox.setHgrow(pane1,Priority.ALWAYS);
                          hbox.setSpacing(75);
          
                            

                        try {
                            v1.getChildren().addAll(cancel,return1);
                            imagev = showPublicationImage(name.getNom());
                            hbox.getChildren().addAll(imagev, label ,label2,label3,label4,v1 );
                            setGraphic(hbox);
                        } catch (SQLException ex) {
                            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    private ImageView showPublicationImage(String titreP) throws SQLException {

        try {

            Connection connexion;
            String req = "SELECT IMAGE FROM produits where NOM=?";
            connexion = MyDB.getInstance().getConnexion();
            Statement stm;
            PreparedStatement pst = connexion.prepareStatement(req);
            pst.setString(1, titreP);
            stm = connexion.createStatement();

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                InputStream is = rst.getBinaryStream(1);
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] contents = new byte[1024];
                int size = 0;
                while ((size = is.read(contents)) != -1) {

                    os.write(contents, 0, size);

                }

                image = new Image("file:photo.jpg", 131, 211, true, true);
                fruitImg = new ImageView(image);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JojoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fruitImg;

    }

}
