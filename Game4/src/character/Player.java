package character;

import artifact.Artifact;
import artifact.Move;
import place.Place;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Player extends Character{
    private UI ui = new UI();


    public Player(Scanner sc){
        super(sc);
        arePlayers = true;
    }

    public Player(int id, String name, String desc, int placeID) {
        super(id, name, desc,placeID);
        arePlayers = true;
    }

    @Override
    public boolean makeMove() {
        System.out.print("\n"+name+": ");
        Move move = ui.getMove(this, location);
        Move.MoveType m = move.type;
        StringTokenizer st = new StringTokenizer(move.args);
        String text = st.nextToken();
        switch(m){
            case get:
                if (st.hasMoreTokens()) {
                    text = st.nextToken();
                    while (st.hasMoreTokens()) {
                        text = text + " " + st.nextToken();
                    }
                    text = text.toLowerCase();
                    Artifact n = location.PickUpArtifact(text);
                    if (n != null) {
                        inventory.add(n);
                        System.out.println("Picked up " + text);
                    } else {
                        System.out.println("could not pick up " + text);
                    }
                } else {
                    System.out.println("could not pick up");
                }
                break;
            case drop:
                if (st.hasMoreTokens()) {
                    text = st.nextToken();
                    while (st.hasMoreTokens()) {
                        text = text + " " + st.nextToken();
                    }
                    text = text.toLowerCase();
                    if (drop(text)) {
                        System.out.println("droped " + text);
                    } else {
                        System.out.println("failed to drop " + text);
                    }
                } else {
                    System.out.println("failed to drop");
                }
                break;
            case look:
                location.display();
                break;
            case wait:
                System.out.println(name+" is waiting");
                break;
            case inventory:
                viewInventory();
                break;
            case use:
                if (st.hasMoreTokens()) {
                    text = st.nextToken();
                    while (st.hasMoreTokens()) {
                        text = text + " " + st.nextToken();
                    }
                    text = text.toLowerCase();

                    use(text);
                } else {
                    System.out.println("use what?");
                }
                break;
            case go:
                text = text.toLowerCase();
                if(text.equals("go")){
                    text = st.nextToken();
                }
                //move
                Place temp = location.followDirection(text);
                if (temp == null) {
                    System.out.println("you reached an exit, leaving");
                    System.exit(0);
                }
                if (!temp.equals(location)) {
                    moveCharacter(temp);
                    System.out.println("moved to " + location.name());
                    location.display();
                } else {
                    System.out.println("failed to move ");
                }
                if (location.isExit()) {

                    break;
                }
                break;
            case exit:
                System.out.println(name+" choose to end the game. Game is exiting now");
                System.exit(0);
            default:
                System.out.println("quit game maybe?");
        }

    }

}
