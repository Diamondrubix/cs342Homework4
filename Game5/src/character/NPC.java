package character;

import Network.Network;
import artifact.Artifact;
import move.Move;
import ui.IO;

import java.util.Scanner;

public class NPC extends Character{
    private boolean isAlive = true;
    private AI ai = new AI();

    public NPC(Scanner sc){
        super(sc);
    }

    public NPC(int id, String name, String desc, int placeID) {
        super(id,name,desc,placeID);
    }


    @Override
    public boolean makeMove() {
        //System.out.print("\n"+name+" makes a move\n");
        Character.println("\n"+name+" makes a move\n");
        Move m = ai.getMove(this,location, io);
        m.execute();
				super.makeMove();

        return true;
    }

    /*
helper function to drop one object
 */
    private void randomDrop(){
        Artifact a = randomArtifactFromInventory();
        if(a == null){
            //System.out.println(name+" tried to drop something but didn't have anything");
            Character.println(name+" tried to drop something but didn't have anything");
        }else{
            location.addArtifact(a);
            inventory.remove(a);
        }
    }
    /*
    helper function to use a random object
     */
    private void randomUse(){
        Artifact a = randomArtifactFromInventory();
        if(a ==null){
            //System.out.println(name+" tried to use and object but found their inventory empty");
            Character.println(name+" tried to use and object but found their inventory empty");
        }else{
            a.use(this,location);
        }


    }

}
