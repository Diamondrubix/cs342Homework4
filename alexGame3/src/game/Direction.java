/* Author: Alexander Oey (NetID: aoey2) */
package game;

import game.Place;
import game.Game;
import game.CleanLineScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represents the connection or path between two Place objects. 
 * Each object of this class represents one way connection between two Place
 * objects.
 *
 * @author Alexander Oey (aoey2)
 * @see Place
 */
public class Direction {
	/** This enum represents the directions recognized by the Direction class.
	 *
	 * @author Alexander Oey
	 */
	private enum DirType {
		NONE("NONE"),
		UP("UP", "U", "UPSTAIRS"),
		DOWN("DOWN", "D", "DOWNSTAIRS"),
		NORTH("NORTH","N"),
		SOUTH("SOUTH", "S"),
		EAST("EAST", "E"),
		WEST("WEST", "W"),
		NORTHEAST("NORTHEAST", "NE"),
		NORTHWEST("NORTHWEST", "NW"),
		SOUTHEAST("SOUTHEAST", "SE"),
		SOUTHWEST("SOUTHWEST", "SW"),
		NORTHNORTHEAST("NORTHNORTHEAST", "NNE"),
		NORTHNORTHWEST("NORTHNORTHWEST", "NNW"),
		SOUTHSOUTHEAST("SOUTHSOUTHEAST", "SSE"),
		SOUTHSOUTHWEST("SOUTHSOUTHWEST", "SSW"),
		EASTNORTHEAST("EASTNORTHEAST", "ENE"),
		EASTSOUTHEAST("EASTSOUTHEAST", "ESE"),
		WESTNORTHWEST("WESTNORTHWEST", "WNW"),
		WESTSOUTHWEST("WESTSOUTHWEST", "WSW");

		//Temporarily hold names for one direction.
		private final String[] aliases; 
		//Mapping input to a direction object. 
		private static final Map<String, DirType> directions = new HashMap<>();
		
		//Collect move strings in aliases array to directions map.
		static {
			for (DirType dir: DirType.values()) {
				for (String name: dir.aliases) {
					directions.put(name, dir);
				}
			}					
		}
		
		//Constructor taking variable number of parameters.
		private DirType(String... names) {
			//This should never happen as constructor is never called outside enum.
			if (names.length < 1) { //No String names provided.
				throw new IllegalArgumentException("Insufficient arguments.");
			}
			aliases = names;
		}
		
		/** Converts a String representation of a direction to the 
		 * DirType enum representation. The name argument is case
		 * insensitive. 
		 * <p>
		 * Returns null if name is not defined in the DirType enum.
		 *
		 * @param name the String name of a direction
		 * @return Directions object of a direction.
		 * @see Directions
		 */
		private static DirType getEnum(String name) {
			//Find provided string in directions map.
			DirType direction = directions.get(name.toUpperCase());
			if (direction == null) {
				direction = DirType.NONE;
			}
			return direction;
		}
		
		/**
		 * Matches the given string matches to any of the aliases of the calling
		 * DirType, ignoring case.
		 *
		 * @param name String to be searched.
		 * @return true if the given string matches the calling DirType
		 */
		public boolean match(String name) {
			return (this.equals(directions.get(name.toUpperCase())));
		}
		
		/**
		 * Returns the string representation of the calling DirType.
		 *
		 * @return String name of the DirType.
		 */
		public String toString() {
			return aliases[0];
		}
	}
	
	//Fields.
	private final int ID;
	private final Place from;
	private final Place to;
	private final DirType direction;
	private boolean locked;
	private final int lockPattern;
	
	/**
	 * Constructs a Direction that specifies a path between from and to with
	 * the direction dir. All new Direction objects are by default unlocked.
	 *
	 * @param ID assigned integer number
	 * @param from the Place where the path originates
	 * @param to the Place where the path ends
	 * @param dir the orientation of the path as defined by DirType
	 * @throws IllegalArgumentException if the dir is not defined by DirType
	 * @see DirType
	 */
	public Direction(int ID, Place from, Place to, String dir) {
		this.ID = ID;
		this.from = from;
		this.to = to;
		this.direction = DirType.getEnum(dir.trim());
		this.locked = false; //By default unlocked.
		this.lockPattern = 1;
		
		//Should never happen.
		if (this.direction == null)
			throw new IllegalArgumentException("Error: invalid direction.");
		//Add direction object to "from" room.
		from.addDirection(this);
	}
	
