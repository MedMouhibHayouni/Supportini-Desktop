/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.addSalleDeSport;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import Exception.AuthException;
import gui.dashboard.DashboardFXMLController;
import interfaces.ISalleSport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.SalleSport;
import org.json.JSONException;
import services.SalleSportServices;
import util.JWebToken;
import util.Notification;
import util.Validation;


/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class AddSalleDeSportController implements Initializable {


    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtNumtel;
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtRue;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button BtnUpload;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label validationNom;
    @FXML
    private Label validationtNumtel;
    @FXML
    private Label validationtVille;
    @FXML
    private Label validationtRue;
    @FXML
    private Label validationtCodePostal;
    @FXML
    private Label validationtPrix;
    @FXML
    private Label validationtDuration;
    @FXML
    private Label validationtDescription;
    @FXML
    private ImageView imgVitrine;
    final FileChooser fc = new FileChooser();
    private File file;
    private Path filepath;
    File xxx = null;
    String filename = null;
    private String path;

    private int idRole, idUser;
    @FXML
    private TextField taimage;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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
                idRole = Integer.parseInt(audience);
                idUser = Integer.parseInt(subject);
                System.out.println(idUser);

            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(AddSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        ISalleSport ip = new SalleSportServices();
        clear();
        if (txtNom.getText().isEmpty()) {
            Notification.notificationError("ERREUR", "entrer le nom de salle de sport");
            return;
        }
        if (txtNumtel.getText().isEmpty()) {
            Notification.notificationError("ERREUR", "entrer le numéro de téléphone");
            return;
        }
        if (txtNumtel.getText().length() != 8) {
            Notification.notificationError("ERREUR", "numéro de téléphone doit contenir 8 chiffres");
            return;
        }
        if (txtVille.getText().isEmpty()) {
            Notification.notificationError("ERREUR", "entrer le nom de la ville");
            return;
        }
        if (txtRue.getText().isEmpty()) {

            Notification.notificationError("ERREUR", "entrer le rue de la ville");
            return;
        }
        if (txtCodePostal.getText().isEmpty()) {

            Notification.notificationError("ERREUR", "entrer le code postal");
            return;
        }
        if (txtCodePostal.getText().length() != 4) {

            Notification.notificationError("ERREUR", "code postale doit contenir 4 chiffre");
            return;
        }
        if (txtPrix.getText().isEmpty()) {

            Notification.notificationError("ERREUR", "entrer le prix");
            return;
        }
        if (Integer.valueOf(txtPrix.getText()) <= 0) {
            Notification.notificationError("ERREUR", "Prix doit étre positif");
            return;
        }

        if (txtDuration.getText().isEmpty()) {
            Notification.notificationError("ERREUR", "entrer la duration");
            return;
        }
        if (txtDescription.getText().isEmpty()) {

            Notification.notificationError("ERREUR", "entrer la description");
            return;
        }
        if (txtDescription.getText().isEmpty()) {

            Notification.notificationError("ERREUR", "entrer la description");
            return;
        }

//        SalleSport p = new SalleSport(Integer.parseInt(txtNumtel.getText()), Integer.parseInt(txtCodePostal.getText()), (txtNom.getText()), txtVille.getText(), txtRue.getText(), txtDescription.getText(), txtDuration.getText(),Float.valueOf(txtPrix.getText()) );
        SalleSport p = new SalleSport();
        p.setNomSalle(txtNom.getText());
        p.setNumTel(Integer.parseInt(txtNumtel.getText()));
        p.setVille(txtVille.getText());
        p.setRue(txtRue.getText());
        p.setCodePostal(txtCodePostal.getText());
        p.setDescription(txtDescription.getText());
        p.setDuration(txtDuration.getText());
        p.setPrix(Float.valueOf(txtPrix.getText()));
        p.setImageVitrine(String.valueOf(filepath.getFileName()));
        p.setFk_idUser(idUser);
        System.out.println();
        ip.ajouterSalleSport(p);
        Notification.notificationSuccess("SALLE DE SPORT AJOUTER AVEC SUCCESS", "Merci");
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);

    }

    private void clear() {

        validationNom.setText("");

        validationtNumtel.setText("");

        validationtVille.setText("");

        validationtRue.setText("");
        //////
        validationtCodePostal.setText("");

        validationtDuration.setText("");

        validationtDescription.setText("");

    }

    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
        fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {

            String x = file.getAbsolutePath();
            String newpath = "src/gui/uicontrolers/imageSalleSport/";
            File dir = new File(newpath);
            if (!dir.exists()) {
                // folder wa7dd ken barchaa mkdirs
                dir.mkdirs();
            }
            File sourceFile = null;
            File destinationFile = null;
            String extension = x.substring(x.lastIndexOf('.') + 1);
            sourceFile = new File(x);
            xxx = new File(newpath + randomStringforimage() + "." + extension);
            filepath = Files.copy(sourceFile.toPath(), xxx.toPath());
            System.out.println(filepath);
            //System.out.println(destinationFile);
            System.out.println(xxx);
            //taimage.appendText(file.getAbsolutePath() + "\n");
            imgVitrine.setImage(new Image(file.toURI().toString()));
            //System.out.println(imgVitrine);
            System.out.println("********************" + file.toURI().toString());
            System.out.println("filename = " + filepath.getFileName());
        } else {

            System.out.println("file is invalide");
        }
    }

    public static String randomStringforimage() {
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
    private void backToDashboard(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();

        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);

        primaryStage.sizeToScene();
        primaryStage.show();
    }

}
