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
public class ShowInventory extends Move {
	private final Character character;
	
	/**
	 * Constructs a view inventory command.
	 * @param c character exiting the game
	 */
	public ShowInventory(Character c) {
		this.character = c;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	public void execute() {
		character.viewInventory();
	}
}