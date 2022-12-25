/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.AffichageRegime;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.MaConnexion;
import util.Statics;
import model.Regime;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class ModifierRegimeController implements Initializable {

    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtKg;
    @FXML
    private CheckBox vegetarien;
    @FXML
    private CheckBox vegan;
    @FXML
    private CheckBox omnivore;
    @FXML
    private Button BtnPublier;
    @FXML
    private TextArea TxtPtDej;
    @FXML
    private TextArea TxtDinner;
    @FXML
    private TextArea TxtDej;
     Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     TxtNom.setText(Statics.rr.getNom());
       TxtKg.setText(String.valueOf(Statics.rr.getNbkg()));
       TxtDej.setText(Statics.rr.getDej());
       TxtPtDej.setText(String.valueOf(Statics.rr.getDej()));
       TxtDinner.setText(Statics.rr.getDinner());
                   
            if (!(vegetarien.isSelected() || vegan.isSelected() || omnivore.isSelected() )) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
         
        }
    
           if (vegetarien.isSelected()) {
            Statics.rr.setType("vegetarien");
        }
          
              if (vegan.isSelected()) {
            Statics.rr.setType("vegan");
        }
          
                if (omnivore.isSelected()) {
            Statics.rr.setType("omnivore");
        }
    }    

     

    
    
    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
        
         
        
         String req = "UPDATE regime SET nomregime=?,type=?,nbkg= ?,petitdej=?,dej=?,dinner=? WHERE id= ?";
     
        try {
            
            
            
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,TxtNom.getText());
           ps.setString(2,Statics.rr.getType() );
            
            ps.setString(3,TxtKg.getText());
            ps.setString(4, TxtPtDej.getText());
            ps.setString(5, TxtDej.getText());
            
            ps.setString(6, TxtDinner.getText());
        
          /////////////////
            ps.setInt(7, Statics.rr.getIdregime());
            System.out.println(ps);

            ps.executeUpdate();
         
//              Notification.notificationSuccess("Coaching MODIFIER AVEC SUCCES", "Merci pour voter contribution ");
        } catch (SQLException ex) {
            Logger.getLogger(ModifierRegimeController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
               
 Stage stage = (Stage)BtnPublier.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
                       
 Stage stage = (Stage)BtnPublier.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
