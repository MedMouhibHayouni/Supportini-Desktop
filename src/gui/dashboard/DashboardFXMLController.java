/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard;

import Exception.AuthException;
import interfaces.IUtilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.json.JSONException;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Utilisateur;
import services.UtilisateurServices;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private VBox sideAnchorPane;
    @FXML
    private Label nomPrenom;
    @FXML
    private Circle myCircle;
    @FXML
    private ComboBox<String> clientComboBox;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnCommerce;

//    private Button btnLogout = new Button("Déconnexion");
    @FXML
    private HBox navbar;
    @FXML
    private VBox iconBar;
    @FXML
    private ImageView iconDash;
    @FXML
    private ImageView iconShop;
    @FXML
    private ImageView iconSport;
    private ImageView iconCoach;
    @FXML
    private ImageView iconMenu;
    @FXML
    private HBox scenePane;
    private int idUser, idRole;
    @FXML
    private Button btnSalleDeSport;
    private Button btnCoachs;
    @FXML
    private Button btnMesSalleDeSport;
    @FXML
    private ImageView iconMesSalle;

    @FXML
    private Button iconSuivi;
    @FXML
    private HBox containIcon1;
    @FXML

    private ImageView iconMesSalle1;
    @FXML
    private Button btnListRegime;
    @FXML
    private Button btnMesRegimes;
    @FXML
    private ImageView espaceCoach;
    @FXML
    private Button btnowner;

    @FXML
    private Button Ajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //recieve the bearer token
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");

        JWebToken incomingToken;

        try {
            incomingToken = new JWebToken(bearerToken);

            if (!incomingToken.isValid()) {
                   
//                get id and idRole for current user
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                idRole = Integer.parseInt(audience);
                idUser = Integer.parseInt(subject);
                 System.out.println(idRole+" "+idUser);
//                control user  side bar
                itemComboBox(idRole);

                espaceCoach.setVisible(idRole == 3);
                espaceCoach.setManaged(idRole == 3);
                iconSuivi.setVisible(idRole != 4);
                iconSuivi.setManaged(idRole != 4);

                btnMesSalleDeSport.setVisible(idRole == 4);
                btnMesSalleDeSport.setManaged(idRole == 4);
                iconMesSalle.setVisible(idRole == 4);
                iconMesSalle.setManaged(idRole == 4);

                sideAnchorPane.setVisible(false);
                sideAnchorPane.setManaged(false);
                nomPrenom.setAlignment(Pos.CENTER);
                displayMenu();

                getCurrentUser();
                btnDashSideBar(btnDashboard);
                btnDashSideBar(btnCommerce);
                btnDashSideBar(btnSalleDeSport);
                btnDashSideBar(btnCoachs);

            } else {
                redirectToLogin();

            }

        } catch (JSONException | InvalidKeyException | IOException ex) {
            redirectToLogin();
             Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException | AuthException ex) {
            redirectToLogin();
            Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//      private  void makeStyleNavSideBar(Button btn){
//            btn.onMouseMovedProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
//                btn.setStyle("-fx-background-color: #000000");
//                btn.setStyle("-fx-text-fill: white");
//                btn.setCursor(Cursor.HAND);
//            });
//        btn.onMouseExitedProperty().set((event) -> {
//            btn.setStyle("-fx-background-color: #ffe6cc");
//            btn.setStyle("-fx-text-fill: white");
//            btn.setCursor(Cursor.DEFAULT);
//        });
//      }
    private void btnDashSideBar(Button btn) {
//
//        btn.setOnMouseClicked((event) -> {
//            FadeTransition fadeButtons = new FadeTransition(Duration.millis(500), btn);
//            fadeButtons.setFromValue(0.0);
//            fadeButtons.setToValue(1.0);
//            fadeButtons.play();
//        });
    }

    private void displayMenu() {

        iconMenu.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), sideAnchorPane);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            sideAnchorPane.setVisible(true);
            sideAnchorPane.setManaged(true);
        });
        sideAnchorPane.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {

            sideAnchorPane.setVisible(true);
            sideAnchorPane.setManaged(true);
        });
        iconBar.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), sideAnchorPane);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            sideAnchorPane.setVisible(true);
            sideAnchorPane.setManaged(true);
        });

        navbar.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), sideAnchorPane);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            iconBar.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.26), 5, 0.05, 0, 1));
            sideAnchorPane.setVisible(false);
            sideAnchorPane.setManaged(false);
        });
        scenePane.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), sideAnchorPane);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            iconBar.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.26), 5, 0.05, 0, 1));
            sideAnchorPane.setVisible(false);
            sideAnchorPane.setManaged(false);
        });

    }

    private void itemComboBox(int idRole) {

        ObservableList<String> items = FXCollections.observableArrayList("Profile", "Configuration", "Déconnexion");
        clientComboBox.setItems(items);
        clientComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (newValue != null) {
                if (newValue.equals(clientComboBox.getItems().get(0))) {
                    ActionEvent event = null;
                    try {
                        profil(event, idRole);

//                   oldValue=observable.getValue();
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (newValue.equals(clientComboBox.getItems().get(1))) {
                    try {
                        profilUpdate();
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (newValue.equals(clientComboBox.getItems().get(2))) {
                    ActionEvent event = null;

                    logout(event);

                }

            }

        });

    }

    public void profilUpdate() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../updateProfil/UpdateProfil.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);
        scenePane.setAlignment(Pos.CENTER);

        scenePane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        scenePane.setHgrow(root, Priority.ALWAYS);

    }

    public void profil(ActionEvent event, int idRole) throws IOException {
        switch (idRole) {

            case 2: {
                Parent root = FXMLLoader.load(getClass().getResource("../profil/ProfilFXML.fxml"));
                scenePane.getChildren().removeAll();
                scenePane.getChildren().setAll(root);
                scenePane.setAlignment(Pos.CENTER);
                scenePane.setHgrow(root, Priority.ALWAYS);
                scenePane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                break;

            }
            default: {
                Parent root = FXMLLoader.load(getClass().getResource("../profilCoach/ProfilCoachFXML.fxml"));
                scenePane.getChildren().removeAll();
                scenePane.getChildren().setAll(root);

                scenePane.setHgrow(root, Priority.ALWAYS);
                scenePane.setAlignment(Pos.CENTER);
                scenePane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                break;
            }

        }

    }

    @FXML
    public void listProduit(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../produits/ProduitsFXML.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);

        scenePane.setHgrow(root, Priority.ALWAYS);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    }

    @FXML
    private void MesSalleDeSport(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../PssAfficheList/ListSallePss.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);

        scenePane.setHgrow(root, Priority.ALWAYS);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        Scene newScene;
