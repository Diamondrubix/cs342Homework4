import java.util.StringTokenizer;
/*
used to store the move information. Some helper functions included as well.
 */
public class Move {
    public String args;
    public MoveType type;
    public enum MoveType{
        go,godir, get, drop, look, use, exit, inventory, invalid, wait
    }


    public Move(String text){
        text = text.toLowerCase();
        args = text;
        StringTokenizer st = new StringTokenizer(text);
        text = st.nextToken();


        switch (text) {
            case "get":
                type = MoveType.get;
                break;
            case "drop":
                type = MoveType.drop;
                break;
            case "look":
                type = MoveType.look;
                break;
            case "inventory":

            case "inve":
                type = MoveType.inventory;
                break;
            case "use":
                type = MoveType.use;
                break;
            case "wait":
                type = MoveType.wait;
                break;
            case "exit":

            case "quit":
                type = MoveType.exit;
                break;
            case "go":
                type = MoveType.go;
                break;
            default:
                if(Direction.dirType.isDir(text)){
                    type = MoveType.go;
                }else{

                    type = MoveType.invalid;
                }
        }
    }




    /*
    private enum MoveType{


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
        public static Direction.dirType getType(String s){
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


        public List<String> getValues() {
            return values;
        }

    }
    */

}
