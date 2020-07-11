package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.hibernate.query.Query;

import controller.DbController;
import model.Osoba;
import model.PrzedmiotGrupa;
import model.PytanieEgzaminacyjne;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class Selection extends JFrame {

	private PrzedmiotGrupa pg;
	Creator p;
	private DbController db;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */

		


	/**
	 * Create the frame.
	 */
	public Selection(String name, JFrame parent, DbController db, PrzedmiotGrupa pg) {
		super(name);
		this.p = (Creator)parent;
		this.db = db;
		this.pg = pg;
		
		((MainWindow)p.parent).setEnabled(false);
		
		
		setPreferredSize(new Dimension(1000, 800));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][]", "[][][grow][grow][][grow][]"));
		
		JLabel lblNewLabel = new JLabel("Pytania z przedmiotu :" + pg.getPrzedmiot());
		contentPane.add(lblNewLabel, "cell 1 0,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Dostępne:");
		contentPane.add(lblNewLabel_1, "cell 0 1");
		
		JLabel lblNewLabel_2 = new JLabel("Wybrane:");
		contentPane.add(lblNewLabel_2, "cell 2 1");
		
		
		
		
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 2 1 2,grow");
		panel.setLayout(new MigLayout("", "[grow][][]", "[][][][]"));
		
		JButton btnNewButton_1 = new JButton("<- USUŃ");
		
		panel.add(btnNewButton_1, "cell 0 1 3 1,growx,aligny center");
		
		JButton btnNewButton = new JButton("DODAJ ->");
		
		panel.add(btnNewButton, "cell 0 2 3 1,growx,aligny center");
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2, "cell 0 3 3 1,growx,aligny center");
		
		JLabel lblNewLabel_3 = new JLabel("Wybrane pytanie:");
		contentPane.add(lblNewLabel_3, "cell 0 4");

		
		
		///////////////tu dodawać//////////////////////////////////////////////////////
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 5 3 2,alignx left,growy");
		
		JLabel lblNewLabel_4 = new JLabel();
		panel_1.add(lblNewLabel_4);
		
		
		/**
		 * Pobierz pytania z wybranego przedmiotu
		 */
		CriteriaBuilder cb = db.getCriteriaBuilder();
		CriteriaQuery<PytanieEgzaminacyjne> cr = cb.createQuery(PytanieEgzaminacyjne.class);
		Root<PytanieEgzaminacyjne> root = cr.from(PytanieEgzaminacyjne.class);
		
		cr.select(root).where(cb.equal(root.get("przedmiot"), pg.getPrzedmiot()));
		
		 
		Query<PytanieEgzaminacyjne> query = db.createQuery(cr);
		List<PytanieEgzaminacyjne> results = (List<PytanieEgzaminacyjne>)query.getResultList();
		
		System.out.println(results);
		
		DefaultListModel<PytanieEgzaminacyjne> listModel = new DefaultListModel<>(); 
		results.forEach(x -> listModel.addElement(x));
		JList<PytanieEgzaminacyjne> list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					String s = listModel.get(list.getSelectedIndex()).toString();
					lblNewLabel_4.setText(s);
				}catch(Exception exc) {}
			}
		});

		contentPane.add(list, "cell 0 2 1 2,grow");
		
		
		DefaultListModel<PytanieEgzaminacyjne> list1Model = new DefaultListModel<>();
		JList<PytanieEgzaminacyjne> list_1 = new JList<>(list1Model);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(list_1, "cell 2 2 1 2,grow");

		/*
		PytanieEgzaminacyjne pytania;
		List<String> zleOdpowiedzi = pytania.getZleOdpowiedzi();
		List<String> dobreOdpowiedzi = pytania.getDobreOdpowiedzi();
		
		

		List<String> odpowiedzi = pytania.getZleOdpowiedzi();
		pytania.getDobreOdpowiedzi().forEach(x -> odpowiedzi.add(x));
		*/
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() > -1) {
					lblNewLabel_4.setText("");
					PytanieEgzaminacyjne pe = listModel.get(list.getSelectedIndex());
					listModel.remove(list.getSelectedIndex());
					list1Model.addElement(pe);
				}
			}
		});
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getSelectedIndex() > -1) {
					PytanieEgzaminacyjne pe = list1Model.get(list_1.getSelectedIndex());
					list1Model.remove(list_1.getSelectedIndex());
					listModel.addElement(pe);
				}
			}
		});
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	((MainWindow)p.parent).setEnabled(true);
		    }
		});

	}
}


