package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DbController;
import model.Przedmiot;
import model.PytanieEgzaminacyjne;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class Pytanie extends JFrame {

	private JPanel contentPane;

	DefaultListModel listModel;
	DbController db;
	JFrame parent;
	JFrame frame = this;
	Przedmiot przedmiot;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Create the frame.
	 * @param parent 
	 * @param db 
	 * @param string 
	 */
	public Pytanie(String name, DbController db, JFrame parent, DefaultListModel listModel, Przedmiot przedmiot) {
		super(name);
		this.db = db;
		this.parent = parent;
		this.listModel = listModel;
		this.przedmiot = przedmiot;
		setPreferredSize(new Dimension(500, 250));
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][40px,center][40px,center]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Twoje pytanie:");
		contentPane.add(lblNewLabel, "cell 1 0 3 1");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 1,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Odpowiedzi:");
		contentPane.add(lblNewLabel_1, "cell 1 2");
		
		JLabel lblNewLabel_3 = new JLabel("Fałsz");
		contentPane.add(lblNewLabel_3, "cell 2 2");
		
		JLabel lblNewLabel_2 = new JLabel("Prawda");
		contentPane.add(lblNewLabel_2, "cell 3 2");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 1 3,growx");
		textField_1.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton();//////////////////////////////////////////////////////////////////////1
		buttonGroup.add(rdbtnNewRadioButton);
		contentPane.add(rdbtnNewRadioButton, "cell 2 3");
		rdbtnNewRadioButton.setSelected(true);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton();
		buttonGroup.add(rdbtnNewRadioButton_1);
		contentPane.add(rdbtnNewRadioButton_1, "cell 3 3");
		

		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton_1);
		
		
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 1 4,growx");
		textField_2.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton();
		buttonGroup_1.add(rdbtnNewRadioButton_2);
		contentPane.add(rdbtnNewRadioButton_2, "cell 2 4");
		rdbtnNewRadioButton_2.setSelected(true);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton();
		buttonGroup_1.add(rdbtnNewRadioButton_3);
		contentPane.add(rdbtnNewRadioButton_3, "cell 3 4");
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rdbtnNewRadioButton_2);
		buttonGroup2.add(rdbtnNewRadioButton_3);
		
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 1 5,growx");
		textField_3.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton();
		contentPane.add(rdbtnNewRadioButton_4, "cell 2 5");
		rdbtnNewRadioButton_4.setSelected(true);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton();
		contentPane.add(rdbtnNewRadioButton_5, "cell 3 5");
		
		ButtonGroup buttonGroup3 = new ButtonGroup();
		buttonGroup3.add(rdbtnNewRadioButton_4);
		buttonGroup3.add(rdbtnNewRadioButton_5);

		
		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 1 6,growx");
		textField_4.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton();
		contentPane.add(rdbtnNewRadioButton_6, "cell 2 6");
		rdbtnNewRadioButton_6.setSelected(true);
		
		
		JRadioButton rdbtnNewRadioButton_7 = new JRadioButton();
		contentPane.add(rdbtnNewRadioButton_7, "cell 3 6");
		
		ButtonGroup buttonGroup4 = new ButtonGroup();
		buttonGroup4.add(rdbtnNewRadioButton_6);
		buttonGroup4.add(rdbtnNewRadioButton_7);

		
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 7 4 1,grow");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 8 3 1,alignx right,growy");
		
		JButton btnNewButton = new JButton("Anuluj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Dodaj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//////////////////////////////////////////////
				try {
					boolean isOk = true;
					String[] list = {textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText()};
					for(int i = 0; i < list.length; i++) {
						if(list[i].length() < 1) isOk = false;
						}
					if(!isOk) {
						JOptionPane.showMessageDialog(frame, "Sprawdź wprowadzone dane!");
						return;
					}
					
					
					
					PytanieEgzaminacyjne pe = new PytanieEgzaminacyjne(textField.getText(), przedmiot);
					if(buttonGroup.isSelected(rdbtnNewRadioButton.getModel())) pe.dodajZlaOdpowiedz(textField_1.getText());
					else pe.dodajDobraOdpowiedz(textField_1.getText());
					
					if(buttonGroup2.isSelected(rdbtnNewRadioButton_2.getModel())) pe.dodajZlaOdpowiedz(textField_2.getText());
					else pe.dodajDobraOdpowiedz(textField_2.getText());
					
					if(buttonGroup3.isSelected(rdbtnNewRadioButton_4.getModel())) pe.dodajZlaOdpowiedz(textField_3.getText());
					else pe.dodajDobraOdpowiedz(textField_3.getText());
					
					if(buttonGroup4.isSelected(rdbtnNewRadioButton_6.getModel())) pe.dodajZlaOdpowiedz(textField_4.getText());
					else pe.dodajDobraOdpowiedz(textField_4.getText());
	
					db.save(pe);
					listModel.addElement(pe);
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					parent.setEnabled(true);
				}catch(Exception exc){
					JOptionPane.showMessageDialog(frame, "Sprawdź wprowadzone dane!");
				}
			}
		});
		panel.add(btnNewButton_1);
		
		
		
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
