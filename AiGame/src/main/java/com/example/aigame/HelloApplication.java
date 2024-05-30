package com.example.aigame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/FirstPage.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void changeScreen(Difficulty diff,Node node, String fxml){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("fxml/"+fxml+".fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)node.getScene().getWindow();
            GameController controller=loader.getController();
            controller.setDifficulty(diff);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}