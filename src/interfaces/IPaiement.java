/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.paiement;

/**
 *
 * @author Anis-PC
 */
public interface IPaiement {
    public void AddPaiement(paiement p);
    public List<paiement>AfficherPaiement() ;
    public void DeletePaiement(paiement p);
    public void ModifyPaiement(paiement p);
    public paiement getPaiementById(int id);
    
    
    
}
    

