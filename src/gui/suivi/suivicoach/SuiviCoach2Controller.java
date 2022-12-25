/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.suivicoach;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import interfaces.ICoach;
import interfaces.IDemandeSuivi;
import interfaces.IEntrainee;
import interfaces.IUtilisateur;
import interfaces.Ifeedback;
import interfaces.Isuivi;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Demande_Suivi;
import model.Feedback;
import model.Suivi;
import model.Utilisateur;
import org.json.JSONException;
import services.CoachServices;
import services.DemandeSuivi_Service;
import services.EntraineeServices;
import services.Suivie_Services;
import services.UtilisateurServices;
import services.feedback_Services;
import util.JWebToken;
import util.MaConnexion;
import util.Notification;
//import supportini_app.SuiviCoach;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class SuiviCoach2Controller implements Initializable {

    @FXML
    private ListView<Suivi> ListEntrainer;
    Isuivi iS = new Suivie_Services();
    @FXML
    private Label NomEntrainer;
    @FXML
    private Label PrenomEntrainer;
    @FXML
    private Label DateSuivi;
    @FXML
    private Label PoidsActuelle;
    @FXML
    private BarChart<String, Integer> chart;
    private Button histobtn;
    @FXML
    private Label ageSuivi;
    @FXML
    private Label tailleshow;
    @FXML
    private Label imclbl;
    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private TextField sendfeedback;
    @FXML
    private HBox sceneAdd;
    @FXML
    private Button btnsearch;
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
            int idUser = Integer.parseInt(subject);
            ICoach ic = new CoachServices();
            int idcoach = ic.queryById(idUser).getId();
            Isuivi is = new Suivie_Services();
            System.out.println(idcoach);
            ObservableList<Suivi> suivis = FXCollections.observableArrayList(iS.afficherEntrainer(idcoach));
            System.out.println(iS.afficherEntrainer(5));
            ListEntrainer.setItems(suivis);

        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void show(MouseEvent event) throws SQLException {

        Suivi s = ListEntrainer.getSelectionModel().getSelectedItem();
        if (ListEntrainer == null) {
            ListEntrainer.setVisible(false);
        } else {
            NomEntrainer.setText(s.getNomE());
            PrenomEntrainer.setText(s.getPrenomE());
            DateSuivi.setText(String.valueOf(s.getDateSuivi()));
            PoidsActuelle.setText(String.valueOf(s.getPoidsActuelle()));
            ageSuivi.setText(String.valueOf(s.getAge()));
            tailleshow.setText(String.valueOf(s.getTaille()));
            //System.out.println(s.getId());
            if (s.getImc() < 18.5) {
                imclbl.setText(String.valueOf(s.getImc()) + " --> Maigreur");
                imclbl.setStyle("-fx-text-fill: DeepSkyBlue;");
            } else if (s.getImc() < 25) {
                imclbl.setText(String.valueOf(s.getImc()) + " --> Normal");
                imclbl.setStyle("-fx-text-fill: #00FF7F;");
            } else if (s.getImc() < 30) {
                imclbl.setText(String.valueOf(s.getImc()) + " --> Surpoids");
                imclbl.setStyle("-fx-text-fill: #FFEA00;-fx-font-weight: bolder;");

            } else if (s.getImc() < 35) {
                imclbl.setText(String.valueOf(s.getImc()) + " --> Obésité modérée");
                imclbl.setStyle("-fx-text-fill: #FFD700;");
            } else if (s.getImc() < 40) {
                imclbl.setText(String.valueOf(s.getImc()) + " --> Obésité sévére");
                imclbl.setStyle("-fx-text-fill: #FF4500;");
            } else {
                imclbl.setText(String.valueOf(s.getImc()) + " --> Obésité massive");
                imclbl.setStyle("-fx-text-fill: #FF0000;");
            }
            String req2 = "SELECT * FROM suivi WHERE fk_id_entr = ? ORDER BY date_suivi ASC";
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            PreparedStatement ps = cnx.prepareStatement(req2);//        XYChart.Series<Date,Double> series = new XYChart.Series<>();
//        series.getData().add(new XYChart.Data<>(s.getDateSuivi(),Double.valueOf(s.getPoidsActuelle())));
//        chart.getData().add(series);
//        
//        series.setName("Poids");
            //series.getData().add(new XYChart.Data("Jan", 100));
//        for(int i = 1; i < 5; i++) {
//        series.getData().add(new XYChart.Data(i, DateSuivi[i]));
//        }
//        chart.getData().add(series);
            ps.setInt(1, s.getFk_id_entr());
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                series.getData().add(new XYChart.Data<>(String.valueOf(res.getDate(8)), res.getInt(5)));

                //chart.getData().clear();
            }
            chart.getData().clear();
            chart.layout();
            chart.getData().add(series);

            //chart.getData().add(series);
        }
    }

    private void gotohisto(ActionEvent event) throws IOException {
        Stage stage = (Stage) histobtn.getScene().getWindow();
        //stage.close();
        Stage primaryStage = new Stage();
        URL url = new File("C:\\Users\\GIGABYTE\\Documents\\NetBeansProjects\\Elife\\LastPulled\\Supportini-main\\Supportini-main\\src\\gui\\suivi\\histosuivi\\DatePicker.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setTitle("HistoSuivi");
        primaryStage.setScene(scene);
        primaryStage.show();

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
            IUtilisateur iu = new UtilisateurServices();
            String nom = iu.queryUserById(identr).getNom();
            String prenom = iu.queryUserById(identr).getPrenom();
            Isuivi is = new Suivie_Services();

            NomEntrainer.setText(nom);
            PrenomEntrainer.setText(prenom);

        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void add_feedback(ActionEvent event) throws JSONException, AuthException, IOException {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;

        incomingToken = new JWebToken(bearerToken);
        String audience = incomingToken.getAudience();
        String subject = incomingToken.getSubject();
        //int idRole = Integer.parseInt(audience);
        int idUser = Integer.parseInt(subject);
        Ifeedback ifeedback = new feedback_Services();
        Isuivi is = new Suivie_Services();
        IEntrainee ie = new EntraineeServices();

        Suivi s = ListEntrainer.getSelectionModel().getSelectedItem();
        //int identr = ie.queryById(s.getFk_id_entr()).getId();
        int idSuivi = is.afficherEntrainerList().getId();
        System.out.println(idSuivi);
        //System.out.println(idfeedback);
        if (ifeedback.afficherfeedback(idSuivi) == null) {
            Feedback feedback = new Feedback(sendfeedback.getText(), s.getId());
            ifeedback.ajouterfeedback(feedback);
            sendfeedback.setStyle("-fx-border: 12px; -fx-text-box-border: green; -fx-focus-color: green;");
            System.out.println(sendfeedback.getText());
        } else if (ifeedback.afficherfeedback(idSuivi) != null && ifeedback.afficherfeedback(idSuivi).getId_suivi() == s.getId()) {
            System.out.println("Un feedback pour ce suivi existe déja");
            Notification.notificationError("Alerte !", "Un feedback pour ce suivi existe déja");
            sendfeedback.setStyle("-fx-border: 12px; -fx-text-box-border: red; -fx-focus-color: red;");
        }
    }

    private void add_feedback(MouseEvent event) throws JSONException, AuthException, IOException {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;

        incomingToken = new JWebToken(bearerToken);
        String audience = incomingToken.getAudience();
        String subject = incomingToken.getSubject();
        //int idRole = Integer.parseInt(audience);
        int idUser = Integer.parseInt(subject);
        Ifeedback ifeedback = new feedback_Services();
        Isuivi is = new Suivie_Services();
        IEntrainee ie = new EntraineeServices();

        Suivi s = ListEntrainer.getSelectionModel().getSelectedItem();
        //int identr = ie.queryById(s.getFk_id_entr()).getId();
        int idSuivi = is.afficherEntrainerList().getId();
        System.out.println(idSuivi);
        //System.out.println(idfeedback);
        if (ifeedback.afficherfeedback(idSuivi) == null) {
            Feedback feedback = new Feedback(sendfeedback.getText(), s.getId());
            ifeedback.ajouterfeedback(feedback);
            sendfeedback.setStyle("-fx-border: 12px; -fx-text-box-border: green; -fx-focus-color: green;");
            System.out.println(sendfeedback.getText());
        } else if (ifeedback.afficherfeedback(idSuivi) != null && ifeedback.afficherfeedback(idSuivi).getId_suivi() == s.getId()) {
            System.out.println("Un feedback pour ce suivi existe déja");
            Notification.notificationError("Alerte !", "Un feedback pour ce suivi existe déja");
            sendfeedback.setStyle("-fx-border: 12px; -fx-text-box-border: red; -fx-focus-color: red;");
        }
    }

}
