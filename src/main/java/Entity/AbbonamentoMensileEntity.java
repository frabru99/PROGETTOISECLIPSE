package Entity;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;

public class AbbonamentoMensileEntity {
	private int idAbbonamentoMensile;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String nomeMese;
	
	
	public AbbonamentoMensileEntity(int id) {
		
		AbbonamentoMensileDAO db = new AbbonamentoMensileDAO(id);
		
		
		this.idAbbonamentoMensile = db.getIdAbbonamentoMensile();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.nomeMese = db.getNomeMese();
	}
	
	public AbbonamentoMensileEntity(AbbonamentoMensileDAO db) {
		//this.idAbbonamentoMensile = db.getIdAbbonamentoMensile();
		this.dataSottoscrizione = db.getDataSottoscrizione();
		this.dataScadenza = db.getDataScadenza();
		this.prezzo = db.getPrezzo();
		this.nomeMese = db.getNomeMese();
		this.idAbbonamentoMensile=(db.prelevaIdMassimo()+1);
	}
	
	public int scrivisuDB(int idAbbonamentoMensile, String dataSottoscrizione, String dataScadenza, int prezzo, String nomeMese) {
		
		int ret=0;
		
		AbbonamentoMensileDAO abb = new AbbonamentoMensileDAO();
		
		//provo a scrivere sul DB
		
		ret = abb.scriviAbbonamentoMensile(idAbbonamentoMensile, dataSottoscrizione, dataScadenza, prezzo, nomeMese);
		
		if(ret!=-1) {	
			this.idAbbonamentoMensile = idAbbonamentoMensile;
            this.dataSottoscrizione = dataSottoscrizione;
            this.dataScadenza = dataScadenza;
            this.prezzo = prezzo;
            this.nomeMese=nomeMese;
		}
		
		return ret;
		
	}
	
	
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

	@Override
	public String toString() {
		return "AbbonamentoMensileEntity [idAbbonamentoMensile=" + idAbbonamentoMensile + ", dataSottoscrizione="
				+ dataSottoscrizione + ", dataScadenza=" + dataScadenza + ", prezzo=" + prezzo + ", nomeMese="
				+ nomeMese + "]";
	}
	
	
	
}
