/* Author: Alexander Oey (aoey2) */
package ui;

import Network.Network;
import ui.UserInterface;
import ui.TextInterface;
import ui.one.GUI_1;
import ui.three.GUI_3;
import ui.two.GUI_2;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

	public static ArrayList<IO> allIO = new ArrayList<IO>();



	/**
	 * Constructs an I/O service object using TextInterface
	 * as the user interface.
	 * @see ui.TextInterface
	 */
	public IO() {
		allIO.add(this);
		selectInterface(TEXT);
	}
	
	/**
	 * Displays the message to the user using the implemented user interface.
	 *
	 * @param message message to display
	 * @author Alexander Oey (aoey2)  Adam Arato aarato2
	 */
	public void display(String message) {
		implementor.display(message);
		Network.netPrintln(message);
	}

	/**
	 * displays method without sending it out on the network.
	 * @Param message message to display
	 * @author Adam Arato aarato2
	 */
	public void noNetDisplay(String message){
		implementor.display(message);
	}

	/**
	 * displays to every interface, not just the owners interface
	 * @Param message message to display
	 * @author Adam Arato aarato2
	 */
	public static void globalDisplay(String message){
		for(int i =0; i <allIO.size();i++){
			allIO.get(i).noNetDisplay(message);
		}
		Network.netPrintln(message);
	}

	/**
	 * displays method to all interfaces without sending it out on the network.
	 * @Param message message to display
	 * @author Adam Arato aarato2
	 */
	public static void noNetglobalDisplay(String message){
		for(int i =0; i <allIO.size();i++){
			allIO.get(i).noNetDisplay(message);
		}
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
			case GUI_3: implementor = new GUI_3(); break;
			default: 
				implementor = new TextInterface(); 
				break;
		}
	}
	
}