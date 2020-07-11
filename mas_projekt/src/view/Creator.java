package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DbController;

public class Creator extends JFrame {

	private JPanel contentPane;
	public DbController db;
	public JFrame parent;

	/**
	 * Create the frame.
	 */
	public Creator(String name, DbController db, JFrame parent) {
		super(name);
		this.db = db;
		this.parent = parent;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	parent.setEnabled(true);
				System.out.println("zamykamy");
		    }
		});
	}

}
