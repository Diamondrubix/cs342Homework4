import java.lang.reflect.Array;
import java.util.*;
/*
main character class. Instances of this class should not be made. Only children should
 */
public class Character{

    protected static boolean arePlayers = false;
    protected int ID;
    protected String name;
    protected String description;
    protected Place location;
    protected ArrayList<Artifact> inventory = new ArrayList<Artifact>();
    private static HashMap<Integer,Character> characters= new HashMap<Integer, Character>();
    private static ArrayList<Integer> characterIDs = new ArrayList<Integer>();

    public static void addCharacter(int id, Character c){
        characters.put(id, c);
        Character.characterIDs.add(id);
    }

    public Character(int i, String n, String desc){
        ID = i;
        name = n;
        description = desc;
        location = Place.places.get(Place.randomID());
    }
    protected Character(int i, String n, String desc, int l){
        ID = i;
        name = n;
        description = desc;
        location = Place.places.get(l);
    }
    public static boolean arePlayers(){
        return arePlayers;
    }

    public Character(Scanner sc){
        //better remove
        String line = sc.nextLine();
        line = line.replaceAll("//.*", "");
        while (line.equals("")&&sc.hasNextLine()) {
            line = sc.nextLine();
            line = line.replaceAll("//.*", "");
        }
        //better remove //end

        if(line.length()>=10) {
            if (!line.substring(0, 10).equals("CHARACTERS")) {
                System.out.println("No Characters" + line.substring(0, 10));
                return;
            }
        }else{
            System.out.println("No Characters");
            return;
        }
        int numCharacters = Integer.parseInt(line.replaceAll("\\D",""));
        if(numCharacters == 0){
            System.out.println("No Characters");
            return;
        }
        //better remove
        line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end

        for(int i = 0; i <numCharacters;i++){
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
                placeID = Place.randomID();
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

            if(i+1!=numCharacters) {
                //better remove
                line = sc.nextLine();
                line = line.replaceAll("//.*", "");
                while (line.equals("")) {
                    line = sc.nextLine();
                    line = line.replaceAll("//.*", "");
                }
                //better remove //end
            }
        }

    }

    public static void playAll(){
        for(int i: characterIDs){
            characters.get(i).makeMove();
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



    public void makeMove(){
        System.out.println("this is the character move function. this should not be running.");
    }


    //returns the character with a matching id
    public static Character getCharacterByID(int id){
        return characters.get(id);
    }

    /*
    lets you view your inventory with item name and description.
     */
    protected void viewInventory(){
        for(int i =0; i<inventory.size();i++){
            inventory.get(i).desc();
        }
    }

    /*
uses the specified artifact.
 */
    protected boolean use(String thing){
        for(int i =0; i< inventory.size();i++){
            String name = inventory.get(i).getName().toLowerCase();
            if(name.equals(thing)){
                inventory.get(i).use();
                return true;

            }
        }
        return false;
    }
    /*
    adds artifact to inventory
     */
    public boolean addToInventory(Artifact a){
        inventory.add(a);
        return true;
    }

    /*
drops the specified artifact
 */
    protected boolean drop(String thing){
        for(int i =0; i< inventory.size();i++){
            String name = inventory.get(i).getName().toLowerCase();
            if(name.equals(thing)){
                location.addArtifact(inventory.get(i));
                inventory.remove(i);
                return true;
            }
        }
        return false;
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



}
