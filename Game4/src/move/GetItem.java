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
		this.artifactName = artifactName;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	public void execute() {
		Artifact a = Place.getArtifact(artifactName);
		if (a != null) {
			character.addArtifact(a);
			Place.removeArtifact(a);
		}
	}
}