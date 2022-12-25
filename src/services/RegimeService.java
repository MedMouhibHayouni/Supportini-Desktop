/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IRegime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import model.Regime;
import util.MaConnexion;
/**
 *
 * @author Elife-Kef-114
 */
public class RegimeService implements IRegime{
Connection cnx = MaConnexion.getInstance().getCnx();


 private static IRegime Regimes;

    public static IRegime getInstance() {
        if (Regimes == null) {
            Regimes = new RegimeService();
        }
        return Regimes;
    }
  
  
    
    
    @Override
    public void ajouterRegime(Regime r) {
        String req = "INSERT INTO `regime`( `nom`, `type`, `nbrkg`,`petidej`,`dejeuner`,`dinner`) VALUES (?,?,?,?,?,?)";
        
         try {
            PreparedStatement ps = cnx.prepareStatement(req);
        
            ps.setString(1,r.getNom());
            ps.setString(2, r.getType());
            ps.setFloat(3, r.getNbkg());
            ps.setString(4, r.getPetitdej());
              ps.setString(5, r.getDej());
           
               ps.setString(6, r.getDinner());
           
           
//            
            
            
            ps.executeUpdate();
            System.out.println("PS : regime  ajoutée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(RegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
    
    @Override
    public ObservableList<Regime> table() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
