/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.PssAfficheList;

import Exception.AuthException;
import gui.ItemSalleSport.ItemSalleSportFXMLController;
import gui.PssAffiche.PssAfficheController;
import gui.dashboard.DashboardFXMLController;
import gui.salleDeSport.ListSalleSportController;
import interfaces.ISalleSport;
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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.SalleSport;
import org.json.JSONException;
import services.SalleSportServices;
import services.UtilisateurServices;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class ListSallePssController implements Initializable {

    @FXML
    private GridPane Lsport;
    private Label nomPrenom;
    private Circle myCircle;
    private ComboBox<String> clientComboBox;
    @FXML
    private ScrollPane scrollListSalle;
    private int idRole;
    private int idUser;
    @FXML
    private Button ajoutSalleDeSport;
    @FXML
    private HBox hboxScene;
   
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ISalleSport ss = new SalleSportServices();
        List<SalleSport> listSalleSport = new ArrayList<>();

        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;

        int column = 0;
        int row = 1;
        try {
            incomingToken = new JWebToken(bearerToken);
            System.out.println(bearerToken);
            if (!incomingToken.isValid()) {

//                get id and idRole for current user
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                idRole = Integer.parseInt(audience);
                idUser = Integer.parseInt(subject);
                listSalleSport.addAll(ss.affichageByIdPss(idUser));
                

                for (int i = 0; i < listSalleSport.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../PssAffiche/PssAffiche.fxml"));

                    HBox hbox = loader.load();

                    PssAfficheController c = loader.getController();
                    c.setData(listSalleSport.get(i));
                    Lsport.add(hbox, column, row++);
//                System.out.println(column+" "+row);
                    //set grid width
                    Lsport.setMinWidth(500);
                    Lsport.setPrefWidth(500);
                    Lsport.setMaxWidth(500);
//
//                //set grid height
//                Lsport.setMinHeight(300);
//                Lsport.setPrefHeight(400);
//                Lsport.setMaxHeight(400);

                    GridPane.setMargin(hbox, new Insets(25));
                    GridPane.setValignment(scrollListSalle, VPos.CENTER);
                }
//                getInfoCurrentUser(idUser);
//                itemComboBox();

            }

        } catch (IOException | JSONException | AuthException | InvalidKeyException ex) {
            Logger.getLogger(ListSalleSportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void affiche(){
         ISalleSport ss = new SalleSportServices();
        List<SalleSport> listSalleSport = new ArrayList<>();

        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;

        int column = 0;
        int row = 1;
        try {
            incomingToken = new JWebToken(bearerToken);
            System.out.println(bearerToken);
            if (!incomingToken.isValid()) {

//                get id and idRole for current user
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                idRole = Integer.parseInt(audience);
                idUser = Integer.parseInt(subject);
                listSalleSport.addAll(ss.affichageByIdPss(idUser));
                

                for (int i = 0; i < listSalleSport.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../PssAffiche/PssAffiche.fxml"));

                    HBox hbox = loader.load();

                    PssAfficheController c = loader.getController();
                    c.setData(listSalleSport.get(i));
                    Lsport.add(hbox, column, row++);
//                System.out.println(column+" "+row);
                    //set grid width
                    Lsport.setMinWidth(500);
                    Lsport.setPrefWidth(500);
                    Lsport.setMaxWidth(500);
//
//                //set grid height
//                Lsport.setMinHeight(300);
//                Lsport.setPrefHeight(400);
//                Lsport.setMaxHeight(400);

                    GridPane.setMargin(hbox, new Insets(25));
                    GridPane.setValignment(scrollListSalle, VPos.CENTER);
                }
//                getInfoCurrentUser(idUser);
//                itemComboBox();

            }

        } catch (IOException | JSONException | AuthException | InvalidKeyException ex) {
            Logger.getLogger(ListSalleSportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void itemComboBox() {

        ObservableList<String> items = FXCollections.observableArrayList("Déconnexion");
        clientComboBox.setItems(items);
        clientComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (newValue.equals(clientComboBox.getItems().get(0))) {
                ActionEvent event = null;

                logout(event);
            }

        });

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
            stage.setResizable(false);
            stage.sizeToScene();
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    public void goDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene newScene;
        newScene = new Scene(root);
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    public void getInfoCurrentUser(int id) throws URISyntaxException {
        IUtilisateur iu = new UtilisateurServices();
        String nom = iu.queryUserById(id).getNom().substring(0, 1).toUpperCase() + iu.queryUserById(id).getNom().substring(1);
        String prenom = iu.queryUserById(id).getPrenom().substring(0, 1).toUpperCase() + iu.queryUserById(id).getPrenom().substring(1);
        nomPrenom.setText(nom + " " + prenom);
        Image im = new Image(getClass().getResource("../uicontrolers/user.png").toURI().toString());
        myCircle.setFill(new ImagePattern(im));
        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITESMOKE));
        myCircle.setStroke(Color.WHITESMOKE);
    }

    @FXML
    private void ajoutSalleDeSport(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("../addSalleDeSport/addSalleDeSport.fxml"));
 
        
      
        hboxScene.getChildren().removeAll();
        hboxScene.getChildren().setAll(root);
    }
}
