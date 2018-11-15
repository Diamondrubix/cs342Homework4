/* Author: Alexander Oey (NetID: aoey2) */
package move;

/**
 * This class encapsulates an action.
 *
 * @author Alexander Oey (aoey2)
 */
public abstract class Move {
	/**
	 * Default constructor that does nothing.
	 */
	public Move() { }
	
	/**
	 * Runs the command encapsulated by the subclasses of this object.
	 */
	public abstract void execute();
}