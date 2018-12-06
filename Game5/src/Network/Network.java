package Network;
//used this as a resource http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.io.IOException;

public class Network {
    public static boolean multiplayer = false;
    private static Server server;
    private static Client client;

    /**
     * The Network class allows you to create a singleton of the client object. It also has a static method to easily
     * send and print data at the same time.
     * Network also originally created a server singleton but that was changes and now only servers client.
     *
     * @author Adam Arato
     */

    /*
    returns a client object based on the server ip and port
     */
    public static Client getClient(String server,int port) throws IOException {
        multiplayer = true;
        if(client == null){
            client = new Client(server,port);
            return client;
        }else{
            return client;
        }
    }

    public static void netPrintln(String str){
        //System.out.println(str);
        if(Network.multiplayer) {
            client.sendData(str);
        }
    }

    /*
    will simply return a client object
     */
    public static Client getClient() {
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
