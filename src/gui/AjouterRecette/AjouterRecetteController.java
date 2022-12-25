/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.AjouterRecette;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import interfaces.IRecette;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.RecetteService;
import model.Recette;
import util.Notification;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class AjouterRecetteController implements Initializable {

    @FXML
    private TextField Txtnom;
    @FXML
    private TextArea TxtIng;
    @FXML
    private TextArea Txtpreparation;
      private String nameImage;

      final FileChooser fc = new FileChooser();
    private File file;
    File xxx = null;
    @FXML
    private ImageView img;
    @FXML
    private Button AjouterRec;
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
    private void AjouterRecette(ActionEvent event) throws IOException {
        
        IRecette ip = new RecetteService();
        Recette p = new Recette();

//       Coachings p = new Coachings(Integer.parseInt(txtIdCoach.getText()),txtTitre.getText(),  txtDiscipline.getText(), txtDescription.getText(),txtPlaning.getText(), txtPrix.getText());
       p.setNomrecette(Txtnom.getText());
       p.setIngredien(TxtIng.getText());
       p.setPrepatation(Txtpreparation.getText());
       p.setIdRegime(Statics.rr.getIdregime());

 
        p.setImage(nameImage);
        System.out.println(nameImage);
        ip.ajouterRecette(p);
        
        Stage stage = (Stage)Txtnom.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Notification.notificationSuccess("RECETTE AJOUTE AVEC SUCCES", "MERCI POUR VOTRE CONTRIBUTION ");
        
    }

    @FXML
    private void AjouterImage(ActionEvent event) throws IOException {
        fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {

            String x = file.getAbsolutePath();
            String newpath = "src/gui/images/";
            File dir = new File(newpath);
            if (!dir.exists()) {
                // folder wa7dd ken barchaa mkdirs
                dir.mkdirs();
            }
            File sourceFile = null;
            File destinationFile = null;
            String extension = x.substring(x.lastIndexOf('.') + 1);
            sourceFile = new File(x);
            String name = randomStringforimage();
            xxx = new File(newpath + name + "." + extension);
            Files.copy(sourceFile.toPath(), xxx.toPath());
            //   System.out.println(destinationFile);
            nameImage = name + "." + extension;

            img.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("file is invalide");
        }
    }
    private String randomStringforimage() {
        //   String  randomString  =null;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 12;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();

        return randomString;
        
   }     

    @FXML
    private void RetourToEspace(ActionEvent event) throws IOException {
        Stage stage = (Stage)btnRetour.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
