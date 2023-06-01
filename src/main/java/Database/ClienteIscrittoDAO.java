package Database;

import java.sql.SQLException;
import java.sql.ResultSet;

public class ClienteIscrittoDAO {
	
	private String  codiceCliente;
	private String nome;
	private String cognome;
	private String email;
	private int idAbbonamentoMensile=-1;	//Inizializzato a -1 per controllare se è presente l'abbonamento nel metodo caricaAbbonamento
	private int idAbbonamentoAnnuale=-1;	//Inizializzato a -1 per controllare se è presente l'abbonamento nel metodo caricaAbbonamento
	private AbbonamentoMensileDAO abbonamentoMensile;
	private AbbonamentoAnnualeDAO abbonamentoAnnuale;
	
	//Costruttore a cui passo la PK
	public ClienteIscrittoDAO(String codiceCliente) {
		
		this.codiceCliente=codiceCliente;
		this.abbonamentoAnnuale=new AbbonamentoAnnualeDAO();
		this.abbonamentoMensile=new AbbonamentoMensileDAO();
		caricaDaDB();
		
	}
	
	//Costruttore senza PK
	public ClienteIscrittoDAO() {
		
		super();
		this.abbonamentoAnnuale=new AbbonamentoAnnualeDAO();
		this.abbonamentoMensile=new AbbonamentoMensileDAO();
		
	}
	
	
	//Per caricare il cliente dal database
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
				// TODO: handle exception
				e.printStackTrace();
			}
		
	}
	
	//Per salvare il cliente nel database
	public int salvaInDB(String codiceCliente, String nome, String cognome, String email) {
		
		String query = "INSERT INTO clienteiscritto(codiceCliente,nome,cognome,email) VALUES (\'"+codiceCliente+"\',"+"\'"+nome+"\','"+cognome+"\','"+email+"')";
		
		int ret=0;
		
		try {
			
			ret=DBConnectionManager.updateQuery(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			ret=-1;
			e.printStackTrace();
		}
		
		return ret;
		
	}
	
	//Carica gli abbonamenti dell'iscritto se ci sono
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
				System.err.println(e.getMessage());
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
				System.err.println(e.getMessage());
			}
			
		}
			
		//}
		//Se non è abbonato
		/*else {
			
			System.out.println("Cliente non abbonato");
			return;
			
		}
		
	}*/
	
	
	//Getters and setters

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
