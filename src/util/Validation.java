/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;
import java.util.Random;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Asus
 */
public class Validation {

    public static boolean patternMatches(String emailAdress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAdress)
                .matches();

    }

    public static boolean validationPasswordLength(TextField inputTextField, Label inputLabel) {
        boolean isValid = true;
        if (inputTextField.getText().length() < 8) {
            inputLabel.setText("mot de passe doit contient au moins 8 caractéres");
            isValid = false;

        }

        return isValid;
    }

    public static boolean validationPasswordCompose(TextField inputTextField, Label inputLabel) {
        boolean isValid = true;
        if (inputTextField.getText().matches("[a-zA-Z_0-9]")) {
            inputLabel.setText("mot de passe doit se composer de lettre, chiffre et symbole");
            isValid = false;

        }

        return isValid;
    }

    public static boolean validationInteger(TextField inputTextField, Label inputLabel) {

        try {
            Integer.parseInt(inputTextField.getText());
            return true;
        } catch (NumberFormatException e) {
            inputLabel.setText(inputTextField.getPromptText() + " doit se composer de chiffre");
            return false;
        }
    }

    public static String hachePassword(String pwd) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(pwd.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
 public static  String randomString(){
            // create a string of all characters
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // create random string builder
    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = 7;

    for(int i = 0; i < length; i++) {

      // generate random index number
      int index = random.nextInt(alphabet.length());

      // get character specified by index
      // from the string
      char randomChar = alphabet.charAt(index);

      // append the character to string builder
      sb.append(randomChar);
    }
    return sb.toString();
   }
}
