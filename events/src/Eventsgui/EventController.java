/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventsgui;

import Event.crud.Evenement;
import Service.Email;
import events.tools.Maconnexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ideapadGAMING
 */
public class EventController implements Initializable {

    Connection cnx = Maconnexion.getInstance().getCnx();
    @FXML
    private TextField txtnom;
    @FXML
    private TextField descriptiontxt;
    @FXML
    private TextField dureetable;
    @FXML
    private TextField nombre_p;
    @FXML
    private DatePicker dateev;
    @FXML
    private TableColumn<Evenement, Integer> idtable;
    @FXML
    private TableColumn<Evenement, String> nomtable;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> durèetable;
    @FXML
    private TableColumn<Evenement, Date> date_debuttable;
    public ObservableList<Evenement> data = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Evenement, String> nombrebretable;
    @FXML
    private Button btnajout;
    @FXML
    private TableView<Evenement> tableview;
    @FXML
    private Label label_image;
    @FXML
    private Button icon_import;
    @FXML
    private ImageView image_event;
    private Object AlertType;
    @FXML
    private Button btndelete;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btinmodify;
    @FXML
    private Button btinmodify1;
    @FXML
    private Button export;
    @FXML
    private Button btnstat;

    //////////////////
    private File imageFile;
    //////////////////
    Email Email = new Email();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEvent();

        descriptiontxt.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(10));

        txtnom.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(10));

        ////////////////////////////////////////
        Calendar cal = Calendar.getInstance();

        int years = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        System.out.println(" " + years + " " + month + " " + day);
        ////////////////////////////////////////
        DatePicker maxDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date
        maxDate.setValue(LocalDate.of(years, month, day)); // Max date available will be 
        ///////////////////////////////////////////////////////
        final Callback<DatePicker, DateCell> dayCellFactory;

        dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(maxDate.getValue())) { //Disable all dates after required date
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
                }
            }
        };
