/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elife-Kef-100
 */
public class Salle {
   private int id,mobile;
   private String nom,materiel;

    public Salle() {
    }

    public Salle(int mobile, String nom, String materiel) {
        this.mobile = mobile;
        this.nom = nom;
        this.materiel = materiel;
    }

    public Salle(int id, int mobile, String nom, String materiel) {
        this.id = id;
        this.mobile = mobile;
        this.nom = nom;
        this.materiel = materiel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMobile() {
        return mobile;
    }
    public void setMobile(int mobile) {
        this.mobile = mobile;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getMateriel() {
        return materiel;
    }
    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", mobile=" + mobile + ", nom=" + nom + ", materiel=" + materiel + '}';
    }
   
   
   
}
