/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Direction;
import game.Artifact;
import game.Character;
import game.CleanLineScanner;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

/**
 * This class represents a location.
 *
 * @author Alexander Oey (aoey2)
 */
public class Place {
	//Fields.
	private final int ID;
	private final String name;
	private final String description;
	private final ArrayList<Character> characters = new ArrayList<>();
	private final ArrayList<Direction> doors = new ArrayList<>();
	private final ArrayList<Artifact> artifacts = new ArrayList<>();
	
	//Static fields.
	private static final HashMap<Integer, Place> rooms = new HashMap<>();
		
	/**
	 * Returns the Place associated with a given ID number or null
	 *
	 * @param id Place ID
	 * @return a Place object if the ID exists, null otherwise.
	 */
	public static Place getPlaceByID(int id) {
		return rooms.get(id);
	}
	
	/**
	 * Constructs a Place with a name, description, and unique ID.
	 *
	 * @param ID assigned integer number
	 * @param name name of the place
	 * @param description detailed information about the place
	 */
	public Place(int ID, String name, String description) {
		this.ID = ID;
		this.name = name;
		this.description = description;
		Place.rooms.put(ID, this); //Add to Integer-Place mapping.
	}
	
	/**
	 * Constructs a Place given a Scanner to a file.
	 *
	 * @param input Scanner instance
	 * @param version GDF file version
	 * @see java.util.Scanner
	 */
	public Place(Scanner input, int version) {
		//Get data for the starting room.
		String[] tokens = CleanLineScanner.getTokens(input);
		//ID or name of Place not provided.
		if (tokens.length < 2) {
			this.ID = -1;
			this.name = "";
			this.description = "";
			return;
		}
		int id = Integer.parseInt(tokens[0]);
		String placeName = "";
		//Append whitespace separated Place name tokens.
		for (int j = 1; j < tokens.length; ++j) {
			//System.out.println("Token " + j + ": " + tokens[j]);
			placeName += tokens[j] + " ";
		}
		
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
		this.name = placeName.trim();
		this.description = desc;
		Place.rooms.put(ID, this);
	}
	
	/**
	 * Provides the name of this place object as set in the constructor.
	 *
	 * @return the string name of this Place object
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Provides the additional information of this place object as set in 
	 * the constructor.
	 *
	 * @return the description string
	 */
	public String description() {
		return description;
	}
	
	/**
	 * Registers a Direction object to this Place.
	 *
	 * @param dir Direction object
	 * @see Direction
	 */
	public void addDirection(Direction dir) {
		doors.add(dir);
	}
	
	/**
	 * Adds an artifact to this Place.
	 *
	 * @param artifact Artifact object
	 */
	public void addArtifact(Artifact artifact) {
		artifacts.add(artifact);
	}
	
	/**
	 * Removes artifact from this Place by name.
	 *
	 * @param name name of artifact to be removed.
	 * @return the artifact if it exists in this Place, null otherwise
	 */
	public Artifact removeArtifactByName(String name) {
		for (Artifact a: artifacts) {
			if (a.name().equalsIgnoreCase(name)) {
				artifacts.remove(a);
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Adds a Character to this Place.
	 *
	 * @param character Character object
	 * @see game.Character
	 */
	public void addCharacter(Character character) {
		characters.add(character);
	}
	
	/**
	 * Removes a Character from this Place by reference to object.
	 * 
	 * @param character Character object
	 * @see game.Character
	 */
	public void removeCharacter(Character character) {
		characters.remove(character);
	}
	
	/**
	 * Removes artifact from this Place.
	 *
	 * @param artifact Artifact object to be removed
	 * @return true if artifact exists in this Place
	 */
	@Deprecated
	public boolean removeArtifact(Artifact artifact) {
		return artifacts.remove(artifact);
	}
	 
	/**
	 * Passes artifact to the useKey() method of all Directions in this place.
	 *
	 * @param artifact Artifact object
	 */
	public void useKey(Artifact artifact) {
		//For each loop on array list directions.
		for (Direction d: doors) {
			if (d.useKey(artifact)) break;
		}
	}
	
	/**
	 * Attempts to follow the direction provided by a String.
	 *
	 * @param dir String name of the direction.
	 * @return target Place object if the string exists and is a valid path 
	 * from this object. Otherwise, returns this Place.
	 */
	public Place followDirection(String dir) {
		//Find matching direction registered to this room.
		for (Direction d: doors) {
			if (d.match(dir)) { 
				return d.follow();
			}
		}
		//Return itself.
		System.out.println("Invalid direction!");
		return this;
	}
	
	/**
	 * Returns an array containing names of Artifacts in this room.
	 *
	 * @return a String array containing names of artifacts
	 */
	public String[] getArtifacts() {
		String[] names = new String[artifacts.size()];
		int i = 0;
		for (Artifact a: artifacts) {
			names[i] = a.name();
			i++;
		}
		return names;
	}
	
	/**
	 * Returns an array containing names of directions in this room.
	 *
	 * @return a String array containing names of directions
	 */
	public String[] getDirections() {
		String[] names = new String[doors.size()];
		int i = 0;
		for (Direction d: doors) {
			names[i] = d.getName();
			i++;
		}
		return names;
	}
	
	/**
	 * Indicates if this Place object is an exit state.
	 *
	 * @return Returns true if this object is an exit state, false otherwise
	 */
	public boolean isExit() {
		return (this.ID == 1);
	}
	
	/**
	 * Returns a random Place object that is not an exit or knowhere (ID: 0, 1)
	 *
	 * @return randomly selected Place object
	 */
	public static Place getRandom() {
		ArrayList<Integer> index = new ArrayList<>(rooms.keySet());
		Random r = new Random();
		int pick = r.nextInt(index.size());
		while (pick < 2) {
			pick = r.nextInt(index.size());
		}
		return rooms.get(index.get(pick));
	}
	
	/**
	 * Displays user-friendly information about this Place object.
	 */
	public void display() {
		//Text-based for now.
		System.out.println(name());
		System.out.println(description());
		//Exclude expansion and exit places.
		if (this.ID > 1) {
			System.out.print("There are exits leaving this room to the " + 
			   doors.stream().map(Direction::getName)
							 .collect(Collectors.joining(", ")));
			System.out.println(".");
			
			System.out.println("Artifacts in this room: ");
			for (Artifact a: artifacts) {
				a.display();
			}
		}
		
	}
	
	/**
	 * Prints debugging information to the console.
	 */
	public void print() {
		System.out.println("Place: ID " + this.ID + " name " + this.name);
		System.out.println("Description: " + this.description);
		System.out.println("Directions available in this object: ");
		for (Direction dir: doors) {
			dir.print();
		}
		System.out.println("Artifacts available in this room: ");
		for (Artifact a: artifacts) {
			a.print();
		}
		System.out.println("Characters available in this room: ");
		for (Character c: characters) {
			c.print();
		}
	}
	
	/**
	 * Print full debugging information of all known places and the Direction, 
	 * Artifacts, and Characters present in each place.
	 */
	public static void printAll() {
		rooms.forEach((k,v) -> v.print());
		// for(Place p: rooms) {
			// p.print();
		// }
	}
}