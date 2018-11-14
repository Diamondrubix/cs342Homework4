package character;

import artifact.Move;
import move.Go;
import move.Move;
import place.Place;

import java.util.Scanner;

/*
this is the decisionMaker for the NPC class. Likely to be extended for different AI controlled characters
in the future
 */

public class AI implements DecisionMaker{
    /*
gets the move information for NPC Characters
 */

    @Override
    public Move getMove(Character c, Place location) {
        int max = 4;
        int min = 0;
        int choice = (int) ((Math.random() * ((max - min) + 1)) + min);
        if(choice <2){
            return new Go();
        }else if(choice == 2){
            return new Move("get");
        }else if(choice == 3) {
            return new Move("drop");
        }else if(choice == 4){
            return new Move("use");
        }else{
            System.out.println("ERROR: getmove of ai class else statement called.");
            return new Move("invalid");
        }

    }
}
