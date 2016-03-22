package edu.htc.tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        char cAnswer;
        String strAnswer = "";
        game.initGame();

        do {
         //X is player 1 and this player will play first

            if (strAnswer == ""){
                game.playGame();
            }
            else {
                game.board.display();
                game.playGame();
            }

         System.out.println("Would you like to play again? Enter Y for yes or N for No. ");
        strAnswer = input.nextLine();
        //cAnswer = (strAnswer.charAt(0));

        } while (strAnswer.equalsIgnoreCase("Y"));

        //Play is finished so now we can display the Game Report

        System.out.println("Thanks for playing TICTACTOE by Clifford J Mauer!!!!");
        System.out.println("====================================================");
        game.gameReport();

    }
}
