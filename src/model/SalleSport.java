/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HSOUNA
 */
public class SalleSport {
    
    private int id , numTel, fk_idUser;
    private String nomSalle, ville , rue ,codePostal , description, duration, imageVitrine;
    private float prix;

    public SalleSport() {
    }

    public SalleSport(int numTel, int fk_idUser, String nomSalle, String ville, String rue, String codePostal, String description, String duration, String imageVitrine, float prix) {
        this.numTel = numTel;
        this.fk_idUser = fk_idUser;
        this.nomSalle = nomSalle;
        this.ville = ville;
        this.rue = rue;
        this.codePostal = codePostal;
        this.description = description;
        this.duration = duration;
        this.imageVitrine = imageVitrine;
        this.prix = prix;
    }

    public SalleSport(int id, int numTel, int fk_idUser, String nomSalle, String ville, String rue, String codePostal, String description, String duration, String imageVitrine, float prix) {
        this.id = id;
        this.numTel = numTel;
        this.fk_idUser = fk_idUser;
        this.nomSalle = nomSalle;
        this.ville = ville;
        this.rue = rue;
        this.codePostal = codePostal;
        this.description = description;
        this.duration = duration;
        this.imageVitrine = imageVitrine;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public int getFk_idUser() {
        return fk_idUser;
    }

    public void setFk_idUser(int fk_idUser) {
        this.fk_idUser = fk_idUser;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageVitrine() {
        return imageVitrine;
    }

    public void setImageVitrine(String imageVitrine) {
        this.imageVitrine = imageVitrine;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "SalleSport{" + "id=" + id + ", numTel=" + numTel + ", fk_idUser=" + fk_idUser + ", nomSalle=" + nomSalle + ", ville=" + ville + ", rue=" + rue + ", codePostal=" + codePostal + ", description=" + description + ", duration=" + duration + ", imageVitrine=" + imageVitrine + ", prix=" + prix + '}';
    }
    
    
    
    
}
