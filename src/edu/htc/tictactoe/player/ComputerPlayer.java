package edu.htc.tictactoe.player;

import edu.htc.tictactoe.GameBoard;
import edu.htc.tictactoe.strategy.*;

/**
 * Created by clifford.mauer on 2/8/2016.
 */
public class ComputerPlayer {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGameMarker() {
        return gameMarker;
    }

    public void setGameMarker(char gameMarker) {
        this.gameMarker = gameMarker;
    }

    public int getWinCounter() {
        return winCounter;
    }

    public void setWinCounter(int winCounter) {
        this.winCounter = winCounter++;
    }

    public String name;
    char gameMarker;
    public int winCounter;
    public boolean isCurrentPlayer;

     public int getBestMove(GameBoard board, char gameMarker) {
            int IntReturnMove = 0;
         if (board.intGameLevel==1) {
             RandomMoveStrategy randomMove = new RandomMoveStrategy();
             IntReturnMove = randomMove.getBestMove(board, gameMarker);
         }
         if (board.intGameLevel==2) {
             BestOpenMoveStrategy bestOpenMove = new BestOpenMoveStrategy() {
                 @Override
                 public int getBestOpenMove(GameBoard board) {
                     return super.getBestOpenMove(board);
                 }
             };
             IntReturnMove = bestOpenMove.getBestOpenMove(board);
         }
         if (board.intGameLevel==3){
             BlockWinStrategy blockWinMove = new BlockWinStrategy();
             IntReturnMove = blockWinMove.findWinFor(gameMarker, board);
         }
         if (board.intGameLevel==4){
             GoForWinStrategy goForWinMove = new GoForWinStrategy();
             IntReturnMove = goForWinMove.findWinFor(gameMarker, board);
         }
         return IntReturnMove;
     }

    public int getMove(GameBoard board, char gameMarker){

        int IntReturnMove;

        IntReturnMove = getBestMove (board,gameMarker);

        System.out.println(this.name+"'s move has been set to : " + IntReturnMove);

        return IntReturnMove;


    }
}
