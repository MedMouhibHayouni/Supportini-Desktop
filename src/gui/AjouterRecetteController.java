/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class AjouterRecetteController implements Initializable {

    @FXML
    private Button AjouterRec;
    @FXML
    private TextField Txtnom;
    @FXML
    private TextField TxtIng;
    @FXML
    private TextField Txtpreparation;
    @FXML
    private ImageView img;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterRecette(ActionEvent event) {
    }

    @FXML
    private void AjouterImage(ActionEvent event) {
    }

    @FXML
    private void RetourToEspace(ActionEvent event) {
    }
    
}
