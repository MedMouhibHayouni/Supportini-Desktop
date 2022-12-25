
package gui.Produit;

import interfaces.ICategories;
import interfaces.IProduits;
import services.Categoriservices;
import services.Produitservices;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
  import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import model.Categorie;
import model.Produit;
import util.MaConnexion;
import util.Notification;
import util.Validation;

  
/**
 * FXML Controller class
 *
 * @author Anis-PC
 */
public class ProduitController implements Initializable {
   int index = -1;
   String filename = null;
    byte[] person_image = null;
    // appel connexion 
    Connection cnx = MaConnexion.getInstance().getCnx();

    @FXML
    private TextField Description;

  
   
    @FXML
    private Button Mdf;

    @FXML
    private TextField Quantite;

    @FXML
    private Button Supp;

    @FXML
    private Button addd;

    @FXML
    private ComboBox<Categorie> categorie;
    @FXML
    private Button pp;
    @FXML
    private TextField id;


    @FXML
    private TextField nomproduit;

    @FXML
    private Pane pnlOverview;

      @FXML
    
    private TextField rechercher;
    @FXML
    private Label welcome;
   
    @FXML
    private Label validationcat;
    @FXML
    private TextField prix;
    @FXML
   
    
    private TableView<Produit> produitTable;
    @FXML
    private TableColumn<Produit, Integer> idcol;
    @FXML
    private TableColumn<Produit, String> idnomproduit;

    @FXML
    private TableColumn<Produit, Float> idprix;
    @FXML
    private TableColumn<Produit, String> iddescription;
    @FXML
    private TableColumn<Produit, Categorie> idcategorie;
     @FXML
    private TableColumn<Produit, String> idimage;
    @FXML
    private TableColumn<Produit, Integer> idquantite;
    ObservableList<Categorie> dataList = FXCollections.observableArrayList();
    ObservableList<Produit> Produitlist = FXCollections.observableArrayList();
    private InputStream input;
    private ImageView img;
    @FXML
    private Button Catégories;
    @FXML
    private ImageView imgProd;
  
     private String path;
   

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String tt = "SELECT * FROM `categorie`";

        Statement statement;
        try {
            
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String x = queryoutput.getString("nom");

                dataList.add(new Categorie(x));
                categorie.setItems(dataList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // afficahage produit
        IProduits x = new Produitservices();
        Produitlist = x.DisplayAllproduit();

        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idnomproduit.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));

        idprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        iddescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        idcategorie.setCellValueFactory(new PropertyValueFactory<>("categorieId"));
        idquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        idimage.setCellValueFactory(new PropertyValueFactory<>("imageProduit"));