//        newScene = new Scene(root);
//        Stage mainWindow;
//        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        
//        mainWindow.setScene(newScene);
    }

    @FXML
    public void salleDeSport(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();

        Parent root = FXMLLoader.load(getClass().getResource("../salleDeSport/ListSalleSport.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);

        scenePane.setHgrow(root, Priority.ALWAYS);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

//        Scene newScene;
//        newScene = new Scene(root);
//        Stage mainWindow;
//        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        
//        mainWindow.setScene(newScene);
    }

    public void mesSalle() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../salleDeSport/ListSalleSport.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);

        scenePane.setHgrow(root, Priority.ALWAYS);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
//    public void dashPss(ActionEvent event) throws IOException {
//    switch (idRole) {
//        case 4:{
//               clientComboBox.getSelectionModel().clearSelection();
//        Parent root = FXMLLoader.load(getClass().getResource("../dashPss/dashPss.fxml"));
//        scenePane.getChildren().removeAll();
//        scenePane.getChildren().setAll(root);
//        }
////        default:{
////               clientComboBox.getSelectionModel().clearSelection();
////        Parent root = FXMLLoader.load(getClass().getResource("../xxx/xxx.fxml"));
////        scenePane.getChildren().removeAll();
////        scenePane.getChildren().setAll(root);
////        }
    @FXML
    public void itemDash(ActionEvent event) throws IOException {
        switch (idRole) {
            case 2: {
                clientComboBox.getSelectionModel().clearSelection();
                Parent root = FXMLLoader.load(getClass().getResource("../../gui/AffichCoaching/ListCoachings.fxml"));
                scenePane.getChildren().removeAll();
                scenePane.getChildren().setAll(root);
            }

//        clientComboBox.getSelectionModel().clearSelection();
//        Parent root = FXMLLoader.load(getClass().getResource("../itemDash/ItemDashFXML.fxml"));
//        scenePane.getChildren().removeAll();
//        scenePane.getChildren().setAll(root);
//         scenePane.setAlignment(Pos.CENTER);
//        scenePane.setHgrow( root, Priority.ALWAYS);
        }
    }

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
            root = FXMLLoader.load(getClass().getResource("../login/LoginFXML.fxml"));
            Stage primaryStage = (Stage) clientComboBox.getScene().getWindow();
            primaryStage.close();
            Scene scene = new Scene(root);
            Image icon;
            icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
          
            Stage stage = new Stage();
            stage.getIcons().add(icon);
            stage.setTitle("Se Connecter chez Supportini");
            stage.setScene(scene);

            stage.sizeToScene();
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    public void getImageProfil(String path) throws URISyntaxException, FileNotFoundException {

        File initialFile = new File(path);


        InputStream is = new FileInputStream(initialFile.getAbsolutePath());;
        Image im = new Image(is);
        myCircle.setFill(new ImagePattern(im));
        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITESMOKE));
        myCircle.setStroke(Color.WHITESMOKE);
    }

    public void getCurrentUser() throws URISyntaxException, FileNotFoundException {
        IUtilisateur iu = new UtilisateurServices();
            
        String nom = iu.queryUserById(idUser).getNom().substring(0, 1).toUpperCase() + iu.queryUserById(idUser).getNom().substring(1);
        String prenom = iu.queryUserById(idUser).getPrenom().substring(0, 1).toUpperCase() + iu.queryUserById(idUser).getPrenom().substring(1);
        
     
            String path = "src/gui/uicontrolers/users/" + iu.queryUserById(idUser).getImageName();
           getImageProfil(path);
        
        nomPrenom.setText(nom + " " + prenom);

    }

