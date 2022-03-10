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
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
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
import tn.esprit.model.Clients;
import tn.esprit.model.Commande;
import tn.esprit.model.Produits;
import tn.esprit.service.ClientsService;
import tn.esprit.service.Mail;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author aders
 */
public class CartController implements Initializable {

    private VBox cartPane;
    @FXML
    private ListView<Commande> listp;
    Produits pd = null;
    public static int id;
    ImageView fruitImg;
    Image image;
    private ObservableList<Commande> produit = FXCollections.observableArrayList();
Button btn = new Button("Confirmer");
            TextField t= new TextField();
           
    /**
     * Initializes the controller class.
     */
    private boolean validatenum() throws SQLException{
Pattern p=Pattern.compile("[a-zA-Z]+");
Matcher m =p.matcher(t.getText());
        if(m.find()&& m.group().equals(t.getText())){
              
        String req = " SELECT ( SUM(total)-SUM(total)*0.4 ) AS TOTALS  FROM `clients`,`commande` where commande.IDClient = clients.REfC ";
        
         Connection connexion;
connexion = MyDB.getInstance().getConnexion();
 Statement stm;
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
            while (rst.next())
            {
                
                Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("validation ID");
        alert.setHeaderText(null);
        alert.setContentText("CONGRATULATION YOUR ORDER HAS BEEN REDUCED TO '"+rst.getDouble("TOTALS")+"'+");
        alert.showAndWait();
            }
            
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
                .filter(i -> (i <= 3|| i >= 4) && (i <= 5 || i >= 6))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println("code:" + generatedString);

        return generatedString;
    }
    
    
     void display() throws SQLException
    {
       produit.clear();
         System.out.println(id);

            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            Statement stm;
            String req = "select *,produits.NOM FROM `produits`, `commande`  WHERE produits.REFERENCE=commande.REFERENCEP";

            stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {

                Commande c = new Commande(
                        rst.getInt("idCommande"),
                        rst.getString("Prix"),
                        rst.getString("DATE"),
                        rst.getInt("REFERENCEP"),
                        rst.getInt("refcmd"),
                         rst.getInt("IDClient"),
                        rst.getString("IMAGE"),
                        rst.getDouble("total"),
                        rst.getInt("quantiteA")
                        
                );

                produit.add(c);

            }

            listp.setItems(produit);
           }
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
 t.setStyle("-fx-margin-botton: 2em;");
            display();
            listp.setCellFactory((ListView<Commande> param) -> new ListCell<Commande>() {

                @Override
                public void updateItem(Commande name, boolean empty) {
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
listp.setStyle("-fx-control-inner-background:#E3EAE9;");
btn.setOnMouseClicked((MouseEvent event) -> {
    
    
    
});
cancel.setOnMouseClicked((MouseEvent event) -> {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Confirm");
    alert.setHeaderText("vous voulez suprimer?");
    alert.setContentText("suppression avec succes");
    
    Optional<ButtonType> option = alert.showAndWait();
    
    if (option.get() == null  || option.get() == ButtonType.CANCEL) {
        // закрываешь программу
    } else if (option.get() == ButtonType.OK) {
        try {
            Connection connection = null ;
            PreparedStatement preparedStatement = null ;
            ResultSet resultSet = null ;
            Commande Produits =null;
            Produits = listp.getSelectionModel().getSelectedItem();
            String query = null;
            query = "DELETE FROM `commande` WHERE idCommande ="+Produits.getIdCom();
            connection =  MyDB.getInstance().getConnexion();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            
           
            listp.refresh();
            display();
            
            
            
            //loadDataFromDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
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
btn.setOnMouseClicked((MouseEvent event) -> {
    try {
        // ici je suppose que retour est un composant (button, textField, etc) annoté avec @FXML
        validatenum();
        
    }                           catch (SQLException ex) {
        Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
    }
});
ImageView imagev;
HBox hbox = new HBox();
Button btn1 = new Button("Plus");
Label label = new Label();
Label label2 = new Label();
Label label3 = new Label();
Label label4 = new Label();
Label label5 = new Label();
Label label6 = new Label();
Label label7 = new Label();
Label label8 = new Label();
VBox v1 = new VBox();
Pane pane = new Pane();
Pane pane1 = new Pane();
//pane.setStyle("-fx-background-color: #FFFFF");
pane.setMaxWidth(2);
//                        btn.setMaxWidth(100);
//                        btn.setMaxHeight(200);
//                        btn1.setMaxWidth(150);
btn.setLayoutX(50);
btn.setLayoutY(40);
String id= String.valueOf(name.getIdCom());
String id2= String.valueOf(name.getRefcmd());
String id3= String.valueOf(name.getREFERENCEP());
String id4= String.valueOf(name.getRefclient());
String id5= String.valueOf(name.getQuantiteA());
String id6= String.valueOf(name.getTotal());
btn1.setMaxHeight(200);
HBox.setHgrow(pane, Priority.ALWAYS);
label.setText(id);
label2.setText(name.getPrix());
label3.setText(name.getDate());
label4.setText(id2);
label5.setText(id3);
label6.setText(id4);
label7.setText(id5);
label8.setText(id6);
Separator separator1 = new Separator();
separator1.setOrientation(Orientation.VERTICAL);
Separator separator2 = new Separator();
separator2.setOrientation(Orientation.HORIZONTAL);
label.setFont(javafx.scene.text.Font.font("Arial", 24));
label2.setFont(javafx.scene.text.Font.font("Arial", 24));
label3.setFont(javafx.scene.text.Font.font("Arial", 24));
label4.setFont(javafx.scene.text.Font.font("Arial", 24));
label5.setFont(javafx.scene.text.Font.font("Arial", 24));
label6.setFont(javafx.scene.text.Font.font("Arial", 24));
label7.setFont(javafx.scene.text.Font.font("Arial", 24));
label8.setFont(javafx.scene.text.Font.font("Arial", 24));
HBox hbox1 = new HBox();
HBox.setHgrow(pane,Priority.ALWAYS);
HBox.setHgrow(pane,Priority.ALWAYS);
HBox.setHgrow(separator1,Priority.ALWAYS);
HBox.setHgrow(separator2,Priority.ALWAYS);
hbox.setSpacing(75);
ClientsService c;
try {
    v1.getChildren().addAll(cancel,return1);
    v1.getChildren().addAll( pane,t,btn,pane1);
    imagev = showPublicationImage(name.getREFERENCEP());
    
//    hbox1.getChildren().addAll(pane,t,btn,pane1);
//    setGraphic(hbox1);
hbox.getChildren().addAll(imagev, label ,label2,label3,label4,label5,label6,label7,label8,v1);
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

    private ImageView showPublicationImage(int titreP) throws SQLException {

        try {

            Connection connexion;
            String req = "SELECT IMAGE FROM produits where REFERENCE=?";
            connexion = MyDB.getInstance().getConnexion();
            Statement stm;
            PreparedStatement pst = connexion.prepareStatement(req);
            pst.setInt(1, titreP);
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
public   void afficher_clientfidele() throws SQLException {
        
    
         
        String req = " SELECT * FROM `clients`,`commande` GROUP BY  commande.IDClient  HAVING COUNT(commande.IDClient)>3   AND TIMESTAMPDIFF(month,MIN(commande.date),MAX(commande.date))>=2";
        
         Connection connexion;
connexion = MyDB.getInstance().getConnexion();
 Statement stm;
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        //System.out.println("hello");
        while (rst.next()) {
            
            Mail.send(
                    "flashelectro06@gmail.com",
                    "dhia1881",
                   "aderssadhia@gmail.com",
                   "CONGRATULATION !!!!! ",
            "Congratulation  you are now elected as VIP CLIENT  This is your promo Code : "
            );
            
           
            
        }
        
        
    }
    
    
}
