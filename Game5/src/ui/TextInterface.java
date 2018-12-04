/* Author: Alexander Oey (aoey2) */
package ui;

import game.KeyboardScanner;

/**
 * This class represents a text-based user interface.
 * This user interface is intended to be used in the command line.
 *
 * @author Alexander Oey (aoey2)
 */
public class TextInterface implements UserInterface {
	/**
	 * Constructs an object representing a text-based user interface.
	 */
	public TextInterface() {
	}
	
	/**
	 * The display method for this class. 
	 * This will print the message to the console/command line.
	 *
	 * @param message message to display
	 */
	public void display(String message) {
		System.out.println(message); // Use Network??
	}
	
	/**
	 * Get user input method for this class.
	 * This will use a Scanner to System.in to obtain the input.	
	 *
	 * @return user input
	 */
	public String getLine() {
		KeyboardScanner sc = KeyboardScanner.getKeyboardScanner();
		return sc.nextLine();
	}
}