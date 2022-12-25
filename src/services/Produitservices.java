
package services;

import interfaces.ICategories;
import interfaces.IProduits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Categorie;
import model.Produit;
import util.MaConnexion;

/**
 *
 * @author Anis-PC
 */
public class Produitservices implements IProduits{
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void insertproduit(Produit st) {
         String requete = "INSERT INTO `produit` (`nomproduit`, `prix`, `quantite`, `imageProduit`,`description` ) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, st.getNomproduit());
            ps.setFloat(2 , st.getPrix());
            ps.setInt(3, st.getQuantite());
            ps.setString(4, st.getImageProduit());
            ps.setString(5, st.getDescription());
//            ps.setInt(6, st.getCategorieId());
           
           
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(Produitservices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }
 
    @Override
    public ObservableList<Produit> DisplayAllproduit() {
        
        ObservableList<Produit> listedepots = FXCollections.observableArrayList();
        String requete = "select * from produit";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            ICategories deptdao =Categoriservices.getInstance();

            while (resultat.next()) {
                Produit pr = new Produit();
                pr.setId(resultat.getInt(1));
                pr.setNom_produit(resultat.getString(2));
              
                pr.setPrix(resultat.getInt(3));
            pr.setQuantite(resultat.getInt(4));
             pr.setImageProduit(resultat.getString(5));
//     pr.setCategorieId(deptdao.findcatById(resultat.getInt(5)));
                
                    pr.setDescription(resultat.getString("description"));
               

                listedepots.add(pr);
            }
            return listedepots;
        } catch (SQLException ex) {
           Logger.getLogger(Produitservices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }
   
        }
    
    






