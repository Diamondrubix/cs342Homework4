
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Trivial client for the date server.
 */
public class Client {
    private String serverAddress;
    private Socket socket;
    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public Client() throws IOException {
        /*
        String serverAddress = JOptionPane.showInputDialog(
                "Enter IP Address of a machine that is\n" +
                        "running the date service on port 9090:");
                        */
        serverAddress = "localhost";
        socket = new Socket(serverAddress, 9090);
        //JOptionPane.showMessageDialog(null, answer);
        //System.exit(0);
    }

    public String getData() {
        String answer = "no answer";
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            answer = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public void sendData(String msg){
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("data from client");

    }

}
