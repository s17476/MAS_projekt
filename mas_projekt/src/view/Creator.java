package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DbController;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Creator extends JFrame {

	private JFrame frame = this;
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
		//setPreferredSize(new Dimension(600, 400));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][200px,grow][grow]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Wybierz grupę i przedmiot");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel, "cell 0 1 3 1,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Grupa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1, "cell 1 3");
		
		JComboBox comboGrupa = new JComboBox();
		contentPane.add(comboGrupa, "cell 1 4,growx");
		
		JLabel lblNewLabel_2 = new JLabel("Przedmiot");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2, "cell 1 5");
		
		JComboBox comboPrzedmiot = new JComboBox();
		contentPane.add(comboPrzedmiot, "cell 1 6,growx");
		
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 7 3 1,grow");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 8 3 1,growx");
		
		JButton btnNewButton = new JButton("Anuluj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Dalej");
		panel.add(btnNewButton_1);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	parent.setEnabled(true);
				System.out.println("zamykamy kreatora");
		    }
		});
	}

}
