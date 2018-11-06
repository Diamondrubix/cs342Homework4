// Zain Zahran
// Netid: zzahra2

import java.util.*;

public class Character {
	private int ID;
	private String name;
	private String description;
	private Vector<Artifact> inventory;		// Collection of Artifacts (char inventory)
	private Place currPlace;				// Characters keeps track of their own current place
	public DecisionMaker decider;	// Set different in Player and NPC constructors
	static private Map <Integer, Character> characters = new HashMap<Integer, Character>();	// ID lookup on characters
	public static int nPlayers = 0;

	// Constructors
	// Create a character
	public Character(int ID, String name, String description, Place place) {
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.currPlace = place;
		currPlace.addCharacter(this);
		characters.put(ID, this);
		inventory = new Vector<Artifact>();
	}
	// Creates character from data in data file.
	// SCANNER
	public Character(Scanner s, int version, int placeID) {
		// characters.put(this.ID, this);	// Put into static collection of characters
		inventory = new Vector<Artifact>();

		this.ID = s.nextInt();					// Character ID
		this.name = s.nextLine().trim();		// Character name
		// Count lines for description
		int numLines = s.nextInt();
		this.description = s.nextLine();
		for (int i = 0; i < numLines; i++) {
			description += s.nextLine() + "\n";
		} // end description count
		// Set this character's current place, as predetermined
		// 		Check if place id != 0
		//		If it is == 0, we must set a random place
		if (placeID == 0) {
			this.currPlace = Place.getRandomPlace();
		}
		// Otherwise, put in place requested
		else if (placeID > 0) {
			this.currPlace = Place.getPlaceByID(placeID);
		}
		currPlace.addCharacter(this);
		characters.put(ID, this);
	}
	// Returns character associated with the ID
	public static Character getCharacterByID(int id) {
		return characters.get(id);	// Returns the character with that ID
	}
	// Adds artifact to this character's inventory
	void addArtifact(Artifact a) {
		inventory.add(a);
	}
	// Given a decision from the Move class
	// Perform the action
	public void makeMove() {
		Move m = decider.getMove(this, this.currPlace);
		switch( m.moveType ) {
			case QUITALL:
			case EXITALL:
				// Exit the whole game regardless of # players
				System.out.println("Quitting the entire game...");
				Character.nPlayers = 0;
				break;
			case EXIT:
			case QUIT:
				// 1 less player
				// 5.0 idea, consider dropping loot when person leaves?
				System.out.println(this.name() + " rage quit the game...");
				// Remove player from game characters and place characters
				Game.toRemove = true;
				this.currPlace.removeCharacter(this);
				(Character.nPlayers)--;
				break;
			case LOOK:
				// Displays place along with available artifacts
				this.currPlace.print();
			case INVE:
				// Displays user inventory
				this.viewInventory();
				break;
			case GET:
				if (m.getArgument().equals("") ) break;	// Error checking
				// Make sure the artifact exists in Place
				if (this.currPlace.containsArtifact(m.getArgument() ) == false) {
					break;
				}
				// Remove artifact from place and add to inventory
				else {
					inventory.add( currPlace.removeArtifact(m.getArgument() ) );
				}
				break;
			case DROP:
				if (m.getArgument().equals("") ) break;	// Error checking
				for (Artifact a : inventory) {
					if (a.name().toUpperCase().equals(m.getArgument() ) ) {
						inventory.remove(a);					// Remove from player inventory
						currPlace.addArtifact(a);		// Add dropped artifact to current place
						break;
					} 
				}
				break;
			case USE:
				if (m.getArgument().equals("") ) break; // Error checking
				// Make sure artifact exists in inventory
				for (Artifact a : inventory) {
					if (a.name().toUpperCase().equals(m.getArgument() ) ) {
						a.use(this.currPlace);
					}
				}
				break;
			case GO:
				// User reached exit? We should probably check that...
				try {
					if (this.currPlace.followDirection(m.getArgument()).getPlace() == 0) {
						break;
					}
					else {					
						// Follow the indicated direction from the user
						this.currPlace = this.currPlace.followDirection(m.getArgument() );	
					}
				} catch (NullPointerException e) {
					System.out.println(this.name() + " exited the dungeon.");
					Character.nPlayers--;
					break;	// Exit the game.
				}
				break;
			case NONE:
				// Wait a turn...
				break;
			default:
				System.out.println("Default case in Character.java");
		}
	}
	int getID() {
		return ID;
	}
	String name() {
		return this.name;
	}
	// Inventory Display
	void viewInventory() {
		System.out.println();
		if (inventory.isEmpty() ) {
			System.out.println("Your inventory is empty! Use 'GET' <Artifact Name> ");
		}
		else {
			System.out.print(this.name + "'s INVENTORY: ");
			int totalWeight = 0;
			for (Artifact a : inventory) {
				System.out.print(a.name() + ", " );
				totalWeight += a.weight();
			}
			System.out.println("Total weight you're carrying: " + totalWeight);
		}
	}
	// Get a random artifact from this character's inventory
	String getRandomArt() {
		if (inventory.size() < 1 ) {
			return "";
		}
		int randomInt;
		Random gen = new Random();
		randomInt = gen.nextInt(inventory.size() );
		Artifact a = (Artifact) inventory.get(randomInt);
		return (a.name() );
	}
	// Displays DEBUGGING info
	public void print() {
		System.out.println(this.ID);
		System.out.println(this.name);
		System.out.println(this.description);
	}
	public void display() {

	}
}