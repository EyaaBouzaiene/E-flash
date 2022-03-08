package tn.esprit.view;

//import com.sun.javaws.Main;
//import javafx.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.esprit.model.Fruit;
import tn.esprit.model.Produits;

import workshopjava3a1.MyListener;
public class ItemController  implements Initializable{
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

//     private MyListener myListener;
        private Produits  fruit;
    @FXML
    private void click(MouseEvent mouseEvent) {
//        myListener.onClickListener(fruit);
    }

 
  

    public void setData(Produits fruit) {
        
    }

    void setData(Produits get, MyListener myListener) {
        this.fruit = fruit;
        
        nameLabel.setText(fruit.getNom());
       // priceLable.setText(WorkshopJava3a1.CURRENCY + fruit.getPrice());
        Image image = new Image(getClass().getResourceAsStream(fruit.getIMAGE()));
        img.setImage(image);
    }
     public void afficher (List<Produits> lt,int i)
    { //Image imt1 = new Image(getClass().getResourceAsStream(lt.get(i*4).getIMAGE()));
       // this.img.setImage(imt1);
        this.nameLabel.setText(lt.get(i).getNom());
        //this.numt1.setText("num "+String.valueOf(i*4+1));
         
    }
     public void afficher2 (Produits p )
    { //Image imt1 = new Image(getClass().getResourceAsStream(lt.get(i*4).getIMAGE()));
       // this.img.setImage(imt1);
        this.nameLabel.setText(p.getNom());
        this.priceLable.setText(p.getRate());
        //this.numt1.setText("num "+String.valueOf(i*4+1));
         
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
}
