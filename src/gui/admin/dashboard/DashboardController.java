/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin.dashboard;

import Exception.AuthException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gui.dashboard.DashboardFXMLController;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Utilisateur;
import org.json.JSONException;
import services.UtilisateurServices;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DashboardController implements Initializable {

    @FXML
    private Circle myCircle;
    private int id, idRole;
    @FXML
    private Button btnLogout;
    ObservableList<Utilisateur> list = FXCollections.observableArrayList();
    @FXML
    private VBox scene;
    @FXML
    private Button gest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");

        JWebToken incomingToken;

        try {
            incomingToken = new JWebToken(bearerToken);

            if (!incomingToken.isValid()) {
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                idRole = Integer.parseInt(audience);
                id = Integer.parseInt(subject);
                getImageProfil("../../uicontrolers/users/default.png");
                gestionUser();
          
            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException | URISyntaxException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void logout(ActionEvent event) {
        Preferences userPreferences = Preferences.userRoot();
        try {
            userPreferences.clear();
            redirectToLogin();
        } catch (BackingStoreException ex) {
            Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void redirectToLogin() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../../login/LoginFXML.fxml"));
            Stage primaryStage = (Stage) btnLogout.getScene().getWindow();
            primaryStage.close();
            Scene scene = new Scene(root);
            Image icon;
            icon = new Image(getClass().getResourceAsStream("../../uicontrolers/logosportstrnsprt.png"));
            Stage stage = new Stage();
            stage.getIcons().add(icon);
            stage.setTitle("Se Connecter chez Supportini");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }



    @FXML
    public void gestionUser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../dashboardUser/GestionUser.fxml"));
        scene.getChildren().removeAll();
        scene.getChildren().setAll(root);
        scene.setAlignment(Pos.CENTER);
        VBox.setVgrow(root, Priority.ALWAYS);
    }

    public void getImageProfil(String path) throws URISyntaxException {

        Image im = new Image(getClass().getResource(path).toURI().toString());
        myCircle.setFill(new ImagePattern(im));
//        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITESMOKE));
        myCircle.setStroke(Color.WHITESMOKE);
        myCircle.setOnMouseClicked((MouseEvent event) -> {

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("../updateProfilAdmin/updateProfilAdmin.fxml"));
                scene.getChildren().removeAll();
                scene.getChildren().setAll(root);
                scene.setAlignment(Pos.CENTER);
                VBox.setVgrow(root, Priority.ALWAYS);
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void gestionSalleDeSport(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../dashboardSalleDeSport/GestionSalleDeSport.fxml"));
        scene.getChildren().removeAll();
        scene.getChildren().setAll(root);
        scene.setAlignment(Pos.CENTER);
        VBox.setVgrow(root, Priority.ALWAYS);
    }

    @FXML
    private void GestionCoachings(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("../dashboardCoachings/GestionCoachings.fxml"));
  scene.getChildren().removeAll();
        scene.getChildren().setAll(root);
        scene.setAlignment(Pos.CENTER);
        VBox.setVgrow(root, Priority.ALWAYS);
    }
    @FXML
    private void gestionproduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Produit/produit.fxml"));

        scene.getChildren().removeAll();
        scene.getChildren().setAll(root);
        scene.setAlignment(Pos.CENTER);
        VBox.setVgrow(root, Priority.ALWAYS);
    }
}
