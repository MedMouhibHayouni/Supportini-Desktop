/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Feedback;

/**
 *
 * @author GIGABYTE
 */
public interface Ifeedback {
    public void ajouterfeedback(Feedback feedback);
    public void supprimerFeedback();
    public void modifierFeedback();
    public Feedback afficherfeedback(int id);
    
}
