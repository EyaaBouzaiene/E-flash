/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;

/**
 *
 * @author EXTRA
 */
public interface IDislikes {
    
    
    public void ajouter_dislike(int id_p,int id_c) throws SQLException;
    
    public void afficher_nombre_dislike(int id_publication) throws SQLException;
    
}