// Zain Zahran
// Netid: zzahra2

import java.util.*;

// PLAYER - Inherits from character
public class Player extends Character {
	public Player(int ID, String name, String description, Place place) {
		super(ID, name, description, place);	// Inherit from parent class
		decider = new UI();
		nPlayers++;
	}
	public Player(Scanner s, int version, int placeID) {
		super(s, version, placeID);				// Inherit from parent class
		// decider = new playerInterface();
		decider = new UI();
		nPlayers++;
	}
}