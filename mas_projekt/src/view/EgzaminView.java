package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import controller.DbController;
import model.Egzamin;
import model.Ocena;
import model.Osoba;
import model.PytanieEgzaminacyjne;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;

/**
 * 
 * @author Grzegorz Frączek
 *
 */

public class EgzaminView extends JFrame {
	JFrame frame  =this;
	private JFrame parent;
	private JPanel contentPane;
	private Egzamin egzamin;
	private DbController db;
	public PytanieEgzaminacyjne pe;
	public Osoba osoba;

	/**
	 * Create the frame.
	 */
	public EgzaminView(String name, DbController db, Egzamin egzamin, JFrame parent, Osoba osoba) {
		super(name);
		this.parent = parent;
		this.egzamin = egzamin;
		this.db = db;
		this.osoba = osoba;
		setBounds(100, 100, 1153, 694);
		setPreferredSize(new Dimension(1200, 700));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow][]", "[][][grow][]"));
		
		JLabel lblNewLabel = new JLabel(egzamin.toString());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel, "cell 0 0");
			
		JLabel lblNewLabel_2 = new JLabel("Czas pozostały do końca egzaminu:");
		contentPane.add(lblNewLabel_2, "flowx,cell 2 1,alignx right");
			
		JLabel lblNewLabel_1 = new JLabel("00:00");
		contentPane.add(lblNewLabel_1, "cell 2 1,alignx right");
			
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 2 4 1,grow");
			
		JPanel panel = new JPanel();
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][]"));
			
		Map<PytanieEgzaminacyjne, Map<String, JCheckBox>> test = new HashMap<>();
			
		/**
		 * dodaje pytania  i odpowiedzi do paneli
		 */
		for (int i = 0; i < egzamin.getPytaniaEgzaminacyjne().size(); i++) {
			
			pe = egzamin.getPytaniaEgzaminacyjne().get(i);
			
			Map<String, JCheckBox> check = new HashMap<>();
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			JSeparator separator = new JSeparator();
			
			panel.add(panel_1, "cell 0 "+i+",grow");
			panel_1.setLayout(new MigLayout("", "[][grow][center]", "[][][][][]"));
			
			JLabel lblNewLabel_3 = new JLabel(pe.getTrescPytania());
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel_1.add(lblNewLabel_3, "cell 1 0");
			panel_1.add(separator, "cell 1 1,grow");
			
			List<String> odpowiedzi = new ArrayList<>(); 
			pe.getDobreOdpowiedzi().stream().forEach(x -> {
				odpowiedzi.add(x);
			});
			pe.getZleOdpowiedzi().stream().forEach(x -> {
				odpowiedzi.add(x);
			});
			
			Collections.shuffle(odpowiedzi);
			
			JLabel lblNewLabel_4 = new JLabel(odpowiedzi.get(0));
			panel_1.add(lblNewLabel_4, "cell 1 2");
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("");
			panel_1.add(chckbxNewCheckBox, "cell 2 2");
			check.put(odpowiedzi.get(0), chckbxNewCheckBox);
			
			JLabel lblNewLabel_5 = new JLabel(odpowiedzi.get(1));
			panel_1.add(lblNewLabel_5, "cell 1 3");
			
			JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
			panel_1.add(chckbxNewCheckBox_1, "cell 2 3");
			
			check.put(odpowiedzi.get(1), chckbxNewCheckBox_1);
			
			JLabel lblNewLabel_6 = new JLabel(odpowiedzi.get(2));
			panel_1.add(lblNewLabel_6, "cell 1 4");
			
			JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
			panel_1.add(chckbxNewCheckBox_2, "cell 2 4");
			
			check.put(odpowiedzi.get(2), chckbxNewCheckBox_2);
			
			JLabel lblNewLabel_7 = new JLabel(odpowiedzi.get(3));
			panel_1.add(lblNewLabel_7, "cell 1 5");
			
			
			JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
			panel_1.add(chckbxNewCheckBox_3, "cell 2 5");	
			
			check.put(odpowiedzi.get(3), chckbxNewCheckBox_3);
			
			test.put(pe, check);
		}
		
		JButton btnNewButton_2 = new JButton("ZAKOŃCZ EGZAMIN");
		
		/**
		 * wątek minutnika
		 */
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
		
		/**
		 * zakończ i zapisz egzamin
		 */
		
		btnNewButton_2.addActionListener(new ActionListener() {
			private final Map<PytanieEgzaminacyjne, Map<String, JCheckBox>> result = test;
			public void actionPerformed(ActionEvent e) {
				
				String[] opcje =  { "Anuluj", "Zakończ"};
				int rc = JOptionPane.showOptionDialog(
				           null,
				           "Czy na pewno chcesz zakończyć egzamin?",
				           "Zakończyć?",
				           JOptionPane.DEFAULT_OPTION,
				           JOptionPane.QUESTION_MESSAGE,
				           null,
				           opcje,
				           opcje[0]);
				
				if(rc == 1) {
					int rezultat = egzamin.check(test);
					JOptionPane.showMessageDialog(frame, "Uzyskałeś " + rezultat + " punktów.", "Wynik egzaminu", JOptionPane.INFORMATION_MESSAGE);
					var ocena = new Ocena(LocalDate.now(), rezultat, egzamin.toString(), osoba.getUczen());
					db.save(ocena);
					parent.setEnabled(true);
			    	thread.interrupt();
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		
		/**
		 * blokuje okno
		 */
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		btnNewButton_2.setBackground(Color.RED);
		contentPane.add(btnNewButton_2, "cell 2 3,alignx center");
		setVisible(true);
	}

}
