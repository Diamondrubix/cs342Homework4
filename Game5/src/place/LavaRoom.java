// Name: Zain Zahran
// Netid: zzahra2

package place;

import character.Character;
import artifact.Artifact;
import artifact.Armor.*;
import game.CleanLineScanner;

import java.util.Scanner;

/* LAVA ROOM */
// Desc: This room is extremely hot. Player/NPC should not
// 		stay here too long unless they have a LavaSuit.
// AmbientFunction: User is required to have a LavaSuit in their
// 		inventory. Otherwise, they will take 10 damage per turn (c.damage(10);).
//		(0/100 health results in death...)
// Extra-Idea: Should LavaSuit regen hp when user stands in this place?

public class LavaRoom extends Place {
	public LavaRoom(Scanner sc) {
		super(sc);
	}
	
	@Override
	public void ambientFunction(Character c) {
		// Check Player/NPC inventory for a LavaSuit
		if (c.isEquipped(ArmorType.LAVA) ) {
			// Do nothing OR regen HP
			return;
		}
		else {
			c.damage(10);	// Take off 10 hp per turn
		}
	}
}