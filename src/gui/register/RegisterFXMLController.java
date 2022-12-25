/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.register;

import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.stage.Stage;
import javafx.stage.Window;
import model.Utilisateur;

import services.UtilisateurServices;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-130
 */
public class RegisterFXMLController implements Initializable {

    @FXML
    private TextField nomUser;
    @FXML
    private TextField prenomUser;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button submitRegister;
    @FXML
    private TextField cin;
    @FXML
    private TextField conf_pass;
    @FXML
    private Label validationNom;
    @FXML
    private Label validationPrenom;
    @FXML
    private Label validationEmail;
    @FXML
    private Label validationPass;
    @FXML
    private Label validationCPass;
    @FXML
    private Label validationCin;
    @FXML
    private Hyperlink login;
    @FXML
    private TextField numPhone;
    @FXML
    private Label phoneValid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    IUtilisateur iu = new UtilisateurServices();
//        System.out.println(iu.displayUser());
    }

    @FXML
    private void register(ActionEvent event) throws IOException {

        final String regex = "^(.+)@(\\S+)$";
        clear();
        if (nomUser.getText().isEmpty()) {
            validationNom.setText("Please enter your name");
            return;
        }
        if (prenomUser.getText().isEmpty()) {
            validationPrenom.setText("Please enter your prénom");
            return;
        }
        if (email.getText().isEmpty()) {
            validationEmail.setText("Please enter your email");
            return;
        }

        if (!Validation.patternMatches(email.getText(), regex)) {
            validationEmail.setText("Please entrer un email existe");
            return;
        }

        if (password.getText().isEmpty()) {
            validationPass.setText("Please enter a password");
            return;
        }
        if (!(Validation.validationPasswordLength(password, validationPass) && Validation.validationPasswordCompose(password, validationPass))) {

            return;
        }

        if (!password.getText().equals(conf_pass.getText())) {
            validationCPass.setText("Please confirme ton mot de passe !!");
            return;
        }
        if (cin.getText().isEmpty()) {
            validationCin.setText("Please enter a numero cin");
            return;
        }
        if (cin.getText().length() != 8) {
            validationCin.setText("Il faut 8 chiffre");
            return;
        }
        if (!Validation.validationInteger(numPhone, phoneValid)) {
            return;
        }
        if (numPhone.getText().length() != 8) {
            phoneValid.setText("Num de portable doit 8 chiffres!!");
            return;
        }

        IUtilisateur iu = new UtilisateurServices();

        Utilisateur newUser = new Utilisateur(nomUser.getText(), prenomUser.getText(), password.getText(), email.getText(), cin.getText(),numPhone.getText());
        Preferences userPerferences = Preferences.userRoot();
        userPerferences.put("pwd", password.getText());
        
        iu.addUser(newUser);
        Utilisateur queryUser = iu.queryByCin(newUser.getCin());
        System.out.println(queryUser);
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("x_id_user", String.valueOf(queryUser.getId()));
        Parent root = FXMLLoader.load(getClass().getResource("../choice_profil/ChoiceProfilFXML.fxml"));
        Stage stage = (Stage) submitRegister.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.sizeToScene();
    }

    private void clear() {

        validationNom.setText("");

        validationPrenom.setText("");

        validationEmail.setText("");

        validationPass.setText("");

        validationCPass.setText("");

        validationCin.setText("");

        phoneValid.setText("");
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException {

        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../login/LoginFXML.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Se Connecter chez Supportini");
        primaryStage.setScene(scene);
       
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
