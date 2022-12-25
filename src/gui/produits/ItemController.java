
package gui.produits;
import Exception.AuthException;
import static gui.produits.ProduitsFXMLController.chosenproduct;
import interfaces.ILignePanier;
import interfaces.IPanier;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.LignePanier;
import model.Panier;
import model.Produit;
import org.json.JSONException;
import services.LignePanierservices;
import services.Panierservices;
import util.JWebToken;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author Anis-PC
 */
public class ItemController implements Initializable{

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    private int idPro,idUser ;
    @FXML
    private Button Ajout;
    @Override
    public void initialize(URL location, ResourceBundle resources)  {
    Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
             idUser = Integer.parseInt(subject);
         
        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(Prod);
    }

    private Produit Prod;
    private MyListener_Produit myListener;

    public void setData(Produit Prod, MyListener_Produit myListener) throws URISyntaxException {
        this.Prod = Prod;
        this.myListener = myListener;
        nameLabel.setText(Prod.getNomproduit());
        priceLable.setText(Prod.getPrix()+"DT" );
        System.out.println(Prod.getImageProduit());
      
        Image aa = new Image(getClass().getResource("../uicontrolers/imagesproduits/"+Prod.getImageProduit()).toURI().toString());
      
        img.setImage(aa);
    }

    @FXML
    private void Ajoutpanier(ActionEvent event) {
        IPanier ip =new Panierservices();
           ILignePanier il = new LignePanierservices();
          if(isStock()) {
           if (ip.querypanier(idUser)instanceof Panier){
               Panier panier= ip.querypanier(idUser);
               
//               if (il.queryByIdProd(idPro)instanceof LignePanier) {
//                 
//                  
//                 il.updateLignePanier(il.queryByIdProd(idPro), true);
//                 
//                   System.out.println(il.calcPanier(idPanier));
////                 float prixtot= il.queryByIdProd(idPro).getPrix_total();
////                 int quant = il.queryByIdProd(idPro).getQuantité();
////                 float prix = prixtot*quant;
////                 Panier finpp = new Panier();
////                 finpp.setPrix(prix);
//                
////                 ip.updateprixpanier(finpp);
                 Preferences userPreferences = Preferences.userRoot();
               userPreferences.put("id_panier", String.valueOf(panier.getId()));
//               }else{
                  // create ligne  panier 
                 LignePanier newLignePanier = new LignePanier(panier.getId(), Prod.getId(), 1, Prod.getPrix());
                    il.AjoutLignePanier(newLignePanier);
         
                    panier.setPrix( ip.querypanier(idUser).getPrix()+Prod.getPrix());
                   ip.updateprixpanier(panier);
              
               
               Notification.notificationSuccess("Produit ajouté avec succés", "Merci");

               System.out.println("produit ajouté au panier");
           }else{
               Panier p = new Panier(Prod.getPrix(), idUser);
               
               ip.addPanier(p);
               int idPanier= ip.querypanier(idUser).getId();
               Preferences userPreferences = Preferences.userRoot();
               userPreferences.put("id_panier", String.valueOf(idPanier));
               System.out.println(ip.querypanier(idUser));
             
                  // create ligne  panier 
                 LignePanier newLignePanier = new LignePanier(idPanier, Prod.getId(), 1, Prod.getPrix());
                   System.out.println(newLignePanier);
                 il.AjoutLignePanier(newLignePanier);
               
           }
    }else 
             Notification.notificationError("ERREUR", "Le produit est hors stock");
    }
    private boolean isStock() {
     if (Prod.getQuantite()<chosenproduct.getQuantite()) {
         return false;
    }else
         return true;
        
    
    
    }   
}
