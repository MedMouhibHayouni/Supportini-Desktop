/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.modifierSalleSport;

import gui.ItemSalleSport.ItemSalleSportFXMLController;
import interfaces.ISalleSport;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.SalleSport;
import services.SalleSportServices;
import util.MaConnexion;
import util.Notification;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class ModifierSalleSportController implements Initializable {

    @FXML
    private Label validationNom;
    @FXML
    private TextField txtNumtel;
    @FXML
    private Label validationtNumtel;
    @FXML
    private TextField txtVille;
    @FXML
    private Label validationtVille;
    @FXML
    private TextField txtRue;
    @FXML
    private Label validationtRue;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private Label validationtCodePostal;
    @FXML
    private TextField txtPrix;
    @FXML
    private Label validationtPrix;
    @FXML
    private TextField txtDuration;
    @FXML
    private Label validationtDuration;
    @FXML
    private TextField txtDescription;
    @FXML
    private Label validationtDescription;
    @FXML
    private ImageView imgVitrine;
    @FXML
    private Button BtnUpload;
    @FXML
    private TextField taimage;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNom;
    @FXML
    private Button btnModifier;
    private SalleSport Salle;
    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /////contriller modifier recuperer donnees
        txtId.setText(String.valueOf(Statics.xx.getId()));
        txtNom.setText(Statics.xx.getNomSalle());
        txtNumtel.setText(String.valueOf(Statics.xx.getNumTel()));
        txtVille.setText(Statics.xx.getVille());
        txtRue.setText(Statics.xx.getRue());
        txtCodePostal.setText(String.valueOf(Statics.xx.getCodePostal()));
        txtDescription.setText(Statics.xx.getDescription());
        txtDuration.setText(Statics.xx.getDuration());
        txtPrix.setText(String.valueOf(Statics.xx.getNumTel()));

    }

    @FXML
    private void uploadimage(ActionEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
//                 ISalleSport iSport = new SalleSportServices();
//        iSport.modifierSalleSport(Salle);
        String req = "UPDATE salledessport SET nomSalle=?,numTel=?,ville= ?,rue=?,codePostal=?,description=?,prix=?,duration=? WHERE id= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, txtNom.getText());
            ps.setString(2, txtNumtel.getText());
            ps.setString(3, txtVille.getText());
            ps.setString(4, txtRue.getText());
            ps.setString(5, txtCodePostal.getText());
            ps.setString(6, txtDescription.getText());
            ps.setString(7, txtPrix.getText());
            ps.setString(8, txtDuration.getText());
            ps.setInt(9, Statics.xx.getId());

        } catch (SQLException ex) {
            Logger.getLogger(SalleSportServices.class.getName()).log(Level.SEVERE, null, ex);

        }
        Notification.notificationSuccess("SALLE DE SPORT MODIFIER AVEC SUCCESS", "Merci");

        Stage stage = (Stage) btnModifier.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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


