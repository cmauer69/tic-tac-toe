package edu.htc.tictactoe.player;

import java.util.Scanner;

/**
 * Created by clifford.mauer on 2/8/2016.
 */
public abstract class Player {
    public String name;
    char gameMarker;
    public int winCounter;
    public boolean isCurrentPlayer;
    Scanner input = new Scanner(System.in);

     public static void main(String[] args){

        Player player = new HumanPlayer();

         player.testGetName(1);
        player.testGetMarker() ;
        player.testGetWinCount(0);
        player.testGetWinCount(1);
    }

    public void testGetName(int intPlayerNumber){
        this.getName(intPlayerNumber);
    }

    public void testGetMarker(){
        this.getMarker();
    }

    public void testGetWinCount(int intStart){
        this.getWinCount(intStart);
    }

    public  void getName(int intPlayerNumber){
        String strName;
        boolean blnValid;

        do {
                System.out.println("Player" + intPlayerNumber +", please enter your name:");
                strName = input.nextLine();
                this.name = strName;
                System.out.println("Player" + intPlayerNumber +" name has been set to : " + this.name);
                if (this.name.length()>1) {
                    blnValid = true;
                } else {
                    System.out.println("Player name has to be at least 2 characters long.  Please enter a player name.");
                    blnValid = false;
                }
         } while (!blnValid);
    }



    public void getMarker(){
        String strMarker;
        char cMarker;

        System.out.println("Enter either an X or an O to choose your game marker: ");
        strMarker = input.nextLine();

        cMarker = (strMarker.charAt(0));
        this.gameMarker = cMarker;
        System.out.println(this.name + "Player marker has been set to : " + this.gameMarker);
    }

    public abstract int getMove(char cMarker);

    public void getWinCount(int intStart){

        if (intStart == 0){
            this.winCounter = 0;
        }
        else{
            this.winCounter++;
        }

        System.out.println("Wins count has been set to : " + this.winCounter);

    }


    public void addWin(){

        this.winCounter++;

        return;

    }
}
