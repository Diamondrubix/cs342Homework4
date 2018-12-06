/* Author: Adam Arato */
package ui.two;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import game.KeyboardScanner;
import ui.UserInterface;

/**
 * This class represents a graphical user interface.
 * This user interface is not intended to be used with the command line.
 *
 * @author 
 */
public class GUI_2 implements UserInterface {

	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 700;

	private JFrame frame;
	private JPanel panel;
	private JButton send;
	private JLabel label;
	private JTextField commandInput = new JTextField(20);
	private JTextArea textOutput;
	private JScrollPane areaScrollPane;
	private boolean gotInput = false;
	private String line = "";

	public GUI_2() {
		frame = new JFrame();
		panel = new JPanel();
		send = new JButton("Send");
		panel.add(send);

		label = new JLabel("no button clicked");

		panel.add(label);
		panel.add(commandInput);

		textOutput= new JTextArea(10,60);
		textOutput.setLineWrap(true);
		textOutput.setWrapStyleWord(true);
		areaScrollPane = new JScrollPane(textOutput);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//areaScrollPane.setPreferredSize(new Dimension(5, 40));
		//panel.add(textOutput);
		panel.add(areaScrollPane);


		frame.add(panel);

		ActionListener listener = new ClickListener();
		send.addActionListener(listener);

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

	}


	public class ClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//send stuff here
			//label.setText("Button "+event.getActionCommand() +" was clicked");
			//System.out.println("action");
			line = commandInput.getText();
			gotInput = true;
		}

	}
	
	/**
	 * The display method for this class. 
	 * This will print the message to the screen.
	 *
	 * @param message message to display
	 */
	public void display(String message) {
		textOutput.append(message+"\n");
		
	}
	
	/**
	 * Get user input method for this class.
	 * This will use various graphical user interface components to obtain the input.
	 *
	 * @return user input
	 */
	public String getLine() {
		/*
		while(!gotInput){

		}
		gotInput = false;

		return line;
		*/

		KeyboardScanner sc = KeyboardScanner.getKeyboardScanner();
		return sc.nextLine();

	}
}