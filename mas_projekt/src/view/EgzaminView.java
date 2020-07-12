package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DbController;
import model.Egzamin;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Color;

public class EgzaminView extends JFrame {
	private JFrame parent;
	private JPanel contentPane;
	private Egzamin egzamin;
	private DbController db;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public EgzaminView(String name, DbController db, Egzamin egzamin, JFrame parent) {
		super(name);
		this.parent = parent;
		this.egzamin = egzamin;
		this.db = db;
		setBounds(100, 100, 1153, 694);
		setPreferredSize(new Dimension(1200, 700));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow][]", "[][][grow][]"));
		
		JLabel lblNewLabel = new JLabel(egzamin.toString());
		contentPane.add(lblNewLabel, "cell 0 0");
		
		JLabel lblNewLabel_2 = new JLabel("Czas pozostały do końca egzaminu:");
		contentPane.add(lblNewLabel_2, "flowx,cell 2 1,alignx right");
		
		JLabel lblNewLabel_1 = new JLabel("00:00");
		contentPane.add(lblNewLabel_1, "cell 2 1,alignx right");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 2 4 1,grow");
		
		JButton btnNewButton_2 = new JButton("ZAKOŃCZ EGZAMIN");
		
		
		Thread thread = new Thread(new Runnable () {

	        @Override
	        public void run() {
	            int countdownSeconds = egzamin.getDostepnyCzas() * 60;

	            for (int i = countdownSeconds ; i >= 0; i--) {

	                try{
	                    Thread.sleep(1000);
	                }catch (InterruptedException e) {}
	                
	                int minuty = (int)i/60;
	                int sekundy = i-(minuty*60);
	                lblNewLabel_1.setText(minuty + ":" +sekundy);
	            }
	            btnNewButton_2.doClick();
	        }
	    });
		thread.start();
		
		
		
		btnNewButton_2.setBackground(Color.RED);
		contentPane.add(btnNewButton_2, "cell 2 3,alignx center");
		setVisible(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	parent.setEnabled(true);
		    	thread.interrupt();
		    }
		});
	}

}
