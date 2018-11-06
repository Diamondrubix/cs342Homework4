/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Direction;
import game.Place;
import game.Artifact;
import game.Character;
import game.Player;
import game.NPC;
import game.CleanLineScanner;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the main game logic.
 *
 * @author Alexander Oey (aoey2)
 */
public class Game {
	//Fields.
	private final String name;
	private final ArrayList<Character> characters = new ArrayList<>();
	private int neededPlayers = 0;
	
	/**
	 * Constructs a Game object with a name.
	 *
	 * @param name name of the game.
	 */
	public Game(String name) {
		this.name = name;
	}
	
	/**
	 * Constructs a Game object given a Scanner to a file.
	 *
	 * @param input Scanner instance
	 */
	public Game(Scanner input) {
		//Construct special rooms.
		//Expansion room.
		Place dlc = new Place(0, "Nowhere", 
		"Room Unavailable. Expansion coming soon. \n" + 
		"Pre-order NOW! Visit: cs.uic.edu/~i342/ for more information.");
		//Exit room.
		Place exit = new Place(1, "Exit", "You exited the game");
		
		//Parse header.
		String line = CleanLineScanner.getCleanLine(input.nextLine());
		String[] words = line.split("\\s+");
		if (words.length < 3 && !words[0].equals("GDF")) {
			System.out.println("Invalid file header.");
			name = "";
		}
		else if (Double.parseDouble(words[1]) > 4.0) {
			System.out.println("Version " + words[1] + " unsupported");
			name = "";
		}
		else {
			name = words[2];
		}
		
		//Parse Places section.
		words = CleanLineScanner.getTokens(input);
		//Places section keyword not found.
		if (words.length < 2 || !words[0].equalsIgnoreCase("PLACES")) {
			System.out.println("Error: PLACES section not defined.");
			Place p = new Place(1, "", "");
			return;
		}
		else {
			//Parse all entries on Places section.
			int numPlaces = Integer.parseInt(words[1]);
			//System.out.println("Num places: " + numPlaces);
			//Number of places is zero or negative.
			if (numPlaces < 1) {
				System.out.println("Warning: No places declared!.");
			}
			for (int i = 0; i < numPlaces; ++i) {
				Place p = new Place(input, 3);
			}
		}

		//Parse Directions section.
		words = CleanLineScanner.getTokens(input);
		if (words.length < 2 || !words[0].equalsIgnoreCase("DIRECTIONS")) {
			System.out.println("Error: DIRECTIONS section not defined");
			return;
		}
		else {
			//Parse all entries in Directions section.
			int numDirections = Integer.parseInt(words[1]);
			//System.out.println("Num directions: " + numDirections);
			//Number of directions is zero or negative.
			if (numDirections < 1) {
				System.out.println("Warning: No directions declared!");
			}
			//Construct direction objects.
			for (int i = 0; i < numDirections; ++i) {
				Direction d = new Direction(input, 3);
			}
		}
		
		//Parse Characters section.
		words = CleanLineScanner.getTokens(input);
		if (words.length < 2 || !words[0].equalsIgnoreCase("CHARACTERS")) {
			System.out.println("CHARACTERS section not defined");
			//No input on Character section.
			Scanner userInput = KeyboardScanner.getKeyboardScanner();
			System.out.print("Enter the number of players: ");
			int numCharacters = userInput.nextInt();
			userInput.nextLine();
			for (int i = 0; i < numCharacters; ++i) {
				System.out.print("Enter character name: ");
				String name = userInput.nextLine().trim();
				System.out.print("Enter character description: ");
				String description = userInput.nextLine().trim();
				Character c = new Player(i+1, name, description);
				characters.add(c);
			}
		}
		else {
			//Parse all entries in the Characters section.
			int numCharacters = Integer.parseInt(words[1]);
			//Construct character objects.
			for (int i = 0; i < numCharacters; ++i) {
				String type = input.next().trim();
				// System.out.println("Char " + i + "Type: " + type);
				if (type.equals("PLAYER")) {
					Character c = new Player(input, 3);
					characters.add(c);
				}
				else if (type.equals("NPC")) {
					Character c = new NPC(input, 3);
					characters.add(c);
				}
				else {
					System.out.println("Error: Invalid character type!");
					return;
				}
			}
		}
		
		
		//Parse Artifacts section.
		//Skip reading next line if characters is not defined.
		if (words.length < 2 || !words[0].equalsIgnoreCase("CHARACTERS"));
		else {
			words = CleanLineScanner.getTokens(input);
		}
		if (words.length < 2 || !words[0].equalsIgnoreCase("ARTIFACTS")) {
			System.out.println("ARTIFACTS section not defined.");
			return;
		}
		else {
			//Parse all entries in Artifacts section.
			int numArtifacts = Integer.parseInt(words[1]);
			// System.out.println("Num artifacts: " + numArtifacts);
			//Construct artifact objects.
			for (int i = 0; i < numArtifacts; ++i) {
				int locationID = input.nextInt();
				// System.out.println("Place ID of Artifact " + i + ": " + locationID);
				Artifact artifact = new Artifact(input, 3);
				
				//Put artifact in its initial location.
				if (locationID < 0) { // Character inventory
					Character.getCharacterByID(Math.abs(locationID))
					         .addArtifact(artifact);
				}
				else if (locationID == 0) { // Random room
					//Random.
				}
				else { // Room
					Place.getPlaceByID(locationID).addArtifact(artifact);
				}
			}
		}
	}
	
	/**
	 * Runs the main loop of the game and handles interaction with the user.
	 */
	public void play() {
		boolean exit = false;
		while (!exit) {
			for (Character c: characters) {
				exit = c.makeMove();
				if (exit) break;
			}
		}
	}
	
	/**
	 * Prints debugging information to the console.
	 */
	public void print() {
		System.out.println("Name: " + this.name);
		System.out.println("Characters in this game: ");
		for (Character character: characters) {
			character.print();
		}

	}

	
	}