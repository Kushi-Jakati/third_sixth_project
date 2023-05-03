package edu.guilford;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class RationalVisual extends Application {

    private static Scene scene;

    // A driver program that instantiates the Stage and Scene for the GUI
    @Override
    public void start(Stage stage) throws IOException {
       
        //Instantiate the controller
        VBox root = new VBox();
        Rational rational = new Rational(1, 4);
        root.getChildren().add(new RationalPane(rational));
        
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
