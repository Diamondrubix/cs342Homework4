/* 
 * Author: 
 * Alexander Oey (aoey2) 
 * Adam Arato (aarato2)
 */
package game;

import Network.Client;
import Network.Network;

import java.util.Scanner;

/**
 * This class interfaces the keyboard with the program. 
 * The purpose of this class is to maintain single Scanner object to System.in.
 *
 * @author Alexander Oey (aoey2)
 * @author Adam Arato (aarato2)
 */
public class KeyboardScanner{
	private Scanner keyboard;
	private static KeyboardScanner kScanner;
	//private Client client;
	
	/**
	 * Returns the Scanner object to System.in
	 * 
	 * @return KeyboardScanner instance
	 */
	public static KeyboardScanner getKeyboardScanner() {
		//First use of KeyboardScanner
		if (kScanner == null) {
			kScanner = new KeyboardScanner();
		}
		return kScanner;
	}

	/**
	 * Returns a string input from keyboard delimited by a new line character.
	 * This method also broadcasts the input to all clients over the network.
	 *
	 * @return string input
	 */
	public String nextLine(){
		String line = keyboard.nextLine();

		if(Network.multiplayer){
			Network.getClient().sendData(line);
		}

		//add socket stuff here
		return line;
	}

	/**
	 * Returns an integer input from keyboard delimited by a whitespace.
	 * This method also broadcasts the input to all clients over the network.
	 *
	 * @return integer input
	 */
	public int nextInt(){
		int l = keyboard.nextInt();

		if(Network.multiplayer){
			Network.getClient().sendData(Integer.toString(l));
		}

		return l;
	}
	
	private KeyboardScanner() {
		keyboard = new Scanner(System.in);
	}
}
	