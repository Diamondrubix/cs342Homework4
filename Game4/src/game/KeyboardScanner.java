/* Author: Alexander Oey (NetID: aoey2) */
package game;

import Network.Client;
import Network.Network;

import java.util.Scanner;

/**
 * This class interfaces the keyboard with the program. 
 * The purpose of this class is to maintain single Scanner object to System.in.
 *
 * @author Alexander Oey (aoey2)
 */
public class KeyboardScanner{
	private Scanner keyboard;
	private static KeyboardScanner kScanner;
	//private Client client;
	
	/**
	 * Returns the Scanner object to System.in
	 * 
	 * @return Scanner object
	 */
	public static KeyboardScanner getKeyboardScanner() {
		//First use of KeyboardScanner
		if (kScanner == null) {
			kScanner = new KeyboardScanner();
		}
		return kScanner;
	}


	public String nextLine(){
		String line = keyboard.nextLine();

		if(Network.multiplayer){
			Network.getCLient().sendData(line);
		}

		//add socket stuff here
		return line;
	}

	public int nextInt(){
		int l = keyboard.nextInt();

		if(Network.multiplayer){
			Network.getCLient().sendData(Integer.toString(l));
		}

		return l;
	}
	
	private KeyboardScanner() {
		keyboard = new Scanner(System.in);
	}
}
	