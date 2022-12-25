/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.addMaterielSalleDeSport;

import interfaces.Imateriel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.MaterielSalle;
import services.MaterielServices;
import util.Notification;
import util.Statics;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author HSOUNA
 */
public class AjouterDeMaterielFXMLController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtQuantite;
    @FXML
    private TextField txtSpecialite;
    @FXML
    private TextField txtDescription;
    @FXML
    private ImageView imgVitrine;
    @FXML
    private Button BtnUpload;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField taimage;
      final FileChooser fc = new FileChooser();
    private File file;
    private Path filepath;
    File xxx = null;
    String filename = null;
     private String path;
    @FXML
    private Label NomMaterielValid;
    @FXML
    private Label QuantiteValid;
    @FXML
    private Label SpecialiteValid;
    @FXML
    private Label DescriptionValid;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private String uploadimage(ActionEvent event) throws IOException {
            fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {

            String x = file.getAbsolutePath();
            String newpath = "src/gui/uicontrolers/materiel/";
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
            System.out.println("********************"+file.toURI().toString());
            System.out.println("filename = " +filepath.getFileName());
        } else {
            System.out.println("file is invalide");
        }
        return null;
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
//    

    

    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
           Imateriel ip = new MaterielServices();
        MaterielSalle m = new MaterielSalle();
    clear();
            if (txtNom.getText().isEmpty()) {
//                NomMaterielValid.setText(" entrer le nom de materiel ");
                            Notification.notificationError("ERREUR", "entrer le nom de materiel");

                return;
            }
            if (txtQuantite.getText().isEmpty()) {
//                QuantiteValid.setText(" entrer la quantité");
                            Notification.notificationError("ERREUR", "entrer la quantité");

                return;
            }
            if (txtSpecialite.getText().isEmpty()) {
//                SpecialiteValid.setText("entrer une specialite");
                            Notification.notificationError("ERREUR", "entrer une specialite");

                return;
            }

            if (txtDescription.getText().isEmpty()) {
//                DescriptionValid.setText(" entrer une description");
                            Notification.notificationError("ERREUR", "entrer une description");

                return; 
            }
//            if (imgVitrine.getText().isEmpty()) {
//                DescriptionValid.setText(" entrer une description");
//                return;
//            }
        m.setFk_idSalle(Statics.xx.getId());
        m.setNomMaterial(txtNom.getText());
        m.setQuantite(Integer.parseInt(txtQuantite.getText()));
        m.setSpecialite(txtSpecialite.getText());
        m.setDescription(txtDescription.getText());
        m.setImageVitrine(String.valueOf(filepath.getFileName()));
//        m.setFk_idSalle(Integer.parseInt(txt.getText()));
        ip.ajouterMaterielSalle(m);
       
//          Stage stage = (Stage) btnAjouter.getScene().getWindow();
//         stage.close();
                Stage stage = (Stage) btnAjouter.getScene().getWindow();

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
         Notification.notificationSuccess("AJOUTER MATERIEL AVEC SUCCESS", "Merci");
    }
      private void clear() {

        NomMaterielValid.setText("");

        QuantiteValid.setText("");

        SpecialiteValid.setText("");

        DescriptionValid.setText("");

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
    

