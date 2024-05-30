package com.example.aigame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {
    @FXML
    AnchorPane container;
    @FXML
    void playMulti(){
        HelloApplication.changeScreen(Difficulty.NON,container,"game");
    }
    @FXML
    void playSingle()throws IOException {
        //HelloApplication.changeScreen(Difficulty.NON,container,"difficulty");
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("fxml/difficulty.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)container.getScene().getWindow();
            DifficultyController controller=loader.getController();
            controller.setDifficulty(Difficulty.NON);
            stage.setScene(new Scene(root));
            stage.show();
    }
}