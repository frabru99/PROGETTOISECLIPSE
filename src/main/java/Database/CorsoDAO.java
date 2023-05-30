package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class CorsoDAO {
	
	private int codiceCorso;
	private String nomeCorso;
	private String istruttore;
	private String oraInizio;
	private String durataCorso;
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
				sala.setNumeroPosti(rs.getInt("numeroPosti"));
				sala.setIdSalaCorsi(rs.getInt("idSalaperCorsi"));
				
				this.salaCorso = sala;
				
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		
	}
	
	
	public int salvaInDB(int codiceCorso,String nome, String istruttore, String oraInizio, String durataCorso, int idSalaperCorsi) {
		
		int ret=0;
		
		String query="INSERT INTO Corso(codiceCorso, nomeCorso, istruttore, oraInizio, durataCorso, SalaperCorsi_idSalaperCorsi) VALUES (\'"+codiceCorso+"\',"+"\'"+nomeCorso+"\','"+istruttore+"\','"+oraInizio+"\','"+durataCorso+"\','"+idSalaperCorsi+"')";
		
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
