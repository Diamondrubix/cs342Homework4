// Name: Zain Zahran
// Netid: zzahra2

package place;

import character.Character;
import artifact.Artifact;
import artifact.Armor.*;
import game.CleanLineScanner;
import java.util.Scanner;

/* SPACE ROOM */
// Desc: There's no oxygen in this room. Player cannot breathe...
//		User should wear a space suit in order to pass without harm.
// AmbientFunction: User should have a space suit in their inventory.
//					Function will check for this. If it doesn't exist
//					Then the user will take -25 damage per turn. (deadly)

public class SpaceRoom extends Place {
	public SpaceRoom(Scanner sc) {
		super(sc);
	}
	
	@Override
	public void ambientFunction(Character c) {
		// Check user inventory for a space suit.
		if (c.isEquipped(ArmorType.SPACE) ) {
			// User takes no damage
		}
		else {
			c.damage(25);
		}
		return;
	}
}