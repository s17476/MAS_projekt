package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DbController;
import model.Osoba;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class MainWindow extends JFrame {
	
	private JPanel contentPane;
	private Osoba osoba;
	public DbController db;
	public JFrame frame = this;



	/**
	 * Create the frame.
	 */
	public MainWindow(Osoba osoba, DbController db) {
		super("Dziennik lekcyjny");
		
		this.osoba = osoba;
		this.db = db;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 958, 400);
		
		/**
		 * Launch the application.
		 */
		
		 
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Plik");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Pomoc");
		menuBar.add(mnNewMenu_1);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][][][][]", "[][grow]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0 5 1,alignx left,growy");
		
		JButton btnNewButton = new JButton("Kalendarz");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wiadomości");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Moje pliki");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Moje przedmioty");
		panel.add(btnNewButton_3);
		
		List list = new ArrayList<>();
		list.add("");
		if(osoba.getAdministrator() != null)
			list.add(osoba.getAdministrator());
		if(osoba.getDydaktyk() != null)
			list.add(osoba.getDydaktyk());
		if(osoba.getUczen() != null)
			list.add(osoba.getUczen());
		
		if (list.size() == 2) list.remove(0);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7, "cell 5 0");
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		
		/**
		 * wybór funkcji użytkownika w oknie głównym
		 */
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, "cell 1 1 5 1,grow");
		panel_3.setLayout(new CardLayout());
		CardLayout cl = (CardLayout)panel_3.getLayout();
		JLabel lblNewLabel = new JLabel("Zalogowano jako:");
		panel_7.add(lblNewLabel);
		JComboBox comboBox = new JComboBox(list.toArray());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(comboBox.getSelectedItem().getClass().getSimpleName());
				cl.show(panel_3, comboBox.getSelectedItem().getClass().getSimpleName());
			}
		});
		panel_7.add(comboBox);
		
		
		
		
		
		JPanel welcome = new JPanel();
		panel_3.add(welcome, "Welcome");
		welcome.setLayout(new MigLayout("", "[grow][][][][][grow]", "[grow][][][][grow][grow]"));
		
		JPanel admin = new JPanel();
		panel_3.add(admin, "KadraAdministracyjna");
		admin.setLayout(new MigLayout("", "[grow][][][][][grow]", "[grow][][][][grow][grow]"));
		
		JPanel uczen = new JPanel();
		panel_3.add(uczen, "Uczen");
		uczen.setLayout(new MigLayout("", "[grow][][][][][grow]", "[grow][][][][grow][grow]"));
		
		JLabel lblNewLabel_4 = new JLabel("uczen");
		uczen.add(lblNewLabel_4, "cell 1 2");
		
		JPanel nauczyciel = new JPanel();
		nauczyciel.setBorder(null);
		panel_3.add(nauczyciel, "KadraDydaktyczna");
		nauczyciel.setLayout(new MigLayout("", "[101px][grow]", "[grow]"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		nauczyciel.add(panel_2, "cell 1 0,grow");
		
		JPanel panel_6 = new JPanel();
		welcome.add(panel_6, "cell 0 1 6 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Witaj " + osoba.getImie());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_1);
		
		JPanel panel_5 = new JPanel();
		welcome.add(panel_5, "cell 0 3 6 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("W prawym górnym rogu wybierz rolę użytkownika.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_2);
		this.setMinimumSize(new Dimension(300, 400));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	db.close();
		    }
		});
	}

}
