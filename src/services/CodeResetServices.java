/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICodeReset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CodeReset;
import util.MaConnexion;

/**
 *
 * @author Asus
 */
public class CodeResetServices implements ICodeReset {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void addCode(CodeReset code) {
        String req = "INSERT INTO `reset_passwords`( `code`, `fk_idUser`, `date_exp`) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, code.getCode());
            ps.setInt(2, code.getIdUser());
            ps.setTimestamp(3, code.getDateExp());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CodeResetServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public CodeReset queryCode(String code) {
        String req = "SELECT * FROM reset_passwords WHERE code=?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, code);
            ResultSet res = ps.executeQuery();
            res.first();
            CodeReset c = new CodeReset();
            c.setId(res.getInt(1));
            c.setCode(res.getString(2));
            c.setDateExp(res.getTimestamp(4));
            c.setDateTime(res.getTimestamp(3));
            c.setIdUser(res.getInt(5));
            ps.close();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(CodeResetServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deleteCode(int id) {
       String req = "DELETE FROM reset_passwords WHERE id=?";
       PreparedStatement ps ; 
        try {
            ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("deleted code");
        } catch (SQLException ex) {
            Logger.getLogger(CodeResetServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
