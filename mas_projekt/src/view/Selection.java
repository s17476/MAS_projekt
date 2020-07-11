package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DbController;
import model.PrzedmiotGrupa;

public class Selection extends JFrame {

	private PrzedmiotGrupa pg;
	private JFrame parent;
	private DbController db;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

		


	/**
	 * Create the frame.
	 */
	public Selection(String name, JFrame parent, DbController db, PrzedmiotGrupa pg) {
		super(name);
		this.parent = parent;
		this.db = db;
		this.pg = pg;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);

	}

}
