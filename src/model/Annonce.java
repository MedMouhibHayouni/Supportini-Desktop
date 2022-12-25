package model;

import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author Elife-Kef-114
 */
public class Annonce {

    private int id;
    private String titre;
    private String discipline;
    private int prix;
    private String type;
    private String rue;
    private String codePostal;
    private String ville;
    private String description;
    private String image;
    private int capacite;
    private Date dateDebut;
    private Date dateFin;
    private int fk_idCoach;

    public Annonce() {
    }

    ////annonce sans date

    public Annonce(int id, String titre, String discipline, int prix, String type, String rue, String codePostal, String ville, String description, String image, int capacite, int fk_idCoach) {
        this.id = id;
        this.titre = titre;
        this.discipline = discipline;
        this.prix = prix;
        this.type = type;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.description = description;
        this.image = image;
        this.capacite = capacite;
        this.fk_idCoach = fk_idCoach;
    }
    
    
    public Annonce(int id, String titre, String discipline, int prix, String type, String rue, String codePostal, String ville, String description, String image, int capacite, Date dateDebut, Date dateFin, int fk_idCoach) {
        this.id = id;
        this.titre = titre;
        this.discipline = discipline;
        this.prix = prix;
        this.type = type;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.description = description;
        this.image = image;
        this.capacite = capacite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.fk_idCoach = fk_idCoach;
    }

    public Annonce(String titre, String discipline, int prix, String type, String rue, String codePostal, String ville, String description, String image, int capacite, Date dateDebut, Date dateFin, int fk_idCoach) {
        this.titre = titre;
        this.discipline = discipline;
        this.prix = prix;
        this.type = type;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.description = description;
        this.image = image;
        this.capacite = capacite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.fk_idCoach = fk_idCoach;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDiscipline() {
        return discipline;
    }

    public int getPrix() {
        return prix;
    }

    public String getType() {
        return type;
    }

    public String getRue() {
        return rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getCapacite() {
        return capacite;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public int getFk_idCoach() {
        return fk_idCoach;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setFk_idCoach(int fk_idCoach) {
        this.fk_idCoach = fk_idCoach;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", titre=" + titre + ", discipline=" + discipline + ", prix=" + prix + ", type=" + type + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", description=" + description + ", image=" + image + ", capacite=" + capacite + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", fk_idCoach=" + fk_idCoach + '}';
    }


    
    
    

}
