/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.choice_profil;

import gui.login.LoginFXMLController;
import interfaces.IAuthentification;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Utilisateur;
import org.json.JSONException;
import services.AuthServices;
import services.UtilisateurServices;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ChoiceProfilFXMLController implements Initializable {

    @FXML
    private Button entrainé;
    @FXML
    private Button coach;
    @FXML
    private Button PSS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void secondStepRegister(ActionEvent event) {
        entrainé.setOnAction(e -> {
            redirect("../infoTrainee/InfoTraineeFXML.fxml", entrainé);
        });
        coach.setOnAction(e -> {
            Preferences userPreferences = Preferences.userRoot();
            String idUser = userPreferences.get("x_id_user", "root");
            IUtilisateur iu = new UtilisateurServices();
            iu.updateRoleUser(Integer.parseInt(idUser), 3);
            redirect("../infoCoach/InfoCoachFXML.fxml", coach);
        });
        PSS.setOnAction(e->{
            Preferences userPreferences = Preferences.userRoot();
            String idUser = userPreferences.get("x_id_user", "root");
            IUtilisateur iu = new UtilisateurServices();
            iu.updateRoleUser(Integer.parseInt(idUser), 4);
            Utilisateur newUser = iu.queryUserById(Integer.parseInt(idUser));
             IAuthentification ia = new AuthServices();
            LoginFXMLController login = new LoginFXMLController();
             Preferences userPerferences = Preferences.userRoot();
             String pwd = userPerferences.get("pwd", "root");
            if (ia.login(newUser.getEmail(), pwd)instanceof Utilisateur){
                try {
                    login.generateCurrentUserJwt(newUser);
                     String prenom =newUser.getPrenom().substring(0, 1).toUpperCase() + newUser.getPrenom().substring(1);
                      Notification.notificationSuccess("INSCRIPTION AVEC SUCCES", "Bienvenue, "+prenom);
                      login.redirectToDashboard(event,PSS);
                } catch (JSONException | InvalidKeyException | IOException ex) {
                    Logger.getLogger(ChoiceProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
          
            }
            
        });
    }

    private void redirect(String path, Button btn) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.sizeToScene();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}