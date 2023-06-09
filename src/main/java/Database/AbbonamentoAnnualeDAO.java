package Database;

import java.sql.Date;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe DAO del package Database per la gestione della persistenza dei dati ed il loro retrieval evocando i propri metodi dalle classi del layer Entity
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class AbbonamentoAnnualeDAO {
	
	//Variabili membro. Data sospensione e ripresa inizializzate a null poichè opzionali.
	private int idAbbonamentoAnnuale;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String dataSopensione=null;
	private String dataRipresa=null;
	
	/**
	 * Costruttore per caricamento da DB attraverso la PK
	 * @param id
	 */
	public AbbonamentoAnnualeDAO(int id) {
		
		this.idAbbonamentoAnnuale = id;
		caricaDaDB(id);
		
	}
	
	
	/**
	 * Costruttore vuoto per inizializzazione
	 */
	public AbbonamentoAnnualeDAO() {
		
		super();
		
	}
	

	/**
	 * Funzione di loading degli attributi del DAO attraverso una query di SELECT
	 * @param id
	 */
	public void caricaDaDB(int id) {
		
		String query = "SELECT * FROM AbbonamentoAnnuale WHERE idAbbonamentoAnnuale = '"+this.idAbbonamentoAnnuale+"';";
		
		try {
			
			ResultSet res = DBConnectionManager.selectQuery(query);
			
			if(res.next()) {
				
				this.idAbbonamentoAnnuale = res.getInt("idAbbonamentoAnnuale");
				this.dataSottoscrizione = res.getString("dataSottoscrizione");
				this.dataScadenza=res.getString("dataScadenza");
				this.prezzo = res.getInt("prezzo");
				this.dataSopensione = res.getString("dataSospensione");
				this.dataRipresa = res.getString("dataRipresa");
				
			}
			
		} catch(SQLException e) {
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Errore nel metodo caricaDaDB");
		} catch(ClassNotFoundException e) {
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Classe non trovata nel metodo caricaDaDB");		
		}
		
	}
	
	
	/**
	 * Funzione di utility per incrementare automaticamente il counter degli id degli abbonamenti
	 * @return id massimo (da incrementare)
	 */
	public int prelevaIdmassimo() {
		
		//Inizializzo il massimo a -1
		int max=-1;
		//Query di select innestata per prelevare l'attuale id maggiore
		String query = "SELECT idAbbonamentoAnnuale FROM AbbonamentoAnnuale WHERE idAbbonamentoAnnuale >=ALL(SELECT idAbbonamentoAnnuale FROM AbbonamentoAnnuale)";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				max=rs.getInt("idAbbonamentoAnnuale");
				
			}
			
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Errore nel metodo prelevaIdMassimo");
		}
		
		return max;
		
	}
	
	
	/**
	 * Metodo di CREATE del CRUD
	 * @param id
	 * @param dataSottoscrizione
	 * @param dataScadenza
	 * @param prezzo
	 * @return esito
	 */
	public int salvaSuDB(int id, String dataSottoscrizione, String dataScadenza, int prezzo){
		
		//Variabile per il risultato della query
		int ret =0;

		String query = "INSERT INTO AbbonamentoAnnuale(idAbbonamentoAnnuale, dataSottoscrizione, dataScadenza, prezzo)  VALUES( \'"+id+"\',"+"\'"+dataSottoscrizione+"\','"+dataScadenza+"\','"+prezzo+"\');";
	

		try {
			
			ret = DBConnectionManager.updateQuery(query);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Errore nella CREATE");
			ret = -1;	//In caso di esito negativo della query
			
		}
		
		return ret;
	
	}
	
	
	/**
	 * Funzionalità DUMMY che permette di sospendere un abbonamento annuale
	 * @param idAbbAnnuale
	 * @param dataSospensione
	 * @param dataRipresa
	 * @return esito
	 */
	public int sospendiAbbonamentoAnnuale(int idAbbAnnuale, java.util.Date dataSospensione, java.util.Date dataRipresa) {
	
		System.out.println("[ABBONAMENTO-ANNUALE-DAO] Settata data di sospensione e di ripresa dell'abbonamento "+idAbbAnnuale+" con successo");
		
		return 0;
		
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return idAbbonamentoAnnuale
	 */
	public int getIdAbbonamentoAnnuale() {
		return idAbbonamentoAnnuale;
	}

	/**
	 * Setter
	 * @param idAbbonamentoAnnuale
	 */
	public void setIdAbbonamentoAnnuale(int idAbbonamentoAnnuale) {
		this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
	}

	/**
	 * Getter
	 * @return dataSottoscrizione
	 */
	public String getDataSottoscrizione() {
		return dataSottoscrizione;
	}

	/**
	 * Setter
	 * @param dataSottoscrizione
	 */
	public void setDataSottoscrizione(String dataSottoscrizione) {
		this.dataSottoscrizione = dataSottoscrizione;
	}

	/**
	 * Getter
	 * @return dataScadenza
	 */
	public String getDataScadenza() {
		return dataScadenza;
	}

	/**
	 * Setter
	 * @param dataScadenza
	 */
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**
	 * Getter
	 * @return prezzo
	 */
	public int getPrezzo() {
		return prezzo;
	}

	/**
	 * Setter
	 * @param prezzo
	 */
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Getter
	 * @return dataSospensione
	 */
	public String getDataSopensione() {
		return dataSopensione;
	}

	/**
	 * Setter
	 * @param dataSopensione
	 */
	public void setDataSopensione(String dataSopensione) {
		this.dataSopensione = dataSopensione;
	}

	/**
	 * Getter
	 * @return dataRipresa
	 */
	public String getDataRipresa() {
		return dataRipresa;
	}

	/**
	 * Setter
	 * @param dataRipresa
	 */
	public void setDataRipresa(String dataRipresa) {
		this.dataRipresa = dataRipresa;
	}
	
	//TO - STRING

	@Override
	public String toString() {
		
		return "AbbonamentoAnnualeDAO [idAbbonamentoAnnuale=" + idAbbonamentoAnnuale + ", dataSottoscrizione="
				+ dataSottoscrizione + ", dataScadenza=" + dataScadenza + ", prezzo=" + prezzo + ", dataSopensione="
				+ dataSopensione + ", dataRipresa=" + dataRipresa + "]";
		
	}
		
		
}
