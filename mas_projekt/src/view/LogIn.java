package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LogIn extends JFrame {

	

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);

		pack();
		setVisible(true);
	}

}
