package tictactoe;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

class dual{

    int a,b;
    dual(int _a, int _b){
        a = _a;
        b = _b;
    }
}

class TicTacToe{

    char[][] arr = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
    String decision = "pending";
    int x = -999;
    int y = -999;
    int movecount = 0;
    Scanner sc = new Scanner(System.in);

    dual convert(int x,int y){
        if(x==1 && y==3){
            return new dual(1,1);
        }
        else if(x==2 && y==3){
            return new dual(1,2);
        }
        else if(x==3 && y==3){
            return new dual(1,3);
        }
        else if(x==1 && y==2){
            return new dual(2,1);
        }
        else if(x==2 && y==2){
            return new dual(2,2);
        }
        else if(x==3 && y==2){
            return new dual(2,3);
        }
        else if(x==1 && y==1){
            return new dual(3,1);
        }
        else if(x==2 && y==1){
            return new dual(3,2);
        }
        else if(x==3 && y==1){
            return new dual(3,3);
        }
        return new dual(7,7);
    }

    void print_field(){
        System.out.println("---------");
        for(int i=0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if(arr[i][j]=='_'){
                    System.out.print(" " + " ");
                }
                else{
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");

    }

    void verdict(int _x, int _y, char Symbol){

        String decision_text = "";
        if(Symbol=='X'){
            decision_text = "win";
        }
        else if(Symbol=='O'){
            decision_text = "lose";
        }

        if(_x==0 && _y==0){
            if(arr[_x][_y]==Symbol && arr[_x+1][_y+1]==Symbol && arr[_x+2][_y+2]==Symbol){
                decision = decision_text;
            }
        }
        else if(_x==2 && _y==0){
            if(arr[_x][_y]==Symbol && arr[_x-1][_y+1]==Symbol && arr[_x-2][_y+2]==Symbol){
                decision = decision_text;
            }
        }
        else if(_x==0 && _y==2){
            if(arr[_x][_y]==Symbol && arr[_x+1][_y-1]==Symbol && arr[_x+2][_y-2]==Symbol){
                decision = decision_text;
            }
        }
        else if(_x==2 && _y==2){
            if(arr[_x][_y]==Symbol && arr[_x-1][_y-1]==Symbol & arr[_x-2][_y-2]==Symbol){
                decision = decision_text;
            }
        }

        if(_x==0){
            if(arr[_x][_y]==Symbol && arr[_x+1][_y]==Symbol && arr[_x+2][_y]==Symbol){
                decision = decision_text;
            }
        }
        else if(_x==1){
            if(arr[_x-1][_y]==Symbol && arr[_x][_y]==Symbol && arr[_x+1][_y]==Symbol){
                decision = decision_text;
            }
        }
        else if(_x==2){
            if(arr[_x-2][_y]==Symbol && arr[_x-1][_y]==Symbol && arr[_x][_y]==Symbol){
                decision = decision_text;
            }
        }

        if(_y==0){
            if(arr[_x][_y]==Symbol && arr[_x][_y+1]==Symbol && arr[_x][_y+2]==Symbol){
                decision = decision_text;
            }
        }
        else if(_y==1){
            if(arr[_x][_y-1]==Symbol && arr[_x][_y]==Symbol && arr[_x][_y+1]==Symbol){
                decision = decision_text;
            }
        }
        else if(_y==2){
            if(arr[_x][_y-2]==Symbol && arr[_x][_y-1]==Symbol && arr[_x][_y]==Symbol){
                decision = decision_text;
            }
        }

    }

    String make_user_move(char Symbol){

        int x, y;
        System.out.println("Enter the coordinates: ");
        boolean break_flag = false;
        while(true){
            while (true) {
                try{
                    x = sc.nextInt();
                    y = sc.nextInt();
                    if (0 < x && x <= 3 && 0 < y && y <= 3) {
                        break_flag = true;
                        break;
                    }
                    else{
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                }
                catch (Exception e){
                    System.out.println("You should enter numbers!");
                    sc.nextLine();
                }
            }

            dual d = convert(x, y);
            x = d.a - 1;
            y = d.b - 1;

            if (arr[x][y] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            }
            else if (break_flag == true) {
                break;
            }
        }

        arr[x][y] = Symbol;
        verdict(x,y,Symbol);

        return decision;
    }

    String make_computer_move(char Symbol){

        System.out.println("Making move level \"easy\"");
        var random = new Random();
        while(true){
            x = random.nextInt(3);
            y = random.nextInt(3);
            if(arr[x][y]=='_'){
                break;
            }
        }

        arr[x][y] = Symbol;
        verdict(x,y,Symbol);

        return decision;
    }

    void play_game(){

        //arr = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
        String input = "";
        String[] sepinputs;
        while(true){
            decision = "pending";
            movecount = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    arr[i][j] = '_';
                }
            }
            System.out.print("Input command: ");
            input = sc.nextLine();
            sepinputs = input.split(" ");

            if(sepinputs[0].equals("start") && sepinputs.length!=3){
                System.out.println("Bad parameters!");
            }
            else if(sepinputs[0].equals("start") && sepinputs.length==3){
                String[] players = {sepinputs[1], sepinputs[2]};
                char[] symbols = {'X','O'};
                print_field();

                while(movecount<9 && decision.equalsIgnoreCase("pending")){
                    int turn = movecount%2;
                    if(players[turn].equals("user")){ // problem
                        System.out.println("inside user moves");
                        decision = make_user_move(symbols[turn]);
                    }
                    else{
                        decision = make_computer_move(symbols[turn]);
                    }
                    movecount++;
                    print_field();
                }

                if(decision.equalsIgnoreCase("win")){
                    System.out.println("X wins");
                }
                else if(decision.equalsIgnoreCase("lose")){
                    System.out.println("O wins");
                }
                else if(movecount>=9){
                    System.out.println("Draw");
                }
            }
            else if(sepinputs[0].equals("exit")){
                break;
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        var ttt = new TicTacToe();
        ttt.play_game();
    }
}
