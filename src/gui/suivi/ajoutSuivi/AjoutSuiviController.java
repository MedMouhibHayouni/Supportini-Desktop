/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.ajoutSuivi;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import gui.suivi.suivitrainer.SuiviTrainerController;
import interfaces.ICoach;
import interfaces.IEntrainee;
import interfaces.IUtilisateur;
import interfaces.Isuivi;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Suivi;
import org.json.JSONException;
import services.CoachServices;
import services.EntraineeServices;
import services.Suivie_Services;
import services.UtilisateurServices;
import util.JWebToken;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class AjoutSuiviController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private HBox sceneAdd;
    @FXML
    private TextField poidsajout;
    @FXML
    private TextField tailleajout;
    @FXML
    private Label erpoids;
    @FXML
    private Label ertaille;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void closescene(ActionEvent event) throws URISyntaxException {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
            int idUser = Integer.parseInt(subject);
            Suivi suivi = new Suivi();
            IUtilisateur iu = new UtilisateurServices();
            IEntrainee ie = new EntraineeServices();
            int identrain = ie.queryById(idUser).getId();
            int age = ie.queryById(idUser).getAge();
            String nom = iu.queryUserById(idUser).getNom();
            String prenom = iu.queryUserById(idUser).getPrenom();
            Isuivi is = new Suivie_Services();
            ICoach ic = new CoachServices();
            int idcoach = is.afficherEntrainerList().getId_coach();

//            int idcoach = ic.queryById(idUser).getId();
            double imc = is.queryById(idUser).getImc();
            long miliseconds = System.currentTimeMillis();
            Date date = new Date(miliseconds);
            try {
                if (!Validation.validationInteger(poidsajout, erpoids) && !Validation.validationInteger(tailleajout, ertaille)) {
                    poidsajout.setStyle("-fx-border: 12px; -fx-text-box-border: red; -fx-focus-color: red;");
                    tailleajout.setStyle("-fx-border: 12px; -fx-text-box-border: red; -fx-focus-color: red;");
                } else if (tailleajout.getText().length() != 3) {
                    ertaille.setText("Taille doit se composer de 3 chiffres");
                    tailleajout.setStyle("-fx-border: 12px; -fx-text-box-border: red; -fx-focus-color: red;");
                    return;
                } else {
                    poidsajout.setStyle("-fx-border: 12px; -fx-text-box-border: green; -fx-focus-color: green;");
                    poidsajout.setStyle("-fx-border: 12px; -fx-text-box-border: green; -fx-focus-color: green;");

                    Suivi s = new Suivi(identrain, age, Integer.parseInt(tailleajout.getText()), Integer.parseInt(poidsajout.getText()), date, nom, prenom, imc, idcoach);
//           Suivi s = new Suivi(idUser, age ,,, date, nom, prenom);
                    is.ajouterSuivi(s);
                    SuiviTrainerController st = new SuiviTrainerController();
                    //st.refreshing(idUser);
                    sceneAdd.getChildren().clear();
                }

            } catch (Exception e) {
            }
        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//        Stage stage = (Stage) closebtn.getScene().getWindow();
//        // do what you have to do
//        stage.close();
}
