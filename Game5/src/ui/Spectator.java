package ui;

import javax.swing.*;

/**
 *a simple gui for the spectator to see all the moves that every other player is doing
 *
 * @author Adam Arato (aarato2)
 */
public class Spectator {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    private JFrame frame;
    private JPanel panel;
    private JTextArea textOutput;
    private JScrollPane areaScrollPane;

    public Spectator(){
        frame = new JFrame();
        panel = new JPanel();

        textOutput= new JTextArea(35,30);
        textOutput.setLineWrap(true);
        textOutput.setWrapStyleWord(true);
        areaScrollPane = new JScrollPane(textOutput);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //textOutput.setEditable(fals
        // e);
        panel.add(areaScrollPane);


        frame.add(panel);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    /**
     * it will display the text on the jframe.
     * @param msg
     */
    public void display(String msg){
        System.out.println(msg);
        textOutput.append(msg+'\n');

    }
}
