package character;

import artifact.Artifact;
import place.Place;

import game.CleanLineScanner;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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


    public Character(Scanner sc){
/*         //better remove
        String line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end
        line = line.trim();

        int placeID =Integer.parseInt(line);

        //better remove
        line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end

        String s[] = line.split("\\s");
        int id = Integer.parseInt(s[0]);
        String name = "";
        for(int j =1; j<s.length;j++){
            if(j==s.length-1) {
                name = name + s[j];
            }else{
                name = name + s[j]+" ";
            }
        }
        line = sc.nextLine();
        String desc = "";
        //getting the desc
        int numLines = Integer.parseInt(line);
        for(int j = 0; j<numLines;j++){
            line = sc.nextLine();
            desc=desc+" "+line;
        }

        //picks a random place id
        if(placeID == 0){
            placeID = Place.getRandomID();
        }

        Character.characters.put(id, new Player(id, name, desc,placeID));
        characterIDs.add(id); */
				
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

    protected Character(int i, String n, String desc, int l){
        ID = i;
        name = n;
        description = desc;
        location = Place.getPlaceByID(l);
    }

    public static Character getCharacterByID(int i){
        return characters.get(i);
    }

    public boolean addArtifact(Artifact a){
        inventory.add(a);
        return true;
    }

    public void print(){
        System.out.println("character print");
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
grabs and returns a random object from the characters inventory. Will be null if inventory is empty.
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
            if(name.equals(thing)){
                location.addArtifact(inventory.get(i));
                inventory.remove(i);
                return true;
            }
        }
        return false;
    }

    public Artifact strToArtifact(String str){
        str.toLowerCase();
        for(int i =0; i< inventory.size();i++){
            if(str.equals(inventory.get(i).name().toLowerCase())){
                return inventory.get(i);
            }
        }
        return null;
    }
		
		public boolean checkFor(String str) {
			return (strToArtifact(str) == null);
		}

    public boolean makeMove(){

        System.out.println("this is the character move function. this should not be running.");
        return true;
    }

}
