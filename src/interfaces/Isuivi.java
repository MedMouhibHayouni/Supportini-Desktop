/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import model.Suivi;
import model.Utilisateur;

/**
 *
 * @author GIGABYTE
 */
public interface Isuivi {
    
    public void ajouterSuivi(Suivi s);
    public void supprimerSuivi(Suivi s);
    public void modifierSuivi(Suivi s);
    public Suivi afficherEntrainerList();
    public Set<Suivi> afficherEntrainer(int id);
    public List<Suivi> afficherselondate();
    public void afficherChart(String nom);
    public Suivi queryById(int id);
    public Set<Suivi> queryByidCoach(int id) throws URISyntaxException;
    
    
    
}
