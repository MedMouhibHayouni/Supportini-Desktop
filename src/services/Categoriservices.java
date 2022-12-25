/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import interfaces.IProduits;
import interfaces.ICategories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categorie;
import util.MaConnexion;

/**
 *
 * @author Anis-PC
 */
public class Categoriservices implements ICategories{
   


  Connection cnx = MaConnexion.getInstance().getCnx();
  @Override
    public void insertcat(Categorie st) {
        String requete = "insert into categorie (nom) values (?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, st.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categoriservices .class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
   

   
   private static ICategories daoCategories;

    public static ICategories getInstance() {
        if (daoCategories == null) {
           daoCategories = new Categoriservices();
        }
        return daoCategories;
    }
@Override
    public Categorie findcatBynom(String nom) {
      Categorie cat = new Categorie();
        String requete = "select * from categorie where nom=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                cat.setId(resultat.getInt(1));
                cat.setName(resultat.getString(2));
            }
            return cat;

        } catch (SQLException ex) {
           Logger.getLogger(Categoriservices .class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du Categories " + ex.getMessage());
            return null;
        }
    }
@Override
    public Categorie findcatById(int id) {
          Categorie cat = new Categorie();
        String requete = "select * from categorie where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                cat.setId(resultat.getInt(1));
                cat.setName(resultat.getString(2));
            }
            return cat;

        } catch (SQLException ex) {
           Logger.getLogger(Categoriservices .class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du Categories " + ex.getMessage());
            return null;
        }
    }
}


    

