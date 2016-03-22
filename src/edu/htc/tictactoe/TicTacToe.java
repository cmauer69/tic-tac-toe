package edu.htc.tictactoe;

import edu.htc.tictactoe.player.Player;
import edu.htc.tictactoe.player.HumanPlayer;
import edu.htc.tictactoe.player.ComputerPlayer;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.Scanner;


public class TicTacToe {

    public GameBoard board = new GameBoard();
    private Player player1;
    private Player player2;
    private ComputerPlayer player3;

    public int intOpenSquares = 9;
    private int intTieGames = 0;
    public boolean blnGameIsWon = false;
    public boolean blnGameisTie = false;
    public boolean blnExitLoop;
    Scanner input = new Scanner(System.in);

    char[] currentBoard = new char[9];

  public void playGame() {

    int intNextMove;
    boolean blnGameWon;

    //Play the game
    do {

      if (board.intCurrentPlayer == 1) {
        player1.isCurrentPlayer = true;
        if (player2 == null) {
          player3.isCurrentPlayer = false;
        } else {
          player2.isCurrentPlayer = false;
        }
      } else {
        if (player2 == null) {
          player3.isCurrentPlayer = true;
          player1.isCurrentPlayer = false;
        } else {
          player2.isCurrentPlayer = true;
          player1.isCurrentPlayer = false;
        }
      }

      if (player1.isCurrentPlayer) {
        intNextMove = player1.getMove('X');
        board.updateSquare(intNextMove, 'X');
        } else {
        if (player2 == null) {
          //Computer is playing ..
          intNextMove = player3.getMove(board, 'O');
          board.updateSquare(intNextMove, 'O');
        } else {
          intNextMove = player2.getMove('O');
          board.updateSquare(intNextMove, 'O');
        }
      }
      board.display();
      intOpenSquares = board.getOpenSquares();
      System.out.println("There are " + intOpenSquares + " open squares.");
        if (board.isGameWon(board.board)) {
          blnExitLoop = true;
          blnGameIsWon = true;
        } else { // Game is a tie
            if (intOpenSquares == 0) {
                blnExitLoop = true;
                blnGameisTie = true;
            }
        }

    } while (!blnExitLoop);

    // if the intOpenSquares variable is greater than 0, the game has been either
    // won or lost.

    if (blnGameIsWon) {
      if (player1.isCurrentPlayer) {
        player1.addWin();
        System.out.println("Congratulations, " + player1.name + " you win the game!");
      } else {
      if (player2 == null) {
        //player3 is the winner. So add a win to the WinCounter..
        player3.setWinCounter(1);
        System.out.println("Congratulations, " + player3.name + " you win the game!");
      } else {
        if (player2 != null){
          player2.addWin();
          System.out.println("Congratulations, " + player2.name + " you win the game!");
        }
      }}

    } else {
      if (blnGameisTie)
          intTieGames++;
        System.out.println("Sorry, game has ended in a tie.");
    }
    ResetGame();
  }

  public void initGame(){
      //Update the tictactoe class to ask if there are two players
      System.out.println("Hi! My name is Hal.");
      System.out.println("How many players will be playing tictactoe? 1? or 2?");
      Integer IntAnswer;
      Boolean blnValid = false;
      String strAnswer = "";
      IntAnswer = 0;
      Integer intloopcount = 0;


      do {
          strAnswer = input.nextLine();
          if(!isInteger(strAnswer)){
              System.out.println("Please enter a number..");
          } else {
              IntAnswer = Integer.valueOf(strAnswer);
          }
          if (IntAnswer > 0 & IntAnswer < 3){
              board.intGameLevel = IntAnswer;
              blnValid = true;
          } else {
              System.out.println("Number of players can only be 1 or 2.  Please choose again.");
              blnValid = false;
          }
          intloopcount++;
      } while (!blnValid);






      if (IntAnswer == 1){
        // Create the human player and then get the name for the human
        player1 = new HumanPlayer();
        player1.getName(1);
        // set the name for the computer to "Hal"
        player3 = new ComputerPlayer();
        player3.setName("HAL");
        player2 = null;
        System.out.println("OK, the computer will be playing the game with you!");
        // Now lets determine the level of difficulty for playing the game
        //     1 -- Simple
        //     2 -- Easy
        //     3 -- Medium
        //     4 -- Hard

        System.out.println("Now lets determine the level of difficulty for this game!");
        System.out.println("Level 1 is Simple");
        System.out.println("Level 2 is Easy");
        System.out.println("Level 3 is Medium");
        System.out.println("Level 4 is Hard");

        strAnswer = "";
        IntAnswer = 0;
        intloopcount = 0;

        do {
            strAnswer = input.nextLine();
            if(!isInteger(strAnswer)){
                if (intloopcount>0){
                    System.out.println("Please enter a number..");
                }
            } else {
                IntAnswer = Integer.valueOf(strAnswer);
            }
            if (IntAnswer > 0 & IntAnswer < 5){
                board.intGameLevel = IntAnswer;
                blnValid = true;
            } else {
                if (intloopcount>0){
                    System.out.println("Level of Difficulty is a number from 1 to 4.  Please choose again.");
                }
                 blnValid = false;
            }
            intloopcount++;
        } while (!blnValid);

    } else {
          player1 = new HumanPlayer();
          player2 = new HumanPlayer();
          player1.getName(1);
          player2.getName(2);
    }
      currentBoard = board.board;
      board.display();
      board.intCurrentPlayer = 1;
      player1.isCurrentPlayer = true;
      if (player2 != null){
          player2.isCurrentPlayer = false;
      }


  }

  public void gameReport(){
    System.out.println("         Game Winner Report                   ");
    System.out.println("---------------------------------------");
    if (player1.winCounter == 1){
      System.out.println(player1.name + " has won " + player1.winCounter + " time.");
    }
    else{
      System.out.println(player1.name + " has won " + player1.winCounter + " times.");
    }
    if (player2 != null) {
      if (player2.winCounter == 1) {
        System.out.println(player2.name + " has won " + player2.winCounter + " time.");
      } else {
        System.out.println(player2.name + " has won " + player2.winCounter + " times.");
      }
    }
    if (player2 == null){
      if (player3.winCounter == 1){
        System.out.println(player3.name + " has won " + player3.winCounter + " time.");
      }
      else {
        System.out.println(player3.name + " has won " + player3.winCounter + " times.");
      }
    }
      if (intTieGames > 0){
          if (intTieGames == 1){
            System.out.println("The game ended in a tie 1 time.");}
          else {
              System.out.println("The game ended in a tie " + intTieGames + " times.");
          }
      }
   }


    public void ResetGame(){
    // reset the board back to its integer values
    board.board = new char[] {'1','2','3','4','5','6','7','8','9'};
    currentBoard = board.board;
    intOpenSquares = 9;
    blnExitLoop = false;
    blnGameIsWon = false;
    blnGameisTie = false;
  }

    public boolean isInteger( String input )
    {
        try
        {
            Integer.parseInt( input );
            return true;
        }
        catch ( Exception exc )
        {
            return false;
        }
    }

}


