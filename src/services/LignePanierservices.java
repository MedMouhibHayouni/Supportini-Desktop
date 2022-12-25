
package services;

import interfaces.ILignePanier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LignePanier;
import util.MaConnexion;

/**
 *
 * @author Anis-PC
 */
public class LignePanierservices implements ILignePanier {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public LignePanier queryByIdProd(int id) {
        String requete = "Select * From ligne_panier where id_Produit=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rest = ps.executeQuery();
            rest.first();
            LignePanier lp = new LignePanier(rest.getInt(1), rest.getInt(2), rest.getInt(3), rest.getInt(4),rest.getFloat(5));
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(LignePanierservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void AjoutLignePanier(LignePanier lp) {
        String req = "INSERT INTO  ligne_panier(id_Panier,id_Produit,quantité,prix_total) VALUES (?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, lp.getId_Panier());
            ps.setInt(2, lp.getId_Produit());
            ps.setInt(3, lp.getQuantité());
            ps.setFloat(4, lp.getPrix_total());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(LignePanierservices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updateLignePanier(LignePanier lp, boolean type) {
        String req = " UPDATE ligne_panier SET quantité = ? WHERE id_Produit=?";
        if (type) {
            PreparedStatement ps;
            try {
                ps = cnx.prepareStatement(req);
                ps.setInt(1, lp.getQuantité() + 1);
                ps.setInt(2, lp.getId_Produit());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(LignePanierservices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            PreparedStatement ps;
            try {
                ps = cnx.prepareStatement(req);
                ps.setInt(1, lp.getQuantité() - 1);
                ps.setInt(2, lp.getId_Produit());
                ps.executeUpdate();
                if (lp.getQuantité() - 1 == 0) {
                    deleteLignePanier(lp.getId_Lpanier());
                }
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(LignePanierservices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteLignePanier(int id) {
        String req = "DELETE FROM ligne_Panier WHERE id_Lpanier";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(LignePanierservices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int calcPanier(int idPanier,int idPro) {
     
        
        String req = "Select COUNT(*) from ligne_Panier where id_Panier=? AND  id_Produit=?";
        PreparedStatement ps;
        
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idPanier);
            ps.setInt(2, idPro);
            ResultSet res = ps.executeQuery();
            res.first();
            return res.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

}
