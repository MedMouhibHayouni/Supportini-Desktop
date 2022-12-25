/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elife-Kef-130
 */
public class Utilisateur {

    private int id, idRole ,status;
    private String nom, prenom, password, email, cin, phone,imageName;
    //constructeur

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String password, String email, String cin, String phone) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin = cin;
        this.phone = phone;
    }

    public Utilisateur(int id, String nom, String prenom, String password, String email, String cin, String phone, int idRole,String image, int status) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin = cin;
        this.idRole = idRole;
        this.phone = phone;
        this.imageName=image;
        this.status=status;

    }
     
    //getters & setters

    

   
   
   

   

    public int getStatus() {
        return status;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

   

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getId() {
        return id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
       

        return "Utilisateur{" + "id=" + id + ", idRole=" + idRole + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + ", cin=" + cin + ", phone=" + phone + ", imageName=" + imageName + ", status=" + status + '}';

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   

}
