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
		this.idAbbonamentoAnnuale = db.getIdAbbonamentoAnnuale();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.dataSospensione = db.getDataSopensione();
		this.dataRipresa = db.getDataRipresa();
		//this.idAbbonamentoAnnuale=(db.prelevaIdmassimo()+1);
	}
	
	
	
	//funzione per scrivere su DB tramite richiesta dell'utente (attraverso controller!)
	public int scriviAbbsuDB() {
		int ret=0;
		AbbonamentoAnnualeDAO abb = new AbbonamentoAnnualeDAO();
		
		int max=(abb.prelevaIdmassimo()+1);
		
		Date dataSottoscrizione = new java.sql.Date(System.currentTimeMillis());
		
		Date dataScadenza = addDays(dataSottoscrizione, 365); //la data di scadenza è esattamente 365 giorni dopo quella di sottoscrizione
		
		//AGGIUNGERE NUOVO METODO DI SCRITTURA SU DB (solo con idAbbAnn, dataSottoscrizione e dataScadenza) IN ABBONAMENTOANNUALEDAO
		ret = abb.scriviAbbonamentoAnnuale(max, dataSottoscrizione.toString(), dataScadenza.toString(), 250);
		
		if(ret!=-1) {	
			this.idAbbonamentoAnnuale = max;
            this.dataSottoscrizione = dataSottoscrizione.toString();
            this.dataScadenza = dataScadenza.toString();
            this.prezzo = 250;	
		}
		
		return ret;
	}
	
	public int sospendiAbbonamento(int idAbb, java.util.Date dataSospensione, java.util.Date dataRipresa) {
		
		AbbonamentoAnnualeDAO abb = new AbbonamentoAnnualeDAO();
		
		
		int ret = abb.sospendiAbbonamentoAnnuale(idAbb, dataSospensione, dataRipresa);
		
		return ret;
	}
	
//	public int sospendiAbbonamentoAnnualeEntity(String sospensione, String ripresa) { //aggiunger euna data sospensione e una data di ripresa!
//		
//	}
	
	
	//classica funzione di scrittura
//	public int scrivisuDB(int idAbbonamentoAnnuale, String dataSottoscrizione, String dataScadenza, int prezzo) {
//		
//		int ret=0;
//		
//		AbbonamentoAnnualeDAO abb = new AbbonamentoAnnualeDAO();
//		
//		//provo a scrivere sul DB
//		
//		ret = abb.scriviAbbonamentoAnnuale(idAbbonamentoAnnuale, dataSottoscrizione, dataScadenza, prezzo);
//		
//		if(ret!=-1) {	
//			this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
//            this.dataSottoscrizione = dataSottoscrizione;
//            this.dataScadenza = dataScadenza;
//            this.prezzo = prezzo;	
//		}
//		
//		return ret;
//		
//	}
	
	//metodo per aggiungere "days" giorni all'oggetto "date"
	public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
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
