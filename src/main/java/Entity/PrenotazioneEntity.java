package Entity;

import Database.PrenotazioneDAO;

public class PrenotazioneEntity {

	private final int idPrenotazione;
	private final String dataPrenotazione;
	private final String codiceCliente;
	private final String codiceCorso;
	private final String emailCliente;
	private final ClienteIscrittoEntity cliente;
	private final CorsoEntity corso;
	
	//Per creare una prenotazione dall'applicazione
	public PrenotazioneEntity(String codiceCliente,String codiceCorso,String dataPrenotazione,String emailCliente) throws NullPointerException {
	
		if (codiceCliente==null || codiceCorso==null) {
			
			throw new NullPointerException();
			
		}
		
		
	this.dataPrenotazione = dataPrenotazione;
	this.codiceCliente = codiceCliente;
	this.codiceCorso = codiceCorso;
	this.emailCliente = emailCliente;
	
	//Setto l'id
	PrenotazioneDAO pdao = new PrenotazioneDAO();
	this.idPrenotazione = (pdao.prelevaIdMassimo()+1);
	
	//Scrivi su db del DAO
		
	
	}
	
	
	
}
