/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import workshopjava3a1.MyListener;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.and;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import tn.esprit.model.Produits;
import tn.esprit.service.ProduitsService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import tn.esprit.service.CathegorieService;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author aders
 */
public class FrontController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private GridPane grid;
private List<Produits> produits = new ArrayList<>();
  private Image image;
    ProduitsService p = new ProduitsService();
     private MyListener myListener;
    // private List<Produits> fruits ;
    private HBox hbox;
     private VBox hbox1;
     private MyListener myListener1;
    private ImageView imageview;
    @FXML
    private VBox vBox;
    private List<Produits> produits2 = new ArrayList<>();
    @FXML
    private TextField search;
    @FXML
    private Spinner<Integer> spinner;
    //private MyListener myListener;
    /**
     * Initializes the controller class.
     */
     private String username;
 final int initialValue = 0;
    @FXML
    private Button addbutton;
    
     
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
      private void showPublicationImage2(String titreP) throws SQLException{
       
       
               
          try {
               String query = "select IMAGE from produits where NOM=?";
                 Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
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
                   
                   image = new Image("file:photo.jpg",100,100,true,true);
                   fruitImg.setImage(image);
               }
          } catch (SQLException ex) {
              Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
               
         
       
    }

 private void setChosenFruit(Produits fruit) throws SQLException {


 fruitNameLable.setText(fruit.getNom());
//       // fruitPriceLabel.setText(WorkshopJava3a1.CURRENCY + fruit.getPrice());
       ImageView imagev = new ImageView();
     showPublicationImage2(fruit.getNom());
       
       fruitPriceLabel.setText(WorkshopJava3a1.CURRENCY + fruit.getPrix());
       
        String query = "select quantiteR  from produits where NOM='" +fruit.getNom()+"'";
                 Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
               PreparedStatement pst = connexion.prepareStatement(query);
               ResultSet rst = pst.executeQuery();
               while(rst.next())
               {
                   spinner.getValueFactory().setValue(rst.getInt("quantiteR"));
               }
       
        /*chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
                "    -fx-background-radius: 30;");*/
    }

 
 
 public List<Produits> afficherproduit() throws SQLException {
     Connection connexion;
connexion = MyDB.getInstance().getConnexion();
            Statement stm;
        List<Produits> produit = new ArrayList<>();
         CathegorieService cp = new CathegorieService();
        String req = "SELECT *,categories.NOMCAT FROM `produits`, `categories`  WHERE produits.CATEGORIE=categories.CODE ORDER BY CATEGORIE";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produits s;
            s = new Produits(rst.getInt("reference"),
                    rst.getInt("quantite"),
                     rst.getInt("quantiteR"),
                    rst.getString("rate"),
                    rst.getString("nom"),
                    rst.getString("desc"),
                    rst.getString("type"),
                    rst.getDate("dateP"),
                    rst.getString("NOMCAT"),
                    rst.getString("IMAGE")
            );
            produit.add(s);
        }
        return produit;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        
         SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue);
        spinner.setValueFactory(valueFactory);

        try {
             
            ProduitsService tt = new ProduitsService();
            List<Produits> ltt = tt.afficherproduit();
           
            

                            myListener = new MyListener() {
                @Override
                public void onClickListener(Produits fruit) {
                  
                    try {
                       
                        setChosenFruit(fruit);
                       
                    } catch (SQLException ex) {
                        
                    }

                }
            };
         
            int nbr= 0;           
            int column = 0;
        int row = 1;          
            for (int i=0;i<ltt.size();i++)
            {
                 //imagev2=showPublicationImage(ltt.get(i).getNom());
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("jojo.fxml"));
                    AnchorPane anchorPane  = loader.load();
                    
                  JojoController dac = (JojoController) loader.getController();
                    System.out.println(i);
                    dac.afficher2(ltt.get(i),myListener);
                     if (column == 3) {
                    column = 0;
                    row++;
                }
                     
                     
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                    
          
                } catch (IOException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
         //addbutton.setUserData(ProductName);
           addbutton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 try {
                     
                     
                     
                      Connection connexion;
connexion = MyDB.getInstance().getConnexion();
            Statement stm;
        List<Produits> produit = new ArrayList<>();
         CathegorieService cp = new CathegorieService();
        String req = "UPDATE `produits` SET `etat`='achete' WHERE NOM='"+fruitNameLable.getText()+"'";
          stm = connexion.createStatement();
        stm.executeUpdate(req);
                   String req1 = "Select  REFERENCE from `produits`  WHERE NOM='"+fruitNameLable.getText()+"'";
                   stm= connexion.createStatement();
      
         ResultSet rst = stm.executeQuery(req1);
         int id = 0;
         while(rst.next())
                   {
                       id=rst.getInt(1);
                   }
                     System.out.println(id);
                     
                     
                     CartController.id=id;
                     
          FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Cart.fxml"));
                            try {
                                 loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                            CartController Page1 = loader.getController();
                           
                            //Page1.setTextField_Commentaire(pd.getId());
                           
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
         
//                     FXMLLoader shoppingCartPage = new FXMLLoader(getClass().getResource("Cart.fxml"));
//                     Parent shoppingCartParent = (Parent) shoppingCartPage.load();
//                     Scene shoppingCartScene = new Scene(shoppingCartParent);
//                     Stage window = (Stage) ((Node) (event.getSource())).getScene().getWindow();
//                     
//                    
//                     
//                     window.setScene(shoppingCartScene);
//                     window.show();
                      
                 
                 
                 
                 }  catch (SQLException ex) {
                     Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                   
                   
                   
             }

         
    });
                   }

    @FXML
    private void clicked(MouseEvent event) {
        
        
  }
    
    
    
  
            
    }

            // TODO
 
