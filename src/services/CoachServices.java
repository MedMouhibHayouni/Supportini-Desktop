/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICoach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coach;
import model.Entrainee;
import model.Utilisateur;
import util.MaConnexion;

/**
 *
 * @author Asus
 */
public class CoachServices implements ICoach {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void addCoach(Coach c) {
        String req = "INSERT INTO coachs (specialite , fk_idUser) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getSpecialite());
            ps.setInt(2, c.getIdUser());
         
            ps.executeUpdate();
          
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public Coach queryById(int id) {
        String req = "SELECT * FROM coachs WHERE fk_idUser =?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            res.first();
            Coach coach = new Coach();
            coach.setId(res.getInt(1));
            coach.setSpecialite(res.getString(2));



            ps.close();
            return coach;

        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

    @Override
    public Coach selectProfil(int id) {
          String req="SELECT c.id ,c.specialite,u.nom,u.prenom,u.email,u.phone,u.fk_idRole,u.image_name FROM utilisateurs u  JOIN coachs c WHERE c.fk_idUser = ? AND u.id=?";
       PreparedStatement ps ;
     try {
         ps=cnx.prepareStatement(req);
         ps.setInt(1, id);
             ps.setInt(2, id);
       ResultSet res =  ps.executeQuery();
        res.first();
                
             Coach c = new Coach(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getInt(7), res.getString(8));
             System.out.println(c);
           return c;
     } catch (SQLException ex) {
         Logger.getLogger(EntraineeServices.class.getName()).log(Level.SEVERE, null, ex);
          return null;
     }
    }

}
