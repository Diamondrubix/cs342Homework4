/* Author: Alexander Oey (NetID: aoey2) */
package move;

import character.Character;
import place.Place;
import move.Move;

/**
 * This class encapsulates a command to move from Place to Place.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class Go extends Move {
	private final Character character;
	private final Place currentPlace;
	private final String[] moveDirs;
	
	public Go(Character c, Place current, String[] directions) {
		this.character = c;
		this.currentPlace = current;
		this.moveDirs = directions;
	}
	
	@Override
	public void execute() {
		for (int i = 0; i < moveDirs.length; ++i) {
			//Ignore GO keyword.
			if (words[i].equalsIgnoreCase("GO")) {
				continue;
			}
			Place prev = currentPlace;
			currentPlace = currentPlace.followDirection(words[i]);
			prev.removeCharacter(c);
			currentPlace.addCharacter(c);
		}
	}
}