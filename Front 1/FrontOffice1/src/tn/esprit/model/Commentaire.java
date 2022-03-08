/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.model;

/**
 *
 * @author EXTRA
 */
public class Commentaire {
    
    int id;
    String messages;
    String date;
    int id_client;
    int id_publication_commentaire;

    public Commentaire(int id, String messages, String date,int id_publication_commentaire,int id_client) {
        this.id = id;
        this.messages = messages;
        this.date = date;
        this.id_publication_commentaire = id_publication_commentaire;
        this.id_client = id_client;
        
    }

    public Commentaire(int id, String messages, String date, int id_client) {
        this.id = id;
        this.messages = messages;
        this.date = date;
        this.id_client = id_client;
    }
    

    public Commentaire(String messages, String date,int id_publication_commentaire, int id_client) {
        this.messages = messages;
        this.date = date;
        this.id_publication_commentaire = id_publication_commentaire;
        this.id_client = id_client;
        
    }

    public Commentaire(String messages, String date, int id_client) {
        this.messages = messages;
        this.date = date;
        this.id_client = id_client;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_publication_commentaire() {
        return id_publication_commentaire;
    }

    public void setId_publication_commentaire(int id_publication_commentaire) {
        this.id_publication_commentaire = id_publication_commentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", messages=" + messages + ", date=" + date + ", id_client=" + id_client + ", id_publication_commentaire=" + id_publication_commentaire + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Commentaire other = (Commentaire) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    

}
