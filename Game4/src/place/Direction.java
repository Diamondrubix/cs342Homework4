// Name: Zain Zahran
// Netid: zzahra2

package place;

import game.Place;
import game.Game;
import game.CleanLineScanner;

import java.util.*;

// Represents a direction or path from one Place/room to another
public class Direction {
	private int ID;
	private Place from;
	private Place to;
	private Boolean locked;
	private int lockPattern;	// Artifact has a corresponding keyPattern
	private DirType dir;		// Enum defined at bottom of Direction class

	// Constructor for Direction object.
	// ID - source - direction - destination - lockPattern
	Direction(Scanner s) {

		String[] tokens = CleanLineScanner.getTokens(input);
			
		//One of required fields not provided.
		if (tokens.length < 5) { //Skip malformed declarations.
			this.ID = -1;
			this.from = null;
			this.to = null;
			this.direction = DirType.getEnum("NONE");
			this.lockPattern = 0;
			this.locked = false;
			return;
		}

		this.ID = Integer.parseInt(tokens[0]);
		this.from = Place.getPlaceByID(Math.abs(Integer.parseInt(tokens[1])));
		this.dir = DirType.getEnum(tokens[2].trim());
		
		int toID = Integer.parseInt(tokens[3]);
		if (toID < 0) {
			locked = true;
		}
		this.to = Place.getPlaceByID(Math.abs(toID));
		this.lockPattern = Integer.parseInt(tokens[4]);

		//Add direction object to "from" room.
		from.addDirection(this);

	}
	// Constructor for direction
	// Note: String is passed in order to use valueOf to get enum value
	Direction(int ID, Place from, Place to, String dir) {
		this.ID = ID;
		this.from = from;
		this.to = to;
		this.dir = DirType.valueOf(dir);	// Use valueOf() to get the enum of it

		locked = false;	// All new directions are by default unlocked
	}
	Direction(int ID, Place from, Place to, String dir, int lockPattern) {
		this.ID = ID;
		this.from = from;
		this.to = to;
		this.lockPattern = lockPattern;
		this.dir = DirType.valueOf(dir);	// Use valueOf() to get the enum of it
		locked = false;	// All new directions are by default unlocked
	}
	// Returns true if the string s matches that of the stored direction, dir
	// Further Enhance ("N", "n", "NORTH", "north", "North")
	public Boolean match(String s) {
		return (this.dir).match(s);	// Calls the match(String) method of DirType
	}
	// If keyPattern of the artifact is positive and equal to lockPattern, toggle state of dir lock
	public void useKey(Artifact a) {
		if (a.keyPattern() > 0 && a.keyPattern() == this.lockPattern) {
			locked = !(locked);
			System.out.println(locked);
			System.out.println("Used " + a.name() + "!\n");
		}
		else {
			System.out.println("Keypattern does not match.\n");
		}
	}
	// Locks this direction
	public void lock() {
		locked = true;
	}
	// Unlocks this direction
	public void unlock() {
		locked = false;
	}
	// Returns true if Direction is locked
	public Boolean isLocked() {
		if (locked == true)
			return true;
		return false;
	}
	// Returns the "to" Place corresponding to this Direction IF it is unlocked
	// Else, return the "from" place
	public Place follow() {
		// Confirm this works...
		if (this.locked == false)			
			return to;
		// Locked, return original
		System.out.println("\nThat direction is locked. Find the key and use it!");
		return from;
	}
	public void print() {
		// Direction ID
		System.out.println("`direction ID: " + this.ID);
		System.out.println("DirType: " + dir);
	}
	// Prints out all of the Direction information. DEBUGGING AND TESTING ONLY
	public void print_DEBUG() {
		System.out.println("Direction");
		System.out.println("ID: " + ID);
		System.out.println("FROM: " + from.name() );
		System.out.println("TO: " + to.name() );
		System.out.println("dir: " + dir);
		System.out.println("Locked: " + locked);
		System.out.println("LockPattern: " + lockPattern);
	}
	public String getDirType() {
		return (this.dir).toString();
	}
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
}