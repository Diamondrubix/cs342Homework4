package character;

import artifact.Artifact;
import artifact.Armor;
import artifact.Armor.ArmorType;
import artifact.Weapon;
import artifact.EquippableArtifact;
import artifact.EquippableType;
import place.Place;

import game.CleanLineScanner;
import game.Game;
import Network.Network;
import ui.IO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Observable;

/**
 * The Character class is not to be used directly, rather the game should implemente the children to move in its place
 *
 * @author Adam Arato
 */


public class Character extends Observable {

    public static HashMap<Integer,Character> characters= new HashMap<Integer, Character>();
    private static ArrayList<Integer> characterIDs = new ArrayList<Integer>();
    protected int ID;
    protected String name;
    protected String description;
    protected Place location;
    protected ArrayList<Artifact> inventory = new ArrayList<>();
		protected ArrayList<EquippableArtifact> equipments = new ArrayList<>();
    protected static boolean arePlayers = false;
    protected int health = 100;
		protected int damage = 10;
    protected Armor armorEquip;
    protected IO io; // Input output interface.
    private static Character currentCharacter;
		
		private int totalValue = 0;

    /*
    gets the total value of a characters inventory
     */
		public int getTotalValue() {
			return totalValue;
		}
		
    /**
		 * Returns the name of the character.
		 *
		 * @return name
     */
    public String getName(){
        return name;
    }
		
		/**
		 * Returns the maximum damage that can be dealt the character.
		 *
		 * @return damage amount
     */
		public int getDamage() {
			return damage;
		}
		
		/** 
		 *
		 */
    public static void setCurrent(Character c){
        currentCharacter = c;
        c.io.noNetDisplay("\nIT IS YOUR TURN");
    }
		
		/**
		 * Helper method to print to this Character instance's 
		 * IO interface.
		 */
    public static void println(String msg){
        currentCharacter.io.display(msg + "\n");
    }
		
		/**
		 * Returns the current active player name.
		 *
		 * @return character name
		 */
    public static String currentPlayerName(){
        return currentCharacter.name;
    }

		
		/**
		 * Constructs a Character instance given a Scanner to a file.
		 *
		 * @param sc Scanner instance to a file
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
				io = new IO();
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

    /**
		 * Returns a Character instance given its id.
		 *
		 * @param id unique integer identifier
		 * @return Character instance with identifier id
		 */
    public static Character getCharacterByID(int i){
        return characters.get(i);
    }

    /*
    adds an artifact to the inventory. The boolean is irrlevent and a depricated features at this point
     */
    public boolean addArtifact(Artifact a){
        inventory.add(a);
				this.setChanged();
				this.notifyObservers(inventoryToString());
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

    /**
		 * Returns a random artifact from the character's inventory
		 *
		 * @return returns null if inventory is empty.
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
		
		/**
		 * Increases the character's health.
		 * 
		 * @param h the amount of health added
		 */
    public void heal(int h){
        health+=h;
    }

		/**
		 * Reduces the character's health.
		 *
		 * @param d the amount of health taken away
		 */
    public void damage(int d){
        health-=d;
    }

    /**
		 * Drops the artifact given by its name from character inventory to 
		 * the floor.
		 *
		 * @param thing artifact name
		 * @return true if sucessful, false if the artifact is not in inventory
		 */
    public boolean drop(String thing){
        for(int i =0; i< inventory.size();i++){
            String name = inventory.get(i).name().toLowerCase();
            if(name.equals(thing.toLowerCase())){
                location.addArtifact(inventory.get(i));
                inventory.remove(i);
								this.setChanged();
								this.notifyObservers(inventoryToString());
                return true;
            }
        }
        return false;
    }
		
		/**
		 * Returns an Artifact instance given a string name of the artifact.
		 * Returns null if the artifact does not exist in this Character's 
		 * inventory.
		 *
		 * @param str artifact name
		 * @return Artifact instance
		 */
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
		public boolean isEquipped(EquippableType type) {
			for (EquippableArtifact ea: equipments) {
				if (ea.isEquipType(type)) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Equips an Equippable item.
		 *
		 * @param str Artifact instance of an Equippable item
		 */
		public void equipArtifact(Artifact artifact) {
			if (artifact instanceof EquippableArtifact) {
				equipments.add((EquippableArtifact) artifact);
				inventory.remove(artifact);
				Character.println(artifact.name() +" equipped.");
				this.setChanged();
				this.notifyObservers(inventoryToString());
			}
			else {
				Character.println(artifact.name() +" is not an equippable Artifact.");
			}
			// Increase stat.
			if (artifact instanceof Weapon) {
				damage += ((Weapon)artifact).getDamage();
				// System.out.println("current damage " + ((Weapon)artifact).getDamage());
			}
		}
		
		/**
		 * Un-equips an Equippable item.
		 *
		 * @param name Artifact name of an Equippable item
		 */
		public void unequipArtifact(String name) {
			for (Artifact a : equipments) {
				if (a.name().equals(name)) {
					equipments.remove(a);
					inventory.add(a);
					// Decrease stat.
					if (a instanceof Weapon) {
						damage -= ((Weapon) a).getDamage();
					}
					
					//Tell observer to update state.
					this.setChanged();
					this.notifyObservers(inventoryToString());
				}
			}
		}
		
		/*
		smple helper function to check not null
		 */
		public boolean checkFor(String str) {
			return (strToArtifact(str) != null);
		}
		
		/**
		 * Removes all equipment and drops all items to the current location.
		 */
		public void dropAll() {
			this.calculateTotalValue();
			for (Artifact a: equipments) {
				equipments.remove(a);
				inventory.add(a);
			}
			//Drop all inventory
			for (Artifact a: inventory) {
				location.addArtifact(a);
			}
			inventory.clear();
		}
		
		/**
		 * This method is called at the end of each character's turn. 
		 * 
		 * @return always returns true
		 */
    public boolean makeMove(){
        //System.out.println("this is the character move function. this should not be running.");
        location.ambientFunction(this);
				if (health <= 0) {
					// Remove all equipments
					Game.removeCharacter(this);
					Character.println(name +" has died.");
				}
				return true;
    }
		
		/**
		 * Changes the location of the character.
		 *
		 * @param p target location
		 */
    public void setLocation(Place p){
        location = p;
    }
		
		/**
		 * Builds the list of items in inventory without its descriptions
		 * onto a String object. 
		 * The string is fully formatted with newline characters.
		 *
		 * @return string containing list of items in inventory
		 */
		private String inventoryToString() {
			StringBuilder message = new StringBuilder();
			for(Artifact a : inventory){
				message.append(a.name()).append("\n");
				message.append("Value: ").append(a.value()).append("\n");
				message.append("Weight: ").append(a.weight()).append("\n").append("\n");
				// message.append(a.description()).append("\n");
			}
			return message.toString();
		}
		
		private void calculateTotalValue(){
			int val = 0;
			for(int i =0; i < inventory.size();i++){
					val+=inventory.get(i).value();
			}
			for (Artifact a: equipments) {
				val += a.value();
			}
			totalValue = val;
		}

}
