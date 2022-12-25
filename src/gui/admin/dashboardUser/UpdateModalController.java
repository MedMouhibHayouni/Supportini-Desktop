/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin.dashboardUser;

import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Utilisateur;
import services.UtilisateurServices;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UpdateModalController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxrole;
    @FXML
    private TextField nomUpdte;
    @FXML
    private TextField prenomUpdte;
    @FXML
    private TextField emailUpdte;
    private TextField pwdUpdte;
    @FXML
    private TextField cinUpdte;
    @FXML
    private TextField phoneUpdte;
    @FXML
    private Label roleLAbel;
    @FXML
    private Label validnom;
    @FXML
    private Label validPrenom;
    @FXML
    private Label validEmail;
    @FXML
    private Label validCin;
    @FXML
    private Label validPhone;
private int id , idRole;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clear();
      selectComboBox();
    } 
    @FXML
    public void update(){
        clear();
          final String regex = "^(.+)@(\\S+)$";
         if (nomUpdte.getText().isEmpty()) {
                validnom.setText("Please entrer un nom");
                return;
            }
            if (prenomUpdte.getText().isEmpty()) {
                validPrenom.setText("Please entrer un prénom");
                return;
            }
            if (emailUpdte.getText().isEmpty()) {
               validEmail.setText("Please entrer un email");
                return;
            }

            if (!Validation.patternMatches(emailUpdte.getText(), regex)) {
                validEmail.setText("Please entrer un email existe");
                return;
            }
                if (!Validation.validationInteger(cinUpdte, validCin)) {
                return;
            }
            if (cinUpdte.getText().length() != 8) {
                validCin.setText("carte d'identité doit 8 chiffres!!");
                return;
            }
               if (!Validation.validationInteger(phoneUpdte, validPhone)) {
                return;
            }
            if (phoneUpdte.getText().length() != 8) {
                validPhone.setText("Num de portable doit 8 chiffres!!");
                return;
            }
            IUtilisateur iu = new UtilisateurServices();
            iu.updateUserByAdmin(id, nomUpdte.getText(), prenomUpdte.getText(), emailUpdte.getText(), cinUpdte.getText(), phoneUpdte.getText(), idRole);
               
    }
    public void clear(){
        validCin.setText("");
        validEmail.setText("");
        validnom.setText("");
        validPrenom.setText("");
        validPhone.setText("");
    }
    public void selectComboBox (){
        comboBoxrole.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            
            if (newValue.equals(comboBoxrole.getItems().get(0))) {
                 roleLAbel.setText("Administrateur");
                  this.idRole=1;
            }
            if (newValue.equals(comboBoxrole.getItems().get(1))) {
                roleLAbel.setText("Entrainé");
                 this.idRole=2;
            }
             if (newValue.equals(comboBoxrole.getItems().get(2))) {
                roleLAbel.setText("Entraineur");
                 this.idRole=3;
            }
              if (newValue.equals(comboBoxrole.getItems().get(3))) {
              roleLAbel.setText("Propriétaire de salle sport");
               this.idRole=4;
            }
        });
    }
    public void setData(int id , String name , String prenom , String email , String cin ,String phone , int Role ){
        this.id=id;
        nomUpdte.setText(name);
        prenomUpdte.setText(prenom);
        emailUpdte.setText(email);
        
        cinUpdte.setText(cin);
        phoneUpdte.setText(phone);
         ObservableList<String> items = FXCollections.observableArrayList("Administrateur", "Entrainé", "Entraineur", "Propriétaire de salle sport");
         comboBoxrole.setItems(items);
        switch (Role) {
            case 1:
                comboBoxrole.getSelectionModel().select(0);
                roleLAbel.setText("Administrateur");
                this.idRole=1;
                break;
            case 2:
                comboBoxrole.getSelectionModel().select(1); 
                  roleLAbel.setText("Entrainé");
                  this.idRole=2;
                break;
            case 3:
                comboBoxrole.getSelectionModel().select(2);
                 roleLAbel.setText("Entraineur");
                 this.idRole=3;
                break;
            default:
                  comboBoxrole.getSelectionModel().select(3);
                  roleLAbel.setText("Propriétaire de salle sport");
                  this.idRole=4;
                break;
        }
    }
    
}
