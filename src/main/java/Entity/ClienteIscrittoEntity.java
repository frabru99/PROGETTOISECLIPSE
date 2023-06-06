package Entity;

import java.sql.Date;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;
import Database.ClienteIscrittoDAO;
import Database.CorsoDAO;
import Database.PrenotazioneDAO;

public class ClienteIscrittoEntity {
	
	private String codiceCliente;
	private String nome;
	private String cognome;
	private String email;
	private int idAbbonamentoMensile=-1;	
	private int idAbbonamentoAnnuale=-1;
	private AbbonamentoMensileEntity abbonamentoMensile;
	private AbbonamentoAnnualeEntity abbonamentoAnnuale;
	
	//Costruttore vuoto
	public ClienteIscrittoEntity() {
		
		super();
		
	}
	
	//Costruttore passando oggetto DAO
	public ClienteIscrittoEntity(ClienteIscrittoDAO	cdao) {
		
		this.codiceCliente = cdao.getCodiceCliente();
		this.nome= cdao.getNome();
		this.cognome= cdao.getCognome();
		this.email= cdao.getEmail();
		this.idAbbonamentoAnnuale= cdao.getIdAbbonamentoAnnuale();
		this.idAbbonamentoMensile= cdao.getIdAbbonamentoMensile();
		
		cdao.caricaAbbonamentoClienteIscrittoDaDB();
		
		//Carica abbonamento (cdao)
		caricaAbbonamento(cdao);
				
	}
	
	//Costruttore passando la PK
	public ClienteIscrittoEntity(String codiceCliente) {
		
		//Creo un DAO con costruttore PK
		ClienteIscrittoDAO cdao = new ClienteIscrittoDAO(codiceCliente);
		
		this.codiceCliente = cdao.getCodiceCliente();
		this.nome= cdao.getNome();
		this.cognome= cdao.getCognome();
		this.email= cdao.getEmail();
		this.idAbbonamentoAnnuale= cdao.getIdAbbonamentoAnnuale();
		this.idAbbonamentoMensile= cdao.getIdAbbonamentoMensile();
		
		cdao.caricaAbbonamentoClienteIscrittoDaDB();
		
		//Carica abbonamento (cdao)
		caricaAbbonamento(cdao);
		
	}
	
	//Metodo carica abbonamento
	public void caricaAbbonamento(ClienteIscrittoDAO cdao) {
			
			AbbonamentoAnnualeEntity abbann = new AbbonamentoAnnualeEntity(cdao.getAbbonamentoAnnuale());
			this.abbonamentoAnnuale=abbann;
			AbbonamentoMensileEntity abbmen = new AbbonamentoMensileEntity(cdao.getAbbonamentoMensile());
			this.abbonamentoMensile=abbmen;
	}
	
	//Metodo scrivi su db
	public int scriviSuDb(String nome, String cognome, String email){
		int ret=0;
		
		ClienteIscrittoDAO corso  = new ClienteIscrittoDAO();
		//provo a scrivere sul DB
		
		Integer maxcod = Integer.parseInt(corso.prelevaIdmassimo().split("_")[1])+1;
		
		String codiceCliente = "Cliente_"+maxcod;
		
		ret = corso.salvaInDB(codiceCliente, nome, cognome, email);
		
		if(ret!=-1) {	
			this.setCodiceCliente(codiceCliente);
			this.setNome(nome);
			this.setCognome(cognome);
			this.setEmail(email);
			this.setIdAbbonamentoAnnuale(-1);
			this.setIdAbbonamentoMensile(-1);
			this.setAbbonamentoAnnuale(null);
			this.setAbbonamentoMensile(null);
		}
		
		return ret;
	}

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

	public AbbonamentoMensileEntity getAbbonamentoMensile() {
		return abbonamentoMensile;
	}

	public void setAbbonamentoMensile(AbbonamentoMensileEntity abbonamentoMensile) {
		this.abbonamentoMensile = abbonamentoMensile;
	}

	public AbbonamentoAnnualeEntity getAbbonamentoAnnuale() {
		return abbonamentoAnnuale;
	}

	public void setAbbonamentoAnnuale(AbbonamentoAnnualeEntity abbonamentoAnnuale) {
		this.abbonamentoAnnuale = abbonamentoAnnuale;
	}

	@Override
	public String toString() {
		return "ClienteIscrittoEntity [codiceCliente=" + codiceCliente + ", nome=" + nome + ", cognome=" + cognome
				+ ", email=" + email + ", abbonamentoMensile=" + abbonamentoMensile + ", abbonamentoAnnuale="
				+ abbonamentoAnnuale + "]";
	}
	
	
	
	public int controllerScriviPrenotazioneSuDB(int idCorso) {
		
		PrenotazioneDAO pdao = new PrenotazioneDAO();
		
		int idpren = pdao.prelevaIdMassimo()+1;
		Date dataod = new java.sql.Date(System.currentTimeMillis());
		String data= dataod.toString();
		
		
		int val = pdao.scrivisuDB(idpren, data, this.codiceCliente, this.email, idCorso);
	
		
		
		
		return val;
		
		
	}
	
	
	public int checkAbbonamento() {
		
		if (this.idAbbonamentoAnnuale != 0) {
			return 1;
		} else if (this.idAbbonamentoMensile!= 0) {
			return 2;
		}
		
		return 0;
	}
	
	
}
