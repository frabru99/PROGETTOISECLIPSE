package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class RicercaCorsi extends JFrame {

	private JPanel contentPane;
	private JButton btnConfermaPrenotazione;
	
	//variabili di utilità
	private DestinazionePanel pannelloCorsiDisponibili;
	private JTextField textFieldScelta;
	
	private String codCliente;
	private String email;
	
	
	
	//Main
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RicercaCorsi frame = new RicercaCorsi();
////					JPanel pannelloCorsiDisponibili = new JPanel();
////				frame.getContentPane().add(pannelloCorsiDisponibili);
//					//frame.pack();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	//Costruttore
	public RicercaCorsi(String codCliente, String email) {
		
		this.codCliente =  codCliente;
		this.email = email;
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Label di richiesta del giorno
		JLabel LabelGiorno = new JLabel("Scegliere il giorno in cui cercare i corsi: ");
		LabelGiorno.setBounds(10, 11, 341, 14);
		contentPane.add(LabelGiorno);
		
		//Bottone di conferma
		btnConfermaPrenotazione = new JButton("Conferma");
		btnConfermaPrenotazione.setBounds(328, 263, 151, 21);
		contentPane.add(btnConfermaPrenotazione);
		
		JButton btnLunedi = new JButton("Lunedì");
		btnLunedi.setToolTipText("");
		btnLunedi.setFont(new Font("Consolas", Font.BOLD, 10));
		btnLunedi.setBounds(10, 35, 107, 21);
		contentPane.add(btnLunedi);
		
		JButton btnMartedi = new JButton("Martedì");
		btnMartedi.setFont(new Font("Consolas", Font.BOLD, 10));
		btnMartedi.setBounds(10, 62, 107, 21);
		contentPane.add(btnMartedi);
		
		JButton btnMercoledi = new JButton("Mercoledì");
		btnMercoledi.setFont(new Font("Consolas", Font.BOLD, 10));
		btnMercoledi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMercoledi.setBounds(10, 93, 107, 21);
		contentPane.add(btnMercoledi);
		
		JButton btnGiovedi = new JButton("Giovedì");
		btnGiovedi.setFont(new Font("Consolas", Font.BOLD, 10));
		btnGiovedi.setBounds(10, 124, 107, 21);
		contentPane.add(btnGiovedi);
		
		JButton btnVenerdi = new JButton("Venerdì");
		btnVenerdi.setFont(new Font("Consolas", Font.BOLD, 10));
		btnVenerdi.setBounds(10, 155, 107, 21);
		contentPane.add(btnVenerdi);
		
		JTextArea textAreaCorsi = new JTextArea();
		textAreaCorsi.setWrapStyleWord(true);
		textAreaCorsi.setLineWrap(true);
		textAreaCorsi.setFont(new Font("Consolas", Font.BOLD, 12));
		textAreaCorsi.setEditable(false);
		textAreaCorsi.setBounds(127, 35, 352, 141);
		contentPane.add(textAreaCorsi);
		
		textFieldScelta = new JTextField();
		textFieldScelta.setBounds(282, 264, 36, 19);
		contentPane.add(textFieldScelta);
		textFieldScelta.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Aggiungi l'ID del corso da prenotare:");
		lblNewLabel.setBounds(98, 267, 174, 13);
		contentPane.add(lblNewLabel);
		
		JLabel labelErroreCorso = new JLabel("");
		labelErroreCorso.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErroreCorso.setBounds(178, 186, 301, 13);
		contentPane.add(labelErroreCorso);
		
		JLabel labelErrorePrenotazione = new JLabel("");
		labelErrorePrenotazione.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErrorePrenotazione.setBounds(202, 240, 277, 13);
		contentPane.add(labelErrorePrenotazione);
		
		//Listener sul bottone di conferma
		
		
		btnLunedi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				textAreaCorsi.setText("");
				labelErroreCorso.setText("");
				labelErrorePrenotazione.setText("");
				ArrayList<String> corsi = Controller.ricercaCorsiArrayStringa("Lunedi");
				//Se non vengono trovati corsi viene promptato un errore
				
				if (corsi.size()==0) {
					labelErroreCorso.setText("Nessun corso disponibile in quel giorno");
				}
				//Altrimenti passiamo al pannello corsi
				else {
					
					for(int i=0;i<corsi.size();i++) {
						textAreaCorsi.append(corsi.get(i)+"\n");
					}
					
				} 
			}	
			
	});
		
		btnMartedi.addMouseListener(new MouseAdapter() {
			
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				textAreaCorsi.setText("");
				labelErroreCorso.setText("");
				labelErrorePrenotazione.setText("");
				ArrayList<String> corsi = Controller.ricercaCorsiArrayStringa("Martedi");
				//Se non vengono trovati corsi viene promptato un errore
				
				if (corsi.size()==0) {
					labelErroreCorso.setText("Nessun corso disponibile in quel giorno");
				}
				//Altrimenti passiamo al pannello corsi
				else {
					
					for(int i=0;i<corsi.size();i++) {
						textAreaCorsi.append(corsi.get(i)+"\n");
					}
					
				} 
			}	
			
	});
		
		btnMercoledi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				textAreaCorsi.setText("");
				labelErroreCorso.setText("");
				labelErrorePrenotazione.setText("");
				ArrayList<String> corsi = Controller.ricercaCorsiArrayStringa("Mercoledi");
				//Se non vengono trovati corsi viene promptato un errore
				
				if (corsi.size()==0) {
					labelErroreCorso.setText("Nessun corso disponibile in quel giorno");
				}
				//Altrimenti passiamo al pannello corsi
				else {
					
					for(int i=0;i<corsi.size();i++) {
						textAreaCorsi.append(corsi.get(i)+"\n");
					}
					
				} 
			}	
			
	});
		
		btnGiovedi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				textAreaCorsi.setText("");
				labelErroreCorso.setText("");
				labelErrorePrenotazione.setText("");
				ArrayList<String> corsi = Controller.ricercaCorsiArrayStringa("Giovedi");
				//Se non vengono trovati corsi viene promptato un errore
				
				if (corsi.size()==0) {
					labelErroreCorso.setText("Nessun corso disponibile in quel giorno");
				}
				//Altrimenti passiamo al pannello corsi
				else {
					
					for(int i=0;i<corsi.size();i++) {
						textAreaCorsi.append(corsi.get(i)+"\n");
					}
					
				} 
			}	
			
	});
		
		btnVenerdi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				textAreaCorsi.setText("");
				labelErroreCorso.setText("");
				labelErrorePrenotazione.setText("");
				ArrayList<String> corsi = Controller.ricercaCorsiArrayStringa("Venerdi");
				//Se non vengono trovati corsi viene promptato un errore
				
				if (corsi.size()==0) {
					labelErroreCorso.setText("Nessun corso disponibile in quel giorno");
				}
				//Altrimenti passiamo al pannello corsi
				else {
					
					for(int i=0;i<corsi.size();i++) {
						textAreaCorsi.append(corsi.get(i)+"\n");
					}
					
				} 
			}	
			
	});
		
		btnConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				//check per capire se l'utente e' abbonato
				
				
				
				
				
			}	
			
	});
		
		
		
		
}
	
	//Funzione di utilità per cambiare pannello
	public void passaAlPannello() {
		remove(this.getContentPane());
		//this.getContentPane().add(pannelloCorsiDisponibili);
		this.pack();
		this.setVisible(true);
		validate();
	}
}
