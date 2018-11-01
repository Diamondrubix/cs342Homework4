import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/*
the map is made up of places that are tied together with directions. They hold what players they have,
what artifacts are loocated in them, and they you can go from there.
 */

public class Place {
    private int ID;
    private String name;
    private String desc;

    public static HashMap<Integer,Place> places= new HashMap<Integer, Place>();
    private static ArrayList<Integer> placeIdList = new ArrayList<Integer>();
    private ArrayList<Character> characters = new ArrayList<Character>();
    private ArrayList<Direction> directions;
    private ArrayList<Artifact> artifacts = new ArrayList<Artifact>();


    /*
    main consturctor to be used by the game class to parse the rest of the map
     */
    public Place(Scanner sc){
        //better remove
        String line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end

        if(!line.substring(0,5).equals("PLACE")){
            System.out.println("Not place Error: "+line.substring(0,5));
        }
        int numPlaces = Integer.parseInt(line.replaceAll("\\D",""));

        //better remove
        line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end

        for(int i =0; i<numPlaces;i++){
            //variable setup for the place
            String tempID = line.replaceAll("\\D.*","");
            int id = Integer.parseInt(tempID);
            //String name = line.replaceAll("\\d|\\s","");
            String name = line.substring(tempID.length(),line.length()).trim();

            //better remove
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
            while(line.equals("")){
                line = sc.nextLine();
                line = line.replaceAll("//.*","");
            }
            //better remove //end

            //getting the desc
            int numLines = Integer.parseInt(line);
            String desc = "";
            for(int j = 0; j<numLines;j++){
                line = sc.nextLine();
                desc=desc+" "+line;
            }
            //debug
            //System.out.println("id: "+id+" name: "+name+" desc: "+desc);
            //PlacesL may not be needed
            Place.addPlace(id, new Place(id, name, desc));

            if(i+1!=numPlaces) {
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

    /*
    this function is left public for support of the first version of this game
    this consturctor could be private.
    it is used in the other contructor when parsing the map for the game.
     */
    public Place(int id, String name, String desc){
        this.ID = id;
        this.name = name;
        this.desc = desc;
        directions = new ArrayList<Direction>();
        //Artifact a = new Artifact("thing","thing" ,1 ,1,1);
    }

    //checks to see if it is an exit.
    public boolean isExit(){
        if(ID == 1){
            return true;
        }
        return false;
    }

    //used to display the information about a room, the desc and the artifacts in it.
    public void display(){
        System.out.println(desc);
        displayArtifacts();

    }
    //a getter for the name function
    public String name() {
        return name;
    }

    //adds artifact to the place
    public void addArtifact(Artifact a){
        artifacts.add(a);
    }

    /*
    displays the name and description of all artifacts in the room
     */
    public void displayArtifacts(){
        System.out.println("artifacts here are\n");
        if(artifacts!=null && artifacts.size()!=0) {
            for (Artifact a : artifacts) {
                a.desc();
            }
        }else{
            System.out.println("there are no artifacts here");
        }
    }

    /*
    allows you to pick up an artifact
     */
    public Artifact PickUpArtifact(String a){
        for(int i =0; i<artifacts.size();i++){
            String name = artifacts.get(i).getName().toLowerCase();
            if(name.equals(a)){
                Artifact t = artifacts.get(i);
                if(t.canMove()) {
                    artifacts.remove(i);
                    return t;
                }else{
                    break;
                }
            }
        }
        return null;
    }

    /*
    add a new room ajecent to this one
    @param Direction it is hte direction of
     */
    public void addDirection(Direction d){
        directions.add(d);

    }
    /*
    attemps to go a certain direction and follows the path.
     */
    public Place followDirection(String d){
        for(Direction dir: directions){
            if(dir.match(d)){
                return dir.follow();
            }
        }
        return this;

    }
    private static void addPlace(int id, Place p){
        Place.places.put(id, p);
        Place.placeIdList.add(id);
    }
    /*
    picks out a random place id used in the construction of character and artifacts that need to be placed randomly
    returns the id of the place that was selected.
     */
    public static int randomID(){
        int size = Place.places.size();
        int items =new Random().nextInt(size);
        int selector = 0;
        for(int p: Place.placeIdList){
            if(selector==items){
                return p;
            }
            selector++;
        }
        System.out.println("place randomid function returned 0 error. THIS IS NOT NORMAL");
        return 0;
    }

    /*
    removes a character from this place. returns true if the remove was sucesfull
    mostly used to move a character from one place to another. I would just implement this functionality into the
    followDirection function but I would need to add a parameter for that so this is my work around.
     */
    public boolean removeCharacter(Character c){
        return characters.remove(c);
    }
    /*
    adds a character to this place.
    exists for as similar reason to removeCharacter although this one has more of a puroses
     */
    public void addCharacter(Character c){
        characters.add(c);
    }

    /*
    takes an artifact (a key) and passes it onto all ajacent rooms
     */
    public boolean useKey(Artifact a){
        for(int i =0; i <directions.size();i++){
            if(directions.get(i).useKey(a)){
                return true;
            }
        }
        return false;
    }
    /*
    returns a random artifact and removes it from the place, usefull for the NPC AI
     */
    public Artifact getRandomArtifact(){
        if(artifacts.size() == 0){
            return null;
        }
        int max = artifacts.size()-1;
        int min = 0;
        int choice = (int) ((Math.random() * ((max - min) + 1)) + min);
        Artifact a = artifacts.get(choice);
        //should later make it so it goes through all objects and only returns null if all objecst cannot be moved.
        if(a.canMove()) {
            artifacts.remove(choice);
            return a;
        }else{
            return null;
        }

    }


}
