// Name: Zain Zahran
// Netid: zzahra2

package place;

import artifact.Artifact;
import game.CleanLineScanner;

/* LAVA ROOM */
// Desc: This room is extremely hot. Player/NPC should not
// 		stay here too long unless they have a LavaSuit.
// AmbientFunction: User is required to have a LavaSuit in their
// 		inventory. Otherwise, they will take 10 damage per turn (c.damage(10);).
//		(0/100 health results in death...)
// Extra-Idea: Should LavaSuit regen hp when user stands in this place?

public class LavaRoom extends Place {
	@Override
	public void ambientFunction(Character c) {
		// Check Player/NPC inventory for a LavaSuit
		if (c.checkFor("LavaSuit") ) {
			// Do nothing OR regen HP
			return;
		}
		else {
			c.damage(10);	// Take off 10 hp per turn
		}
	}
}