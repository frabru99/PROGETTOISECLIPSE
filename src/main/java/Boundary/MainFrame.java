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

public class MainFrame extends JFrame {

	
	private JPanel contentPane;	//Content 
	private JTextField textFieldEmail;	
	private JTextField textFieldCodiceCliente;
	private String codiceCliente;
	private String email;

	//Main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Costruttore del mainframe
	public MainFrame() {
				
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
		labelErroreLogin.setBounds(159, 157, 86, 20);
		contentPane.add(labelErroreLogin);
		
		//Bottone di conferma
		JButton btnConfermaLogin = new JButton("Conferma");
		btnConfermaLogin.setBounds(148, 169, 107, 23);
		contentPane.add(btnConfermaLogin);
				
		//Listener sul bottone di conferma
		btnConfermaLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				codiceCliente = textFieldCodiceCliente.getText();
				email = textFieldEmail.getText();
				
				//Chiamata al controller
				boolean esito = Controller.AccessoAlCentro(codiceCliente, email);
				
				if (esito == false) {
					labelErroreLogin.setText("Errore login. Credenziali errate");
				}
				
				else if (esito == true) {
				
					dispose();
					new RicercaCorsi().setVisible(true);
					
				}
				
			}
			
		});
		
	}
	
}
	
		



