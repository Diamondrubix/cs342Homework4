package character;


import Network.Network;
import sun.nio.ch.Net;

import java.util.Scanner;

/**
 * The player class. has its own make move  that uses a UI to make moves based on user input
 *
 * @author Adam Arato
 */

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
        //SOystem.out.print("\n"+name+": ");
        Network.netPrintln("\n"+name+": ");
        ui.getMove(this, location).execute();
				super.makeMove();
        return true;
    }

}
