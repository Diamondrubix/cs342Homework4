/* Author: Alexander Oey (NetID: aoey2) */
package move;

import character.Character;
import place.Place;
import move.Move;
import game.Game;

/**
 * This class encapsulates a command to move from Place to Place.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class Go extends Move {
	private final Character character;
	private final Place currentPlace;
	private final String[] moveDirs;
	private final Place destination;
	
	/**
	 * Constructs a movement command.
	 *
	 * @param c character to move
	 * @param current current place of the character
	 * @param directions movement directions for the character
	 */
	public Go(Character c, Place current, String[] directions) {
		this.character = c;
		this.currentPlace = current;
		this.moveDirs = directions;
		this.destination = null;
	}
	
	/**
	 * Constructs a movement command.
	 *
	 * @param c character to move
	 * @param current current place of the character
	 * @param directions movement directions for the character
	 */
	@Deprecated
	public Go(Character c, Place current, Place dest) {
		this.character = c;
		this.currentPlace = current;
		this.moveDirs = null;
		this.destination = dest;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	@Override
	public void execute() {
		for (int i = 0; i < moveDirs.length; ++i) {
			//Ignore GO keyword.
			if (moveDirs[i].equalsIgnoreCase("GO")) {
				continue;
			}
			System.out.println("Direction " + moveDirs[i]);
			Place dest = currentPlace.followDirection(moveDirs[i]);
			if (dest.equals(Place.getPlaceByID(1))) { //Exit state
				currentPlace.removeCharacter(character);
				Game.removeCharacter(character);
			} 
			else if (dest.equals(Place.getPlaceByID(0))) { //Expansion
				//Do nothing except print.
				
			}
			else {
				currentPlace.removeCharacter(character);
				character.setLocation(dest);
				dest.addCharacter(character);
				dest.print();
			}
		}
	}
}