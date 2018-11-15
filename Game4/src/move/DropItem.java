/* Author: Alexander Oey (NetID: aoey2) */
package move;

import Network.Network;
import character.Character;
import place.Place;
import move.Move;
import artifact.Artifact;

/**
 * This class encapsulates a command to drop an item in inventory.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class DropItem extends Move {
	private final Character character;
	private final Place currentPlace;
	private final String artifactName;
	
	/**
	 * Constructs a drop item command.
	 *
	 * @param c character exiting the game
	 * @param p current location of the character
	 * @param artifactName name of item to be dropped
	 */
	public DropItem(Character c, Place p, String artifactName) {
		this.character = c;
		this.currentPlace = p;
		this.artifactName = artifactName;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	public void execute() {
		character.drop(artifactName);
		//System.out.println("Dropped: " + artifactName);
		Network.netPrintln("Dropped: " + artifactName.toLowerCase());
	}
}