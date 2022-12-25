/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Asus
 */
public class Coach {
    private int id ,idUser;
    private String specialite;
     public String nom, prenom,email, phone, image;
    public int idRole;

    public Coach() {
    }

    public Coach(int idUser,  String specialite) {
        this.idUser = idUser;
       
        this.specialite = specialite;
    }

    public Coach(int id, int idUser,  String specialite) {
        this.id = id;
        this.idUser = idUser;
      
        this.specialite = specialite;
    }

    public Coach(int id, String specialite, String nom, String prenom, String email, String phone, int idRole, String image) {
        this.id = id;
        this.specialite = specialite;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.idRole = idRole;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", idUser=" + idUser + ", specialite=" + specialite + '}';
    }
    
}
