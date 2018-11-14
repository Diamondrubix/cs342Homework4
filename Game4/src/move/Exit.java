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
	
	/**
	 * Constructs an exit command.
	 * @param c character exiting the game
	 * @param p current location of the character
	 */
	public Exit(Character c, Place p) {
		this.character = c;
		this.current = p;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	@Override
	public void execute() {
		p.removeCharacter(c);
	}
}
	