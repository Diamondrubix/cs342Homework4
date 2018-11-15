package Network;
//used this as a resource http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.io.IOException;

public class Network {
    public static boolean multiplayer = false;
    private static Server server;
    private static Client client;

/*
    public static Server getServer() throws IOException {
        if(server == null){
            server = new Server();
            return server;
        }else{
            return server;
        }
    }
    */

    public static Client getCLient(String server,int port) throws IOException {
        multiplayer = true;
        if(client == null){
            client = new Client(server,port);
            return client;
        }else{
            return client;
        }
    }

    public static Client getCLient() {
        multiplayer = true;
        if(client == null){
            System.out.println("New Client created without specifiying network. prolly not good");
            try {
                client = new Client("localhost",3001);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return client;
        }else{
            return client;
        }
    }






}
