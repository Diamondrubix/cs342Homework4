/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Character;

import java.util.Scanner;

/**
 * Represents a player in the game world. 
 * 
 * @author Alexander Oey (aoey2)
 */
public class Player extends Character {
	/**
	 * Constructs a Player object given a Scanner and GDF file version.
	 *
	 * @param input Scanner instance
	 * @version GDF file version
	 */
	public Player(Scanner input, int version) {
		super(input,version);
		decider = new PlayerInterface();
	}
	
	/**
	 * Constructs a Player with id, name, and description.
	 *
	 * @param id Character ID number
	 * @param name character name
	 * @param desc detailed information about the player
	 */
	public Player(int id, String name, String desc) {
		super(id, name, desc);
		decider = new PlayerInterface();
	}
}