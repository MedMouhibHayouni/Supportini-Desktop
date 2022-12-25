package gui.AffichRecette;

import Exception.AuthException;
import gui.PssAffiche.PssAfficheController;
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

import interfaces.RecetteListener;
import model.Recette;

import java.io.IOException;
import java.net.URISyntaxException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.json.JSONException;
import util.JWebToken;
import util.MaConnexion;
import util.Statics;

public class ListRecette implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;


    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Recette> clist = new ArrayList<>();
    private String image;
    private RecetteListener RecetteListener;
    Connection cnx = MaConnexion.getInstance().getCnx();
 
    @FXML
    private VBox vtest;
    @FXML
    private TextFlow TxtIng;
    @FXML
    private TextFlow TxtPreparation;
    @FXML
    private Button btnRetour;
 

    
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        
        try {
            clist.addAll(getData());
            System.out.println(clist.get(0).getImage());
            if (clist.size() > 0) {
                seletedRecette(clist.get(0));
                RecetteListener = new RecetteListener() {
                    @Override
                    public void onClickListener(Recette Recette) {
                        seletedRecette(Recette);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < clist.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("./item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(clist.get(i), RecetteListener);

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
            Logger.getLogger(ListRecette.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        
        
    }
    
    

    

    
    
    private List<Recette> getData() throws SQLException {
        List<Recette> clist = new ArrayList<>();
        Recette Recette;
        String tt = "SELECT * FROM `recette`";


        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            Recette = new Recette();
            Recette.setNomrecette(queryoutput.getString("nomrecette"));
      
            Recette.setImage(queryoutput.getString("imagerecette"));
            Recette.setIdRegime(queryoutput.getInt("idregime"));
            Recette.setIngredien(queryoutput.getString("ingredien"));
            Recette.setPrepatation(queryoutput.getString("preparatin"));
        
            
//             Recette.setDiscipline(queryoutput.getString("discipline"));
//             Recette.setDescription(queryoutput.getString("description"));
            clist.add(Recette);

        }

        return clist;
    }

    private void seletedRecette(Recette Recette) {
        fruitNameLable.setText(Recette.getNomrecette());
      

       TxtIng.getChildren().clear();
        Text t1 = new Text(Recette.getIngredien());
        TxtIng.getChildren().add(t1);
        t1.setStyle("-fx-font-family: serif;-fx-font-size: 20px;");

      
     TxtPreparation.getChildren().clear();
        Text t2 = new Text(Recette.getPrepatation());
        TxtPreparation.getChildren().add(t2);
        t1.setStyle("-fx-font-family: serif;-fx-font-size: 20px;");

     
    
        chosenFruitCard.setStyle("-fx-background-color: #" +  ";\n"
                + "    -fx-background-radius: 30;");
        

        Image im;
        try {
            im = new Image(getClass().getResource("../images/" + Recette.getImage()).toURI().toString());
            fruitImg.setImage(im);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ListRecette.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 
    }

    @FXML
    private void RetourToEspace(ActionEvent event) throws IOException {
               Stage stage = (Stage)btnRetour.getScene().getWindow();
             stage.close();
             
             Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
     
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    



}
