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
    String decision_global = "pending";
    int x = -999;
    int y = -999;
    int movecount = 0;
    Scanner sc = new Scanner(System.in);

    dual convert(int x,int y){
        if(x==1 && y==3){
            return new dual(0,0);
        }
        else if(x==2 && y==3){
            return new dual(0,1);
        }
        else if(x==3 && y==3){
            return new dual(0,2);
        }
        else if(x==1 && y==2){
            return new dual(1,0);
        }
        else if(x==2 && y==2){
            return new dual(1,1);
        }
        else if(x==3 && y==2){
            return new dual(1,2);
        }
        else if(x==1 && y==1){
            return new dual(2,0);
        }
        else if(x==2 && y==1){
            return new dual(2,1);
        }
        else if(x==3 && y==1){
            return new dual(2,2);
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

    String verdict(int _x, int _y, char Symbol){

        String decision = "pending";
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
        return decision;
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
            x = d.a;
            y = d.b;

            if (arr[x][y] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            }
            else if (break_flag == true) {
                break;
            }
        }

        arr[x][y] = Symbol;
        decision_global = verdict(x,y,Symbol);

        return decision_global;
    }

    String make_computer_move(char Symbol,String mode){

        if(mode.equals("easy")){
            System.out.println("Making move level \"easy\"");
        }
        else if(mode.equals("medium")){
            System.out.println("Making move level \"medium\"");
        }

        if(mode.equals("medium")){
            // Detecting potential oneshot win situation for both sides. Applicable for medium level.
            ArrayList<dual> list = new ArrayList<dual>();
            char otherSymbol = '\0';
            if (Symbol=='X'){
                otherSymbol = 'O';
            }
            else{
                otherSymbol = 'X';
            }

            for(int i=0;i<3;i++){ // listing empty cells
                for(int j=0;j<3;j++){
                    if(arr[i][j]=='_'){
                        list.add(new dual(i,j));
                    }
                }
            }

            boolean decision_taken = false;
            dual decided_pos = new dual(-999,-999);

            for(int i=0;i<list.size();i++){ // checking each empty cell's potential for its own
                dual d = list.get(i);

                arr[d.a][d.b] = Symbol;
                String semi_dec = verdict(d.a,d.b,Symbol);
                if(Symbol == 'X' && semi_dec.equals("win") || Symbol == 'O' && semi_dec.equals("lose")){
                    decided_pos = d;
                    decision_taken = true;
                    break;
                }
                arr[d.a][d.b] = '_';
            }

            if(decision_taken==false){
                for(int i=0;i<list.size();i++){ // checking each empty cell's potential for the opponent
                    dual d = list.get(i);
                    arr[d.a][d.b] = otherSymbol;
                    String semi_dec = verdict(d.a,d.b,otherSymbol);
                    if(otherSymbol == 'O' && semi_dec.equals("lose") || otherSymbol == 'X' && semi_dec.equals("win")){
                        decided_pos = d;
                        decision_taken = true;
                        break;
                    }
                    arr[d.a][d.b] = '_';
                }
            }
            if(decision_taken==true){
                x = decided_pos.a;
                y = decided_pos.b;
                arr[x][y] = Symbol;
                decision_global = verdict(x,y,Symbol);
            }
            else{
                var random = new Random();
                while(true){
                    x = random.nextInt(3);
                    y = random.nextInt(3);
                    if(arr[x][y]=='_'){
                        break;
                    }
                }
                arr[x][y] = Symbol;
                decision_global = verdict(x,y,Symbol);
            }
        }

        else if(mode.equals("easy")){
            var random = new Random();
            while(true){
                x = random.nextInt(3);
                y = random.nextInt(3);
                if(arr[x][y]=='_'){
                    break;
                }
            }
            arr[x][y] = Symbol;
            decision_global = verdict(x,y,Symbol);
        }

        return decision_global;
    }

    void play_game(){

        String input = "";
        String[] sepinputs;
        while(true){
            decision_global = "pending";
            movecount = 0;
            var EmptyList = new ArrayList();
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

                while(movecount<9 && decision_global.equalsIgnoreCase("pending")){
                    int turn = movecount%2;
                    if(players[turn].equals("user")){ // problem
                        System.out.println("inside user moves");
                        decision_global = make_user_move(symbols[turn]);
                    }
                    else{
                        decision_global = make_computer_move(symbols[turn],players[turn]);
                    }
                    movecount++;
                    print_field();
                }

                if(decision_global.equalsIgnoreCase("win")){
                    System.out.println("X wins");
                }
                else if(decision_global.equalsIgnoreCase("lose")){
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
