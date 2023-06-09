package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * 
 * Classe del package Boundary che permette al cliente di scegliere se abbonarsi o effettuare la prenotazione
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 */

public class HomePage extends JFrame {

	private JPanel contentPane;
	private String codCliente;
	private String email;


	/**
	 * Costruttore del frame
	 */
	
	public HomePage(String codiceCliente, String email) {
		setResizable(false);
		
		this.codCliente = codiceCliente;
		this.email = email;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnEffettuaPren = new JButton("Ricerca corsi ed effettua Prenotazione");
		btnEffettuaPren.setFont(new Font("Calibri", Font.BOLD, 15));
		btnEffettuaPren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new RicercaCorsi(codiceCliente, email).setVisible(true);
			}
		});
		
		
		btnEffettuaPren.setBounds(82, 81, 352, 72);
		contentPane.add(btnEffettuaPren);
		
		JButton btnAbbonati = new JButton("Abbonati");
		btnAbbonati.setFont(new Font("Calibri", Font.BOLD, 16));
		btnAbbonati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				new VistaAbbonamenti(codiceCliente, email).setVisible(true);
				
			}
		});
		btnAbbonati.setBounds(82, 163, 352, 72);
		contentPane.add(btnAbbonati);
	}
}
