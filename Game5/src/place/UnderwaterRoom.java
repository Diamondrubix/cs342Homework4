// Name: Zain Zahran
// Netid: zzahra2

package place;

import artifact.Artifact;
import artifact.Armor.*;
import character.Character;
import game.CleanLineScanner;
import java.util.Scanner;

/* UNDERWATER ROOM */
// Desc: This room is completely submerged in cold water. 
//		Player/NPC should not stay here too long unless they have a ScubaSuit.
// AmbientFunction: User is required to have a ScubaSuit in their
// 		inventory. Otherwise, -10 hp per turn.
// Extra-Idea: Should ScubaSuit regen hp when user swims in this place?

public class UnderwaterRoom extends Place {
	public UnderwaterRoom(Scanner sc) {
		super(sc);
	}
	
	@Override
	public void ambientFunction(Character c) {
		// Check Player/NPC inventory for a ScubaSuit
		if (c.armorEquipped(ArmorType.SCUBA) ) {
			// Do nothing OR regen HP
			return;
		}
		else {
			c.damage(10);	// Take off 10 hp per turn
		}
	}
}