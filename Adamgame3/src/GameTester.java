import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
only here to test the game
 */
public class GameTester {

    public static void main(String[] args) {
        System.out.println("Adam Arato\naarato2\n676174995\nproject3\n\n");

        try {
            Scanner sc = new Scanner(new File("src/"+args[0]));

            Game g = new Game(sc);
            g.play();
        }catch(FileNotFoundException e){
            try {
                Scanner sc = new Scanner(new File(args[0]));

                Game g = new Game(sc);
                g.play();
            }catch(FileNotFoundException ee){
                //System.out.println(ee);
                System.out.println("file name not found");
            }

        }





    }
}
