/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import model.Produit;
import javafx.collections.ObservableList;

/**
 *
 * @author Anis-PC
 */
public interface IProduits {
     void insertproduit(Produit st);

  

    ObservableList<Produit> DisplayAllproduit();
}

    

