
package gui.admin.dashboardUser;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gui.admin.dashboard.DashboardController;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Utilisateur;
import services.UtilisateurServices;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class GestionUserController implements Initializable {

    @FXML
    private VBox scene;
    @FXML
    private Button searchEnt;
    @FXML
    private Button searchCoachs;
    @FXML
    private Button seachPss;
    @FXML
    private TableView<Utilisateur> tableUser;
    @FXML
    private TableColumn<Utilisateur, String> colNom;
    @FXML
    private TableColumn<Utilisateur, String> colPrenom;
    @FXML
    private TableColumn<Utilisateur, String> colEmail;
    @FXML
    private TableColumn<Utilisateur, String> colCin;
    @FXML
    private TableColumn<Utilisateur, String> colPhone;
    @FXML
    private TableColumn<Utilisateur, String> colBtn;
    @FXML
    private TableColumn<Utilisateur, String> colId;
 ObservableList<Utilisateur> list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listUsers();
    }    
        private void refreshTable() {

        list.clear();
        IUtilisateur iu = new UtilisateurServices();

        list.addAll(iu.displayUser());

        tableUser.setItems(list);

    }

    public void listUsers() {
        refreshTable();

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(
                new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        colCin.setCellValueFactory(
                new PropertyValueFactory<>("cin"));
//        ObservableList<ImageView> listImg = FXCollections.observableArrayList();
//        for (int i = 0; i < list.size(); i++) {
//            ImageView Image = new ImageView();
//            Image img = new Image("../../uicontrolers/users/" + list.get(i).getImageName());
//            Image.setImage(img);
//            listImg.add(Image);
//        }

        IUtilisateur iu = new UtilisateurServices();
        tableUser.setFixedCellSize(50);
        tableUser.prefHeightProperty().bind(tableUser.fixedCellSizeProperty().multiply(Bindings.size(tableUser.getItems()).add(1.01)));
        tableUser.minHeightProperty().bind(tableUser.prefHeightProperty());
        tableUser.maxHeightProperty().bind(tableUser.prefHeightProperty());

        Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory;

        cellFoctory = (TableColumn<Utilisateur, String> param) -> {

            // make cell containing buttons
            TableCell<Utilisateur, String> cell;
            cell = new TableCell<Utilisateur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView unlockIcon = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK);
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.BAN);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        boolean x = false;
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            Utilisateur user = tableUser.getSelectionModel().getSelectedItem();

                            iu.banUser(user.getId(), user.getStatus());

                            refreshTable();

                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            Utilisateur user = tableUser.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("./updateModal.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            loader.setLocation();
                            UpdateModalController umc = loader.getController();

                            umc.setData(user.getId(), user.getNom(), user.getPrenom(), user.getEmail(), user.getCin(), user.getPhone(), user.getIdRole());

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.UTILITY);
                            stage.setScene(new Scene(parent));

                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
//                            } else {
//                                HBox managebtn = new HBox(editIcon, unlockIcon);
//                                managebtn.setStyle("-fx-alignment:center");
//                                HBox.setMargin(unlockIcon, new Insets(2, 2, 0, 3));
//                                HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
//                                setGraphic(managebtn);
//
//                                setText(null);
//                            }

//                     
                    }
                }
            };

            return cell;

        };

        colBtn.setCellFactory(cellFoctory);

        tableUser.setFixedCellSize(50);
        tableUser.prefHeightProperty().bind(tableUser.fixedCellSizeProperty().multiply(Bindings.size(tableUser.getItems()).add(1.01)));
        tableUser.minHeightProperty().bind(tableUser.prefHeightProperty());
        tableUser.maxHeightProperty().bind(tableUser.prefHeightProperty());
    }

  
    @FXML
    private void searchByIdRole() {
            searchEnt.setOnAction((event) -> {
            IUtilisateur iu = new UtilisateurServices();
            list.clear();
            list.addAll(iu.queryUserByRoleId(2));

            tableUser.setItems(list);
        });
        searchCoachs.setOnAction((event) -> {
            IUtilisateur iu = new UtilisateurServices();
            list.clear();
            list.addAll(iu.queryUserByRoleId(3));

            tableUser.setItems(list);
        });
        seachPss.setOnAction((event) -> {
            IUtilisateur iu = new UtilisateurServices();
            list.clear();
            list.addAll(iu.queryUserByRoleId(4));

            tableUser.setItems(list);
        });
    }
    
}
