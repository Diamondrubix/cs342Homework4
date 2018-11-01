import java.util.Scanner;
import java.util.StringTokenizer;

/*
handles user input to control each player instance's movements
 */

public class UI implements DecisionMaker{
    /*
    gets the move information for players
     */
    @Override
    public Move getMove(Character c, Place location) {
        boolean valid = false;
        Move m = null;
        while(!valid) {
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            //text = text.toLowerCase();
            m = new Move(text);
            if(m.type!=Move.MoveType.invalid){
                valid = true;
            }else{
                System.out.println("input is invalid");
                System.out.print(": ");
            }
        }
        return m;


    }

}
