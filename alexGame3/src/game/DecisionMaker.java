/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Move;

/**
 * An interface that defines character action selection.
 *
 * @author Alexander Oey (aoey2)
 */
public interface DecisionMaker {
	/**
	 * Encapsulate character action in a Move object.
	 *
	 * @param character character executing the action
	 * @param place current location of the character
	 * @return Move object containing details of the action
	 */
	public Move getMove(Character character, Place place);
}