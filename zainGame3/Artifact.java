// Name: Zain Zahran
// Netid: zzahra2

import java.util.*;

// Represents an item that a player can hold/obtain
public class Artifact {
	private String name;		// Name of artifact
	private String description;	// Description of what the artifact is
	private int value;
	private int mobility;		// Mobility is a measure of size or weight.
								// ** Negative value indicates an immovable object

	private int keyPattern;		// KeyPattern is zero for any artifact that cannot
								// act as a key.
	private int placeID;		// Designated location of artifact
	private int artID;			// ID for artifact itself

	// Constructor for creating Artifact object
	// placeID - ID - value - mobility - keyPattern - name - nDesc - description
	Artifact(Scanner s) {
		// Filter data into Artifact object
		this.placeID = s.nextInt();	// Place where the artifact should initially be

		this.artID = s.nextInt();			// Artifact ID
		this.value = s.nextInt();
		this.mobility = s.nextInt();
		this.keyPattern = s.nextInt();
		this.name = s.nextLine().trim();	// Remove these damn spaces
		int nDesc = s.nextInt();			// How many lines of description we need
		this.description = s.nextLine();
		for (int i = 0; i < nDesc; i++) {
			this.description += s.nextLine() + "\n";
		}
		// Placing the artifact
		if (this.placeID < 0) {
			// Character possession
			int charID = this.placeID * -1;	// Convert to positive value
			(Character.getCharacterByID(charID)).addArtifact(this);
		}
		else if (this.placeID == 0) {
			// Random Place
			(Place.getRandomPlace() ).addArtifact(this);
		}
		else {
			// Specified Place
			(Place.getPlaceByID(this.placeID)).addArtifact(this);
		}
		// DEBUG STATEMENT
		// System.out.println(placeID + " " + artID + " " + this.value + " " + this.mobility + " " + this.keyPattern
		// 					+ " " + this.name + " " + nDesc + " " + this.description);

	}
	Artifact(String name, String desc, int value, int mobility, int keyPattern) {
		this.name = name;
		this.description = desc;
		this.value = value;
		this.mobility = mobility;
		this.keyPattern = keyPattern;
	}
	// Returns value of where artifact needs to go
	int placeID() {
		return placeID;
	}
	// Returns value of the artifact
	int value() {
		return value;
	}
	// Returns the movability value
	int weight() {
		return mobility;
	}
	// Returns the name of the artifact
	String name() {
		return name;
	}
	// Returns the description of the artifact
	String description() {
		return description;
	}
	// Returns the KeyPattern
	int keyPattern() {
		return keyPattern;
	}
	// "Uses" the artifact. 
	// TODO: Fix use()
	void use(Place p) {
		p.useKey(this);
	}
	// Used for debugging, prints out full details
	void print_DEBUG() {
		System.out.println("Name: " + name);
		System.out.println("Description: " + description);
		System.out.println("Value: " + value);
		System.out.println("Mobility " + mobility);
		System.out.println("KeyPattern " + keyPattern);
	}
	// For printAll()
	void print() {
		System.out.println("Name: " + name);
		System.out.println("Description: " + description);
	}
};