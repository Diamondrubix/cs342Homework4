import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
the game class holds the game and needs simply be initialized and the play function run to start.
 */
public class Game {

    private String name;
    private ArrayList<Place> places;


    /*
    takes a scanner object to start reading in a file from a GDF file. it will then pass it onto the
    place, artifact and direction class to start parsing things
     */
    public Game(Scanner sc){
        String line =sc.nextLine();
        /*
        if(!line.substring(0,3).equals("GDF")){
            System.out.println("Error: "+line.substring(0,3));
            return false;
        }
        */

        new Place(sc);
        new Direction(sc);
        if(sc.hasNextLine()) {
            new Character(sc);
        }
        if(sc.hasNextLine()) {
            new Artifact(sc);
        }

        //Game.location = Place.places.get(12);
        sc.close();

        if(!Character.arePlayers()){
            System.out.print("there are no players. how many players would you like?\nplease input a number: ");
            Scanner sr = new Scanner(System.in);
            int numPlayers = Integer.parseInt(sr.nextLine());
            for(int i =0; i <numPlayers;i++){
                System.out.print("enter a player id: ");
                int id = Integer.parseInt(sr.nextLine());
                System.out.print("\nenter a player name: ");
                String n = sr.nextLine();
                Character.addCharacter(id, new Player(id, n, "custom player id:"+id));
            }
        }

    }

    /*
    other constructor for older version of the game
     */
    public Game(String n){
        name = n;
        places = new ArrayList<Place>();
    }

    /*
    depricated function from the older version of the game
     */

    public void addPlace(Place p){
        places.add(p);
        /*
        if(Game.location == null){
            Game.location = p;
        }
        */
    }

    /*
    this is the function you run in order the start the entire game
    it has a switch statement to deal with the main commands and uses a tokenizer to parse the commands
     */
    public void play(){
        for(;;) {
            Character.playAll();
        }

    }







}
