/* Author: Alexander Oey (aoey2) */
package game;

import java.util.Scanner;

/**
 * Utility class to parse GDF file.
 *
 * @author Alexander Oey (aoey2)
 */
public class CleanLineScanner {
	/**
	 * Cleans comments and whitespaces from a line of String from file input.
	 *
	 * @param line string to be cleaned
	 * @return clean string
	 */
	public static String getCleanLine(String line) {
		return line.replaceAll("//.*", "").trim();
	}
	
	/**
	 * Moves sc pointer to a non-empty non-comment line and tokenizes the words
	 * in that line using whitespace as delimiter.
	 *
	 * @param sc Scanner instance to a file
	 * @return String array containing the tokens
	 */
	public static String[] getTokens(Scanner sc) {
		String line = CleanLineScanner.getCleanLine(sc.nextLine());
		while (line.equals("")) {
			line = CleanLineScanner.getCleanLine(sc.nextLine());
		}
		return line.split("\\s+");
	}
}