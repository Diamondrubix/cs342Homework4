package character;

import move.Go;
import move.*;
import place.Place;

import java.util.Scanner;

/**
 * Ai is the DecisionMaker Class that takes care of Movements of the NPC
 *
 * @author Adam Arato
 */


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
            return new Go(c, location, location.getDirections());
        }else if(choice == 2){
            return new GetItem(c,location,location.getRandomArt());
        }else if(choice == 3) {
            return new DropItem(c,location,location.getRandomArt());
        }else if(choice == 4){
            return new UseItem(c,location,location.getRandomArt());
        }else{
            System.out.println("ERROR: getmove of ai class else statement called.");
            return null;
        }


    }
}
