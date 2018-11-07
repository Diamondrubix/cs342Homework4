/* Author: Alexander Oey (NetID: aoey2) */
package game;

/**
 * This class encapsulates an action.
 *
 * @author Alexander Oey (aoey2)
 */
public class Move {
	/**
	 * Represents the type of action encapsulated by the Move object.
	 *
	 */
	public enum MoveType {
		EXIT,
		LOOK,
		INVENTORY,
		GET,
		DROP,
		USE,
		GO;
	}
	
	//Fields
	private final MoveType type;
	private final String argument;
		
	/**
	 * Constructs a Move object given the type and arguments.
	 *
	 * @param type type of action
	 * @param args for the given action
	 */
	public Move(MoveType type, String args) {
		this.type = type;
		this.argument = args;
	}
	
	/**
	 * Provides the type of the action.
	 *
	 * @return type of action in MoveType
	 */
	public MoveType type() {
		return type;
	}
	
	/**
	 * Provides the arguments given to this action.
	 *
	 * @return argument(s) to the action
	 */
	public String argument() {
		return argument;
	}
}