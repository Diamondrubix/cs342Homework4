// Name: Zain Zahran
// Netid: zzahra2

package place;

import artifact.Artifact;
import character.Character;
import game.CleanLineScanner;

/* UNDERWATER ROOM */
// Desc: This room is completely submerged in cold water. 
//		Player/NPC should not stay here too long unless they have a ScubaSuit.
// AmbientFunction: User is required to have a ScubaSuit in their
// 		inventory. Otherwise, -10 hp per turn.
// Extra-Idea: Should ScubaSuit regen hp when user swims in this place?

public class UnderwaterRoom extends Place {
	@Override
	public void ambientFunction(Character c) {
		// Check Player/NPC inventory for a ScubaSuit
		if (c.checkFor("ScubaSuit") ) {
			// Do nothing OR regen HP
			return;
		}
		else {
			c.damage(10);	// Take off 10 hp per turn
		}
	}
}