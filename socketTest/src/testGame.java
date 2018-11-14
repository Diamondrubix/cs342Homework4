import sun.nio.ch.Net;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class testGame {
    public static void main(String args[]) throws IOException {
        Server server;
        Client client;
        System.out.println("hi");
        Scanner sc = new Scanner(System.in);
        int Playerone = Integer.parseInt(sc.nextLine());
        if(Playerone==1){
            server =Network.getServer();
            server.sendData("some data");
        }else{
            client = Network.getCLient();
            System.out.println(client.getData());
        }
    }
}
