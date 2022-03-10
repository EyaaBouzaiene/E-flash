/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import com.teknikindustries.bulksms.SMS;
import workshopjava3a1.MyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.Random;
import static javafx.beans.binding.Bindings.and;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Alert;
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
import org.controlsfx.control.Rating;
import tn.esprit.model.Clients;
import tn.esprit.model.Commande;

import tn.esprit.service.CathegorieService;
import tn.esprit.service.CommandeService;
import tn.esprit.service.Mail;
import tn.esprit.service.sms;
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
    @FXML
    private Rating rating1;

    CommandeService sp;
     Connection connexion= MyDB.getInstance().getConnexion();
    @FXML
    private ImageView panier;
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
       
       fruitPriceLabel.setText(fruit.getPrix().toString());
       
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

 public void ajouterC() throws SQLException {
        
        List<Produits> produit = new ArrayList<>();
         Connection connexion;
connexion = MyDB.getInstance().getConnexion();
            Statement stm;
        
//    String req = "INSERT INTO `clients`(`NOM`,`Prenom`, `QTA`, `IDP`,`DATEA`) VALUES ('" + p.getNom() + "','" + p.getPrenom()+ "','" + p.getQuantiteA()+ "','" + p.getIDP()+ "','"  + p.getDateA() + "') ";   
//        stm = connexion.createStatement();            
//        stm.executeUpdate(req); 
       String req2=" select produits.quantiteR,commande.quantiteA from produits,commande where commande.REFERENCEP=produits.REFERENCE";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req2);
       if (rst.next())
       {
           
       
        if(rst.getInt("quantiteR")>rst.getInt("quantiteA"))
        {
             String req3="UPDATE `produits` INNER JOIN  `commande` ON  produits.REFERENCE=commande.REFERENCEP SET quantiteR=`quantiteR`-`quantiteA`";
        stm = connexion.createStatement();
        stm.executeUpdate(req3);
        }
        else if (rst.getInt("quantiteR")<rst.getInt("quantiteA"))
        {
       
        String req5="UPDATE `produits` INNER JOIN  `commande` ON  produits.REFERENCE=commande.REFERENCEP SET commande.quantiteA=produits.quantiteR";
        stm = connexion.createStatement();
        stm.executeUpdate(req5);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Echec");
            alert.setContentText("La quantite Restante est INSUFFISANTE ! ");
            alert.show();
        //System.err.println("La quantite Restante est INSUFFISANTE ");
         String req4="UPDATE `produits` INNER JOIN  `commande` ON  produits.REFERENCE=commande.REFERENCEP SET quantiteR= produits.quantite ";
        stm = connexion.createStatement();
        stm.executeUpdate(req4);
        
         
        } 
        else {
             
           String req3="UPDATE `produits` INNER JOIN  `commande` ON  produits.REFERENCE=commande.REFERENCEP  SET quantiteR=`quantiteR`-`quantiteA`";
        stm = connexion.createStatement();
        stm.executeUpdate(req3); 
        //System.err.println("La quantite Restante est " +rst.getInt("quantiteR"));
         
         String req4="UPDATE `produits` INNER JOIN  `commande` ON  produits.REFERENCE=commande.REFERENCEP  SET quantiteR=`quantite`";
        stm = connexion.createStatement();
        stm.executeUpdate(req4); 
          
        
           
        }
       }
            
        
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
//           String req10 = " SELECT * FROM `clients`,`commande` GROUP BY  commande.IDClient  HAVING COUNT(commande.IDClient)>3   AND TIMESTAMPDIFF(month,MIN(commande.date),MAX(commande.date))>=2";
//        
//        
//connexion = MyDB.getInstance().getConnexion();
// 
//        try {
//            Statement stm;
//            stm = connexion.createStatement();
//                    ResultSet rst2 = stm.executeQuery(req10);
//        //System.out.println("hello");
//        while (rst2.next()) {
//            
//            Mail.send(
//                    "flashelectro06@gmail.com",
//                    "dhia1881",
//                   "aderssadhia@gmail.com",
//                   "CONGRATULATION !!!!! ",
//            "Congratulation  you are now elected as VIP CLIENT"
//            );
//            
//           
//            
//        }
//        } catch (SQLException ex) {
//            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //ensemble de resultat

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
                     
                       Statement stm;
                       Statement stm2;
                       Statement stm3;
                      String req5 = "UPDATE `produits` SET `rate`= '"+rating1.getRating()+"' WHERE NOM='"+fruitNameLable.getText()+"' ";
  

     stm2 = connexion.createStatement();
        stm2.executeUpdate(req5);
        
        afficher_clientfidele();
//       
        
        
                    
          
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
                     DateFormat format= new SimpleDateFormat("yyyy/MM/dd");
                     Date date = new Date();
                     
                     
                     

      
       String req9 = "INSERT INTO `commande` (`refcmd`, `prix`,`date`,`REFERENCEP`,IDClient,quantiteA,total) VALUES (?,?,?,?,?,?,?)";
      
            connexion = MyDB.getInstance().getConnexion();
        PreparedStatement ps = connexion.prepareStatement(req9);
        ps.setString(1,"1234");
        ps.setString(2,fruitPriceLabel.getText());
         ps.setString(3,format.format(date));
          ps.setInt(4,id);
           ps.setInt(5,154);
           ps.setInt(6,spinner.getValue());
           ps.setDouble(7,0);
           
           
         
        ps.executeUpdate();
        
         String req3 = "UPDATE `commande` SET `total`= `quantiteA`*`prix`";
          stm = connexion.createStatement();
        stm.executeUpdate(req3);
        
        
           ajouterC();
                           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("PRODUCT is added successfully!");
            alert.show();
   
          
         

                      
                 
                 
                 
                 }  catch (SQLException ex) {
                     Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                   
                   
                   
             }

         
    });

//           
           
                   }

 

    @FXML
    private void rateclicked(MouseEvent event) throws SQLException {
       
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
            
//            Mail.send(
//                    "flashelectro06@gmail.com",
//                    "dhia1881",
//                   "aderssadhia@gmail.com",
//                   "CONGRATULATION !!!!! ",
//            "Congratulation '"+rst.getString("NOM")+"' you are now elected as VIP CLIENT This is your promo code = '"+generate(6)+"' "
//            );
            
            sms s = new sms();
            s.send("Congratulation '"+rst.getString("NOM")+"' you are now elected as VIP CLIENT This is your promo code = '"+generate(rst.getInt("REfC"))+"' ");
            
             String req10 = "UPDATE `clients` SET `COUPON`= '"+generate(6)+"' where REfC='"+rst.getInt("REfC")+"'  ";
          stm = connexion.createStatement();
        stm.executeUpdate(req10);
           
            
        }
        
        
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
    @FXML
    private void makeclicked(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Cart.fxml"));
                            try {
                                 loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                            CartController Page1 = loader.getController();
                           
                            //Page1.setTextField_Commentaire(pd.getId());
                            
                           /* sms s=new sms();
                            s.sms("helllo baby");*/
                            
                           
                           
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }

    @FXML
    private void clicked(MouseEvent event) {
    }
    
  
            
    }

            // TODO
 
