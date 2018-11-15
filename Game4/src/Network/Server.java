package Network;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


/*
creates a server in another thread in order to start listening to clients and broadcasting their messages to the other
clients
 */

public class Server extends Thread{


    //default port to listen on
    private static int port = 3001;


    /*
     * The set of all the print writers for all the clients. This
     * set is kept so we can easily broadcast messages.
     */
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public Server(int p){
        port = p;
    }


    public void run(){
        try {
            StartServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The appplication main method, which just listens on a port and
     * spawns handler threads.
     */
    private void StartServer(int p) throws IOException {
        port = p;
        System.out.println("Server starting on port "+port);
        ServerSocket listener =new ServerSocket(port);
        try {

            while (true) {
                new Server.Handler(listener.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            listener.close();
        }
    }

    /**
     * just starts the listening in a seperate thread in order not to hold up the entire program
     */
    private static class Handler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        /**
         * just accepts a socket to be able to run
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * constantly listening in a seperate thread in order to get teh data to forward it to the clients
         */
        public void run() {
            try {

                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                writers.add(out);

                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println(input);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {

                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }


}
