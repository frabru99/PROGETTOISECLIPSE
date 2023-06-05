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


public class RicercaCorsi extends JFrame {

	private JPanel contentPane;
	private JTextField textInputGiorno;
	private JButton btnConfermaGiorno;
	private JLabel LabelErrore;
	
	//variabili di utilità
	private DestinazionePanel pannelloCorsiDisponibili;
	

	//Main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaCorsi frame = new RicercaCorsi();
//					JPanel pannelloCorsiDisponibili = new JPanel();
//				frame.getContentPane().add(pannelloCorsiDisponibili);
					//frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Costruttore
	public RicercaCorsi() {
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Label di richiesta del giorno
		JLabel LabelGiorno = new JLabel("Scegliere il giorno in cui cercare i corsi (es.Lunedi)");
		LabelGiorno.setBounds(10, 11, 341, 14);
		contentPane.add(LabelGiorno);
		
		//Text input per il giorno
		textInputGiorno = new JTextField();
		textInputGiorno.setBounds(10, 36, 179, 20);
		contentPane.add(textInputGiorno);
		textInputGiorno.setColumns(10);
		
		//Bottone di conferma
		btnConfermaGiorno = new JButton("Conferma");
		btnConfermaGiorno.setBounds(273, 227, 151, 23);
		contentPane.add(btnConfermaGiorno);
		
		//Label per eventuale errore
		LabelErrore = new JLabel("");
		LabelErrore.setBounds(10, 67, 341, 14);
		contentPane.add(LabelErrore);
		
		//Listener sul bottone di conferma
		btnConfermaGiorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Acquisisco il corso dal textbox
				String giorno=textInputGiorno.getText();
				
				//Errore nel caso di formattazione errata
				if(giorno.compareTo("Lunedi")==0 || giorno.compareTo("Martedi")==0 || 
					giorno.compareTo("Mercoledi")==0 || giorno.compareTo("Giovedi")==0
					|| giorno.compareTo("Venerdi")==0) {
					
					
					//Costruisco il pannello passando il giorno scelto come parametro
//					pannelloCorsiDisponibili = new DestinazionePanel(giorno);
//					pannelloCorsiDisponibili.setVisible(true);
					LabelErrore.setText("");
					ArrayList<String> corsi = Controller.ricercaCorsiArrayStringa(giorno);
					//Se non vengono trovati corsi viene promptato un errore
					if (corsi.size()==0) {
						LabelErrore.setText("Nessun corso disponibile in quel giorno");
					}
					//Altrimenti passiamo al pannello corsi
					else {
						//Switch pannello
						//passaAlPannello();
						pannelloCorsiDisponibili = new DestinazionePanel(giorno);
						pannelloCorsiDisponibili.setVisible(true);
						//Append dei corsi
						for(int i=0;i<corsi.size();i++) {
							pannelloCorsiDisponibili.getJTextAreaCorsi().append(corsi.get(i)+"\n");
						}
						
					}
					
				}else {
					LabelErrore.setText("Giorno non valido");
				}
				
				
					
			}
			
		}) ;
		
		
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
