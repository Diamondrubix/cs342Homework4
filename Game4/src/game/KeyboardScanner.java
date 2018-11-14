/* Author: Alexander Oey (NetID: aoey2) */
package game;

import java.util.Scanner;

/**
 * This class interfaces the keyboard with the program. 
 * The purpose of this class is to maintain single Scanner object to System.in.
 *
 * @author Alexander Oey (aoey2)
 */
public class KeyboardScanner{
	private Scanner keyboard = null;
	private static KeyboardScanner kScanner;
	
	/**
	 * Returns the Scanner object to System.in
	 * 
	 * @return Scanner object
	 */
	public static KeyboardScanner getKeyboardScanner() {
		//First use of KeyboardScanner
		if (kScanner == null) {
			new KeyboardScanner();
		}
		return getKeyboardScanner();
	}


	public String nextLine(){
		String line = keyboard.nextLine();
		//add socket stuff here
		return line;
	}

	public int nextInt(){
		int l = keyboard.nextInt();

		return l;
	}
	
	private KeyboardScanner() {
		keyboard = new Scanner(System.in);
	}
}
	