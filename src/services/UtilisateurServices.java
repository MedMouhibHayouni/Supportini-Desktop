/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import gui.choice_profil.ChoiceProfilFXMLController;
import gui.login.LoginFXMLController;
import interfaces.IAuthentification;
import interfaces.IUtilisateur;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Utilisateur;
import org.json.JSONException;
import org.json.JSONObject;
import util.MaConnexion;
import util.Notification;
import util.Validation;

/**
 *
 * @author Elife-Kef-130
 */
public class UtilisateurServices implements IUtilisateur {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void addUser(Utilisateur user) {

        String req = "INSERT INTO `utilisateurs`(`nom`, `prenom`, `cin`, `email`, `password`,`phone`)  VALUES (?,?,?,?,?,?)";
        try {
            String mdp = Validation.hachePassword(user.getPassword());

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getCin());
            ps.setString(4, user.getEmail());
            ps.setString(5,  mdp);
            ps.setString(6, user.getPhone());
            ps.executeUpdate();
           
            ps.close();
        } catch (Exception ex) {
            System.err.println("Utilisateur existe déja");
            Notification.notificationError("DESOLE", "E-mail ou Mot De Passe existe déja !!");
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex); 
        }

    }

    @Override
    public List displayUser() {
        List<Utilisateur> listUser = new ArrayList<>();
        String req = "SELECT * FROM utilisateurs";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt(1));
                user.setNom(rs.getString(2));
                user.setPrenom(rs.getString(3));
                user.setCin(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setIdRole(rs.getInt(11));
                user.setPhone(rs.getString(7));
                 user.setImageName(rs.getString(8));
                  user.setStatus(rs.getInt(9));
                listUser.add(user);

            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);

        }
        return listUser;
    }

    @Override
    public Utilisateur queryUserById(int id) {
    
        String req = "SELECT * FROM utilisateurs WHERE id=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            res.first();
            Utilisateur user = new Utilisateur();
            user.setId(res.getInt(1));
            user.setNom(res.getString(2));
            user.setPrenom(res.getString(3));
            user.setCin(res.getString(4));
            user.setEmail(res.getString(5));
            user.setPassword(res.getString(6));
            user.setIdRole(res.getInt(11));
            user.setPhone(res.getString(7));
            user.setImageName(res.getString(8));
             user.setStatus(res.getInt(9));
            ps.close();
           
            return user;

        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }

    }

    @Override
    public void updateUser(Utilisateur user) {
        String req = "UPDATE utilisateurs SET `nom`=?,`prenom`=?,`email`=?,`password`=?,`phone`=? WHERE id=?";
       
        try {
             String hachePwd = Validation.hachePassword(user.getPassword());
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, hachePwd);
            ps.setString(5, user.getPhone());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
          Notification.notificationSuccess("SUCCEE", "  Tes Données sont enregistré");
            ps.close();
        } catch (SQLException ex) {
     
        } catch (Exception ex) {
         
        }

    }

    @Override
    public void deleteUser(Utilisateur user) {
        String req = "DELETE FROM utilisateurs WHERE cin=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getCin());
            ps.executeUpdate();
            System.out.println("Utilisateur supprimé avec succés");
            ps.close();
        } catch (SQLException ex) {
     
        }

    }

    @Override
    public void updateRoleUser(int id, int role) {
        String req = "UPDATE `utilisateurs` SET `fk_idRole`=? WHERE `id`= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, role);
            ps.setInt(2, id);

            ps.executeUpdate();
            ps.close();
            
          
            
        } catch (SQLException ex) {
        
        }

    }

    @Override
    public Utilisateur queryByCin(String cin) {
        String req = "SELECT * FROM utilisateurs WHERE cin=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, cin);
            ResultSet res = ps.executeQuery();
            res.first();
            Utilisateur user = new Utilisateur();
            user.setId(res.getInt(1));
            user.setNom(res.getString(2));
            user.setPrenom(res.getString(3));
            user.setCin(res.getString(4));
            user.setEmail(res.getString(5));
            user.setPassword(res.getString(6));
            user.setIdRole(res.getInt(11));
            user.setPhone(res.getString(7));
            user.setImageName(res.getString(8));
            user.setStatus(res.getInt(9));
            ps.close();
            return user;

        } catch (SQLException ex) {
          
            return null;
        }
    }

    @Override
    public void uploadUserImg(String path,int id) {
        String req="UPDATE utilisateurs SET image_name =? WHERE id=?";
        try {
            System.out.println(path+" "+id);
            PreparedStatement ps =  cnx.prepareStatement(req);
           
            ps.setString(1, path);
            ps.setInt(2, id);
             System.out.println(ps);
            ps.executeUpdate();
            Notification.notificationSuccess("SUCCEES", "Photo Enregistrée");
//            ps.close();
        } catch (SQLException ex) {
             
            Notification.notificationError("ERREUR", "Fichier Incompatible");
            
        }
    }

    @Override
    public void banUser(int id,int status ) {
        System.out.println("service "+status);
        String req="UPDATE utilisateurs SET status=? WHERE id=?";
          try {
            PreparedStatement ps =  cnx.prepareStatement(req);
            if(status==1){
                ps.setInt(1, 0); 
            }else{
                ps.setInt(1, 1); 
            }
           
            ps.setInt(2, id);
            ps.executeUpdate();
            if(status==1){
                 
                      Notification.notificationSuccess("SUCCEES", "Utilisateur a été bannie"); 
            }else {
                  Notification.notificationSuccess("SUCCEES", "Utilisateur a été unbannie");
            }
          
            ps.close();
             } catch (SQLException ex) {
            Notification.notificationError("ERREUR", "ban est insuccée");
            
        }
    }

    @Override
    public List queryUserByRoleId(int idRole) {
          List<Utilisateur> listUser = new ArrayList<>();
        String req = "SELECT * FROM utilisateurs WHERE fk_idRole = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idRole);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt(1));
                user.setNom(rs.getString(2));
                user.setPrenom(rs.getString(3));
                user.setCin(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setIdRole(rs.getInt(11));
                user.setPhone(rs.getString(7));
                 user.setImageName(rs.getString(8));
                  user.setStatus(rs.getInt(9));
                listUser.add(user);

            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);

        }
        return listUser;
    }

    @Override
    public void updateUserByAdmin(int id,String nom, String prenom, String email, String cin, String phone, int role) {
        String req = "UPDATE utilisateurs SET `nom`=?,`prenom`=?,`email`=? ,cin=?,`phone`=? , fk_idRole = ? WHERE id=?";
        try {
            PreparedStatement ps= cnx.prepareStatement(req);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, cin);
            ps.setString(5, phone);
            ps.setInt(6, role);
            ps.setInt(7, id);
            ps.executeUpdate();
            ps.close();
            Notification.notificationSuccess("SUCCESS", "Données enregistréé");
        } catch (SQLException ex) {
       
            Notification.notificationError("ERREUR", "Donnée n'est pas enregistré");
        }
        
    }

    @Override
    public int checkUser(String email) {
        String req= "SELECT id FROM utilisateurs WHERE email = ?";
        PreparedStatement ps;
        try {
            ps=cnx.prepareStatement(req);
              ps.setString(1, email);
            
          ResultSet res =  ps.executeQuery();
          res.first();
           int id  = res.getInt(1);
           ps.close();
           return id;
        } catch (SQLException ex) {
           
        }
      return -1;
        
    }

    @Override
    public void resetPwd(int id, String pwd) {
              String req = "UPDATE utilisateurs SET password = ? WHERE id=?";
              PreparedStatement ps;
        try {
            ps=cnx.prepareStatement(req);
            String mdp = Validation.hachePassword(pwd);
            ps.setString(1, mdp);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            Notification.notificationSuccess("SUCCEE", "Mot de Passe Réinitialisé");
        } catch (SQLException ex) {
           
            Notification.notificationError("ERREUR", "Mot de Passe incorrect");
        } catch (Exception ex) {
       
             Notification.notificationError("ERREUR", "Mot de Passe incorrect");
        }
              
    }

}
