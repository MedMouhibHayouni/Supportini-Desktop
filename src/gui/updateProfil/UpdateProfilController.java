/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.updateProfil;

import Exception.AuthException;
import gui.dashboard.DashboardFXMLController;
import interfaces.IUtilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import model.Utilisateur;
import org.json.JSONException;
import services.UtilisateurServices;
import util.JWebToken;
import util.Notification;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UpdateProfilController implements Initializable {

    private Circle myCircle;
    @FXML
    private Button updateImage;
    @FXML
    private TextField updateNom;
    @FXML
    private TextField updatePrenom;
    @FXML
    private TextField updateEmail;
    @FXML
    private TextField updateOldPwd;
    @FXML
    private TextField updateNewPwd;
    @FXML
    private TextField updatePhone;
    @FXML
    private Button updateData;
    @FXML
    private Label nomValid;
    @FXML
    private Label prenomValid;
    @FXML
    private Label emailValid;
    @FXML
    private Label oldPwdValid;
    @FXML
    private Label newPwdValid;
    @FXML
    private Label phoneValid;
    @FXML
    private Button attachBtn;
    @FXML
    private ImageView imgView;
    private String path;
    private int idUser;
            private int idRole;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                // get function for initialisation controler
                getCurrentUser(idUser);

            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException | URISyntaxException ex) {
            Logger.getLogger(UpdateProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getImageProfil(String path) throws URISyntaxException {
        Image im = new Image(getClass().getResource(path).toURI().toString());

//        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITESMOKE));
        imgView.setImage(im);
    }

    private void getCurrentUser(int id) throws URISyntaxException {
        IUtilisateur iu = new UtilisateurServices();
        Utilisateur currentUser = iu.queryUserById(id);
         if (iu.queryUserById(id).getImageName()== null){
        String pathLocale = "../uicontrolers/user.png";
        getImageProfil(pathLocale);
         }else {
             String pathLocale = "../uicontrolers/users/"+iu.queryUserById(id).getImageName();
             getImageProfil(pathLocale);
         }
        updateNom.setText(currentUser.getNom());
        updatePrenom.setText(currentUser.getPrenom());
        updateEmail.setText(currentUser.getEmail());
        updatePhone.setText(currentUser.getPhone());
    }

    @FXML
    private void updateData() throws Exception {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        JWebToken incomingToken = new JWebToken(bearerToken);
        if (!incomingToken.isValid()) {

            String subject = incomingToken.getSubject();

            int idUser = Integer.parseInt(subject);
            IUtilisateur iu = new UtilisateurServices();
            Utilisateur currentUser = iu.queryUserById(idUser);
            final String regex = "^(.+)@(\\S+)$";
            clear();
            if (updateNom.getText().isEmpty()) {
                nomValid.setText("Please entrer ton nom");
                return;
            }
            if (updatePrenom.getText().isEmpty()) {
                prenomValid.setText("Please entrer ton prénom");
                return;
            }
            if (updateEmail.getText().isEmpty()) {
                emailValid.setText("Please entrer ton email");
                return;
            }

            if (!Validation.patternMatches(updateEmail.getText(), regex)) {
                emailValid.setText("Please entrer un email existe");
                return;
            }

            if (updateOldPwd.getText().isEmpty()) {
                oldPwdValid.setText("Please entrer ton ancien password");
                return;
            }
            if (!Validation.hachePassword(updateOldPwd.getText()).equals(currentUser.getPassword())) {
                oldPwdValid.setText("Ancien password est faux");
                return;
            }
            if (updateOldPwd.getText().isEmpty()) {
                oldPwdValid.setText("Please entrer ton ancien password");
                return;
            }
            if (!(Validation.validationPasswordLength(updateNewPwd, newPwdValid) && Validation.validationPasswordCompose(updateNewPwd, newPwdValid))) {

                return;
            }

            if (!Validation.validationInteger(updatePhone, phoneValid)) {
                return;
            }
            if (updatePhone.getText().length() != 8) {
                phoneValid.setText("Num de portable doit 8 chiffres!!");
                return;
            }
            Utilisateur updateUser = new Utilisateur();
            updateUser.setId(currentUser.getId());
            updateUser.setNom(updateNom.getText());
            updateUser.setPrenom(updatePrenom.getText());
            updateUser.setEmail(updateEmail.getText());
            updateUser.setPassword(updateNewPwd.getText());
            updateUser.setPhone(updatePhone.getText());
            iu.updateUser(updateUser);
            getCurrentUser(idUser);
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("../dashboard/DashboardFXML.fxml"));
            DashboardFXMLController dashboardController = new DashboardFXMLController();
            dashboardController.getCurrentUser();
        }
    }

    private void clear() {

        nomValid.setText("");

        prenomValid.setText("");

        emailValid.setText("");

        oldPwdValid.setText("");

        newPwdValid.setText("");

        phoneValid.setText("");
    }

    @FXML
    private void attachImage(ActionEvent event) throws URISyntaxException, FileNotFoundException, IOException {
    
       FileChooser chooser = new  FileChooser();
         chooser.setTitle("Upload Image");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().clear();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(null);
        if (file.isFile()) {
           
            String absPath = file.getAbsolutePath();
            String newpath = "src/gui/uicontrolers/users/";
            File dir = new File(newpath);
            if (!dir.exists()) {
                // folder wa7dd ken barchaa mkdirs
                dir.mkdir();
            }
            File sourceFile = null;
            File destinationFile = null;
            String extension = absPath.substring(absPath.lastIndexOf('.') + 1);
            sourceFile = new File(absPath);
            String nameFile = Validation.randomString();
            File newFile = new File(newpath + nameFile+ "." + extension);
            Files.copy(sourceFile.toPath(), newFile.toPath());
            //   System.out.println(destinationFile);
            
            path= nameFile+ "." + extension;
            imgView.setImage(new Image(file.toURI().toString()));
        } else {
           Notification.notificationError("ERREUR", "Il faut choisir une image");
        }
           Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);
        
    }
   
    @FXML
    private void saveImageProfil (ActionEvent event){
       
      IUtilisateur iu =new UtilisateurServices();
      iu.uploadUserImg(path, idUser);
    }
}
