/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ajoutCoaching;

import Exception.AuthException;

import gui.AffichCoaching.ListCoachings;
//import gui.itemDash.ItemDashFXMLController;

import interfaces.ICoachings;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

import model.Coachings;
import model.Salle;

import org.json.JSONException;

import services.CoachingsService;
import util.JWebToken;

import util.Notification;
import util.Validation;


/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class AjouterCoachController implements Initializable {

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
    private TextField TxtImage;
    @FXML
    private Button btnImage;
    
    
    
    


    private String nameImage;

    private int idRole, idUser;

    ICoachings ic = new CoachingsService();
    ObservableList<Coachings> listCoachings = FXCollections.observableArrayList();
    PreparedStatement pst, ps;
    int myIndex;
    int id;
    final FileChooser fc = new FileChooser();
    private File file;
    File xxx = null;
    @FXML
    private Label VerifTitre;
    @FXML
    private Label VerifDiscipline;
    @FXML
    private Label VerifPlanning;
    @FXML
    private Label VerifPrix;
    @FXML
    private Label VerifNB;
    @FXML
    private Label VerifDescription;
    @FXML
    private Label VerifImage;
    private Button retour;
    private Button owner;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
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
                  
            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(ListCoachings.class.getName()).log(Level.SEVERE, null, ex); ////////ItemDashFXMLController////
        }
    }    



    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
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

        if (txtPrix.getText().isEmpty()) {
            VerifPrix.setText("Entrez le Prix");
            return;
        }

        if (txtPlaning.getText().isEmpty()) {
            VerifPlanning.setText("Entrez un planing");
            return;
        }

        ICoachings ip = new CoachingsService();
        Coachings p = new Coachings();

//       Coachings p = new Coachings(Integer.parseInt(txtIdCoach.getText()),txtTitre.getText(),  txtDiscipline.getText(), txtDescription.getText(),txtPlaning.getText(), txtPrix.getText());
        p.setIdcoach(idUser);


        p.setTitre(txtTitre.getText());
        p.setDiscipline(txtDiscipline.getText());
        p.setDescription(txtDescription.getText());
        p.setPlaning(txtPlaning.getText());
        p.setPrix(txtPrix.getText());
        p.setNbmax(Integer.parseInt(txtNbMax.getText()));
        p.setNbinscri(0);
        p.setImage(nameImage);
      
        ip.ajouterCoaching(p);
        Notification.notificationSuccess("ANNONCE DE COACHING AJOUTER AVEC SUCCES", "Merci pour voter contribution ");
         
    
        
        /////////////////////
              Stage stage = (Stage)btnAjouter.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
///////////////////////////////
    }

    private void clear() {

        VerifTitre.setText("");

        VerifDescription.setText("");

        VerifDiscipline.setText("");

        VerifImage.setText("");

        VerifNB.setText("");

        VerifPrix.setText("");

        VerifPlanning.setText("");


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
    
    
    

    @FXML
    private void retour(ActionEvent event) throws IOException {

  
 Stage stage = (Stage)btnAjouter.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void redirectToOwner(ActionEvent event) throws IOException {
         Stage stage = (Stage) owner.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../ModifSuppCoaching/MesCoaching.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
