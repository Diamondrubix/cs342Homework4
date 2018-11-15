package character;

import game.KeyboardScanner;
import move.*;
import place.Place;

import java.util.Scanner;
        import java.util.StringTokenizer;

/*
handles user input to control each player instance's movements
 */

public class UI implements DecisionMaker {
    /*
    gets the move information for players
     */
    @Override
    public Move getMove(Character c, Place location) {
        System.out.print("\n"+c.name+": ");

        //KeyboardScanner sc = KeyboardScanner.getKeyboardScanner();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        input = input.toUpperCase().trim();

        String[] args = input.split(" ");

        //Quit.
        if (input.equals("QUIT") || input.equals("Q") ||
                input.equals("EXIT")) {
            return new Exit(c,location);
        }
        //Redisplay current place.
        else if (input.equals("LOOK")) {
            return new Look(c,location);
        }
        //Access inventory.
        else if (input.equals("INVE") || input.equals("INVENTORY")) {
            return new ShowInventory(c);
        }
        //Pick up artifact.
        else if (input.contains("GET")) {
            String[] words = input.split("\\s+");
            String artifactName = "";
            // Append tokenized names.
            for (int i = 1; i < words.length; ++i) {
                artifactName += words[i] + " ";
            }
            return new GetItem(c,location, artifactName.trim());
        }
        //Drop or use artifact.
        else if (input.contains("DROP")) {
            String[] words = input.split("\\s+");
            // Append tokenized names.
            String artifactName = "";
            for (int i = 1; i < words.length; ++i) {
                artifactName += words[i] + " ";
            }
            return new DropItem(c,location, artifactName.trim());
        }
        else if (input.contains("USE")) {
            String[] words = input.split("\\s+");
            // Append tokenized names.
            String artifactName = "";
            for (int i = 1; i < words.length; ++i) {
                artifactName += words[i] + " ";
            }
            return new UseItem(c,location, artifactName.trim());
        }
        //Move to other rooms.
        else {
            return new Go(c,location, args);
        }



    }


}
