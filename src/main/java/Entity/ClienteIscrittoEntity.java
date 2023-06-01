package Entity;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;
import Database.ClienteIscrittoDAO;

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
				
		if (this.idAbbonamentoAnnuale !=-1) {
			
			this.abbonamentoAnnuale.setPrezzo(cdao.getAbbonamentoAnnuale().getPrezzo());
			this.abbonamentoAnnuale.setDataSottoscrizione(cdao.getAbbonamentoAnnuale().getDataSottoscrizione());
			this.abbonamentoAnnuale.setDataScadenza(cdao.getAbbonamentoAnnuale().getDataScadenza());
			this.abbonamentoAnnuale.setDataRipresa(cdao.getAbbonamentoAnnuale().getDataRipresa());		
			
		}
		else if (this.idAbbonamentoMensile!=-1) {
			
			this.abbonamentoMensile.setPrezzo(cdao.getAbbonamentoMensile().getPrezzo());
			this.abbonamentoMensile.setDataSottoscrizione(cdao.getAbbonamentoMensile().getDataSottoscrizione());
			this.abbonamentoMensile.setDataScadenza(cdao.getAbbonamentoMensile().getDataScadenza());
			this.abbonamentoMensile.setNomeMese(cdao.getAbbonamentoMensile().getNomeMese());		
			
		}
		else {
			System.out.println("Cliente non abbonato");
		}
		
	}
	
	//Metodo scrivi su db
	public void scriviSuDb(String ok){
		
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
	
	
	
	
	
	
	
	
}
