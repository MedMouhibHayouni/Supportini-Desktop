/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IGalerie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GalerieImage;
import util.MaConnexion;
import util.Notification;

/**
 *
 * @author Asus
 */
public class GalerieServices implements IGalerie{
 Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void addImage(GalerieImage gi) {
        String req = "INSERT INTO galeries( `fk_idUser`, `image`) VALUES (?,?)";
        PreparedStatement ps ; 
     try {
         ps=cnx.prepareStatement(req);
         ps.setInt(1, gi.getIdUser());
         ps.setString(2, gi.getImageName());
         ps.executeUpdate();
         ps.close();
         Notification.notificationSuccess("SUCCEES", "Image enregistré");
     } catch (SQLException ex) {
         Logger.getLogger(GalerieServices.class.getName()).log(Level.SEVERE, null, ex);
         Notification.notificationError("ERREUR", "Image invalide");
     }
        
    }

    @Override
    public List<GalerieImage> selectImageById(int idUser) {
       String req="SELECT * FROM galeries WHERE fk_idUSer=?";
       PreparedStatement ps;
     try {
         ps=cnx.prepareStatement(req);
         ps.setInt(1, idUser);
         ResultSet res= ps.executeQuery();
         List<GalerieImage> listImg =new ArrayList<>();
         while (res.next()) {             
             GalerieImage gi = new GalerieImage(res.getInt(1), res.getInt(3), res.getString(2));
             listImg.add(gi);
         }
         return listImg;
     } catch (SQLException ex) {
         Logger.getLogger(GalerieServices.class.getName()).log(Level.SEVERE, null, ex);
     }
     return null;
    }

    @Override
    public void deleteImg(int id) {
       String req = "DELETE FROM galeries WHERE id = ?";
       PreparedStatement ps ; 
     try {
         ps=cnx.prepareStatement(req);
          ps.setInt(1, id);
          ps.executeUpdate();
          ps.close();
          Notification.notificationSuccess("SUCCEES", "Votre photo supprimé avec succées");
     } catch (SQLException ex) {
         Logger.getLogger(GalerieServices.class.getName()).log(Level.SEVERE, null, ex);
         Notification.notificationError("ERREUR", "Cette photo n'éxiste pas");
     }
      
    }
    
}
