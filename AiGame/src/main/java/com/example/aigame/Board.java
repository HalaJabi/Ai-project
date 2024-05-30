package com.example.aigame;

public class Board {
    GameController controller;
    public Board(GameController gameController) {
        this.controller=gameController;
    }
    //very important Ai algorithm (brain of project)
    double alphaBeta(int [] position, int depth, boolean maximizingPlayer, double alpha, double beta){
        double v;
        if(depth==0 || controller.winning(position).equals(State.DRAW) || controller.winning(position).equals(State.X_WIN) || controller.winning(position).equals(State.O_WIN)){
            return evaluate(position);
        }
        if(maximizingPlayer){//max player
            v=Double.MIN_VALUE;//-infinity
            int [][] pos = child(position,false);
            for(int i=0; i<numOfChild(position);i++){ //for each child
                v=Math.max(v, alphaBeta(pos[i],depth-1,false,alpha,beta));
                alpha=Math.max(alpha,v);
                if(beta<=alpha){
                    break;
                }
            }
            return v;
        }else{//min player
            v=Double.MAX_VALUE;//infinity
            int [][] pos =child(position,true);
            for(int i=0; i<numOfChild(position);i++) {
                v = Math.min(v, alphaBeta(pos[i], depth - 1, true, alpha, beta));
                beta = Math.min(beta, v);
                if (beta <= alpha) {
                    break;
                }
            }
            return v;
        }
    }
    // evaluate function: number of possible X win -number of possible O win
    public int evaluate(int [] t) {
        int score = 0;
        for (int i = 1; i <= 9; i += 3) {
            if (t[i] == 1 && t[i+1] == 1 && t[i+2] == 1) {//row three X
                return 100;
            } else if ((t[i] == 1 && t[i+1] == 1 && t[i+2] == 2)
                    || (t[i] == 1 && t[i+1] == 2 && t[i+2] == 1)
                    || (t[i] == 2 && t[i+1] == 1 && t[i+2] == 1)) {
                score++;
            }else if ((t[i] == 1 && t[i+1] == 2 && t[i+2] == 2)
                    || (t[i] == 2 && t[i+1] == 1 && t[i+2] == 2)
                    || (t[i] == 2 && t[i+1] == 2 && t[i+2] == 1)) {
                score++;
            }
            if (t[i] == 0 && t[i+1] == 0 && t[i+2] == 0) {//row three O
                return -100;
            } else if ((t[i] == 0 && t[i+1] == 0 && t[i+2] == 2)
                    || (t[i] == 0 && t[i+1] == 2 && t[i+2] == 0)
                    || (t[i] == 2 && t[i+1] == 0 && t[i+2] == 0)) {
                score--;
            }else if ((t[i] == 0 && t[i+1] == 2 && t[i+2] == 2)
                    || (t[i] == 2 && t[i+1] == 0 && t[i+2] == 2)
                    || (t[i] == 2 && t[i+1] == 2 && t[i+2] == 0)) {
                score--;
            }
        }
        for (int i = 1; i <= 3; i++) {
            if (t[i] == 1 && t[i+3] == 1 && t[i+6] == 1) {//column three X
                return 100;
            } else if ((t[i] == 1 && t[i+3] == 1 && t[i+6] == 2)
                    || (t[i] == 1 && t[i+3] == 2 && t[i+6] == 1)
                    || (t[i] == 2 && t[i+3] == 1 && t[i+6] == 1)) {
                score++;
            }else if ((t[i] == 1 && t[i+3] == 2 && t[i+6] == 2)
                    || (t[i] == 2 && t[i+3] == 1 && t[i+6] == 2)
                    || (t[i] == 2 && t[i+3] == 2 && t[i+6] == 1)) {
                score++;
            }
            if (t[i] == 0 && t[i+3] == 0 && t[i+6] == 0) {//column three 0
                return -100;
            } else if ((t[i] == 0 && t[i+3] == 0 && t[i+2] == 2)
                    || (t[i] == 0 && t[i+3] == 2 && t[i+2] == 0)
                    || (t[i] == 2 && t[i+3] == 0 && t[i+2] == 0)) {
                score--;
            }else if ((t[i] == 0 && t[i+3] == 2 && t[i+6] == 2)
                    || (t[i] == 2 && t[i+3] == 0 && t[i+6] == 2)
                    || (t[i] == 2 && t[i+3] == 2 && t[i+6] == 0)) {
                score--;
            }
        }if(t[1]==1 && t[5]==1 && t[9]==1){ //diagonal X
            return 100;
        } else if (t[1]==1 && t[5]==1 && t[9]==2
                || t[1]==1 && t[5]==2 && t[9]==1
                || t[1]==2 && t[5]==1 && t[9]==1) {
            score++;
        }else if (t[1]==1 && t[5]==2 && t[9]==2
                || t[1]==2 && t[5]==2 && t[9]==1
                || t[1]==2 && t[5]==1 && t[9]==2) {
            score++;
        }
        if(t[1]==0 && t[5]==0 && t[9]==0){//diagonal O
            return -100;
        }else if (t[1]==0 && t[5]==0 && t[9]==2
                || t[1]==0 && t[5]==2 && t[9]==0
                || t[1]==2 && t[5]==0 && t[9]==0) {
            score--;
        }else if (t[1]==0 && t[5]==2 && t[9]==2
                || t[1]==2 && t[5]==2 && t[9]==0
                || t[1]==2 && t[5]==0 && t[9]==2) {
            score--;
        }
        if(t[0]==9){ //Draw
            return 50;
        }
        return score;
    }
    public int numOfChild(int [] t){
        int count=0;
        for (int i=1;i<=9;i++){
            if(t[i]==2){
                count++;
            }
        }
        return count;
    } //find number of children of node
    public int [][] child(int [] t,boolean maximizingPlayer){
        //to find all child for the node
        int children[][]=new int[numOfChild(t)][10];
        int count=0;
        for (int j=0;j<numOfChild(t);j++){
            for (int i=0;i<=9;i++){
                children [j][i]=t[i];
            }
        }
        for (int j=0;j<numOfChild(t);j++) {//3 2 2 0 1 0 2 2 2 2
            count=0;
            for (int i = 1; i <= 9; i++) {
                if (children [j][i]==2){
                    if(j==count){
                        if(maximizingPlayer) {
                            children[j][i] = 1;
                        }else {
                            children[j][i] = 0;
                        }
                    }
                }else {
                    continue;
                }
                count++;
            }
        }
        return children;
    } //to find children of node
    public void print(int []t,int [][] child){
        int children[][]=child;
        for (int j=0;j<numOfChild(t);j++){
            for (int i=0;i<=9;i++){
                System.out.print(children [j][i]+" ");
            }
            System.out.print("\n"+evaluate(children[j]));
            System.out.print("\n");
        }
    }//you can use it for testing
}