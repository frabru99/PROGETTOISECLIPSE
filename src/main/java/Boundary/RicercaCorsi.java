package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
	private JLabel LabelErroreGiorno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaCorsi frame = new RicercaCorsi();
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
	public RicercaCorsi() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelGiorno = new JLabel("Scegliere il giorno in cui cercare i corsi (es.Lunedi)");
		LabelGiorno.setBounds(10, 11, 341, 14);
		contentPane.add(LabelGiorno);
		
		textInputGiorno = new JTextField();
		textInputGiorno.setBounds(10, 36, 179, 20);
		contentPane.add(textInputGiorno);
		textInputGiorno.setColumns(10);
		
		btnConfermaGiorno = new JButton("Conferma");
		btnConfermaGiorno.setBounds(273, 227, 151, 23);
		contentPane.add(btnConfermaGiorno);
		
		LabelErroreGiorno = new JLabel("");
		LabelErroreGiorno.setBounds(10, 67, 341, 14);
		contentPane.add(LabelErroreGiorno);
		
		textInputGiorno.addMouseListener(new MouseAdapter() {
			boolean clickato=false;
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(clickato==false) {
					textInputGiorno.setText(" ");
					clickato=true;
				}
				
			}
			
		}) ;
		
		
		btnConfermaGiorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//acquisisco il giorno dal textbox
				String giorno=textInputGiorno.getText();
				
				if(giorno.compareTo("Lunedi")!=0 || giorno.compareTo("Martedi")!=0 || 
					giorno.compareTo("Mercoledi")!=0 || giorno.compareTo("Giovedi")!=0
					|| giorno.compareTo("Venerdi")!=0) {
					LabelErroreGiorno.setText("Giorno non valido");
					
				}
				
				LabelErroreGiorno.setText("");
				
				ArrayList<String> corsi = Controller.ricercaCorsiArrayStringa(giorno);
				
				if(corsi!=null) {
					//creo una nuova finestra in cui mostro i corsi trovati
					contentPane.removeAll();
					contentPane.repaint();
					JTextArea textArea = new JTextArea();
					textArea.setBounds(22, 11, 381, 212);
					getContentPane().add(textArea);
					getContentPane().add(textArea, BorderLayout.CENTER);
					for(int i=0;i<corsi.size();i++) {
						textArea.append(corsi.get(i)+"\n");
					}
					textArea.setEditable(false);
//					ArrayList<JLabel> arraylabels = new ArrayList<JLabel>(); //prenota corso
//					for(int i=0;i<corsi.size();i++) {
//						JLabel label_corrente = arraylabels.get(i);
//						arraylabels.get(i).setText(corsi.get(i)+"\n");
//						getContentPane().add(arraylabels.get(i));
//						arraylabels.get(i).addMouseListener(new MouseAdapter() {	
//							@Override
//							public void mouseClicked(MouseEvent e) {
//								String corso_stringa =label_corrente.getText(); //prelevo il nome del corso
//								//String nome_corso = corso_stringa.split(" ")[0];
//								System.out.println(corso_stringa);
//							}
//						});
					
				}

			}
			
		}) ;
		
		
	}
}
