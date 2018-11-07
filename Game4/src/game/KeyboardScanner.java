/* Author: Alexander Oey (NetID: aoey2) */
package game;

import java.util.Scanner;

/**
 * This class interfaces the keyboard with the program. 
 * The purpose of this class is to maintain single Scanner object to System.in.
 *
 * @author Alexander Oey (aoey2)
 */
public class KeyboardScanner {
	private static Scanner keyboard = null;
	
	/**
	 * Returns the Scanner object to System.in
	 * 
	 * @return Scanner object
	 */
	public static Scanner getKeyboardScanner() {
		//First use of KeyboardScanner
		if (keyboard == null) {
			new KeyboardScanner();
		}
		return keyboard;
	}
	
	private KeyboardScanner() {
		keyboard = new Scanner(System.in);
	}
}
	