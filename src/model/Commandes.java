
package model;

import java.time.LocalDate;

/**
 *
 * @author Anis-PC
 */
public class Commandes {


    private int idCommande;
    private String nom_Produit;
    private float prix;
    private int id_user;
private int id_panier;
    public Commandes() {
    }

    public Commandes(String nom_Produit, float prix) {
        this.nom_Produit = nom_Produit;
        this.prix = prix;
    }

    public Commandes(int idCommande, String nom_Produit, float prix) {
        this.idCommande = idCommande;
        this.nom_Produit = nom_Produit;
        this.prix = prix;
    }

    public Commandes(String nom_Produit, float prix, int id_user) {
        this.nom_Produit = nom_Produit;
        this.prix = prix;
        this.id_user = id_user;
    }

    public Commandes(String nom_Produit, float prix, int id_user, int id_panier) {
        this.nom_Produit = nom_Produit;
        this.prix = prix;
        this.id_user = id_user;
        this.id_panier = id_panier;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getNom_Produit() {
        return nom_Produit;
    }

    public void setNom_Produit(String nom_Produit) {
        this.nom_Produit = nom_Produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    @Override
    public String toString() {
        return "Commandes{" + "idCommande=" + idCommande + ", nom_Produit=" + nom_Produit + ", prix=" + prix + ", id_user=" + id_user + ", id_panier=" + id_panier + '}';
    }

    
}
    