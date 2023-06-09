package Entity;

import java.util.ArrayList;

import Database.CorsoDAO;
import Database.GiornoDAO;

/**
 * Classe del package Entity che rappresenta l'oggetto del mondo reale definendone il comportamento e l'implementazione vera e propria dei metodi
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
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
	
	
	/**
	 * Costruttore vuoto
	 */
	public CorsoEntity() {
		
		super();
		
	}
	
	
	/**
	 * Costruttore per caricamento da classe DAO attraverso la PK
	 * @param codiceCorso
	 */
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
	
	
	/**
	 * Costruttore per caricamento da un'istanza della classe DAO
	 * @param corso
	 */
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
	
	
	/**
	 * Funzione che permette di decrementare i posti disponibili di un corso
	 */
	public void decrementaPostiDisponibili() {
		
		this.postiDisponibili--;
		
		//Una volta inserita la prenotazione, effettuo un update dei posti del corso
        CorsoDAO corso = new CorsoDAO(codiceCorso); 
	    corso.updatePosti(); 
		
	}
	
	
	/**
	 * Metodo per rendere persistente il salvataggio di un corso
	 * @param nomeCorso
	 * @param istruttore
	 * @param oraInizio
	 * @param durataCorso
	 * @param postiDisp
	 * @param idSalaperCorsi
	 * @return esito
	 */
	public int scriviSuDB(String nomeCorso, String istruttore, String oraInizio, String durataCorso, int postiDisp ,int idSalaperCorsi) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		CorsoDAO corso  = new CorsoDAO();
		
		//Chiamo la funzione prelevaIdMassimo del DAO a cui aggiungo 1 per ottenere il nuovo idCorso
		int idCorso = corso.prelevaIdMassimo()+1;
		
		ret = corso.salvaSuDB(idCorso, nomeCorso, istruttore, oraInizio, durataCorso, postiDisp, idSalaperCorsi);
		
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
	
	
	/**
	 * Funzione di loading della SalaperCorsi
	 * @param corso
	 */
	public void caricaSalaperCorsi(CorsoDAO corso) {
		
		SalaperCorsiEntity sala = new SalaperCorsiEntity(corso.getSalaCorso());
		this.salaCorso = sala;
		
	}
	
	
	/**
	 * Funzione di utility che permette di controllare se data una chiave, esiste quel corso sul DB ed ha disponibilità di posti
	 * @param idCorso
	 * @return disponibilità
	 */
	public int checkDisponibilitaCorso(int idCorso) {
		
		CorsoDAO corso = new CorsoDAO();
		int val = corso.checkDisponibilitaCorsoSuDB(idCorso);
		return val;
        
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return postiDisponibili
	 */
	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	/**
	 * Setter
	 * @param postiDisponibili
	 */
	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}

	/**
	 * Getter
	 * @return
	 */
	public int getCodiceCorso() {
		return codiceCorso;
	}
	
	/**
	 * Setter
	 * @param codiceCorso
	 */
	public void setCodiceCorso(int codiceCorso) {
		this.codiceCorso = codiceCorso;
	}
	
	/**
	 * Getter
	 * @return nomeCorso
	 */
	public String getNomeCorso() {
		return nomeCorso;
	}
	
	/**
	 * Setter
	 * @param nomeCorso
	 */
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getIstruttore() {
		return istruttore;
	}
	
	/**
	 * Setter
	 * @param istruttore
	 */
	public void setIstruttore(String istruttore) {
		this.istruttore = istruttore;
	}
	
	/**
	 * Getter
	 * @return oraInizio
	 */
	public String getOraInizio() {
		return oraInizio;
	}
	
	/**
	 * Setter
	 * @param oraInizio
	 */
	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	/**
	 * Getter
	 * @return durataCorso
	 */
	public String getDurataCorso() {
		return durataCorso;
	}
	
	/**
	 * Setter
	 * @param durataCorso
	 */
	public void setDurataCorso(String durataCorso) {
		this.durataCorso = durataCorso;
	}
	
	/**
	 * Getter 
	 * @return
	 */
	public int getIdSalaperCorsi() {
		return idSalaperCorsi;
	}
	
	/**
	 * Setter
	 * @param idSalaperCorsi
	 */
	public void setIdSalaperCorsi(int idSalaperCorsi) {
		this.idSalaperCorsi = idSalaperCorsi;
	}
	
	/**
	 * Getter
	 * @return salaCorso
	 */
	public SalaperCorsiEntity getSalaCorso() {
		return salaCorso;
	}

	/**
	 * Setter
	 * @param salaCorso
	 */
	public void setSalaCorso(SalaperCorsiEntity salaCorso) {
		this.salaCorso = salaCorso;
	}

	
	//TO - STRING - ritorna informazioni sul corso , utile nel Boundary
	@Override
	public String toString() {
		
		return ("CORSO: "+this.getNomeCorso()+" #  ID: "+this.getCodiceCorso()+" #  ORA: "+this.getOraInizio()+" #  SALA: "+this.getSalaCorso().getIdSalaCorsi()+" #  POSTI DISPONIBILI:"+this.getPostiDisponibili());
		
	}
	
	
}
