package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GiornoDAO {
	
	//Variabili membro
	String nomeGiorno;
	String orarioAperturaCentro;
	String orarioChiusuraCentro;
	ArrayList<CorsoDAO> corsi;
	
	
	//Costruttore per caricamento da DB attraverso la PK
	public GiornoDAO(String nomeGiorno) {
		
		this.nomeGiorno=nomeGiorno;
		this.corsi=new ArrayList<CorsoDAO>();
		caricaDaDB();
		
	}

	
	//Costruttore vuoto per inizializzazione
	public GiornoDAO() {
		
		super();
		this.corsi=new ArrayList<CorsoDAO>();
		
	}
	
	
	//Funzione di loading degli attributi del DAO attraverso una query di SELECT
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
	
	
	//Funzione di loading dell'array CorsoDAO
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
	
	
	//Metodo di CREATE del CRUD
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
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	//GETTERS AND SETTERS
	public String getNomeGiorno() {
		return nomeGiorno;
	}

	public void setNomeGiorno(String nomeGiorno) {
		this.nomeGiorno = nomeGiorno;
	}

	public String getOrarioAperturaCentro() {
		return orarioAperturaCentro;
	}

	public void setOrarioAperturaCentro(String orarioAperturaCentro) {
		this.orarioAperturaCentro = orarioAperturaCentro;
	}

	public String getOrarioChiusuraCentro() {
		return orarioChiusuraCentro;
	}

	public void setOrarioChiusuraCentro(String orarioChiusuraCentro) {
		this.orarioChiusuraCentro = orarioChiusuraCentro;
	}

	public ArrayList<CorsoDAO> getCorsi() {
		return corsi;
	}

	public void setCorsi(ArrayList<CorsoDAO> corsi) {
		this.corsi = corsi;
	}
	
	
}
