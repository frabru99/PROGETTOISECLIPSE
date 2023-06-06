package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CorsoDAO {
	
	private int codiceCorso;
	private String nomeCorso;
	private String istruttore;
	private String oraInizio;
	private String durataCorso;
	private int postiDisponibili;
	private SalaperCorsiDAO salaCorso;
	private int idSalaperCorsi;
	private ArrayList<GiornoDAO> giorni;
	
	public CorsoDAO(int codiceCorso) {
		this.codiceCorso=codiceCorso;
		this.giorni=new ArrayList<GiornoDAO>();
		caricaDaDB();
	}

	public CorsoDAO() {
		super();
		this.giorni=new ArrayList<GiornoDAO>();
	}
	
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
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void caricaGiorniCorsodaDB() {
		
		String query=new String("SELECT * FROM Giorno WHERE NomeGiorno IN (SELECT Giorno_nomeGiorno FROM Corso_has_Giorno WHERE Corso_codiceCorso=\'"+this.codiceCorso+"')");
		
		try {
			
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			while(rs.next()) {
				
				GiornoDAO giorno=new GiornoDAO();
				giorno.setNomeGiorno(rs.getString("nomeGiorno"));
				giorno.setOrarioAperturaCentro(rs.getString("orarioAperturaCentro"));
				giorno.setOrarioChiusuraCentro(rs.getString("orarioChiusuraCentro"));
				
				//chiamata ricorsiva
				giorno.caricaCorsiGiornoDaDB();
				this.giorni.add(giorno);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		
	}
	
	public void caricaSalaperCorsiCorsodaDB() {
		
		salaCorso = new SalaperCorsiDAO();
		
		
		String query = new String ("SELECT * FROM SalaperCorsi WHERE idSalaperCorsi = "+this.idSalaperCorsi)+";";
		
		try {
			
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			
			
			if (rs.next()) {
				
				SalaperCorsiDAO sala = new SalaperCorsiDAO();
				sala.setCapienza(rs.getInt("capienza"));
				sala.setIdSalaCorsi(rs.getInt("idSalaperCorsi"));
				
				this.salaCorso = sala;
				
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		
	}
	
	
	public int salvaInDB(int codiceCorso,String nome, String istruttore, String oraInizio, String durataCorso, int postiDisponibili,int idSalaperCorsi) {
		
		int ret=0;
		
		String query="INSERT INTO Corso(codiceCorso, nomeCorso, istruttore, oraInizio, durataCorso, postiDisponibili ,SalaperCorsi_idSalaperCorsi) VALUES (\'"+codiceCorso+"\',"+"\'"+nome+"\','"+istruttore+"\','"+oraInizio+"\','"+durataCorso+"\','"+postiDisponibili+"\','"+idSalaperCorsi+"')";
		
		System.out.println(query);

		try {
			
			ret=DBConnectionManager.updateQuery(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			ret=-1;
			e.printStackTrace();
		}
		
		return ret;
		
	}
	
	public int prelevaIdMassimo() {
		
		//Inizializziamo il massimo a -1. Se non ci sono prenotazioni si parte con l'id 0
		int max =-1;
		
		String query = "SELECT codiceCorso FROM Corso WHERE codiceCorso >= ALL(SELECT codiceCorso FROM Corso)";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if (rs.next()) {
				max=rs.getInt("codiceCorso");
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			
			System.err.println("Non ci sono id"+ e.getMessage());
			
		}
		
		return max;
		
		
	}
	
	public void updatePosti() {
		
        String query = "UPDATE Corso SET postiDisponibili = postiDisponibili - 1 WHERE codiceCorso = "+this.codiceCorso;
        
        
        
        try {
            
            DBConnectionManager.updateQuery(query);
            
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
	}
	
	
	public int checkCorsosuDB(int codCorso) {
		
        String query = "SELECT * FROM Corso WHERE codiceCorso = "+codCorso+";";
        
        try {
            
            ResultSet rs=DBConnectionManager.selectQuery(query);
            
            if(rs.next()) {
                
               return 1;
                
            } else {
            	return -1;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        }
       
		
		
	}
	
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
