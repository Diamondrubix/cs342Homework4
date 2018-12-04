/* Author: Alexander Oey (aoey2) */
package ui;

/**
 * The UserInterface interface is defined to provide a common protocol 
 * between the user interface implementations and the core game objects.
 *
 * This interface should be implemented by any class 
 * whose instances are intended to be user interfaces for the game.
 *
 * @author Alexander Oey (aoey2)
 */
public interface UserInterface {
	/**
	 * Invoked when a message needs to be displayed to the user.
	 */
	public void display(String message);
	
	/**
	 * Invoked when the game requires user input.
	 */
	public String getLine();
}