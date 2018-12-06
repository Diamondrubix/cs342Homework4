/* Author: Alexander Oey (aoey2) */
package ui.one;

import ui.UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;

/**
 * This class represents a graphical user interface.
 * This user interface is not intended to be used with the command line.
 *
 * @author Alexander Oey (aoey2)
 */
public class GUI_1 implements UserInterface {
	private final JFrame frame;
	
	public GUI_1() {
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		frame.setVisible(true);
		frame.pack();
	}
	
	/**
	 * The display method for this class. 
	 * This will print the message to the screen.
	 *
	 * @param message message to display
	 */
	public void display(String message) {
		
	}
	
	/**
	 * Get user input method for this class.
	 * This will use various graphical user interface components to obtain the input.
	 *
	 * @return user input
	 */
	public String getLine() {
		return "";
	}
	
	/* Set up components for the frame. Extract to other classes later. */
	
	private void init() {
		JTextArea dialog = new JTextArea("all dialog", 20, 10);
		JScrollPane scroll = new JScrollPane(dialog); 
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		JButton endTurnButton = new JButton("End Turn");
		frame.getContentPane().add(endTurnButton, BorderLayout.SOUTH);
		
		JLabel nameLabel = new JLabel("Player");
		frame.getContentPane().add(nameLabel, BorderLayout.NORTH);
		
		JTextArea inventory = new JTextArea("inventory", 5, 2);
		frame.getContentPane().add(inventory, BorderLayout.EAST);
		
		JTextArea stats = new JTextArea("stats", 5, 2);
		frame.getContentPane().add(stats, BorderLayout.WEST);
	}
}