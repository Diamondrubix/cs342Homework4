/* Author: Alexander Oey (NetID: aoey2) */
package move;

import Network.Network;
import character.Character;
import place.Place;
import move.Move;
import artifact.Artifact;
import artifact.Armor;

/**
 * This class encapsulates a command to equip an artifact in inventory.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class Equip extends Move {
	private Character character;
	private  Place currentPlace;
	private String artifact;
	
	/**
	 * Constructs a use item command.
	 *
	 * @param c character exiting the game
	 * @param p current location of the character
	 * @param itemName name of item to be used
	 */
	public Equip(Character c, Place p, String itemName) {
		this.character = c;
		this.currentPlace = p;
		this.artifact = itemName;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	public void execute() {
		Artifact a = character.strToArtifact(artifact);
		if(a!=null) {
			character.equipArtifact(a);
		}else{
			//System.out.println("couldn't use artifact " + artifact);
			Character.println("couldn't equip artifact " + artifact);
		}
	}
}