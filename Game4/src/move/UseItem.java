/* Author: Alexander Oey (NetID: aoey2) */
package move;

import character.Character;
import place.Place;
import move.Move;
import artifact.Artifact;

/**
 * This class encapsulates a command to use an item in inventory.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class UseItem extends Move {
	private Character character;
	private  Place currentPlace;
	private String artifact;
	
	/**
	 * Constructs a use item command.
	 *
	 * @param c character exiting the game
	 * @param p current location of the character
	 * @param artifactName name of item to be used
	 */
	public UseItem(Character c, Place p, String itemName) {
		this.character = c;
		this.currentPlace = p;
		this.artifact = itemName;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	public void execute() {
		// Do nothing.
		Artifact a = character.strToArtifact(artifact);
		if(a!=null) {
			a.use(character, currentPlace);
		}else{
			System.out.println("coudn't use artifact");
		}
	}
}