/* Author: Alexander Oey (NetID: aoey2) */
package move;

import character.Character;
import place.Place;
import move.Move;
import artifact.Artifact;

/**
 * This class encapsulates a command to use an item in inventory.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class Attack extends Move {
	private Character attacker;
	private Character target;
	private Place currentPlace;
	private int damage;
	
	/**
	 * Constructs a use item command.
	 *
	 * @param c character exiting the game
	 * @param p current location of the character
	 * @param itemName name of item to be used
	 */
	public Attack(Character c, Character t, Place p, int damage) {
		this.attacker = c;
		this.target = t;
		this.currentPlace = p;
		this.damage = damage;
	}
	
	/**
	 * Runs the command encapsulated by this object.
	 */
	public void execute() {
		if (target != null) {
			target.damage(damage);
			Character.println("Attacked " + target.getName() +" dealing "
                  			+ damage + " damage");
		}
		else {
			Character.println("Invalid attack target.");
		}
	}
}