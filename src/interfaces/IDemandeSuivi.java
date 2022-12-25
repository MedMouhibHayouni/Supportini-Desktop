/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Demande_Suivi;

/**
 *
 * @author GIGABYTE
 */
public interface IDemandeSuivi {
    
    public void AjouterDemanderSuivi(Demande_Suivi demande);
    public Demande_Suivi afficherDemandeSuivi(int id);
    
}
