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
public class Coachings {
     private int id;
    private int idcoach;
    private String titre;
    private String discipline;

    private String description;

    private String planing;

    private String prix;
    private int nbmax;
     private int nbinscri;
     private String image ; 

    public Coachings() {
    }

    public Coachings(int id, int idcoach, String titre, String discipline, String description, String planing, String prix, int nbmax, int nbinscri, String image) {
        this.id = id;
        this.idcoach = idcoach;
        this.titre = titre;
        this.discipline = discipline;
        this.description = description;
        this.planing = planing;
        this.prix = prix;
        this.nbmax = nbmax;
        this.nbinscri = nbinscri;
        this.image = image;
    }

    public Coachings(int idcoach, String titre, String discipline, String description, String planing, String prix, int nbmax, int nbinscri, String image) {
        this.idcoach = idcoach;
        this.titre = titre;
        this.discipline = discipline;
        this.description = description;
        this.planing = planing;
        this.prix = prix;
        this.nbmax = nbmax;
        this.nbinscri = nbinscri;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(int idcoach) {
        this.idcoach = idcoach;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaning() {
        return planing;
    }

    public void setPlaning(String planing) {
        this.planing = planing;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getNbmax() {
        return nbmax;
    }

    public void setNbmax(int nbmax) {
        this.nbmax = nbmax;
    }

    public int getNbinscri() {
        return nbinscri;
    }

    public void setNbinscri(int nbinscri) {
        this.nbinscri = nbinscri;
    }

    @Override
    public String toString() {
        return "Coachings{" + "id=" + id + ", idcoach=" + idcoach + ", titre=" + titre + ", discipline=" + discipline + ", description=" + description + ", planing=" + planing + ", prix=" + prix + ", nbmax=" + nbmax + ", nbinscri=" + nbinscri + ", image=" + image + '}';
    }
     
     
     
     
     
     
     
}
