/* Author: Zain Zahran*/
package ui.three;

import ui.UserInterface;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class represents a graphical user interface.
 * This user interface is not intended to be used with the command line.
 *
 * @author Zain Zahran
 */
public class GUI_3 implements UserInterface {

	private JFrame frame;			// Main frame for whole GUI
	private JLabel label;			// Label for "enter command"
	// Panels
	private JPanel nPanel;			// North panel
	private JPanel cPanel;			// Center panel
	private JPanel sPanel;			// South panel
	// Buttons
	private JButton submit;			// Submit btn
	private JButton inveBtn;		// Inventory btn
	private JButton lookBtn;		// Look btn
	private JButton quitBtn;		// Quit btn
	private JButton resetBtn;		// Reset btn
	
	private JTextField textField;	// Textfield for input
	private JTextArea ta;			// Text Area
	private JScrollPane areaScroll;	// Scrolling for area text

	private boolean inputTrigger;
	
	// Constructor
	public GUI_3() {
    	/* BORDER LAYOUT GUI */
        // Creating the Frame
        frame = new JFrame("GUI Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        /* --NORTH panel-- */
        // Creating the panel at top and adding components
        nPanel = new JPanel(); // the panel is not visible in output
        label = new JLabel("Enter Command");
        textField = new JTextField(15); // accepts upto 15 characters
        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ea) {
        		GUI_3.this.inputTrigger = true;
        		textField.setText("");
        	}
        });
        // Set the default button on 'Enter' to submit
        frame.getRootPane().setDefaultButton(submit);

        // Add components to panel
        nPanel.add(label);
        nPanel.add(textField);
        nPanel.add(submit);

        /* --Center Panel-- */
        // Dedicated for printing/display
        cPanel = new JPanel();
        // Text Area at the south
        ta = new JTextArea();
        ta.setFont(new Font("Serif", Font.ITALIC, 16));
        ta.setLineWrap(true);
        ta.setEditable(false);	// User is not allowed to change any of the text
        // Provide scrolling capability
        areaScroll = new JScrollPane(ta);
        areaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScroll.setPreferredSize(new Dimension(300,300) );
        // Add scrolling text area to center panel
        cPanel.add(areaScroll);
        // ta.append(text);
        // ta.setText(text);

        /* --South/PAGE_END-- */
        // Look, Inve, Quit, Reset
        // Displaying Inventory button
        sPanel = new JPanel();
        // Inventory button
        inveBtn = new JButton("Inventory");
        // Look button
        lookBtn = new JButton("Look");
        // Quit button
        quitBtn = new JButton("QUIT");
        quitBtn.setBackground(new Color(209,52,52) );
        quitBtn.setForeground(Color.WHITE);
        // Function - Quit the game
        quitBtn.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ea) {
        		System.exit(0);
        	}
        });
        // Reset button
        resetBtn = new JButton("RESET");
        resetBtn.setBackground(new Color(5, 69, 173) );
        resetBtn.setForeground(Color.WHITE);
        // Add components to panel
        sPanel.add(lookBtn);
        sPanel.add(inveBtn);
        sPanel.add(quitBtn);
        sPanel.add(resetBtn);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, nPanel);
        frame.getContentPane().add(BorderLayout.CENTER, cPanel);
        frame.getContentPane().add(BorderLayout.PAGE_END, sPanel);

        // Show it to the world!
        frame.setVisible(true);
	}
	
	/**
	 * The display method for this class. 
	 * This will print the message to the screen.
	 *
	 * @param message message to display
	 */
	public void display(String message) {
		// Change to display
		ta.append(message+"\n");
	}
	
	/**
	 * Get user input method for this class.
	 * This will use various graphical user interface components to obtain the input.
	 *
	 * @return user input
	 */
	public String getLine() {
		while (!inputTrigger) {
			try {
				Thread.sleep(200);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		inputTrigger = false;
		return textField.getText();
	}
}