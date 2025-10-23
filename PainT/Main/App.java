package Main;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
 
public class App extends Application {
    
    /** 
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
    Scene scene = new Scene(root);

    primaryStage.setTitle("PeinT");
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.getIcons().add(new Image("Main\\images\\icona.png"));
    }
 
 
 /** 
  * @param args
  */
 public static void main(String[] args) {
        launch(args);
    }
}