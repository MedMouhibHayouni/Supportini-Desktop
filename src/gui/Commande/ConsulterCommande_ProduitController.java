/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Commande;

import interfaces.ICommande;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceCommande;
import util.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Anis-PC
 */
public class ConsulterCommande_ProduitController implements Initializable {

   private int id_Panier;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private Label fullName;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TableView<String> tableCommandes;
    @FXML
    private TableColumn<String, String> tablecolumnnom;
 List<String> hashcom = new ArrayList<>();
    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Text prix_tot;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      Preferences userPreferences = Preferences.userRoot();
//        id_Panier=Integer.parseInt(userPreferences.get("id_panier", "root"));
//        ICommande ic=new ServiceCommande();
//        hashcom.addAll(ic.commender(id_Panier));
////        for (Iterator<LignePanier> iterator = hashcom.iterator(); iterator.hasNext();) {
////            LignePanier next = iterator.next();
////            
////            
////        }
//       tablecolumnnom.setCellValueFactory(
//                new PropertyValueFactory<>("id"));
//      tableCommandes.setItems((ObservableList<String>) hashcom);
//    }    

     
        System.out.println("prix");
    }
    }    
   
