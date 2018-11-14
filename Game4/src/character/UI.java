package character;

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

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] args = new StringTokenizer(line);



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
