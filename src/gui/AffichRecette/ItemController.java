package gui.AffichRecette;

import gui.PssAffiche.PssAfficheController;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import interfaces.RecetteListener;
import model.Recette;


public class ItemController {

    @FXML
    private Label nameLabel;
    private ImageView img;
    @FXML
    private ImageView imgRecette;

private Recette Recette;
    private RecetteListener RecetteListener;
    @FXML
    private void click(MouseEvent mouseEvent) {
      RecetteListener.onClickListener(Recette);
    }
 

    public void setData(Recette Recette, RecetteListener RecetteListener) {
      this.Recette = Recette;
        System.out.println(Recette);
        this.RecetteListener = RecetteListener;
        nameLabel.setText(Recette.getNomrecette());
        
 Image im;
      
        try {
            im = new Image(getClass().getResource("../images/" + Recette.getImage()).toURI().toString());
          
            imgRecette.setImage(im);
    
        } catch (URISyntaxException ex) {
            Logger.getLogger(PssAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
