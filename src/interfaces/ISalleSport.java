/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.MaterielSalle;
import model.SalleSport;

/**
 *
 * @author HSOUNA
 */
public interface ISalleSport {
    
   public List<SalleSport> affichage ();
   public void ajouterSalleSport(SalleSport s);
   public List<SalleSport> affichageByIdPss(int idUser);
     public void supprimerSalleSport( SalleSport s);
      public void modifierSalleSport( SalleSport s );
 public SalleSport affichageById (int id);
     
   
   
}
