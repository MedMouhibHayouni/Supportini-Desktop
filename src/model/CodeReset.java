/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Asus
 */
public class CodeReset {
    private int  id, idUser ;
    private String  code;
    private Timestamp dateTime, dateExp;

    public CodeReset() {
    }

    public CodeReset(int idUser, String code, Timestamp dateExp) {
        this.idUser = idUser;
        this.code = code;
        this.dateExp = dateExp;
    }

    public CodeReset(int id, int idUser, String code, Timestamp dateTime, Timestamp dateExp) {
        this.id = id;
        this.idUser = idUser;
        this.code = code;
        this.dateTime = dateTime;
        this.dateExp = dateExp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp getDateExp() {
        return dateExp;
    }

    public void setDateExp(Timestamp dateExp) {
        this.dateExp = dateExp;
    }

    @Override
    public String toString() {
        return "CodeReset{" + "id=" + id + ", idUser=" + idUser + ", code=" + code + ", dateTime=" + dateTime + ", dateExp=" + dateExp + '}';
    }
    
    
}
