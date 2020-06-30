package tictactoe;

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
    char userSymbol = 'T';
    char otherSymbol = 'T';
    String decision = "pending";
    int x = -999;
    int y = -999;
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

    void take_field(){
        System.out.println("Enter cells:");
        String N = sc.next();
        //String N = "O__XX___O";
        int numX = 0;
        int numO = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                char s = N.charAt(i*3+j);
                if(s=='X'){
                    arr[i][j] = 'X';
                    numX++;
                }
                else if(s=='O'){
                    arr[i][j] = 'O';
                    numO++;
                }
                else if(s=='_'){
                    arr[i][j] = '_';
                }
            }
        }
        if(numX==numO){
            userSymbol = 'X';
        }
        else if(numX>numO){
            userSymbol = 'O';
        }

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

    void verdict(int _x, int _y){

        if(_x==0 && _y==0){
            if(arr[_x][_y]==userSymbol && arr[_x+1][_y+1]==userSymbol && arr[_x+2][_y+2]==userSymbol){
                decision = "win";
            }
        }
        else if(_x==2 && _y==0){
            if(arr[_x][_y]==userSymbol && arr[_x-1][_y+1]==userSymbol && arr[_x-2][_y+2]==userSymbol){
                decision = "win";
            }
        }
        else if(_x==0 && _y==2){
            if(arr[_x][_y]==userSymbol && arr[_x+1][_y-1]==userSymbol && arr[_x+2][_y-2]==userSymbol){
                decision = "win";
            }
        }
        else if(_x==2 && _y==2){
            if(arr[_x][_y]==userSymbol && arr[_x-1][_y-1]==userSymbol & arr[_x-2][_y-2]==userSymbol){
                decision = "win";
            }
        }

        if(_x==0){
            if(arr[_x][_y]==userSymbol && arr[_x+1][_y]==userSymbol && arr[_x+2][_y]==userSymbol){
                decision = "win";
            }
        }
        else if(_x==1){
            if(arr[_x-1][_y]==userSymbol && arr[_x][_y]==userSymbol && arr[_x+1][_y]==userSymbol){
                decision = "win";
            }
        }
        else if(_x==2){
            if(arr[_x-2][_y]==userSymbol && arr[_x-1][_y]==userSymbol && arr[_x][_y]==userSymbol){
                decision = "win";
            }
        }

        if(_y==0){
            if(arr[_x][_y]==userSymbol && arr[_x][_y+1]==userSymbol && arr[_x][_y+2]==userSymbol){
                decision = "win";
            }
        }
        else if(_y==1){
            if(arr[_x][_y-1]==userSymbol && arr[_x][_y]==userSymbol && arr[_x][_y+1]==userSymbol){
                decision = "win";
            }
        }
        else if(_y==2){
            if(arr[_x][_y-2]==userSymbol && arr[_x][_y-1]==userSymbol && arr[_x][_y]==userSymbol){
                decision = "win";
            }
        }

    }

    String take_next_move(){

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

        arr[x][y] = userSymbol;

        verdict(x,y);

        return decision;
    }

    void play_game(){

        var arrlist = new ArrayList();
        int empty = 0;
        take_field();
        print_field();
        decision = take_next_move();
        print_field();
        if(decision.equalsIgnoreCase("pending")){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(arr[i][j]=='_'){
                        empty++;
                        arrlist.add(new dual(i,j));
                    }
                }
            }
            if(empty==0){
                decision = "draw";
            }
            else{
                decision = "pending";
            }
        }

        if(decision.equalsIgnoreCase("win")){
            System.out.println(userSymbol + " wins");
        }
        else if(decision.equalsIgnoreCase("draw")){
            System.out.println("Draw");
        }
        else if(decision.equalsIgnoreCase("pending")){
            System.out.println("Game not finished");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        var ttt = new TicTacToe();
        ttt.play_game();
    }
}
