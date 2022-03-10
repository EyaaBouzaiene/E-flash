/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javazoom.jl.decoder.JavaLayerException;
import tn.esprit.model.Produits;
import tn.esprit.service.ProduitsService;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author aders
 */
public class LoveController implements Initializable {

       
 ObservableList<Produits> list = FXCollections.observableArrayList();
 
  Connection connexion=MyDB.getInstance().getConnexion();;
   @FXML
    private ComboBox<String> Acategorie;

    @FXML
    private DatePicker Adate;

    @FXML
    private TextField Adesc;

    @FXML
    private TextField Anom;

    @FXML
    private Spinner<Integer> Aqte;

    @FXML
    private Spinner<Integer> Aqte1;

    @FXML
    private TextField Atype;
    @FXML
    private Label lnom;
   
    @FXML
    private Label ldesc;
    @FXML
    private Label ltype;
    @FXML
    private Label lqte;
    @FXML
    private Label ldate;
   
    
    @FXML
    private Label lcategorie;
   
 
    @FXML
    private Label lqte1;
    @FXML
    private TextField tnom;
    @FXML
    private Button edit;
    @FXML
    private TextField tdesq;
    @FXML
    private TextField ttype;
    @FXML
    private Spinner<Integer> tqt;
    @FXML
    private Spinner<Integer> tqt1;
     @FXML
    private TableView<Produits> tbleview;
    @FXML
    private TableColumn<Produits, Integer> id;
    @FXML
    private TableColumn<Produits, String> nom1;
    @FXML
    private TableColumn<Produits, String> desc1;
    @FXML
    private TableColumn<Produits, String> Type;
    @FXML
    private TableColumn<Produits, Integer> quantite;
    @FXML
    private TableColumn<Produits, java.util.Date> date1;
    @FXML
    private TableColumn<Produits, String> categorie1;
@FXML
    private TableColumn<Produits, Integer> quantiteR1;
  @FXML
    private TableColumn<Produits, String> delet1;
  
   @FXML
    private AnchorPane anchopane;
  
