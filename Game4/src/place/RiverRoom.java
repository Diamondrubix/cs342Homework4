// Name: Zain Zahran
// Netid: zzahra2

package place;

import character.Character;
import artifact.Artifact;
import game.CleanLineScanner;

/* RIVER ROOM */
// Desc: You have to cross a river or travel down it. Watch out for jumping 
//		fish, crocodiles and the occasional rapids and waterfalls. Don't fall in!
//		This is a watery room that can drag you faster than you could imagine.
// AmbientFunction: Checks if player has a raft in their inventory in order to
//		cross the river safely
// Extra-idea: User drowns or gets attacked when/if attempting to cross the river

public class RiverRoom extends Place {
	@Override
	public void ambientFunction(Character c) {
		// Check user inventory for a raft.
		if (c.checkFor("raft") ) {
			// User takes no damage and can cross safely
		}
		else {
			c.damage(10);
		}
		return;
	}
}