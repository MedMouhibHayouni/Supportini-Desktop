package gui.produits;

import interfaces.ICategories;
import java.io.IOException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import util.MaConnexion;
import model.Produit;
import javafx.scene.layout.GridPane;
import java.net.URISyntaxException;
import services.Categoriservices;

public class ProduitsFXMLController implements Initializable {

   static Produit chosenproduct;

    @FXML
    private ImageView ProdImg;

    @FXML
    private Label ProdNameLable;

    @FXML
    private Label ProdPriceLabel;

    @FXML
    private VBox chosenProdCard;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    Connection cnx = MaConnexion.getInstance().getCnx();
    private ArrayList<Produit> produits = new ArrayList<Produit>();

    private String image;
    private MyListener_Produit myListener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            produits.addAll(getData());
            if (produits.size() > 0) {
                setChosenProd(produits.get(0));
               
                myListener = new MyListener_Produit() {
                    @Override
                    public void onClickListener(Produit produits) {
                       
                        setChosenProd(produits);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < produits.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../../gui/produits/item.fxml"));
                    VBox vbox = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(produits.get(i), myListener);

                    if (column == 4) {
                        column = 0;
                        row++;
                    }

                    grid.add(vbox, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(vbox, new Insets(10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Produit> getData() throws SQLException {
        List<Produit> Produits = new ArrayList<>();
        Produit Prod;
        String tt = "SELECT * FROM `produit`";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            Prod = new Produit();
            Prod.setId(queryoutput.getInt("id"));
            Prod.setNom_produit(queryoutput.getString("nomproduit"));
            Prod.setPrix(Integer.parseInt(queryoutput.getString("prix")));
            Prod.setDescription(queryoutput.getString("description"));
          ICategories deptdao = Categoriservices.getInstance();
            Prod.setQuantite(Integer.parseInt(queryoutput.getString("quantite")));
            Prod.setImageProduit(queryoutput.getString("imageProduit"));

            Produits.add(Prod);

        }

        return Produits;
    }

    private void setChosenProd(Produit prod) {
        ProdNameLable.setText(prod.getNomproduit());
        ProdPriceLabel.setText(prod.getPrix() + "DT");
        String path;

//          this.img.setImage(image);
        path = prod.getImageProduit();

        Image aa;
        try {
            aa = new Image(getClass().getResource("../uicontrolers/" + prod.getImageProduit()).toURI().toString());

            ProdImg.setImage(aa);
            chosenproduct = prod;
        } catch (URISyntaxException ex) {
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}