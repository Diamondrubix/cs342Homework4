// Name: Zain Zahran
// Netid: zzahra2

import java.util.*;

// Serves as a container for the entire game system.
public class Game {
	private String name;
	private Vector<Character> characters;		// Keep track of all characters in the game
	static public boolean toRemove = false;

	// NEW play method
	void play() {
		// Asks each of the characters to make a move in turn
		// Repeats until there are no players left
		while (Character.nPlayers > 0) {
			Vector<Character> cToRemove = new Vector<Character>();	// Vector of characters that decide to exit the game
			Character ch;	// Dummy ch to add to cToRemove
			for (Character c : characters) {
				c.makeMove();	// Old play work is basically moved in Character.makeMove() and DecisionMaker
				// Sanity check
				if (Character.nPlayers == 0) {
					break;
				}
				if (toRemove) {
					ch = c;
					cToRemove.add(ch);
					toRemove = false;
				}
			}
			for (Character c : cToRemove) {
				characters.remove(c);
			}
		}
	}
	Game (Scanner s) {
		toRemove = false;
		characters = new Vector<Character>();	// Allocate vector of characters

		/* Use given input to make a new file without comments */
		String newFileData = "";
		while (s.hasNextLine() ) {
			newFileData += s.nextLine().replaceAll("//.*","").trim() + "\n";
		}
		// DEBUG STATEMENT: System.out.print(newFileData);
		s = new Scanner(newFileData);	// New file, commentless
		
		/* Validate GDF and Check version */
		if (s.hasNext() ) {
			// Is file written in GDF?
			if (s.next().toUpperCase().equals("GDF") ) {
				double versionNum = s.nextDouble();
				if (versionNum == 3.1 ) {
					System.out.println("Version 3.1 detected.");
					// 3.1 Game name
					String name = s.nextLine();
					this.name = name;
					gdf3(s);	// Pass in scanner to create game for 3.1
				}
				else if (versionNum == 4.0) {
					System.out.println("Version 4.0 detected.");
					// 4.0 Game Name
					String name = s.nextLine().replaceAll("\\-.*", "");	// Trims excess garbage
					this.name = name;
					gdf4(s);	// Pass in scanner to create game for 4.0
				}
			}
			else {
				System.out.println("No compatible version detected.");
			}
		}
	}
	// Version 3.1 GDF Creator
	// *Called by Game(Scanner s) constructor
	void gdf3(Scanner s) {
		int numPlaces = 0;
		/* PLACE CONSTRUCTORS */
		if (new String(s.next() ).toUpperCase().equals("PLACES") ) {
			numPlaces = s.nextInt();
			// DEBUG STATEMENT: System.out.println(numPlaces);
			
			// Create an exit place
			Place.getPlaceByID(0);
			// Make all places and store them to known collection
			for (int i = 0; i < numPlaces; i++) {
				Place p = new Place(s);	// Must create an object to invoke constructor

				// Player is put in the FIRST place
				if (i == 0) {
					Player p1 = new Player(1, "Player one", "Main player of the game", p);
					characters.add(p1);
				}
			}
			/* DIRECTION CONSTRUCTORS */
			while (s.hasNextLine() ) {
				if (new String(s.next() ).toUpperCase().equals("DIRECTIONS") ) {
					int numDirections = s.nextInt();
					for (int i = 0; i < numDirections; i++) {
						Direction d = new Direction(s);
					}
					break;
				}
			}
			/* ARTIFACT CONSTRUCTORS */
			while (s.hasNextLine() ) {
				if (new String(s.next() ).toUpperCase().equals("ARTIFACTS") ) {
					int numArtifacts = s.nextInt();
					for (int i = 0; i < numArtifacts; i++) {
						Artifact a = new Artifact(s);
					}						
					break;	// That's all the input we need up to this version 3.1
				}
			}
			// Place.printAll();
		}
	}
	// Version 4.0 GDF Creator
	// *Called by Game(Scanner s) constructor
	void gdf4(Scanner s) {
		int numPlaces = 0;
		/* PLACE CONSTRUCTORS */
		if (new String(s.next() ).toUpperCase().equals("PLACES") ) {
			numPlaces = s.nextInt();
			// DEBUG STATEMENT: System.out.println(numPlaces);
			
			// Create an exit place
			Place.getPlaceByID(0);
			// Make all places and store them to known collection
			for (int i = 0; i < numPlaces; i++) {
				Place p = new Place(s);	// Must create an object to invoke constructor
			}
			/* DIRECTION CONSTRUCTORS */
			while (s.hasNextLine() ) {
				if (new String(s.next() ).toUpperCase().equals("DIRECTIONS") ) {
					int numDirections = s.nextInt();
					for (int i = 0; i < numDirections; i++) {
						Direction d = new Direction(s);
					}
					break;
				}
			}
			/* CHARACTER CONSTRUCTORS */
			while (s.hasNextLine() ) {
				if (new String(s.next() ).toUpperCase().equals("CHARACTERS") ) {
					int numChars = s.nextInt();	// Number of characters
					for (int i = 0; i < numChars; i++) {					
						String charType = (s.next()).toUpperCase();		// Player or NPC?
						int location = s.nextInt();		// Where this char. will be placed
						if (charType.equals("PLAYER") ) {
							Player p = new Player(s, 4, location);	// Location is placeID
							characters.add(p);	// Game tracks all players
						}
						else if (charType.equals("NPC") ) {
							NPC p = new NPC(s, 4, location);	// Location is placeID
							characters.add(p);	// Game tracks all NPCs
						}
						/* DEBUG STATEMENTS */
						// System.out.println("Character #" + i);
						// System.out.println(charType);
						// System.out.println(location);
					}
					break;
				}
			}
			/* ARTIFACT CONSTRUCTORS */
			while (s.hasNextLine() ) {
				if (new String(s.next() ).toUpperCase().equals("ARTIFACTS") ) {
					int numArtifacts = s.nextInt();
					for (int i = 0; i < numArtifacts; i++) {
						Artifact a = new Artifact(s);
					}						
					break;	// That's all the input we need up to this version 3.1
				}
			}

			// Place.printAll();
		}
	}

	// Prints out the game information. Debugging and Testing
	void print() {
		System.out.println("Name: " + name);
	}
}