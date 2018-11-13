package Network;

import Network.Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Network {
    private ServerSocket listener;

    /**
     * Runs the server.
     */
    public Server() throws IOException {
        listener = new ServerSocket(9090);

        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("got the data");

                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }


    public void sendData(String data){

    }


}
