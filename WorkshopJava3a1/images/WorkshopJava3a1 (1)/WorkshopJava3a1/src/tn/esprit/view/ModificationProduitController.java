/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tn.esprit.model.Produits;
import tn.esprit.service.ProduitsService;
import tn.esprit.utils.MyDB;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static java.awt.SystemColor.text;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import javazoom.jl.decoder.JavaLayerException;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author aders
 */
public class ModificationProduitController implements Initializable {
   @FXML
    private TableView<Produits> tbleview;
    @FXML
    private TableColumn<Produits, Integer> id;
    @FXML
    private TableColumn<Produits, String> nom;
    @FXML
    private TableColumn<Produits, String> desc;
    @FXML
    private TableColumn<Produits, String> Type;
    @FXML
    private TableColumn<Produits, Integer> quantite;
    @FXML
    private TableColumn<Produits, Date> date;
    @FXML
    private TableColumn<Produits, String> categorie;
@FXML
    private TableColumn<Produits, Integer> quantiteR;
  @FXML
    private TableColumn<Produits, String> delet1;
  
    @FXML
    private TextField search;
  @FXML
    private ComboBox<String> tcategorie;

    @FXML
    private DatePicker tdate;

    @FXML
    private TextField tdesq;

    @FXML
    private TextField tnom;

    @FXML
    private Spinner<Integer> tqt;

    @FXML
    private Spinner<Integer> tqt1;

    @FXML
    private TextField ttype;
  String query = null;
  Produits Produits = null ;
   int index = -1;
   final int initialValue = 0;
   PreparedStatement pst = null;
    /**
     * Initializes the controller class.
     */
    ProduitsService ps = new ProduitsService();
    PreparedStatement pst5 = null;

    ObservableList<Produits> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affiche();
    }
      @FXML
    private Button edit;
      @FXML
    void EditA(ActionEvent event) {
   try {
            
            String value1 = tnom.getText();
            String value2 = tdesq.getText();
            Integer value3 = tqt.getValue();
            Integer value6 = tqt1.getValue();
            String value4 = ttype.getText();
            String value5 = tcategorie.getSelectionModel().getSelectedItem();
            String req = "UPDATE `produits` SET `NOM`='"+value1+"',`TYPE`='"+value4+"',`quantite`='"+value3+"',`DESC`='"+value2+"',`quantiteR`='"+value6+"'";
                   
             Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
           
          pst5 =  connexion.prepareStatement(req);
              pst5.execute();
          //display();
            JOptionPane.showMessageDialog(null, "Update");
            display();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML
    void getSelected(MouseEvent event) throws SQLException {
         SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue);
       tqt.setValueFactory(valueFactory);
        tqt1.setValueFactory(valueFactory);
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
  
    tnom.setText(nom.getCellData(index));
    tdesq.setText(desc.getCellData(index));
     while (rst.next()) {
            list.add(rst.getString("CODE"));
            tcategorie.setItems(list);
             tcategorie.getSelectionModel().select(categorie.getCellData(index).toString());
            }
  
   tqt.getValueFactory().setValue(quantite.getCellData(index));
     ttype.setText(Type.getCellData(index));
   
    
    tqt1.getValueFactory().setValue(quantiteR.getCellData(index));
    
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
    void affiche()
    {
          
        display();
        id.setCellValueFactory(new PropertyValueFactory<>("reference"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateP"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("CATEGORIE"));
        quantiteR.setCellValueFactory(new PropertyValueFactory<>("quantiteR"));
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
    @FXML
    void handle(ActionEvent event) {
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
    }
    
    

