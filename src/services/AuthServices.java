/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IAuthentification;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Utilisateur;
import org.json.JSONArray;
import supportini.MainSupportini;
import util.JWebToken;
import util.MaConnexion;
import util.Notification;
import util.Validation;

/**
 *
 * @author Asus
 */
public class AuthServices implements IAuthentification {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override

    public Utilisateur login(String email, String pwd) {
        String req = "SELECT * FROM utilisateurs WHERE email = ? ";

        try {
            String hachePwd = Validation.hachePassword(pwd);

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
       
            ResultSet res = ps.executeQuery();
            Utilisateur existUser = new Utilisateur();
            res.first();
            existUser.setId(res.getInt(1));
            existUser.setNom(res.getString(2));
            existUser.setPrenom(res.getString(3));
            existUser.setCin(res.getString(4));
            existUser.setEmail(res.getString(5));
            existUser.setPassword(res.getString(6));
            existUser.setIdRole(res.getInt(11));
            existUser.setPhone(res.getString(7));
            existUser.setImageName(res.getString(8));
            existUser.setStatus(res.getInt(9));
            ps.close();
            System.out.println(existUser.getStatus());
//           
//          if (existUser.getPassword().equals(hachePwd)){
              if(existUser.getStatus()!=0){
                     return existUser; 
              }else{
                  Notification.notificationError("ERREUR", "Compte bannie! Cause Mal Comportement");
              }
        
//          }else{
//            Notification.notificationError("DESOLE", "Mot de Passe incorrect !!");
//          }
        } catch (Exception ex) {
             Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);

            Notification.notificationError("DESOLE", "E-mail incorrect !!");
        }
return null ;
    }
  private void redirectToDashboard (Button btn) throws IOException{
         Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../gui/dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../gui/uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
     
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
