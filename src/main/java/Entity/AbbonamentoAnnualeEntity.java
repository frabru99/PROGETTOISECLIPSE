package Entity;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;

public class AbbonamentoAnnualeEntity {

	//Variabili membro. Data sospensione e ripresa inizializzate a null poichè opzionali.
	private int idAbbonamentoAnnuale;
	private int prezzo;
	private String dataSottoscrizione;
	private String dataScadenza;
	private String dataSospensione=null;
	private String dataRipresa=null;
	

	//Costruttore vuoto
	public AbbonamentoAnnualeEntity() {
		super();
	}
	
	
	//Costruttore per caricamento da classe DAO attraverso la PK
	public AbbonamentoAnnualeEntity(int id) {
		
		AbbonamentoAnnualeDAO db = new AbbonamentoAnnualeDAO(id);
		this.idAbbonamentoAnnuale = db.getIdAbbonamentoAnnuale();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.dataSospensione = db.getDataSopensione();
		this.dataRipresa = db.getDataRipresa();
		
	}
	
	
	//Costruttore per caricamento da un'istanza della classe DAO
	public AbbonamentoAnnualeEntity(AbbonamentoAnnualeDAO db) {
		
		this.idAbbonamentoAnnuale = db.getIdAbbonamentoAnnuale();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.dataSospensione = db.getDataSopensione();
		this.dataRipresa = db.getDataRipresa();

	}
	
	
	//Metodo chiamato dal controller in seguito ad una richiesta di un utente iscritto per salvare un abbonamento annuale
	public int scriviAbbsuDB() {
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		AbbonamentoAnnualeDAO abb = new AbbonamentoAnnualeDAO();
		
		//Chiamo la funzione prelevaIdMassimo del DAO a cui aggiungo 1 per ottenere il nuovo idAbbonamento
		int max=(abb.prelevaIdmassimo()+1);
		
		//Data corrente
		Date dataSottoscrizione = new java.sql.Date(System.currentTimeMillis());
		
		//Dopo 365 giorni dalla sottoscrizione
		Date dataScadenza = addDays(dataSottoscrizione, 365); 
		
		//AGGIUNGERE NUOVO METODO DI SCRITTURA SU DB (solo con idAbbAnn, dataSottoscrizione e dataScadenza) IN ABBONAMENTOANNUALEDAO
		ret = abb.scriviAbbonamentoAnnuale(max, dataSottoscrizione.toString(), dataScadenza.toString(), 250);
		
		//Se la richiesta al DAO va a buon fine
		if(ret!=-1) {
			
			this.idAbbonamentoAnnuale = max;
            this.dataSottoscrizione = dataSottoscrizione.toString();
            this.dataScadenza = dataScadenza.toString();
            this.prezzo = 250;	
            
		}
		
		return ret;
		
	}
	
	
	//Funzionalità che permette di sospendere un abbonamento annuale
	public int sospendiAbbonamento(int idAbb, java.util.Date dataSospensione, java.util.Date dataRipresa) {
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		AbbonamentoAnnualeDAO abb = new AbbonamentoAnnualeDAO();
		
		//Variabile per il risultato della query
		int ret = abb.sospendiAbbonamentoAnnuale(idAbb, dataSospensione, dataRipresa);
		
		return ret;
		
	}
	
	
	// CONTROLLARE SE SERVE
	
	/*
	public int sospendiAbbonamentoAnnualeEntity(String sospensione, String ripresa) { //aggiunger euna data sospensione e una data di ripresa!
		
	}
	
	
	//classica funzione di scrittura
	public int scrivisuDB(int idAbbonamentoAnnuale, String dataSottoscrizione, String dataScadenza, int prezzo) {
		
		int ret=0;
		
		AbbonamentoAnnualeDAO abb = new AbbonamentoAnnualeDAO();
		
		//provo a scrivere sul DB
		
		ret = abb.scriviAbbonamentoAnnuale(idAbbonamentoAnnuale, dataSottoscrizione, dataScadenza, prezzo);
		
		if(ret!=-1) {	
			this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
           this.dataSottoscrizione = dataSottoscrizione;
            this.dataScadenza = dataScadenza;
            this.prezzo = prezzo;	
		}
		
		return ret;
		
	}
	*/
	
	
	//Funzione di utility per aggiungere giorni all'oggetto Date
	public static Date addDays(Date date, int days) {
		
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
        
    }
	

	//GETTERS AND SETTERS
	
	public String getDataSottoscrizione() {
		return dataSottoscrizione;
	}
	
	public void setDataSottoscrizione(String dataSottoscrizione) {
		this.dataSottoscrizione = dataSottoscrizione;
	}
	
	public String getDataScadenza() {
		return dataScadenza;
	}
	
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
	public int getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getIdAbbonamentoAnnuale() {
		return idAbbonamentoAnnuale;
	}

	public void setIdAbbonamentoAnnuale(int idAbbonamentoAnnuale) {
		this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
	}

	public String getDataSospensione() {
		return dataSospensione;
	}

	public void setDataSospensione(String dataSospensione) {
		this.dataSospensione = dataSospensione;
	}

	public String getDataRipresa() {
		return dataRipresa;
	}

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
