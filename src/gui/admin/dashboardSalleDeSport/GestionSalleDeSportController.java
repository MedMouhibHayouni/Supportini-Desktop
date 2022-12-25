/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin.dashboardSalleDeSport;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.SalleSport;
import util.MaConnexion;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author HSOUNA
 */
public class GestionSalleDeSportController implements Initializable {

    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private VBox scene;
    @FXML
    private TableColumn<SalleSport, String> IDColmn;
    @FXML
    private TableColumn<SalleSport, String> SalleDeSportColmn;
    @FXML
    private TableColumn<SalleSport, String> NumTelColmn;
    @FXML
    private TableColumn<SalleSport, String> VilleColmn;
    @FXML
    private TableColumn<SalleSport, String> RueColmn;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtNumtel;
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtRue;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private TableView<SalleSport> table;
    @FXML
    private TableColumn<?, ?> CodePostalColmn;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtPrix;
    @FXML
    private TableColumn<?, ?> PrixColmn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
    PreparedStatement pst;
    int myIndex;
    int id;

    public void table() {

        ObservableList<SalleSport> SalleDesSports = FXCollections.observableArrayList();
        try {
            pst = (PreparedStatement) cnx.prepareStatement("SELECT  `id`,`nomSalle`, `numTel`, `Ville`, `Rue`, `codePostal`, `prix` FROM `salledessport`");

            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    SalleSport st = new SalleSport();
                    st.setId(rs.getInt("id"));
                    st.setNomSalle(rs.getString("nomSalle"));
                    st.setNumTel(rs.getInt("numTel"));
                    st.setVille(rs.getString("Ville"));
                    st.setRue(rs.getString("Rue"));
                    st.setCodePostal(rs.getString("CodePostal"));
                    st.setPrix(rs.getInt("prix"));
                    SalleDesSports.add(st);

                }
            }
            table.setItems(SalleDesSports);
            IDColmn.setCellValueFactory(new PropertyValueFactory("id"));
            SalleDeSportColmn.setCellValueFactory(new PropertyValueFactory("nomSalle"));
            NumTelColmn.setCellValueFactory(new PropertyValueFactory("numTel"));
            VilleColmn.setCellValueFactory(new PropertyValueFactory("Ville"));
            RueColmn.setCellValueFactory(new PropertyValueFactory("Rue"));
            CodePostalColmn.setCellValueFactory(new PropertyValueFactory("codePostal"));
            PrixColmn.setCellValueFactory(new PropertyValueFactory("prix"));

        } catch (SQLException ex) {
            Logger.getLogger(GestionSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setRowFactory(tv -> {
            TableRow<SalleSport> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtNom.setText(table.getItems().get(myIndex).getNomSalle());
                    txtNumtel.setText(String.valueOf(table.getItems().get(myIndex).getNumTel()));
                    txtVille.setText(table.getItems().get(myIndex).getVille());
                    txtRue.setText(table.getItems().get(myIndex).getRue());
                    txtCodePostal.setText(table.getItems().get(myIndex).getCodePostal());
                    txtPrix.setText(String.valueOf(table.getItems().get(myIndex).getPrix()));

                }
            });
            return myRow;
        });

    }

    private void reset(ActionEvent event) {
        txtNom.clear();
        txtNumtel.clear();
        txtVille.clear();
        txtRue.clear();
        txtCodePostal.clear();
        txtPrix.clear();
    }


    @FXML
    private void ModifierSalleDeSport(ActionEvent event) {
         String nomsalle, numtel, ville, rue, codepostal;

        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        nomsalle = txtNom.getText();
        numtel = txtNumtel.getText();
        ville = txtVille.getText();
        rue = txtRue.getText();
        codepostal = txtCodePostal.getText();
        try {
            pst = (PreparedStatement) cnx.prepareStatement("update salledessport set nomsalle = ?,NumTel = ? ,Ville = ?,Rue = ?,CodePostal = ? where id = ? ");
            pst.setString(1, nomsalle);
            pst.setString(2, numtel);
            pst.setString(3, ville);
            pst.setString(4, rue);
            pst.setString(5, codepostal);
            pst.setInt(6, id);
            pst.executeUpdate();
                   Notification.notificationSuccess("SALLE DE SPORT MODIFIER AVEC SUCCESS", "Merci");

            table();

        } catch (SQLException ex) {
            Logger.getLogger(GestionSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerSalleDeSport(ActionEvent event) {
          myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        try {
            pst = (PreparedStatement) cnx.prepareStatement("delete from salledessport where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Notification.notificationSuccess("SALLE DE SPORT SUPPRIMER AVEC SUCCESS", "Merci");
            table();

        } catch (SQLException ex) {
            Logger.getLogger(GestionSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
