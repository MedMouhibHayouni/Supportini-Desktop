/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Coach;

/**
 *
 * @author Asus
 */
public interface ICoach {
    public void addCoach (Coach c);
    public Coach queryById (int id);
    public Coach selectProfil (int id);
}
