package Entity;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;

public class AbbonamentoAnnualeEntity {

	private int idAbbonamentoAnnuale;
	//CAMBIARE EVENTUALMENTE I TIPI DELLE DATE IN "Date"
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String dataSospensione=null;
	private String dataRipresa=null;

	public AbbonamentoAnnualeEntity() {
		super();
	}
	
	public AbbonamentoAnnualeEntity(int id) {
		
		AbbonamentoAnnualeDAO db = new AbbonamentoAnnualeDAO(id);
		
		this.idAbbonamentoAnnuale = db.getIdAbbonamentoAnnuale();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.dataSospensione = db.getDataSopensione();
		this.dataRipresa = db.getDataRipresa();
	}
	
	public AbbonamentoAnnualeEntity(AbbonamentoAnnualeDAO db) {
		//this.idAbbonamentoAnnuale = db.getIdAbbonamentoAnnuale();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.dataSospensione = db.getDataSopensione();
		this.dataRipresa = db.getDataRipresa();
		this.idAbbonamentoAnnuale=(db.prelevaIdmassimo()+1);
	}
	
	//QUESTA LA INVOCA IL CONTROLLER
	public int ControllerScrivisuDB() {
		AbbonamentoAnnualeDAO abbann = new AbbonamentoAnnualeDAO();
		int max=(abbann.prelevaIdmassimo()+1);
		int ret=0;
		Date dataSottoscrizione = new java.sql.Date(System.currentTimeMillis()); //la data di sottoscrizione sarà, per forza di cose, coincidente con la data in cui invoco il metodo
		Date dataScadenza = this.addDays(dataSottoscrizione, 365);//la data di scadenza è esattamente 365 giorni dopo quella di sottoscrizione
		//fare eventualmente un altro scrivisuDB ma solo con id e datasottoscrizione (provare a usare il tipo Date di PrenotazioneDAO)
		ret=scriviAbbsuDB(max,dataSottoscrizione,dataScadenza);
		
		return ret;
	}
	
	//funzione per scrivere su DB tramite richiesta dell'utente (attraverso boundary)
	public int scriviAbbsuDB(int idAbbonamentoAnnuale,Date dataSottoscrizione,Date dataScadenza) {
		int ret=0;
		AbbonamentoAnnualeDAO abb = new AbbonamentoAnnualeDAO();
		//int max=(abb.prelevaIdmassimo()+1);
		//provo a scrivere sul DB (commento vecchio)
		
		Date dataScadenza = this.addDays(dataSottoscrizione, 365); //la data di scadenza è esattamente 365 giorni dopo quella di sottoscrizione
		
		//AGGIUNGERE NUOVO METODO DI SCRITTURA SU DB (solo con idAbbAnn, dataSottoscrizione e dataScadenza) IN ABBONAMENTOANNUALEDAO
		ret = abb.scriviAbbonamentoAnnuale(idAbbonamentoAnnuale, dataSottoscrizione, dataScadenza, prezzo);
		
		if(ret!=-1) {	
			this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
            this.dataSottoscrizione = dataSottoscrizione;
            this.dataScadenza = dataScadenza;
            this.prezzo = prezzo;	
		}
		
		return ret;
	}
	
	public int sospendiAbbonamentoAnnualeEntity() {
		
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
	
	//metodo per aggiungere "days" giorni all'oggetto "date"
	public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }
	
	
	public int getIdAbbonamentoMensile() {
		return idAbbonamentoAnnuale;
	}
	public void setIdAbbonamentoMensile(int idAbbonamentoMensile) {
		this.idAbbonamentoAnnuale = idAbbonamentoMensile;
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

	@Override
	public String toString() {
		return "AbbonamentoAnnualeEntity [idAbbonamentoAnnuale=" + idAbbonamentoAnnuale + ", dataSottoscrizione="
				+ dataSottoscrizione + ", dataScadenza=" + dataScadenza + ", prezzo=" + prezzo + ", dataSospensione="
				+ dataSospensione + ", dataRipresa=" + dataRipresa + "]";
	}
	
	
}
