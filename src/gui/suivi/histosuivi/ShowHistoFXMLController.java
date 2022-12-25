/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.histosuivi;

import gui.suivi.suivitrainer.SuiviTrainerController;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Suivi;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class ShowHistoFXMLController implements Initializable {

    @FXML
    private Label NomEntrainer;
    @FXML
    private Label PrenomEntrainer;
    @FXML
    private Label DateSuivi;
    @FXML
    private Label PoidsActuelle;
    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private Label ageSuivi;
    @FXML
    private Label tailleshow;
    Suivi s = new Suivi();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    private void setChosenSuivi(Suivi suivi){
        
        NomEntrainer.setText(String.valueOf(s.getNomE()));

    }

}
