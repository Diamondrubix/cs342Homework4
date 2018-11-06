// Name: Zain Zahran
// Netid: zzahra2
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
		this.locked = false;	// Directions are by default unlocked

		this.ID = s.nextInt();
		int fromID = s.nextInt();
		this.dir = DirType.valueOf(s.next());
		int toID = s.nextInt();
		this.lockPattern = s.nextInt();
		if (toID < 0) {
			this.locked = true;	// Negative destinations indicated locked direction
		}
		from = Place.getPlaceByID(fromID);
		to   = Place.getPlaceByID(toID);
		/* DEBUG STATEMENT */
		// System.out.println(this.ID + " " + fromID + " " + this.dir + " " + toID + " " +this.lockPattern );
		// Add itself to Place corresponding to its "from" field
		(Place.getPlaceByID(fromID)).addDirection(this);
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
	Boolean match(String s) {
		return (this.dir).match(s);	// Calls the match(String) method of DirType
	}
	// If keyPattern of the artifact is positive and equal to lockPattern, toggle state of dir lock
	void useKey(Artifact a) {
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
	void lock() {
		locked = true;
	}
	// Unlocks this direction
	void unlock() {
		locked = false;
	}
	// Returns true if Direction is locked
	Boolean isLocked() {
		if (locked == true)
			return true;
		return false;
	}
	// Returns the "to" Place corresponding to this Direction IF it is unlocked
	// Else, return the "from" place
	Place follow() {
		// Confirm this works...
		if (this.locked == false)			
			return to;
		// Locked, return original
		System.out.println("\nThat direction is locked. Find the key and use it!");
		return from;
	}
	void print() {
		// Direction ID
		System.out.println("`direction ID: " + this.ID);
		System.out.println("DirType: " + dir);
	}
	// Prints out all of the Direction information. DEBUGGING AND TESTING ONLY
	void print_DEBUG() {
		System.out.println("Direction");
		System.out.println("ID: " + ID);
		System.out.println("FROM: " + from.name() );
		System.out.println("TO: " + to.name() );
		System.out.println("dir: " + dir);
		System.out.println("Locked: " + locked);
		System.out.println("LockPattern: " + lockPattern);
	}
	String getDirType() {
		return (this.dir).toString();
	}
	/* ENUM */
	// An enum DirType to be used for all directions
	private enum DirType {
		/* Predefined constants for 19 direction types */
		NONE("None", "None"), N("North", "N"), S("South", "S"), 
		E("East", "E"), W("West", "W"), U("Up","U"), D("Down", "D"), 
		NE("North-East", "NE"), NW("North-West","NW"), 
		SE("South-East","SE"), SW("South-West", "SW"), 
		NNE("North-Northeast", "NNE"), NNW("North-Northwest","NNW"), 
		ENE("East-Northeast","ENE"), WNW("West-Northwest","WNW"), 
		ESE("East-Southeast","ESE"), WSW("West-Southwest","WSW"), 
		SSE("South-Southeast","SSE"), SSW("South-Southwest","SSW");
		// Data fields
		private String text;
		private String abbrev;
		// Constructor for creating DirType
		DirType(String text, String abbrev) {
			this.text = text;
			this.abbrev = abbrev;
		}
		// Returns the text field
		public String toString() {
			return this.text;
		}
		// Returns true if the given string matches either
		// the text or the abbreviation, ignoring case.
		Boolean match(String s) {
			s = s.toUpperCase();
			if (s.equals( (this.text).toUpperCase() ) || s.equals( (this.abbrev).toUpperCase() )) {
				return true;
			}
			return false;
		}
	}
}