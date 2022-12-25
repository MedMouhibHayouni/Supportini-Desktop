/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IEntrainee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Entrainee;
import model.Utilisateur;
import util.MaConnexion;

/**
 *
 * @author Asus
 */
public class EntraineeServices implements IEntrainee{
 Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void addEntrainee(Entrainee e) {
        String req = "INSERT INTO entrainees (age,taille,poids,sexe,fk_idUser) VALUES (?,?,?,?,?)";
     try {
         PreparedStatement ps = cnx.prepareStatement(req);
       
         ps.setInt(1, e.getAge());
         ps.setInt(2, e.getTaille());
         ps.setInt(3, e.getPoids());
       
         ps.setString(4, e.getSexe());
         ps.setInt(5, e.getFk_idUser());
         ps.executeUpdate();
        
         ps.close();
     } catch (SQLException ex) {
         Logger.getLogger(EntraineeServices.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }

    @Override
    public Entrainee queryById(int id) {
           String req = "SELECT * FROM entrainees WHERE fk_idUser =?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            res.first();
            Entrainee ent = new Entrainee();
            ent.setId(res.getInt(1));
            ent.setAge(res.getInt(2));
            ent.setTaille(res.getInt(3));
            ent.setPoids(res.getInt(4));
           
            ent.setSexe(res.getString(5));
          
            ps.close();
            return ent;

        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

    @Override
    public Entrainee selectProfil(int id) {
       String req="SELECT e.id ,e.age,e.taille,e.poids,e.sexe,u.nom,u.prenom,u.email,u.phone,u.fk_idRole,u.image_name FROM utilisateurs u  JOIN entrainees e WHERE e.fk_idUser = ? AND u.id=?";
       PreparedStatement ps ;
     try {
         ps=cnx.prepareStatement(req);
         ps.setInt(1, id);
             ps.setInt(2, id);
       ResultSet res =  ps.executeQuery();
        res.first();
                 Utilisateur user = new Utilisateur();
             Entrainee ent = new Entrainee(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4), res.getString(5),res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getInt(10), res.getString(11));
           return ent;
     } catch (SQLException ex) {
         Logger.getLogger(EntraineeServices.class.getName()).log(Level.SEVERE, null, ex);
          return null;
     }
       
    }
    
}
