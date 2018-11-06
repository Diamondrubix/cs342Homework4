/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Character;

import java.util.Scanner;

/**
 * Represents an NPC in the game world.
 */
public class NPC extends Character {
	/**
	 * Constructs an NPC given a Scanner and GDF file version.
	 *
	 * @param input Scanner instance
	 * @param version GDF file version
	 */
	public NPC(Scanner input, int version) {
		super(input,version);
		decider = new AI();
	}
}