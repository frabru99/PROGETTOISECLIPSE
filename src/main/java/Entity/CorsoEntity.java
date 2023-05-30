package Entity;

import java.util.ArrayList;

import Database.CorsoDAO;
import Database.GiornoDAO;

public class CorsoEntity {

	private int codiceCorso;
	private String nomeCorso;
	private String istruttore;
	private String oraInizio;
	private String durataCorso;
	private int idSalaperCorsi;
	private SalaperCorsiEntity salaCorso;
	private ArrayList<GiornoEntity> giorni;
	
	public CorsoEntity(int codiceCorso) {
		
		CorsoDAO corso=new CorsoDAO(codiceCorso);
		
		this.codiceCorso=codiceCorso;
		this.nomeCorso=corso.getNomeCorso();
		this.istruttore=corso.getIstruttore();
		this.oraInizio=corso.getOraInizio();
		this.durataCorso=corso.getDurataCorso();
		
		this.giorni=new ArrayList<GiornoEntity>();
		
		corso.caricaGiorniCorsodaDB();
		caricaGiorni(corso);
		
		
		corso.caricaSalaperCorsiCorsodaDB();
		caricaSalaperCorsi(corso);
		
	}
	
	
	public CorsoEntity(CorsoDAO corso) {
			
        this.codiceCorso=corso.getCodiceCorso();
        this.nomeCorso=corso.getNomeCorso();
        this.istruttore=corso.getIstruttore();
        this.oraInizio=corso.getOraInizio();
        this.durataCorso=corso.getDurataCorso();
        this.idSalaperCorsi=corso.getIdSalaperCorsi();
        this.giorni=new ArrayList<GiornoEntity>();
        
       //corso.caricaGiorniCorsodaDB();
        //caricaGiorni(corso);
        
    
        corso.caricaSalaperCorsiCorsodaDB(); 
        caricaSalaperCorsi(corso);;
	}
	
	
	
	public int scriviSuDb(int idCorso, String nomeCorso, String istruttore, String oraInizio, String durataCorso, int idSalaperCorsi) {
		int ret=0;
		
		CorsoDAO corso  = new CorsoDAO();
		//provo a scrivere sul DB
		
		ret = corso.salvaInDB(idCorso, nomeCorso, istruttore, oraInizio, durataCorso, idSalaperCorsi);
		
		if(ret!=-1) {	
			this.setCodiceCorso(idCorso);
			this.setDurataCorso(durataCorso);
			this.setIdSalaperCorsi(idSalaperCorsi);
			this.setNomeCorso(nomeCorso);
			this.setIstruttore(istruttore);
			this.setOraInizio(oraInizio);
			this.setDurataCorso(durataCorso);
		}
		
		return ret;
	}
	
	public CorsoEntity() {
		super();
	}
	
	
	public int getCodiceCorso() {
		return codiceCorso;
	}
	public void setCodiceCorso(int codiceCorso) {
		this.codiceCorso = codiceCorso;
	}
	public String getNomeCorso() {
		return nomeCorso;
	}
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}
	public String getIstruttore() {
		return istruttore;
	}
	public void setIstruttore(String istruttore) {
		this.istruttore = istruttore;
	}
	public String getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}
	public String getDurataCorso() {
		return durataCorso;
	}
	public void setDurataCorso(String durataCorso) {
		this.durataCorso = durataCorso;
	}
	public int getIdSalaperCorsi() {
		return idSalaperCorsi;
	}
	public void setIdSalaperCorsi(int idSalaperCorsi) {
		this.idSalaperCorsi = idSalaperCorsi;
	}
	public ArrayList<GiornoEntity> getGiorni() {
		return giorni;
	}
	public void setGiorni(ArrayList<GiornoEntity> giorni) {
		this.giorni = giorni;
	}

	
	
	public SalaperCorsiEntity getSalaCorso() {
		return salaCorso;
	}

	public void setSalaCorso(SalaperCorsiEntity salaCorso) {
		this.salaCorso = salaCorso;
	}

	@Override
	public String toString() {
		return "CorsoEntity [codiceCorso=" + codiceCorso + ", nomeCorso=" + nomeCorso + ", istruttore=" + istruttore
				+ ", oraInizio=" + oraInizio + ", durataCorso=" + durataCorso + ", idSalaperCorsi=" + idSalaperCorsi+"]";
		
		//TO STRING DA RIVEDERE
	}
	
	public void caricaGiorni (CorsoDAO corso) {
		
		for (int i =0;i<corso.getGiorni().size();i++) {
			
			GiornoEntity giorno = new GiornoEntity (corso.getGiorni().get(i));
			this.giorni.add(giorno);
			
		}
		
	}
	
	public void caricaSalaperCorsi(CorsoDAO corso) {
		
		
		SalaperCorsiEntity sala = new SalaperCorsiEntity(corso.getSalaCorso());
		this.salaCorso = sala;
		
		
	}
	
}
