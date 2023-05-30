package Entity;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;

public class AbbonamentoAnnualeEntity {

	private int idAbbonamentoAnnuale;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String dataSospensione=null;
	private String dataRipresa=null;
	
	
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
	}
	
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
