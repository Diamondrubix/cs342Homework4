// Name: Zain Zahran
// Netid: zzahra2
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GameTester {
	public static void main(String []args) throws FileNotFoundException {
		System.out.println("Name: Zain Zahran");
		System.out.println("Netid: zzahra2\n");
		String filename = "";
		if (args.length > 0)
			filename = args[0];		// Filename is initially a command line argument 
		Scanner input;
		// Continue requesting input until a file is open
		while (true) {
			try {
				if ((filename.toUpperCase()).equals("QUIT") ) return;	// Exit program
				input = new Scanner(new File(filename) );
				break;	// File name found, break out of loop
			} catch(FileNotFoundException ex) {		//If command line argument is not found or incorrent
				System.out.println(ex.getMessage());
				System.out.print("Please enter a filename with its extension: ");
				Scanner sc = new Scanner(System.in);
				filename = sc.nextLine();
			}
		}
		Game gm = new Game(input);
		gm.play();
	}
}