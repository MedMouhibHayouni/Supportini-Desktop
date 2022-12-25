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
public class Regime {
    private int idregime ;

    private int nbkg ;

    private String type ;
    private String nom ;
    private String petitdej ;
      private String dej ;
        private String dinner ;

    public Regime() {
    }


    public Regime( int nbkg, String type, String nom, String petitdej, String dej, String dinner) {
        this.nbkg = nbkg;
        this.type = type;
        this.nom = nom;
        this.petitdej = petitdej;
        this.dej = dej;
        this.dinner = dinner;
    }

   

    

    public int getIdregime() {
        return idregime;
    }

    public void setIdregime(int idregime) {
        this.idregime = idregime;
    }

   

   

    public float getNbkg() {
        return nbkg;
    }


    public void setNbkg(int nbkg) {

        this.nbkg = nbkg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPetitdej() {
        return petitdej;
    }

    public void setPetitdej(String petitdej) {
        this.petitdej = petitdej;
    }

    public String getDej() {
        return dej;
    }

    public void setDej(String dej) {
        this.dej = dej;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    @Override
    public String toString() {
        return "Regime{" + "idregime=" + idregime +  ", nbkg=" + nbkg + ", type=" + type + ", nom=" + nom + ", petitdej=" + petitdej + ", dej=" + dej + ", dinner=" + dinner + '}';
    }

    
    

    
}
