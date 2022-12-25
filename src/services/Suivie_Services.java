/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import interfaces.ICoach;
import interfaces.IDemandeSuivi;
import interfaces.IEntrainee;
import interfaces.Isuivi;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.scene.chart.XYChart;
import model.Demande_Suivi;
import model.Entrainee;
import model.Suivi;
import model.Utilisateur;
import org.json.JSONException;
import util.JWebToken;
import util.MaConnexion;
import util.Notification;

/**
 *
 * @author GIGABYTE
 */
public class Suivie_Services implements Isuivi {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterSuivi(Suivi s) {
        String req = "INSERT INTO `suivi`(`nom`, `prenom`, `age`, `poids`, `taille`, `imc`, `date_suivi`, `fk_id_entr`, `fk_id_coach`) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println(s);
            ps.setString(1, s.getNomE());
            ps.setString(2, s.getPrenomE());
            ps.setInt(3, s.getAge());
            ps.setInt(4, s.getPoidsActuelle());
            ps.setInt(5, s.getTaille());
            ps.setDate(7, s.getDateSuivi());
            ps.setDouble(6, s.getImc());
            ps.setInt(8, s.getFk_id_entr());
            ps.setInt(9, s.getId_coach());
            ps.executeUpdate();
            System.out.println("PS : Suivi ajoutée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerSuivi(Suivi s) {
        String req = "DELETE FROM `suivi` WHERE `nom`= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, s.getNomE());
            ps.executeUpdate();
            System.out.println("PS : Personne supprimé avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierSuivi(Suivi s) {

    }

    @Override
    public Suivi afficherEntrainerList() {

        List<Suivi> suivis = new ArrayList<>();
        Suivi s = new Suivi();
        String req = "SELECT * FROM `suivi`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                s.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
                s.setFk_id_entr(rs.getInt(9));
                s.setId_coach(rs.getInt(10));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
                if (suivis != null && !suivis.isEmpty()) {
                    s = suivis.get(suivis.size() - 1);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    @Override
    public Set<Suivi> afficherEntrainer(int id) {

        Set<Suivi> suivis = new HashSet<>();
        String req = "SELECT * FROM suivi WHERE fk_id_coach = ? ORDER BY date_suivi DESC";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
                s.setPoidsActuelle(rs.getInt("poids"));
                s.setDateSuivi(rs.getDate("date_suivi"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
                s.setAge(rs.getInt("age"));
                s.setTaille(rs.getInt("taille"));
                s.setFk_id_entr(rs.getInt("fk_id_entr"));
                s.setId_coach(rs.getInt("fk_id_coach"));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suivis;
    }

    @Override
    public void afficherChart(String nom) {

        List<Suivi> suivis = new ArrayList<>();
        String req = "SELECT poids,date FROM `suivi`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Suivi s = new Suivi();
//                p.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
                s.setPoidsActuelle(rs.getInt("poids"));
                s.setDateSuivi(rs.getDate("date_suivi"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
    }

    @Override
    public List<Suivi> afficherselondate() {

        List<Suivi> suivis = new ArrayList<>();
        String req = "SELECT * FROM `suivi`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Suivi s = new Suivi();
//                p.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
                s.setPoidsActuelle(rs.getInt("poids"));
                s.setDateSuivi(rs.getDate("date_suivi"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suivis;
    }

    @Override
    public Suivi queryById(int id) {

        List<Suivi> suivis = new ArrayList<>();

        Suivi date_suivi = new Suivi();
        String req2 = "SELECT * FROM suivi WHERE fk_id_entr = ? ORDER BY date_suivi ASC";
        try {
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {

                //res.first();
                date_suivi.setId(res.getInt(1));
                
//                date_suivi.setNomE(res.getString(2));
//                date_suivi.setPrenomE(res.getString(3));
                date_suivi.setPoidsActuelle(res.getInt(5));
                date_suivi.setDateSuivi(res.getDate(8));
//                date_suivi.setAge(res.getInt(4));
                date_suivi.setTaille(res.getInt(6));
                date_suivi.setImc(res.getDouble(7));
                date_suivi.setFk_id_entr(res.getInt(10));
                date_suivi.setId_coach(res.getInt(9));
                suivis.add(date_suivi);
                
                if (suivis != null && !suivis.isEmpty()) {
                    date_suivi = suivis.get(suivis.size() - 1);

                }

                //ps.close();
            }
        } catch (SQLException ex) {
            Notification.notificationError("DESOLE", "Vous allez inscrire d'abord avec l'un de nos Coach");
            return null;
        }
        return date_suivi;
    }

    @Override
    public Set<Suivi> queryByidCoach(int id) {

        Set<Suivi> suivis = new HashSet<>();
        String req = "SELECT * FROM suivi WHERE fk_id_coach = ? ORDER BY date_suivi ASC";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Suivi s = new Suivi();
                s.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
                s.setPoidsActuelle(rs.getInt("poids"));
                s.setDateSuivi(rs.getDate("date_suivi"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
                s.setAge(rs.getInt("age"));
                s.setTaille(rs.getInt("taille"));
                s.setFk_id_entr(rs.getInt("fk_id_entr"));
                s.setId_coach(rs.getInt("fk_id_coach"));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suivis;
    }

}
