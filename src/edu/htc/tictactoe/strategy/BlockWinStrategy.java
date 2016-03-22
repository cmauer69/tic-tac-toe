package edu.htc.tictactoe.strategy;

import edu.htc.tictactoe.GameBoard;

/**
 * Created by clifford.mauer on 2/8/2016.
 */
public class BlockWinStrategy {

    int intBlockCount = 0;
    char playerMarker;


    private int[][] winCombinations = new int[][] {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //vertical wins
            {0, 4, 8}, {2, 4, 6}             //diagonal wins
    };

    public static void main(String[] args){
        testBlockWinStrategy();
    }
        //**new char[]{'1','X','3','4','O','6','X','8','O'}

    public static void testBlockWinStrategy(){
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("Testing getBestOpenMove method");
        GameBoard board = new GameBoard();
        board.updateSquare(1,'X');
        board.updateSquare(2,'X');
        board.updateSquare(5,'O');
        board.updateSquare(7,'X');
        char gameMarker = 'X';
        int blockingSquareInt = 0 ;

        BlockWinStrategy BlockWinStrategy = new BlockWinStrategy();

        blockingSquareInt = BlockWinStrategy.findWinFor(gameMarker,board);

        System.out.println("Blocking square is " + blockingSquareInt);
    }

    public int findWinFor(char marker, GameBoard board){
        intBlockCount = 0;
        int intWinBlock = 0;
        boolean blnCanBlock = false;
        if (marker == 'O'){
            marker = 'X';
        }
        else {
            marker = 'O';
        }

        //** First look for an opponent win and block the first one found
        //** if any of the possible combinations have two squares filled, block the win
        for (int i = 0; i < 9 ; i++) {
            System.out.println("Square value " + i + " is " + board.getSquareValue(i) + ".");
        }
        for (int i = 0; i < 8 ; i++) {
            // check each winning combination
            //--------------------------------------------------------------------------
            if (board.getSquareValue(winCombinations[i][0]) == marker & board.getSquareValue(winCombinations[i][1]) == marker) {
                //block the win by putting the O into square 3
                //we can only block if the square is open
                if (board.isSquareOpen(winCombinations[i][2])) {
                    intWinBlock = winCombinations[i][2];
                    blnCanBlock = true;
                }
            }
            if (board.getSquareValue(winCombinations[i][1]) == marker & board.getSquareValue(winCombinations[i][2]) == marker) {
                //block the win by putting the O into square 1
                if (board.isSquareOpen(winCombinations[i][0])) {
                    intWinBlock = winCombinations[i][0];
                    blnCanBlock = true;
                }
            }
            if (board.getSquareValue(winCombinations[i][0]) == marker & board.getSquareValue(winCombinations[i][2]) == marker) {
                //block the win by putting the O into square 2
                if (board.isSquareOpen(winCombinations[i][1])) {
                    intWinBlock = winCombinations[i][1];
                    blnCanBlock = true;
                }
            }
        }

        if (blnCanBlock == false){
            System.out.println("There is no block so pick a random square with the BestOpenMove Strategy");
            BestOpenMoveStrategy bestOpenMove = new BestOpenMoveStrategy() {
                @Override
                public int getBestOpenMove(GameBoard board) {
                    return super.getBestOpenMove(board);
                }
            };
            intWinBlock = bestOpenMove.getBestOpenMove(board);

        }
        //we need to adjust the array number to a board block number
        intWinBlock++;
        //** Second if no opponent win option is found, then take the best open move using
        // the rules from the BestOpenMoveStrategy
        return intWinBlock;
    }

}
