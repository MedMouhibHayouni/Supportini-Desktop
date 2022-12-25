/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.GalerieImage;

/**
 *
 * @author Asus
 */
public interface IGalerie {
    public void addImage (GalerieImage gi);
    public List<GalerieImage> selectImageById(int idUser);
    public void deleteImg(int id);
}
