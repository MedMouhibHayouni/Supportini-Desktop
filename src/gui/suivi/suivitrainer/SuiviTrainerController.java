/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.suivitrainer;

import Exception.AuthException;
import gui.AffichCoaching.ListCoachings;
import gui.profil.ProfilFXMLController;
import interfaces.IDemandeSuivi;
import interfaces.IEntrainee;
import interfaces.IUtilisateur;
import interfaces.Ifeedback;
import interfaces.Isuivi;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Suivi;
import org.json.JSONException;
import services.DemandeSuivi_Service;
import services.EntraineeServices;

import services.Suivie_Services;
import services.UtilisateurServices;
import services.feedback_Services;
import util.JWebToken;
import util.MaConnexion;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class SuiviTrainerController implements Initializable {

    @FXML
    private Label DateSuivi;
    @FXML
    private Label PoidsActuelle;
    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private Label ageSuivi;
    @FXML
    private Label tailleshow;
    @FXML
    private Button histo;
    @FXML
    private Button addsuivi;
    @FXML
    private HBox sceneAdd;

    Suivi s = new Suivi();

    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Label imclbl;
    @FXML
    private Label alerteDemande;
    @FXML
    private Label tailleshow1;
    @FXML
    private Label PoidsActuelle1;

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
            //int idRole = Integer.parseInt(audience);
            IEntrainee ie = new EntraineeServices();

            int idUser = Integer.parseInt(subject);
            int identr = ie.queryById(idUser).getId();
            getInfoCurrentUser(identr);
            //refreshing(identr);
            //scenesuivi.getScene().getWindow().setWidth(scenesuivi.getScene().getWidth()+0.001);
            try {
                getForChart();
            } catch (SQLException ex) {
                Logger.getLogger(SuiviTrainerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //refreshing(idUser);
        } catch (URISyntaxException | JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getInfoCurrentUser(int id) throws URISyntaxException {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
            int idUser = Integer.parseInt(subject);
            IEntrainee ie = new EntraineeServices();
            int identr = ie.queryById(idUser).getId();
            IDemandeSuivi iDemandeSuivi = new DemandeSuivi_Service();
            Ifeedback ifeedback = new feedback_Services();
            IUtilisateur iu = new UtilisateurServices();
            String nom = iu.queryUserById(idUser).getNom();
            String prenom = iu.queryUserById(idUser).getPrenom();
            Isuivi is = new Suivie_Services();
            int idsuivi = is.queryById(id).getId();
            //System.out.println(is.queryById(idUser).getId());
            ListCoachings lic = new ListCoachings();
         
            //Isuivi is = new Suivie_Services();
            //Suivi s = new Suivi();
            if (is.queryById(id).getPoidsActuelle() != 0 || is.queryById(id).getTaille() != 0 || is.queryById(id).getDateSuivi() != null || is.queryById(id).getImc() != 0) {
                PoidsActuelle.setText(String.valueOf(is.queryById(id).getPoidsActuelle()));
                tailleshow.setText(String.valueOf(is.queryById(id).getTaille()));
                if (is.queryById(id).getImc() < 18.5) {
                    imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Maigreur");
                    imclbl.setStyle("-fx-text-fill: DeepSkyBlue;");
                } else if (is.queryById(id).getImc() < 25) {
                    imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Normal");
                    imclbl.setStyle("-fx-text-fill: #00FF7F;");
                } else if (is.queryById(id).getImc() < 30) {
                    imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Surpoids");
                    imclbl.setStyle("-fx-text-fill: #FFEA00;-fx-font-weight: bolder;");

                } else if (is.queryById(id).getImc() < 35) {
                    imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité modérée");
                    imclbl.setStyle("-fx-text-fill: #FFD700;");
                } else if (is.queryById(id).getImc() < 40) {
                    imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité sévére");
                    imclbl.setStyle("-fx-text-fill: #FF4500;");
                } else {
                    imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité massive");
                    imclbl.setStyle("-fx-text-fill: #FF0000;");
                }

            } else {
                PoidsActuelle.setText("Ajouter une nouvelle Suivi");
                DateSuivi.setText("Ajouter une nouvelle Suivi");
                tailleshow.setText("Ajouter une nouvelle Suivi");
                imclbl.setText("Ajouter une nouvelle Suivi");

            }

            long miliseconds = System.currentTimeMillis();
            Date date = new Date(miliseconds);
            Date date_suiviDate = is.queryById(id).getDateSuivi();
            ageSuivi.setText(String.valueOf(ie.queryById(idUser).getAge()));
//            System.out.println(date.toLocalDate().isEqual(date_suiviDate.toLocalDate()));
            //System.out.println(date);
                        //System.out.println(idsuivi);
            if (date_suiviDate == null) {
                DateSuivi.setText("Ajouter une nouvelle Suivi");
            } else if (date.before(date_suiviDate) || date.toLocalDate().isEqual(date_suiviDate.toLocalDate())) {
                DateSuivi.setText(String.valueOf(date_suiviDate));
                if (ifeedback.afficherfeedback(idsuivi) == null) {
                    alerteDemande.setText("Aucun Feedback Dans ce Moment");
                } else {
                    alerteDemande.setText("Votre Coach Vous dites : "+"\""+(ifeedback.afficherfeedback(idsuivi).getFeedback())+"\"");
                }

            } else {
                //alerteDemande.setText((ifeedback.afficherfeedback(idsuivi).getFeedback()));
                alerteDemande.setText(("Merci d'envoyer votre nouvelle Suivi"));
                //System.out.println(idsuivi);
                //System.out.println(ifeedback.afficherfeedback(10).getFeedback());
            }

            //System.out.println(iDemandeSuivi.afficherDemandeSuivi(idUser).getDemande());
        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getForChart() throws SQLException {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        String req2 = "SELECT * FROM suivi WHERE fk_id_entr = ? ORDER BY date_suivi ASC";
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
            IEntrainee ie = new EntraineeServices();

            int idUser = Integer.parseInt(subject);
            int identr = ie.queryById(idUser).getId();

            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1, identr);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                series.getData().add(new XYChart.Data<>(String.valueOf(res.getDate(8)), res.getInt(5)));
            }
            chart.getData().clear();
            chart.layout();
            chart.getData().add(series);
            //series.getNode().setStyle("-fx-fill:#;");

        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void goToHisto(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) histo.getScene().getWindow();
            //stage.close();
            Stage primaryStage = new Stage();
            URL url;
            url = new File("src/gui/suivi/histosuivi/DatePicker.fxml").toURI().toURL();

            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            primaryStage.setTitle("HistoSuivi");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (MalformedURLException ex) {
            Logger.getLogger(SuiviTrainerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addSuivibtn(ActionEvent event) throws IOException {
        try {
            //Stage stage = (Stage) histo.getScene().getWindow();
            //stage.close();
            //Stage primaryStage = new Stage();
            URL url;
            url = new File("src/gui/suivi/ajoutSuivi/AjoutSuivi.fxml").toURI().toURL();

            Parent root = FXMLLoader.load(url);
            sceneAdd.getChildren().removeAll();
            sceneAdd.getChildren().setAll(root);

        } catch (MalformedURLException ex) {
            Logger.getLogger(SuiviTrainerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    private void refresh(ActionEvent event) {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
            IEntrainee ie = new EntraineeServices();

            int idUser = Integer.parseInt(subject);
            int identr = ie.queryById(idUser).getId();
            getInfoCurrentUser(identr);
            //refreshing(identr);
            //scenesuivi.getScene().getWindow().setWidth(scenesuivi.getScene().getWidth()+0.001);
            try {
                getForChart();
            } catch (SQLException ex) {
                Logger.getLogger(SuiviTrainerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //refreshing(idUser);
        } catch (URISyntaxException | JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    

}
