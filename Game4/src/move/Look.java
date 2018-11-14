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
	private final Place current;
	
	public Look(Character c, Place p) {
		this.character = c;
		this.current = p;
	}
	
	public void execute() {
		c.display();
		p.display();
	}
}