/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashPss;

import Exception.AuthException;
import gui.ItemSalleSport.ItemSalleSportFXMLController;
import gui.addSalleDeSport.AddSalleDeSportController;
import gui.dashboard.DashboardFXMLController;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.json.JSONException;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class DashPssController implements Initializable {

    @FXML
    private HBox hboxScene;

    @FXML
    private Button goToAddSalle;


    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
                Preferences userPreferences = Preferences.userRoot();
       
        String bearerToken = userPreferences.get("BearerToken", "root");
     
        JWebToken incomingToken;

     
    try {
        incomingToken = new JWebToken(bearerToken);
           if (!incomingToken.isValid()) {

//                get id and idRole for current user
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                int idRole = Integer.parseInt(audience);
                int idUser = Integer.parseInt(subject);
         
    }  
    } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
        Logger.getLogger(DashPssController.class.getName()).log(Level.SEVERE, null, ex);
    }
           

    }
    @FXML
    private void redirectToAddSalleSport (ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("../addSalleDeSport/addSalleDeSport.fxml"));
 
         
        
        hboxScene.getChildren().removeAll();
        hboxScene.getChildren().setAll(root);
    }  
    
}

