// Zain Zahran
// Netid: zzahra2

import java.util.*;

// AI implementation decides what move the NPC character make
// !!NOT ALLOWED COMMANDS -> "LOOK", "QUIT", "EXIT", and "INVENTORY"
public class AI implements DecisionMaker {
	@Override
	public Move getMove(Character c, Place p) {
		System.out.println("\nGetting move for " + c.name() +"...");
		String[] commands = {"GO", "GET", "DROP", "USE", "NONE"};
		Random gen = new Random();
		int randomInt = gen.nextInt(commands.length);
		// Generate a random command for AI to make
		String moveType = commands[randomInt];
		/* NONE */
		// Do nothing command
		if (moveType.equals("NONE") ) {
			return new Move("NONE");
		}
		/* GO */
		else if (moveType.equals("GO") ) {
			String d = p.getRandomDir();
			if (d.equals("") ) { return new Move("NONE"); }
			return new Move("GO", d);
		}
		/* GET */
		else if (moveType.equals("GET") ) {
			String a = p.getRandomArt();
			if (a.equals("") ) { return new Move("NONE"); }
			return new Move("GET", a);
		}
		/* DROP */
		else if (moveType.equals("DROP") ) {
			String a = c.getRandomArt();
			if (a.equals("") ) { return new Move("NONE"); }
			return new Move("DROP", a);
		}
		/* USE */
		else if (moveType.equals("USE") ) {
			String a = c.getRandomArt();
			if (a.equals("") ) { return new Move("NONE"); }
			return new Move("USE", a);
		}
		/* NOTHING */
		return new Move("NONE");
	}

}