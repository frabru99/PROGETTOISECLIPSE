package Entity;

import java.sql.Date;

import Database.ClienteIscrittoDAO;
import Database.CorsoDAO;
import Database.PrenotazioneDAO;

/**
 * Classe del package Entity che rappresenta l'oggetto del mondo reale definendone il comportamento e l'implementazione vera e propria dei metodi
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class PrenotazioneEntity {

	private int idPrenotazione;
	private int codiceCorso;
	private String dataPrenotazione;
	private String codiceCliente;
	private String emailCliente;
	private ClienteIscrittoEntity cliente;
	private CorsoEntity corso;
	
	/**
	 * Costruttore che permette di CREARE una prenotazione passando le PK del corso e del cliente
	 * @param cl
	 * @param cr
	 * @throws NullPointerException
	 */
	public PrenotazioneEntity(ClienteIscrittoEntity cl , CorsoEntity cr) throws NullPointerException {

		
		if (cl==null || cr==null) {
			
			throw new NullPointerException();
			
		}
		
		this.cliente=cl;
		this.corso=cr;
		Date dataod = new java.sql.Date(System.currentTimeMillis());
		this.dataPrenotazione = dataod.toString();
		this.codiceCliente = cl.getCodiceCliente();
		this.codiceCorso = cr.getCodiceCorso();
		this.emailCliente = cl.getEmail();
		
		//Setto l'id della prenotazione chiamando il metodo prelevaIdMassimo di PrenotazioneDAO
		PrenotazioneDAO pdao = new PrenotazioneDAO();
		this.idPrenotazione = (pdao.prelevaIdMassimo()+1);
		
		//Scrivo su DB
		scriviSuDB();

	}
	
	/**
	 * Costruttore vuoto
	 */
	public PrenotazioneEntity() {
		super();
	}
	
	
	/**
	 * Costruttore per CARICAMENTO da classe DAO attraverso la PK
	 * @param idPren
	 */
	public PrenotazioneEntity(int idPren) {
		
		PrenotazioneDAO pren = new PrenotazioneDAO(idPren);		
		this.idPrenotazione = pren.getIdPrenotazione();
		this.dataPrenotazione = pren.getDataPrenotazione();
		this.codiceCliente = pren.getCodiceCliente();
		this.codiceCorso = pren.getCodiceCorso();
		this.emailCliente = pren.getEmailCliente();
		
		//Carico il cliente
		pren.caricaClienteDaDB();
		caricaCliente(pren);
		
		//Carico il corso
		pren.caricaCorsoDaDB();
		caricaCorso(pren);
	
	}
	
	
	/**
	 * Costruttore per caricamento da un'istanza della classe DAO
	 * @param pren
	 */
	public PrenotazioneEntity(PrenotazioneDAO pren) {
		
		this.idPrenotazione = pren.getIdPrenotazione();
		this.dataPrenotazione = pren.getDataPrenotazione();
		this.codiceCliente = pren.getCodiceCliente();
		this.codiceCorso = pren.getCodiceCorso();
		this.emailCliente = pren.getEmailCliente();
		
		//Carico il cliente
		pren.caricaClienteDaDB();
		caricaCliente(pren);
		
		//Carico il corso
		pren.caricaCorsoDaDB();
		caricaCorso(pren);
		
	}

	
	/**
	 * Metodo per rendere persistente il salvataggio di una prenotazione
	 * @return 
	 */
	public int scriviSuDB() {
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		PrenotazioneDAO pr = new PrenotazioneDAO();
		
		//Variabile per il risultato della query
		int ret = pr.salvaSuDB(this.idPrenotazione, this.dataPrenotazione, this.codiceCliente, this.emailCliente, this.codiceCorso);
		
		//Se la richiesta al DAO va a buon fine
		if(ret != -1) {
			
			System.out.println("Ho scritto la prenotazione!");
			
		} else {
			
			System.out.println("Non ho scritto!");
			
		}
		
		return ret;
		
	}
		
	
	/**
	 * Funzione di loading del cliente
	 * @param pren
	 */
	public void caricaCliente(PrenotazioneDAO pren) {
		
		ClienteIscrittoEntity cl = new ClienteIscrittoEntity(pren.getCliente());
		this.cliente=cl;
		
	}
	
	/**
	 * Funzione di loading del corso
	 * @param pren
	 */
	public void caricaCorso(PrenotazioneDAO pren ) {
		
		CorsoEntity cr = new CorsoEntity(pren.getCorso());
		this.corso = cr;
		
	}


	//GETTERS 
	
	/**
	 * Getter
	 * @return idPrenotazione
	 */
	public int getIdPrenotazione() {
		return idPrenotazione;
	}

	/**
	 * Getter
	 * @return dataPrenotazione
	 */
	public String getDataPrenotazione() {
		return dataPrenotazione;
	}

	/**
	 * Getter
	 * @return codiceCliente
	 */
	public String getCodiceCliente() {
		return codiceCliente;
	}

	/**
	 * Getter
	 * @return codiceCorso
	 */
	public int getCodiceCorso() {
		return codiceCorso;
	}

	/**
	 * Getter
	 * @return emailCliente
	 */
	public String getEmailCliente() {
		return emailCliente;
	}

	/**
	 * Getter
	 * @return cliente
	 */
	public ClienteIscrittoEntity getCliente() {
		return cliente;
	}

	/**
	 * Getter
	 * @return corso
	 */
	public CorsoEntity getCorso() {
		return corso;
	}
	
	
	//TO - STRING
	@Override
	public String toString() {
		
		return "PrenotazioneEntity [idPrenotazione=" + idPrenotazione + ", dataPrenotazione=" + dataPrenotazione
				+ ", codiceCliente=" + codiceCliente + ", codiceCorso=" + codiceCorso + ", emailCliente=" + emailCliente
				+ "]";
		
	}
	
	
}
