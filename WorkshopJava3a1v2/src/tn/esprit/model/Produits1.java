/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.model;

/**
 *
 * @author aders
 */
public enum Produits1 {
    APPLE("apple.jpg",0.55f),MILK("milk.jpg",0.78f),JUICE("juice.jpg",0.56f),LETTUCE("lettuce.jpg",0.78f), EMPTY("",0.0f);

    private String imageFile;
    private float price;

    Produits1(String imageFile,float price){
        this.imageFile = imageFile;
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }

    public float getPrice() {
        return price;
    }
}
