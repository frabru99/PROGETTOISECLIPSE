package Database;

import java.lang.NullPointerException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.ClienteIscrittoEntity;
import Entity.CorsoEntity;

/**
 * Classe DAO del package Database per la gestione della persistenza dei dati ed il loro retrieval evocando i propri metodi dalle classi del layer Entity
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class PrenotazioneDAO {

	//Variabili membro
	private int idPrenotazione;
	private int codiceCorso;
	private String dataPrenotazione;
	private String codiceCliente;
	private String emailCliente;
	private ClienteIscrittoDAO cliente;
	private CorsoDAO corso;
	
	
	/**
	 * Costruttore per caricamento da DB attraverso la PK
	 * @param idPrenotazione
	 */
	public PrenotazioneDAO(int idPrenotazione) {
		
		this.idPrenotazione = idPrenotazione;
		caricaDaDB();
		
	}
	
	
	/**
	 * Costruttore vuoto per inizializzazione
	 */
	public PrenotazioneDAO() {
		super();
	}
	
	
	/**
	 * Funzione di loading degli attributi del DAO attraverso una query di SELECT
	 */
	public void caricaDaDB() {
		
		String query = "SELECT * FROM Prenotazione WHERE idPrenotazione='"+this.idPrenotazione+"';";	
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if (rs.next()) {
				
				this.setIdPrenotazione(rs.getInt("idPrenotazione"));
				this.setDataPrenotazione(rs.getString("dataPrenotazione"));
				this.setCodiceCliente(rs.getString("ClienteIscritto_codiceCliente"));
				this.setCodiceCorso(rs.getInt("Corso_codiceCorso"));
				this.setEmailCliente(rs.getString("ClienteIscritto_email"));
				
			}
			
		}catch(SQLException| ClassNotFoundException e) {
			
			System.out.println("[PRENOTAZIONE-DAO] Errore nel metodo caricaDaDB");
			
		}
		
	}
	
	
	/**
	 * Funzione di loading del corso a cui si riferisce la prenotazione
	 */
	public void caricaCorsoDaDB() {
		
		String query = "SELECT * FROM Corso WHERE codiceCorso =" + this.codiceCorso;
		
		//Inizializzo la variabile membro
		this.corso = new CorsoDAO();
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				CorsoDAO corso = new CorsoDAO();
				corso.setCodiceCorso(rs.getInt("codiceCorso"));
				corso.setDurataCorso(rs.getString("durataCorso"));
				corso.setIdSalaperCorsi(rs.getInt("SalaperCorsi_idSalaperCorsi"));
				corso.setNomeCorso(rs.getString("nomeCorso"));
				corso.setOraInizio(rs.getString("oraInizio"));
				corso.setIstruttore(rs.getString("istruttore"));
				//Setto la variabile membro
				this.corso=corso;
				
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			
			System.err.println("[PRENOTAZIONE-DAO] Errore nel metodo caricaCorsoDaDB");
			
		}
		
	}
	
	
	/**
	 * Funzione di loading del cliente a cui si riferisce la prenotazione
	 */
	public void caricaClienteDaDB() {
		
		String query = "SELECT * FROM ClienteIscritto WHERE codiceCliente = '" + this.codiceCliente+"';";
		
		//Inizializzo la variabile membro
		this.cliente = new ClienteIscrittoDAO();
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				ClienteIscrittoDAO cl = new ClienteIscrittoDAO();
				cl.setCodiceCliente(rs.getString("codiceCliente"));
				cl.setNome(rs.getString("nome"));
				cl.setCognome(rs.getString("cognome"));
				cl.setEmail(rs.getString("email"));
				cl.setIdAbbonamentoAnnuale(rs.getInt("idAbbAnnuale"));
				cl.setIdAbbonamentoMensile(rs.getInt("idAbbMensile"));
				//Setto la variabile membro
				this.cliente=cl;
				
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			
			System.err.println("[PRENOTAZIONE-DAO] Errore nel metodo caricaClienteDaDB");
			
		}
		
	}
	
	
	/**
	 * Funzione di utility per incrementare automaticamente il counter delle prenotazioni
	 * @return id massimo (da incrementare)
	 */
	public int prelevaIdMassimo() {
		
		//Inizializzo il massimo a -1.
		int max =-1;
		
		//Query di select innestata per prelevare l'attuale codice maggiore
		String query = "SELECT idPrenotazione FROM Prenotazione WHERE idPrenotazione >= ALL(SELECT idPrenotazione FROM Prenotazione)";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if (rs.next()) {
				
				max=rs.getInt("idPrenotazione");
				
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			
			System.err.println("[PRENOTAZIONE-DAO] Errore nel metodo prelevaIdMassimo");
			
		}
		
		return max;
		
	}
	
	
	/**
	 * Metodo di CREATE del CRUD 
	 * @param idPrenotazione
	 * @param dataPrenotazione
	 * @param codiceCliente
	 * @param email
	 * @param codiceCorso
	 * @return esito
	 */
	public int salvaSuDB(int idPrenotazione, String dataPrenotazione, String codiceCliente, String email, int codiceCorso) {
		
		//Variabile per il risultato della query
		int ret=0;
				
		String search = "SELECT * FROM Prenotazione WHERE ClienteIscritto_codiceCliente = \'"+codiceCliente+"' AND Corso_codiceCorso= \'"+codiceCorso+"';";
		
		
		try {
			
		ResultSet set = DBConnectionManager.selectQuery(search);
		
			
			if(!set.next()) {
			
	        String query = "INSERT INTO Prenotazione (idPrenotazione, dataPrenotazione, ClienteIscritto_codiceCliente, ClienteIscritto_email, Corso_codiceCorso) VALUES (\'"+idPrenotazione+"\',"+"\'"+dataPrenotazione+"\','"+codiceCliente+"\','"+email+"\','"+codiceCorso+"');";
	        
	         ret =  DBConnectionManager.updateQuery(query);
	         
	         
		     
			} else {
				
				ret =-1;
				System.err.println("Prenotazione già presente");
				
			}
            
        } catch (ClassNotFoundException | SQLException e) {
            
        	System.err.println("[PRENOTAZIONE-DAO] Errore nel metodo scrivisuDB");
            ret = -1;
            
        }
        
        return ret;
        
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return idPrenotazione
	 */
	public int getIdPrenotazione() {
		return idPrenotazione;
	}

	/**
	 * Setter
	 * @param idPrenotazione
	 */
	public void setIdPrenotazione(int idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}

	/**
	 * Getter
	 * @return dataPrenotazione
	 */
	public String getDataPrenotazione() {
		return dataPrenotazione;
	}

	/**
	 * Setter
	 * @param dataPrenotazione
	 */
	public void setDataPrenotazione(String dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	/**
	 * Getter
	 * @return codiceCliente
	 */
	public String getCodiceCliente() {
		return codiceCliente;
	}

	/**
	 * Setter
	 */
	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	/**
	 * Getter 
	 * @return codiceCorso
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
	 * @return emailCliente
	 */
	public String getEmailCliente() {
		return emailCliente;
	}

	/**
	 * Setter
	 * @param emailCliente
	 */
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	/**
	 * Getter
	 * @return cliente
	 */
	public ClienteIscrittoDAO getCliente() {
		return cliente;
	}

	/**
	 * Setter
	 * @param cliente
	 */
	public void setCliente(ClienteIscrittoDAO cliente) {
		this.cliente = cliente;
	}

	/**
	 * Getter
	 * @return corso
	 */
	public CorsoDAO getCorso() {
		return corso;
	}

	/**
	 * Setter
	 * @param corso
	 */
	public void setCorso(CorsoDAO corso) {
		this.corso = corso;
	}

	
}
	
	
	
	
	

