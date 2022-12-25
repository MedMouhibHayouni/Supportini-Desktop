/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.histosuivi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Suivi;
import javafx.scene.input.TouchEvent;
import gui.suivi.histosuivi.MyListener_Suivi;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class Suivi_ItemController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label date;

   

    private Suivi suivi;
    private MyListener_Suivi myListener;


 @FXML
    private void showHisto(MouseEvent event) {        
        myListener.onClickListener(suivi);
                

    }

    public void setData(Suivi suivi, MyListener_Suivi myListener) {
        this.suivi = suivi;
        this.myListener = myListener;
        date.setText(String.valueOf(suivi.getDateSuivi()));
//        String path = fruit.getImgSrc();
//        Image aa = new Image("file:" + path);
//
//        img.setImage(aa);
    }

   

}
