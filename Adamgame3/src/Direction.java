import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
holds the variouse paths that a character can traverse. Goes from one place to another. May be locked
could be unlocked with a key
 */

public class Direction {

    //all the directions in a enume
    public enum dirType{
        north("north","n"),
        south("south", "s"),
        east("east", "e"),
        west("west","w"),
        down("down", "d"),
        up("up", "u"),
        nowhere("none", "nowhere"),
        northEast("northeast", "ne"),
        northWest("northwest","nw"),
        southEast("southeast", "se"),
        southWest("southwest", "sw"),
        northnorthwest("north-northwest", "nnw"),
        northnortheast("north-northeast", "nne"),
        eastnortheast("east-northeast", "ene"),
        westnorthwest("west-northwest", "wnw"),
        eastsoutheast("east-southeast", "ese"),
        westsouthwest("west-southwest", "wsw"),
        southsoutheast("south-southeast", "sse"),
        southsouthwest("south-southwest", "ssw");

        private final List<String> values;

        dirType(String ...values) {
            this.values = Arrays.asList(values);
        }

        //this turns the basic text into a dirtype to be used in the constructor when parsing the directions
        public static dirType getType(String s){
            switch(s){
                case "n":
                    return north;
                case "s":
                    return south;
                case "e":
                    return east;
                case "w":
                    return west;
                case "d":
                    return down;
                case "u":
                    return up;
                case "ne":
                    return northEast;
                case "nw":
                    return northWest;
                case "se":
                    return southEast;
                case "sw":
                    return southWest;
                case "nnw":
                    return northnorthwest;
                case "nne":
                    return northnortheast;
                case "ene":
                    return eastnortheast;
                case "wnw":
                    return westnorthwest;
                case "ese":
                    return eastsoutheast;
                case "wsw":
                    return westsouthwest;
                case "sse":
                    return southsoutheast;
                case "ssw":
                    return southsouthwest;
            }
            return nowhere;
        }
        /*
        returns a random direction. only used for the NPC class at the moment
         */
        public static dirType randomDirection(){

            int pick = new Random().nextInt(dirType.values().length);
            return dirType.values()[pick];


        }


        public List<String> getValues() {
            return values;
        }
        /*
        determins if the given input is a direction
         */
        public static boolean isDir(String s){
            for(dirType d: dirType.values()){
                if(d.getValues().contains(s)){
                    return true;
                }
            }
            return getType(s)!=nowhere;

        }

    }

    /*
     NONE, N, S, E, W, U, D, NE, NW, SE,
SW, NNE, NNW, ENE, WNW, ESE, WSW, SSE, and SSW
     */

    private int ID;
    private Place from;
    private Place to;
    private dirType dir;
    private boolean locked;
    private int lockPattern;

    /*//old
    public Direction(int ID, Place from, Place to, String dir){
        this.ID = ID;
        this.from = from;
        this.to = to;
        this.dir = ;
        locked = false;

    }
    */

    /*
    old consturctor for the original version of the game
     */
    public Direction(int ID, Place from, Place to, String dir, int lockP){
        this.ID = ID;
        this.from = from;
        this.to = to;
        dir = dir.toLowerCase();
        this.dir = dirType.getType(dir);
        this.lockPattern = lockP;
        locked = false;

    }

    /*
    helper constructor used in the scanner constructor to create the directions
     */
    private Direction(int ID, Place from, Place to, String dir, int lockP, boolean islocked){
        this.ID = ID;
        this.from = from;
        this.to = to;
        dir = dir.toLowerCase();
        this.dir = dirType.getType(dir);
        this.lockPattern = lockP;
        locked = islocked;

    }

    //main consturctor that takes a scanner object
    public Direction(Scanner sc){
        //better remove
        String line = sc.nextLine();
        line = line.replaceAll("//.*", "");
        while (line.equals("")) {
            line = sc.nextLine();
            line = line.replaceAll("//.*", "");
        }
        //better remove //end



        if(!line.substring(0,10).equals("DIRECTIONS")){
            System.out.println("Not DIRECTION Error: "+line.substring(0,10));
        }
        int numDirections = Integer.parseInt(line.replaceAll("\\D",""));
        //better remove
        line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end
        for(int i = 0 ; i< numDirections; i++){
            String[] s = line.split("\\s\\s*");
            int dirID = Integer.parseInt(s[0]);
            Place from = Place.places.get(Integer.parseInt(s[1]));
            boolean locked = Integer.parseInt(s[3])<0;
            Place to = Place.places.get(Math.abs(Integer.parseInt(s[3])));
            String dir = s[2];
            int LockPattern = Integer.parseInt(s[4]);

            //creates the directions

            Place.places.get(Integer.parseInt(s[1])).addDirection(new Direction(dirID, from,to,dir,LockPattern,locked));


            if(i+1!=numDirections) {
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


    //checks to see if the direction matches the dirtypes enum
    public boolean match(String d){
        return dir.getValues().contains(d);

    }

    //locks the path
    public void lock(){
        locked = true;
    }
    //unlocks the path
    public void unlock(){

        locked = false;
    }

    //checks the keys pattern against the directions lock
    public boolean useKey(Artifact a){
        if(a.correctKey(lockPattern)){
            unlock();
            return true;
        }
        return false;
    }




    //used to go to a new room, checks to see if it's locked or not
    public Place follow(){
        if(!locked){
            return to;
        }
        return from;
    }





}
