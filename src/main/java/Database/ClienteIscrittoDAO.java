package Database;

import java.sql.SQLException;
import java.sql.ResultSet;

public class ClienteIscrittoDAO {
	
	//Variabili membro.
	private String codiceCliente;
	private String nome;
	private String cognome;
	private String email;
	private int idAbbonamentoMensile=-1;	//Inizializzato a -1 per controllare se è presente l'abbonamento nel metodo caricaAbbonamento
	private int idAbbonamentoAnnuale=-1;	//Inizializzato a -1 per controllare se è presente l'abbonamento nel metodo caricaAbbonamento
	private AbbonamentoMensileDAO abbonamentoMensile;
	private AbbonamentoAnnualeDAO abbonamentoAnnuale;
	
	
	//Costruttore per caricamento da DB attraverso la PK
	public ClienteIscrittoDAO(String codiceCliente) {
		
		this.codiceCliente=codiceCliente;
		this.abbonamentoAnnuale=new AbbonamentoAnnualeDAO();
		this.abbonamentoMensile=new AbbonamentoMensileDAO();
		caricaDaDB();
		
	}
	
	//Costruttore vuoto per inizializzazione
	public ClienteIscrittoDAO() {
		
		super();
		this.abbonamentoAnnuale=new AbbonamentoAnnualeDAO();
		this.abbonamentoMensile=new AbbonamentoMensileDAO();
		
	}
	
	
	//Funzione di loading degli attributi del DAO attraverso una query di SELECT
	public void caricaDaDB() {
		
		String query="SELECT * FROM ClienteIscritto WHERE codiceCliente='"+this.codiceCliente+"';";
		
			try {
				
				ResultSet rs=DBConnectionManager.selectQuery(query);
				
				if(rs.next()) {
					
					this.setNome(rs.getString("nome"));
					this.setCognome(rs.getString("cognome"));
					this.setEmail(rs.getString("email"));
					this.setIdAbbonamentoAnnuale(rs.getInt("idAbbAnnuale"));
					this.setIdAbbonamentoMensile(rs.getInt("idAbbMensile"));
					
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nel metodo caricaDaDB");
			}
		
	}
	
	
	//Metodo di CREATE del CRUD
	public int salvaInDB(String codiceCliente, String nome, String cognome, String email) {
		
		//Variabile per il risultato della query
		int ret=0;
		
		String query = "INSERT INTO clienteiscritto(codiceCliente,nome,cognome,email) VALUES (\'"+codiceCliente+"\',"+"\'"+nome+"\','"+cognome+"\','"+email+"')";
	
		
		try {
			
			ret=DBConnectionManager.updateQuery(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nella CREATE");
			ret=-1;
	
		}
		
		return ret;
		
	}
	
	
	//Funzione di utility per incrementare automaticamente il counter degli id degli iscritti
	public String prelevaIdmassimo() {
		
		//Inizializzo il massimo a null
		String max=null;
		String query = "SELECT codiceCliente FROM ClienteIscritto WHERE codiceCliente >=ALL(SELECT codiceCliente FROM ClienteIscritto)";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				max=rs.getString("codiceCliente");
				
			}
			
		}catch(ClassNotFoundException | SQLException e) {
			
			System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nel metodo prelevaIdMassimo");
			
		}
		
		return max;
		
	}
	
	
	//Funzione di loading degli abbonamenti del cliente SE ESISTONO.
	public void caricaAbbonamentoClienteIscrittoDaDB() {
		
		String query;
			
		//Controllo se è un abbonato mensile
		//if (this.idAbbonamentoMensile!=-1 ) {
			
			query=new String("SELECT * FROM AbbonamentoMensile WHERE idAbbonamentoMensile= "+this.idAbbonamentoMensile+";");
		
			try {
			
				ResultSet rs = DBConnectionManager.selectQuery(query);
				
				if (rs.next()) {
					
					AbbonamentoMensileDAO dao = new AbbonamentoMensileDAO();
					dao.setIdAbbonamentoMensile(rs.getInt("idAbbonamentoMensile"));
					dao.setPrezzo(rs.getInt("prezzo"));
					dao.setDataScadenza(rs.getString("dataScadenza"));
					dao.setDataSottoscrizione(rs.getString("dataSottoscrizione"));
					dao.setNomeMese(rs.getString("nomeMese"));
					this.abbonamentoMensile =dao;
					
				}
			
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nel metodo caricaAbbonamentoClienteIscrittoDaDB");
			}
		

		//}
		//Controllo se è un abbonato annuale
		//else if (this.idAbbonamentoAnnuale!=-1  && this.idAbbonamentoMensile==-1) {
			
			query=new String("SELECT * FROM AbbonamentoAnnuale WHERE idAbbonamentoAnnuale= "+this.idAbbonamentoAnnuale+" ;");	
		
			try {
				
				ResultSet rs = DBConnectionManager.selectQuery(query);
				
				if (rs.next()) {
					
					AbbonamentoAnnualeDAO dao = new AbbonamentoAnnualeDAO();
					dao.setIdAbbonamentoAnnuale(rs.getInt("idAbbonamentoAnnuale"));
					dao.setPrezzo(rs.getInt("prezzo"));
					dao.setDataScadenza(rs.getString("dataScadenza"));
					dao.setDataSottoscrizione(rs.getString("dataSottoscrizione"));
					dao.setDataSopensione(rs.getString("dataSospensione"));
					dao.setDataRipresa(rs.getString("dataRipresa"));
					this.abbonamentoAnnuale =dao;
				}
			
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("[CLIENTE-ISCRITTO-DAO] Errore nel metodo caricaAbbonamentoClienteIscrittoDaDB");
			}
			
		//} else{
			//sysout ["cliente non abbonato"];
			//}
		
		
			
	}
			
	

	//GETTERS AND SETTERS

	public String getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdAbbonamentoMensile() {
		return idAbbonamentoMensile;
	}

	public void setIdAbbonamentoMensile(int idAbbonamentoMensile) {
		this.idAbbonamentoMensile = idAbbonamentoMensile;
	}

	public int getIdAbbonamentoAnnuale() {
		return idAbbonamentoAnnuale;
	}

	public void setIdAbbonamentoAnnuale(int idAbbonamentoAnnuale) {
		this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
	}

	public AbbonamentoMensileDAO getAbbonamentoMensile() {
		return abbonamentoMensile;
	}

	public void setAbbonamentoMensile(AbbonamentoMensileDAO abbonamentoMensile) {
		this.abbonamentoMensile = abbonamentoMensile;
	}

	public AbbonamentoAnnualeDAO getAbbonamentoAnnuale() {
		return abbonamentoAnnuale;
	}

	public void setAbbonamentoAnnuale(AbbonamentoAnnualeDAO abbonamentoAnnuale) {
		this.abbonamentoAnnuale = abbonamentoAnnuale;
	}

	
}
