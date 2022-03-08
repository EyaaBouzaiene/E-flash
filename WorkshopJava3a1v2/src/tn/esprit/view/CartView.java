/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import static com.itextpdf.text.Annotation.URL;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import static javafx.scene.input.DataFormat.URL;

/**
 *
 * @author aders
 */
public class CartView  {
    private Parent view ;
    public CartView() throws IOException {
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
        Parent root  = loader.load();
        
    }
    
    public  Parent getView()
    {
        return this.view;
    }
}
