/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Artifact;
import game.Place;
import game.Character;
import game.DecisionMaker;
import game.KeyboardScanner;
import game.Move;
import static game.Move.MoveType;

import java.util.Scanner;

/**
 * Handles user input for player character.
 *
 * @author Alexander Oey (aoey2)
 */
public class PlayerInterface implements DecisionMaker {	
	
	/**
	 * Encapsulates user input into a Move object.
	 *
	 * @return Move object containing user command
	 */
	@Override
	public Move getMove(Character character, Place place) {
		System.out.println("Player: ");
		character.display();
		Scanner sc = KeyboardScanner.getKeyboardScanner();
		String input = sc.nextLine().toUpperCase().trim();
			
		//Quit.
		if (input.equals("QUIT") || input.equals("Q") ||
			input.equals("EXIT")) {
			return new Move(MoveType.EXIT, "");
		}
		//Redisplay current place.
		else if (input.equals("LOOK")) {
			return new Move(MoveType.LOOK, "");
		}
		//Access inventory.
		else if (input.equals("INVE") || input.equals("INVENTORY")) {
			return new Move(MoveType.INVENTORY, "");
		}
		//Pick up artifact.
		else if (input.contains("GET")) {
			String[] words = input.split("\\s+");
			String artifactName = "";
			// Append tokenized names.
			for (int i = 1; i < words.length; ++i) {
				artifactName += words[i] + " ";
			}
			return new Move(MoveType.GET, artifactName.trim());
		}
		//Drop or use artifact.
		else if (input.contains("DROP")) {
			String[] words = input.split("\\s+");
			// Append tokenized names.
			String artifactName = "";
			for (int i = 1; i < words.length; ++i) {
				artifactName += words[i] + " ";
			}
			return new Move(MoveType.DROP, artifactName.trim());
		}
		else if (input.contains("USE")) {
			String[] words = input.split("\\s+");
			// Append tokenized names.
			String artifactName = "";
			for (int i = 1; i < words.length; ++i) {
				artifactName += words[i] + " ";
			}
			return new Move(MoveType.USE, artifactName.trim());
		}
		//Move to other rooms.
		else {
			return new Move(MoveType.GO, input);
		}
	}
}