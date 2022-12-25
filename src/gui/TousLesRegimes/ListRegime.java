package gui.TousLesRegimes;

import Exception.AuthException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import interfaces.RegimeListen;
import model.Regime;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.sql.Connection;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.json.JSONException;
import util.JWebToken;
import util.MaConnexion;
import util.Statics;

public class ListRegime implements Initializable {

     @FXML
    private VBox chosenFruitCard;

   @FXML
    private TextFlow TxtPtitDej;
    @FXML
    private TextFlow TxtDej;
    @FXML
    private TextFlow TxtDinner;


    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Regime> regimes = new ArrayList<>();
   
    private RegimeListen RegimeListen;
      
    Connection cnx = MaConnexion.getInstance().getCnx();
    private Button btnmodifier;
    
    
      int idRole,idUser;
    @FXML
    private Button ConsulterRecette;
      
//  public Integer jwt() {
//
//        Preferences userPreferences = Preferences.userRoot();
//
//        String bearerToken = userPreferences.get("BearerToken", "root");
//
//        //verify and use
//        JWebToken incomingToken;
//
//        try {
//            incomingToken = new JWebToken(bearerToken);
//
//            if (!incomingToken.isValid()) {
//
////                get id and idRole for current user
//                String audience = incomingToken.getAudience();
//                String subject = incomingToken.getSubject();
//                idRole = Integer.parseInt(audience);
//                idUser = Integer.parseInt(subject);
//
//                System.out.println(idUser);
//
//            }
//        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
//            Logger.getLogger(gui.AffichCoaching.ListCoachings.class.getName()).log(Level.SEVERE, null, ex); ////////ItemDashFXMLController////
//        }
//
//        return idUser;
//
//    }
 
    private List<Regime> getData() throws SQLException {

//            tt = "SELECT * FROM `regime` where idcoach ='" + jwt() + "' ";


 List<Regime> clist = new ArrayList<>();
        Regime Regime;
        String tt = "SELECT * FROM `regime`";
//           String   tt = "SELECT * FROM `regime` where idcoach ='" + jwt() + "' ";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
          Regime = new Regime();
       
            Regime.setIdregime(queryoutput.getInt("id"));
            Regime.setNom(queryoutput.getString("nom"));
            Regime.setNbkg(queryoutput.getInt("nbrkg"));
            Regime.setType(queryoutput.getString("type"));
            Regime.setDej(queryoutput.getString("dejeuner"));
            Regime.setPetitdej(queryoutput.getString("petidej"));
            Regime.setDinner(queryoutput.getString("dinner"));
        
           
            clist.add(Regime);

        }

        return clist;
    
        
    }
  static int IdRegime , idcoach,nbkg;
        static String type,dej,ptitdej,din,nom;
        
        
        
    private void setChosenRegime(Regime Regime) {
     TxtDej.getChildren().clear();
        Text t1 = new Text(Regime.getDej());
        TxtDej.getChildren().add(t1);
      ///////////////////////////////////////////////
       TxtPtitDej.getChildren().clear();
        Text t2 = new Text(Regime.getPetitdej() );
        TxtPtitDej.getChildren().add(t2);
//////////////////////////////////////////////////
     TxtDinner.getChildren().clear();
        Text t3 = new Text(Regime.getDinner());
        TxtDinner.getChildren().add(t3);
        ///////////////
         IdRegime = Regime.getIdregime();
        
      
         nbkg=(int) Regime.getNbkg();
         type=Regime.getType();
         dej=Regime.getDej();
         ptitdej=Regime.getPetitdej();
         din=Regime.getDinner();
         nom=Regime.getNom();
    }

  
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            regimes.addAll(getData());
            if (regimes.size() > 0) {
                setChosenRegime(regimes.get(0));
                RegimeListen = new RegimeListen() {
                    @Override
                    public void onClickListener(Regime Regime) {
                        setChosenRegime(Regime);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < regimes.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(regimes.get(i), RegimeListen);

                    if (column == 1) {
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
            Logger.getLogger(gui.TousLesRegimes.ListRegime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


//            Stage stage = (Stage)TxtPtitDej.getScene().getWindow();
//             stage.close();
//             
//             Parent root = FXMLLoader.load(getClass().getResource("/gui/AffichageRegime/ListRegime.fxml"));
//     
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
   
    
   

   
   

    @FXML
    private void ConsulterRecette(ActionEvent event) throws IOException {
        
//        System.out.println("test recette");
//         Statics.rr.setIdregime(IdRegime);
          Stage stage = (Stage)ConsulterRecette.getScene().getWindow();
             stage.close();
        
             Parent root = FXMLLoader.load(getClass().getResource("../AffichRecette/ListRecette.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    

}
