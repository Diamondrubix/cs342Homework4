// Zain Zahran
// Netid: zzahra2

import java.util.*;


// UI conducts alot of the work formerly in HW 2 Game.play() method
// Displays current location to user
// Gets a command from keyboard
// Verifies it is a valid command
// Encapsulates user input into Move object
public class UI implements DecisionMaker {
	
	@Override
	public Move getMove(Character c, Place p) {
		String userDir = "";		// Will come as a token from a split up string
		String artifactName = "";	// Will come as a token
		boolean validMove = false;
		while (!validMove) {	
			System.out.println("\n-== it is " + c.name() + "'s turn ==-\n");
			/* DISPLAY CURRENT PLACE OF USER*/
			p.print();
			// Start a scanner to getline
			Scanner scanner = new Scanner (System.in).useDelimiter("\\n");
			System.out.print("Where would you like to go? > ");
			// Get MoveType
			String moveType = (scanner.next() ).toUpperCase();

			/* EXIT - 1 Player */ 
			// Option to exit the game
			if (moveType.equals("QUIT") || moveType.equals("EXIT") ) {
				validMove = true;
				return new Move("QUIT");
			}
			/* EXIT - Entire Game */
			if (moveType.equals("QUITALL") || moveType.equals("EXITALL") ) {
				validMove = true;
				return new Move("QUIT");
			}
			/* LOOK */
			if (moveType.equals("LOOK") || moveType.equals("L") ) {
				return new Move("LOOK");
			}
			/* INVENTORY */
			if (moveType.equals("INVENTORY") || moveType.equals("INVE") ) {
				return new Move("INVE");
			}
			/* NONE */
			// Do nothing command
			if (moveType.equals("NONE") ) {
				validMove = true;
				return new Move("NONE");
			}

			/* GET - DROP - USE - GO */
			
			// Split up string into tokens
			String[] tokens = moveType.split("\\s");	// Use REGEX
			int i = 0;	// Token-word index
			
			for (String s : tokens) {
				s = s.toUpperCase();
				if (s.equals("GET") || s.equals("DROP") || s.equals("USE") ) {
					/* GET ARTIFACT NAME FROM USER INPUT */
					artifactName = "";
					i++;	// Go one token AFTER "GET"/"DROP"/"USE"
					if (i < tokens.length ) { 
						artifactName += tokens[i];			// Add first word without any spacing
						i++;
					}
					while (i < tokens.length ) {
						artifactName += " " + tokens[i];	// Add with spacing
						i++;
					}
					validMove = true;
					// Return result as move object
					return new Move(s, artifactName);
				}
				/* GO */
				// Directional Commands
				if (dirCheck(s) ) {
					validMove = true;
					return new Move("GO", s);
				} // END IF STATEMENT
			}
			validMove = false;
			System.out.println("\nPlease make a valid move. Type \"NONE\" to skip a turn.\n" );
		}
			return new Move("NONE");
	}
	
	// Helper function that returns true/false if a string equals a direction command
	boolean dirCheck(String s) {
		if (s.equals("N") || s.equals("E") || s.equals("S") || s.equals("W") || s.equals("U") || s.equals("D")
						|| s.equals("NE") || s.equals("NW") || s.equals("SE") || s.equals("SW") || s.equals("NNE")
						|| s.equals("NNW") || s.equals("ENE") || s.equals("WNW") || s.equals("ESE") || s.equals("WSW")
						|| s.equals("SSE") || s.equals("SSW")
						|| s.equals("NORTH") || s.equals("EAST") || s.equals("SOUTH") || s.equals("WEST") || s.equals("UP") || s.equals("DOWN")
						|| s.equals("NORTH-EAST") || s.equals("NORTH-WEST") || s.equals("SOUTH-EAST") ||  s.equals("SOUTH-WEST")
						|| s.equals("NORTH-NORTHEAST") || s.equals("NORTH-NORTHWEST") || s.equals("EAST-NORTHEAST") || s.equals("WEST-NORTHWEST")
						|| s.equals("EAST-SOUTHEAST") || s.equals("WEST-SOUTHWEST") || s.equals("SOUTH-SOUTHEAST") || s.equals("SOUTH-SOUTHWEST") ) 
		{
			return true;
		}
		return false;
	}
}

// // Main method to play the whole game (Called in GameTester.java -> Main() )
// 	void play() {
// 		// for (Place p : places) {
// 		// 	p.print_DEBUG();
// 		// }
// 		while (true) {		
// 			String userDir = "";		// Will come as a token from a split up string
// 			String artifactName = "";	// Will come as a token
// 			// Start input/output
// 			Scanner scanner = new Scanner (System.in).useDelimiter("\\n");	// Initialize scanner so it delimits input using a new line character (ie. getline() )
// 			Game.getCurrentPlace().print();
// 			System.out.print("Where would you like to go? > ");
// 			String userInput = scanner.next();
// 			userInput = userInput.toUpperCase();
// 			// Option to exit the game
// 			if (userInput.equals("QUIT") || userInput.equals("EXIT") ) {
// 				break;
// 			}
// 			// Redisplay current location if requested
// 			if (userInput.equals("LOOK") ) {
// 				System.out.println();
// 				continue;	// No need to follow any directions at this time
// 			}
// 			// Split up string into tokens
// 			String[] tokens = userInput.split("\\s");	// Use REGEX

