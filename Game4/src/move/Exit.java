/* Author: Alexander Oey (NetID: aoey2) */
package move;

import character.Character;
import place.Place;
import move.Move;

/**
 * This class encapsulates a command to exit from the game.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class Exit extends Move {
	private final Character character;
	private final Place current;
	
	public Exit(Character c, Place p) {
		this.character = c;
		this.current = p;
	}
	
	public void execute() {
		p.removeCharacter(c);
	}
}
	