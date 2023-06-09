package Entity;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;

/**
 * Classe del package Entity che rappresenta l'oggetto del mondo reale definendone il comportamento e l'implementazione vera e propria dei metodi
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class AbbonamentoAnnualeEntity {

	
	//Variabili membro. Data sospensione e ripresa inizializzate a null poichè opzionali.
	private int idAbbonamentoAnnuale;
	private int prezzo;
	private String dataSottoscrizione;
	private String dataScadenza;
	private String dataSospensione=null;
	private String dataRipresa=null;
	

	/**
	 * Costruttore vuoto
	 */
	public AbbonamentoAnnualeEntity() {
		super();
	}
	
	
	/**
	 * Costruttore per caricamento da classe DAO attraverso la PK
	 * @param id
	 */
	public AbbonamentoAnnualeEntity(int id) {
		
		AbbonamentoAnnualeDAO db = new AbbonamentoAnnualeDAO(id);
		this.idAbbonamentoAnnuale = db.getIdAbbonamentoAnnuale();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.dataSospensione = db.getDataSopensione();
		this.dataRipresa = db.getDataRipresa();
		
	}
	
	
	/**
	 * Costruttore per caricamento da un'istanza della classe DAO
	 * @param db
	 */
	public AbbonamentoAnnualeEntity(AbbonamentoAnnualeDAO db) {
		
		this.idAbbonamentoAnnuale = db.getIdAbbonamentoAnnuale();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.dataSospensione = db.getDataSopensione();
		this.dataRipresa = db.getDataRipresa();

	}
	
	
	/**
	 * Metodo chiamato dal controller in seguito ad una richiesta di un utente iscritto per salvare persistentemente un abbonamento annuale
	 * @param dataSottoscrizione
	 * @param dataScadenza
	 * @return esito
	 */
	public int scriviSuDB(String dataSottoscrizione, String dataScadenza) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		AbbonamentoAnnualeDAO abb = new AbbonamentoAnnualeDAO();
		
		//Chiamo la funzione prelevaIdMassimo del DAO a cui aggiungo 1 per ottenere il nuovo idAbbonamento
		int max=(abb.prelevaIdmassimo()+1);
		
		//Data corrente
		//Date dataSottoscrizione = new java.sql.Date(System.currentTimeMillis());
		
		//Dopo 365 giorni dalla sottoscrizione
		//Date dataScadenza = addDays(dataSottoscrizione, 365); 
		
		ret = abb.salvaSuDB(max, dataSottoscrizione, dataScadenza, 250);
		
		//Se la richiesta al DAO va a buon fine
		if(ret!=-1) {
			
			this.idAbbonamentoAnnuale = max;
            this.dataSottoscrizione = dataSottoscrizione;
            this.dataScadenza = dataScadenza;
            this.prezzo = 250;	
            
		}
		
		return ret;
		
	}
	
	
	/**
	 * Funzionalità DUMMY che permette di di settare una data di sospensione e una data di ripresa all'abbonamento annuale
	 * @param dataSospensione
	 * @param dataRipresa
	 * @return esito
	 */
	public int sospendiAbbonamento(java.util.Date dataSospensione, java.util.Date dataRipresa) {
		
		System.out.println("[ABBONAMENTO-ANNUALE-DAO] Settata data di sospensione e di ripresa dell'abbonamento "+this.idAbbonamentoAnnuale+" con successo");
		
		return 0;
		
	}
	
	
	/**
	 * Funzione di utility STATICper aggiungere giorni all'oggetto Date
	 * @param date
	 * @param days
	 *
	 */
	public static Date addDays(Date date, int days) {
		
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
        
    }
	

	//GETTERS AND SETTERS
	
	/**
	 * Getter 
	 * @return dataSottoscrizione
	 */
	public String getDataSottoscrizione() {
		return dataSottoscrizione;
	}
	
	/**
	 * Setter
	 * @param dataSottoscrizione
	 */
	public void setDataSottoscrizione(String dataSottoscrizione) {
		this.dataSottoscrizione = dataSottoscrizione;
	}
	
	/**
	 * Getter
	 * @return dataScadenza
	 */
	public String getDataScadenza() {
		return dataScadenza;
	}
	
	/**
	 * Setter
	 * @param dataScadenza
	 */
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
	/**
	 * Getter
	 * @return prezzo
	 */
	public int getPrezzo() {
		return prezzo;
	}
	
	/**
	 * Setter
	 * @param prezzo
	 */
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Getter
	 * @return idAbbonamentoAnnuale
	 */
	public int getIdAbbonamentoAnnuale() {
		return idAbbonamentoAnnuale;
	}

	/**
	 * Setter
	 * @param idAbbonamentoAnnuale
	 */
	public void setIdAbbonamentoAnnuale(int idAbbonamentoAnnuale) {
		this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
	}

	/**
	 * Getter
	 * @return dataSospensione
	 */
	public String getDataSospensione() {
		return dataSospensione;
	}

	/**
	 * Setter
	 * @param dataSospensione
	 */
	public void setDataSospensione(String dataSospensione) {
		this.dataSospensione = dataSospensione;
	}

	/**
	 * Getter
	 * @return dataRipresa
	 */
	public String getDataRipresa() {
		return dataRipresa;
	}

	/**
	 * Setter
	 * @param dataRipresa
	 */
	public void setDataRipresa(String dataRipresa) {
		this.dataRipresa = dataRipresa;
	}
	
	//TO - STRING

	@Override
	public String toString() {
		return "AbbonamentoAnnualeEntity [idAbbonamentoAnnuale=" + idAbbonamentoAnnuale + ", dataSottoscrizione="
				+ dataSottoscrizione + ", dataScadenza=" + dataScadenza + ", prezzo=" + prezzo + ", dataSospensione="
				+ dataSospensione + ", dataRipresa=" + dataRipresa + "]";
	}
	
	
}
