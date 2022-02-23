/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author EXTRA
 */
public class Dislikes {
    
    private int id;
    private int id_publication_dislike;
    private int id_client;

    public Dislikes(int id_publication_dislike, int id_client) {
        this.id_publication_dislike = id_publication_dislike;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_publication_dislike() {
        return id_publication_dislike;
    }

    public void setId_publication_dislike(int id_publication_dislike) {
        this.id_publication_dislike = id_publication_dislike;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    
    
    
}
