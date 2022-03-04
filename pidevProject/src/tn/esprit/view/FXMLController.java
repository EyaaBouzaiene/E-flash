package tn.esprit.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
import java.awt.Desktop;
import static java.awt.SystemColor.text;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.load;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.fxml.FXMLLoader.load;
import javafx.geometry.Bounds;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import javazoom.jl.decoder.JavaLayerException;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Rating;
import tn.esprit.model.Cathegorie;

public class FXMLController implements Initializable {
    
       

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
    private TextField search;
    @FXML
    private Button search1;
       @FXML
    private TextField path;
 
    //private Button browse;
    private File file;
    private final Desktop desktop = Desktop.getDesktop();
    
    private TextArea textArea;
    
    private Image image;
     @FXML
    private ImageView imageView;

    private FileInputStream fis;
    private Button exportToXL, importXLToDB;

   final int initialValue = 0;
    Image myimage= new Image(getClass().getResourceAsStream("paysage.png"));
    @FXML
    private AnchorPane anchopane;
FileChooser fc = new FileChooser();
     String query = null;
  Produits Produits = null ;
   int index = -1;
   final int initialValue1 = 0;
   PreparedStatement pst = null;
   ProduitsService ps = new ProduitsService();
 @FXML
    private TextField idt;

    @FXML
    private AnchorPane ancopane1;
    ObservableList<Produits> list = FXCollections.observableArrayList();
    @FXML
    private Button loading;
    @FXML
    private TableColumn<Produits, Image> IMAGE;
    @FXML
    private AnchorPane ancopane11;
    @FXML
    private PieChart produitchart;
    @FXML
    private BarChart<String, Number> linechart;
    
    ObservableList<PieChart.Data> list1 = FXCollections.observableArrayList();
    @FXML
    private Label pielabel;
    @FXML
    private StackedBarChart<String, Number> stackedbar;
    @FXML
    private AnchorPane ancopane111;
    @FXML
    private Label pielabel1;
    @FXML
    private Rating rating;
    @FXML
    private Rating rating1;
    @FXML
    private TableColumn<Produits, Double> trating;
    @FXML
    private Button bexport;
   
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
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
                
                //Image myimage= new Image(getClass().getResourceAsStream("paysage.png"));
            } catch (SQLException ex) {
                Logger.getLogger( FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            affiche();
            
            
            FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
            fc.getExtensionFilters().addAll(ext1,ext2);
            
            Connection connexion2;
            connexion2 = MyDB.getInstance().getConnexion();
            Statement stm;
            stm = connexion2.createStatement();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
             XYChart.Series<String, Number> series2 = new XYChart.Series<>();
            String req = "SELECT NOM,quantite from produits";
                ResultSet rst = stm.executeQuery(req);
                while (rst.next())
                {
                    list1.add(new PieChart.Data(rst.getString("NOM"),rst.getInt("quantite")));
                 series.getData().add(new XYChart.Data<>(rst.getString("NOM"),rst.getInt("quantite")));
                  series2.getData().add(new XYChart.Data<>(rst.getString("NOM"),rst.getInt("quantite")));
                  
                }
            linechart.getData().add(series);
            stackedbar.getData().add(series2);
        produitchart.setData(list1);
          produitchart.setLabelLineLength(10);
produitchart.setLegendSide(Side.LEFT);  
produitchart.setClockwise(false); 


pielabel.setTextFill(Color.BLACK);
pielabel.setStyle("-fx-font: 20 arial;");

for (final PieChart.Data data : produitchart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                pielabel.setTranslateX(e.getSceneX()-pielabel.getLayoutX());
                pielabel.setTranslateY(e.getSceneY()-pielabel.getLayoutY());
                pielabel.setText(String.valueOf(data.getPieValue()) + "%");
                
                Bounds b1 = data.getNode().getBoundsInLocal();
double newX = (b1.getWidth()) / 2.0 + b1.getMinX();

System.out.println(b1.getMinX());
double newY = (b1.getHeight()) / 2.0 + b1.getMinY();

// Make sure pie wedge location is reset
data.getNode().setTranslateX(0);
data.getNode().setTranslateY(0);

TranslateTransition tt = new TranslateTransition(
Duration.millis(1500), data.getNode());

tt.setByX(newX);
tt.setByY(newY);

tt.setAutoReverse(true);
tt.setCycleCount(2);
tt.play();
             }
        });
}


            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     
  
            
    
                
