
import java.io.IOException;

//il make this a singleton
public class Network {

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
        if(client == null){
            client = new Client(server,port);
            return client;
        }else{
            return client;
        }
    }






}
