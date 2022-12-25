
package services;
import model.Panier;
import util.MaConnexion;
import interfaces.IPanier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Panierservices  implements  IPanier{
Connection cnx=MaConnexion.getInstance().getCnx();


    @Override
    public Panier querypanier(int idUser) {
      String req= "Select * from Panier where id_User=?" ;
      PreparedStatement ps;
    try {
        ps=cnx.prepareStatement(req);
        ps.setInt(1, idUser);
        ResultSet res = ps.executeQuery();
        res.first();
        Panier p = new Panier();
        p.setId(res.getInt(1));
        
        p.setPrix(res.getFloat(2));
        p.setId_user(res.getInt(3));
        return p;
    } catch (SQLException ex) {
        Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }

    @Override
    public void addPanier(Panier p) {
       String req = "INSERT INTO `panier`(`prix`, `id_user`) VALUES (?,?)";
       PreparedStatement ps ; 
    try {
        ps=cnx.prepareStatement(req);
        ps.setFloat(1, p.getPrix());
        ps.setInt(2, p.getId_user());
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
    }
    }   
    
    
    
    
    
    
    
    
    

    @Override
    public List<Panier> getAllPanier() {
        List<Panier> panier = new ArrayList<>();
        String req = "SELECT * FROM panier";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Panier p = new Panier();
                p.setId(rs.getInt("id"));
                p.setPrix(rs.getInt(2));
                p.setId_user(rs.getInt(3));
               
                panier.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return panier
;    }
    

    @Override
    public Panier updateprixpanier(Panier p) {
         String req= "UPDATE `panier` SET prix=? WHERE id=?" ;
      PreparedStatement ps;
    
             try {
                 ps=cnx.prepareStatement(req);
               
                ps.setFloat(1,p.getPrix());
                ps.setInt(2,p.getId());
                ps.executeUpdate();
                ps.close();
                
             } catch (SQLException ex) {
        Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
        
    }

    @Override
    public void deletepanier(Panier p) {
        try {
            String req = "DELETE FROM panier WHERE id = '"+ p.getId()+"'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Panier supprimer avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
    }
       
    }

    
        
        
    
    
    
    


    

