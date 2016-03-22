package edu.htc.tictactoe.strategy;

import edu.htc.tictactoe.GameBoard;

/**
 * Created by clifford.mauer on 2/8/2016.
 */
public class GoForWinStrategy {

    int intBlockCount = 0;

    char playerMarker;

    private int[][] winCombinations = new int[][] {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //vertical wins
            {0, 4, 8}, {2, 4, 6}             //diagonal wins
    };

    public int findWinFor(char marker, GameBoard board){

     // first look for a computer win
     intBlockCount = 0;
     int intWinMove = 0;
     boolean blnCanWin = false;

     // ** First look for a win and take the first one found
     // ** if any of the possible combinations have two squares filled, block the win
     for (int i = 0; i < 9 ; i++) {
        System.out.println("Square value " + i + " is " + board.getSquareValue(i) + ".");
     }
    for (int i = 0; i < 8 ; i++) {
        // check each winning combination
        //--------------------------------------------------------------------------
        if (board.getSquareValue(winCombinations[0][0]) == marker & board.getSquareValue(winCombinations[0][1]) == marker) {
            //take the win by putting the O into square 3
            //we can only take the win if the square is open
            if (board.isSquareOpen(winCombinations[0][2])) {
                intWinMove = winCombinations[0][2];
                blnCanWin = true;
            }
        }
        if (board.getSquareValue(winCombinations[0][1]) == marker & board.getSquareValue(winCombinations[0][2]) == marker) {
            //take the win by putting the O into square 1
            if (board.isSquareOpen(winCombinations[0][0])) {
                intWinMove = winCombinations[0][0];
                blnCanWin = true;
            }
        }
        if (board.getSquareValue(winCombinations[0][0]) == marker & board.getSquareValue(winCombinations[0][2]) == marker) {
            //take the win by putting the O into square 2
            if (board.isSquareOpen(winCombinations[0][1])) {
                intWinMove = winCombinations[0][1];
                blnCanWin = true;
            }
        }
    }

        // second block a win
        //** Second if no opponent win option is found, then take the best open move using
        // the rules from the BestOpenMoveStrategy
        // third take the best open move

     if (blnCanWin == false){
        System.out.println("There is no win so check for a block");
        BlockWinStrategy blockMove = new BlockWinStrategy() {
            public int getBestOpenMove(edu.htc.tictactoe.GameBoard board) {
                return super.findWinFor(marker, board);
            }
        };
        intWinMove = blockMove.findWinFor(marker,board);
         intWinMove--;
     }
     //we need to adjust the array number to a board block number
     intWinMove++;
      return intWinMove;
    }
}
