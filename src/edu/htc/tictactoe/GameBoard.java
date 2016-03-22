package edu.htc.tictactoe;

import java.util.ArrayList;
import java.lang.*;
import java.util.Scanner;

/**
 * Created by clifford.mauer on 2/8/2016.
 */
public class GameBoard {

    //Since we will need to be able to test with different configurations of moves on the board,
    //let's add a new constructor to the GameBoard just for testing.  This constructor should take on
    //input parameter, a char[] that contains the desired state of the board.
    //Make sure the the ArrayList openSquares is set up correctly to match the input array values.
    private char[] boardstatus;

    // this variable tells the program what level of difficulty that
    // the playing environment will be.
    // This game will have 4 levels of difficulty:
    //     1 -- Simple
    //     2 -- Easy
    //     3 -- Medium
    //     4 -- Hard
    public int intGameLevel;
    public int intCurrentPlayer;


    public GameBoard(char[] stateArray){
        boardstatus = stateArray;
    }
    public GameBoard(){
        boardstatus = null;
    }
    Scanner input = new Scanner(System.in);

    char[] board = new char[] {'1','2','3','4','5','6','7','8','9'};

    private int[][] winCombinations = new int[][] {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //vertical wins
            {0, 4, 8}, {2, 4, 6}             //diagonal wins
    };

    public static ArrayList<Integer> openSquares = new ArrayList<>(9);

    public static void main(String[] args){
        //Initialize the arraylist with the integers 1-9
        for (int i = 1; i <= 9 ; i++) {
            openSquares.add(i);
        }
        testGameBoardDisplay();
        testIsSquareOpen();
    }

    public static void testIsSquareOpen(){
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("Testing is square open method");

        GameBoard board = new GameBoard();

        System.out.println("Testing with empty board - all squares should be open.");

        boolean error = false;
        // test for an empty board
        for (int i = 0; i < 9 ; i++) {
            if (board.isSquareOpen(i) == false ){
                error = true;
                System.out.println("Error: Square " + i + " is NOT open but should be!");
            }
        }
        if (error == false){
            System.out.println("Correct: All squares are open.");
        }

        System.out.println();
        System.out.println("Testing after updates:");
        System.out.println("Update square 1 with X.");
        // test for square updates
        // we have 9 squares to test for updating

        for (int i = 0; i <9 ; i++) {
            board.updateSquare(i+1,'X');
            // make sure that the target square updated
            if ( board.isSquareOpen(i)){
                System.out.println("Error: Square " + (i+1) + " is still open.");
            }else{
                System.out.println("Correct:  Square " + (i+1) + "  is no longer open.");
            }
            // make sure that the non-target squares are blank
            for (int j = 0; j < 9 ; j++) {
                //if (j != i){
                    if (board.isSquareOpen(j) == false ){
                        System.out.println("Error:  Square " + (j+1) + " is NOT open which is correct!");
                    }else{
                        System.out.println("Correct: Square " + (j+1) + " is open!");
                    }

                //}
            }
            // reset the square back to its integer
            // create a character primitives ch1
            char squareNum;

            // assign char representation of i to ch1 using radix
            squareNum = Character.forDigit(i+1, 10);
            board.board[i]=squareNum;
        }
        System.out.println("End testing of isOpenSquare");
    }

    public static void testGameBoardDisplay(){
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Testing display of board");

        GameBoard board = new GameBoard();
        board.display();

        System.out.println("Testing display of board after adding O in top right, X in center and X in bottom left.");
        board.updateSquare(5,'X');
        board.updateSquare(3,'O');
        board.updateSquare(7,'X');
        board.display();
        System.out.println("End testing display of board");

    }

    public void display(){

        System.out.println("   |   |   ");
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("___|___|___");
        System.out.println("   |   |   ");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("___|___|___");
        System.out.println("   |   |   ");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
        System.out.println("   |   |   ");

    }

    public Boolean isGameWon(char[] currentBoard){
        Boolean isWon = false;

        // Check if the currentBoard has all X's or all O's in the array of win combinations
        // There are simply 8 combinations, so we will check for X's or O's in each one.

        for (int i = 0; i < 8; i++) {

            // check if the array matches the currentBoard array
            if( currentBoard[winCombinations[i][0]]==(currentBoard[winCombinations[i][1]]) &&
                    currentBoard[winCombinations[i][1]]==(currentBoard[winCombinations[i][2]])) {
                isWon = true;
            }
          }
        // this is where we swap players if the game has not been won
        if (intCurrentPlayer == 1){
            // swap to player2
            intCurrentPlayer = 2;
        }
        else{
            //swap to player1
            intCurrentPlayer = 1;
        }



        return isWon;

    }

    public int getOpenSquares(){

        int intOpenSquares = 0;

        for (int i = 0; i < 9; i++) {
            if (getSquareValue(i)=='X' | getSquareValue(i)=='O' ){
                // just ignore this area and don't do anything
            } else {
                intOpenSquares++;
            }
         }

        return intOpenSquares;

    }

    public boolean isSquareOpen(int x){

        int SquareLocation = x;

        if (getSquareValue(SquareLocation)=='X' || getSquareValue(SquareLocation)=='O' ){

            return false;
        } else {

            return true;
        }
    }

    public void updateSquare(int x, char c){

        // x is the location that is being chosen
        // c is the character that will be placed in the square
        int boardlocation ;
        int i = 0;
        int intSquareChoice;
        String strMove;
        Boolean blnValidInput;

        do {
            if (i > 0){
                System.out.println("Square is already taken, please choose another.");
                System.out.println("Enter a block to place your " + c + " in: ");
                strMove = input.nextLine();
                intSquareChoice = Integer.valueOf(strMove);
                System.out.println("Player move has been set to : " + intSquareChoice);
                boardlocation = intSquareChoice-1;
                if (isSquareOpen(boardlocation)){
                    blnValidInput = true;
                }
                else{
                    blnValidInput = false;
                }
             }
            else{
                boardlocation = x-1;
                if (isSquareOpen(boardlocation)){
                    blnValidInput = true;
                }
                else{
                    blnValidInput = false;
                }
            }

            i++;
        } while (!blnValidInput);

        board[boardlocation] = c;

        return;


    }

    public char getSquareValue(int x){

        char charValue = board[x];

        return charValue;

    }
}
