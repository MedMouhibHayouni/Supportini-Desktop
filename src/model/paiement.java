
package model;

/**
 *
 * @author Anis-PC
 */
public class paiement {

    private int id;
    private String mode_paiement;
    private int num_carte,cryptograme;
    private String date_exp;

    public paiement() {
    }

    public paiement(String modepaiement) {
        this.mode_paiement = modepaiement;
    }

    public paiement(int id, String modepaiement, int numcarte, int cryptograme) {
        this.id = id;
        this.mode_paiement = modepaiement;
        this.num_carte = numcarte;
        this.cryptograme = cryptograme;
    }

    public paiement(String modepaiement, int numcarte, int cryptograme) {
        this.mode_paiement = modepaiement;
        this.num_carte = numcarte;
        this.cryptograme = cryptograme;
    }

    public paiement(int numcarte, int cryptograme) {
        this.num_carte = num_carte;
        this.cryptograme = cryptograme;
    }

    public paiement(String selectedRadioValue, int y, int x, String dateConverted) {
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(String modepaiement) {
        this.mode_paiement = modepaiement;
    }

    public int getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(int numcarte) {
        this.num_carte = numcarte;
    }

    public int getCryptograme() {
        return cryptograme;
    }

    public void setCryptograme(int cryptograme) {
        this.cryptograme = cryptograme;
    }

    public String getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(String dateexp) {
        this.date_exp = dateexp;
    }

   

    @Override
    public String toString() {
        return "paiement{" + "id=" + id + ", mode_paiement=" + mode_paiement + ", num_carte=" + num_carte + ", cryptograme=" + cryptograme +"}\n" ;
    }
    
    
    
        
    
    
    
}

    

