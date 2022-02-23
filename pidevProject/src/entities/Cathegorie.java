/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author aders
 */
public class Cathegorie {

    private int reference;
    private String nomC;

    public Cathegorie(int reference, String nomC) {
        this.reference = reference;
        this.nomC = nomC;
    }

    public Cathegorie(String nomC) {
        this.nomC = nomC;
    }

    @Override
    public String toString() {
        return "Cathegorie{" + "reference=" + reference + ", nomC=" + nomC + '}';
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

}
