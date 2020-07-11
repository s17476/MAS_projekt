package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import controller.DbController;
import model.Osoba;

import java.awt.BorderLayout;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogIn extends JFrame {
	private JFrame frame= this;
	private String name;
	private DbController db;
	private JTextField textField;
	private JPasswordField passwordField;
	

	/**
	 * Create the frame.
	 */
	public LogIn(String name, DbController db) {
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[69px,grow][][grow]", "[][][23px,grow]"));
		
		JLabel lblNewLabel = new JLabel("Pesel lub nr Id");
		panel.add(lblNewLabel, "cell 0 0,alignx right");
		
		textField = new JTextField();
		panel.add(textField, "cell 2 0,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Hasło");
		panel.add(lblNewLabel_1, "cell 0 1,alignx right");
		
		passwordField = new JPasswordField();
		panel.add(passwordField, "cell 2 1,growx");
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 2 3 1,grow");
		
		JButton btnNewButton = new JButton("LOG IN");

		panel_1.add(btnNewButton);

		
		/**
		 * Log in
		 * id + hasło
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(passwordField.getPassword());
				CriteriaBuilder cb = db.getCriteriaBuilder();
				CriteriaQuery<Osoba> cr = cb.createQuery(Osoba.class);
				Root<Osoba> root = cr.from(Osoba.class);
				
				Predicate[] predicates = {
						cb.equal(root.get("pesel"), textField.getText()),
						cb.equal(root.get("password"), pass)};
				
				cr.select(root).where(predicates);
				
				 
				Query<Osoba> query = db.createQuery(cr);
				List<Osoba> results = (List<Osoba>)query.getResultList();
				
				
				//System.out.println(pass);
				//System.out.println(results.get(0).getClass());
				
				if(results.size() > 0) { 
					isOk(results.get(0), db);
					close();
				}else {
					JOptionPane.showMessageDialog(frame, "Zły pesel lub hasło");
					
				}
			}
		});
		getRootPane().setDefaultButton(btnNewButton);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	db.close();
				System.out.println("zamykamy");
		    }
		});
	}
	
	
	
	private void close() {
		this.setVisible(false);;
	}
	
	private void isOk(Osoba osoba, DbController db) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow frame = new MainWindow(osoba, db);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
