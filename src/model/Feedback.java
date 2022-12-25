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
public class Feedback {
    private int id;
    private String feedback;
    private int id_suivi;

    public Feedback(String feedback, int id_suivi) {
        this.feedback = feedback;
        this.id_suivi = id_suivi;
    }

    public Feedback() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getId_suivi() {
        return id_suivi;
    }

    public void setId_suivi(int id_suivi) {
        this.id_suivi = id_suivi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_suivi;
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
        final Feedback other = (Feedback) obj;
        if (this.id_suivi != other.id_suivi) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedback=" + feedback + '}';
    }
    
    
    
    
}
