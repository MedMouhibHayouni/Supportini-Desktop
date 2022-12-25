/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forgetPassword;

import interfaces.ICodeReset;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.CodeReset;
import services.CodeResetServices;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ValidCodeController implements Initializable {

    @FXML
    private TextField inputCode;
    @FXML
    private Button btnConf;
    private CodeReset code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void checkCode () throws IOException{
        ICodeReset ic= new CodeResetServices();
        code = ic.queryCode(inputCode.getText());
        if(code == null){
            Notification.notificationError("ERREUR", "Code invalide");
            return;
        }
         Timestamp current = new java.sql.Timestamp(new java.util.Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(current.getTime());
        Timestamp now = new Timestamp(cal.getTime().getTime());
        if(now.after(code.getDateExp())){
            Notification.notificationError("DESOLE", "Code est expiré");
            ic.deleteCode(code.getId());
            return;
        }
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("id_user", String.valueOf(code.getIdUser()));
        goToResetPwd();
    }
    private void goToResetPwd () throws IOException{
         Stage stage = (Stage) btnConf.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("./ResetPassword.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Réinitialiser le mot de passe");
        primaryStage.setScene(scene);

        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
