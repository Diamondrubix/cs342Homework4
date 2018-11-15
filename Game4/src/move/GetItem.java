/* Author: Alexander Oey (NetID: aoey2) */
package move;

import character.Character;
import place.Place;
import move.Move;
import artifact.Artifact;

/**
 * This class encapsulates a command to get an item in the current Place.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class GetItem extends Move {
	private final Character character;
	private final Place currentPlace;
	private final String artifactName;
	
	/**
	 * Constructs a get item command.
	 *
	 * @param c character exiting the game
	 * @param p current location of the character
	 * @param artifactName name of item to be picked up
	 */
	public GetItem(Character c, Place p, String artifactName) {
		this.character = c;
		this.currentPlace = p;
		this.artifactName = artifactName.toLowerCase();
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	public void execute() {
		Artifact a = currentPlace.removeArtifact(artifactName);
		if(a==null){
			System.out.println("no artifacts to pick up/coudn't pick it up");
		}else {
			character.addArtifact(a);
		}
	}
}