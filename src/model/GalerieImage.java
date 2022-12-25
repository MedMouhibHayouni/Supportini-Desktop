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
public class GalerieImage {
    private  int id , idUser;
    private String imageName;

    public GalerieImage() {
    }

    public GalerieImage(int idUser, String imageName) {
        this.idUser = idUser;
        this.imageName = imageName;
    }

    public GalerieImage(int id, int idUser, String imageName) {
        this.id = id;
        this.idUser = idUser;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "GalerieImage{" + "id=" + id + ", idUser=" + idUser + ", imageName=" + imageName + '}';
    }
    
}
