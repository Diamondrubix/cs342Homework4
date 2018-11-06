// Zain Zahran
// Netid: zzahra2

import java.util.*;

// Decides WHAT move to make
// Character's makeMove IMPLEMENTS the chosen decision
interface DecisionMaker {
	// Single public method
	// Determines the next move to make for the given character
	Move getMove(Character c, Place p);	// Apply game.play()
}

// Class that encapsulates the results of asking a DecisionMaker
// 	to select the next move to make.
public class Move {
	public MoveType moveType;	// What type of move is the user making...?
	private String argument;	// Could be an artifact name or something

	// Default constructor
	Move(String move, String arg) {
		this.moveType = MoveType.valueOf(move);	// Get the enumerated value
		this.argument = arg;
		// this.exit = false;		// Default
	}
	// For moves that only need 1 command
	Move(String move) {
		this.moveType = MoveType.valueOf(move);
		this.argument = "";
	}
	String getArgument() {
		return argument;
	}
	// An enum MoveType to be used for Character.makeMove() method
	public enum MoveType {
		/* Predefined constants for move types */
		GO("GO"),GET("GET"),DROP("DROP"),LOOK("LOOK"),USE("USE"),
		INVENTORY("INVENTORY"), INVE("INVE"), QUITALL("QUITALL"),
		EXITALL("EXITALL"), QUIT("QUIT"), EXIT("EXIT"),
		NONE("NONE"), RETRY("RETRY");
		// Data fields
		private String text;
		private String text2;	// Another name for this action
		// Constructor for creating MoveType
		MoveType(String text) {
			this.text = text;
			this.text2 = "";
		}
		// Constructor for creating MoveType w/ 2 arguments
		MoveType(String text, String text2) {
			this.text = text;
			this.text2 = text2;
		}
		// Returns the text field
		public String toString() {
			return this.text;
		}
		// Returns true if the given string matches either
		// the text or the abbreviation, ignoring case.
		Boolean match(String s) {
			s = s.toUpperCase();
			if (s.equals( (this.text).toUpperCase() ) || s.equals( (this.text2).toUpperCase() )) {
				return true;
			}
			return false;
		}
	}

}