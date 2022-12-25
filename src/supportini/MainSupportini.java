/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supportini;

import Exception.AuthException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONException;
import util.JWebToken;

/**
 *
 * @author Elife-Kef-130
 */
public class MainSupportini extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root;
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        JWebToken jwt = null;

        try {

            jwt = new JWebToken(bearerToken);

            if (!jwt.isValid()) {
                String audience = jwt.getAudience();
                String subject = jwt.getSubject();
                int idRole = Integer.parseInt(audience);

                if (idRole == 1) {
                    root = FXMLLoader.load(getClass().getResource("../gui/admin/dashboard/Dashboard.fxml"));

                    primaryStage.setTitle("Dashboard Administrateur ");
                } else {
                    root = FXMLLoader.load(getClass().getResource("../gui/dashboard/DashboardFXML.fxml"));

                    primaryStage.setTitle("Dashboard");
                }
                Scene scene = new Scene(root);
                Image icon;
                icon = new Image(getClass().getResourceAsStream("../gui/uicontrolers/logosportstrnsprt.png"));
                primaryStage.getIcons().add(icon);

                primaryStage.setScene(scene);

                primaryStage.sizeToScene();

                primaryStage.show();

            }

        } catch (IOException | JSONException | InvalidKeyException | AuthException ex) {
            Logger.getLogger(MainSupportini.class.getName()).log(Level.SEVERE, null, ex);
            root = FXMLLoader.load(getClass().getResource("../gui/login/LoginFXML.fxml"));
            primaryStage.setTitle("Se Connecter chez Supportini");
            Scene scene = new Scene(root);
            Image icon;
            icon = new Image(getClass().getResourceAsStream("../gui/uicontrolers/logosportstrnsprt.png"));
            primaryStage.getIcons().add(icon);

            primaryStage.setScene(scene);

            primaryStage.sizeToScene();

            primaryStage.show();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static Stage primaryStage; // **Declare static Stage**

    private void setPrimaryStage(Stage stage) {
        MainSupportini.primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return MainSupportini.primaryStage;
    }
    /**
     * Requests an authorization code from the auth server
     *
     * @return
     * @throws MalformedURLException
     * @throws URISyntaxException
     * @throws AuthorizationException
     */
}
