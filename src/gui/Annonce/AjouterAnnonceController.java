/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Annonce;

import Exception.AuthException;

import gui.AffichCoaching.ListCoachings;
import interfaces.IAnnonce;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Annonce;
import org.json.JSONException;
import services.AnnonceService;
import util.JWebToken;
import util.MaConnexion;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class AjouterAnnonceController implements Initializable {

    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtDiscipline;
    @FXML
    private TextField txtType;
    @FXML
    private Label validationtRue;
    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtCapacite;
    @FXML
    private TextArea txtDescription;
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
    private Label validationtRue1;
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtRue;
    @FXML
    private TextField txtCode;
    @FXML
    private Label labelcode;
    @FXML
    private Label validationtRue111;
    @FXML
    private Label validationtRue1111;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private Button btnAjouter;
    @FXML
    private ImageView img;
    @FXML
    private TextField TxtImage;
    @FXML
    private Button btnImage;
    @FXML
    private Label VerifImage;
    @FXML
    private Button retour;
    @FXML
    private Button owner;
////////////////////////////////////
    PreparedStatement pst, ps;
    int myIndex;
    int id;
    final FileChooser fc = new FileChooser();
    private File file;
    File xxx = null;
    private int idRole, idUser;
  Connection cnx = MaConnexion.getInstance().getCnx();
    private String nameImage;

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
                idRole = Integer.parseInt(audience);
                idUser = Integer.parseInt(subject);
                System.out.println(idUser);

            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(ListCoachings.class.getName()).log(Level.SEVERE, null, ex); ////////ItemDashFXMLController////
        }
    }

    
    
    
    
    
    
       public static Date convertToDateUsingDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
       
    }
    
    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
             String  tt;
                tt = "SELECT * FROM `coachs` where fk_idUser ='" + idUser + "' ";
     
                
                   Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
      
      while(queryoutput.next()){
           System.out.println(" test affich resultSet"+queryoutput.getInt(1));
          
          
       Date dtDebut=convertToDateUsingDate(DateDebut.getValue());
  Date dtFin=convertToDateUsingDate(DateFin.getValue());
  
  
  
       IAnnonce IAnn = new AnnonceService();
        Annonce a = new Annonce();
        a.setFk_idCoach(queryoutput.getInt(1));
        a.setTitre(txtTitre.getText());
        a.setDiscipline(txtDiscipline.getText());
        a.setDescription(txtDescription.getText());
        a.setPrix(Integer.parseInt(txtPrix.getText()));
        a.setImage(nameImage);
        a.setCapacite(Integer.parseInt(txtCapacite.getText()));
        a.setType(txtType.getText());
        a.setVille(txtVille.getText());
        a.setRue(txtRue.getText());
        a.setCodePostal(txtCode.getText());
       a.setDateDebut(dtDebut);
         a.setDateFin(dtFin);

        IAnn.ajouterAnnonce(a);
        Notification.notificationSuccess("Annonce AJOUTER AVEC SUCCES", "Merci pour voter contribution ");
      }
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
    private void retour(ActionEvent event) {
    }

    @FXML
    private void redirectToOwner(ActionEvent event) {
    }

}
