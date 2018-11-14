// Name: Zain Zahran
// Netid: zzahra2

package place;

import artifact.Artifact;
import game.CleanLineScanner;
import character.Character;
import java.util.*;

// Represents as an object ROOM or place in the game
public class Place {
	private int ID;
	private String name, description;
	private Vector <Direction> directions;	// Collection of Directions
	private Vector <Artifact> artifacts;	// Collection of Artifacts in Place
	private Vector <Character> characters;	// Collection of people that are in this Place
	static private Map <Integer, Place> places = new HashMap<Integer, Place>();	// ID lookup on places 
	static boolean firstTime = true;
	// Constructor for creating Place object
	//	** Add newly created place to static collection of known places
	public Place(Scanner s) {
		directions = new Vector<Direction>();
		artifacts = new Vector<Artifact>();
		characters = new Vector<Character>();
		
		String[] tokens = CleanLineScanner.getTokens(s);
		try {
			this.ID = Integer.parseInt(tokens[0]);
			String placeName = "";
			//Append whitespace separated Place name tokens.
			for (int j = 1; j < tokens.length; ++j) {
				placeName += tokens[j] + " ";
			}
			this.name = placeName;
			int numLines = s.nextInt();
			this.description = "";
			s.nextLine();
			for (int i = 0; i < numLines; i++) {
				this.description += CleanLineScanner.getCleanLine(s.nextLine()) + "\n";
			}
			
			places.put(this.ID, this);	// Put into static collection of Places
			/* DEBUG STATEMENTS */
			// System.out.println(this.ID);
			// System.out.println(this.name);
			// System.out.println(numLines);
			// System.out.println(this.description);
		} catch (InputMismatchException e) {
			// Failed file parsing, incorrect assigning.
		}
	}
	// Constructor for creating the place object
	public Place(int ID, String name, String description) {
		this.ID = ID;
		this.name = name;
		this.description = description;
		directions = new Vector<Direction>();
		artifacts  = new Vector<Artifact>();
		characters = new Vector<Character>();
	}
	// NEW in 4.0
	public void addCharacter(Character c) {
		characters.add(c);
	}

	// Adds an Artifact object to this Place's collection of Artifacts
	public void addArtifact(Artifact a) {
		artifacts.add(a);
	}
	public Boolean containsArtifact(String name) {
		for (Artifact a : artifacts) {
			if (name.equals(a.name().toUpperCase() )) {
				return true;
			}
		}
		return false;
	}
	// Remove artifact from Place's artifact collection
	// Return that artifact to be stored in user inventory (in Game)
	public Artifact removeArtifact(String name) {
		for (Artifact a : artifacts) {
			if (name.equals(a.name().toUpperCase()) && a.weight() >= 0 ) {	// weight must be movable (>= 0)
				artifacts.remove(a);
				return a;
			}
		}
		return null;
	}
	public void removeCharacter(Character c) {
		characters.remove(c);
		// Note: Have not implemented loot-drop
	}
	// Passes the artifact to the useKey() method of all Directions present in this Place
	public void useKey(Artifact a) {
		for (Direction d : directions) {
			d.useKey(a);
		}
	}
	// Returns the Place associated with the given ID number, or null
	// Requires a map
	public static Place getPlaceByID(int id) {
		if (firstTime) {
			firstTime = false;
			/* CREATE EXIT */
			Place p0 = new Place (0, "Exit", "You have exited the building.");
			places.put(p0.getPlace(), p0);
			return p0;
		}
		return places.get(id);
	}
	public static Place getRandomPlace() {
		int randomInt;	// Used to get a random index per the length of array
		// Basically, convert hashmap to array
		// Randomly select a place with random index
		Random gen = new Random();
		Object[] placesArray = places.values().toArray();
		do {
			randomInt = gen.nextInt(placesArray.length);
		} while (randomInt == 0);	// This is to prevent player/NPC/Artifacts from being put in an exit

		Place randomPlace = (Place) placesArray[randomInt];
		return randomPlace;
	}
	// Returns a random string from direction contained in this place
	public String getRandomDir() {
		if (directions.size() < 1) {
			return "";
		}
		int randomInt;
		Random gen = new Random();
		randomInt = gen.nextInt(directions.size() );
		Direction d = (Direction) directions.get(randomInt);
		return (d.getDirType());
	}
	// Return a random place ID
	public static int getRandomID() {
		int randomInt;	// Used to get a random index per the length of array
		// Basically, convert hashmap to array
		// Randomly select a place with random index
		Random gen = new Random();
		Object[] placesArray = places.values().toArray();
		do {
			randomInt = gen.nextInt(placesArray.length);
		} while (randomInt == 0);	// This is to prevent player/NPC/Artifacts from being put in an exit

		Place randomPlace = (Place) placesArray[randomInt];
		return randomPlace.getPlace();	// Return the ID of a random place
	}
	// Return a random string from artifacts contained in this place
	public String getRandomArt() {
		if (artifacts.size() < 1) {
			return "";
		}
		int randomInt;
		Random gen = new Random();
		randomInt = gen.nextInt(artifacts.size() );
		Artifact a = (Artifact) artifacts.get(randomInt);
		return (a.name());
	}
	// Returns name of place
	public String name() {
		return name;
	}
	// Returns the description
	public String description() {
		return description;
	}
	// Adds a Direction obj to this place's COLLECTION of directions
	public void addDirection(Direction dir) {
		directions.add(dir);
	}
	// Checks to see if this place has a a valid unlocked Direction corresponding to string passed
	// If it does correspond, returns the place arrived at (after following)
	// Else, returns itself
	public Place followDirection(String dir) {
		for (Direction d : directions) {
			if (d.match(dir) ) {
				return d.follow();
			}
		}
		return this;
	}
	// Prints out all Place information. DEBUGGING AND TESTING
	public void print_DEBUG() {
		System.out.println("Place");
		System.out.println("ID: " + ID);
		System.out.println("Name: " + name);
		System.out.println("Description: " + description);
		// System.out.println(directions);
	}

	// Prints out the area the user is current in
	public void print() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(description);
		int totalWeight = 0;
		// Print all the available artifacts in the room.
		if ( !artifacts.isEmpty() ) {
			System.out.print("All available artifacts: ");
			for (Artifact a : artifacts ) {
				System.out.print(a.name() + ", ");
				totalWeight += a.weight();
			}
			System.out.println();
		}
	}
	// Returns ID of place
	int getPlace() {
		return ID;
	}
	// Prints all debugging info
	// Directions, artifacts, characters in each place
	public static void printAll() {
		for (Map.Entry<Integer, Place> entry : places.entrySet() ) {

			Place p = entry.getValue();	// Place
			System.out.println("-----------------------------");
			// PLACE ID
			System.out.println("ID: " + p.getPlace() );
			// Name + Desc
			System.out.println("Name: " + p.name() );
			System.out.println("Description: " + p.description() );
			// Directions
			for (Direction d : p.directions) {
				System.out.println("->Direction<-");
				d.print();
			}
			// Artifacts
			for (Artifact a : p.artifacts) {
				System.out.println("==Artifact==");
				a.print();
			}
			// Characters present
			for (Character c : p.characters) {
				System.out.println("~~~Character~~~");
				//c.print();
			}
		}
	}
}