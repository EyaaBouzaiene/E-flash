/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import com.gluonhq.impl.charm.a.b.b.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import tn.esprit.model.Produits;
import tn.esprit.model.Produits1;

/**
 *
 * @author aders
 */
public class ShoppingCart {
    
    private static ShoppingCart instance;
private Map<String,CartEntry> entries;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.entries);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShoppingCart other = (ShoppingCart) obj;
        if (!Objects.equals(this.entries, other.entries)) {
            return false;
        }
        return true;
    }

    public ShoppingCart() {
        this.entries =  new HashMap<>();
    }
    public static ShoppingCart getInstance() {
        if (instance==null)
        {
           instance=new ShoppingCart();
        }
            
        
        
        return instance;
    }

    public static void setInstance(ShoppingCart instance) {
        ShoppingCart.instance = instance;
    }
    
    public  void addProduct (String Productname)

    {
        
         CartEntry p=entries.get(Productname);
        if(p!=null)
        {
            p.increaseQuantity();
        }
        else
        {
         // Produits x = Produits.class.cast(Productname);
            //Produits x = Produits.valueOF(Productname);
             //Produits x = Produits.class.cast(Productname);
              //Float a = Float.valueOf("80");    
            //CartEntry entry = new CartEntry(x, 1);
              //entries.put(Productname,entry);
        }
    }
    
     public  int getQuantity (String Productname)

    {
        
         CartEntry p=entries.get(Productname.toUpperCase());
        if(p!=null)
        {
            p.getQuantity();
        }
       return 0;
    }
    
     public  float calculateTotal ()

    {
        float total=0;
        for(CartEntry entry : entries.values())
        {
            float entrycost=(float) (entry.getProduit().getPrix()*entry.getQuantity());
            total= total+entrycost;
        }
       return total;
    }
     
     
     public List<CartEntry> getEntries()
     {
         return new ArrayList<>(entries.values());
     }
    
    
    
    
    
}