	/**
	 * Constructs a Direction given a Scanner object to a file.
	 * Direction is locked if lockPattern is non-zero.
	 *
	 * @param input Scanner instance
	 * @see java.util.Scanner
	 */
	public Direction(Scanner input, int version) {
		String[] tokens = CleanLineScanner.getTokens(input);
			
		//One of required fields not provided.
		if (tokens.length < 5) { //Skip malformed declarations.
			this.ID = -1;
			this.from = null;
			this.direction = DirType.getEnum("NONE");
			this.to = null;
			this.lockPattern = 0;
			return;
		}
		this.ID = Integer.parseInt(tokens[0]);
		this.from = Place.getPlaceByID(Math.abs(Integer.parseInt(tokens[1])));
		this.direction = DirType.getEnum(tokens[2].trim());
		
		int toID = Integer.parseInt(tokens[3]);
		if (toID < 0) {
			locked = true;
		}
		this.to = Place.getPlaceByID(Math.abs(toID));
		this.lockPattern = Integer.parseInt(tokens[4]);
		//Add direction object to "from" room.
		from.addDirection(this);
		//for (int i = 0; i < 5; ++i)
		//	System.out.println("token[" + i + "] =" + tokens[i]);
		
		/*System.out.println("Direction " + ID + " " + from.name() + " " +
		direction.toString() + " " + to.name() + " lock: " + lockPattern);*/
	}
	
	/**
	 * Compares String s to the direction represented by this object.
	 * If the String does not represent any of the directions defined in Directions,
	 * the return value will be false.
	 * 
	 * @param s String name of the direction
	 * @return true if the given string matches the direction represented by 
	 *         this object, false otherwise.
	 * @see DirType
	 */
	public boolean match(String s) {
		return direction.match(s);
	}
	
	/**
	 * Disables this path.
	 */
	public void lock() {
		if (lockPattern == 0) {
			return;
		}
		locked = true;
	}
	
	/**
	 * Enables this path.
	 */
	public void unlock() {
		if (lockPattern == 0) {
			return;
		}
		locked = false;
	}
	
	/**
	 * Toggles the state of this direction's lock.
	 *
	 * @param artifact the item to attempt to open the door.
	 * @return true if lock state is changed.
	 */
	public boolean useKey(Artifact artifact) {
		//Cannot change lock status.
		if (lockPattern == 0) {
			return false;
		}
		//Toggle locked status.
		else if (lockPattern == artifact.getKeyPattern()) {
			System.out.println("Door unlocked.");
			locked = !locked;
			return true;
		}
		//Do nothing
		return false;
	}
	
	/**
	 * Checks if the path is disabled.
	 *
	 * @return true if path is disabled, false otherwise
	 */
	public boolean isLocked() {
		return locked;
	}
	
	/**
	 * Attempts to go through this direction.
	 *
	 * @return destination Place object if sucessfull, 
	 *         otherwise returns source Place object.
	 */
	public Place follow() {
		//Print advertisement :D.
		if (to.name().equalsIgnoreCase("Nowhere")) {
			System.out.println(to.description());
			return from;
		}
		//Return from room if door is locked.
		if (locked) {
			System.out.println("Door is locked.");
			return from;
		}
		return to;
	}
	
	/**
	 * Provides the String name of the orientation of the path as defined by
	 * the Directions enum.
	 *
	 * @return the String representation of the orientation.
	 */
	public String getName() {
		//Direction object name in Directions enum.
		return direction.toString();
	}
	
	/**
	 * Prints debugging information to the console.
	 */
	public void print() {
		//Debug information.
		System.out.print("Direction: ID " + this.ID + " from " + from.name() 
		+ " " + direction.toString() + " to " + to.name() + " and lockPattern" + 
		this.lockPattern + " ");
		if (locked) System.out.println("locked");
		else System.out.println("unlocked");
	}
}