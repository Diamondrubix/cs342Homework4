/* Author: Alexander Oey (NetID: aoey2) */
package game;

import character.Character;
import character.NPC;
import character.Player;

import place.Direction;
import place.Place;
import artifact.Artifact;

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
	public static ArrayList<Character> characters = new ArrayList<>();
	private int neededPlayers = 0;
	
	/**
	 * Constructs a Game object with a name.
	 *
	 * @param name name of the game.
	 */
	public Game(String name) {
		this.name = name;
	}

	public static void removeCharacter(Character c) {
		characters.remove(c);
		// Note: Have not implemented loot-drop
	}
	
	/**
	 * Constructs a Game object given a Scanner to a file.
	 *
	 * @param input Scanner instance
	 */
	public Game(Scanner input) {
		System.out.println("press 0 to play single player. press 1 to host multiplayer\nenter an ip to join a host");
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
				Place p = new Place(input);
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
				Direction d = new Direction(input);
			}
		}
		
		//Parse Characters section.
		words = CleanLineScanner.getTokens(input);
		if (words.length < 2 || !words[0].equalsIgnoreCase("CHARACTERS")) {
			System.out.println("CHARACTERS section not defined");
			//No input on Character section.
			KeyboardScanner userInput = KeyboardScanner.getKeyboardScanner();
			System.out.print("Enter the number of players: ");
			int numCharacters = userInput.nextInt();
			userInput.nextLine();
			for (int i = 0; i < numCharacters; ++i) {
				System.out.print("Enter character name: ");
				String name = userInput.nextLine().trim();
				System.out.print("Enter character description: ");
				String description = userInput.nextLine().trim();
				Character c = new Player(i+1, name, description, Place.getRandomID());
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
				input.nextLine();
				if (type.equals("PLAYER")) {
					Character c = new Player(input); //change player to Character to solve temporary error to test compile
					characters.add(c);
				}
				else if (type.equals("NPC")) {
					Character c = new NPC(input);//change player to Character to solve temporary error to test compile
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
		while (!shouldExit()) {
			for(int i=0; i<characters.size();i++){
				exit = characters.get(i).makeMove();
				if(shouldExit())break;
			}

		}
	}



	private boolean shouldExit(){
		for(int i =0; i<characters.size();i++){
			if(characters.get(i) instanceof Player){
				return false;
			}
		}
		return true;
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