@FXML
    void loding(ActionEvent event) {
      
        Stage primaryStage = (Stage)anchopane.getScene().getWindow();
         file = fc.showOpenDialog(primaryStage);
        String file1= file.getAbsolutePath();
        path.setText(file1);
    }
     
            
    
    @FXML
    void AddProduits(ActionEvent event) throws FileNotFoundException {
 ProduitsService sp =new ProduitsService();
 
        
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
            try {
           //Produits p = new Produits(Aqte.getValue(),Aqte1.getValue(),"5", Anom.getText(), Adesc.getText(), Atype.getText(),gettedDatePickerDate,Acategorie.getSelectionModel().getSelectedItem(),path.getText());
          //sp.ajouterp(p);
           //String req = "Update `publication` set titre = ?, description = ?, image_publication = ? where id_publication ='"+TextF_IdP.getText()+"'";        
        String req = "INSERT INTO `produits`(`NOM`, `TYPE`, `quantite`, `DATEP`, `DESC`,`IMAGE`,`CATEGORIE`,`rate`,`quantiteR`) VALUES (?,?,?,?,?,?,?,?,?)";
 Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1,Anom.getText());
        ps.setString(2,Atype.getText());
         ps.setInt(3,Aqte.getValue());
          ps.setDate(4,gettedDatePickerDate);
           ps.setString(5, Adesc.getText());
            ps.setString(7,Acategorie.getSelectionModel().getSelectedItem());
            ps.setDouble(8,rating1.getRating());
             ps.setInt(9,Aqte1.getValue());
            fis=new FileInputStream(file);
        ps.setBinaryStream(6, fis,file.length());
        ps.executeUpdate();
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("PRODUCT is added successfully!");
            alert.show();
           display();   
             
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       /* }*/
        
    }

    void EditA(ActionEvent event) throws SQLException {
        
    }

    @FXML
    void exit(ActionEvent event) {
System.exit(0);
    }

    @FXML
    void getSelected(MouseEvent event) throws SQLException {
 SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue1);
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
  idt.setText(id.getCellData(index).toString());
    tnom.setText(nom1.getCellData(index));
    tdesq.setText(desc1.getCellData(index));
    
   tqt.getValueFactory().setValue(quantite.getCellData(index));
     ttype.setText(Type.getCellData(index));
   
    
    tqt1.getValueFactory().setValue(quantiteR1.getCellData(index));
    showPublicationImage(nom1.getCellData(index));
    }

    @FXML
    void handle(MouseEvent event) {
Audio audio = Audio.getInstance();
        InputStream sound = null;
        try {
        sound = audio.getAudio(search.getText(), Language.ENGLISH);
        } catch (IOException ex) {
        Logger.getLogger( FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
        audio.play(sound);
        } catch (JavaLayerException ex) {
        Logger.getLogger( FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  /*  private SimpleObjectProperty prodImage;

 public void setprodImage(Image value) {
    prodImageProperty().set(value);
}

public Object getprodImage() {
    return prodImageProperty().get();
}

public SimpleObjectProperty prodImageProperty() {
    if (prodImage == null) {
        prodImage = new SimpleObjectProperty(this, "prodImage");
    }
    return prodImage;
}*/
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
        trating.setCellValueFactory(new PropertyValueFactory<>("rate"));
          IMAGE.setPrefWidth(800); 
        IMAGE.setCellValueFactory(new PropertyValueFactory<>("IMAGE2"));
        
//       IMAGE.setCellFactory((TableColumn<Produits, Image> param) -> {
//            TableCell<Produits,Image> cell = new TableCell<Produits,Image>(){
//                ImageView imageview = new ImageView();
//                
//                public void updateItem(Produits item, boolean empty) {
//                    
//                    if(item!=null){                            
//                        
//                        HBox box= new HBox();
//                        box.setSpacing(10) ;
//                        VBox vbox = new VBox();
//                        //vbox.getChildren().add(new Label(item.getArtist()));
//                        vbox.getChildren().add(new Label(item.getIMAGE()));
//                        
//                        
//                        imageview.setFitHeight(50);
//                        imageview.setFitWidth(50);
//                        imageview.setImage(new Image(Produits.class.getResource("/tn/esprit/images").toString()));
//                        box.getChildren().addAll(imageview,vbox);
//                        //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//                        setGraphic(box);
//                    }
//                }
//            };
//                
//            return cell;
//        });  

 IMAGE.setCellFactory((TableColumn<Produits, Image> param) -> {
     TableCell<Produits,Image> cell = new TableCell<Produits,Image>(){
         public void updateItem(Produits item, boolean empty) {
             if(item!=null){
                 HBox box= new HBox();
                 box.setSpacing(10) ;
                 VBox vbox = new VBox();
                 
                 vbox.getChildren().add(new Label(item.getIMAGE()));
                 
                 ImageView imageview = new ImageView();
                 imageview.setFitHeight(50);
                 imageview.setFitWidth(50);
                 imageview.setImage(new Image(FXMLController.class.getResource("IMAGE").toString()));
                 
                 box.getChildren().addAll(imageview,vbox);
                 //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
                 setGraphic(box);
             }
         }
     };
     System.out.println(cell.getIndex());
     return cell;
        }
//            public TableCell<Produits,String> call(TableColumn<Produits,String> param) {                
//                TableCell<Produits,Image> cell = new TableCell<Produits,Image>(){
//                    public void updateItem(Produits item, boolean empty) {
//                        if(item!=null){                            
//                            HBox box= new HBox();
//                            box.setSpacing(10) ;
//                            VBox vbox = new VBox();
//
//                            vbox.getChildren().add(new Label(item.getIMAGE()));
//
//                            ImageView imageview = new ImageView();
//                            imageview.setFitHeight(50);
//                            imageview.setFitWidth(50);
//                            imageview.setImage(new Image(FXMLController.class.getResource("/tn/esprit/images").toString())); 
//
//                            box.getChildren().addAll(imageview,vbox); 
//                            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//                            setGraphic(box);
//                        }
//                    }
//                };
//                System.out.println(cell.getIndex());               
//                return cell;
//            }
        );        
         tbleview.getColumns().addAll();
        
        
        
        
//        tbleview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produits>() {
//			
//
//             @Override
//             public void changed(ObservableValue<? extends Produits> observable, Produits oldValue, Produits newValue) {
//                if(newValue!=null) {
//					imageView.setImage(new Image(getClass().getResource("/tn/esprit/images/" + newValue.getIMAGE1()).toString()));
//				}
//             }
//		});
        
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
                                Logger.getLogger( FXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
           Logger.getLogger( FXMLController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void EDITB(ActionEvent event) {
         try {
           String value1 = tnom.getText();
            String value2 = tdesq.getText();
            Integer value3 = tqt.getValue();
            Integer value6 = tqt1.getValue();
            String value4 = ttype.getText();
              /*String req = "Update `publication` set NOM = ?, DESC = ?, TYPE= ? , quantite= ?,quantiteR= ? where REFERENCE='"+idt.getText()+"'";        
     
            Connection  connexion = MyDB.getInstance().getConnexion();
        PreparedStatement ps1 = connexion.prepareStatement(req);
        ps1.setString(1,tnom.getText());
        ps1.setString(2,tdesq.getText());
        ps1.setString(3,ttype.getText());
        ps1.setInt(4,tqt.getValue());
        ps1.setInt(5, tqt1.getValue());
       // ps.setBinaryStream(3, fis,file.length());
             System.out.println("heyyyy");
       
        ps1.executeUpdate();*/
            String req = "UPDATE `produits` SET `NOM`='"+value1+"',`TYPE`='"+value4+"',`quantite`='"+value3+"',`DESC`='"+value2+"',`quantiteR`='"+value6+"' where REFERENCE='"+idt.getText()+"'";
                   
             Connection connexion;
            connexion = MyDB.getInstance().getConnexion();
           
             PreparedStatement pst5 = connexion.prepareStatement(req);
              pst5.execute();
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Publication is updated successfully!");
            alert.show();
          //display();
            
            display();
        } catch (SQLException e) {
        }
    }

    
    private void showPublicationImage(String titreP) throws SQLException{
       
       
               
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
                   
                   image = new Image("file:photo.jpg",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
                   imageView.setImage(image);
               }
          } catch (SQLException ex) {
              Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }
               
         
       
    }

    @FXML
    private void export(ActionEvent event) throws FileNotFoundException, IOException {
        
        

     

//            try {
// Connection connexion;
//            connexion = MyDB.getInstance().getConnexion();
//            Statement stm;
//            stm = connexion.createStatement();
//            String req = "SELECT *,categories.NOMCAT FROM `produits`, `categories`  WHERE produits.CATEGORIE=categories.CODE";
//            ResultSet rst = stm.executeQuery(req);
//                
//
//                
//
//                
//
//               
//
//                //Apache POI Jar Link-
//
//                //http://a.mbbsindia.com/poi/release/bin/poi-bin-3.13-20150929.zip
//
//                XSSFWorkbook wb = new XSSFWorkbook();//for earlier version use HSSF
//
//                XSSFSheet sheet = wb.createSheet("Product Details");
//
//                XSSFRow header = sheet.createRow(0);
//
//              /*  header.createCell(0).setCellValue("ID");
//
//                header.createCell(1).setCellValue("NOM");
//
//                header.createCell(2).setCellValue("TYPE");
//
//                header.createCell(3).setCellValue("Quantite");
//                 header.createCell(4).setCellValue("DATEP");
//                   header.createCell(5).setCellValue("DESCRIPTION");
//                   // header.createCell(6).setCellValue("IMAGE");
//                    header.createCell(6).setCellValue("CATEGORIE");
//                    header.createCell(7).setCellValue("Rate");
//                    header.createCell(8).setCellValue("QauntiteR");*/
//                   
//
//
//               
//
//                sheet.autoSizeColumn(1);
//
//                sheet.autoSizeColumn(2);
//
//                sheet.setColumnWidth(3, 256*25);//256-character width
//
//               
//
//                sheet.setZoom(150);//scale-150%
//
//               
//
//               
//
//                int index1 = 1;
//
////                while(rst.next()){
////
////                    XSSFRow row = sheet.createRow(index1);
////
////                    row.createCell(0).setCellValue(rst.getString("ID"));
////
////                    row.createCell(1).setCellValue(rst.getString("NOM"));
////
////                    row.createCell(2).setCellValue(rst.getString("TYPE"));
////
////                    row.createCell(3).setCellValue(rst.getInt("quantite"));
////                   // row.createCell(4).setCellValue(rst.getDate("DATEP"));
//////                    row.createCell(5).setCellValue(rst.getString("DESC"));
//////                   // row.createCell(6).setCellValue(rst.getString("IMAGE"));
//////                    row.createCell(6).setCellValue(rst.getString("NOMCAT"));
//////                    row.createCell(7).setCellValue(rst.getDouble("rate"));
//////                    row.createCell(8).setCellValue(rst.getInt("quantiteR"));
////
////                    index1++;                  
////
////                }
//
//               
//
//                FileOutputStream fileOut = new FileOutputStream("/tn/esprit/view/productDetails.xlsx");// before 2007 version xls
//
//                wb.write(fileOut);
//
//                //fileOut.close();
//
//               
//
//                Alert alert = new Alert(AlertType.INFORMATION);
//
//                alert.setTitle("Information Dialog");
//
//                alert.setHeaderText(null);
//
//                alert.setContentText("User Details Exported in Excel Sheet.");
//
//                alert.showAndWait();
//
//               
//
//                //pst.close();
//
//                rst.close();
//
//               
//
//            }catch (SQLException ex) {
//
//               // Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
//
//            }
//       // Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
//
//           
//
//        
//
//       
//
//        HBox hbox = new HBox(5);
//
//        hbox.getChildren().addAll();
    }

}