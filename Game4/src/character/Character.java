package character;

import artifact.Artifact;
import place.Place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Character {

    private static HashMap<Integer,Character> characters= new HashMap<Integer, Character>();
    private static ArrayList<Integer> characterIDs = new ArrayList<Integer>();
    protected int ID;
    protected String name;
    protected String description;
    protected Place location;
    protected ArrayList<Artifact> inventory = new ArrayList<Artifact>();
    protected static boolean arePlayers = false;


    public Character(Scanner sc){
        //better remove
        String line = sc.nextLine();
        line = line.replaceAll("//.*", "");
        while (line.equals("")&&sc.hasNextLine()) {
            line = sc.nextLine();
            line = line.replaceAll("//.*", "");
        }
        //better remove //end

        //int i, String n, String desc //these are the characters constructors
        line = line.replaceAll("\\s\\s*"," ");
        String[] s =line.split("\\s");
        String type =s[0];
        int placeID = Integer.parseInt(s[1]);

        //better remove
        line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end

        s = line.split("\\s");
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


        //System.out.println("name "+name+" desc "+desc+"id "+id);
        type = type.toLowerCase();

        //picks a random place id
        if(placeID == 0){
            placeID = Place.getRandomID();
        }

        if(type.equals("player")){
            Character.characters.put(id, new Player(id, name, desc,placeID));
        }else if(type.equals("npc")){
            Character.characters.put(id, new NPC(id,name,desc, placeID));
        }else{
            System.out.println("something went wrong, we will be making a regular character object");
            Character.characters.put(id, new Character(id,name,desc,placeID));
        }

        characterIDs.add(id);


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


    public boolean makeMove(){
        return true;
    }


}
