package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaperCorsiDAO {
	
	//Variabili membro
	private int idSalaCorsi;
	private int capienza;
	
	
	//Costruttore per caricamento da DB attraverso la PK
	public SalaperCorsiDAO(int idSala) {
		
		this.idSalaCorsi = idSala;		
		caricaDaDB();
		
	}
	
	
	//Costruttore vuoto per inizializzazione
	public SalaperCorsiDAO() {
		super();
	}
	
	
	//Funzione di loading degli attributi del DAO attraverso una query di SELECT
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
	
	
	//Metodo di CREATE del CRUD
	public int scriviSala(int idSalaCorsi, int capienza) {
		
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
	
	
//	public void caricaCorsi() {
//		String query = "SELECT * FROM CORSI WHERE SalaperCorsi_idSalaperCorsi= \'"+this.idSalaCorsi+"');";
//	
//		try {
//			
//			ResultSet rs = DBConnectionManager.selectQuery(query);
//			
//			while(rs.next()) {
//				
//				CorsoDAO corso = new CorsoDAO();
//				corso.setCodiceCorso(rs.getInt("codiceCorso"));
//				corso.setNomeCorso(rs.getString("nomeCorso"));
//				corso.setIstruttore(rs.getString("istruttore"));
//				corso.setOraInizio(rs.getString("oraInizio"));
//				corso.setDurataCorso(rs.getString("durataCorso"));
//				corso.setIdSalaperCorsi(rs.getInt("SalaperCorsi_idSalaperCorsi"));
//														
//			}
//			
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//	
//
//	}
	

	//GETTERS AND SETTERS

	public int getIdSalaCorsi() {
		return idSalaCorsi;
	}

	public void setIdSalaCorsi(int idSalaCorsi) {
		this.idSalaCorsi = idSalaCorsi;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza =capienza;
	}
	
	
}
