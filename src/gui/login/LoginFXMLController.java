/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import interfaces.IAuthentification;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Utilisateur;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import services.AuthServices;
import util.JWebToken;
import util.Notification;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField emailLogin;
    @FXML
    private TextField pwdLogin;
    @FXML
    private Button buttonConf;
    @FXML
    private Label validationEmail;
    @FXML
    private Label validationPass;
    @FXML
    private Hyperlink register;
    @FXML
    private Hyperlink forgetPwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void login(ActionEvent event) throws NoSuchAlgorithmException, JSONException, InvalidKeyException, IOException {
        final String regex = "^(.+)@(\\S+)$";

        if (emailLogin.getText().isEmpty()) {
            validationEmail.setText("Entrez votre e-mail");
            return;
        }
        if (!Validation.patternMatches(emailLogin.getText(), regex)) {
            validationEmail.setText("Please entrer un email existe");
            return;
        }
        if (pwdLogin.getText().isEmpty()) {
            validationPass.setText("Entrez votre mot de passe");
            return;
        }
        IAuthentification ia = new AuthServices();
        if (ia.login(emailLogin.getText(), pwdLogin.getText()) instanceof Utilisateur) {
            generateCurrentUserJwt(ia.login(emailLogin.getText(), pwdLogin.getText()));
            String prenom =ia.login(emailLogin.getText(), pwdLogin.getText()).getPrenom().substring(0, 1).toUpperCase() + ia.login(emailLogin.getText(), pwdLogin.getText()).getPrenom().substring(1);
              Notification.notificationSuccess("INSCRIPTION AVEC SUCCES", "Bienvenue, "+prenom);
              if(ia.login(emailLogin.getText(), pwdLogin.getText()).getIdRole()==1){
                  redirectToDashboardAdmin(event,buttonConf);
              }else {
                 redirectToDashboard(event,buttonConf);  
              }
           
        }
    }

    @FXML
    private void redirectToRegister(ActionEvent event) throws IOException {
        Stage stage = (Stage) register.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../register/RegisterFXML.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Inscription chez Supportini");
        primaryStage.setScene(scene);
     
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void generateCurrentUserJwt(Utilisateur user) throws JSONException, InvalidKeyException {
        JSONObject payload = new JSONObject();
        
        payload.put("sub", String.valueOf(user.getId()));
       payload.put("aud", String.valueOf(user.getIdRole()));
        payload.put("exp", 10);

        String BearerToken = new JWebToken(payload).toString();
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("BearerToken", BearerToken);
     
    }
    public void redirectToDashboard (ActionEvent event,Button btn) throws IOException{
         Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
     
        primaryStage.sizeToScene();
        primaryStage.show();
    }
      private void redirectToDashboardAdmin (ActionEvent event,Button btn) throws IOException{
         Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../admin/dashboard/Dashboard.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Dashboard Administrateur");
        primaryStage.setScene(scene);
     
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    @FXML
        private void redirectToCodeSend () throws IOException{
          Stage stage = (Stage) forgetPwd.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../forgetPassword/sendCodeToEmail.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Envoyée votre code ...");
        primaryStage.setScene(scene);
     
        primaryStage.sizeToScene();
        primaryStage.show(); 
      }
}