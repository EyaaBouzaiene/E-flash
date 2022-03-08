/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.SQLException;

/**
 *
 * @author EXTRA
 */
public interface ILikes<T> {
    
    
    
    public void ajouter_like(int id_p,int id_c) throws SQLException;
    
    public int afficher_nombre_like(int id_publication) throws SQLException;
    
    
}
