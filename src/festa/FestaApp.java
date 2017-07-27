/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package festa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 *
 * @author Rafael Rutsatz
 */
public class FestaApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/festa.fxml"));
        
        Scene scene = new Scene(root);
        
        EntityManager entityManager = JpaUtil.getEntityManager();
        
        stage.setScene(scene);
        stage.setTitle("Gerenciador de convites");
        stage.show();
        
        stage.setOnCloseRequest((event) -> {
            JpaUtil.closeEntityManagerFactory();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
