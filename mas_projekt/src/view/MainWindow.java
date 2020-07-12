package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DbController;
import model.Egzamin;
import model.Ocena;
import model.Osoba;
import model.PrzedmiotGrupa;
import model.PytanieEgzaminacyjne;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.DefaultListModel;
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
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import org.hibernate.query.Query;

import javax.swing.border.BevelBorder;
import javax.swing.JList;

public class MainWindow extends JFrame {
	
	private JPanel contentPane;
	private Osoba osoba;
	public DbController db;
	public JFrame frame = this;
	public Egzamin egzamin;
	public final JComboBox<Egzamin> comboBox_1;



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
		
		JButton btnNewButton_3 = new JButton("Zamknij");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel.add(btnNewButton_3);
		
		List list = new ArrayList<>();
		list.add("");
		if(osoba.getAdministrator() != null)
			list.add(osoba.getAdministrator());
		if(osoba.getDydaktyk() != null)
			list.add(osoba.getDydaktyk());
		if(osoba.getUczen() != null)
			list.add(osoba.getUczen());
		
		//if (list.size() == 2) list.remove(0);
		
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
		uczen.setLayout(new MigLayout("", "[grow][][][][][grow]", "[][][][][][][grow][grow]"));
		
		JLabel lblNewLabel_3 = new JLabel("Dostępne testy:");
		uczen.add(lblNewLabel_3, "cell 0 0");
		JButton btnNewButton_8 = new JButton("Rozwiąż");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new EgzaminView("Egzamin", db, (Egzamin)comboBox_1.getSelectedItem(), frame, osoba);
						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		
		uczen.add(btnNewButton_8, "cell 0 2");
		DefaultListModel<Ocena> lm = new DefaultListModel();
		JList list_1 = new JList(lm);
		JLabel lblNewLabel_4 = new JLabel("Moduł uczeń");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uczen.add(lblNewLabel_4, "cell 1 4");
		
		JButton btnNewButton_9 = new JButton("Pokaż moje oceny");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CriteriaBuilder cb = db.getCriteriaBuilder();
					CriteriaQuery<Ocena> cr = cb.createQuery(Ocena.class);
					Root<Ocena> root = cr.from(Ocena.class);
					
					cr.select(root).where(cb.equal(root.get("uczen"), osoba.getUczen()));/////////////////////////////////////////////////////////////////////
					
					 
					Query<Ocena> query = db.createQuery(cr);
					List<Ocena> results = (List<Ocena>)query.getResultList();
					
					results.forEach(x -> {
						if(!lm.contains(x))
							lm.addElement(x);
					});
				}catch(Exception exc) {}
			}
		});
		uczen.add(btnNewButton_9, "cell 0 5");
		
		JPanel panel_4 = new JPanel();
		uczen.add(panel_4, "cell 0 6 1 2,grow");
		
		
		panel_4.add(list_1);
		
		
		

		
		
		
		/**
		 * pobiera dostępne Egzaminy
		 */
		if(osoba.getUczen() != null) {
		
		
			CriteriaBuilder cb = db.getCriteriaBuilder();
			CriteriaQuery<PrzedmiotGrupa> cr = cb.createQuery(PrzedmiotGrupa.class);
			Root<PrzedmiotGrupa> root = cr.from(PrzedmiotGrupa.class);
			
			cr.select(root).where(cb.equal(root.get("grupa"), osoba.getUczen().getGrupa()));
			System.out.println("Grupa"+osoba.getUczen().getGrupa());
			 
			Query<PrzedmiotGrupa> query = db.createQuery(cr);
			List<PrzedmiotGrupa> results = (List<PrzedmiotGrupa>)query.getResultList();
			
			
			
			comboBox_1 = new JComboBox(results.get(0).getListaEgzaminow().toArray());////////////////////////////////////////tu testy
			uczen.add(comboBox_1, "cell 0 0");
			
			System.out.println("Jest grupa "+results);
		}
		else
			comboBox_1 = new  JComboBox();
		
		/**
		 * uczeń - rozwiąż egzamin///////////////////////////////////////////////////////////////////////
		 */
		
		JPanel nauczyciel = new JPanel();
		nauczyciel.setBorder(null);
		panel_3.add(nauczyciel, "KadraDydaktyczna");
		nauczyciel.setLayout(new MigLayout("", "[][grow]", "[grow]"));
		
		JPanel panel_1 = new JPanel();
		nauczyciel.add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new MigLayout("", "[89px]", "[23px][][][][]"));
		
		JButton btnNewButton_4 = new JButton("Pokaż grupy");
		panel_1.add(btnNewButton_4, "cell 0 0,growx,aligny top");
		
		JButton btnNewButton_5 = new JButton("Pokaż przedmioty");
		panel_1.add(btnNewButton_5, "cell 0 1,growx");
		
		JSeparator separator = new JSeparator();
		panel_1.add(separator, "cell 0 2,grow");
		
		
		/**
		 * kreator nowego testu
		 */
		JButton btnNewButton_6 = new JButton("Nowy test");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new Creator("Utwórz nowy test", db, frame);
						} catch (Exception e) {
							e.printStackTrace();
							
						}
					}
				});
			}
		});
		panel_1.add(btnNewButton_6, "cell 0 3,growx");
		
		JButton btnNewButton_7 = new JButton("Pokaż testy");
		panel_1.add(btnNewButton_7, "cell 0 4,growx");
		
		
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
		setPreferredSize(new Dimension(1000, 700));
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
