package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
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
import model.Egzamin;
import model.Osoba;
import model.Przedmiot;
import model.PrzedmiotGrupa;
import model.PytanieEgzaminacyjne;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Selection extends JFrame {

	public JFrame frame  =this; 
	private PrzedmiotGrupa pg;
	Creator p;
	private DbController db;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		
		
		setPreferredSize(new Dimension(1000, 450));
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[300px:n:300px][128px][300px:n:300px,grow]", "[14px][14px][183px][14px][10px][][14px][78px,grow]"));
		
		JLabel lblNewLabel = new JLabel("Pytania z przedmiotu :" + pg.getPrzedmiot());
		contentPane.add(lblNewLabel, "cell 1 0,alignx center,aligny top");
		
		JLabel lblNewLabel_1 = new JLabel("Dostępne:");
		contentPane.add(lblNewLabel_1, "cell 0 1,alignx left,aligny top");
		
		JLabel lblNewLabel_2 = new JLabel("Wybrane:");
		contentPane.add(lblNewLabel_2, "cell 2 1,alignx left,aligny top");
		
		
		
		
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 2,grow");
		panel.setLayout(new MigLayout("", "[grow][][]", "[][][][]"));
		
		JButton btnNewButton_1 = new JButton("<- USUŃ");
		
		panel.add(btnNewButton_1, "cell 0 1 3 1,growx,aligny center");
		
		JButton btnNewButton = new JButton("DODAJ ->");
		
		panel.add(btnNewButton, "cell 0 2 3 1,growx,aligny center");
		
		DefaultListModel<PytanieEgzaminacyjne> listModel = new DefaultListModel<>(); 
		/**
		 * noew pytanie
		 */
		JButton btnNewButton_2 = new JButton("Nowe pytanie");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new Pytanie("Nowe pytanie", db, frame, listModel, pg.getPrzedmiot());
							frame.setEnabled(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(btnNewButton_2, "cell 0 3 3 1,growx,aligny center");
		
		JLabel lblNewLabel_3 = new JLabel("Wybrane pytanie:");
		contentPane.add(lblNewLabel_3, "cell 0 3,alignx left,aligny top");
		
		
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
		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		JLabel lblNewLabel_6 = new JLabel("");
		//lblNewLabel_6.setSize(500, 100);
		
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		
		results.forEach(x -> listModel.addElement(x));
		JList<PytanieEgzaminacyjne> list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					String s = listModel.get(list.getSelectedIndex()).getTrescPytania();
					lblNewLabel_4.setText(s);
					
				}catch(Exception exc) {}
				try {
					PytanieEgzaminacyjne p = (PytanieEgzaminacyjne)listModel.get(list.getSelectedIndex());
					List<String> zle = p.getZleOdpowiedzi();
					List<String> dobre = p.getDobreOdpowiedzi();
					String odpowiedzi = "<html><pre>";
					for (int i = 1; i <= zle.size(); i++) {
						odpowiedzi += i + ". <font color=red>" + zle.get(i-1) + "</font><br>";
					}
					for (int i = 1; i <= dobre.size(); i++) {
						odpowiedzi += i+zle.size() + ".  <font color=green>" + dobre.get(i-1) + "</font><br>";
					}
					odpowiedzi+="</pre></html>";
					lblNewLabel_6.setText(odpowiedzi);
					System.out.println(odpowiedzi);
					
				}catch(Exception exc) {}
			
			}
		});

		contentPane.add(list, "cell 0 2,grow");
		
		
		DefaultListModel<PytanieEgzaminacyjne> list1Model = new DefaultListModel<>();
		JList<PytanieEgzaminacyjne> list_1 = new JList<>(list1Model);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(list_1, "cell 2 2,grow");
				
				JLabel lblNewLabel_7 = new JLabel("Tytuł:");
				contentPane.add(lblNewLabel_7, "cell 1 3,alignx right");
		
				
				
				///////////////tu dodawać//////////////////////////////////////////////////////
				JPanel panel_1 = new JPanel();
				contentPane.add(panel_1, "cell 0 4,alignx left,growy");
				
				JLabel lblNewLabel_8 = new JLabel("Dostępny czas:");
				contentPane.add(lblNewLabel_8, "cell 1 4,alignx trailing");
				
				textField_1 = new JTextField();
				textField_1.setText("30");
				contentPane.add(textField_1, "cell 2 4,growx");
				textField_1.setColumns(10);
				
				JLabel lblNewLabel_9 = new JLabel("Dostępny od:");
				contentPane.add(lblNewLabel_9, "cell 1 5,alignx trailing");
				
				
				
				JLabel lblNewLabel_5 = new JLabel("Odpowiedzi:");
				contentPane.add(lblNewLabel_5, "cell 0 6,alignx left,aligny top");
				
				JPanel panel_2 = new JPanel();
				//contentPane.add(panel_2, "cell 3 8, alignx left,growy");
				
				
				

				panel_1.add(lblNewLabel_4);
				
				JLabel lblNewLabel_10 = new JLabel("Dostępny do:");
				contentPane.add(lblNewLabel_10, "cell 1 6,alignx right");
				
				JPanel panel_3 = new JPanel();
				contentPane.add(panel_3, "cell 0 7,alignx left,growy");
				
				panel_3.add(lblNewLabel_6);
				
				JPanel panel_4 = new JPanel();
				contentPane.add(panel_4, "cell 2 7,alignx center,aligny center");
				
				JButton btnNewButton_3 = new JButton("Anuluj");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
				});
				panel_4.add(btnNewButton_3);
				
				JButton btnNewButton_4 = new JButton("Utwórz egzamin");

				panel_4.add(btnNewButton_4);
				
				JTextField txtPrzykad = new JTextField();
				txtPrzykad.setText("Przykład");
				contentPane.add(txtPrzykad, "cell 2 3,growx");
				txtPrzykad.setColumns(10);
				
				textField_3 = new JTextField();
				textField_3.setText("2020-07-14");
				contentPane.add(textField_3, "cell 2 6,growx");
				textField_3.setColumns(10);
				
				textField_2 = new JTextField();
				textField_2.setText("2020-07-13");
				contentPane.add(textField_2, "cell 2 5,growx");
				textField_2.setColumns(10);				
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//	try {
							List<PytanieEgzaminacyjne> pe = new ArrayList();
							for(int i = 0; i < list1Model.getSize(); i++) {
								pe.add(list1Model.get(i));
							}
							Egzamin egzamin = new Egzamin(txtPrzykad.getText(), pe, 10, Integer.parseInt(textField_1.getText()), 
															LocalDate.parse(textField_2.getText()), LocalDate.parse(textField_3.getText()), pg.getPrzedmiot());
							pg.dodajEgzamin(egzamin);
							db.save(egzamin);
							System.out.println(egzamin);
							frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						//}catch(Exception exc) {
						//	exc.printStackTrace();
						//	JOptionPane.showMessageDialog(frame, "Sprawdź wprowadzone dane!");
						//}
						
						
					}
				});
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


