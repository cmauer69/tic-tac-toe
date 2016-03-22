package edu.htc.tictactoe.player;

/**
 * Created by clifford.mauer on 2/8/2016.
 */
public class HumanPlayer extends Player {

    public int getMove(char cMarker){
        String strMove;
        int intSquareChoice = 0;
        Boolean blnValid = false;

        System.out.println(this.name+", please enter a block to place your " + cMarker + " in: ");

        do {
            strMove = input.nextLine();
            if(!isInteger(strMove)){
                System.out.println("Please enter a number..");
            } else {
                intSquareChoice = Integer.valueOf(strMove);
            }
            if (intSquareChoice > 0 & intSquareChoice < 10){
                blnValid = true;
            } else {
                System.out.println("Player move can only be numbers 1 through 9.  Please choose again.");
                blnValid = false;
            }
        } while (!blnValid);


        System.out.println(this.name+"'s move has been set to : " + intSquareChoice);

        return intSquareChoice;

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
