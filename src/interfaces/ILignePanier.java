/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.LignePanier;
import model.Panier;

/**
 *
 * @author Anis-PC
 */
public interface ILignePanier {

    public void AjoutLignePanier(LignePanier lp);

    public LignePanier queryByIdProd(int id);

    public void updateLignePanier(LignePanier lp, boolean type);

    public void deleteLignePanier(int id);

    public int calcPanier(int idLPanier,int idPro);

}
