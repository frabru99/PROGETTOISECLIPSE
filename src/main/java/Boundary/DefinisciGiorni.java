package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.Font;

//QUESTA RIGUARDA LA GUI DELL'AMMINISTRAZIONE

public class DefinisciGiorni extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldGiorno;
	private JTextField textFieldOraApertura;
	private JTextField textFieldOraChiusura;
	private String nomeGiorno;
	private String oraApertura;
	private String oraChiusura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefinisciGiorni frame = new DefinisciGiorni();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DefinisciGiorni() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGiorno = new JLabel("Inserire il giorno in cui il centro sarà aperto:");
		lblGiorno.setBounds(5, 5, 424, 14);
		contentPane.add(lblGiorno);
		
		textFieldGiorno = new JTextField();
		textFieldGiorno.setBounds(10, 30, 198, 20);
		contentPane.add(textFieldGiorno);
		textFieldGiorno.setColumns(10);
		
		JLabel lblOraApertura = new JLabel("Inserire l'ora di apertura del centro (formato hh:mm): ");
		lblOraApertura.setBounds(10, 61, 266, 14);
		contentPane.add(lblOraApertura);
		
		textFieldOraApertura = new JTextField();
		textFieldOraApertura.setBounds(10, 80, 198, 20);
		contentPane.add(textFieldOraApertura);
		textFieldOraApertura.setColumns(10);
		
		JLabel lblOraChiusura = new JLabel("Inserire l'ora di chiusura del centro (formato hh:mm):");
		lblOraChiusura.setBounds(10, 118, 266, 14);
		contentPane.add(lblOraChiusura);
		
		textFieldOraChiusura = new JTextField();
		textFieldOraChiusura.setBounds(10, 143, 198, 20);
		contentPane.add(textFieldOraChiusura);
		textFieldOraChiusura.setColumns(10);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setBounds(313, 227, 111, 23);
		contentPane.add(btnConferma);
		
		JLabel lblErrore = new JLabel("");
		lblErrore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErrore.setBounds(10, 227, 283, 23);
		contentPane.add(lblErrore);	
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nomeGiorno = textFieldGiorno.getText();
				oraApertura = textFieldOraApertura.getText();
				oraChiusura = textFieldOraChiusura.getText();
				//se il giorno è valido
				if(nomeGiorno.compareTo("Lunedi")==0 || nomeGiorno.compareTo("Martedi")==0 || 
						nomeGiorno.compareTo("Mercoledi")==0 || nomeGiorno.compareTo("Giovedi")==0
						|| nomeGiorno.compareTo("Venerdi")==0) {
					//controllo se il giorno sia già stato inserito nel db
					
					int ok = Controller.checkGiorno(nomeGiorno);
					if(ok!=-1) {
						//il giorno è stato già inserito precedentemente nel db e quindi devo fare l'update degli orari di apertura e chiusura
						int val = Controller.aggiornaOrariGiorno(nomeGiorno, oraApertura, oraChiusura);
						if(val!=-1) {
							lblErrore.setText("Aggiornati gli orari di apertura e chiusura!");
						}
					}else {
						//inserisco il giorno e gli orari nel db----MANCANTE ancora da passare
						int value=Controller.inserisciOrariCentro(nomeGiorno, oraApertura, oraChiusura);
						if(value!=-1) {
							lblErrore.setText("Giorno inserito correttamente");
						}
					}
				}else {
					
					textFieldGiorno.setText("");
					textFieldOraApertura.setText("");
					textFieldOraChiusura.setText("");
					lblErrore.setText("Giorno non valido");
				}
			}
		});
	}
}




//USARE SPLIT PER CHECK SU FORMAT ORARIO (HH:MM) IN QUESTA FUNZIONE