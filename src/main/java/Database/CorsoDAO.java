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
public class CorsoDAO {
	
	//Variabili membro.
	private int codiceCorso;
	private int postiDisponibili;
	private int idSalaperCorsi;
	private String nomeCorso;
	private String istruttore;
	private String oraInizio;
	private String durataCorso;
	private SalaperCorsiDAO salaCorso;
	private ArrayList<GiornoDAO> giorni;
	
	
	/**
	 * Costruttore per caricamento da DB attraverso la PK
	 * @param codiceCorso
	 */
	public CorsoDAO(int codiceCorso) {
		
		this.codiceCorso=codiceCorso;
		this.giorni=new ArrayList<GiornoDAO>();
		caricaDaDB();
		
	}

	
	/**
	 * Costruttore vuoto per inizializzazione
	 */
	public CorsoDAO() {
		
		super();
		this.giorni=new ArrayList<GiornoDAO>();
		
	}
	
	
	/**
	 * Funzione di loading degli attributi del DAO attraverso una query di SELECT
	 */
	public void caricaDaDB() {
		
		String query="SELECT * FROM Corso WHERE CodiceCorso='"+this.codiceCorso+"';";
		
		try {
			
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				this.setNomeCorso(rs.getString("nomeCorso"));
				this.setIstruttore(rs.getString("istruttore"));
				this.setOraInizio(rs.getString("oraInizio"));
				this.setDurataCorso(rs.getString("durataCorso"));
				this.setPostiDisponibili(rs.getInt("postiDisponibili"));
				this.setIdSalaperCorsi(rs.getInt("SalaperCorsi_idSalaperCorsi"));
				
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[CORSO-DAO] Errore nel metodo caricaDaDB");
			
		}
		
	}
	
	
	/**
	 * Funzione di loading dell'array GiornoDAO
	 */
	public void caricaGiorniCorsodaDB() {
		
		String query=new String("SELECT * FROM Giorno WHERE NomeGiorno IN (SELECT Giorno_nomeGiorno FROM Corso_has_Giorno WHERE Corso_codiceCorso=\'"+this.codiceCorso+"')");
		
		try {
			
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			while(rs.next()) {
				
				GiornoDAO giorno=new GiornoDAO();
				giorno.setNomeGiorno(rs.getString("nomeGiorno"));
				giorno.setOrarioAperturaCentro(rs.getString("orarioAperturaCentro"));
				giorno.setOrarioChiusuraCentro(rs.getString("orarioChiusuraCentro"));
				giorno.caricaCorsiGiornoDaDB();
				this.giorni.add(giorno);
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.err.println("[CORSO-DAO] Errore nel metodo caricaGiorniCorsodaDB");
			
		}	
		
	}
	
	
	/**
	 * Funzione di loading della SalaperCorsiDAO
	 */
	public void caricaSalaperCorsiCorsodaDB() {
		
		//Inizializzo la variabile membro
		salaCorso = new SalaperCorsiDAO();
		
		String query = new String ("SELECT * FROM SalaperCorsi WHERE idSalaperCorsi = "+this.idSalaperCorsi)+";";
		
		try {
			
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			if (rs.next()) {
				
				SalaperCorsiDAO sala = new SalaperCorsiDAO();
				sala.setCapienza(rs.getInt("capienza"));
				sala.setIdSalaCorsi(rs.getInt("idSalaperCorsi"));
				//Setto la variabile membro salaCorso
				this.salaCorso = sala;
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.err.println("[CORSO-DAO] Errore nel metodo caricaSalaperCorsiCorsodaDB");
			
		}	
		
	}
	
	
	/**
	 * Metodo di CREATE del CRUD
	 * @param codiceCorso
	 * @param nome
	 * @param istruttore
	 * @param oraInizio
	 * @param durataCorso
	 * @param postiDisponibili
	 * @param idSalaperCorsi
	 * @return esito
	 */
	public int salvaSuDB(int codiceCorso,String nome, String istruttore, String oraInizio, String durataCorso, int postiDisponibili,int idSalaperCorsi) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		String query="INSERT INTO Corso(codiceCorso, nomeCorso, istruttore, oraInizio, durataCorso, postiDisponibili ,SalaperCorsi_idSalaperCorsi) VALUES (\'"+codiceCorso+"\',"+"\'"+nome+"\','"+istruttore+"\','"+oraInizio+"\','"+durataCorso+"\','"+postiDisponibili+"\','"+idSalaperCorsi+"')";

		try {
			
			ret=DBConnectionManager.updateQuery(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[CORSO-DAO] Errore nella CREATE");
			ret=-1;
			
		}
		
		return ret;
		
	}
	
	
	/**
	 * Funzione di utility per incrementare automaticamente il counter del codice del corso
	 * @return id massimo (da incrementare)
	 */
	public int prelevaIdMassimo() {
		
		//Inizializzo il massimo a -1.
		int max =-1;
		//Query di select innestata per prelevare l'attuale codice maggiore
		String query = "SELECT codiceCorso FROM Corso WHERE codiceCorso >= ALL(SELECT codiceCorso FROM Corso)";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if (rs.next()) {
				
				max=rs.getInt("codiceCorso");
				
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			
			System.out.println("[CORSO-DAO] Errore nel metodo prelevaIdMassimo");
			
		}
		
		return max;
		
	}
	
	
	/**
	 * Funzione che permette di aggiornare il numero di posti disponibili di un corso
	 */
	public void updatePosti() {
		
        String query = "UPDATE Corso SET postiDisponibili = postiDisponibili - 1 WHERE codiceCorso = "+this.codiceCorso;
        
        try {
            
            DBConnectionManager.updateQuery(query);
            
        } catch (ClassNotFoundException | SQLException e) {
            
        	System.out.println("[CORSO-DAO] Errore nel metodo updatePosti");
        	
        }
        
	}
	
	
	/**
	 * Funzione di utility che permette di controllare se data una chiave, esiste quel corso sul DB ed ha disponibilità di posti
	 * @param codCorso
	 * @return disponibilità
	 */
	public int checkDisponibilitaCorsoSuDB(int codCorso) {
		
        String query = "SELECT * FROM Corso WHERE codiceCorso = "+codCorso+" AND postiDisponibili>0;";
        
        try {
            
            ResultSet rs=DBConnectionManager.selectQuery(query);
            
            if(rs.next()) {
                
               return 1;	//Se esiste torno 1
                
            } else {
            	
            	return -1;	//Se non esiste torno -1
            	
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            
        	System.out.println("[CORSO-DAO] Errore nel metodo checkCorsosuDB");
            return -1;
            
        }
       
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return postiDisponibili
	 */
	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	/**
	 * Setter
	 * @param postiDisponibili
	 */
	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
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
	 * @return nomeCorso
	 */
	public String getNomeCorso() {
		return nomeCorso;
	}

	/**
	 * Setter
	 * @param nomeCorso
	 */
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	/**
	 * Getter
	 * @return istruttore
	 */
	public String getIstruttore() {
		return istruttore;
	}

	/**
	 * Setter
	 * @param istruttore
	 */
	public void setIstruttore(String istruttore) {
		this.istruttore = istruttore;
	}

	/**
	 * Getter
	 * @return oraInizio
	 */
	public String getOraInizio() {
		return oraInizio;
	}

	/**
	 * Setter
	 * @param oraInizio
	 */
	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}

	/**
	 * Getter
	 * @return durataCorso
	 */
	public String getDurataCorso() {
		return durataCorso;
	}

	/**
	 * Setter
	 * @param durataCorso
	 */
	public void setDurataCorso(String durataCorso) {
		this.durataCorso = durataCorso;
	}

	/**
	 * Getter
	 * @return salaCorso
	 */
	public SalaperCorsiDAO getSalaCorso() {
		return salaCorso;
	}

	/**
	 * Setter
	 * @param sala
	 */
	public void setSalaCorso(SalaperCorsiDAO sala) {
		this.salaCorso = sala;
	}
	
	/**
	 * Getter
	 * @return idSalaperCorsi
	 */
	public int getIdSalaperCorsi() {
		return idSalaperCorsi;
	}

	/**
	 * Setter
	 * @param idSalaperCorsi
	 */
	public void setIdSalaperCorsi(int idSalaperCorsi) {
		this.idSalaperCorsi = idSalaperCorsi;
	}

	/**
	 * Getter
	 * @return giorni
	 */
	public ArrayList<GiornoDAO> getGiorni() {
		return giorni;
	}

	/**
	 * Setter
	 * @param giorni
	 */
	public void setGiorni(ArrayList<GiornoDAO> giorni) {
		this.giorni = giorni;
	}
	
	
}
