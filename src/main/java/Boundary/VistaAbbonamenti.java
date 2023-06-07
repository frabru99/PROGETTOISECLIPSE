package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VistaAbbonamenti extends JFrame {

	private JPanel contentPane;
	private String codCliente;
	private String email;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VistaAbbonamenti frame = new VistaAbbonamenti();
//					frame.setVisible(false);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VistaAbbonamenti(String cod, String email) {
		
		this.codCliente = cod;
		this.email=email;
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		
		JButton btnGennaio = new JButton("Gennaio");
		btnGennaio.setVisible(false);
		btnGennaio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				
				
			}
		});
		btnGennaio.setBounds(10, 64, 85, 21);
		contentPane.add(btnGennaio);
		
		JButton btnNovembre = new JButton("Novembre");
		btnNovembre.setVisible(false);
		btnNovembre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNovembre.setBounds(390, 98, 85, 21);
		contentPane.add(btnNovembre);
		
		JButton btnFebbraio = new JButton("Febbraio");
		btnFebbraio.setVisible(false);
		btnFebbraio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFebbraio.setBounds(105, 64, 85, 21);
		contentPane.add(btnFebbraio);
		
		JButton btnMarzo = new JButton("Marzo");
		btnMarzo.setVisible(false);
		btnMarzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnMarzo.setBounds(200, 64, 85, 21);
		contentPane.add(btnMarzo);
		
		JButton btnSettembre = new JButton("Settembre");
		btnSettembre.setVisible(false);
		btnSettembre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSettembre.setBounds(200, 98, 85, 21);
		contentPane.add(btnSettembre);
		
		JButton btnAprile = new JButton("Aprile");
		btnAprile.setVisible(false);
		btnAprile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAprile.setBounds(295, 64, 85, 21);
		contentPane.add(btnAprile);
		
		JButton btnMaggio = new JButton("Maggio");
		btnMaggio.setVisible(false);
		btnMaggio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnMaggio.setBounds(390, 64, 85, 21);
		contentPane.add(btnMaggio);
		
		JButton btnLuglio = new JButton("Luglio");
		btnLuglio.setVisible(false);
		btnLuglio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLuglio.setBounds(10, 98, 85, 21);
		contentPane.add(btnLuglio);
		
		JButton btnGiugno = new JButton("Giugno");
		btnGiugno.setVisible(false);
		btnGiugno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnGiugno.setBounds(485, 64, 85, 21);
		contentPane.add(btnGiugno);
		
		JButton btnAgosto = new JButton("Agosto");
		btnAgosto.setVisible(false);
		btnAgosto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAgosto.setBounds(105, 98, 85, 21);
		contentPane.add(btnAgosto);
		
		JButton btnOttobre = new JButton("Ottobre");
		btnOttobre.setVisible(false);
		btnOttobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnOttobre.setBounds(295, 98, 85, 21);
		contentPane.add(btnOttobre);
		
		JButton btnDicembre = new JButton("Dicembre");
		btnDicembre.setVisible(false);
		btnDicembre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDicembre.setBounds(485, 98, 85, 21);
		contentPane.add(btnDicembre);
		
		
		//bottone di conferma
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setVisible(false);	
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
			}
			
		});
		

		btnConferma.setBounds(463, 262, 107, 21);
		contentPane.add(btnConferma);
		
		
		
		//label riepilogo
		JLabel lblRiepilogo = new JLabel("Riepilogo:");
		lblRiepilogo.setBounds(10, 138, 85, 13);
		contentPane.add(lblRiepilogo);
		
		
		//area di testo di riepilogo
		JTextArea textAreaRiepilogo = new JTextArea();
		textAreaRiepilogo.setBounds(10, 161, 560, 91);
		contentPane.add(textAreaRiepilogo);
		
		
		
		
		
		
	
		
		//Label all'inizio della pagina
		JLabel lblAbbonamenti = new JLabel("Scegli una tipologia di abbonamento:");
		lblAbbonamenti.setBounds(10, 10, 197, 13);
		contentPane.add(lblAbbonamenti);
		
		
		
		
		
		
		//Bottone abbonamento annuale
		JButton btnAbbAnnuale = new JButton("Abbonamento Annuale");
		btnAbbAnnuale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGennaio.setVisible(false);
				btnNovembre.setVisible(false);
				btnFebbraio.setVisible(false);
				btnMarzo.setVisible(false);
				btnSettembre.setVisible(false);
				btnAprile.setVisible(false);
				btnMaggio.setVisible(false);
				btnGiugno.setVisible(false);
				btnLuglio.setVisible(false);
				btnAgosto.setVisible(false);
				btnDicembre.setVisible(false);
				btnOttobre.setVisible(false);
				btnConferma.setVisible(true);
				
				
				//
			}
		});
		
		
		//bottone abbonamento mensile
		btnAbbAnnuale.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAbbAnnuale.setBounds(10, 33, 135, 21);
		contentPane.add(btnAbbAnnuale);
		
		JButton btnAbbMensile = new JButton("Abbonamento Mensile");
		btnAbbMensile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGennaio.setVisible(true);
				btnNovembre.setVisible(true);
				btnFebbraio.setVisible(true);
				btnMarzo.setVisible(true);
				btnSettembre.setVisible(true);
				btnAprile.setVisible(true);
				btnMaggio.setVisible(true);
				btnGiugno.setVisible(true);
				btnLuglio.setVisible(true);
				btnAgosto.setVisible(true);
				btnDicembre.setVisible(true);
				btnOttobre.setVisible(true);
				btnConferma.setVisible(true);
				
				
			}
		});
		
		
		btnAbbMensile.setBounds(178, 33, 153, 21);
		contentPane.add(btnAbbMensile);
		
		
		

	}
}