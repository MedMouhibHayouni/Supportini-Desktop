/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IDemandeSuivi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Demande_Suivi;
import model.Suivi;
import util.MaConnexion;

/**
 *
 * @author GIGABYTE
 */
public class DemandeSuivi_Service implements IDemandeSuivi {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void AjouterDemanderSuivi(Demande_Suivi demande) {

        String req = "INSERT INTO `demande_suivi`(`demande`, `id_entr`, `id_coach`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "Merci d'envoyer votre derniére Suivi");
            ps.setInt(2, demande.getId_entr());
            ps.setInt(3, demande.getId_coach());
            ps.executeUpdate();
            System.out.println("PS : Demande Envoyer avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Demande_Suivi afficherDemandeSuivi(int id) {
        Demande_Suivi ds = new Demande_Suivi();
        List<Demande_Suivi> demandes = new ArrayList<>();
        String req2 = "SELECT * FROM demande_suivi WHERE id_entr = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {

                ds.setId(res.getInt(1));
                ds.setDemande(res.getString(2));

                ds.setId_entr(res.getInt(3));
                ds.setId_coach(res.getInt(4));

                demandes.add(ds);
                if (demandes != null && !demandes.isEmpty()) {
                    ds = demandes.get(demandes.size() - 1);
                }

                //ps.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
        return ds;
    }

}
