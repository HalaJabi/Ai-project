package com.example.aigame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Pair;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {
    @FXML
    Button btn1;
    @FXML
    Button btn2;
    @FXML
    Button btn3;
    @FXML
    Button btn4;
    @FXML
    Button btn5;
    @FXML
    Button btn6;
    @FXML
    Button btn7;
    @FXML
    Button btn8;
    @FXML
    Button btn9;
    @FXML
    Label xPlayerTxt;
    @FXML
    Label oPlayerTxt;
    @FXML
    Label round;
    @FXML
    Label difficultyTxt;
    int [] t;
    int xCount;
    int oCount;
    int roundNum;
    Player player;
    Board board;
    Difficulty difficulty;
    void initialT(){
        t[0]=0;
        for(int i=1;i<=9;i++){
            t[i]=2;
        }
    }
    boolean pressed(Button btn){
        if(!btn.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid button, press another button");
            alert.setTitle("warning");
            alert.showAndWait();
            return true;
        }
        return false;
    }
    void toChoosePlayer(){
        State state=winning(t);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("winner");
        String msg="";
        switch (state){
            case DRAW -> msg= "It is a draw";
            case X_WIN -> {
                msg="X is winner in this turn";
                xCount++;
                xPlayerTxt.setText(String.valueOf(xCount));
            }
            case O_WIN -> {
                msg="O is winner in this turn";
                oCount++;
                oPlayerTxt.setText(String.valueOf(oCount));
            }
            case NO_WIN -> {
                if (player==Player.X){
                    player=Player.O;
                }else{
                    player=Player.X;
                }
                return;
            }
        }
        alert.setContentText(msg);
        alert.showAndWait();
        roundNum++;
        round.setText(String.valueOf(roundNum));
        reset();
    }
    State winning(int[] t){
        for (int i = 1; i < 8; i = i + 3) {
            if ((t[i] == 1 && t[i + 1] == 1 && t[i + 2] == 1)) {
                return State.X_WIN;
            } else if ((t[i] == 0 && t[i + 1] == 0 && t[i + 2] == 0)) {
                return State.O_WIN;
            }
        }
        for (int i = 1; i <= 3; i++) {
            if ((t[i] == 1 && t[i + 3] == 1 && t[i + 6] == 1)) {
                return State.X_WIN;
            } else if ((t[i] == 0 && t[i + 3] == 0 && t[i + 6] == 0)) {
                return State.O_WIN;
            }
        }
        if ((t[1] == 1 && t[5] == 1 && t[9] == 1) ||(t[3] == 1 && t[5] == 1 && t[7] == 1)) {
            return State.X_WIN;
        } else if ((t[1] == 0 && t[5] == 0 && t[9] == 0)||(t[3] == 0 && t[5] == 0 && t[7] == 0)) {
            return State.O_WIN;
        }
        if(t[0]==9){
            return State.DRAW;
        }
        return State.NO_WIN;
    }
    int[] getOptimalChild(){
        int[][] children=board.child(t,true);
        ArrayList<Pair<Double,int[]>> pairs=new ArrayList<>();
        for (int i = 0; i < board.numOfChild(t); i++) {
            var child=children[i];
            pairs.add(new Pair<>(board.alphaBeta(child,getDepth(),false,Double.MIN_VALUE,Double.MAX_VALUE),child));
        }
        Optional<Pair<Double,int[]>> optional=pairs.stream().min(Comparator.comparing(Pair::getKey));
        return optional.map(Pair::getValue).orElse(null);
    }
    int getDepth(){ // very important!!
        switch (difficulty){
            case EASY -> { //esay alph beta but depth 1
                return 1;
            }
            case MEDIUM -> {//medium alph beta but depth 5
                return 5;
            }
            case HARD -> { //hard alph beta but depth 5
                return 9;
            }
            default -> { //non depth 0
                return 0;
            }
        }
    }
    @FXML
    void reset(){
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        player=Player.X;
        initialT();
        if(difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm you went to exit", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Exit");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }
    //all btn same
    @FXML
    void btn1Action(){
        if(pressed(btn1)){
            return;
        }
        btn1.setText(String.valueOf(player));
        if(player==Player.X){
            t[1]=1;
        }else {
            t[1]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){ //if player x ||if easy or medum or hard (Ai round)
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void btn2Action(){
        if(pressed(btn2)){
            return;
        }
        btn2.setText(String.valueOf(player));
        if(player==Player.X){
            t[2]=1;
        }else {
            t[2]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void btn3Action(){
        if(pressed(btn3)){
            return;
        }
        btn3.setText(String.valueOf(player));
        if(player==Player.X){
            t[3]=1;
        }else {
            t[3]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void btn4Action(){
        if(pressed(btn4)){
            return;
        }
        btn4.setText(String.valueOf(player));
        if(player==Player.X){
            t[4]=1;
        }else {
            t[4]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void btn5Action(){
        if(pressed(btn5)){
            return;
        }
        btn5.setText(String.valueOf(player));
        if(player==Player.X){
            t[5]=1;
        }else {
            t[5]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void btn6Action(){
        if(pressed(btn6)){
            return;
        }
        btn6.setText(String.valueOf(player));
        if(player==Player.X){
            t[6]=1;
        }else {
            t[6]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void btn7Action(){
        if(pressed(btn7)){
            return;
        }
        btn7.setText(String.valueOf(player));
        if(player==Player.X){
            t[7]=1;
        }else {
            t[7]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void btn8Action(){
        if(pressed(btn8)){
            return;
        }
        btn8.setText(String.valueOf(player));
        if(player==Player.X){
            t[8]=1;
        }else {
            t[8]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    @FXML
    void btn9Action(){
        if(pressed(btn9)){
            return;
        }
        btn9.setText(String.valueOf(player));
        if(player==Player.X){
            t[9]=1;
        }else {
            t[9]=0;
        }
        t[0]++;
        toChoosePlayer();
        roundAction();
        if(player==Player.X&& difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    void roundAction(){
        if(roundNum==5){
            roundNum=0;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("winner");
            if(xCount>oCount){
                alert.setContentText("Congratulation, winner X");
            }else if(xCount<oCount){
                alert.setContentText("Congratulation, winner O");
            }else{
                alert.setContentText("Oops,No winner");
            }
            alert.showAndWait();
            xCount=0;
            oCount=0;
            xPlayerTxt.setText(String.valueOf(0));
            oPlayerTxt.setText(String.valueOf(0));
            round.setText(String.valueOf(0));
            reset();
        }
    }
    void setDifficulty(Difficulty diff){
        difficultyTxt.setText(String.valueOf(diff));
        difficulty=diff;
        if(difficulty!=Difficulty.NON){
            actionAI(t,getOptimalChild());
        }
    }
    void actionAI(int [] tCurrent,int [] tNew){ //control of ai
        for (int i=1;i<=9;i++){
            if(tCurrent[i]!=tNew[i]){
                switch (i){
                    case 1 -> btn1Action();
                    case 2 -> btn2Action();
                    case 3 -> btn3Action();
                    case 4 -> btn4Action();
                    case 5 -> btn5Action();
                    case 6 -> btn6Action();
                    case 7 -> btn7Action();
                    case 8 -> btn8Action();
                    case 9 -> btn9Action();
                }
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t= new int[]{0, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        xCount=0;
        oCount=0;
        roundNum=0;
        player=Player.X;
        board=new Board(this);
    }
}