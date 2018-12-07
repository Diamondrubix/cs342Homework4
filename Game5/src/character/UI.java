package character;

import game.KeyboardScanner;
import move.*;
import place.Place;
import ui.IO;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The players decision maker. It takes user input and then returns a move based on that user input
 * Handles userinput
 * @author Adam Arato
 */


public class UI implements DecisionMaker {
    /*
    gets the move information for players
     */
    public Move getMove(Character c, Place location, IO sc) {
        // KeyboardScanner sc = KeyboardScanner.getKeyboardScanner();
        //Scanner sc = new Scanner(System.in);

        String input = sc.getLine();
        input = input.toUpperCase().trim();
				
				// Change gui
				while (input.contains("GUI")) {
					String[] words = input.split("\\s+");
					sc.selectInterface(Integer.parseInt(words[1]));
					sc.addToObservable(c);
					// Ask for input again.
					input = sc.getLine();
					input = input.toUpperCase().trim();
				}
				
				// tokenize.
        String[] args = input.split("\\s+");
				// System.out.println(input);
				if (args.length > 0) {
					//Quit.
					if (args[0].equals("QUIT") || args[0].equals("Q") ||
									args[0].equals("EXIT")) {
							return new Exit(c,location);
					}
					//Redisplay current place.
					else if (args[0].equals("LOOK")) {
							return new Look(c,location);
					}
					//Access inventory.
					else if (args[0].equals("INVE") || args[0].equals("INVENTORY")) {
							return new ShowInventory(c);
					}
					//Pick up artifact.
					else if (args[0].equals("GET")) {
							// System.out.println("GET");
							String[] words = input.split("\\s+");
							String artifactName = "";
							// Append tokenized names.
							for (int i = 1; i < words.length; ++i) {
									artifactName += words[i] + " ";
							}
							return new GetItem(c,location, artifactName.trim());
					}
					//Drop or use artifact.
					else if (args[0].equals("DROP")) {
							String[] words = input.split("\\s+");
							// Append tokenized names.
							String artifactName = "";
							for (int i = 1; i < words.length; ++i) {
									artifactName += words[i] + " ";
							}
							return new DropItem(c,location, artifactName.trim());
					}
					else if (args[0].equals("USE")) {
							// System.out.println("USE");
							String[] words = input.split("\\s+");
							// Append tokenized names.
							String artifactName = "";
							for (int i = 1; i < words.length; ++i) {
									artifactName += words[i] + " ";
							}
							return new UseItem(c,location, artifactName.trim());
					}
					else if (args[0].equals("EQUIP")) {
							// System.out.println("EQUIP");
							String[] words = input.split("\\s+");
							// Append tokenized names.
							String artifactName = "";
							for (int i = 1; i < words.length; ++i) {
									artifactName += words[i] + " ";
							}
							return new Equip(c,location, artifactName.trim());
					}
					else if (args[0].equals("ATTACK")) {
							// System.out.println("ATTACK");
							String[] words = input.split("\\s+");
							// Append tokenized names.
							String targetName = "";
							for (int i = 1; i < words.length; ++i) {
									targetName += words[i] + " ";
							}
							// System.out.println("target: " + targetName);
							Character t = location.getCharacter(targetName.trim());
							return new Attack(c, t, location, c.getDamage());
					}
				}
        //Move to other rooms.
        // else {
            return new Go(c,location, args);
        // }



    }


}
