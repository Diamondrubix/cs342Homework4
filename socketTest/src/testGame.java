import sun.nio.ch.Net;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class testGame {
    public static String line;
    public static Server server;
    public static Client client;
    public static void main(String args[]) throws IOException {
        int port = 3001;

        System.out.println("type in the ip of the server, or type 1 to be the server");
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();
        if(line.equals("1")){
            System.out.println("you are playing 1");
            new Server(port).start();
            client = Network.getCLient("localhost",port);
        }else{
            System.out.println("you are not player 1");
            client = Network.getCLient(line,port);
        }
        new InputHandler().start();

        while(true){
            System.out.println(client.getData());
        }
    }

    private static class InputHandler extends Thread{

        public void run(){
            Scanner sc = new Scanner(System.in);
            while(!line.equals("exit")) {
                line = sc.nextLine();
                client.sendData(line);
            }

        }


    }
}
