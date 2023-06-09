package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe DAO del package Database per la gestione della persistenza dei dati ed il loro retrieval evocando i propri metodi dalle classi del layer Entity
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class SalaperCorsiDAO {
	
	//Variabili membro
	private int idSalaCorsi;
	private int capienza;
	
	
	/**
	 * Costruttore per caricamento da DB attraverso la PK
	 * @param idSala
	 */
	public SalaperCorsiDAO(int idSala) {
		
		this.idSalaCorsi = idSala;		
		caricaDaDB();
		
	}
	
	
	/**
	 * Costruttore vuoto per inizializzazione
	 */
	public SalaperCorsiDAO() {
		super();
	}
	
	
	/**
	 * Funzione di loading degli attributi del DAO attraverso una query di SELECT
	 */
	public void caricaDaDB() {
		
		String query = "SELECT * FROM SalaperCorsi WHERE idSalaperCorsi='"+this.idSalaCorsi+"';";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				this.setIdSalaCorsi(rs.getInt("idSalaperCorsi"));
				this.setCapienza(rs.getInt("capienza"));		
				
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			
			System.err.println("[SALA-PER-CORSI-DAO] Errore nel metodo caricaDaDB");
			
		}
		
	}
	
	
	/**
	 * Metodo di CREATE del CRUD
	 * @param idSalaCorsi
	 * @param capienza
	 * @return esito
	 */
	public int salvaSuDB(int idSalaCorsi, int capienza) {
		
		//Variabile per il risultato della query
		int ret=0;
		
        String query = "INSERT INTO SalaperCorsi (idSalaperCorsi, capienza) VALUES (\'"+idSalaCorsi+"\',"+"\'"+capienza+"\');";
        
        try {
            
         ret =  DBConnectionManager.updateQuery(query);
            
        } catch (ClassNotFoundException | SQLException e) {
            
        	System.out.println("[SALA-PER-CORSI-DAO] Errore nella CREATE");
            ret = -1;
            
        }
        
        return ret;
	}
	

	//GETTERS AND SETTERS

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
		this.capienza =capienza;
	}
	
	
}
