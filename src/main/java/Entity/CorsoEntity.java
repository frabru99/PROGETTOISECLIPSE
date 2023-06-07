package Entity;

import java.util.ArrayList;

import Database.CorsoDAO;
import Database.GiornoDAO;

public class CorsoEntity {

	//Variabili membro.
	private int codiceCorso;
	private int idSalaperCorsi;
	private int postiDisponibili;
	private String nomeCorso;
	private String istruttore;
	private String oraInizio;
	private String durataCorso;
	private SalaperCorsiEntity salaCorso;
	
	
	//Costruttore vuoto
	public CorsoEntity() {
		
		super();
		
	}
	
	
	//Costruttore per caricamento da classe DAO attraverso la PK
	public CorsoEntity(int codiceCorso) {
		
		CorsoDAO corso=new CorsoDAO(codiceCorso);
		this.codiceCorso=codiceCorso;
		this.nomeCorso=corso.getNomeCorso();
		this.istruttore=corso.getIstruttore();
		this.oraInizio=corso.getOraInizio();
		this.durataCorso=corso.getDurataCorso();
		this.postiDisponibili = corso.getPostiDisponibili();
		this.idSalaperCorsi = corso.getIdSalaperCorsi();
	
		//Carico anche la sala per corsi
		corso.caricaSalaperCorsiCorsodaDB();
		caricaSalaperCorsi(corso);
		
	}
	
	
	//Costruttore per caricamento da un'istanza della classe DAO
	public CorsoEntity(CorsoDAO corso) {
			
        this.codiceCorso=corso.getCodiceCorso();
        this.nomeCorso=corso.getNomeCorso();
        this.istruttore=corso.getIstruttore();
        this.oraInizio=corso.getOraInizio();
        this.durataCorso=corso.getDurataCorso();
        this.postiDisponibili = corso.getPostiDisponibili();
        this.idSalaperCorsi=corso.getIdSalaperCorsi();

        //Carico anche la sala per corsi
        corso.caricaSalaperCorsiCorsodaDB(); 
        caricaSalaperCorsi(corso);;
        
	}
	
	
	//Funzione che permette di decrementare i posti disponibili di un corso
	public void decrementaPostiDisponibili() {
		
		this.postiDisponibili--;
		
	}
	
	
	//Metodo scrivi su db
	public int scriviSuDb(String nomeCorso, String istruttore, String oraInizio, String durataCorso, int postiDisp ,int idSalaperCorsi) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		CorsoDAO corso  = new CorsoDAO();
		
		//Chiamo la funzione prelevaIdMassimo del DAO a cui aggiungo 1 per ottenere il nuovo idCorso
		int idCorso = corso.prelevaIdMassimo()+1;
		
		ret = corso.salvaInDB(idCorso, nomeCorso, istruttore, oraInizio, durataCorso, postiDisp, idSalaperCorsi);
		
		//Se la richiesta al DAO va a buon fine
		if(ret!=-1) {
			
			this.setCodiceCorso(idCorso);
			this.setDurataCorso(durataCorso);
			this.setIdSalaperCorsi(idSalaperCorsi);
			this.setNomeCorso(nomeCorso);
			this.setIstruttore(istruttore);
			this.setOraInizio(oraInizio);
			this.setPostiDisponibili(postiDisp);
			this.setDurataCorso(durataCorso);
			
		}
		
		return ret;
		
	}
	
	
	//Funzione di loading della SalaperCorsi
	public void caricaSalaperCorsi(CorsoDAO corso) {
		
		SalaperCorsiEntity sala = new SalaperCorsiEntity(corso.getSalaCorso());
		this.salaCorso = sala;
		
	}
	
	
	//Funzione di utility che permette di controllare se data una chiave, esiste quel corso sul DB
	public int checkCorso(int idCorso) {
		
		CorsoDAO corso = new CorsoDAO();
		int val = corso.checkCorsosuDB(idCorso);
		return val;
        
	}
	
	
	//GETTERS AND SETTERS
	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
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
	
	public SalaperCorsiEntity getSalaCorso() {
		return salaCorso;
	}

	public void setSalaCorso(SalaperCorsiEntity salaCorso) {
		this.salaCorso = salaCorso;
	}

	
	//TO - STRING - ritorna informazioni sul corso , utile nel Boundary
	@Override
	public String toString() {
		
		return ("CORSO: "+this.getNomeCorso()+" #  ID: "+this.getCodiceCorso()+" #  ORA: "+this.getOraInizio()+" #  SALA: "+this.getSalaCorso().getIdSalaCorsi()+" #  POSTI DISPONIBILI:"+this.getPostiDisponibili());
		
	}
	
	
}
