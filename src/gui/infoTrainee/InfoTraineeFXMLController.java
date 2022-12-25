/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.infoTrainee;

import gui.choice_profil.ChoiceProfilFXMLController;
import gui.login.LoginFXMLController;
import interfaces.IAuthentification;
import interfaces.IEntrainee;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Entrainee;
import model.Utilisateur;
import org.json.JSONException;
import services.AuthServices;
import services.EntraineeServices;
import services.UtilisateurServices;
import util.Notification;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InfoTraineeFXMLController implements Initializable {

    @FXML
    private TextField ageEnt;
    @FXML
    private TextField tailleEnt;
    @FXML
    private TextField poidsEnt;
    private TextField numPhone;
    @FXML
    private Button confEntInfo;
    @FXML
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
    private Label ageValid;
    private Label phoneValid;
    @FXML
    private Label poidsValid;
    @FXML
    private Label tailleValid;
    @FXML
    private Label sexeValid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void registerInfoAdditionnel(ActionEvent event) {
        clear();
        if (!Validation.validationInteger(ageEnt, ageValid)) {
            return;
        }
          if (Integer.parseInt(ageEnt.getText())< 0 ) {
            ageValid.setText("Age doit etre  positif ");
            return;
        }
        if (!Validation.validationInteger(tailleEnt, tailleValid)) {
            return;
        }
        if (tailleEnt.getText().length() != 3) {
            tailleValid.setText("Taille doit se composer de 3 chiffres");
            return;
        }
         if (Integer.parseInt(tailleEnt.getText())> 210 || Integer.parseInt(tailleEnt.getText())<130) {
            tailleValid.setText("Taille entre 130 cm et 210 cm ");
            return;
        }
           if (Integer.parseInt(poidsEnt.getText())< 0 ) {
            poidsEnt.setText("Poids doit etre  positif ");
            return;
        }
        if (!Validation.validationInteger(poidsEnt, poidsValid)) {
            return;
        }

        if (!(femme.isSelected() || homme.isSelected())) {
            sexeValid.setText("Ajouter votre sexe !!");
            return;
        }
        Preferences userPreferences = Preferences.userRoot();
        String idUser = userPreferences.get("x_id_user", "root");
        IEntrainee ie = new EntraineeServices();
        IUtilisateur iu = new UtilisateurServices();
        IAuthentification ia = new AuthServices();
        LoginFXMLController login = new LoginFXMLController();
          String pwd = userPreferences.get("pwd", "root");
        if (homme.isSelected()) {
            Entrainee ent = new Entrainee(Integer.parseInt(idUser), Integer.parseInt(ageEnt.getText()), Integer.parseInt(tailleEnt.getText()), Integer.parseInt(poidsEnt.getText()), "homme");
            ie.addEntrainee(ent);
            Utilisateur newUser = iu.queryUserById(Integer.parseInt(idUser));
            if (ia.login(newUser.getEmail(), pwd) instanceof Utilisateur) {
                try {
                    login.generateCurrentUserJwt(newUser);
                    login.redirectToDashboard(event, confEntInfo);
                } catch (JSONException | InvalidKeyException | IOException ex) {
                    Logger.getLogger(ChoiceProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            Entrainee ent = new Entrainee(Integer.parseInt(idUser), Integer.parseInt(ageEnt.getText()), Integer.parseInt(tailleEnt.getText()), Integer.parseInt(poidsEnt.getText()), "femme");
            ie.addEntrainee(ent);
            Utilisateur newUser = iu.queryUserById(Integer.parseInt(idUser));
            if (ia.login(newUser.getEmail(), pwd) instanceof Utilisateur) {
                try {
                    login.generateCurrentUserJwt(newUser);
                    String prenom =newUser.getPrenom().substring(0, 1).toUpperCase() + newUser.getPrenom().substring(1);
                    Notification.notificationSuccess("INSCRIPTION AVEC SUCCES", "Bienvenue, "+prenom);
                    login.redirectToDashboard(event,confEntInfo);
                } catch (JSONException | InvalidKeyException | IOException ex) {
                    Logger.getLogger(ChoiceProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    public void clear() {

        ageValid.setText("");

        tailleValid.setText("");

        poidsValid.setText("");

        sexeValid.setText("");
    }
}