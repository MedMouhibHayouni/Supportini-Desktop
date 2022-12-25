package model;

public class MaterielSalle {
   private int id,quantite,fk_idSalle;
    private String nomMaterial ,specialite,description;
    private String imageVitrine;
//    private Integer price;
//    private String color;

    public MaterielSalle() {
    }

    public MaterielSalle(int quantite, int fk_idSalle, String nomMaterial, String specialite, String description, String imageVitrine) {
        this.quantite = quantite;
        this.fk_idSalle = fk_idSalle;
        this.nomMaterial = nomMaterial;
        this.specialite = specialite;
        this.description = description;
        this.imageVitrine = imageVitrine;
    }

    public MaterielSalle(int id, int quantite, int fk_idSalle, String nomMaterial, String specialite, String description, String imageVitrine) {
        this.id = id;
        this.quantite = quantite;
        this.fk_idSalle = fk_idSalle;
        this.nomMaterial = nomMaterial;
        this.specialite = specialite;
        this.description = description;
        this.imageVitrine = imageVitrine;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getFk_idSalle() {
        return fk_idSalle;
    }

    public void setFk_idSalle(int fk_idSalle) {
        this.fk_idSalle = fk_idSalle;
    }

    public String getNomMaterial() {
        return nomMaterial;
    }

    public void setNomMaterial(String nomMaterial) {
        this.nomMaterial = nomMaterial;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Integer getPrice() {
//        return price;
//    }
//
//    public void setPrice(Integer price) {
//        this.price = price;
//    }

//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }

    public String getImageVitrine() {
        return imageVitrine;
    }

    public void setImageVitrine(String imageVitrine) {
        this.imageVitrine = imageVitrine;
    }
}
