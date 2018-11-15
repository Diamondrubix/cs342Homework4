package character;

import artifact.Artifact;
import artifact.Armor;
import artifact.Armor.ArmorType;
import place.Place;

import game.CleanLineScanner;
import game.Game;
import Network.Network;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The Character class is not to be used directly, rather the game should implemente the children to move in its place
 *
 * @author Adam Arato
 */


public class Character {

    public static HashMap<Integer,Character> characters= new HashMap<Integer, Character>();
    private static ArrayList<Integer> characterIDs = new ArrayList<Integer>();
    protected int ID;
    protected String name;
    protected String description;
    protected Place location;
    protected ArrayList<Artifact> inventory = new ArrayList<Artifact>();
    protected static boolean arePlayers = false;
    protected int health = 100;
    protected Armor armorEquip;


	/*
	main constructor by which characters are made.
	 */
    public Character(Scanner sc){
				//Get place information.
				String[] tokens = CleanLineScanner.getTokens(sc);
				//PlaceID not provided.
				if (tokens.length < 1) {
					this.ID = -1;
					this.name = "";
					this.description = "";
					return;
				}
				//Set current place.
				int placeID = Integer.parseInt(tokens[0]);
				if (placeID > 0) {
					location = Place.getPlaceByID(placeID);
				}
				else if (placeID == 0) { // Random starting place.
					location = Place.getRandomPlace();
				}
				else { // Invalid
					System.out.println("Error! Invalid PlaceID");
					this.ID = -1;
					this.name = "";
					this.description = "";
					return;
				}
				
				// System.out.println("placeID " + placeID);
				
				//Get data for the character.
				tokens = CleanLineScanner.getTokens(sc);
				
				//ID or name not provided.
				if (tokens.length < 2) {
					this.ID = -1;
					this.name = "";
					this.description = "";
					return;
				}
				int id = Integer.parseInt(tokens[0]);
				String charaName = "";
				//Append whitespace separated Place name tokens.
				for (int j = 1; j < tokens.length; ++j) {
					//System.out.println("Token " + j + ": " + tokens[j]);
					charaName += tokens[j] + " ";
				}
				// System.out.println("id " + id);
				// System.out.println("name " + charaName);
				
				//Get Place description.
				int descLines = sc.nextInt();
				String desc = "";
				sc.nextLine();
				for (int j = 0; j < descLines; ++j) {
					desc += CleanLineScanner.getCleanLine(sc.nextLine()) + "\n";
				}
				
				//System.out.println("Place 0" + ": " + id + " " + placeName + " " + desc);
				//Set data fields of this object.
				this.ID = id;
				this.name = charaName.trim();
				this.description = desc;
				Character.characters.put(ID, this);
				location.addCharacter(this);
    }

    /*
    a secondary constructor to make players if the gdf load has none
     */
    protected Character(int i, String n, String desc, int l){
        ID = i;
        name = n;
        description = desc;
        location = Place.getPlaceByID(l);
    }

    /*
    returns a character by its id
     */
    public static Character getCharacterByID(int i){
        return characters.get(i);
    }

    /*
    adds an artifact to the inventory. The boolean is irrlevent and a depricated features at this point
     */
    public boolean addArtifact(Artifact a){
        inventory.add(a);
        return true;
    }

    /*
    dislays health and armor
     */
    public void print(){
        System.out.println("health: " + health);
				if (armorEquip != null) {
					System.out.println("Equipped: " + armorEquip.name());
				}
    }

    /*
			helper function to move characters
		 */
    protected void moveCharacter(Place temp){

        location.removeCharacter(this);
        temp.addCharacter(this);
        location = temp;
    }

    /*
			lets you view your inventory with item name and description.
		 */
    public void viewInventory(){
        for(int i =0; i<inventory.size();i++){
            inventory.get(i).display();
        }
    }

    /*
			grabs and returns a random object from the characters inventory. 
			Will be null if inventory is empty.
		 */
    protected Artifact randomArtifactFromInventory(){
        int max = inventory.size();
        if(max == 0){
            return null;
        }
        max--;
        int min = 0;
        int choice = (int) ((Math.random() * ((max - min) + 1)) + min);
        return inventory.get(choice);

    }

    public void heal(int h){
        health+=h;
    }

    public void damage(int d){
        health-=d;
    }

    /*
			drops the specified artifact
		*/
    public boolean drop(String thing){
        for(int i =0; i< inventory.size();i++){
            String name = inventory.get(i).name().toLowerCase();
            if(name.equals(thing.toLowerCase())){
                location.addArtifact(inventory.get(i));
                inventory.remove(i);
                return true;
            }
        }
        return false;
    }

    public Artifact strToArtifact(String str){
        str = str.toLowerCase();
        for(int i =0; i< inventory.size();i++){
            if(str.equals(inventory.get(i).name().toLowerCase())){
                return inventory.get(i);
            }
        }
        return null;
    }
		
		/**
		 * Checks if a certain type of armor is being used.
		 *
		 * @param armor type of armor
		 * @return true if ArmorType armor is equipped
		 */
		public boolean armorEquipped(ArmorType armor) {
			if (armorEquip == null) return false;
			return (armorEquip.getType() == armor);
		}
		
		/**
		 * Equips armor.
		 *
		 * @param armor Armor object
		 */
		public void equipArmor(Artifact armor) {
			if (armor instanceof Armor) {
				armorEquip = (Armor) armor;
				inventory.remove(armor);
			}
			else {
				System.out.println("Not an Armor");
			}
		}
		/*
		smple helper function to check not null
		 */
		public boolean checkFor(String str) {
			return (strToArtifact(str) != null);
		}

    public boolean makeMove(){
        //System.out.println("this is the character move function. this should not be running.");
        location.ambientFunction(this);
				if (health <= 0) {
					Game.removeCharacter(this);
					Network.netPrintln(name +" has died.");
				}
				return true;
    }

    public void setLocation(Place p){
        location = p;
    }

}
