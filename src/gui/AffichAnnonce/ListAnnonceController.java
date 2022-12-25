/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.AffichAnnonce;

import gui.AffichCoaching.ItemController;
import gui.AffichCoaching.ListCoachings;
import gui.PssAffiche.PssAfficheController;
import interfaces.AnnonceListener;
import interfaces.CoachingsListener;
import java.io.IOException;
import java.net.URISyntaxException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import static javax.xml.bind.DatatypeConverter.parseString;

import util.MaConnexion;
import model.Annonce;
import model.Coachings;
/**
 * FXML Controller class
 *
 * @author Elife-Kef-100
 */
public class ListAnnonceController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label prixlab;
    @FXML
    private Label fruitNameLable;
    @FXML
    private TextFlow txtDescription;
    @FXML
    private ComboBox<?> CombiDiscipline;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
 
    
      Connection cnx = MaConnexion.getInstance().getCnx();
          private AnnonceListener AnnonceListener;
              private String image;
                  private List<Annonce> clist = new ArrayList<>();
                  
                  
                      
                  
 private List<Annonce> getData() throws SQLException {
        List<Annonce> clist = new ArrayList<>();
        Annonce Annonce;
        String tt = "SELECT * FROM `annonce` ";
    
        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            Annonce = new Annonce();

          
            Annonce.setTitre(queryoutput.getString("titre"));
            Annonce.setPrix(queryoutput.getInt("prix"));
            Annonce.setImage(queryoutput.getString("image"));
            Annonce.setDiscipline(queryoutput.getString("discipline"));
            Annonce.setDescription(queryoutput.getString("description"));

            clist.add(Annonce);

        }

        return clist;
    }
      private String path;             
 private void seletedCoaching(Annonce Annonce) {
        fruitNameLable.setText(Annonce.getTitre());
        prixlab.setText(Integer.toString(Annonce.getPrix()));
//       labdiscipline.setText(Coachings.getDiscipline());




       txtDescription.getChildren().clear();
        Text t1 = new Text(Annonce.getDescription());
        txtDescription.getChildren().add(t1);
        t1.setStyle("-fx-font-family: serif;-fx-font-size: 20px;");


        //   this.img.setImage(image);
       // path = Annonce.getImage();
       
        chosenFruitCard.setStyle("-fx-background-color: #" + ";\n"
                + "    -fx-background-radius: 30;");
    
        Image im;
        try {
            im = new Image(getClass().getResource("../uicontrolers/images/" + Annonce.getImage()).toURI().toString());
            fruitImg.setImage(im);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ItemAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }           
                  
                  
                  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            clist.addAll(getData());
            if (clist.size() > 0) {
                seletedCoaching(clist.get(0));
                AnnonceListener = new AnnonceListener() {
                  

                    @Override
                    public void onClickListener(Annonce Annonce) {
                         seletedCoaching(Annonce);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < clist.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/AffichAnnonce/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemAnnonceController ItemAnnonceController = fxmlLoader.getController();
                    ItemAnnonceController.setData(clist.get(i), AnnonceListener);


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
            Logger.getLogger(ItemAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void inscrire(ActionEvent event) {
    }

    @FXML
    private void FiltreDiscipline(ActionEvent event) {
    }
    
}
