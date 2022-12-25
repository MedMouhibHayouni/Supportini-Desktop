/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.EspaceCoach;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.json.JSONException;
/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class EspaceCoachController implements Initializable {

    @FXML
    private Button btnAnnonce;
    @FXML
    private Button btnRegime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RedirectToCachings(ActionEvent event) throws IOException {

         Stage stage = (Stage)btnAnnonce.getScene().getWindow();
             stage.close();
             
              Parent root = FXMLLoader.load(getClass().getResource("../Annonce/AjouterAnnonce.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void RedirectToRegime(ActionEvent event) throws IOException {
        
             Stage stage = (Stage)btnAnnonce.getScene().getWindow();
             stage.close();
             
              Parent root = FXMLLoader.load(getClass().getResource("../AffichageRegime/ListRegime.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
