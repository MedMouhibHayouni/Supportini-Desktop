/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin.dashboardCoachings;

import gui.admin.dashboardSalleDeSport.GestionSalleDeSportController;
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
import model.Coachings;
import model.SalleSport;
import util.MaConnexion;
/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class GestionCoachingsController implements Initializable {
 Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<Coachings> table;
    @FXML
    private TableColumn<Coachings, Integer> IDColmn;
    @FXML
    private VBox scene;
    @FXML
    private TableColumn<Coachings, Integer> ColIdCoach;
    @FXML
    private TableColumn<Coachings, String> ColTitre;
    @FXML
    private TableColumn<Coachings, String> ColDesc;
    @FXML
    private TableColumn<Coachings, String> colPlaning;
    @FXML
    private TableColumn<Coachings, String> ColPrix;
    @FXML
    private TableColumn<Coachings, Integer> colNbmax;
    @FXML
    private TableColumn<Coachings, Integer> ColNbInscri;
    @FXML
    private TableColumn<Coachings, Coachings> ColDiscipline;

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


    @FXML
    private void SupprimerCoachings(ActionEvent event) throws SQLException {
        
         myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        pst = (PreparedStatement) cnx.prepareStatement("delete from coachings where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("supprimer coachings");

            alert.setHeaderText("supprimer de coachings terminée");
            alert.setContentText("supprimer de coachings terminée");

            alert.showAndWait();
            table();

        
    }
        public void table() {

        ObservableList<Coachings> Coachings = FXCollections.observableArrayList();
        try {
            pst = (PreparedStatement) cnx.prepareStatement("SELECT * FROM coachings");

            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                   Coachings st = new Coachings();
                       st.setId(rs.getInt("id"));
                    st.setIdcoach(rs.getInt("idcoach"));
                    st.setTitre(rs.getString("titre"));
                    st.setDiscipline(rs.getString("discipline"));
                    st.setDescription(rs.getString("description"));
                    st.setPlaning(rs.getString("planing"));
                      st.setPrix(rs.getString("prix"));
                      st.setNbmax(rs.getInt("nbmax"));
                      st.setNbinscri(rs.getInt("nbinscri"));
                    Coachings.add(st);

                }
            }
            table.setItems(Coachings);
            IDColmn.setCellValueFactory(new PropertyValueFactory("id"));
            ColIdCoach.setCellValueFactory(new PropertyValueFactory("idcoach"));
            ColTitre.setCellValueFactory(new PropertyValueFactory("titre"));
            ColDesc.setCellValueFactory(new PropertyValueFactory("description"));
            ColDiscipline.setCellValueFactory(new PropertyValueFactory("discipline"));
            ColPrix.setCellValueFactory(new PropertyValueFactory("prix"));
            colPlaning.setCellValueFactory(new PropertyValueFactory("planing"));
            ColNbInscri.setCellValueFactory(new PropertyValueFactory("nbinscri"));
            colNbmax.setCellValueFactory(new PropertyValueFactory("nbmax"));

        } catch (SQLException ex) {
            Logger.getLogger(GestionSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setRowFactory(tv -> {
            TableRow<Coachings> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                   

                }
            });
            return myRow;
        });

    }
}
