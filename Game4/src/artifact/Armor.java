/* Author: Alexander Oey (aoey2) */
package artifact;

import Network.Network;
import game.CleanLineScanner;
import game.Game;
import place.Place;
import character.Character;
import artifact.Artifact;

import java.util.Scanner;

/**
 * This class represents an Artifact that is wearable on a character.
 *
 *	These are ID codes that will be used in GDF 5.1 file
 *	~ 0 ~ Lava Armor
 *	~ 1 ~ Space Armor
 *	~ 2 ~ Scuba Armor
 *
 * @author Alexander Oey (aoey2)
 */
public class Armor extends Artifact {
	/**
	 * Represents the type of armor encapsulated by the Armor class.
	 */
	public enum ArmorType {
		NOTARMOR, // should never be used.
		LAVA,
		SPACE,
		SCUBA;
	}
	
	//Fields
	private final ArmorType suitType;
	
	/**
	 * Constructs an armor given a Scanner to a file.
	 *
	 * @param sc Scanner instance
	 */
	public Armor(Scanner sc, int version) {
		super(sc, version);
		String[] tokens = CleanLineScanner.getTokens(sc);
		switch(Integer.parseInt(tokens[0])) {
			case 0: suitType = ArmorType.LAVA; break;
			case 1: suitType = ArmorType.SPACE; break;
			case 2: suitType = ArmorType.SCUBA; break;
			default: suitType = ArmorType.NOTARMOR; break;
		}
		// System.out.println("token[0] : "+tokens[0]);
	}
	
	/**
	 * Returns the type of armor.
	 *
	 * @return type of armor in ArmorType
	 */
	public ArmorType getType() {
		return suitType;
	}
}