        produitTable.setItems(Produitlist);
        search();

    }

    public void search() {

        FilteredList<Produit> filteredData = new FilteredList<>(Produitlist, b -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((pr) -> {
                // If filter text is empty, display all product.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (pr.getNomproduit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.

                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Produit> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(produitTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        produitTable.setItems(sortedData);

    }

    
    @FXML
    private void delete(ActionEvent event) throws SQLException {

        if (id.getText().equals("")) {
            showMessageDialog(null, "vous devez selectionné un produit");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer");
            alert.setHeaderText("Vous êtes sur le point de supprimer le produit!");
            alert.setContentText("Voulez-vous supprimer ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;
                ResultSet rs;
                Integer nom = Integer.parseInt(id.getText());
                String xx = id.getText();
                String yy = "delete   from  produits where id = '" + nom + "' ";
                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "produits supprimer avec succes");
                nomproduit.clear();
                prix.clear();
                Description.clear();
                Quantite.clear();
                refreshTable();
                this.imgProd.setVisible(false);
                search();
            }
        }
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {

        if (categorie.getSelectionModel().isSelected(-1)) {
            showMessageDialog(null, "categorie  must be selected");
            categorie.requestFocus();
            
        } else {

            if (this.isValidated()) {
                String s = categorie.getSelectionModel().getSelectedItem().toString();
                PreparedStatement ps, cat;
                ResultSet rs, rs2;
                String req = "INSERT INTO `produit` ( `nomproduit`, `prix`, `quantite`,`imageProduit`, `description`, ` categorieId`) VALUES (?,?,?,?,?,?)";
                String yy = "SELECT * FROM produit WHERE nomproduit ='" + nomproduit.getText() + "'";
                 String query = "select * from Categorie WHERE nom = ?";
                ps = cnx.prepareStatement(yy);
                 cat = cnx.prepareStatement(query);
                cat.setString(1, s);
                rs2 = cat.executeQuery();
                rs = ps.executeQuery();
                if (rs.next()) {
                    showMessageDialog(null, "deja existe");
                    nomproduit.requestFocus();
                } else {
                    Produit produit = new Produit();
                    produit.setNom_produit(nomproduit.getText());
                   
                    produit.setPrix(Integer.parseInt(prix.getText()));
                    produit.setDescription(Description.getText());
                    ICategories deptdao = Categoriservices.getInstance();
//                 produit.setCategorieId(deptdao.findcatBynom(s));
                    produit.setQuantite(Integer.parseInt(Quantite.getText()));
                    produit.setImageProduit(path);
                   IProduits x = new Produitservices();
                    x.insertproduit(produit);

                    System.out.println("PS : produit ajoutée avec succés!");
                    nomproduit.clear();
                    prix.clear();
                    Description.clear();
                    Quantite.clear();
                    refreshTable();
                      this.imgProd.setVisible(false);
                    showMessageDialog(null, "produit ajouter avec succes");
                    search();

                }

            }
        }
    }

    private boolean isValidated() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();
        String number = "[0-9]+";
        Pattern x = Pattern.compile(number);
        if (nomproduit.getText().equals("")) {

            showMessageDialog(null, "Le champ de texte nom du produit ne peut pas être vide.");
            nomproduit.requestFocus();
        } else if (prix.getText().equals("")) {
            showMessageDialog(null, "Le champ de texte prix ne peut pas être vide.");
            prix.requestFocus();
        
        } else if (Description.getText().equals("")) {
            showMessageDialog(null, "Le champ de texte Description ne peut pas être vide.");
            Description.requestFocus();
        } else if (Quantite.getText().equals("") && Quantite.equals("[a-zA-Z_]+")) {
            showMessageDialog(null, "Le champ de texte Quantite ne peut pas être vide.");
            Quantite.requestFocus();
        } else if (!x.matcher(Quantite.getText()).matches()) {
            showMessageDialog(null, "Quantite contains only number.");
            Quantite.requestFocus();
        } else if (!x.matcher(prix.getText()).matches()) {
            showMessageDialog(null, "prix contient seulement des nombre.");
            prix.requestFocus();
        } else if (categorie.getSelectionModel().isSelected(-1)) {
            showMessageDialog(null, "vous devez sélectionner une catégorie");
            categorie.requestFocus();
        } else if (path == null) {
            showMessageDialog(null, "image Obligatoire");
            pp.requestFocus();} 
        else {
            return true;
        }
        return false;
    }

//!name.matches("[a-zA-Z_]+")
    @FXML

    private void update(ActionEvent event) throws SQLException {

        if (id.getText().equals("")) {
            showMessageDialog(null, "vous devez sélectionner un produit");
        } else {

            PreparedStatement cat;
            ResultSet rs2;
            String s = categorie.getSelectionModel().getSelectedItem().toString();
            ICategories x = Categoriservices.getInstance();
            String query = "select * from categorie WHERE nom = ?";
            cat = cnx.prepareStatement(query);
            cat.setString(1, s);
            rs2 = cat.executeQuery();
            if (rs2.next()) {

                String s1 = rs2.getString("id");
                PreparedStatement ps;
                ResultSet rs;
                String nom = nomproduit.getText();
                String xx = id.getText();

                String yy = "update   produits set nomproduit ='" + nom + "' , prix ='" + prix.getText() + "', Description ='" + Description.getText() + "' , quantite ='" + Quantite.getText() + "' , categories ='" + s1 + "' ,image =?  where id = '" + xx + "' ";

                ps = cnx.prepareStatement(yy);
               ps.setString(1, filename);
                ps.execute();

                showMessageDialog(null, "produit modifier avec succes");
                refreshTable();
                search();
            }
        }
        // cat = cnx.prepareStatement(query);
        //  cat.setString(1, s);
        // rs2 = cat.executeQuery();

    }

    @FXML
    private void refreshTable() {
        dataList.clear();
        IProduits x = new Produitservices();
        Produitlist = x.DisplayAllproduit();
        produitTable.setItems(Produitlist);
        String tt = "SELECT * FROM `categorie`";

        Statement statement;
        try {
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String xx = queryoutput.getString("nom");

                dataList.add(new Categorie(xx));
                categorie.setItems(dataList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     @FXML
    private void uploud(ActionEvent event) throws IOException {
        FileChooser chooser = new  FileChooser();
         chooser.setTitle("Choisir une  Image");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().clear();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(null);
        if (file.isFile()) {
           
            String absPath = file.getAbsolutePath();
            String newpath = "src/gui/uicontrolers/imagesproduits/";
            File dir = new File(newpath);
            if (!dir.exists()) {
                // folder wa7dd ken barchaa mkdirs
                dir.mkdir();
            }
            File sourceFile = null;
            File destinationFile = null;
            String extension = absPath.substring(absPath.lastIndexOf('.') + 1);
            sourceFile = new File(absPath);
            String nameFile = Validation.randomString();
            File newFile = new File(newpath + nameFile+ "." + extension);
            Files.copy(sourceFile.toPath(), newFile.toPath());
            //   System.out.println(destinationFile);
            
             path= nameFile+ "." + extension;
            System.out.println(file.toURI().toString());
            imgProd.setImage(new Image(file.toURI().toString()));
        } else {
           Notification.notificationError("ERREUR", "Il faut choisir une image");
        }
           Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);

    }


    @FXML
    void getSelected(MouseEvent event) {

        index = produitTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        id.setText(String.valueOf(idcol.getCellData(index)));
        nomproduit.setText(idnomproduit.getCellData(index));
        prix.setText(String.valueOf(idprix.getCellData(index)));
        Description.setText(iddescription.getCellData(index));
        Quantite.setText(String.valueOf(idquantite.getCellData(index)));
        Categorie xx = idcategorie.getCellData(index);

        categorie.getSelectionModel().select(xx);
        this.img.setVisible(true);
        String path = idimage.getCellData(index);
        Image image = new Image("file:" + path);
        this.img.setImage(image);

     

    }

    @FXML
    private void Catégoriesrediractor(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("../Categorie/Categories.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setTitle("Gestion Catégories");
            stage.setScene(scene);
           
            stage.sizeToScene();
            stage.show();
            
      
           
        }
    }
