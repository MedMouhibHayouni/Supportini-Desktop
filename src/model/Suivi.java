/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Objects;
import static javafx.util.Duration.millis;

/**
 *
 * @author GIGABYTE
 */
public class Suivi {
    
        
    private int id;
    private int fk_id_entr;
    private int age;
    private int taille;
    private int poidsActuelle;
    private Date dateSuivi; 
    private String nomE;
    private String prenomE;
    private double imc;
    private int id_coach;
    public Suivi() {
    }

//    public Suivi(int age, int taille, int poidsActuelle, Date dateSuivi, String nomE, String prenomE) {
//        this.age = age;
//        this.taille = taille;
//        this.poidsActuelle = poidsActuelle;
//        this.dateSuivi = dateSuivi;
//        this.nomE = nomE;
//        this.prenomE = prenomE;
//    }

 

    public Suivi(int fk_id_entr, int age, int taille, int poidsActuelle, Date dateSuivi, String nomE, String prenomE) {
        this.fk_id_entr = fk_id_entr;
        this.age = age;
        this.taille = taille;
        this.poidsActuelle = poidsActuelle;
        this.dateSuivi = dateSuivi;
        this.nomE = nomE;
        this.prenomE = prenomE;
    }

    public Suivi(int fk_id_entr, int age, int taille, int poidsActuelle, Date dateSuivi, String nomE, String prenomE, double imc) {
        this.fk_id_entr = fk_id_entr;
        this.age = age;
        this.taille = taille;
        this.poidsActuelle = poidsActuelle;
        this.dateSuivi = dateSuivi;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.imc = imc;
    }

    public Suivi(int fk_id_entr, int age, int taille, int poidsActuelle, Date dateSuivi, String nomE, String prenomE, double imc, int id_coach) {
        this.fk_id_entr = fk_id_entr;
        this.age = age;
        this.taille = taille;
        this.poidsActuelle = poidsActuelle;
        this.dateSuivi = dateSuivi;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.imc = imc;
        this.id_coach = id_coach;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoidsActuelle() {
        return poidsActuelle;
    }

    public void setPoidsActuelle(int poidsActuelle) {
        this.poidsActuelle = poidsActuelle;
    }

    public Date getDateSuivi() {
        return dateSuivi;
    }

    public void setDateSuivi(Date dateSuivi) {
        this.dateSuivi = dateSuivi;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }

    public int getFk_id_entr() {
        return fk_id_entr;
    }

    public void setFk_id_entr(int fk_id_entr) {
        this.fk_id_entr = fk_id_entr;
    }

    public double getImc() {
        double getTaille = getTaille();
        double tailleimc=((getTaille/100)*(getTaille/100));
        int poids= getPoidsActuelle();
        double imcCalcul = poids/tailleimc;
        imc = Math.round(imcCalcul*1e2)/1e2;
//        DecimalFormat df = new DecimalFormat("0.00");
//        imc = Double.valueOf(df.format());
        
        
        //setImc(imc);
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    @Override   
    public int hashCode() {
        return Objects.hash(fk_id_entr) ;
    }

//    @Override
//    public String toString() {
//        return "Suivi{" + "id=" + id + ", fk_id_entr=" + fk_id_entr + ", age=" + age + ", taille=" + taille + ", poidsActuelle=" + poidsActuelle + ", dateSuivi=" + dateSuivi + ", nomE=" + nomE + ", prenomE=" + prenomE + ", imc=" + imc + ", id_coach=" + id_coach + '}';
//    }

    @Override
    public String toString() {
        return nomE + " " + prenomE ;
    }
    


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Suivi other = (Suivi) obj;
        if (this.fk_id_entr != other.fk_id_entr) {
            return false;
        }
        return true;
    }

    public Suivi(int id, int fk_id_entr, int age, int taille, int poidsActuelle, Date dateSuivi, String nomE, String prenomE, double imc, int id_coach) {
        this.id = id;
        this.fk_id_entr = fk_id_entr;
        this.age = age;
        this.taille = taille;
        this.poidsActuelle = poidsActuelle;
        this.dateSuivi = dateSuivi;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.imc = imc;
        this.id_coach = id_coach;
    }

   

    
    

    
    
    
    
}
