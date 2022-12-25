/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author GIGABYTE
 */
public class Demande_Suivi {
   private int id;
   private String demande;
   private int id_entr;
   private int id_coach;

    public Demande_Suivi() {
    }

    public Demande_Suivi(String demande, int id_entr, int id_coach) {
        this.demande = demande;
        this.id_entr = id_entr;
        this.id_coach = id_coach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDemande() {
        return demande;
    }

    public void setDemande(String demande) {
        this.demande = demande;
    }

    public int getId_entr() {
        return id_entr;
    }

    public void setId_entr(int id_entr) {
        this.id_entr = id_entr;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Demande_Suivi other = (Demande_Suivi) obj;
        if (this.id_entr != other.id_entr) {
            return false;
        }
        if (this.id_coach != other.id_coach) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Demande_Suivi{" + "demande=" + demande + '}';
    }
   
   


}
