/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Entrainee;

/**
 *
 * @author Asus
 */
public interface IEntrainee {
    public void addEntrainee (Entrainee e);
   public  Entrainee queryById (int id);
   public Entrainee selectProfil (int id);
}
