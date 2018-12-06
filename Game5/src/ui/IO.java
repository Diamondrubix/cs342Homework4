/* Author: Alexander Oey (aoey2) */
package ui;

import ui.UserInterface;
import ui.TextInterface;
import ui.one.GUI_1;
import ui.two.GUI_2;

/**
 * This class provides the input and output services from the program 
 * to the user.
 *
 * @author Alexander Oey (aoey2)
 */ 
public class IO {
	private UserInterface implementor; // user interface shown to the user.
	
	public static final int TEXT = 0;
	public static final int GUI_1 = 1;
	public static final int GUI_2 = 2;
	public static final int GUI_3 = 3;

	/**
	 * Constructs an I/O service object using TextInterface
	 * as the user interface.
	 * @see ui.TextInterface
	 */
	public IO() {
		selectInterface(TEXT);
	}
	
	/**
	 * Displays the message to the user using the implemented user interface.
	 *
	 * @param message message to display
	 */
	public void display(String message) {
		implementor.display(message);
	}
	
	/**
	 * Obtains input from the user using the implemented user interface.
	 *
	 * @return String containing the user input
	 */
	public String getLine() {
		return implementor.getLine();
	}
	
	/**
	 * Changes the user interface implementation.
	 *
	 * @param select integer values associated to the constants defined in this class.
	 */
	public void selectInterface(int select) {
		switch(select) {
			case TEXT: implementor = new TextInterface(); break;
			case GUI_1: implementor = new GUI_1(); break;
			case GUI_2: implementor = new GUI_2(); break;
			case GUI_3: // text interface placeholder until implemented
			default: 
				implementor = new TextInterface(); 
				break;
		}
	}
	
}