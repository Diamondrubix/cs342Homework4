// Name: Zain Zahran
// Netid: zzahra2

package place;

import artifact.Artifact;
import character.Character;
import game.CleanLineScanner;

/* FOUNTAIN ROOM */
// Desc: This room is very colorful and bright. The light shines
//		upon a crystal clear water fountain. The user heals while standing
//		nearby it.
// AmbientFunction: User gets healed 10hp a turn while staying in this room.

public class FountainRoom extends Place {
	@Override
	public void ambientFunction(Character c) {
		c.heal(10);	// Heal 10 hp per turn in this room
	}
}