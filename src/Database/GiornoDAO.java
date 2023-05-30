package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GiornoDAO {
	String nomeGiorno;
	String orarioAperturaCentro;
	String orarioChiusuraCentro;
	ArrayList<CorsoDAO> corsi;
	
	public GiornoDAO(String nomeGiorno) {
		this.nomeGiorno=nomeGiorno;
		this.corsi=new ArrayList<CorsoDAO>();
		caricaDaDB();
	}

	public GiornoDAO() {
		super();
		this.corsi=new ArrayList<CorsoDAO>();
	}
	
	public void caricaDaDB() {
		
		String query="SELECT * FROM Giorno WHERE nomeGiorno='"+this.nomeGiorno+"';";
		
		try {
		
			ResultSet rs=DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				this.setOrarioAperturaCentro(rs.getString("orarioAperturaCentro"));
				this.setOrarioChiusuraCentro(rs.getString("orarioChiusuraCentro"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void caricaCorsiGiornoDaDB() {
		
		corsi = new ArrayList<CorsoDAO>();
		
		
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
				corso.setIdSalaperCorsi(rs.getInt("SalaperCorsi_idSalaperCorsi"));
				
			
				
				//chiamata a cascata
				//corso.caricaGiorniCorsodaDB();
				this.corsi.add(corso);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public int salvaInDB(String nomeGiorno,String orarioAperturaCentro, String orarioChiusuraCentro) {
		int ret=0;
		
		String query="INSERT INTO Giorno(nomeGiorno, orarioAperturaCentro, orarioChiusuraCentro) VALUES (\'"+nomeGiorno+"\',"+"\'"+orarioAperturaCentro+"\','"+orarioChiusuraCentro+"\');";
	
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
	
	
	//Setter e Getter
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
