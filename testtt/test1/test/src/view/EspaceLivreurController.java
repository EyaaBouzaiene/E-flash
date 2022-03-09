/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.JDBCType;
import static java.sql.JDBCType.NULL;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import model.Livraison;
import org.controlsfx.control.Notifications;
import service.LivraisonService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class EspaceLivreurController implements Initializable {
      Connection connexion =MyDB.getInstance().getConnexion();
      ObservableList<Livraison> list = FXCollections.observableArrayList();
      LivraisonService L = new LivraisonService();
      Statement stm;
    @FXML
    private Button boutton_mail;
    @FXML
    private Button exit;
    @FXML
    private Button boutton_stat;
    @FXML
    private Button rating;
    @FXML
    private Button espaceLivreur;
    @FXML
    private TableView<Livraison> table_livreur;
    @FXML
    private TextField idLivreur;
    @FXML
    private TableColumn<Livraison, Integer> col_cmd;
    @FXML
    private TableColumn<Livraison, Integer> col_prix;
    @FXML
    private TableColumn<Livraison, Integer> col_ville;
    @FXML
    private TableColumn<Livraison, Integer> col_etat;
    @FXML
    private FontAwesomeIconView SMS;
    @FXML
    private FontAwesomeIconView mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void tomail(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void gostat(ActionEvent event) {
    }

    @FXML
    private void torate(ActionEvent event) {
    }

    @FXML
    private void toespace(ActionEvent event) {
    }

    

    @FXML
    private void livreurshow(KeyEvent event) {
         try {
              String id = idLivreur.getText();
              Statement stm = connexion.createStatement();
              
             String req1 = "SELECT * FROM  livraison where idLivreur='"+id+"' ";
            
            PreparedStatement statement1=connexion.prepareStatement(req1) ;
            ResultSet rst = statement1.executeQuery();
            while (rst.next()) {
           list.add(new Livraison(rst.getInt("idCommande"),//or rst.getInt(1)
                    
                   
                    rst.getInt("prix"),
                    rst.getString("ville"),
                    rst.getString("etat")
            ));
            }
          } catch (SQLException ex) {
              Logger.getLogger(EspaceLivreurController.class.getName()).log(Level.SEVERE, null, ex);
          }
       
            col_cmd.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
             
                            
                        
            table_livreur.setItems(list);
        
    }
    int index=-1;
    @FXML
    private void select(MouseEvent event) throws SQLException {
        index = table_livreur.getSelectionModel().getSelectedIndex();
        if(index<= -1)
        {
            return;
        }
            
            int cmd=col_cmd.getCellData(index);
            System.out.println(cmd);
            /*String req = "delete from livraison where  `idCommande`='"+cmd+"'";
        stm = connexion.createStatement();
        stm.executeUpdate(req);*/
            
         String req = "UPDATE livraison set etat='validé' where `idCommande`="+cmd+"   ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        Notifications notificationbuilder =  Notifications.create()
                .title("Livraison ")
                .text("la livraison est parfaitement livré")
                 .graphic(null)
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.TOP_RIGHT)
                  .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("click on notification");
                      }
                    }
                  );
       notificationbuilder.showConfirm();
                        
                        
    }

    @FXML
    private void sendsms(MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/sendSMS.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void tomail(MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/sendMail.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
}
