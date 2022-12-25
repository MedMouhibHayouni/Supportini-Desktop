/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.infoCoach;

import gui.choice_profil.ChoiceProfilFXMLController;
import gui.login.LoginFXMLController;
import interfaces.IAuthentification;
import interfaces.ICoach;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import model.Coach;
import model.Utilisateur;
import org.json.JSONException;
import services.AuthServices;
import services.CoachServices;
import services.UtilisateurServices;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InfoCoachFXMLController implements Initializable {

    @FXML
    private CheckBox fitness;
    @FXML
    private CheckBox musculation;
    @FXML
    private Button confInfCoach;
    @FXML
    private CheckBox dance;
    @FXML
    private CheckBox boxing;
    @FXML
    private CheckBox karate;
    @FXML
    private CheckBox taik;
    @FXML
    private CheckBox autre;
    @FXML
    private Label validationSpec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void secondStepRegister(ActionEvent event) {
        validationSpec.setText("");
        if (!(fitness.isSelected() || musculation.isSelected() || dance.isSelected() || boxing.isSelected() || karate.isSelected() || taik.isSelected() || autre.isSelected())) {
            validationSpec.setText("Précise vos spécialités sportives !!");
            return;
        }
        String DescrSpecialite = "Maitre en:\n";
        if (fitness.isSelected()) {
            DescrSpecialite += "- fitness\n";
        }
        if (musculation.isSelected()) {
            DescrSpecialite += "- musculation\n";
        }
        if (dance.isSelected()) {
            DescrSpecialite += "- dance\n";
        }
        if (boxing.isSelected()) {
            DescrSpecialite += "- boxing\n";
        }
        if (karate.isSelected()) {
            DescrSpecialite += "- karate\n";
        }
        if (taik.isSelected()) {
            DescrSpecialite += "- taikwando\n";
        }
        if (autre.isSelected()) {
            DescrSpecialite += "- autres...";
        }
        ICoach ic = new CoachServices();
        Preferences userPreferences = Preferences.userRoot();
        String idUser = userPreferences.get("x_id_user", "root");
        //fk id planning static will change
        Coach newCoach = new Coach(Integer.parseInt(idUser), DescrSpecialite);
        ic.addCoach(newCoach);
        LoginFXMLController login = new LoginFXMLController();
        IUtilisateur iu = new UtilisateurServices();
        Utilisateur newUser = iu.queryUserById(Integer.parseInt(idUser));
        IAuthentification ia = new AuthServices();
        
             String pwd = userPreferences.get("pwd", "root");
        if (ia.login(newUser.getEmail(), pwd) instanceof Utilisateur) {
            try {
                login.generateCurrentUserJwt(newUser);
                String prenom =newUser.getPrenom().substring(0, 1).toUpperCase() + newUser.getPrenom().substring(1);
                  Notification.notificationSuccess("INSCRIPTION AVEC SUCCES", "Bienvenue notre Coach, "+prenom);
                login.redirectToDashboard(event,confInfCoach);
            } catch (JSONException | InvalidKeyException | IOException ex) {
                Logger.getLogger(ChoiceProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}