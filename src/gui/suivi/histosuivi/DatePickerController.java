/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.histosuivi;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import gui.suivi.suivitrainer.SuiviTrainerController;
import interfaces.IEntrainee;
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
import java.sql.Statement;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Suivi;
import org.json.JSONException;
import services.EntraineeServices;
import services.Suivie_Services;
import util.JWebToken;
import util.MaConnexion;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class DatePickerController implements Initializable {

    @FXML
    private GridPane grid;

    private List<Suivi> suivis = new ArrayList<>();
    private String image;
    private MyListener_Suivi myListener;
    private ShowHistoFXMLController showscenehiste;
    Isuivi interIsuivis = new Suivie_Services();
    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Label name_perdate;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label name_perdate1;
    @FXML
    private Label name_perdate2;
    @FXML
    private Label name_perdate3;
    @FXML
    private TextField search;
    @FXML
    private Button btnsearch;

    private List<Suivi> getData(int id) throws SQLException {
        List<Suivi> suivis = new ArrayList<>();
        Suivi suivi;
        String tt = "SELECT * FROM suivi WHERE fk_id_entr = ? ORDER BY id DESC";

        //Statement statement;
        PreparedStatement ps = cnx.prepareStatement(tt);
        ps.setInt(1, id);
        ResultSet queryoutput = ps.executeQuery();
        while (queryoutput.next()) {
            suivi = new Suivi();
            suivi.setNomE(queryoutput.getString("nom"));
            suivi.setDateSuivi(queryoutput.getDate("date_suivi"));
            suivi.setId(queryoutput.getInt("fk_id_entr"));
            suivi.setImc(queryoutput.getDouble("imc"));
            suivi.setTaille(queryoutput.getInt("taille"));
            suivi.setPoidsActuelle(queryoutput.getInt("poids"));

            //System.out.println("Empty");
            suivis.add(suivi);

        }

        return suivis;
    }
    

    private void setChosenSuivi(Suivi suivi) {
        name_perdate.setText(String.valueOf("Date Suivi : " + suivi.getDateSuivi()));
        name_perdate1.setText(String.valueOf("Taille : " + suivi.getTaille()));
        name_perdate2.setText(String.valueOf("Poids : " + suivi.getPoidsActuelle()));
        name_perdate3.setText(String.valueOf("IMC : " + suivi.getImc()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            try {
                suivis.addAll(getData(identr));

                if (suivis.size() > 0) {
                    setChosenSuivi(suivis.get(0));
                    myListener = new MyListener_Suivi() {
                        @Override
                        public void onClickListener(Suivi suivi) {
                            setChosenSuivi(suivi);

                        }
                    };
                } else {
                    Notification.notificationError("DESOLE", "Vous allez inscrire d'abord avec l'un de nos Coach");

                }
                int column = 0;
                int row = 1;
                try {
                    for (int i = 0; i < suivis.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/gui/suivi/histosuivi/Suivi_Item.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        Suivi_ItemController itemController = fxmlLoader.getController();
                        itemController.setData(suivis.get(i), myListener);

                        if (column == 2) {
                            column = 0;
                            row++;
                        }

                        grid.add(anchorPane, column++, row); //(child,column,row)
                        //set grid width
                        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatePickerController.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (JSONException ex) {
            Logger.getLogger(DatePickerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthException ex) {
            Logger.getLogger(DatePickerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatePickerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void search(ActionEvent event) throws JSONException, AuthException, IOException, SQLException {
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
            try {
                suivis.addAll(getData(identr));

                if (suivis.size() > 0) {
                    setChosenSuivi(suivis.get(0));
                    myListener = new MyListener_Suivi() {
                        @Override
                        public void onClickListener(Suivi suivi) {
                            System.out.println("laaaaaaaaaaaaaaaaaaaaaaaaaaa");
                            setChosenSuivi(suivi);

                        }
                    };
                } else {
                    Notification.notificationError("DESOLE", "Vous allez inscrire d'abord avec l'un de nos Coach");

                }
                int column = 0;
                int row = 1;
                try {
                    for (int i = 0; i < suivis.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/gui/suivi/histosuivi/Suivi_Item.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        Suivi_ItemController itemController = fxmlLoader.getController();
                        itemController.setData(suivis.get(i), myListener);

                        if (column == 2) {
                            column = 0;
                            row++;
                        }

                        grid.add(anchorPane, column++, row); //(child,column,row)
                        //set grid width
                        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatePickerController.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (JSONException ex) {
            Logger.getLogger(DatePickerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthException ex) {
            Logger.getLogger(DatePickerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatePickerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
