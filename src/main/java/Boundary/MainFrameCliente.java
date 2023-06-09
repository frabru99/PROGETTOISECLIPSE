package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import Controller.Controller;

/**
 * 
 * Classe del package Boundary che avvia il login per un cliente
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 */

public class MainFrameCliente extends JFrame {

	
	private JPanel contentPane;	//Content 
	private JTextField textFieldEmail;	
	private JTextField textFieldCodiceCliente;
	private String codiceCliente;
	private String email;

	/**
	 * 
	 * Avvia il frame
	 * 
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCliente frame = new MainFrameCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * 
	 * Costruttore del frame
	 * 
	 */
	
	public MainFrameCliente() {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Label login
		JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelLogin.setBounds(170, 47, 75, 25);
		contentPane.add(labelLogin);
		
		//Text field email
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(159, 83, 160, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		//Text field codice cliente
		textFieldCodiceCliente = new JTextField();
		textFieldCodiceCliente.setBounds(159, 126, 160, 20);
		contentPane.add(textFieldCodiceCliente);
		textFieldCodiceCliente.setColumns(10);
		
		//Label email
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setVerticalAlignment(SwingConstants.BOTTOM);
		labelEmail.setBounds(98, 86, 46, 14);
		contentPane.add(labelEmail);
		
		//Label codice cliente
		JLabel labelCodiceCliente = new JLabel("Codice Cliente");
		labelCodiceCliente.setBounds(65, 129, 84, 17);
		contentPane.add(labelCodiceCliente);
		
		//Eventuale errore
		JLabel labelErroreLogin = new JLabel("");
		labelErroreLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErroreLogin.setBounds(10, 190, 309, 25);
		contentPane.add(labelErroreLogin);
		
		//Bottone di conferma
		JButton btnConfermaLogin = new JButton("Conferma");
		btnConfermaLogin.setBounds(212, 156, 107, 23);
		contentPane.add(btnConfermaLogin);
				
		//Listener sul bottone di conferma
		btnConfermaLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				codiceCliente = textFieldCodiceCliente.getText();
				email = textFieldEmail.getText();
				
				if(email.length()>30 || email.length()<=5) {
					labelErroreLogin.setText("Email inserita non valida");
					System.out.println("Email con più di 30 caratteri totali o minori/uguali a 5.");
					return;
				}
				
				//Chiamata al controller
				boolean esito = Controller.LoginCentroSportivo(codiceCliente, email);
				
				if (esito == false) {
					labelErroreLogin.setText("Errore login. Credenziali errate");
				}
				
				else if (esito == true) {
				
					dispose();
					new HomePage(codiceCliente, email).setVisible(true); //home page di scelta
					
				}
				
			}
			
		});
		
	}
	
}
	
		



