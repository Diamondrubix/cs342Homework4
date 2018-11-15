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

        KeyboardScanner sc = KeyboardScanner.getKeyboardScanner();

        String input = sc.nextLine().toUpperCase().trim();

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
            return new Inve(c, "");
        }
        //Pick up artifact.
        else if (input.contains("GET")) {
            String[] words = input.split("\\s+");
            String artifactName = "";
            // Append tokenized names.
            for (int i = 1; i < words.length; ++i) {
                artifactName += words[i] + " ";
            }
            return new Get(c, artifactName.trim());
        }
        //Drop or use artifact.
        else if (input.contains("DROP")) {
            String[] words = input.split("\\s+");
            // Append tokenized names.
            String artifactName = "";
            for (int i = 1; i < words.length; ++i) {
                artifactName += words[i] + " ";
            }
            return new Drop(c, artifactName.trim());
        }
        else if (input.contains("USE")) {
            String[] words = input.split("\\s+");
            // Append tokenized names.
            String artifactName = "";
            for (int i = 1; i < words.length; ++i) {
                artifactName += words[i] + " ";
            }
            return new Use(c, artifactName.trim());
        }
        //Move to other rooms.
        else {
            return new Go(c,location, args);
        }



    }


    private Move _move(String text){
        text = text.toLowerCase();
        String args = text;
        StringTokenizer st = new StringTokenizer(text);
        text = st.nextToken();


        switch (text) {
            case "get":
                type = MoveType.get;
                break;
            case "drop":
                type = MoveType.drop;
                break;
            case "look":
                type = MoveType.look;
                break;
            case "inventory":

            case "inve":
                type = MoveType.inventory;
                break;
            case "use":
                type = MoveType.use;
                break;
            case "wait":
                type = MoveType.wait;
                break;
            case "exit":

            case "quit":
                type = MoveType.exit;
                break;
            case "go":
                type = MoveType.go;
                break;
            default:
                if(Direction.dirType.isDir(text)){
                    type = MoveType.go;
                }else{

                    type = MoveType.invalid;
                }
        }
    }

}
