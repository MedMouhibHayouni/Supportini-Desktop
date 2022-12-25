package model;
import java.util.ArrayList;
import java.util.List;
public class Panier {
    
    private int id;
    private float prix;
    private int id_user;

    public Panier() {
    }

    public Panier(float prix, int id_user) {
        this.prix = prix;
        this.id_user = id_user;
    }

    public Panier(int id, float prix, int id_user) {
        this.id = id;
        this.prix = prix;
        this.id_user = id_user;
    }
   
  
 public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


   
 
   


    

   
    
    
}
