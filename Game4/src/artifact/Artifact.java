/* Author: Alexander Oey (NetID: aoey2) */
package artifact;

import game.CleanLineScanner;
import game.Game;
import place.Place;
import character.Character;

import java.util.Scanner;

/**
 * This class represents a useable artifact located in rooms.
 *
 * @author Alexander Oey
 */
public class Artifact {
	//Fields.
	private final String name;
	private final String description;
	private final int ID;
	private final int value;
	private final int mobility;
	private final int keyPattern;
	private Place currentPlace;
	private String suitType;
	
	
	/**
	 * Constructs an Artifact given a Scanner to a file and GDF file version.
	 *
	 * @param input Scanner instance
	 * @param version GDF file version
	 * @see java.util.Scanner
	 */
	public Artifact(Scanner input, int version) {
		String[] tokens = CleanLineScanner.getTokens(input);
		
		//One of required fields not provided.
		if (tokens.length < 5) { //Skip malformed declarations.
			this.ID = -1;
			this.name = "";
			this.description = "";
			this.value = -1;
			this.mobility = -1;
			this.keyPattern = 0;
			return;
		}
		
		String artifactName = "";
		//Append whitespace separated Artifact name tokens.
		for (int j = 4; j < tokens.length; ++j) {
			//System.out.println("Token " + j + ": " + tokens[j]);
			artifactName += tokens[j] + " ";
		}
		
		//Get Artifact description.
		int descLines = input.nextInt();
		String desc = "";
		input.nextLine();
		for (int j = 0; j < descLines; ++j) {
			desc += CleanLineScanner.getCleanLine(input.nextLine()) + "\n";
		}
		
		this.ID = Integer.parseInt(tokens[0]);
		this.value = Integer.parseInt(tokens[1]);
		this.mobility = Integer.parseInt(tokens[2]);
		this.keyPattern = Integer.parseInt(tokens[3]);
		this.name = artifactName.trim();
		this.description = desc;
	}
	
	/**
	 * Provides the name of this Artifact object as set in the constructor.
	 *
	 * @return the name of this Artifact
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Provides the more information about this artifact.
	 *
	 * @return the description string
	 */
	public String description() {
		return description;
	}
	
	/**
	 * Provides the value of this Artifact object as set in the constructor.
	 *
	 * @return the value of this Artifact
	 */
	public int value() {
		return value;
	}
	
	/**
	 * Provides the movability of the artifact.
	 *
	 * @return the value of movablity
	 */
	public int weight() {
		return mobility;
	}
	
	/**
	 * Provides the keyPattern of the artifact.
	 *
	 * @return the key pattern.
	 */
	public int getKeyPattern() {
		return keyPattern;
	}
	
	/**
	 * Uses the artifact.
	 */
	public void use(Character character, Place room) {
		if (keyPattern == 0) return;
		room.useKey(this);
	}
	
	/**
	 * Displays user-friendly information about this Artifact object.
	 */
	public void display() {
		System.out.println(this.name);
		System.out.println("Value: " + this.value);
		System.out.println("Weight: " + this.mobility);
		System.out.println(this.description);
	}
	
	/**
	 * Prints debugging information.
	 */
	public void print() {
		System.out.println("Artifact: " + this.name + " with value " + this.value +
		", weight " + this.mobility + " and keyPattern " + this.keyPattern);
		System.out.println("Description: " + this.description);
	}
}