/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Livraison;
import service.LivraisonService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.MyDB;


/**
 *
 * @author 21627
 */
public class livraisonController implements Initializable {
    LivraisonService L = new LivraisonService();
     Connection connexion =MyDB.getInstance().getConnexion();
    Statement stm;
    private Label label;
    @FXML
    private Button boutton_mail;
    @FXML
    private Button exit;
    @FXML
    private TableColumn<Livraison, Integer> col_id;
    @FXML
    private RadioButton sortprix;
    @FXML
    private RadioButton sortville;
    @FXML
    private Button boutton_stat;
    @FXML
    private ComboBox<Integer> combo_cmd;
    @FXML
    private ComboBox<String> combo_Liv;
    @FXML
    private TextField txt_id;
    @FXML
    private FontAwesomeIconView refresh;
    @FXML
    private Button rating;
    @FXML
    private ComboBox<String> comb_ville;
    @FXML
    private Button espaceLivreur;
    @FXML
    private FontAwesomeIconView print;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    
    @FXML
    private TableColumn<Livraison, Integer> col_cmd;

    @FXML
    private TableColumn<Livraison, String> col_etat;

    @FXML
    private TableColumn<Livraison, Integer> col_livreur;

    @FXML
    private TableColumn<Livraison, Integer> col_prix;

    @FXML
    private TableColumn<Livraison, String> col_ville;

    @FXML
    private TableView<Livraison> table_livraison;
    ObservableList<Livraison> livraisons;
    ObservableList<Livraison> ListeL;
    @FXML
    private TextField txt_cmd;

    private TextField txt_etat;

    @FXML
    private TextField txt_liv;

    @FXML
    private TextField txt_prix;

    private TextField txt_ville;
    @FXML
    private TextField txt_search;