//Finally, we just need to update our DatePicker cell factory as follow:
        dateev.setDayCellFactory(dayCellFactory);

    }

    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[A-Za-z]")) {
                } else {
                    e.consume();
                }
            }
        };
    }

    public void showEvent() {

        List<Evenement> EventList = new ArrayList<>();

        EventList = LoadDataEvent();

        ObservableList<Evenement> Liste = FXCollections.observableArrayList(EventList);

        idtable.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
        nomtable.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        durèetable.setCellValueFactory(new PropertyValueFactory<Evenement, String>("duree"));
        nombrebretable.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nombre_place"));
        date_debuttable.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_debut"));
        tableview.setItems(Liste);

        tableview.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.PRIMARY) && ev.getClickCount() == 2) {

                txt_search.setText(String.valueOf(tableview.getSelectionModel().getSelectedItem().getId()));
                descriptiontxt.setText(tableview.getSelectionModel().getSelectedItem().getDescription());
                nombre_p.setText(tableview.getSelectionModel().getSelectedItem().getNombre_place());
                dureetable.setText(tableview.getSelectionModel().getSelectedItem().getDuree());
                dateev.setValue(tableview.getSelectionModel().getSelectedItem().getDate_debut().toLocalDate());
                txtnom.setText(tableview.getSelectionModel().getSelectedItem().getNom());

                image_event.setImage(getImage());

            }
        });
    }

    private List<Evenement> LoadDataEvent() {

        List<Evenement> EventList = new ArrayList<>();
        try {
            String requete = "SELECT id,nom,description,duree,nombre_place,date_debut from evenement";
            Statement pst = Maconnexion.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Evenement r = new Evenement();
                r.setId(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setDescription(rs.getString(3));
                //
                r.setDuree(rs.getString(4));
                r.setNombre_place(rs.getString(5));
                r.setDate_debut(rs.getDate(6));
                EventList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return EventList;
    }

    private InputStream getInputStream() throws FileNotFoundException {
        InputStream isssssssss = null;
        if (imageFile != null) {
            System.out.println("Ajouttttt");
            isssssssss = new FileInputStream(imageFile);
        } else {
            try {
                String sql = "SELECT image FROM evenement  WHERE id='" + txt_search.getText() + "' ";
                PreparedStatement ps = Maconnexion.getInstance().getCnx().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    isssssssss = rs.getBinaryStream("image");
                    System.out.println("Modiiifffier");
                }
            } catch (SQLException ex) {
            }
        }
        return isssssssss;
    }

    private Image getImage() {
        Image image = null;
        try {
            String sql = "SELECT image FROM evenement WHERE id='" + txt_search.getText() + "'";
            PreparedStatement ps = Maconnexion.getInstance().getCnx().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InputStream img = rs.getBinaryStream("image");
                if (img != null) {
                    image = new Image(img, 200, 200, true, true);
                }
            }
        } catch (SQLException ex) {
        }
        return image;
    }

    @FXML
    private void addevent(MouseEvent event) {
        java.util.Date date_debut = java.util.Date.from(dateev.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        ///////// ====>>>> Send Email When you creat new Event 
        String to = "hadil.khemissi@esprit.tn";
      
//        Email.sendMail("Nom Event : "+txtnom.getText()+"\n"+" Date Event : "+date_debut.getTime()+"", to);

        ////////
        try {

            PreparedStatement ste;
            Statement st;
            Connection cnx;

            cnx = Maconnexion.getInstance().getCnx();
            String nom = txtnom.getText();
            String description = descriptiontxt.getText();
            String nombre_place = nombre_p.getText();
            String duree = dureetable.getText();
            Date sqlDate = new Date(date_debut.getTime());

            //////
            InputStream isssssssss = null;
            isssssssss = getInputStream();
            if ((!nom.equals("")) && (!description.equals("")) && (!nombre_place.equals("")) && (!duree.equals(""))) {
                  Email.sendMail("Nom Event : " + txtnom.getText() + ""
                + "\ndescription : " + descriptiontxt.getText() + ""
                + "\nNombre de place : " + nombre_p.getText() + ""
                + "\nduree : " + dureetable.getText() + ""
                + "\nDate Event : " + date_debut.getTime() + "", to);

                String sql = "insert into evenement(nom,description,duree,nombre_place,date_debut,image)" + "Values(?,?,?,?,?,?)";

                ste = cnx.prepareStatement(sql);
                ste.setString(1, nom);
                ste.setString(2, description);
                ste.setString(3, duree);
                ste.setString(4, nombre_place);

                ste.setDate(5, sqlDate);

                ste.setBlob(6, isssssssss);

                ste.executeUpdate();

                label_image.setText("aucune selectionné");
                txtnom.setText("");
                descriptiontxt.setText("");
                nombre_p.setText("");
                dureetable.setText("");
                dateev.setValue(null);
                image_event.setImage(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "evenement ajoutés avec succcès", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
                showEvent();

                Notifications notificationBuild = Notifications.create()
                        .title("New event")
                        .text("A event was Added")
                        //.hideAfter(Duration.Hours(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("click here");
                            }
                        });
                notificationBuild.showConfirm();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("il existe un champ vide");
                alert.showAndWait();

            }

        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void importimage(MouseEvent event) {

        imageFile = getImageFromFileChooser(getStage());
        if (imageFile != null) {
            Image image = new Image(imageFile.toURI().toString(), 200, 200, true, true);
            image_event.setImage(image);
        }

    }

    private Stage getStage() {
        return (Stage) btnajout.getScene().getWindow();
    }

    private File getImageFromFileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterImages = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().addAll(extFilterImages);
        fileChooser.setTitle("Select an image");
        File selectedImage = fileChooser.showOpenDialog(stage);
        return selectedImage;
    }

    @FXML
    private void deleteevent(MouseEvent event) {
//           String to = "hadil.khemissi@esprit.tn";
//        Email.sendMail("Due to unforeseen circumstances our event  "+txtnom.getText()+" has been cancelled. Please accept our apologies for any inconvenience.  " 
//           
//               , to);

        if (!tableview.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le produit " + tableview.getSelectionModel().getSelectedItem().getNom() + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                try {
                    String sql2 = "DELETE FROM rating WHERE id_event='" + txt_search.getText() + "'";
                    PreparedStatement ste2;
                    Connection cnxx2;
                    cnxx2 = Maconnexion.getInstance().getCnx();
                    ste2 = cnxx2.prepareStatement(sql2);
                    ste2.executeUpdate();

                    String sql = "DELETE FROM evenement WHERE id='" + txt_search.getText() + "'";
                    PreparedStatement ste;
                    Connection cnxx;
                    cnxx = Maconnexion.getInstance().getCnx();
                    ste = cnxx.prepareStatement(sql);
                    ste.executeUpdate();

                    label_image.setText("aucune selectionné");
                    txt_search.setText("");
                    txtnom.setText("");
                    descriptiontxt.setText("");
                    nombre_p.setText("");
                    dureetable.setText("");
                    dateev.setValue(null);
                    image_event.setImage(null);

                    Notifications notificationBuild = Notifications.create()
                            .title("Traitement d'évenement")
                            .text("l'eveement a été supprimé avec succes")
                            .graphic(null)
                            //.hideAfter(Duration.Hours(5))
                            .position(Pos.CENTER)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("click here");
                                }

                            });
                    notificationBuild.show();
                } catch (SQLException ex) {
                    Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();
        }

    }

    @FXML
    private void searchevent(MouseEvent event) {
        int m = 0;
        try {
            String sql = "select nom ,description,duree,nombre_place,date_debut,image from evenement WHERE nom='" + txt_search.getText() + "'";

            PreparedStatement ste;
            Statement st;
            Connection cnx;
            cnx = Maconnexion.getInstance().getCnx();
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                txtnom.setText(rs.getString("nom"));
                descriptiontxt.setText(rs.getString("description"));
                nombre_p.setText(rs.getString("nombre_place"));
                dureetable.setText(rs.getString("duree"));
                Date date = rs.getDate("date_debut");
                dateev.setValue(date.toLocalDate());
                m = 1;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (m == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun evenement trouvé avec nom =" + txt_search.getText() + "", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    private void modifyevent(MouseEvent event) {

        PreparedStatement ste;
        Statement st;
        Connection cnx;

        cnx = Maconnexion.getInstance().getCnx();
        String nom = txtnom.getText();
        String description = descriptiontxt.getText();
        String nombre_place = nombre_p.getText();
        String duree = dureetable.getText();
        java.util.Date date_debut = java.util.Date.from(dateev.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqlDate = new Date(date_debut.getTime());
        try {

            String sql = "update evenement set nom=?,description=?,duree=?,nombre_place=?,date_debut=?,image=?  WHERE id='" + txt_search.getText() + "'";

            ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setString(2, description);
            ste.setString(3, duree);
            ste.setString(4, nombre_place);

            ste.setDate(5, sqlDate);

            ste.setBlob(6, getInputStream());
            ste.executeUpdate();

            label_image.setText("aucune selectionné");
            txtnom.setText("");
            descriptiontxt.setText("");
            nombre_p.setText("");
            dureetable.setText("");
            dateev.setValue(null);
            image_event.setImage(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "evenement modifié avec succcès", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            showEvent();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void esportexcel(ActionEvent event) throws SQLException {

        try {
            String filename = "C:\\Users\\ideapadGAMING\\OneDrive\\Documents\\NetBeansProjects\\events\\data.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("id");
            rowhead.createCell((short) 1).setCellValue("nom");
            rowhead.createCell((short) 2).setCellValue("description");
            rowhead.createCell((short) 3).setCellValue("duree");
            rowhead.createCell((short) 4).setCellValue("nombre_place");

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("select id, nom ,description,duree,nombre_place,date_debut,image from evenement");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
                row.createCell((short) 1).setCellValue(rs.getString("nom"));
                row.createCell((short) 2).setCellValue(rs.getString("description"));
                row.createCell((short) 3).setCellValue(rs.getString("duree"));
                row.createCell((short) 4).setCellValue(rs.getString("nombre_place"));

                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
            File file = new File(filename);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    @FXML
    private void stat(ActionEvent event) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();

            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
//
//    @FXML
//    private void agenda(ActionEvent event) {
//    }

    @FXML
    private void agenda(ActionEvent event) {
        
        
       FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("TrackEmployeeFX.fxml"));
                            try {
                                 loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                            TrackEmployeeController Page1 = loader.getController();
                           
                            //Page1.setTextField_Commentaire(pd.getId());
                           
                           /* sms s=new sms();
                            s.sms("helllo baby");*/
                           
                           
                           
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

        
    }
}
