/* Author: Alexander Oey (aoey2) */
package ui.one;

import ui.UserInterface;

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

/**
 * This class represents a graphical user interface.
 * This user interface is not intended to be used with the command line.
 *
 * @author Alexander Oey (aoey2)
 */
public class GUI_1 implements UserInterface {
	private final JFrame frame;
	private JTextField inputField = new JTextField();
	private volatile boolean inputReceived = false;
	
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
		
		System.out.println("inputField: " + inputField.getText());
		inputReceived = false;
		return inputField.getText();
	}
	
	/* Set up components for the frame. Extract to other classes later. */
	
	private void init() {
		JTextArea dialog = new JTextArea("all dialog", 20, 10);
		JScrollPane scroll = new JScrollPane(dialog); 
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		JPanel inputPanel = new JPanel(new GridLayout(2, 1));
		JButton endTurnButton = new JButton("End Turn");
		
		endTurnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("inputField: " + GUI_1.this.inputField.getText());
				GUI_1.this.inputReceived = true;
			}
		});
		
		inputPanel.add(inputField);
		inputPanel.add(endTurnButton);
	
		frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);
		
		JLabel nameLabel = new JLabel("Player");
		frame.getContentPane().add(nameLabel, BorderLayout.NORTH);
		
		JTextArea inventory = new JTextArea("inventory", 5, 2);
		frame.getContentPane().add(inventory, BorderLayout.LINE_END);
		
		JTextArea stats = new JTextArea("stats", 5, 2);
		frame.getContentPane().add(stats, BorderLayout.LINE_START);
	}
}