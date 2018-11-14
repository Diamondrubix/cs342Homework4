/* Author: Alexander Oey (NetID: aoey2) */
package move;

import character.Character;
import place.Place;
import move.Move;

/**
 * This class encapsulates a command to redisplay the information about current place.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class Look extends Move {
	private final Character character;
	private final Place currentPlace;
	
	/**
	 * Constructs a look around command.
	 *
	 * @param c character exiting the game
	 * @param p current location of the character
	 */
	public Look(Character c, Place p) {
		this.character = c;
		this.currentPlace = p;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	public void execute() {
		character.display();
		currentPlace.display();
	}
}