//    private void ajouterCoching(ActionEvent event) throws IOException {
//          clientComboBox.getSelectionModel().clearSelection();
//        Parent root = FXMLLoader.load(getClass().getResource("../ajoutCoaching/AjouterCoach.fxml"));
//        scenePane.getChildren().removeAll();
//        scenePane.getChildren().setAll(root);
//         scenePane.setAlignment(Pos.CENTER);
//        scenePane.setHgrow( root, Priority.ALWAYS);
//    }
    @FXML
    private void affsuivi(ActionEvent event) throws IOException {
        System.out.println("IdRole = "+idRole);
        clientComboBox.getSelectionModel().clearSelection();
        if (idRole == 3) {

            Parent root = FXMLLoader.load(getClass().getResource("../suivi/suivicoach/SuiviCoach2.fxml"));
            scenePane.getChildren().removeAll();
            scenePane.getChildren().setAll(root);
        } else {

            Parent root = FXMLLoader.load(getClass().getResource("../suivi/suivitrainer/SuiviTrainer.fxml"));
            scenePane.getChildren().removeAll();
            scenePane.getChildren().setAll(root);
        }
    }

    //////////// en affichage mes coachings//////////////
    private void addCoaching(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../ajoutCoaching/AjouterCoach.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setHgrow(root, Priority.ALWAYS);
    }

    @FXML
    private void ListCachings(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../AffichAnnonce/ListAnnonce.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setHgrow(root, Priority.ALWAYS);
    }

    private void MesCoachings(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../ModifSuppCoaching/MesCoaching.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setHgrow(root, Priority.ALWAYS);
    }

    @FXML
    private void LIstRegime(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();

        Parent root = FXMLLoader.load(getClass().getResource("../TousLesRegimes/ListRegime.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setHgrow(root, Priority.ALWAYS);
    }

    private void MesRegimes(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();

        Parent root = FXMLLoader.load(getClass().getResource("../AffichageRegime/ListRegime.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setHgrow(root, Priority.ALWAYS);

    }

    @FXML
    private void ownerEspace(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();

        Parent root = FXMLLoader.load(getClass().getResource("../EspaceCoach/EspaceCoach.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);
        scenePane.setAlignment(Pos.CENTER);
        scenePane.setHgrow(root, Priority.ALWAYS);
    }

    @FXML
    private void Consultercommande(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../Commande/consulterCommande_Produit.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);

    }

}