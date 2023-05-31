package Database;

import java.lang.NullPointerException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.ClienteIscrittoEntity;
import Entity.CorsoEntity;

public class PrenotazioneDAO {

	private int idPrenotazione;
	private String dataPrenotazione;
	private String codiceCliente;
	private String codiceCorso;
	private String emailCliente;
	private ClienteIscrittoDAO cliente;
	private CorsoDAO corso;
	
	
	public PrenotazioneDAO(int idPrenotazione) {
		
		this.idPrenotazione = idPrenotazione;
		caricaDaDB();
		
	}
	
	public void caricaDaDB() {
		
		String query = "SELECT * FROM Prenotazione WHERE idPrenotazione="+this.idPrenotazione;	
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if (rs.next()) {
				
				this.setIdPrenotazione(rs.getInt("idPrenotazione"));
				this.setDataPrenotazione(rs.getString("dataPrenotazione"));
				this.setCodiceCliente(rs.getString("codiceCliente"));
				this.setCodiceCorso(rs.getString("codiceCorso"));
				this.setEmailCliente(rs.getString("emailCliente"));
				
			}
			
		}catch(SQLException| ClassNotFoundException e) {
			
			System.err.println("Non trovato");
			
		}
		
	}
	
	
//	public void caricaCorsoDaDB() {
//		
//		String query = "SELECT * FROM Corso WHERE codiceCorso"
//		
//	}
	
	
	public int prelevaIdMassimo() {
		
		//Inizializziamo il massimo a -1. Se non ci sono prenotazioni si parte con l'id 0
		int max =-1;
		
		String query = "SELECT idPrenotazione FROM Prenotazione WHEERE idPrenotazione >= ALL(SELECT idPrenotazione FROM Prenotazione)";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if (rs.next()) {
				max=rs.getInt("idPrenotazione");
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			
			System.err.println("Non ci sono id");
			
		}
		
		return max;
		
		
	}

	public int getIdPrenotazione() {
		return idPrenotazione;
	}

	public void setIdPrenotazione(int idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}

	public String getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(String dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public String getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getCodiceCorso() {
		return codiceCorso;
	}

	public void setCodiceCorso(String codiceCorso) {
		this.codiceCorso = codiceCorso;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public ClienteIscrittoDAO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIscrittoDAO cliente) {
		this.cliente = cliente;
	}

	public CorsoDAO getCorso() {
		return corso;
	}

	public void setCorso(CorsoDAO corso) {
		this.corso = corso;
	}

	
		
}
	
	
	
	
	

