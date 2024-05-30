package com.example.aigame;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
public class DifficultyController {
    @FXML
    AnchorPane difficultyCont;
    @FXML
    void easyAction() {
        HelloApplication.changeScreen(Difficulty.EASY,difficultyCont,"game");
    }
    @FXML
    void mediumAction() {
        HelloApplication.changeScreen(Difficulty.MEDIUM,difficultyCont,"game");
    }
    @FXML
    void difficultAction() {
        HelloApplication.changeScreen(Difficulty.HARD,difficultyCont,"game");
    }
    void setDifficulty(Difficulty diff){
        // This method is used to not give an error when setting the difficulty when changing screens
    }
    //Note that iam write the code in javaFx and use CSS
}
