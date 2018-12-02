// Name: Zain Zahran
// Netid: zzahra2

package place;

import Network.Network;
import artifact.Artifact;
import character.Character;
import game.CleanLineScanner;

import java.util.Scanner;

/* DARK ROOM */
// Desc: It's really dark in this room. Player can't see a thing...
// 		user should have a flashlight to navigate and see what
//		exists in this room. Drawbacks occur if no light exists..
// AmbientFunction: User should have a flashlight in their inventory
//					to see what exists in this room. If no flashlight is present
//					user will NOT get a display of the current artifacts, inventory,
//					characters, or directions except the way they came from.
// Extra-Idea: User gets lost by being in this room and gets placed back to the start
//				OR user gets put in a random place.

public class DarkRoom extends Place {
	public DarkRoom(Scanner sc) {
		super(sc);
	}
	
	@Override
	public void ambientFunction(Character c) {
		// Check user inventory for a flash light.
		if (c.checkFor("flashlight") ) {
			super.print();	// Flashlight exists
		}
		else {
			this.print();	// Flashlight does NOT exist
		}
		return;
	}
	// Overwrite print to print nothing but darkness...
	@Override
	public void print() {
		//System.out.println("It's too dark in here to see anything...");
		Network.netPrintln("It's too dark in here to see anything...");
	}
}