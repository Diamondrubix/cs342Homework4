package ui;

import javax.swing.*;

public class Spectator {
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 300;

    private JFrame frame;
    private JPanel panel;
    private JTextArea textOutput;
    private JScrollPane areaScrollPane;

    public Spectator(){
        frame = new JFrame();
        panel = new JPanel();

        textOutput= new JTextArea(10,300);
        textOutput.setLineWrap(true);
        textOutput.setWrapStyleWord(true);
        areaScrollPane = new JScrollPane(textOutput);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        textOutput.setEditable(false);
        panel.add(areaScrollPane);


        frame.add(panel);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display(String msg){
        textOutput.append("stuff");
    }

}
