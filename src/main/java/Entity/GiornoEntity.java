package Entity;

import java.util.ArrayList;

import Database.GiornoDAO;

public class GiornoEntity {
	
	//Variabili membro.
	String nomeGiorno;
	String orarioAperturaCentro;
	String orarioChiusuraCentro;
	ArrayList<CorsoEntity> corsi;
	
	
	//Costruttore per caricamento da classe DAO attraverso la PK
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
	
	
	//Costruttore per caricamento da un'istanza della classe DAO
	public GiornoEntity(GiornoDAO giorno) {
		
		this.nomeGiorno=giorno.getNomeGiorno();
		this.orarioAperturaCentro=giorno.getOrarioAperturaCentro();
		this.orarioChiusuraCentro=giorno.getOrarioChiusuraCentro();
		this.corsi=new ArrayList<CorsoEntity>();
		
		//Carico anche i corsi
		giorno.caricaCorsiGiornoDaDB();
		caricaCorsi(giorno);
		
	}
	
	
	//Costruttore vuoto - inizializza l'array di corsi
	public GiornoEntity() {
		super();
		this.corsi=new ArrayList<CorsoEntity>();	
	}
	
	
	//Metodo scrivi su db
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
	
	
	//Funzione di loading dei CorsoEntity
	public void caricaCorsi(GiornoDAO giorno) {
		
		for(int i=0;i<giorno.getCorsi().size();i++) {
			
			CorsoEntity corso=new CorsoEntity(giorno.getCorsi().get(i));
			this.corsi.add(corso);	
			
		}
		
	}

	
	//GETTERS AND SETTERS
	public String getNomeGiorno() {
		return nomeGiorno;
	}
	
	public void setNomeGiorno(String nomeGiorno) {
		this.nomeGiorno = nomeGiorno;
	}
	
	public String getOrarioAperturaCentro() {
		return orarioAperturaCentro;
	}
	
	public void setOrarioAperturaCentro(String orarioAperturaCentro) {
		this.orarioAperturaCentro = orarioAperturaCentro;
	}
	
	public String getOrarioChiusuraCentro() {
		return orarioChiusuraCentro;
	}
	
	public void setOrarioChiusuraCentro(String orarioChiusuraCentro) {
		this.orarioChiusuraCentro = orarioChiusuraCentro;
	}
	
	public ArrayList<CorsoEntity> getCorsi() {
		return corsi;
	}
	
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
