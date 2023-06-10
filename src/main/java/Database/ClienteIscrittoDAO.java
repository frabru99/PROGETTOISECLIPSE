package Database;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Classe DAO del package Database per la gestione della persistenza dei dati ed il loro retrieval evocando i propri metodi dalle classi del layer Entity
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class ClienteIscrittoDAO {
	
	//Variabili membro.
	private String codiceCliente;
	private String nome;
	private String cognome;
	private String email;
	private int idAbbonamentoMensile=-1;	//Inizializzato a -1 per controllare se è presente l'abbonamento nel metodo caricaAbbonamento
	private int idAbbonamentoAnnuale=-1;	//Inizializzato a -1 per controllare se è presente l'abbonamento nel metodo caricaAbbonamento
	private AbbonamentoMensileDAO abbonamentoMensile;
	private AbbonamentoAnnualeDAO abbonamentoAnnuale;
	
	
	/**
	 * Costruttore per caricamento da DB attraverso la PK
	 * @param codiceCliente
	 */
	public ClienteIscrittoDAO(String codiceCliente) {
		
		this.codiceCliente=codiceCliente;
		this.abbonamentoAnnuale=new AbbonamentoAnnualeDAO();
		this.abbonamentoMensile=new AbbonamentoMensileDAO();
		caricaDaDB();
		
	}
	
	/**
	 * Costruttore vuoto per inizializzazione
	 */
	public ClienteIscrittoDAO() {
		
		super();
		this.abbonamentoAnnuale=new AbbonamentoAnnualeDAO();
		this.abbonamentoMensile=new AbbonamentoMensileDAO();
		
	}
	
	
	/**
	 * Funzione di loading degli attributi del DAO attraverso una query di SELECT
	 */
	public void caricaDaDB() {
		
		String query="SELECT * FROM ClienteIscritto WHERE codiceCliente='"+this.codiceCliente+"';";
		
			try {
				
				ResultSet rs=DBConnectionManager.selectQuery(query);
				
				if(rs.next()) {
					
					this.setNome(rs.getString("nome"));
					this.setCognome(rs.getString("cognome"));
					this.setEmail(rs.getString("email"));
					this.setIdAbbonamentoAnnuale(rs.getInt("idAbbAnnuale"));
					this.setIdAbbonamentoMensile(rs.getInt("idAbbMensile"));
					
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nel metodo caricaDaDB" + e.getMessage());
			}
		
	}
	
	
	/**
	 * Metodo di CREATE del CRUD
	 * @param codiceCliente
	 * @param nome
	 * @param cognome
	 * @param email
	 * @return esito
	 */
	public int salvaSuDB(String codiceCliente, String nome, String cognome, String email) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		String query = "INSERT INTO clienteiscritto(codiceCliente,nome,cognome,email) VALUES (\'"+codiceCliente+"\',"+"\'"+nome+"\','"+cognome+"\','"+email+"')";
	
		
		try {
			
			ret=DBConnectionManager.updateQuery(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nella CREATE");
			ret=-1;
	
		}
		
		return ret;
		
	}
	
	
	/**
	 * Metodo di UPDATE del CRUD
	 * @param idAbbonamentoMensile
	 * @return esito
	 */
	public int updateAbbonamentoMensileSuDB(int idAbbonamentoMensile) {
		
		int ret=0;
		
		String query ="UPDATE ClienteIscritto SET idAbbMensile="+idAbbonamentoMensile+" WHERE codiceCliente='"+this.codiceCliente+"';";
		
		try {
			
			ret=DBConnectionManager.updateQuery(query);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nella UPDATE ABBONAMENTO MENSILE" + e.getMessage());
			ret=-1;
	
		}
		
		return ret;
		
		
	}
	
	
	/**
	 * Metodo di UPDATE del CRUD
	 * @param idAbbonamentoAnnuale
	 * @return esito
	 */
	public int updateAbbonamentoAnnualeSuDB(int idAbbonamentoAnnuale) {
		
		int ret=0;
		
		String query ="UPDATE ClienteIscritto SET idAbbAnnuale="+idAbbonamentoAnnuale+" WHERE codiceCliente='"+this.codiceCliente+"';";
		
		try {
			
			ret=DBConnectionManager.updateQuery(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nella UPDATE ABBONAMENTO ANNUALE");
			ret=-1;
	
		}
		
		return ret;
		
	}
	
	
	/**
	 * Funzione di utility per incrementare automaticamente il counter degli id degli iscritti
	 * @return id massimo (da incrementare)
	 */
	public String prelevaIdmassimo() {
		
		//Inizializzo il massimo a null
		String max=null;
		String query = "SELECT codiceCliente FROM ClienteIscritto WHERE codiceCliente >=ALL(SELECT codiceCliente FROM ClienteIscritto)";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				max=rs.getString("codiceCliente");
				
			}
			
		}catch(ClassNotFoundException | SQLException e) {
			
			System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nel metodo prelevaIdMassimo");
			
		}
		
		return max;
		
	}
	
	
	/**
	 * Funzione di loading degli abbonamenti del cliente SE ESISTONO.
	 */
	public void caricaAbbonamentoClienteIscrittoDaDB() {
		
		String query;
			
		//Controllo se è un abbonato mensile
			
			query=new String("SELECT * FROM AbbonamentoMensile WHERE idAbbonamentoMensile= "+this.idAbbonamentoMensile+";");
		
			try {
			
				ResultSet rs = DBConnectionManager.selectQuery(query);
				
				if (rs.next()) {
					
					AbbonamentoMensileDAO dao = new AbbonamentoMensileDAO();
					dao.setIdAbbonamentoMensile(rs.getInt("idAbbonamentoMensile"));
					dao.setPrezzo(rs.getInt("prezzo"));
					dao.setDataScadenza(rs.getString("dataScadenza"));
					dao.setDataSottoscrizione(rs.getString("dataSottoscrizione"));
					dao.setNomeMese(rs.getString("nomeMese"));
					this.abbonamentoMensile =dao;
					
				}
			
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nel metodo caricaAbbonamentoClienteIscrittoDaDB");
			}
		
		//Controllo se è un abbonato annuale
		
			query=new String("SELECT * FROM AbbonamentoAnnuale WHERE idAbbonamentoAnnuale= "+this.idAbbonamentoAnnuale+" ;");	
		
			try {
				
				ResultSet rs = DBConnectionManager.selectQuery(query);
				
				if (rs.next()) {
					
					AbbonamentoAnnualeDAO dao = new AbbonamentoAnnualeDAO();
					dao.setIdAbbonamentoAnnuale(rs.getInt("idAbbonamentoAnnuale"));
					dao.setPrezzo(rs.getInt("prezzo"));
					dao.setDataScadenza(rs.getString("dataScadenza"));
					dao.setDataSottoscrizione(rs.getString("dataSottoscrizione"));
					dao.setDataSopensione(rs.getString("dataSospensione"));
					dao.setDataRipresa(rs.getString("dataRipresa"));
					this.abbonamentoAnnuale =dao;
				}
			
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nel metodo caricaAbbonamentoClienteIscrittoDaDB");
			}
				
	}
			
	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return codiceCliente
	 */
	public String getCodiceCliente() {
		return codiceCliente;
	}

	/**
	 * Setter
	 * @param codiceCliente
	 */
	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	/**
	 * Getter
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Getter
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Setter
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Getter
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

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
	 * @return abbonamentoMensile
	 */
	public AbbonamentoMensileDAO getAbbonamentoMensile() {
		return abbonamentoMensile;
	}

	/**
	 * Setter
	 * @param abbonamentoMensile
	 */
	public void setAbbonamentoMensile(AbbonamentoMensileDAO abbonamentoMensile) {
		this.abbonamentoMensile = abbonamentoMensile;
	}

	/**
	 * Getter
	 * @return abbonamentoAnnuale
	 */
	public AbbonamentoAnnualeDAO getAbbonamentoAnnuale() {
		return abbonamentoAnnuale;
	}

	/**
	 * Setter
	 * @param abbonamentoAnnuale
	 */
	public void setAbbonamentoAnnuale(AbbonamentoAnnualeDAO abbonamentoAnnuale) {
		this.abbonamentoAnnuale = abbonamentoAnnuale;
	}

	
}
