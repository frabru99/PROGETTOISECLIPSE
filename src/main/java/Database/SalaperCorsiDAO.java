package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaperCorsiDAO {
	
	//Data members
	private int idSalaCorsi;
	private int capienza;
	
	//Costruttore
	public SalaperCorsiDAO(int idSala) {
		
		this.idSalaCorsi = idSala;		
		caricaDaDB();
		
	}
	
	public SalaperCorsiDAO() {
		super();
	}
	
	//Metodi
	
	public void caricaDaDB() {
		
		String query = "SELECT * FROM SalaperCorsi WHERE idSalaperCorsi='"+this.idSalaCorsi+"';";
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) { //se ho un risultato
				
				//mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
				this.setIdSalaCorsi(rs.getInt("idSalaperCorsi"));
				this.setCapienza(rs.getInt("capienza"));		
				
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public int scriviSala(int idSalaCorsi, int capienza) {
		int ret=0;
		
        String query = "INSERT INTO SalaperCorsi (idSalaperCorsi, capienza) VALUES (\'"+idSalaCorsi+"\',"+"\'"+capienza+"\');";
        try {
            
         ret =  DBConnectionManager.updateQuery(query);
            
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
	

//Setters e getters

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
