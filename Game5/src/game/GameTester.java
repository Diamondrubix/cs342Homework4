/*
 * Author: 
 * Alexander Oey (aoey2) 
 * Zain Zahran (zzahra2)
 * Adam Arato (aarato2)
 */
package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Contains the main method and sets up the game for testing.
 *
 * @author Alexander Oey (aoey2)
 * @author Zain Zahran (zzahra2)
 * @author Adam Arato (aarato2)
 */
public class GameTester {

	public static void main(String[] args) {
		System.out.println("Author: ");
		System.out.println("Alexander Oey (aoey2)");
		System.out.println("Zain Zahran (zzahra2)");
		System.out.println("Adam Arato (aarato2)");
		System.out.println("Text-based exploration game.");
		System.out.println("");
		System.out.println("Instructions: ");
		System.out.println("QUIT or Q or EXIT to quit the game.");
		System.out.println("LOOK to print information about the current room.");
		System.out.println("GO <direction>, where <direction> is one of ");
		System.out.println("N, S, E, W, U, D, NE, NW, SE, SW, NNE, NNW, ENE, " +
				"WNW, ESE, WSW, SSE, and SSW");
		System.out.println("");
		System.out.println("All inputs are case-insensitive and directions can " +
				"be shortened (e.g. N instead of NORTH or SSW instead of SOUTHSOUTHWEST.)");

		System.out.println("");
		System.out.println("Running game ");

		boolean gameRan = false;
		//File name provided.
		if (args.length == 1) {
			// System.out.println("arg: " + args[0]);
			try (Scanner sc = new Scanner(new File(args[0]))) {
				Game game = new Game(sc);
				game.play();
				gameRan = true;
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}

		//exit.
		if (gameRan) return;

		Scanner userInput = new Scanner(System.in);
		System.out.print("File not found. Please enter a filename: ");
		String userString = userInput.nextLine().trim();
		while (!gameRan && !userString.equalsIgnoreCase("quit")) {
			try (Scanner sc = new Scanner(new File(userString))) {
				Game game = new Game(sc);
				game.play();
				gameRan = true;
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
				System.out.print("File not found. Please enter a filename: ");
				userString = userInput.nextLine().trim();
				continue;
			}
		}
	}
}