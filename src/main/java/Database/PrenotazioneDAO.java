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
	private int codiceCorso;
	private String emailCliente;
	private ClienteIscrittoDAO cliente;
	private CorsoDAO corso;
	
	
	public PrenotazioneDAO(int idPrenotazione) {
		
		this.idPrenotazione = idPrenotazione;
		caricaDaDB();
		
	}
	
	public void caricaDaDB() {
		
		String query = "SELECT * FROM Prenotazione WHERE idPrenotazione='"+this.idPrenotazione+"';";	
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if (rs.next()) {
				
				this.setIdPrenotazione(rs.getInt("idPrenotazione"));
				this.setDataPrenotazione(rs.getString("dataPrenotazione"));
				this.setCodiceCliente(rs.getString("ClienteIscritto_codiceCliente"));
				this.setCodiceCorso(rs.getInt("Corso_codiceCorso"));
				this.setEmailCliente(rs.getString("ClienteIscritto_email"));
				
			}
			
		}catch(SQLException| ClassNotFoundException e) {
			
			System.err.println("Non trovato" + e.getMessage());
			
		}
		
	}
	
	
	public PrenotazioneDAO() {
		super();
	}
	
	
	public void caricaCorsoDaDB() {
		
		String query = "SELECT * FROM Corso WHERE codiceCorso =" + this.codiceCorso;
		
		
		this.corso = new CorsoDAO();
		
		try {
			
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			
			if(rs.next()) {
				CorsoDAO corso = new CorsoDAO();
				
				corso.setCodiceCorso(rs.getInt("codiceCorso"));
				corso.setDurataCorso(rs.getString("durataCorso"));
				corso.setIdSalaperCorsi(rs.getInt("SalaperCorsi_idSalaperCorsi"));
				corso.setNomeCorso(rs.getString("nomeCorso"));
				corso.setOraInizio(rs.getString("oraInizio"));
				corso.setIstruttore(rs.getString("istruttore"));
				
				this.corso=corso;
				
			}
			
			
			
			
		} catch(SQLException | ClassNotFoundException e) {
			System.err.println("Errore nella query!");
			
		}
		
	}
	
	
	public void caricaClienteDaDB() {
		
		String query = "SELECT * FROM ClienteIscritto WHERE codiceCliente = '" + this.codiceCliente+"';";
		
		
		this.cliente = new ClienteIscrittoDAO();
		
		try {
			
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			
			if(rs.next()) {
				ClienteIscrittoDAO cl = new ClienteIscrittoDAO();
				
				cl.setCodiceCliente(rs.getString("codiceCliente"));
				cl.setNome(rs.getString("nome"));
				cl.setCognome(rs.getString("cognome"));
				cl.setEmail(rs.getString("email"));
				cl.setIdAbbonamentoAnnuale(rs.getInt("idAbbAnnuale"));
				cl.setIdAbbonamentoMensile(rs.getInt("idAbbMensile"));
				
			
				this.cliente=cl;
				
			}
			
			
			
		} catch(SQLException | ClassNotFoundException e) {
			System.err.println("Errore nella query!" + e.getMessage());
			
		}
		
	}
	
	public int prelevaIdMassimo() {
		
		//Inizializziamo il massimo a -1. Se non ci sono prenotazioni si parte con l'id 0
		int max =-1;
		
		String query = "SELECT idPrenotazione FROM Prenotazione WHERE idPrenotazione >= ALL(SELECT idPrenotazione FROM Prenotazione)";
		
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
	
	public int scrivisuDB(int idPrenotazione, String dataPrenotazione, String codiceCliente, String email, int codiceCorso) {
		int ret=0;
		
		//qui magari una query con if !
		
		String search = "SELECT * FROM Prenotazione WHERE ClienteIscritto_codiceCliente = \'"+codiceCliente+"' AND Corso_codiceCorso= \'"+codiceCorso+"';";
		
		
		try {
			
		ResultSet set = DBConnectionManager.selectQuery(search);
		
		
		if(!set.next()) { //FARE UN CHECK
		
        String query = "INSERT INTO Prenotazione (idPrenotazione, dataPrenotazione, ClienteIscritto_codiceCliente, ClienteIscritto_email, Corso_codiceCorso) VALUES (\'"+idPrenotazione+"\',"+"\'"+dataPrenotazione+"\','"+codiceCliente+"\','"+email+"\','"+codiceCorso+"');";
        
        
            
         ret =  DBConnectionManager.updateQuery(query);
         
		} else {
			
			ret =-1;
			System.err.println("Prenotazione già presente");
		}
            
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ret = -1;
        }
        
		
        
        return ret;
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

	public int getCodiceCorso() {
		return codiceCorso;
	}

	public void setCodiceCorso(int codiceCorso) {
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
	
	
	
	
	

