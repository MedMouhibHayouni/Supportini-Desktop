/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ModifSuppCoaching;

import static gui.ModifSuppCoaching.MesCoaching.idselect;

import gui.PssAffiche.PssAfficheController;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.MaConnexion;
import util.Notification;

import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtDiscipline;
    @FXML
    private TextField txtPlaning;
    @FXML
    private Label validationtRue;
    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtNbMax;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnAjouter;
    @FXML
    private ImageView img;
    @FXML
    private Button btnImage;
    Connection cnx = MaConnexion.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */

   private String nameImage;
    
    
    ///////////////
      final FileChooser fc = new FileChooser();
    private File file ;
    File xxx = null ;
    @FXML
    private Label VerifTitre;
    @FXML
    private Label VerifPlanning;
    @FXML
    private Label VerifPrix;
    @FXML
    private Label VerifNB;
    @FXML
    private Label VerifDiscipline;
    @FXML
    private Label VerifDescription;
    @FXML
    private Button btnRetour;
    ///////////////

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtTitre.setText(Statics.cc.getTitre());
       txtDiscipline.setText(Statics.cc.getDiscipline());
       txtPlaning.setText(Statics.cc.getPlaning());
       txtNbMax.setText(String.valueOf(Statics.cc.getNbmax()));
       txtDescription.setText(Statics.cc.getDescription());
       txtPrix.setText(Statics.cc.getPrix());

                 
         Image im;
        try {
            im = new Image(getClass().getResource("../uicontrolers/images/" + Statics.cc.getImage()).toURI().toString());
            img.setImage(im);
        } catch (URISyntaxException ex) {
            Logger.getLogger(PssAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        

    }    


    


    @FXML
    private void AjouterImage(ActionEvent event) throws IOException, IOException {


 fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {

            String x = file.getAbsolutePath();
            String newpath = "src/gui/uicontrolers/images/";
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
    
    
    private void clear() {

        VerifTitre.setText("");

        VerifDescription.setText("");

        VerifDiscipline.setText("");

//        VerifImage.setText("");

        VerifNB.setText("");

        VerifPrix.setText("");

        VerifPlanning.setText("");

    }
    
      String number = "[0-9]+";
     Pattern x =Pattern.compile(number);
    @FXML
    private void modifier(ActionEvent event) throws IOException {
        clear();
        if (txtTitre.getText().isEmpty()) {
            VerifTitre.setText("Entrez un Titre");
            return;
        }

        if (txtDescription.getText().isEmpty()) {
            VerifDescription.setText("Entrez une Description");
            return;
        }

        if (txtNbMax.getText().isEmpty()) {
            VerifNB.setText("Entrez un nombre");
            return;
        }
        if (txtDiscipline.getText().isEmpty()) {
            VerifDiscipline.setText("Entrez une Discipline");
            return;
        }

        if (txtPrix.getText().isEmpty()||!x.matcher(txtPrix.getText()).matches()) {
            VerifPrix.setText("Entrez le Prix");
            return;
        }

        if (txtPlaning.getText().isEmpty()) {
            VerifPlanning.setText("Entrez un planing");
            return;
        }
        String req = "UPDATE coachings SET titre=?,discipline=?,description= ?,planing=?,prix=?,nbmax=?,image=? WHERE id= ?";

     
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,txtTitre.getText());
            ps.setString(2,txtDiscipline.getText());
            ps.setString(3, txtDescription.getText());
            ps.setString(4, txtPlaning.getText());
            ps.setString(5, txtPrix.getText());
            ps.setString(6, txtNbMax.getText());

            /////////////
          ps.setString(7, nameImage);
           
          /////////////////
            ps.setInt(8, Statics.cc.getId());

            ps.executeUpdate();
         
              Notification.notificationSuccess("Coaching MODIFIER AVEC SUCCES", "Merci pour voter contribution ");

        } catch (SQLException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        /////////////////////
        

 Stage stage = (Stage)btnAjouter.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        
        /////////////////////////
        
    }


    @FXML
    private void retour(ActionEvent event) throws IOException {
                
 Stage stage = (Stage)btnAjouter.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("../ModifSuppCoaching/MesCoaching.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
