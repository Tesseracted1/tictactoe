package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void display(char[][] game){
        System.out.println("---------");
        System.out.println("| " + game[0][0] + " " + game[0][1] + " " + game[0][2] + " |");
        System.out.println("| " + game[1][0] + " " + game[1][1] + " " + game[1][2] + " |");
        System.out.println("| " + game[2][0] + " " + game[2][1] + " " + game[2][2] + " |");
        System.out.println("---------");
    }

    public static boolean checkIfFinished(char[][] game){
        for(char[] row : game){
            for(char elem: row){
                if(elem == '_'){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkIfWinningC(char[][] game, char c){
        //rows
        for(int i = 0; i < 3; i++) {
            if (game[i][0] == c && game[i][1] == c && game[i][2] == c) {
                return true;
            }
        }
        //collumns
        for(int j = 0; j < 3; j++) {
            if (game[0][j] == c && game[1][j] == c && game[2][j] == c) {
                return true;
            }
        }
        //diagonals
        if(game[0][0] == c && game[1][1] == c && game[2][2] == c){
            return true;
        }
        if(game[0][2] == c && game[1][1] == c && game[2][0] == c){
            return true;
        }

        return false;
    }

    public static boolean checkIfWinning(char[][] game){
        boolean x = checkIfWinningC(game, 'X'), o = checkIfWinningC(game, 'O');
        if(x && o){
            System.out.print("Impossible");
            return true;
        }
        else if(x){
            display(game);
            System.out.print("X wins");

            return true;
        }
        else if(o){
            display(game);
            System.out.print("O wins");

            return true;
        }
        return false;

    }

    public static boolean isContinuing(char[][] game){
        if(checkIfWinning(game))
            return false;
        if(checkIfFinished(game)){
            display(game);
            System.out.print("Draw");
            return false;
        }
        return true;
    }

    public static void handleInput(Scanner scanner, char[][] game, char player){
        System.out.print("Enter the coordinates: ");
        String coords = scanner.nextLine();
        int x, y;
        if(coords.charAt(0) < '0' || coords.charAt(0) > '9' || coords.length() != 3){
            System.out.println("You should enter numbers!");
            handleInput(scanner, game, player);
            return;
        }

        x = Integer.parseInt(coords.charAt(0) + "");
        y = Integer.parseInt(coords.charAt(2) + "");
        if(x > 3 || y > 3 || x < 1 || y < 1){
            System.out.println("Coordinates should be from 1 to 3!");
            handleInput(scanner, game, player);
            return;
        }
        if(game[x - 1][y - 1] != '_')
        {
            System.out.println("This cell is occupied! Choose another one!");
            handleInput(scanner, game, player);
            return;
        }


        game[x - 1][y - 1] = player;
    }

    public static void gameLoop(Scanner scanner, char[][] game){
        char player = 'X';
        while(true){
            display(game);

            handleInput(scanner, game, player);

            if(!isContinuing(game)){
                break;
            }

            player = player == 'X' ? 'O' : 'X';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] game = {{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};

        gameLoop(sc, game);

        return;
    }
}
