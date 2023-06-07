package Entity;

import java.sql.Date;
import java.util.Calendar;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;
import ch.qos.logback.core.joran.conditional.IfAction;

public class AbbonamentoMensileEntity {
	
	//Variabili membro.
	private int idAbbonamentoMensile;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String nomeMese;
	
	
	//Costruttore per caricamento da classe DAO attraverso la PK
	public AbbonamentoMensileEntity(int id) {
		
		AbbonamentoMensileDAO db = new AbbonamentoMensileDAO(id);
		this.idAbbonamentoMensile = db.getIdAbbonamentoMensile();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.nomeMese = db.getNomeMese();
		
	}
	
	
	//Costruttore vuoto
	public AbbonamentoMensileEntity() {
		super();
	}

	
	//Costruttore per caricamento da un'istanza della classe DAO
	public AbbonamentoMensileEntity(AbbonamentoMensileDAO db) {
		
		
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.nomeMese = db.getNomeMese();
		this.idAbbonamentoMensile=(db.prelevaIdMassimo()+1);
		
	}
	
	
	//Metodo chiamato dal controller in seguito ad una richiesta di un utente iscritto per salvare un abbonamento mensile
	public int scrivisuDB(String nomeMese) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		AbbonamentoMensileDAO abb = new AbbonamentoMensileDAO();
		
		//Chiamo la funzione prelevaIdMassimo del DAO a cui aggiungo 1 per ottenere il nuovo idAbbonamento
		int max=(abb.prelevaIdMassimo()+1);
		
		// ??
		Date tempoSott = new java.sql.Date(System.currentTimeMillis());
		int anno = tempoSott.getYear();
		String dataSottoscrizione = new String();
		String dataScadenza = new String();
			
		
		//HARDCODED
		if(nomeMese.equals("Gennaio")){
			dataSottoscrizione = new String("20"+(anno-100)+"-01-01");	 //gestione hardcoded della data, scusaci ing de Luca....
			dataScadenza = new String("20"+(anno-100)+"-02-01");
		} else if(nomeMese.equals("Febbraio")){
			dataSottoscrizione = new String("20"+(anno-100)+"-02-01");	
			dataScadenza = new String("20"+(anno-100)+"-03-01");
		} else if(nomeMese.equals("Marzo")){
			dataSottoscrizione = new String("20"+(anno-100)+"-03-01");	
			dataScadenza = new String("20"+(anno-100)+"-04-01");
		} else if(nomeMese.equals("Aprile")){
			dataSottoscrizione = new String("20"+(anno-100)+"-04-01");	
			dataScadenza = new String("20"+(anno-100)+"-05-01");
		} else if(nomeMese.equals("Maggio")){
			dataSottoscrizione = new String("20"+(anno-100)+"-05-01");	
			dataScadenza = new String("20"+(anno-100)+"-06-01");
		} else if(nomeMese.equals("Giugno")){
			dataSottoscrizione = new String("20"+(anno-100)+"-06-01");	
			dataScadenza = new String("20"+(anno-100)+"-07-01");
		} else if(nomeMese.equals("Luglio")){
			dataSottoscrizione = new String("20"+(anno-100)+"-07-01");	
			dataScadenza = new String("20"+(anno-100)+"-08-01");
		} else if(nomeMese.equals("Agosto")){
			dataSottoscrizione = new String("20"+(anno-100)+"-08-01");	
			dataScadenza = new String("20"+(anno-100)+"-09-01");
		} else if(nomeMese.equals("Settembre")){
			dataSottoscrizione = new String("20"+(anno-100)+"-09-01");	
			dataScadenza = new String("20"+(anno-100)+"-10-01");
		} else if(nomeMese.equals("Ottobre")){
			dataSottoscrizione = new String("20"+(anno-100)+"-10-01");	
			dataScadenza = new String("20"+(anno-100)+"-11-01");
		} else if(nomeMese.equals("Novembre")){
			dataSottoscrizione = new String("20"+(anno-100)+"-11-01");	
			dataScadenza = new String("20"+(anno-100)+"-12-01");
		} else if(nomeMese.equals("Dicembre")){
			dataSottoscrizione = new String("20"+(anno-100)+"-01-01");	
			dataScadenza = new String(("20"+(anno-100)+1)+"-02-01");
		}
		//HARDCODED
		
		
		ret = abb.scriviAbbonamentoMensile(max, dataSottoscrizione, dataScadenza, 40, nomeMese);
		
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
	
	public int getIdAbbonamentoMensile() {
		return idAbbonamentoMensile;
	}
	
	public void setIdAbbonamentoMensile(int idAbbonamentoMensile) {
		this.idAbbonamentoMensile = idAbbonamentoMensile;
	}
	
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
	
	public String getNomeMese() {
		return nomeMese;
	}
	
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
