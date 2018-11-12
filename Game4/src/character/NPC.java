package character;

import artifact.Artifact;
import artifact.Move;

import java.util.Scanner;

public class NPC extends Character{
    private static final Object Direction = ;
    private boolean isAlive = true;

    public NPC(Scanner sc){
        super(sc);
    }

    public NPC(int id, String name, String desc, int placeID) {
        super(id,name,desc,placeID);
    }


    @Override
    public void makeMove() {
        if (isAlive) {
            //System.out.println("\n"+name+" is moving");
            Move move = ai.getMove(this, location);
            Move.MoveType m = move.type;
            String text = "";
            switch (m) {
                case go:
                    boolean followed = false;
                    int count = 0;
                    while (count < 50 && !followed) {
                        String dir = String.valueOf(Direction.dirType.randomDirection());
                        Place temp = location.followDirection(dir);
                        if (temp == null) {
                            System.out.println(name + "has reached an exit and is leaving");
                            isAlive = false;
                            //System.exit(0);
                            break;
                        }
                        if(temp == null){
                            System.out.println(name + " did not move");
                            break;
                        }
                        if (!temp.equals(location)) {
                            moveCharacter(temp);
                            System.out.println("\n"+name + " moved to " + location.name());
                            followed = true;
                        } else {
                            count++;
                        }
                    }
                    if (!followed) {
                        System.out.println(name + " did nothing");
                    }
                    break;
                case get:
                    Artifact a = location.getRandomArtifact();
                    if(a!=null){
                        inventory.add(a);
                        System.out.println(name+" picked up "+a.getName());
                    }else{
                        System.out.println(name+" found nothing to pick up");
                    }
                    break;
                case drop:
                    randomDrop();
                    break;
                case use:
                    randomUse();
                    break;
                default:
                    System.out.println("default case for " + name);

            }
        }
    }

    /*
helper function to drop one object
 */
    private void randomDrop(){
        Artifact a = randomArtifactFromInventory();
        if(a == null){
            System.out.println(name+" tried to drop something but didn't have anything");
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
            System.out.println(name+" tried to use and object but found their inventory empty");
        }else{
            a.use();
        }


    }

}
