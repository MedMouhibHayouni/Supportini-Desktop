/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.RRegime;

import Exception.AuthException;

import gui.AffichCoaching.ListCoachings;
import interfaces.IRegime;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;
import services.RegimeService;
import util.JWebToken;
import model.Regime;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class AjouterRegimeController implements Initializable {

    @FXML
    private TextField TxtNom;
    private TextField TxtIMC;
    @FXML
    private CheckBox vegetarien;
    @FXML
    private CheckBox vegan;
    @FXML
    private CheckBox omnivore;
    @FXML
    private Button BtnPublier;
    private TextArea TxtDescription;
  private int idRole , idUser ; 
  
  
  
  
   
      PreparedStatement pst,ps;
    @FXML
    private TextField TxtKg;
    @FXML
    private TextArea TxtPtDej;
    @FXML
    private TextArea TxtDinner;
    @FXML
    private TextArea TxtDej;
    @FXML
    private Button btnRetour;
    @FXML
    private Label labVerif;
    /**
     * Initializes the controller class.
     */
  
    
    
     private void clear() {

        labVerif.setText("");

    

    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
       //recieve the bearer token
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
               if (!incomingToken.isValid()) {

//                get id and idRole for current user
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                idRole= Integer.parseInt(audience);
                 idUser =        Integer.parseInt(subject);
                   System.out.println(idUser);
                
            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(ListCoachings.class.getName()).log(Level.SEVERE, null, ex); ////////ItemDashFXMLController////
        }
    }    

    @FXML
    private void Publier(ActionEvent event) throws IOException {
         IRegime Rg = new RegimeService();
         Regime r = new Regime() ; 
       
          r.setNom(TxtNom.getText());
          //////////////////////////////////
  
           clear();
          
         if (TxtNom.getText().isEmpty()) {
            labVerif.setText("Entrez un nom");
            return;
        }
          
          
               if (TxtKg.getText().isEmpty()) {
            labVerif.setText("Entrez un nombre de kg");
            return;
        }
          
          
           if (TxtPtDej.getText().isEmpty()) {
            labVerif.setText("Entrez un petit dejene");
            return;
        }
           if (TxtDej.getText().isEmpty()) {
            labVerif.setText("Entrez un dejene");
            return;
        }
           if (TxtDinner.getText().isEmpty()) {
            labVerif.setText("Entrez un dinner");
            return;
        }
          
          
          if (!(vegetarien.isSelected() || vegan.isSelected() || omnivore.isSelected() )) {
             labVerif.setText("Entrez un type");
       
        }
          
          
          
          
          //////////////////////////////////////////////////////
           if (vegetarien.isSelected()) {
            r.setType("vegetarien");
        }
          
              if (vegan.isSelected()) {
            r.setType("vegan");
        }
          
                if (omnivore.isSelected()) {
            r.setType("omnivore");
        }
         
          
          ///////////////////////////////////////
          r.setNbkg(Integer.valueOf(TxtKg.getText()));
        r.setPetitdej(TxtPtDej.getText());
        r.setDej(TxtDej.getText());
        r.setDinner(TxtDinner.getText());
          Rg.ajouterRegime(r);
         Notification.notificationSuccess("REGIME AJOUTER AVEC SUCCES", "Merci pour voter contribution ");
         
         
                       
 Stage stage = (Stage)BtnPublier.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         
    }

    @FXML
    private void RetourToEspace(ActionEvent event) throws IOException {
         Stage stage = (Stage)btnRetour.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