    public void setliv(String n) {
        combo_Liv.setValue(n);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        try {
            // TODO
            
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(livraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement stm;
        
        
        try {
            stm = connexion.createStatement();
       
        
             String req = "SELECT refcmd FROM  commande";
             
            
            PreparedStatement statement=connexion.prepareStatement(req) ;
            
            ResultSet rst = statement.executeQuery();
          
            ObservableList<Integer> list = FXCollections.observableArrayList();
            
            while (rst.next()) {
            list.add(rst.getInt("refcmd"));
            combo_cmd.setItems(list);
        
            }
            
            
             } catch (SQLException ex) {
            Logger.getLogger(livraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stm = connexion.createStatement();
       
             String req1 = "SELECT email FROM  user where role='livreur' ";
            
            PreparedStatement statement1=connexion.prepareStatement(req1) ;
            ResultSet rst1 = statement1.executeQuery();
            ObservableList<String> list1 = FXCollections.observableArrayList();
             while (rst1.next()) {
           list1.add(rst1.getString("email"));
            combo_Liv.setItems(list1);
            }
            
            
             } catch (SQLException ex) {
            Logger.getLogger(livraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
             //statement.close();
        
           comb_ville.getItems().addAll("Tunis", "Ariana", "Bizerte","Sfax","Nebeul","Monastir","Jandouba");
           
        
        
        
        
      
    }    
    
     public void showLivraison(){
        
       
   
            try {
            
            table_livraison.setItems(L.afficherlivraison());
         } catch (SQLException ex) {
            Logger.getLogger(livraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /////////////// recherche
     public void showLivraisonSearch(){
        
       
   int m = Integer.parseInt(txt_search.getText());
        //ArrayList<Livraison> l = (ArrayList<Livraison>) L.RechercherlivraisonPrix_ID(m);
        //ObservableList<Livraison> obs = FXCollections.observableArrayList(l);
        //L.afficherlivraison(obs); 
           try {
            
            table_livraison.setItems( L.RechercherlivraisonPrix_ID(m));
         } catch (SQLException ex) {
            Logger.getLogger(livraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
        
     
    private void loadData() throws SQLException {

        showLivraison();
        
       
             
            col_id.setCellValueFactory(new PropertyValueFactory<>("idLivraison"));
            col_cmd.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
            col_livreur.setCellValueFactory(new PropertyValueFactory<>("idLivreur"));
       
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            
                   
    }
   int index=-1;
  
  
   

   

    private void modifierLivraison(ActionEvent event) throws SQLException {
        LivraisonService sp= new LivraisonService();
        
        int v = Integer.parseInt(txt_id.getText());
        int v1 = combo_cmd.getSelectionModel().getSelectedItem();
      String v2 = combo_Liv.getSelectionModel().getSelectedItem();
         //String v3 = comb_ville.getSelectionModel().getSelectedItem();
            System.out.println(v); 
         //System.out.println(v1);
           System.out.println(v2);
             
        ////////livreur
       int id = getidLivreur(v2);
     
       
       
     
       String v4 = txt_ville.getText();
       String v5 = "En cours";
       String v6= txt_cmd.getText();
       
        
      
        
        try {
             //int prix=getprixcmd(v1);
             //String sprix= String.valueOf(prix);
             Livraison p = new Livraison(id,v4,v5);
          
            sp.modifierlivraison(p,v,Integer.parseInt(v6));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Livraison is updated successfully!");
            alert.show();
            loadData();
            txt_cmd.setText("");
            txt_liv.setText("");
            txt_prix.setText("");
            txt_ville.setText("");
            txt_etat.setText("");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @FXML
    private void tomail(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/sendMail.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    /////////////avoir id par mail
    int getidLivreur(String v) throws SQLException
    {
        String req = "select id from user where  email='"+v+"'";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        int i = rs.getInt("id");
        return i ;
    }
    //////////////avoir id livreur de livreur
    int getid() throws SQLException
    {
       String req = "select idLivreur from livreur ";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        int i = rs.getInt("idLivreur");
        return i;
    }
    /////////////////////avoir nom livreur par id
     String getnomLivreur() throws SQLException
    {
        int i =getid();
       String req = "select nom from user where  id='"+i+"'";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        String n = rs.getString("nom");
        return n ;
    }
     /////////////////avoir idcommande par reference
       int getidbyref(int v) throws SQLException
    {
        String req = "select idCommande from commande where  refcmd='"+v+"'";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        int i = rs.getInt("idCommande");
        return i ;
    }
       ///////////////////avoir prix de commande par reference
  int getprixcmd(int v) throws SQLException
    {
        String req = "select prix from commande where  refcmd='"+v+"'";
        stm = connexion.createStatement();
        ResultSet rs =stm.executeQuery(req);
        rs.last();
        int i = rs.getInt("prix");
        return i ;
    }
  public List<Integer> getcommande() throws SQLException {
        List <Integer> lc = new ArrayList<>();
        String req = "select idCommande from livraison";
        stm = connexion.createStatement();
        
        ResultSet rst = stm.executeQuery(req);
        while(rst.next())
        {
            int cmd = rst.getInt("idCommande");
            lc.add(cmd);
        }
        return lc;
    }
  ///////////////////// ajout
    @FXML
    private void ajoutLivraison(ActionEvent event) throws SQLException {
       LivraisonService sp= new LivraisonService();
       int v1 = combo_cmd.getSelectionModel().getSelectedItem();
     String v2 = combo_Liv.getSelectionModel().getSelectedItem();
         String v3 = comb_ville.getSelectionModel().getSelectedItem();
          System.out.println(v1);
           System.out.println(v2);
             System.out.println(v3);
             int cmd =getidbyref(v1);
     ////////////// test sur controle saisie
       boolean isliv = (combo_Liv.getValue() == null);
       boolean iscmd = (combo_cmd.getValue() == null);
       boolean isville = (comb_ville.getValue() == null);
       
        if((isliv==true)||(iscmd==true)||(isville==true))
        {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Noo");
            alert.setContentText("You need to insert data!");
            alert.show();
            
        }
        else
        {
            if(getcommande().contains(cmd)==true)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Noo");
            alert.setContentText("Commande existe!");
            alert.show();
            }
        
        else
            {
        try {
            int idl=getidLivreur(v2);
            System.out.println(idl);
             int id2=getidbyref(v1);
             int prix=getprixcmd(v1);
             String sprix= String.valueOf(prix);
             txt_prix.setText(sprix);
             Livraison p = new Livraison(id2,
                 idl,prix,
                v3,"En Cours",v2);
            sp.ajouterlivraison(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Livraison is added successfully!");
            alert.show();
            loadData();
            txt_id.setText("");
            txt_cmd.setText("");
            txt_liv.setText("");
            txt_prix.setText("");
            txt_ville.setText("");
            txt_etat.setText("");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }
        }
    }
/////////////////////////supprimer
    @FXML
    private void suppLivraison(ActionEvent event) {
         LivraisonService sp= new LivraisonService();
       int v = Integer.parseInt(txt_id.getText());
        System.out.println(v);
          try {
         sp.supprimerlivraison(v);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Livraison is deleted successfully!");
            alert.show();
            loadData();
            txt_cmd.setText(null);
            txt_liv.setText(null);
            txt_prix.setText(null);
            txt_ville.setText(null);
            txt_etat.setText(null);
          }
         catch (SQLException ex) {
            Logger.getLogger(livraisonController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
          
    }
/////////////////////////trie prix
    @FXML
    private void sortprix(ActionEvent event) throws SQLException {
        //table_livraison.getChildren().clear();
        sortprix.setSelected(false);
       
        table_livraison.setItems( L.Trierlivraison(1));
        
    }
//////////////////////////////trie ville
    @FXML
    private void sortville(ActionEvent event) throws SQLException {
        sortprix.setSelected(false);
        table_livraison.setItems(L.Trierlivraison(2));
    }
/////////////////////////////recherche
    @FXML
    private void searchLivraison(KeyEvent event) throws SQLException {
      
           int m = Integer.parseInt(txt_search.getText());
      
           try {
            
            table_livraison.setItems( L.RechercherlivraisonPrix_ID(m));
         } catch (SQLException ex) {
            Logger.getLogger(livraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
            col_id.setCellValueFactory(new PropertyValueFactory<>("idLivraison"));
            col_cmd.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
            col_livreur.setCellValueFactory(new PropertyValueFactory<>("idLivreur"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            //txt_search.setText("");
            
    }

    @FXML
    private void gostat(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/StatLivreur.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void selected(javafx.scene.input.MouseEvent event) {
         index = table_livraison.getSelectionModel().getSelectedIndex();
        if(index<= -1)
        {
            return;
        }
            txt_cmd.setText(col_cmd.getCellData(index).toString());
            txt_liv.setText(col_livreur.getCellData(index).toString());
            txt_id.setText(col_id.getCellData(index).toString());
            txt_prix.setText(col_prix.getCellData(index).toString());
            txt_ville.setText(col_ville.getCellData(index).toString());
            txt_etat.setText(col_etat.getCellData(index).toString());
    }

    private void refresh(ActionEvent event) throws SQLException {
        txt_search.setText(null);
        loadData();
    }

    @FXML
    private void torate(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/Rate.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toespace(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/EspaceLivreur.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        
    }

    @FXML
    private void again(javafx.scene.input.MouseEvent event) throws SQLException {
         txt_search.setText(null);
        loadData();
    }

    @FXML
    private void updateLivraison(ActionEvent event) throws SQLException {
         LivraisonService sp= new LivraisonService();
        
        int v = Integer.parseInt(txt_id.getText());
        
      String v2 = combo_Liv.getSelectionModel().getSelectedItem();
       String v3 = comb_ville.getSelectionModel().getSelectedItem();
       System.out.println(v); 
         
           System.out.println(v2);
             int id = getidLivreur(v2);
             
             String v5 = "En cours";
             String v6= txt_cmd.getText();
       
       try {
            
             Livraison p = new Livraison(id,v3,v5);
          
            sp.modifierlivraison(p,v,Integer.parseInt(v6));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Livraison is updated successfully!");
            alert.show();
            loadData();
            txt_cmd.setText("");
            txt_liv.setText("");
            txt_prix.setText("");
            txt_ville.setText("");
            txt_etat.setText("");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

  

    @FXML
    private void print(javafx.scene.input.MouseEvent event) {
         try{
String filename="C:\\Users\\21627\\Desktop\\testtt\\livraison.xls" ;
HSSFWorkbook hwb=new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("ID");
rowhead.createCell((short) 1).setCellValue("Commande");
rowhead.createCell((short) 2).setCellValue("Livreur");
rowhead.createCell((short) 3).setCellValue("Prix");
rowhead.createCell((short) 4).setCellValue("Ville");
rowhead.createCell((short) 5).setCellValue("etat");


Statement st=connexion.createStatement();
ResultSet rs=st.executeQuery("Select idLivraison,idCommande,idLivreur,prix,ville,etat from livraison");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);

row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("idLivraison")));
row.createCell((short) 1).setCellValue(Integer.toString(rs.getInt("idCommande")));
row.createCell((short) 2).setCellValue(Integer.toString(rs.getInt("idLivreur")));
row.createCell((short) 3).setCellValue(Integer.toString(rs.getInt("prix")));
row.createCell((short) 4).setCellValue(rs.getString("ville"));
row.createCell((short) 5).setCellValue(rs.getString("etat"));
i++;
}
FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");

} catch ( Exception ex ) {
    System.out.println(ex);

}
    }

    @FXML
    private String select() {
        String v2 = combo_Liv.getSelectionModel().getSelectedItem();
        return v2;
    }
    

}
