package Network;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * The Network class allows you to create a singleton of the client object. It also has a static method to easily
 * send and print data at the same time.
 * Network also originally created a server singleton but that was changes and now only servers client.
 *
 * @author Adam Arato
 */

public class Client {
    private String serverAddress;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    /**
     * I do relize that for a proper singleton this should not exist, but this is the result of constatnly changeing and
     * experimenting with the code. This should not be called directly by anybody
     */
    public Client(String address, int port) throws IOException {
        // Make connection and initialize streams
        serverAddress = address;
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

    }

    /*
    gets data from the server
     */
    public String getData() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failed to get a message";
    }

    /*
    sends data to the server
     */
    public void sendData(String msg){
        out.println(msg);
    }

}