   @FXML
    private TextField path;
    @FXML
    private TextField search;
    @FXML
    private Button search1;
FileChooser fc = new FileChooser();
     String query = null;
  Produits Produits = null ;
   int index = -1;
   final int initialValue1 = 0;
   PreparedStatement pst = null;
   ProduitsService sp = new ProduitsService();
   private FileInputStream fis;
   final int initialValue = 0;
     private File file;
     @FXML
    private TextField idt;
    @FXML
    private AnchorPane ancopane1;
    @FXML
    private Button loading;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, initialValue);
        Aqte.setValueFactory(valueFactory);
        Aqte1.setValueFactory(valueFactory);
        try {
            
            Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            Statement stm;
            stm = connexion.createStatement();
            String req = "SELECT CODE,NOMCAT FROM  categories";
            ResultSet rst = stm.executeQuery(req);
            PreparedStatement statement=connexion.prepareStatement(req) ;
            ObservableList<String> list = FXCollections.observableArrayList();
            while (rst.next()) {
            list.add(rst.getString("CODE"));
            Acategorie.setItems(list);
            }
             statement.close();
            
            Image myimage= new Image(getClass().getResourceAsStream("paysage.png"));
        } catch (SQLException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       affiche();
                 
       
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
        fc.getExtensionFilters().addAll(ext1,ext2);
        
        
        
       tqt.setValueFactory(valueFactory);
        tqt1.setValueFactory(valueFactory);
    }    

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void AddProduits(ActionEvent event) {
        String value1 = Anom.getText();
            String value2 = Adesc.getText();
            Integer value3 = Aqte.getValue();
            Integer value6 = Aqte1.getValue();
            String value4 = Atype.getText();
            String value5 = Acategorie.getSelectionModel().getSelectedItem();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(Adate.getValue());
       if (/*value1.isEmpty() ||*/ value2.isEmpty() ||  value4.isEmpty() ||  value5.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
       
        }
     /* else {*/
     /*   Produits p = new Produits(Aqte.getValue(),Aqte1.getValue(),"5", Anom.getText(), Adesc.getText(), Atype.getText(),gettedDatePickerDate,Acategorie.getSelectionModel().getSelectedItem(),path.getText());
     sp.ajouterp(p);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("PRODUCT is added successfully!");
     alert.show();
     display();  */    }

    @FXML
    private void loding(ActionEvent event) {
         Stage primaryStage = (Stage)anchopane.getScene().getWindow();
         file = fc.showOpenDialog(primaryStage);
        String file1= file.getAbsolutePath();
        path.setText(file1);
    }

    @FXML
    private void EDITB(ActionEvent event) {
        
         try {
            
           /* String value1 = tnom.getText();
            String value2 = tdesq.getText();
            Integer value3 = tqt.getValue();
            Integer value6 = tqt1.getValue();
            String value4 = ttype.getText();
            String value7 = idt.getText();*/
          /*  String req = "Update produits set NOM = ?, TYPE = ?,quantite= ?,DESC= ?,quantiteR= ? where REFERENCE ='"+idt.getText()+"'";  */  
            String req10 = "Update `produits` set NOM = ?, TYPE = ?, quantite = ?,DESC= ?,quantiteR= ? where REFERENCE ='"+idt.getText()+"'"; 
                
            pst = connexion.prepareStatement(req10);
         
           
        pst.setString(1,tnom.getText());
        pst.setString(2,ttype.getText());
       // ps.setString(3,value4);
        pst.setInt(3,tqt.getValue());
        pst.setString(4,tdesq.getText());
        pst.setInt(5, tqt1.getValue());
        //ps.setBinaryStream(3, fis,file.length());
              pst.executeUpdate();

         
         System.out.println("hiiiiiiiiii"); 
           
         
       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Product is updated successfully!");
            alert.show();
            
            display();
        } catch (SQLException e) {
        }
    }

    @FXML
    private void getSelected(MouseEvent event) throws SQLException {
        
        
       
        Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
            Statement stm;
            stm = connexion.createStatement();
            String req = "SELECT categories.NOMCAT,categories.CODE FROM `produits`, `categories`  ";
            ResultSet rst = stm.executeQuery(req);
            PreparedStatement statement=connexion.prepareStatement(req) ;
            ObservableList<String> list = FXCollections.observableArrayList();
           
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       

index = tbleview.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
  idt.setText(id.getCellData(index).toString());
    tnom.setText(nom1.getCellData(index));
    tdesq.setText(desc1.getCellData(index));
    
   tqt.getValueFactory().setValue(quantite.getCellData(index));
     ttype.setText(Type.getCellData(index));
   
    
    tqt1.getValueFactory().setValue(quantiteR1.getCellData(index));
    }

     @FXML
    void handle(MouseEvent event) {
Audio audio = Audio.getInstance();
        InputStream sound = null;
        try {
        sound = audio.getAudio(search.getText(), Language.ENGLISH);
        } catch (IOException ex) {
        Logger.getLogger(ModificationProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
        audio.play(sound);
        } catch (JavaLayerException ex) {
        Logger.getLogger(ModificationProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      void affiche()
    {
          
        display();
        id.setCellValueFactory(new PropertyValueFactory<>("reference"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc1.setCellValueFactory(new PropertyValueFactory<>("desc"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        date1.setCellValueFactory(new PropertyValueFactory<>("dateP"));
        categorie1.setCellValueFactory(new PropertyValueFactory<>("CATEGORIE"));
        quantiteR1.setCellValueFactory(new PropertyValueFactory<>("quantiteR"));
        //add cell of button edit
        Callback<TableColumn<Produits, String>, TableCell<Produits, String>> cellFoctory = (TableColumn<Produits, String> param) -> {
            // make cell containing buttons
            final TableCell<Produits, String> cell = new TableCell<Produits, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                Connection connection = null ;
                                PreparedStatement preparedStatement = null ;
                                ResultSet resultSet = null ;
                                Produits = tbleview.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `produits` WHERE REFERENCE ="+Produits.getReference();
                                connection =  MyDB.getInstance().getConnexion();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                display();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(ModificationProduitController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                            
                            
                            
                        });
                        
                        
                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        
                        
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        delet1.setCellFactory(cellFoctory);
        
        
        FilteredList<Produits> filtredData = new FilteredList<>(list, b-> true);
        search.textProperty().addListener(((observable,oldValue,newValue) -> {
            
            filtredData.setPredicate(Produits-> {
                if ( newValue.isEmpty()  || newValue==null  )
                {
                    return true;
                }
                String searchword = newValue.toLowerCase();
                if(Produits.getNom().toLowerCase().indexOf(searchword) > -1 ){
                    return true;
                }
                else if(Produits.getDesc().toLowerCase().indexOf(searchword) > -1 ){
                    return true;
                }
                 else if(Produits.getCATEGORIE().toLowerCase().indexOf(searchword) > -1 ){
                    return true;
                }
                 else if(Produits.getType().toLowerCase().indexOf(searchword) > -1 ){
                    return true;
                }
                 else 
                 {
                     return false;
                 }
                     
                 
                
            });
        })
        );
           SortedList<Produits> sortedData = new SortedList<>(filtredData);
           sortedData.comparatorProperty().bind(tbleview.comparatorProperty());
         tbleview.setItems(sortedData);
              
        
        
        
    }
         
         
            void display()
    {
       try {
           Connection connexion;
           connexion = MyDB.getInstance().getConnexion();
           String req = "SELECT *,categories.NOMCAT FROM `produits`, `categories`  WHERE produits.CATEGORIE=categories.CODE ORDER BY CATEGORIE";
           PreparedStatement stm;
           stm = connexion.prepareStatement(req);
           
       list.clear();
           //ensemble de resultat
           ResultSet rst = stm.executeQuery(req);
           while(rst.next())
           {
               list.add(new Produits(rst.getInt("reference"),
                       rst.getInt("quantite"),
                       rst.getInt("quantiteR"),
                       rst.getString("rate"),
                       rst.getString("nom"),
                       rst.getString("desc"),
                       rst.getString("type"),
                       rst.getDate("dateP"),
                       rst.getString("NOMCAT"),
                       rst.getString("IMAGE")
                       
               )
               
              
                );
                 tbleview.setItems(list);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ModificationProduitController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

}
