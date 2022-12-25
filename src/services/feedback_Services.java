/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Ifeedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Coach;
import model.Demande_Suivi;
import model.Feedback;
import util.MaConnexion;

/**
 *
 * @author GIGABYTE
 */
public class feedback_Services implements Ifeedback {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterfeedback(Feedback feedback) {
        String req = "INSERT INTO `feedback`(`feedback`, `id_suivi`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, feedback.getFeedback());
            ps.setInt(2, feedback.getId_suivi());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void supprimerFeedback() {

    }

    @Override
    public void modifierFeedback() {

    }

    @Override
    public Feedback afficherfeedback(int id) {
        Feedback feedback = new Feedback();

        String req2 = "SELECT * FROM feedback WHERE id_suivi = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
               res.first();
                feedback.setId(res.getInt(1));
                feedback.setFeedback(res.getString(2));
                feedback.setId_suivi(res.getInt(3));
                  return feedback;
//                feedbacks.add(feedback);
//                if (feedbacks != null && !feedbacks.isEmpty()) {
//                    feedback = feedbacks.get(feedbacks.size() - 1);
//
//     
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
      
    }

}
