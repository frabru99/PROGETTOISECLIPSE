package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class DestinazionePanel extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private String parametro;
	private JTextArea textAreaCorsi;

	/**
	 * Create the frame.
	 */
	public DestinazionePanel(String parametro) {
		this.parametro=parametro;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textAreaCorsi = new JTextArea();
		textAreaCorsi.setBounds(25, 22, 353, 185);
		contentPane.add(textAreaCorsi);
		
		
	}
	
	public JTextArea getJTextAreaCorsi() {
		return textAreaCorsi;
	}
}
