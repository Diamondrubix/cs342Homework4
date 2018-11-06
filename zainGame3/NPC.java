// Zain Zahran
// Netid: zzahra2

import java.util.*;

// NPC - Inherits from character
public class NPC extends Character {
	public NPC(int ID, String name, String description, Place place) {
		super(ID, name, description, place);	// Inherit from parent class
		decider = new AI();
	}
	public NPC(Scanner s, int version, int placeID) {
		super(s, version, placeID);				// Inherit from parent class
		decider = new AI();
	}
}