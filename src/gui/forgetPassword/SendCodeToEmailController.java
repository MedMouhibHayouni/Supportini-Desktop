/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forgetPassword;

import interfaces.ICodeReset;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.security.Security;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.CodeReset;
import services.CodeResetServices;
import services.UtilisateurServices;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class SendCodeToEmailController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button btnCode;
    @FXML
    private Button login;
    private int randomCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void checkEmail() {
        IUtilisateur iu = new UtilisateurServices();
        if (iu.checkUser(email.getText()) == -1) {
            Notification.notificationError("ERREUR", "Vérifier votre adresse e-mail");
        } else {
            sendCode(iu.checkUser(email.getText()));
            Notification.notificationSuccess("SUCCEES", "Code est envoyée à votre adresse e-mail");

        }
    }

    private void sendCode(int id) {
        try {
            Random rand = new Random();
            randomCode = rand.nextInt(999999);
            String host = "smtp.gmail.com";
            String user = "chaweli10@gmail.com";
            String pass = "frwbgosplsqunvkt";
            String to = email.getText();
            String subject = "Code de Configuration";
            String message = "<html>\n"
                    + "  <head>\n"
                    + "    <style>\n"
                    + "      #body {\n"
                    + "        text-align:center;\n"
                    + "      }\n"
                    + "      .code{\n "
                    + "         background-image:linear-gradient(45deg, #553c9a, #ee4b2b);\n"
                    + "        color: white;\n"
                    + "        font-size:70px;\n"
                    + "         font-weight: 600;\n"
                    + "         -webkit-background-clip: text;\n"
                    + " text-shadow: 2px 2px 2px rgba(206,89,55,0);"
                    + "       }\n"
                    + "    </style>\n"
                    + "  </head>\n"
                    + "  <body>\n"
                    + "      <p class=\"colored\">\n"
                    + "       Ton Code pour réinitialiser le mot de passe est :\n"
                    + "      </p>\n"
                    + "    <div id=\"body\">\n"
                    + "      <h1 class=\"code\">" + randomCode + "</h1>"
                    + "    </div>\n"
                    + "  </body>\n"
                    + "</html>";
            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "host");
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.starttls.required", "true");
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailsession = Session.getDefaultInstance(props, null);
            mailsession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailsession);

            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setContent(message, "text/html; charset=utf-8");
            Transport transport = mailsession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            saveCode(id);
        } catch (AddressException ex) {
            Logger.getLogger(SendCodeToEmailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SendCodeToEmailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SendCodeToEmailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saveCode(int idUser) throws IOException {
        Timestamp current = new java.sql.Timestamp(new java.util.Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(current.getTime());
        cal.add(Calendar.MINUTE, 10);
        Timestamp exptime = new Timestamp(cal.getTime().getTime());
        CodeReset code = new CodeReset(idUser, String.valueOf(randomCode), exptime);

        ICodeReset ic = new CodeResetServices();
        ic.addCode(code);
        goToReset();
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../login/LoginFXML.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Se Connecter chez Supportini");
        primaryStage.setScene(scene);

        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void goToReset() throws IOException {
        Stage stage = (Stage) btnCode.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("./ValidCode.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Se Connecter chez Supportini");
        primaryStage.setScene(scene);

        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
