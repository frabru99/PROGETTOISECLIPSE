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
public class GiornoDAO {
	
	//Variabili membro
	String nomeGiorno;
	String orarioAperturaCentro;
	String orarioChiusuraCentro;
	ArrayList<CorsoDAO> corsi;
	
	
	/**
	 * Costruttore per caricamento da DB attraverso la PK
	 * @param nomeGiorno
	 */
	public GiornoDAO(String nomeGiorno) {
		
		this.nomeGiorno=nomeGiorno;
		this.corsi=new ArrayList<CorsoDAO>();
		caricaDaDB();
		
	}

	
	/**
	 * Costruttore vuoto per inizializzazione
	 */
	public GiornoDAO() {
		
		super();
		this.corsi=new ArrayList<CorsoDAO>();
		
	}
	
	
	/**
	 * Funzione di loading degli attributi del DAO attraverso una query di SELECT
	 */
	public void caricaDaDB() {
		
		String query="SELECT * FROM Giorno WHERE nomeGiorno='"+this.nomeGiorno+"';";
		
		try {
		
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				this.setOrarioAperturaCentro(rs.getString("orarioAperturaCentro"));
				this.setOrarioChiusuraCentro(rs.getString("orarioChiusuraCentro"));
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[GIORNO-DAO] Errore nel metodo caricaDaDB");
			
		}
		
	}
	
	
	/**
	 * Funzione di loading dell'array CorsoDAO
	 */
	public void caricaCorsiGiornoDaDB() {
			
		String query=new String("SELECT * FROM Corso WHERE codiceCorso IN(SELECT Corso_codiceCorso FROM Corso_has_Giorno WHERE Giorno_nomeGiorno=\'"+this.nomeGiorno+"')");
		
		try {
			
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			while(rs.next()) {
				
				CorsoDAO corso=new CorsoDAO();
				corso.setCodiceCorso(rs.getInt("codiceCorso"));
				corso.setNomeCorso(rs.getString("nomeCorso"));
				corso.setIstruttore(rs.getString("istruttore"));
				corso.setOraInizio(rs.getString("oraInizio"));
				corso.setDurataCorso(rs.getString("durataCorso"));
				corso.setPostiDisponibili(rs.getInt("postiDisponibili"));
				corso.setIdSalaperCorsi(rs.getInt("SalaperCorsi_idSalaperCorsi"));
				this.corsi.add(corso);
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.err.println("[CORSO-DAO] Errore nel metodo caricaCorsiGiornodaDB");
			
		}
		
	}
	
	
	/**
	 * Metodo di CREATE del CRUD
	 * @param nomeGiorno
	 * @param orarioAperturaCentro
	 * @param orarioChiusuraCentro
	 * @return esito
	 */
	public int salvaSuDB(String nomeGiorno,String orarioAperturaCentro, String orarioChiusuraCentro) {
		
		
		//Variabile per il risultato della query
		int ret=0;
		
		String query="INSERT INTO Giorno(nomeGiorno, orarioAperturaCentro, orarioChiusuraCentro) VALUES (\'"+nomeGiorno+"\',"+"\'"+orarioAperturaCentro+"\','"+orarioChiusuraCentro+"\');";
		
		try {
			
			ret=DBConnectionManager.updateQuery(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[GIORNO-DAO] Errore nella CREATE");
			ret=-1;
			
		}
		
		return ret;
		
	}
	
	
	/**
	 * Funzione di utility che checka se data una PK, esiste il giorno corrispondete sul DB
	 * @param nomeGiorno
	 * @return esito
	 */
	public int checkGiornoSuDB(String nomeGiorno) {
			
		String query="SELECT * FROM Giorno WHERE nomeGiorno='"+nomeGiorno+"';";
		int ret=0;
		
			try {
				
				ResultSet rs=DBConnectionManager.selectQuery(query);
				
				if(rs.next()) {
					
					ret=1;
					
				}else {
					
					ret=-1;
					
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				
				System.out.println("[GIORNO-DAO] Errore nel metodo checkGiornoSuDB");
				
			}
			
		return ret;
		
		}
	
	
	/**
	 * Metodo che permette di aggiornare gli orari del centro dato un giorno
	 * @param nomeGiorno, oraApertura, oraChiusura
	 * @return esito
	 */
	public int updateOrariSuDB(String nomeGiorno, String oraApertura, String oraChiusura) {
		
		String query = "UPDATE Giorno SET orarioAperturaCentro = '"+oraApertura+"' , orarioChiusuraCentro ='"+oraChiusura+"' WHERE nomeGiorno = '"+nomeGiorno+"';";
		int ret=0;
		
			try {
				
				ret=DBConnectionManager.updateQuery(query);
				
			}catch(SQLException | ClassNotFoundException e) {
				
				System.out.println("[GIORNO-DAO] Errore nel metodo updateOrariSuDB");
				
			}
			
		return ret;
		
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return nomeGiorno;
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
	public ArrayList<CorsoDAO> getCorsi() {
		return corsi;
	}

	/**
	 * Setter
	 * @param corsi
	 */
	public void setCorsi(ArrayList<CorsoDAO> corsi) {
		this.corsi = corsi;
	}
	
	
}
