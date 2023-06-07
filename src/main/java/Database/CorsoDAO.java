package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
	
	
	//Costruttore per caricamento da DB attraverso la PK
	public CorsoDAO(int codiceCorso) {
		
		this.codiceCorso=codiceCorso;
		this.giorni=new ArrayList<GiornoDAO>();
		caricaDaDB();
		
	}

	
	//Costruttore vuoto per inizializzazione
	public CorsoDAO() {
		
		super();
		this.giorni=new ArrayList<GiornoDAO>();
		
	}
	
	
	//Funzione di loading degli attributi del DAO attraverso una query di SELECT
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
	
	
	//Funzione di loading dell'array GiornoDAO
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
	
	
	//Funzione di loading della SalaperCorsiDAO
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
	
	
	//Metodo di CREATE del CRUD
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
	
	
	//Funzione di utility per incrementare automaticamente il counter del codice del corso
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
	
	
	//Funzione che permette di aggiornare il numero di posti disponibili di un corso
	public void updatePosti() {
		
        String query = "UPDATE Corso SET postiDisponibili = postiDisponibili - 1 WHERE codiceCorso = "+this.codiceCorso;
        
        try {
            
            DBConnectionManager.updateQuery(query);
            
        } catch (ClassNotFoundException | SQLException e) {
            
        	System.out.println("[CORSO-DAO] Errore nel metodo updatePosti");
        	
        }
        
	}
	
	
	//Funzione di utility che permette di controllare se data una chiave, esiste quel corso sul DB ed ha disponibilità di posti
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
	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}

	public int getCodiceCorso() {
		return codiceCorso;
	}

	public void setCodiceCorso(int codiceCorso) {
		this.codiceCorso = codiceCorso;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public String getIstruttore() {
		return istruttore;
	}

	public void setIstruttore(String istruttore) {
		this.istruttore = istruttore;
	}

	public String getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}

	public String getDurataCorso() {
		return durataCorso;
	}

	public void setDurataCorso(String durataCorso) {
		this.durataCorso = durataCorso;
	}

	public SalaperCorsiDAO getSalaCorso() {
		return salaCorso;
	}

	public void setSalaCorso(SalaperCorsiDAO sala) {
		this.salaCorso = sala;
	}
	
	
	public int getIdSalaperCorsi() {
		return idSalaperCorsi;
	}

	public void setIdSalaperCorsi(int idSalaperCorsi) {
		this.idSalaperCorsi = idSalaperCorsi;
	}

	public ArrayList<GiornoDAO> getGiorni() {
		return giorni;
	}

	public void setGiorni(ArrayList<GiornoDAO> giorni) {
		this.giorni = giorni;
	}
	
	
}
