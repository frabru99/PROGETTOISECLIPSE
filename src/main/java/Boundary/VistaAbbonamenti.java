package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VistaAbbonamenti extends JFrame {

	private JPanel contentPane;
	private String codCliente;
	private String email;
	private Date tempoSottoscrizione;
	private int anno;
	private String nomeMese;
	private int mese;
	private int giorno;
	private String dataSottoscrizione;
	private String dataScadenza;

	/**
	 * Launch the application.
	 */
	//MESSO FRAME VISIBILE SOLO PER PROVARE BISOGNA CAMBIARLO
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VistaAbbonamenti frame = new VistaAbbonamenti("Cliente_1","puppa@gmail.com");
//					frame.setVisible(true);
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
		setBounds(100, 100, 600, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label riepilogo
				JLabel lblRiepilogo = new JLabel("Riepilogo:");
				lblRiepilogo.setBounds(10, 138, 85, 13);
				contentPane.add(lblRiepilogo);
				
				
				//area di testo di riepilogo
				JTextArea textAreaRiepilogo = new JTextArea();
				textAreaRiepilogo.setEditable(false);
				textAreaRiepilogo.setBounds(10, 161, 560, 91);
				contentPane.add(textAreaRiepilogo);
				
				
				JLabel lblCheckConferma = new JLabel("");
				lblCheckConferma.setHorizontalAlignment(SwingConstants.LEFT);
				lblCheckConferma.setBounds(271, 287, 182, 13);
				contentPane.add(lblCheckConferma);
		
		
				//bottone di conferma mensile
				JButton btnConfermaMensile = new JButton("Conferma");
				btnConfermaMensile.setVisible(false);	
				btnConfermaMensile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//Alla conferma del cliente chiamo il metodo del controller per la sottoscrizione
						int ret=Controller.sottoscriviAbbonamentoMensile(codCliente,nomeMese,dataSottoscrizione,dataScadenza);
						
						//gestione del ret e label a schermo
						if(ret!=-1 && ret !=2) {
							lblCheckConferma.setText("Abbonamento sottoscritto!");
						} else if (ret ==2){
							lblCheckConferma.setText("Abbonamento già sottoscritto!");
						} else {
							lblCheckConferma.setText("Errore nella sottoscrizione!");
						}
						
					}
					
				});
				

				btnConfermaMensile.setBounds(463, 283, 107, 21);
				contentPane.add(btnConfermaMensile);
				
				//bottone di conferma Annuale
				JButton btnConfermaAnnuale = new JButton("Conferma");
				btnConfermaAnnuale.setVisible(false);	
				btnConfermaAnnuale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//Alla conferma del cliente chiamo il metodo del controller per la sottoscrizione
						int ret=Controller.sottoscriviAbbonamentoAnnuale(codCliente,dataSottoscrizione,dataScadenza);
						
						//gestione del ret e label a schermo
						if(ret!=-1 && ret !=2) {
							lblCheckConferma.setText("Abbonamento sottoscritto");
						} else if (ret ==2){
							lblCheckConferma.setText("Abbonamento già sottoscritto!");
						} else {
							lblCheckConferma.setText("Errore nella sottoscrizione!");
						}
					}
					
				});
				

				btnConfermaAnnuale.setBounds(463, 283, 107, 21);
				contentPane.add(btnConfermaAnnuale);

		
		JButton btnGennaio = new JButton("Gennaio");
		btnGennaio.setVisible(false);
		btnGennaio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//creo la data di sottosscrizione e di scadenza per mostrarle come riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Gennaio");
				dataSottoscrizione = new String("01/01/20"+anno);
				dataScadenza = new String("01/02/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/01/20"+anno+" al 01/02/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
				
			}
		});
		btnGennaio.setBounds(10, 64, 85, 21);
		contentPane.add(btnGennaio);
		
		JButton btnNovembre = new JButton("Novembre");
		btnNovembre.setVisible(false);
		btnNovembre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Novembre");
				dataSottoscrizione = new String("01/11/20"+anno);
				dataScadenza = new String("01/12/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/11/20"+anno+" al 01/12/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
			}
		});
		btnNovembre.setBounds(390, 98, 85, 21);
		contentPane.add(btnNovembre);
		
		JButton btnFebbraio = new JButton("Febbraio");
		btnFebbraio.setVisible(false);
		btnFebbraio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Febbraio");
				dataSottoscrizione = new String("01/02/20"+anno);
				dataScadenza = new String("01/03/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/02/20"+anno+" al 01/03/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
			}
		});
		btnFebbraio.setBounds(105, 64, 85, 21);
		contentPane.add(btnFebbraio);
		
		
		
		JButton btnMarzo = new JButton("Marzo");
		btnMarzo.setVisible(false);
		btnMarzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Marzo");
				dataSottoscrizione = new String("01/03/20"+anno);
				dataScadenza = new String("01/04/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/03/20"+anno+" al 01/04/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
				
			}
		});
		btnMarzo.setBounds(200, 64, 85, 21);
		contentPane.add(btnMarzo);
		
		JButton btnSettembre = new JButton("Settembre");
		btnSettembre.setVisible(false);
		btnSettembre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Settembre");
				dataSottoscrizione = new String("01/09/20"+anno);
				dataScadenza = new String("01/10/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/09/20"+anno+" al 01/10/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
			}
		});
		btnSettembre.setBounds(200, 98, 85, 21);
		contentPane.add(btnSettembre);
		
		JButton btnAprile = new JButton("Aprile");
		btnAprile.setVisible(false);
		btnAprile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Aprile");
				dataSottoscrizione = new String("01/04/20"+anno);
				dataScadenza = new String("01/05/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/04/20"+anno+" al 01/05/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
			}
		});
		btnAprile.setBounds(295, 64, 85, 21);
		contentPane.add(btnAprile);
		
		JButton btnMaggio = new JButton("Maggio");
		btnMaggio.setVisible(false);
		btnMaggio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Maggio");
				dataSottoscrizione = new String("01/05/20"+anno);
				dataScadenza = new String("01/06/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/05/20"+anno+" al 01/06/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
				
			}
		});
		btnMaggio.setBounds(390, 64, 85, 21);
		contentPane.add(btnMaggio);
		
		JButton btnLuglio = new JButton("Luglio");
		btnLuglio.setVisible(false);
		btnLuglio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Luglio");
				dataSottoscrizione = new String("01/07/20"+anno);
				dataScadenza = new String("01/08/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/07/20"+anno+" al 01/08/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
				
			}
		});
		btnLuglio.setBounds(10, 98, 85, 21);
		contentPane.add(btnLuglio);
		
		JButton btnGiugno = new JButton("Giugno");
		btnGiugno.setVisible(false);
		btnGiugno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Giugno");
				dataSottoscrizione = new String("01/06/20"+anno);
				dataScadenza = new String("01/07/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/06/20"+anno+" al 01/07/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
				
			}
		});
		btnGiugno.setBounds(485, 64, 85, 21);
		contentPane.add(btnGiugno);
		
		JButton btnAgosto = new JButton("Agosto");
		btnAgosto.setVisible(false);
		btnAgosto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Agosto");
				dataSottoscrizione = new String("01/08/20"+anno);
				dataScadenza = new String("01/09/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/08/20"+anno+" al 01/09/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
			}
		});
		btnAgosto.setBounds(105, 98, 85, 21);
		contentPane.add(btnAgosto);
		
		JButton btnOttobre = new JButton("Ottobre");
		btnOttobre.setVisible(false);
		btnOttobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Ottobre");
				dataSottoscrizione = new String("01/10/20"+anno);
				dataScadenza = new String("01/11/20"+anno);
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/10/20"+anno+" al 01/11/20"+anno+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
				
					btnConfermaMensile.setVisible(true);
				}
			}
		});
		btnOttobre.setBounds(295, 98, 85, 21);
		contentPane.add(btnOttobre);
		
		JButton btnDicembre = new JButton("Dicembre");
		btnDicembre.setVisible(false);
		btnDicembre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//resetto il text area e conferma
				textAreaRiepilogo.setText("");
				btnConfermaMensile.setVisible(false);
				
				
				//impostare il riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				nomeMese=new String("Dicembre");
				dataSottoscrizione = new String("01/12/20"+anno);
				dataScadenza = new String("01/01/20"+(anno+1));
				
				textAreaRiepilogo.setText("L'abbonamento mensile scelto va dal 01/12/20"+anno+" al 01/01/20"+(anno+1)+"\nCosto: 40.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaMensile.setVisible(true);
				}
				
			}
		});
		btnDicembre.setBounds(485, 98, 85, 21);
		contentPane.add(btnDicembre);
		
		
		
		//Label all'inizio della pagina
		JLabel lblAbbonamenti = new JLabel("Scegli una tipologia di abbonamento:");
		lblAbbonamenti.setBounds(10, 10, 197, 13);
		contentPane.add(lblAbbonamenti);
		
		
		
		//Bottone abbonamento annuale
		JButton btnAbbAnnuale = new JButton("Abbonamento Annuale");
		btnAbbAnnuale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaRiepilogo.setText("");
				btnConfermaAnnuale.setVisible(false);
				btnConfermaMensile.setVisible(false);
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
				lblCheckConferma.setText("");
				
				
				//settare il testo da visualizzare come riepilogo
				Date tempoSottoscrizione = new java.sql.Date(System.currentTimeMillis());
				anno = tempoSottoscrizione.getYear()-100;
				mese=tempoSottoscrizione.getMonth()+1;
				giorno=tempoSottoscrizione.getDay()+4;
				dataSottoscrizione = new String(giorno+"/"+mese+"/"+anno);
				dataScadenza = new String(giorno+"/"+mese+"/"+(anno+1));
				
				textAreaRiepilogo.setText("L'abbonamento annuale scelto va dal "+giorno+"/"+mese+"/"+anno+" al "+giorno+"/"+mese+"/"+(anno+1)+"\nCosto: 250.00\nPremere Conferma per proseguire con la sottoscrizione");
				
				
				
				if(textAreaRiepilogo.getText().compareTo("")!=0) {
					
					btnConfermaAnnuale.setVisible(true);
				}
				
				
				
				//
			}
		});
		btnAbbAnnuale.setBounds(10, 33, 180, 21);
		contentPane.add(btnAbbAnnuale);
		
		JButton btnAbbMensile = new JButton("Abbonamento Mensile");
		btnAbbMensile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaRiepilogo.setText("");
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
				btnConfermaMensile.setVisible(false);
				btnConfermaAnnuale.setVisible(false);
				lblCheckConferma.setText("");
			}
		});
		
		
		btnAbbMensile.setBounds(200, 33, 180, 21);
		contentPane.add(btnAbbMensile);
		
		
		
		
		

	}
}