/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import interfaces.IRecette;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Recette;
import util.MaConnexion;
/**
 *
 * @author Elife-Kef-114
 */
public class RecetteService implements IRecette{
    
    Connection cnx = MaConnexion.getInstance().getCnx();
   
    private static IRecette RecetteIns;

    public static IRecette getInstance() {
        if (RecetteIns == null) {
            RecetteIns = new RecetteService();
        }
        return RecetteIns;
    }

    @Override
    public void ajouterRecette(Recette r) {
      
        String req = "INSERT INTO `recette`(`nomrecette`, `ingredien`, `preparatin`, `imagerecette`,`idregime`) VALUES (?,?,?,?,?)";
           try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getNomrecette());
            ps.setString(2,r.getIngredien());
            ps.setString(3, r.getPrepatation());
            ps.setString(4, r.getImage());
            ps.setInt(5, r.getIdRegime());
               System.out.println(r.getImage());
            
//            
            
            
            ps.executeUpdate();
         
        } catch (SQLException ex) {
            Logger.getLogger(RecetteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
