/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Artifact;
import game.Place;
import game.Character;
import game.DecisionMaker;
import game.KeyboardScanner;
import game.Move;
import static game.Move.MoveType;

import java.util.Random;

/**
 * This class handles actions of non-player characters
 *
 * @author Alexander Oey(aoey2)
 */
public class AI implements DecisionMaker {
	//Fields.
	private final Random r;
	
	/**
	 * Constructs an AI object.
	 */
	public AI() {
		r = new Random();
	}
	
	/**
	 * Encapsulates random number generator based actions.
	 *
	 * @return Move object containing selected action
	 */
	@Override
	public Move getMove(Character character, Place place) {
		int pick = 0;
		while (true) {
			switch(r.nextInt(4)) {
				case 0: //Pick up artifact.
					String[] pickArtifact = place.getArtifacts();
					// System.out.println("AI length0: "+ pickArtifact.length);
					if (pickArtifact.length <= 0) continue;
					pick = r.nextInt(pickArtifact.length);
					return new Move(MoveType.GET, pickArtifact[pick]);
					
				case 1: //Drop artifact.
					String[] dropArtifact = character.getArtifacts();
					//System.out.println("AI length1: "+ dropArtifact.length);
					if (dropArtifact.length <= 0) continue;
					pick = r.nextInt(dropArtifact.length);
					return new Move(MoveType.DROP, dropArtifact[pick]);
					
				case 2: // Use artifact.
					String[] useArtifact = character.getArtifacts();
					// System.out.println("AI length2: "+ useArtifact.length);
					if (useArtifact.length <= 0) continue;
					pick = r.nextInt(useArtifact.length);
					return new Move(MoveType.USE, useArtifact[pick]);
				
				case 3: //Move to other rooms.
				default:
					String[] directionNames = place.getDirections();
					// System.out.println("AI length3: "+ directionNames.length);
					if (directionNames.length <= 0) continue;
					pick = r.nextInt(directionNames.length);
					return new Move(MoveType.GO, directionNames[pick]);
			}
		}
	}
	
}