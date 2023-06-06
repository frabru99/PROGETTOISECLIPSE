package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class HomePage extends JFrame {

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
//					MainPage frame = new MainPage();
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
	public HomePage(String codiceCliente, String email) {
		setResizable(false);
		
		this.codCliente = codiceCliente;
		this.email = email;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEffettuaPren = new JButton("Effettua Prenotazione");
		btnEffettuaPren.setFont(new Font("Consolas", Font.BOLD, 9));
		btnEffettuaPren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new RicercaCorsi(codiceCliente, email).setVisible(true);
			}
		});
		btnEffettuaPren.setBounds(131, 53, 140, 62);
		contentPane.add(btnEffettuaPren);
		
		JButton btnAbbonati = new JButton("Abbonati");
		btnAbbonati.setFont(new Font("Consolas", Font.BOLD, 9));
		btnAbbonati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//creare vista abbonamenti SEGUENDO IL SEQUENCE DIAGRAM
			}
		});
		btnAbbonati.setBounds(131, 139, 140, 62);
		contentPane.add(btnAbbonati);
	}
}
