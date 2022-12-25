package interfaces;


import java.util.List;
import java.util.Set;
import javafx.collections.ObservableList;
import model.Commandes;
import model.LignePanier;

/**
 *
 * @author Elife-Kef-010
 */
public interface ICommande {
     public void ajouterCommande(Commandes C);
     public List<Commandes> afficherCommande();
     public void supprimerCommande(Commandes C);
     public Set<String> commender(int idPanier);
     
     
     
     
}

