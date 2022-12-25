/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import model.Annonce;
import util.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import interfaces.IAnnonce;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnnonceService implements IAnnonce {

    Connection cnx = MaConnexion.getInstance().getCnx();
    private static IAnnonce Annonces;

    public static IAnnonce getInstance() {
        if (Annonces == null) {
            Annonces = new AnnonceService();
        }
        return Annonces;
    }

    @Override
    public void ajouterAnnonce(Annonce a) {
        String req = "INSERT INTO `annonce`( `titre`, `discipline`,`prix`,`type`,`rue`,`codePostal`,`ville`, `description`,`image`,`capacite`,`dateDebut`,`dateFin`,`fk_idCoach`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, a.getTitre());
            ps.setString(2, a.getDiscipline());
            ps.setInt(3, a.getPrix());
            ps.setString(4, a.getType());
            ps.setString(5, a.getRue());
            ps.setString(6, a.getCodePostal());
            ps.setString(7, a.getVille());

            ps.setString(8, a.getDescription());
            ps.setString(9, a.getImage());
            ps.setInt(10, a.getCapacite());
            ps.setDate(11, a.getDateDebut());
            ps.setDate(12, a.getDateFin());
            ps.setInt(13, a.getFk_idCoach());

//            
            ps.executeUpdate();
            System.out.println("PS : Annonce  ajoutée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(CoachingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
