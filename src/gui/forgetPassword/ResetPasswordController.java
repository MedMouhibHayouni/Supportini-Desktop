/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forgetPassword;

import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.UtilisateurServices;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ResetPasswordController implements Initializable {
    private int idUser;
    /**
     * Initializes the controller class.
     */
  
    @FXML
    private TextField inputReset;
    @FXML
    private Label validReset;
    @FXML
    private Button btnConf;
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences userPreferences=Preferences.userRoot();
        idUser= Integer.valueOf(userPreferences.get("id_user", "root"));
        
    }    
    private void clear(){
        validReset.setText("");
    }
    @FXML
    private void resetPwd () throws IOException, BackingStoreException{
        clear();
         if (inputReset.getText().isEmpty()) {
            validReset.setText("Entrer mot de passe");
            return;
        }
        if (!(Validation.validationPasswordLength(inputReset, validReset) && Validation.validationPasswordCompose(inputReset, validReset))) {

            return;
        }
        IUtilisateur iu = new UtilisateurServices();
        iu.resetPwd(idUser, inputReset.getText());
        redirectToLogin();
    }
    private void redirectToLogin () throws IOException, BackingStoreException{
          Stage stage = (Stage) btnConf.getScene().getWindow();
        stage.close();
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.clear();
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
