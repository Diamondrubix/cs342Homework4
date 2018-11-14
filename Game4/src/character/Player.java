package character;


import java.util.Scanner;

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
        ui.getMove(this, location).execute();

        return true;
    }

}
