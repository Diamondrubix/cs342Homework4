/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Direction;
import game.Place;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Contains the main method and sets up the game for testing.
 *
 * @author Alexander Oey (aoey2)
 */
public class GameTester {

	public static void main(String[] args) {
		//testPlaceAndDirection();
		
		System.out.println("Author: Alexander Oey (NetID: aoey2)");
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
			}
			catch (FileNotFoundException ex) {
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
			}
			catch (FileNotFoundException ex) {
				ex.printStackTrace();
				System.out.print("File not found. Please enter a filename: ");
				userString = userInput.nextLine().trim();
				continue;
			}
		}
	}
	
	private static void testPlaceAndDirection() {
		System.out.println("testPlaceAndDirection");
		Place one = new Place(10, "Room 1", "Desc of Room 1");
		Place two = new Place(12, "Room 2", "Desc of Room 2");
		
		one.name();
		System.out.println("");
		one.description();
		System.out.println("");
		one.display();
		System.out.println("");
		one.print();
		System.out.println("");
		Place.getPlaceByID(12).print();
		System.out.println("");
		
		Direction d = new Direction(200, one, two, "NORTH");
		d.print();
		System.out.println("");
		System.out.println("Locked: " + d.isLocked());
		d.lock();
		System.out.println("");
		System.out.println("Locked: " + d.isLocked());
		d.unlock();
		System.out.println("");
		System.out.println("Locked: " + d.isLocked());
		d.follow().print();
		System.out.println("");
		d.getName();
		System.out.println("");
		
		one.followDirection("NORTH").print();
		System.out.println("");
		one.followDirection("N").print();
	}
}