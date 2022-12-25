/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Utilisateur;
import org.json.JSONObject;

/**
 *
 * @author Elife-Kef-130
 */
public interface IUtilisateur {

    public void addUser(Utilisateur user);

    public List displayUser();

    public Utilisateur queryUserById(int id);

    public List queryUserByRoleId(int idRole);

    public void updateUser(Utilisateur user);

    public void deleteUser(Utilisateur user);

    public Utilisateur queryByCin(String cin);

    public void updateRoleUser(int id, int role);

    public void uploadUserImg(String path, int id);

    public void banUser(int id, int status);

    public void updateUserByAdmin(int id, String nom, String prenom, String email, String cin, String phone, int role);

    public  int checkUser (String email);
    
    public void resetPwd(int id , String pwd);
}
