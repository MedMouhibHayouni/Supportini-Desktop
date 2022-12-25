/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.profil;

import Exception.AuthException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gui.ItemSalleSport.ItemSalleSportFXMLController;
import interfaces.IEntrainee;
import interfaces.IGalerie;
import interfaces.IUtilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import model.GalerieImage;
import org.json.JSONException;
import services.EntraineeServices;
import services.GalerieServices;
import services.UtilisateurServices;
import util.JWebToken;
import util.Notification;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProfilFXMLController implements Initializable {

    @FXML
    private Circle myCircle;
    @FXML
    private Label nomPrenom;
    @FXML
    private Label typeUser;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    private Pane detailProfilPane;
    @FXML
    private Label poids;
    @FXML
    private Label age;
    @FXML
    private Label taille;

    @FXML
    private ImageView iconSexe;
    @FXML
    private ScrollPane scrollProfil;
    private int idUser, idRole;
    @FXML
    private Button addImages;
    @FXML
    private HBox sceneGalerie;
    @FXML
    private GridPane gridImg;
    @FXML
    private ScrollPane scrolImg;
    private List<GalerieImage> listImg = new ArrayList<>();
    @FXML
    private GridPane gridInfo;
    @FXML
    private HBox boxGalerie;
    @FXML
    private HBox boxAdd;

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
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            idRole = Integer.parseInt(audience);
            idUser = Integer.parseInt(subject);

            getGalerie();
            getInfoCurrentUser(idUser);
        } catch (URISyntaxException | JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getImageProfil(String path) throws URISyntaxException {
        Image im = new Image(getClass().getResource(path).toURI().toString());
        myCircle.setFill(new ImagePattern(im));
//        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITESMOKE));
        myCircle.setStroke(Color.WHITESMOKE);
    }

    private void getGalerie() throws URISyntaxException, FileNotFoundException {
        IGalerie ig = new GalerieServices();

        listImg.clear();
        listImg.addAll(ig.selectImageById(idUser));

        if (listImg.size() == 0) {
            sceneGalerie.setVisible(false);
            sceneGalerie.setVisible(false);
        } else {
              sceneGalerie.setVisible(true);
              sceneGalerie.setManaged(true);
            int column = 1;
            int row = 0;
            for (int i = 0; i < listImg.size(); i++) {
                if (column == 3) {
                    column = 1;
                    row++;
                }
                FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.REMOVE);
                deleteIcon.setStyle(
                        " -fx-cursor: hand ;"
                        + "-glyph-size:28px;"
                        + "-fx-fill:#ff1744;"
                );
                Button btnDelete = new Button("", deleteIcon);
                int id = listImg.get(i).getId();
                String name = listImg.get(i).getImageName();
                btnDelete.setOnAction((ActionEvent event) -> {
                    deleteImage(id, name);
                });

                HBox hBox = new HBox();
                File initialFile = new File("src/gui/uicontrolers/galerie/" + listImg.get(i).getImageName());

                InputStream is = new FileInputStream(initialFile.getAbsolutePath());;

                ImageView image = new ImageView(new Image(is, 320, 400, false, false));

                hBox.getChildren().add(image);

                hBox.getChildren().add(btnDelete);

                gridImg.add(hBox, column++, row);
//                System.out.println(column+" "+row);
                //set grid width
                gridImg.setMinWidth(100);
                gridImg.setPrefWidth(100);
                gridImg.setMaxWidth(100);
//              
//                //set grid height
//                Lsport.setMinHeight(300);
//                Lsport.setPrefHeight(400);
//                Lsport.setMaxHeight(400);

                GridPane.setMargin(hBox, new Insets(15));
                GridPane.setValignment(scrolImg, VPos.CENTER);
            }
//                getIn
        }
    }

    private void deleteImage(int id, String name) {
        IGalerie ig = new GalerieServices();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Vous étes sur pour la sppression de cette photo !!");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                Path path = FileSystems.getDefault().getPath("./src/gui/uicontrolers/galerie/" + name);

                try {
                    Files.delete(path);
                } catch (IOException ex) {
                    Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ig.deleteImg(id);
                try {
                    gridImg.getChildren().clear();
                    getGalerie();

                } catch (URISyntaxException ex) {
                    Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void attachImages() throws IOException, URISyntaxException {
        FileChooser chooser = new FileChooser();
        IGalerie ig = new GalerieServices();
        chooser.setTitle("Choisir vos Images");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().clear();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        List<File> file = (List<File>) chooser.showOpenMultipleDialog(null);
        for (int i = 0; i < file.size(); i++) {
            if (file.get(i).isFile()) {

                String absPath = file.get(i).getAbsolutePath();
                String newpath = "src/gui/uicontrolers/galerie/";
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
                File newFile = new File(newpath + nameFile + "." + extension);
                Files.copy(sourceFile.toPath(), newFile.toPath());
                //   System.out.println(destinationFile);
                String path = nameFile + "." + extension;
                ig.addImage(new GalerieImage(idUser, path));
                listImg.clear();
                listImg.addAll(ig.selectImageById(idUser));
                gridImg.getChildren().clear();
                getGalerie();

            } else {
                Notification.notificationError("ERREUR", "Il faut choisir une image");
            }
        }

    }

    private void getInfoCurrentUser(int id) throws URISyntaxException {
        IEntrainee ie = new EntraineeServices();
        String nom = ie.selectProfil(id).nom.substring(0, 1).toUpperCase() + ie.selectProfil(id).nom.substring(1);
        String prenom = ie.selectProfil(id).prenom.substring(0, 1).toUpperCase() + ie.selectProfil(id).prenom.substring(1);

        nomPrenom.setText(nom + " " + prenom);
        email.setText(ie.selectProfil(id).email);
        phone.setText(ie.selectProfil(id).phone);

        if (ie.selectProfil(id).image == null) {
            String pathLocale = "../uicontrolers/user.png";
            getImageProfil(pathLocale);
        } else {
            String pathLocale = "../uicontrolers/users/" + ie.selectProfil(id).image;
            getImageProfil(pathLocale);
        }

        age.setText(String.valueOf(ie.selectProfil(id).getAge()) + " ans");
        poids.setText(String.valueOf(ie.selectProfil(id).getPoids() + " kg"));
        taille.setText(String.valueOf(ie.selectProfil(id).getTaille()) + " cm");
        typeUser.setText("Entrainé");
        if ("homme".equals(ie.selectProfil(id).getSexe())) {
            Image imageSexe = new Image(getClass().getResource("../uicontrolers/male.jpg").toURI().toString());
            iconSexe.setImage(imageSexe);
            iconSexe.setFitWidth(50);
            iconSexe.setFitHeight(50);
        } else {
            Image imageSexe = new Image(getClass().getResource("../uicontrolers/femine.jpg").toURI().toString());
            iconSexe.setImage(imageSexe);
            iconSexe.setFitWidth(70);
            iconSexe.setFitHeight(70);

        }

    }

}