// 			int i = 0;
// 			// List all artifacts in possession of player
// 			if (userInput.equals("INVE") || userInput.equals("INVENTORY") ) {
// 				if (inventory.isEmpty() ) { 
// 					System.out.print("\nYour inventory is empty!\n\n"); 
// 					continue;
// 				}
// 				int totalWeight = 0;
// 				System.out.print("\nItems in your inventory: ");
// 				for (Artifact a : inventory) {
// 					System.out.print(a.name() + ", ");
// 					totalWeight += a.weight();
// 				}
// 				System.out.println("\nTotal weight you're carrying: " + totalWeight + "\n");
// 				System.out.println();
// 				continue;
// 			}
// 			for (String s : tokens) {

// 				/* Get artifact name from command */
// 				if (s.equals("GET") || s.equals("DROP") || s.equals("USE")) {
// 					userInput = s;
// 					/* GET ARTIFACT NAME FROM USER INPUT */
// 					artifactName = "";
// 					i++;	// Go one token AFTER "GET"/"DROP"/"USE"
// 					if (i < tokens.length ) { 
// 						artifactName += tokens[i];			// Add first word without any spacing
// 						i++;
// 					}
// 					while (i < tokens.length ) {
// 						artifactName += " " + tokens[i];	// Add with spacing
// 						i++;
// 					}
// 					/* GET/DROP/USE COMMANDS */
// 					// Get
// 					if (s.equals("GET") ) {
// 						if (artifactName.equals("") ) break;	// Error checking
// 						// Make sure the artifact exists in Place
// 						if (getCurrentPlace().containsArtifact(artifactName) == false) {
// 							break;
// 						}
// 						// Remove artifact from place and add to inventory
// 						else {
// 							inventory.add( getCurrentPlace().removeArtifact(artifactName) );
// 						}
// 					}
// 					// Drop
// 					if (s.equals("DROP") ) {
// 						if (artifactName.equals("") ) break;	// Error checking
// 						for (Artifact a : inventory) {
// 							if (a.name().toUpperCase().equals(artifactName) ) {
// 								inventory.remove(a);					// Remove from player inventory
// 								getCurrentPlace().addArtifact(a);		// Add dropped artifact to current place
// 								break;
// 							} 
// 						}
// 					}
// 					// Use
// 					if (s.equals("USE") ) {
// 						if (artifactName.equals("") ) break; // Error checking
// 						// Make sure artifact exists in inventory
// 						for (Artifact a : inventory) {
// 							if (a.name().toUpperCase().equals(artifactName) ) {
// 								a.use();
// 								System.out.println("\nItem used!");
// 							}
// 						}
// 					}
// 					break;
// 				}
// 				// Directional Commands
// 				if (s.equals("N") || s.equals("E") || s.equals("S") || s.equals("W") || s.equals("U") || s.equals("D")
// 						|| s.equals("NE") || s.equals("NW") || s.equals("SE") || s.equals("SW") || s.equals("NNE")
// 						|| s.equals("NNW") || s.equals("ENE") || s.equals("WNW") || s.equals("ESE") || s.equals("WSW")
// 						|| s.equals("SSE") || s.equals("SSW")
// 						|| s.equals("NORTH") || s.equals("EAST") || s.equals("SOUTH") || s.equals("WEST") || s.equals("UP") || s.equals("DOWN")
// 						|| s.equals("NORTH-EAST") || s.equals("NORTH-WEST") || s.equals("SOUTH-EAST") ||  s.equals("SOUTH-WEST")
// 						|| s.equals("NORTH-NORTHEAST") || s.equals("NORTH-NORTHWEST") || s.equals("EAST-NORTHEAST") || s.equals("WEST-NORTHWEST")
// 						|| s.equals("EAST-SOUTHEAST") || s.equals("WEST-SOUTHWEST") || s.equals("SOUTH-SOUTHEAST") || s.equals("SOUTH-SOUTHWEST") ) {
// 					userDir = s;
// 					// Follow the indicated direction from the user
// 					Game.setCurrPlace(getCurrentPlace().followDirection(userDir));

// 				} // END IF STATEMENT

// 				i++;
// 			} // End for loop

// 			if (userDir.equals("") && artifactName.equals("") ) {
// 				System.out.println("Bad input. Try again.\n");	// Bad input
// 			}
// 			else {			
// 				// User reached exit? We should probably check that...
// 				try {
// 					if (getCurrentPlace().getPlace() == 0 ) {
// 						//
// 					}
// 				} catch (NullPointerException e) {
// 					System.out.println("You have exited the building.");
// 					break;	// Exit the game.
// 				}
// 			}
// 			System.out.println();

// 		}

// 	}