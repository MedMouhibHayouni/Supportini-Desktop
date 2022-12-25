/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.PssAffiche;

import gui.PssAfficheList.ListSallePssController;
import interfaces.ISalleSport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.SalleSport;
import services.SalleSportServices;
import util.Notification;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class PssAfficheController implements Initializable {

    @FXML
    private HBox itemsalle;
    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label prix;
    @FXML
    private Label adresse;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;

    private SalleSport Salle;
    private Label id;
    int index = -1;
    private int idRole, idUser, idSalleSport;
    @FXML
    private Hyperlink goToAddMateriel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(SalleSport SalleSport) throws FileNotFoundException {

        this.Salle = SalleSport;
//        System.out.println(Salle);

        nom.setText(Salle.getNomSalle());
        prix.setText(String.valueOf(Salle.getPrix()) + " DT");
        adresse.setText(Salle.getVille() + " ," + Salle.getRue() + " " + Salle.getCodePostal());
        description.setText(Salle.getDescription());
        idSalleSport = Salle.getId();
       
        Image im;
                File initialFile = new File("src/gui/uicontrolers/imageSalleSport/" + Salle.getImageVitrine());

                InputStream is = new FileInputStream(initialFile.getAbsolutePath());;
       
          
            image.setImage(new Image(is));
     

    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        ISalleSport iSport = new SalleSportServices();
        iSport.supprimerSalleSport(Salle);
        
          Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);
       Notification.notificationSuccess("SALLE SUPPRIMER AVEC SUCCESS", "Merci");

    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {

        Statics.xx.setId(Salle.getId());
        Statics.xx.setNomSalle(Salle.getNomSalle());
        Statics.xx.setNumTel(Salle.getNumTel());
        Statics.xx.setVille(Salle.getVille());
        Statics.xx.setRue(Salle.getRue());
        Statics.xx.setCodePostal(Salle.getCodePostal());
        Statics.xx.setPrix(Salle.getPrix());
        Statics.xx.setDescription(Salle.getDescription());
        Statics.xx.setDuration(Salle.getDuration());
        Statics.xx.setImageVitrine(Salle.getImageVitrine());
//                idSalleSport = Salle.getId();
//                      nom.setText(Salle.getNomSalle());
//                       description.setText(Salle.getDescription());

//        Parent root = FXMLLoader.load(getClass().getResource("../modifierSalleSport/ModifierSalleSport.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

  Stage stage = (Stage)description.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("../modifierSalleSport/ModifierSalleSport.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//         Notification.notificationSuccess("SALLE MODIFIER AVEC SUCCESS", "Merci");
        

    }

    @FXML
    private void redirectToAddMateriel(ActionEvent event) throws IOException {
          Stage stage = (Stage) goToAddMateriel.getScene().getWindow();

        stage.close();
        idSalleSport = Salle.getId();
        Statics.xx.setId(idSalleSport);
        Parent root = FXMLLoader.load(getClass().getResource("../addMaterielSalleDeSport/addMaterielSalleDeSport.fxml"));
//        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(idSalleSport);
    }
}
