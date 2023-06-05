package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class DestinazionePanel extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private String parametro;
	private JTextArea textAreaCorsi;
	private JTextField textFieldIdCorso;
	private JLabel lblErroreId;

	/**
	 * Create the frame.
	 */
	public DestinazionePanel(String parametro) {
		this.parametro=parametro;
		setBounds(100, 100, 573, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Text area
		textAreaCorsi = new JTextArea();
		textAreaCorsi.setEditable(false);
		textAreaCorsi.setBounds(17, 25, 522, 256);
		contentPane.add(textAreaCorsi);
		
		//Label per inserimento id corso
		JLabel labelIdCorso = new JLabel("Inserire l'id del corso da prenotare");
		labelIdCorso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelIdCorso.setBounds(27, 298, 212, 14);
		contentPane.add(labelIdCorso);
		
		//Inserimento id corsi
		textFieldIdCorso  = new JTextField();
		textFieldIdCorso.setBounds(242, 297, 31, 20);
		contentPane.add(textFieldIdCorso);
		textFieldIdCorso.setColumns(10);
		
		//Bottone di conferma
		JButton btnPrenotaCorso = new JButton("Conferma");
		btnPrenotaCorso.setBounds(281, 296, 113, 23);
		contentPane.add(btnPrenotaCorso);
		
		//Label errore id errato
		lblErroreId = new JLabel("");
		lblErroreId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErroreId.setBounds(380, 300, 89, 14);
		contentPane.add(lblErroreId);
		
		//Listener sul bottone
		btnPrenotaCorso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int idCorsoScelto = Integer.parseInt(textFieldIdCorso.getText());
				
				//devo gestire la prenotazione		
				
			}
			
		});
		
		
		
		
	}
	
	public JTextArea getJTextAreaCorsi() {
		return textAreaCorsi;
	}
}
