package Entity;

import java.sql.Date;
import java.util.Calendar;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;
import ch.qos.logback.core.joran.conditional.IfAction;

/**
 * Classe del package Entity che rappresenta l'oggetto del mondo reale definendone il comportamento e l'implementazione vera e propria dei metodi
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class AbbonamentoMensileEntity {
	
	//Variabili membro.
	private int idAbbonamentoMensile;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String nomeMese;
	
	
	/**
	 * Costruttore per caricamento da classe DAO attraverso la PK
	 * @param id
	 */
	public AbbonamentoMensileEntity(int id) {
		
		AbbonamentoMensileDAO db = new AbbonamentoMensileDAO(id);
		this.idAbbonamentoMensile = db.getIdAbbonamentoMensile();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.nomeMese = db.getNomeMese();
		
	}
	
	
	/**
	 * Costruttore vuoto
	 */
	public AbbonamentoMensileEntity() {
		super();
	}

	
	/**
	 * Costruttore per caricamento da un'istanza della classe DAO
	 * @param db
	 */
	public AbbonamentoMensileEntity(AbbonamentoMensileDAO db) {
		
		
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.nomeMese = db.getNomeMese();
		this.idAbbonamentoMensile=(db.prelevaIdMassimo()+1);
		
	}
	
	
	/**
	 * Metodo chiamato dal controller in seguito ad una richiesta di un utente iscritto per salvare persistentemente un abbonamento mensile
	 * @param nomeMese
	 * @param dataSottoscrizione
	 * @param dataScadenza
	 * @return esito
	 */
	public int scriviSuDB(String nomeMese,String dataSottoscrizione,String dataScadenza) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		AbbonamentoMensileDAO abb = new AbbonamentoMensileDAO();
		
		//Chiamo la funzione prelevaIdMassimo del DAO a cui aggiungo 1 per ottenere il nuovo idAbbonamento
		int max=(abb.prelevaIdMassimo()+1);
		
		ret = abb.salvaSuDB(max, dataSottoscrizione, dataScadenza, 40, nomeMese);
		
		//Se la richiesta al DAO va a buon fine
		if(ret!=-1) {	
			this.idAbbonamentoMensile = max;
            this.dataSottoscrizione = dataSottoscrizione;
            this.dataScadenza = dataScadenza;
            this.prezzo = 40;
            this.nomeMese=nomeMese;
		}
		
		return ret;
		
	}
		
	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return idAbbonamentoMensile
	 */
	public int getIdAbbonamentoMensile() {
		return idAbbonamentoMensile;
	}
	
	/**
	 * Setter
	 * @param idAbbonamentoMensile
	 */
	public void setIdAbbonamentoMensile(int idAbbonamentoMensile) {
		this.idAbbonamentoMensile = idAbbonamentoMensile;
	}
	
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
	 * @return nomeMese
	 */
	public String getNomeMese() {
		return nomeMese;
	}
	
	/**
	 * Setter
	 * @param nomeMese
	 */
	public void setNomeMese(String nomeMese) {
		this.nomeMese = nomeMese;
	}

	
	//TO - STRING
	@Override
	public String toString() {
		return "AbbonamentoMensileEntity [idAbbonamentoMensile=" + idAbbonamentoMensile + ", dataSottoscrizione="
				+ dataSottoscrizione + ", dataScadenza=" + dataScadenza + ", prezzo=" + prezzo + ", nomeMese="
				+ nomeMese + "]";
	}
	

}
