/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Elife-Kef-114
 */
public class Recette {
    private int idRecette,idRegime;
    private String ingredien,prepatation,image,nomrecette;

   

    public Recette() {
    }

    public Recette(int idRecette, int idRegime, String ingredien, String prepatation, String image, String nomrecette) {
        this.idRecette = idRecette;
        this.idRegime = idRegime;
        this.ingredien = ingredien;
        this.prepatation = prepatation;
        this.image = image;
        this.nomrecette = nomrecette;
    }

    public Recette(int idRegime, String ingredien, String prepatation, String image, String nomrecette) {
        this.idRegime = idRegime;
        this.ingredien = ingredien;
        this.prepatation = prepatation;
        this.image = image;
        this.nomrecette = nomrecette;
    }

    public String getNomrecette() {
        return nomrecette;
    }

    public void setNomrecette(String nomrecette) {
        this.nomrecette = nomrecette;
    }

   

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public int getIdRegime() {
        return idRegime;
    }

    public void setIdRegime(int idRegime) {
        this.idRegime = idRegime;
    }

    public String getIngredien() {
        return ingredien;
    }

    public void setIngredien(String ingredien) {
        this.ingredien = ingredien;
    }

    public String getPrepatation() {
        return prepatation;
    }

    public void setPrepatation(String prepatation) {
        this.prepatation = prepatation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
    
}
