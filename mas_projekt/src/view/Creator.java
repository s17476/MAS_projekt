package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DbController;
import model.Grupa;
import model.Przedmiot;
import model.PrzedmiotGrupa;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Grzegorz Frączek
 *
 */

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

		/**
		 *pobierz dane grup i przedmiotów
		 */
		List<PrzedmiotGrupa> przedmiotyWGrupach = (List<PrzedmiotGrupa>)db.load("PrzedmiotGrupa");
		List<Przedmiot> przedmioty = new ArrayList();
		przedmiotyWGrupach.stream().forEach(x -> {
			
			if(!przedmioty.contains(x.getPrzedmiot()))
				przedmioty.add(x.getPrzedmiot());
		});
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][200px,grow][grow]", "[][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Wybierz grupę i przedmiot");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel, "cell 0 1 3 1,growx");
		
		JLabel lblNewLabel_2 = new JLabel("Przedmiot");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2, "cell 1 3");
		
		/**
		 * grupy dla wybranego przedmiotu
		 */
		JComboBox comboGrupa = new JComboBox();
		contentPane.add(comboGrupa, "cell 1 9,growx");
		JComboBox comboPrzedmiot = new JComboBox(przedmioty.toArray());
		
		comboPrzedmiot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Przedmiot selected = (Przedmiot)comboPrzedmiot.getSelectedItem();
				List<Grupa> grupy = new ArrayList<>();
				przedmiotyWGrupach.stream().filter(x -> x.getPrzedmiot().equals(selected)).forEach(x -> grupy.add(x.getGrupa()));
				comboGrupa.setModel(new DefaultComboBoxModel(grupy.toArray()));
			}
		});
		comboPrzedmiot.setSelectedIndex(0);
		
		contentPane.add(comboPrzedmiot, "cell 1 5,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Grupa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1, "cell 1 7");
		
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 1 11,grow");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 12 3 1,growx");
		
		/**
		 * przycisk anuluj
		 */
		JButton btnNewButton = new JButton("Anuluj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		panel.add(btnNewButton);
		
		
		/**
		 * przycisk dalej
		 */
		JButton btnNewButton_1 = new JButton("Dalej");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrzedmiotGrupa pg = (PrzedmiotGrupa)przedmiotyWGrupach
					.stream()
					.filter(x -> x.getPrzedmiot().equals((Przedmiot)comboPrzedmiot.getSelectedItem()))
					.filter(x -> x.getGrupa().equals((Grupa)comboGrupa.getSelectedItem()))
					.toArray()[0];
				System.out.println(pg);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new Selection("Wybierz pytania", frame, db, pg);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(btnNewButton_1);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	parent.setEnabled(true);
		    }
		});
	}

}
