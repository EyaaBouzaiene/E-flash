/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import service.UserService;
import service.CryptWithMD5;


/**
 *
 * @author Mayssa
 */
public class User {
     private int id;
    private String nom;
    private String prenom;
    private String role;
    private int tel;
    private String email;
    private String password;
    private Date date;

    public User(int id, String nom, String prenom, String role, int tel, String email, String password, Date date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.date = date;
    }
    UserService u = new UserService();
    CryptWithMD5 cr = new CryptWithMD5();

    public User() {
    }
    
    

    public User( String nom, String prenom, String role, int tel, String email, String password) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public User(int id, String nom, String prenom, String role, int tel, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRole() {
        return role;
    }

    public int getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
       return cr.cryptWithMD5(password);
    
    
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRole(String role) {
        this.role= role;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + role + ", num_tel=" + tel + ", email=" + email + ", password=" + password + '}';
    }

    
}
