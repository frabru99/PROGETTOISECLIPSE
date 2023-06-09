package Database;

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
public class AbbonamentoMensileDAO {
	
	//Variabili membro.
	private int idAbbonamentoMensile;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String nomeMese;
	
	
	/**
	 * Costruttore per caricamento da DB attraverso la PK
	 * @param id
	 */
	public AbbonamentoMensileDAO(int id) {
		
		this.idAbbonamentoMensile = id;
		caricaDaDB(id);
		
	}
	
	/**
	 * Costruttore vuoto per inizializzazione
	 */
	public AbbonamentoMensileDAO() {
		
		super();
		
	}
	
	
	/**
	 * Funzione di loading degli attributi del DAO attraverso una query di SELECT
	 * @param id
	 */
	public void caricaDaDB(int id) {
		
		String query = "SELECT * FROM AbbonamentoMensile WHERE idAbbonamentoMensile = '"+this.idAbbonamentoMensile+"';";
		
		try {
			
			ResultSet res = DBConnectionManager.selectQuery(query);
			
			if(res.next()) {
				
				this.idAbbonamentoMensile = res.getInt("idAbbonamentoMensile");
				this.dataSottoscrizione = res.getString("dataSottoscrizione");
				this.dataScadenza=res.getString("dataScadenza");
				this.prezzo = res.getInt("prezzo");
				this.nomeMese = res.getString("nomeMese");
				
			}
			
		} catch(SQLException e) {
			System.out.println("[ABBONAMENTO-MENSILE-DAO] Errore nel metodo caricaDaDB");
		} catch(ClassNotFoundException e) {
			System.out.println("[ABBONAMENTO-MENSILE-DAO] Classe non trovata nel metodo caricaDaDB");
		}
		
	}
	
	
	/**
	 * Funzione di utility per incrementare automaticamente il counter degli id degli abbonamenti
	 * @return id massimo (da incrementare)
	 */
	public int prelevaIdMassimo() {
		
		//Inizializzo il massimo a -1
		int max=-1;
		//Query di select innestata per prelevare l'attuale id maggiore
		String query="SELECT idAbbonamentoMensile FROM AbbonamentoMensile WHERE idAbbonamentoMensile >= ALL(SELECT idAbbonamentoMensile FROM AbbonamentoMensile)";
		
		try {
			
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				max=rs.getInt("idAbbonamentoMensile");
				
			}
						
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("[ABBONAMENTO-MENSILE-DAO] Errore nel metodo prelevaIdMassimo");
		}
		
		return max;
		
	}
	
	
	/**
	 * Metodo di CREATE del CRUD
	 * @param id
	 * @param dataSottoscrizione
	 * @param dataScadenza
	 * @param prezzo
	 * @param nomeMese
	 * @return esito
	 */
	public int salvaSuDB(int id, String dataSottoscrizione, String dataScadenza, int prezzo, String nomeMese){
		
		//Variabile per il risultato della query
		int ret =0;
		
		String query = "INSERT INTO AbbonamentoMensile(idAbbonamentoMensile, dataSottoscrizione, dataScadenza, prezzo, nomeMese)  VALUES( \'"+id+"\',"+"\'"+dataSottoscrizione+"\','"+dataScadenza+"\','"+prezzo+"\','"+nomeMese+"\');";
	

		try {
			
			ret = DBConnectionManager.updateQuery(query);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[ABBONAMENTO-MENSILE-DAO] Errore nella CREATE");
			ret = -1;	//In caso di esito negativo della query
			
		}
		
		return ret;
	
	}

	
	//SETTERS AND GETTERS
	
	/**
	 * Getter
	 * @return idAbbonamentoMensile
	 */
	public int getIdAbbonamentoMensile() {
		return idAbbonamentoMensile;
	}
	
	/**
	 * Setter
	 * @param idAbbonamentoMensile
	 */
	public void setIdAbbonamentoMensile(int idAbbonamentoMensile) {
		this.idAbbonamentoMensile = idAbbonamentoMensile;
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
	 * @return nomeMese
	 */
	public String getNomeMese() {
		return nomeMese;
	}
	
	/**
	 * Setter
	 * @param nomeMese
	 */
	public void setNomeMese(String nomeMese) {
		this.nomeMese = nomeMese;
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

	
	//TO - STRING
	@Override
	public String toString() {
		return "AbbonamentoMensileDAO [idAbbonamentoMensile=" + idAbbonamentoMensile + ", dataSottoscrizione="
				+ dataSottoscrizione + ", dataScadenza=" + dataScadenza + ", prezzo=" + prezzo + ", nomeMese="
				+ nomeMese + "]";
	}
	
}
