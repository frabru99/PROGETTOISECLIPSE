package Entity;

import java.util.ArrayList;

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
public class GiornoEntity {
	
	//Variabili membro.
	String nomeGiorno;
	String orarioAperturaCentro;
	String orarioChiusuraCentro;
	ArrayList<CorsoEntity> corsi;
	
	
	/**
	 * Costruttore per caricamento da classe DAO attraverso la PK
	 * @param nomeGiorno
	 */
	public GiornoEntity(String nomeGiorno) {
	
		GiornoDAO giorno=new GiornoDAO(nomeGiorno);
		this.nomeGiorno=nomeGiorno;
		this.orarioAperturaCentro=giorno.getOrarioAperturaCentro();
		this.orarioChiusuraCentro=giorno.getOrarioChiusuraCentro();
		this.corsi=new ArrayList<CorsoEntity>();
		
		//Carico anche i corsi
		giorno.caricaCorsiGiornoDaDB();
		caricaCorsi(giorno);
		
	}
	
	
	/**
	 * Costruttore per caricamento da un'istanza della classe DAO
	 * @param giorno
	 */
	public GiornoEntity(GiornoDAO giorno) {
		
		this.nomeGiorno=giorno.getNomeGiorno();
		this.orarioAperturaCentro=giorno.getOrarioAperturaCentro();
		this.orarioChiusuraCentro=giorno.getOrarioChiusuraCentro();
		this.corsi=new ArrayList<CorsoEntity>();
		
		//Carico anche i corsi
		giorno.caricaCorsiGiornoDaDB();
		caricaCorsi(giorno);
		
	}
	
	
	/**
	 * Costruttore vuoto - inizializza l'array di corsi
	 */
	public GiornoEntity() {
		super();
		this.corsi=new ArrayList<CorsoEntity>();	
	}
	
	
	/**
	 * Metodo per rendere persistente il salvataggio di un corso
	 * @param nomeGiorno
	 * @param orarioApertura
	 * @param orarioChiusura
	 * @return
	 */
	public int scriviSuDB(String nomeGiorno, String orarioApertura, String orarioChiusura) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		GiornoDAO giorno = new GiornoDAO();
		
		ret = giorno.salvaSuDB(nomeGiorno, orarioApertura, orarioChiusura);
		
		//Se la richiesta al DAO va a buon fine
		if(ret!=-1) {	
			
			this.setNomeGiorno(nomeGiorno);
			this.setOrarioAperturaCentro(orarioApertura);
			this.setOrarioChiusuraCentro(orarioChiusura);
			
		}
		
		return ret;
		
	}
	
	
	/**
	 * Funzione di loading dei CorsoEntity
	 * @param giorno
	 */
	public void caricaCorsi(GiornoDAO giorno) {
		
		for(int i=0;i<giorno.getCorsi().size();i++) {
			
			CorsoEntity corso=new CorsoEntity(giorno.getCorsi().get(i));
			this.corsi.add(corso);	
			
		}
		
	}
	
	
	/**
	 * Metodo che permette di aggiornare gli orari del centro sportivo dato un giorno
	 * @param nomeGiorno
	 * @param oraApertura
	 * @param oraChiusura
	 * @return esito
	 */
	public int aggiornaOrari(String nomeGiorno, String oraApertura, String oraChiusura) {
		
		GiornoDAO giornodb = new GiornoDAO();
		int val=giornodb.updateOrariSuDB(nomeGiorno,oraApertura,oraChiusura);
		return val;
		
	}
	
	
	/**
	 * Funzione di utility che permette di controllare se, data una PK, esiste un giorno sul DB
	 * @param giorno
	 * @return esito
	 */
	public int checkGiorno(String giorno) {
		
		GiornoDAO giornodb = new GiornoDAO();
		int val=giornodb.checkGiornoSuDB(giorno);
		return val;
		
	}
	
	
	/**
	 * Metodo che permette di aggiornare gli inserire del centro sportivo dato un giorno
	 * @param nomeGiorno
	 * @param oraApertura
	 * @param oraChiusura
	 * @return esito
	 */
	public int inserisciOrari(String nomeGiorno, String oraApertura, String oraChiusura) {
		
		GiornoDAO giornodb = new GiornoDAO();
		int ret=giornodb.salvaSuDB(nomeGiorno, oraApertura, oraChiusura);
		return ret;
		
	}

	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return nomeGiorno
	 */
	public String getNomeGiorno() {
		return nomeGiorno;
	}
	
	/**
	 * Setter
	 * @param nomeGiorno
	 */
	public void setNomeGiorno(String nomeGiorno) {
		this.nomeGiorno = nomeGiorno;
	}
	
	/**
	 * Getter
	 * @return orarioAperturaCentro
	 */
	public String getOrarioAperturaCentro() {
		return orarioAperturaCentro;
	}
	
	/**
	 * Setter
	 * @param orarioAperturaCentro
	 */
	public void setOrarioAperturaCentro(String orarioAperturaCentro) {
		this.orarioAperturaCentro = orarioAperturaCentro;
	}
	
	/**
	 * Getter
	 * @return orarioChiusuraCentro
	 */
	public String getOrarioChiusuraCentro() {
		return orarioChiusuraCentro;
	}
	
	/**
	 * Setter
	 * @param orarioChiusuraCentro
	 */
	public void setOrarioChiusuraCentro(String orarioChiusuraCentro) {
		this.orarioChiusuraCentro = orarioChiusuraCentro;
	}
	
	/**
	 * Getter 
	 * @return corsi
	 */
	public ArrayList<CorsoEntity> getCorsi() {
		return corsi;
	}
	
	/**
	 * Setter
	 * @param corsi
	 */
	public void setCorsi(ArrayList<CorsoEntity> corsi) {
		this.corsi = corsi;
	}

	
	//TO - STRING
	@Override
	public String toString() {
		return "GiornoEntity [nomeGiorno=" + nomeGiorno + ", orarioAperturaCentro=" + orarioAperturaCentro
				+ ", orarioChiusuraCentro=" + orarioChiusuraCentro + ", corsi=" + corsi + "]";
	}
	
	
}
