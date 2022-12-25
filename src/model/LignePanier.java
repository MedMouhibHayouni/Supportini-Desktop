/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Anis-PC
 */
public class LignePanier {

     private int Id_Lpanier;
     private int Id_Panier;
     private int  id_Produit;
     private int quantité;
     private float prix_total;
   

    public LignePanier() {
      
       
    }

    public LignePanier(int Id_Panier, int id_Produit, int quantité, float prix_total) {
        this.Id_Panier = Id_Panier;
        this.id_Produit = id_Produit;
        this.quantité = quantité;
        this.prix_total = prix_total;
       
    }

    public LignePanier(int Id_Lpanier, int Id_Panier, int id_Produit, int quantité, float prix_total) {
        this.Id_Lpanier = Id_Lpanier;
        this.Id_Panier = Id_Panier;
        this.id_Produit = id_Produit;
        this.quantité = quantité;
        this.prix_total = prix_total;
      
    }

    

    public int getId_Lpanier() {
        return Id_Lpanier;
    }

    public void setId_Lpanier(int Id_Lpanier) {
        this.Id_Lpanier = Id_Lpanier;
    }

    public int getId_Panier() {
        return Id_Panier;
    }

    public void setId_Panier(int Id_Panier) {
        this.Id_Panier = Id_Panier;
    }

    public int getId_Produit() {
        return id_Produit;
    }

    public void setId_Produit(int id_Produit) {
        this.id_Produit = id_Produit;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

  

    @Override
    public String toString() {
        return "LignePanier{" + "Id_Lpanier=" + Id_Lpanier + ", Id_Panier=" + Id_Panier + ", id_Produit=" + id_Produit + ", quantit\u00e9=" + quantité + ", prix_total=" + prix_total + '}';
    }

}