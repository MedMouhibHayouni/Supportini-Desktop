/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.collections.ObservableList;
import model.Regime;

/**
 *
 * @author Elife-Kef-114
 */
public interface IRegime {
     ObservableList<Regime>  table ();
     public void ajouterRegime(Regime r);
}
