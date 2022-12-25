/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.AffichageListeSalle;

import gui.PssAffiche.PssAfficheController;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.MaterielSalle;

/**
 * FXML Controller class
 *
 * @author HSOUNA
 */
public class ItemMaterielAfficheController implements Initializable {

    @FXML
    private VBox itemMateriel;
    @FXML
    private ImageView image;
    @FXML
    private Label nomMateriel;
    @FXML
    private Label Specialite;
    @FXML
    private Label Quantite;
    @FXML
    private Label descreption;
     private MaterielSalle Materiel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void  setData (MaterielSalle MaterielSalle) throws URISyntaxException   {
       
    
    this.Materiel=MaterielSalle;
//        System.out.println(Materiel);
//       id.setText(String.valueOf(Materiel.getId()));

        nomMateriel.setText("Nom: "+Materiel.getNomMaterial());
        Specialite.setText("Type Sport:\n"+Materiel.getSpecialite());
        descreption.setText("Desciption:\n"+Materiel.getDescription());
        Quantite.setText("Quantité:\n"+String.valueOf(Materiel.getQuantite()));
  
         
   Image img;
        try {
            img = new Image(getClass().getResource("../uicontrolers/materiel/" + Materiel.getImageVitrine()).toURI().toString());
      
         
            image.setImage(img);
        } catch (URISyntaxException ex) {
            Logger.getLogger(PssAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }
    
    
}
