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
	
	public ShowInventory(Character c) {
		this.character = c;
	}
	
	public void execute() {
		character.displayInventory();
	}
}