/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


//import gui.itemDash.ItemDashFXMLController;
import gui.AffichCoaching.ListCoachings;
import interfaces.ICoachings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Coachings;
import util.MaConnexion;

/**
 *
 * @author Elife-Kef-114
 */
public class CoachingsService implements ICoachings{

    
         Connection cnx = MaConnexion.getInstance().getCnx();
   
    private static ICoachings Coachinges;

    public static ICoachings getInstance() {
        if (Coachinges == null) {
            Coachinges = new CoachingsService();
        }
        return Coachinges;
    }
    


    @Override
    public void ajouterCoaching(Coachings c) {
         String req = "INSERT INTO `coachings`(`idcoach`, `titre`, `discipline`, `description`,`planing`,`prix`,`nbmax`,`nbinscri`,`image`) VALUES (?,?,?,?,?,?,?,?,?)";
//         String req = "INSERT INTO `coaching`(`idcoach`, `titre`, `discipline`, `description`,`planing`,`prix`) VALUES (?,?,?,?,?,?)";
           try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getIdcoach());/////
            ps.setString(2,c.getTitre());
            ps.setString(3, c.getDiscipline());
            ps.setString(4, c.getDescription());
            ps.setString(5, c.getPlaning());
            ps.setString(6, c.getPrix());
            ps.setInt(7, c.getNbmax());
            ps.setInt(8, c.getNbinscri());
            ps.setString(9, c.getImage());
//            
            
            
            ps.executeUpdate();
            System.out.println("PS : Coaching  ajoutée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(CoachingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   
    
}
