/* Author: Alexander Oey (NetID: aoey2) */
package game;

import static game.Move.MoveType;
import game.Place;
import game.Artifact;
import game.DecisionMaker;
import game.Move;
import game.CleanLineScanner;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Character {
	//Fields.
	private final int ID;
	private final String name;
	private final String description;
	private Place current = null;
	private final ArrayList<Artifact> inventory = new ArrayList<>();
	protected DecisionMaker decider; 
	
	//Static fields.
	private static final HashMap<Integer, Character> characters = new HashMap<>();
	
	/**
	 * Returns the Character associated with a given ID number or null
	 *
	 * @param id Character ID
	 * @return a Character object if the ID exists, null otherwise.
	 */
	static Character getCharacterByID(int id) {
		return characters.get(id);
	}
	
	/**
	 * Constructs a character with id, name, and description.
	 *
	 * @param ID Character ID number
	 * @param name character name
	 * @param desc detailed information about the character
	 */
	public Character(int ID, String name, String desc) {
		this.ID = ID;
		this.name = name;
		this.description = desc;
		Character.characters.put(ID, this);
		current = Place.getRandom(); //Start in random place.
		current.addCharacter(this);
	}
	
	/**
	 * Constructs a character given a Scanner to a file.
	 *
	 * @param input Scanner instance
	 * @param version GDF file version
	 */
	public Character(Scanner input, int version) {
		//Get place information.
		String[] tokens = CleanLineScanner.getTokens(input);
		//PlaceID not provided.
		if (tokens.length < 1) {
			this.ID = -1;
			this.name = "";
			this.description = "";
			return;
		}
		//Set current place.
		int placeID = Integer.parseInt(tokens[0]);
		if (placeID > 0) {
			current = Place.getPlaceByID(placeID);
		}
		else if (placeID == 0) { // Random starting place.
			current = Place.getRandom();
		}
		else { // Invalid
			System.out.println("Error! Invalid PlaceID");
			this.ID = -1;
			this.name = "";
			this.description = "";
			return;
		}
		
		// System.out.println("placeID " + placeID);
		
		//Get data for the character.
		tokens = CleanLineScanner.getTokens(input);
		
		//ID or name not provided.
		if (tokens.length < 2) {
			this.ID = -1;
			this.name = "";
			this.description = "";
			return;
		}
		int id = Integer.parseInt(tokens[0]);
		String charaName = "";
		//Append whitespace separated Place name tokens.
		for (int j = 1; j < tokens.length; ++j) {
			//System.out.println("Token " + j + ": " + tokens[j]);
			charaName += tokens[j] + " ";
		}
		// System.out.println("id " + id);
		// System.out.println("name " + charaName);
		
		//Get Place description.
		int descLines = input.nextInt();
		String desc = "";
		input.nextLine();
		for (int j = 0; j < descLines; ++j) {
			desc += CleanLineScanner.getCleanLine(input.nextLine()) + "\n";
		}
		
		//System.out.println("Place 0" + ": " + id + " " + placeName + " " + desc);
		//Set data fields of this object.
		this.ID = id;
		this.name = charaName.trim();
		this.description = desc;
		Character.characters.put(ID, this);
		current.addCharacter(this);
	}
	
	/**
	 * Executes the an action encapsulated by Move.
	 *
	 * @see game.Move
	 */
	public boolean makeMove() {
		if (this instanceof Player) { //Print room info to player.
			current.display();
		}
		Move m = decider.getMove(this, current);
		
		switch(m.type()) {
			case EXIT: // quit game
				return true;
				
			case LOOK: // re-display current room
				current.display();
				break;
				
			case INVENTORY: // access inventory
				System.out.println("Inventory: ");
				int totalValue = 0, totalMobility = 0;
				for (Artifact a: inventory) {
					a.display();
					totalValue += a.value();
					totalMobility += a.weight();
				}
				System.out.println("");
				System.out.println("Total value: " + totalValue);
				System.out.println("Total mobility: " + totalMobility);
				System.out.println("");
				break;
				
			case GET: // pick up artifact
				Artifact get = current.removeArtifactByName(m.argument());
				if (get != null) {
					System.out.println("Picked up " + get.name());
					this.addArtifact(get);
				}
				else {
					System.out.println("Artifact " + m.argument()
					                   + " does not exist");
				}
				System.out.println("");
				break;
				
			case DROP: // drop artifact
				Artifact drop = findArtifact(m.argument());
				if (drop == null) { // invalid artifact
					System.out.println("Artifact " + m.argument() + 
					                   " does not exist");
				}
				else {
					current.addArtifact(drop);
					inventory.remove(drop);
					System.out.println("Dropped " + drop.name());
				}
				System.out.println("");
				break;
				
			case USE:
				Artifact a = findArtifact(m.argument());
				if (a == null) { // invalid artifact
					System.out.println("Artifact " + m.argument() + 
					                   " does not exist");
				}
				else {
					System.out.println("Used " + a.name());
					a.use(this, current);
				}
				System.out.println("");
				break;
				
			case GO:
				String[] words = m.argument().split("\\s+");
				for (int i = 0; i < words.length; ++i) {
					if (words[i].equalsIgnoreCase("GO")) {
						continue;
					}
					Place prev = current;
					current = current.followDirection(words[i]);
					prev.removeCharacter(this);
					current.addCharacter(this);
					//Went to exit state.
					if (current.isExit()) {
						return true;
					}
				}
				break;
				
			default: // error invalid command
				System.out.println("Invalid move for " + this.name);
				break;
		}
		return false;
	}
	
	/**
	 * Adds an artifact to character inventory.
	 *
	 * @param artifact Artifact instance
	 * @return true if item sucessfully added to inventory
	 */
	public boolean addArtifact(Artifact artifact) {
		return inventory.add(artifact);
	}
	
	/**
	 * Removes an artifact from the character inventory.
	 *
	 * @param artifact artifact to be removed
	 * @return true if inventory contains artifact
	 */
	public boolean removeArtifact(Artifact artifact) {
		return inventory.remove(artifact);
	}
	
	/**
	 * Returns an array containing names of Artifacts in this room.
	 *
	 * @return a String array containing names of artifacts
	 */
	public String[] getArtifacts() {
		String[] names = new String[inventory.size()];
		int i = 0;
		for (Artifact a: inventory) {
			names[i] = a.name();
			i++;
		}
		return names;
	}
	
	/**
	 * Displays user-friendly information about this Character.
	 */
	public void display() {
		System.out.println(this.name);
		System.out.println(this.description);
	}
	
	/*
	 * Prints debugging information to the console.
	 */
	public void print() {
		System.out.println("Character: ID " + this.ID + " name " + this.name);
		System.out.println("Description " + this.description);
	}
	
	/**
	 * Finds artifact in inventory.
	 */
	private Artifact findArtifact(String name) {
		for (Artifact a: inventory) {
			if (a.name().equalsIgnoreCase(name)) {
				return a;
			}
		}
		return null;
	}
}
