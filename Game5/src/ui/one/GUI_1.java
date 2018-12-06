/* Author: Alexander Oey (aoey2) */
package ui.one;

import ui.UserInterface;
import character.Character;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;
import java.awt.Dimension;

import java.util.Observer;
import java.util.Observable;

/**
 * This class represents a graphical user interface.
 * This user interface is not intended to be used with the command line.
 *
 * @author Alexander Oey (aoey2)
 */
public class GUI_1 implements UserInterface, Observer {
	private final JFrame frame;
	private JTextField inputField = new JTextField();
	private JTextArea dialog = new JTextArea("all dialog", 20, 10);
	private JTextArea inventory = new JTextArea("inventory", 20, 10);
	
	private volatile boolean inputReceived = false;
	private volatile String input;
	
	public GUI_1() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setBounds(0,0,800, 600);
		frame.setVisible(true);
	}
	
	/**
	 * The display method for this class. 
	 * This will print the message to the screen.
	 *
	 * @param message message to display
	 */
	public void display(String message) {
		dialog.append(message);
	}
	
	/**
	 * Get user input method for this class.
	 * This will use various graphical user interface components to obtain the input.
	 *
	 * @return user input
	 */
	public String getLine() {
		while (!inputReceived) {
			try {
				Thread.sleep(200);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inputReceived = false;
		// System.out.println("DEBUG inputField: " + input);
		
		if (input.equals("QUIT") || input.equals("Q") || input.equals("EXIT")) {
			frame.dispose();
		}
		else if (input.contains("GUI")) {
			String[] words = input.split("\\s+");
			if (Integer.parseInt(words[1]) != 1) {
				frame.dispose();
			}
		}
		return input;
	}
	
	/**
	 * Implement Observer interface method. This method is called when 
	 * the observed object is changed.
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("DEBUG "+ (String) arg);
		inventory.setText((String)arg);
	}
	
	/* Set up components for the frame. Extract to other classes later. */
	private void init() {
		dialog.setEditable(false);
		JScrollPane scroll = new JScrollPane(dialog); 
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		JPanel inputPanel = new JPanel(new GridLayout(2, 1));
		JButton endTurnButton = new JButton("End Turn");
		
		endTurnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("inputField: " + GUI_1.this.inputField.getText());
				GUI_1.this.inputReceived = true;
				GUI_1.this.input = inputField.getText().toUpperCase();
			}
		});
		
		inputPanel.add(inputField);
		inputPanel.add(endTurnButton);
	
		frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);
		frame.getContentPane().add(inventory, BorderLayout.LINE_END);
		
		JLabel nameLabel = new JLabel(Character.currentPlayerName());
		frame.getContentPane().add(nameLabel, BorderLayout.NORTH);
		
		JTextArea stats = new JTextArea("stats", 5, 2);
		frame.getContentPane().add(stats, BorderLayout.LINE_START);
	}
}