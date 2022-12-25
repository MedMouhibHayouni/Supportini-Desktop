/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Panier;

/**
 *
 * @author Anis-PC
 */
public interface IPanier {
    public void addPanier (Panier p );
    public List<Panier>getAllPanier();
    public Panier querypanier(int idUser);
    public Panier updateprixpanier(Panier p);
    public void deletepanier(Panier p);
}

