/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.AffichAnnonce;

import gui.PssAffiche.PssAfficheController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Annonce;
import interfaces.AnnonceListener;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

 
public class ItemAnnonceController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;

   private Annonce Annonce;
   private AnnonceListener AnnonceListener;
    @FXML
    private void click(MouseEvent event) {
        AnnonceListener.onClickListener(Annonce);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
  
     public void setData(Annonce Annonce, AnnonceListener AnnonceListener) {
         this.Annonce=Annonce;
     this.AnnonceListener=AnnonceListener;
        nameLabel.setText(Annonce.getTitre());

        
        Image im;
        try {
            im = new Image(getClass().getResource("../uicontrolers/images/" + Annonce.getImage()).toURI().toString());
            img.setImage(im);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ItemAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
    }    

    

