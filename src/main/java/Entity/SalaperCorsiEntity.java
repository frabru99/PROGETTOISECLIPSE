package Entity;

import Database.SalaperCorsiDAO;

public class SalaperCorsiEntity {

	//Variabili membro.	
	private int capienza;
	private int idSalaCorsi;
	
	
	//Costruttore vuoto
	public SalaperCorsiEntity() {
		super();
	}
	
	
	//Costruttore per caricamento da classe DAO attraverso la PK
	public SalaperCorsiEntity(int idSalaCorsi) {
		
		SalaperCorsiDAO db = new SalaperCorsiDAO(idSalaCorsi);
		this.capienza = db.getCapienza();
		this.idSalaCorsi = idSalaCorsi;
		
	}
	
	
	//Costruttore per caricamento da un'istanza della classe DAO
	public SalaperCorsiEntity(SalaperCorsiDAO db) {
		
		this.capienza = db.getCapienza();
        this.idSalaCorsi = db.getIdSalaCorsi();
        
	}
	
	
	//Metodo scrivi su db
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
	public int getCapienza() {
        return capienza;
    }
	
	public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
	
    public int getIdSalaCorsi() {
        return idSalaCorsi;
    }
    
    public void setIdSalaCorsi(int idSalaCorsi) {
        this.idSalaCorsi = idSalaCorsi;
    }


    //TO - STRING
	@Override
	public String toString() {
		return "SalaperCorsiEntity [Capienza=" + capienza+ ", idSalaCorsi=" + idSalaCorsi + "]";
	}
    
  
}
