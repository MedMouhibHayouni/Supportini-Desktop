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
 * @author Elife-Kef-113
 */
public interface Imateriel {
           public void ajouterMaterielSalle(MaterielSalle m);
         public List<MaterielSalle> affichageById(int fk_idSalle);
              public void supprimerMateriel( MaterielSalle m);


}
