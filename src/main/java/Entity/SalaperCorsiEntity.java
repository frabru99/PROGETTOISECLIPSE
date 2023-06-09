package Entity;

import Database.SalaperCorsiDAO;

/**
 * Classe del package Entity che rappresenta l'oggetto del mondo reale definendone il comportamento e l'implementazione vera e propria dei metodi
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class SalaperCorsiEntity {

	//Variabili membro.	
	private int capienza;
	private int idSalaCorsi;
	
	
	/**
	 * Costruttore vuoto
	 */
	public SalaperCorsiEntity() {
		super();
	}
	
	
	/**
	 * Costruttore per caricamento da classe DAO attraverso la PK
	 * @param idSalaCorsi
	 */
	public SalaperCorsiEntity(int idSalaCorsi) {
		
		SalaperCorsiDAO db = new SalaperCorsiDAO(idSalaCorsi);
		this.capienza = db.getCapienza();
		this.idSalaCorsi = idSalaCorsi;
		
	}
	
	
	/**
	 * Costruttore per caricamento da un'istanza della classe DAO
	 * @param db
	 */
	public SalaperCorsiEntity(SalaperCorsiDAO db) {
		
		this.capienza = db.getCapienza();
        this.idSalaCorsi = db.getIdSalaCorsi();
        
	}
	
	
	//Metodo per rendere persistente il salvataggio di una sala per corsi
	public int scriviSuDB(int idSalaCorsi, int capienza) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		SalaperCorsiDAO sala= new SalaperCorsiDAO();
		
		ret = sala.salvaSuDB(idSalaCorsi, capienza);
		
		//Se la richiesta al DAO va a buon fine
		if(ret!=-1) {			
			
			this.setIdSalaCorsi(idSalaCorsi);
			this.setCapienza(capienza);
			
		}
		
		return ret;
	}
	

	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return capienza
	 */
	public int getCapienza() {
        return capienza;
    }
	
	/**
	 * Setter
	 * @param capienza
	 */
	public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
	
	/**
	 * Getter
	 * @return idSalaCorsi
	 */
    public int getIdSalaCorsi() {
        return idSalaCorsi;
    }
    
    /**
     * Setter
     * @param idSalaCorsi
     */
    public void setIdSalaCorsi(int idSalaCorsi) {
        this.idSalaCorsi = idSalaCorsi;
    }


    //TO - STRING
	@Override
	public String toString() {
		return "SalaperCorsiEntity [Capienza=" + capienza+ ", idSalaCorsi=" + idSalaCorsi + "]";
	}
    
  
}
