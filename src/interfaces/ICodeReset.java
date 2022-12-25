/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.CodeReset;

/**
 *
 * @author Asus
 */
public interface ICodeReset {

    public void addCode(CodeReset code);

    public CodeReset queryCode(String code);

    public void deleteCode(int id);
}
