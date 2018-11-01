import java.util.Scanner;
/*
not used at the moment
 */
public class CleanLineScanner {

    public static void getCleanLine(Scanner sc){
        //better remove
        String line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end

    }
}
