/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import tn.esprit.model.Produits;
import tn.esprit.model.Produits1;

/**
 *
 * @author aders
 */
public class CartEntry {
    private Produits Produit;
     private Produits1 Produit1;
            private int quantity;

    public Produits1 getProduit1() {
        return Produit1;
    }

    public void setProduit1(Produits1 Produit1) {
        this.Produit1 = Produit1;
    }

    public CartEntry(Produits1 Produit1, int quantity) {
        this.Produit1 = Produit1;
        this.quantity = quantity;
    }

            
    public CartEntry(Produits Produit, int quantity) {
        this.Produit = Produit;
        this.quantity = quantity;
    }

    public Produits getProduit() {
        return Produit;
    }

    public void setProduit(Produits Produit) {
        this.Produit = Produit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartEntry{" + "Produit=" + Produit + ", quantity=" + quantity + '}';
    }
            
        public void increaseQuantity(){
            this.quantity++;
        } 
        
        public void decreaseQuantity(){
            if(this.quantity>0)
            {
                   this.quantity--;
            }
         
        } 
}
