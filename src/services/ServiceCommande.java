package services;

import interfaces.ICommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Commandes;
import model.LignePanier;
import util.MaConnexion;

/**
 *
 * @author Elife-Kef-010
 */
public class ServiceCommande implements ICommande {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterCommande(Commandes C) {
        String req = "INSERT INTO `commandes`(`nom_Produit`, `prix`,'id_user', 'id_Panier' ) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, C.getNom_Produit());
            ps.setFloat(2, C.getPrix());
            ps.setInt(3, C.getId_user());
            ps.setInt(4, C.getId_panier());
            ps.executeUpdate();
            System.out.println("PS : commande ajoutée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Commandes> afficherCommande() {
        List<Commandes> commandes = new ArrayList<>();
        String req = "SELECT * FROM commandes";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Commandes C = new Commandes();
                C.setIdCommande(rs.getInt("idCommandes"));
                C.setNom_Produit(rs.getString(2));
                C.setPrix(rs.getInt("prix"));
                C.setId_user(rs.getInt("id_user"));
                C.setId_panier(rs.getInt("id_Panier"));
                commandes.add(C);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commandes;
    }

    @Override
    public void supprimerCommande(Commandes C) {
        try {
            String req = "DELETE FROM commandes WHERE id_Commande= = '" + C.getIdCommande() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande supprimer avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Set<String> commender(int idPanier) {
        String xx = "SELECT p.nom_Produit FROM  produits p JOIN  ligne_panier l WHERE l.id_Panier=? AND p.id=l.id_Produit";
        Set<String> hashcom = new HashSet<>();
   

        try {
            PreparedStatement ps = cnx.prepareStatement(xx);
            ps.setInt(1, idPanier);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
                hashcom.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashcom;
    }
}
