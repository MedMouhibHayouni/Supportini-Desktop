package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Produit {
 
    private Integer id, quantite,categorieId;
    private String  Description;
        private String nomproduit;
    private float prix;
    
 
  private String imageProduit ;
    public Produit() {
    }

    public Produit(Integer quantite, String Description, String nomproduit, float prix, Integer categorieId,String imageProduit) {
        this.quantite = quantite;
        this.Description = Description;
        this.nomproduit = nomproduit;
        this.prix = prix;
       this.categorieId=categorieId;
        this.imageProduit=imageProduit;
    }

  

    public Produit(Integer id, Integer quantite, String Description, String nomproduit, float prix,Integer categorieId, String imageProduit) {
        this.id = id;
        this.quantite = quantite;
        this.Description = Description;
        this.nomproduit = nomproduit;
        this.prix = prix;
         this.categorieId=categorieId;
        this.imageProduit=imageProduit;
    }

  

  
 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNom_produit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Integer getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Integer categorieId) {
        this.categorieId = categorieId;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", quantite=" + quantite + ", categorieId=" + categorieId + ", Description=" + Description + ", nomproduit=" + nomproduit + ", prix=" + prix + ", imageProduit=" + imageProduit + '}';
    }

   

    

  
   

   
   }
