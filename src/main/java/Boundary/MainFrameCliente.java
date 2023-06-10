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
import javax.swing.ImageIcon;
import java.awt.Color;

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
		setResizable(false);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Label login
		JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelLogin.setBounds(159, 47, 75, 25);
		contentPane.add(labelLogin);
		
		//Text field email
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(159, 103, 160, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		//Text field codice cliente
		textFieldCodiceCliente = new JTextField();
		textFieldCodiceCliente.setBounds(159, 149, 160, 20);
		contentPane.add(textFieldCodiceCliente);
		textFieldCodiceCliente.setColumns(10);
		
		//Label email
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setForeground(Color.BLACK);
		labelEmail.setVerticalAlignment(SwingConstants.BOTTOM);
		labelEmail.setBounds(103, 106, 46, 14);
		contentPane.add(labelEmail);
		
		//Label codice cliente
		JLabel labelCodiceCliente = new JLabel("Codice Cliente");
		labelCodiceCliente.setForeground(Color.BLACK);
		labelCodiceCliente.setBounds(64, 151, 84, 17);
		contentPane.add(labelCodiceCliente);
		
		//Eventuale errore
		JLabel labelErroreLogin = new JLabel("");
		labelErroreLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		labelErroreLogin.setBounds(10, 213, 309, 25);
		contentPane.add(labelErroreLogin);
		
		//Bottone di conferma
		JButton btnConfermaLogin = new JButton("Conferma");
		btnConfermaLogin.setBounds(212, 180, 107, 23);
		contentPane.add(btnConfermaLogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainFrameCliente.class.getResource("/org/springframework/context/config/spring-context.gif")));
		lblNewLabel.setBounds(-125, -14, 1029, 359);
		contentPane.add(lblNewLabel);
				
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
	
		



