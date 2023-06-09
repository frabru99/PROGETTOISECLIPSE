package Entity;

import java.sql.Date;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;
import Database.ClienteIscrittoDAO;
import Database.CorsoDAO;
import Database.PrenotazioneDAO;

/**
 * Classe del package Entity che rappresenta l'oggetto del mondo reale definendone il comportamento e l'implementazione vera e propria dei metodi
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class ClienteIscrittoEntity {
	
	//Variabili membro.
	private String codiceCliente;
	private String nome;
	private String cognome;
	private String email;
	private int idAbbonamentoMensile=-1;	
	private int idAbbonamentoAnnuale=-1;
	private AbbonamentoMensileEntity abbonamentoMensile;
	private AbbonamentoAnnualeEntity abbonamentoAnnuale;
	
	
	/**
	 * Costruttore vuoto
	 */
	public ClienteIscrittoEntity() {
		
		super();
		
	}
	
	/**
	 * Costruttore per caricamento da un'istanza della classe DAO
	 * @param cdao
	 */
	public ClienteIscrittoEntity(ClienteIscrittoDAO	cdao) {
		
		this.codiceCliente = cdao.getCodiceCliente();
		this.nome= cdao.getNome();
		this.cognome= cdao.getCognome();
		this.email= cdao.getEmail();
		this.idAbbonamentoAnnuale= cdao.getIdAbbonamentoAnnuale();
		this.idAbbonamentoMensile= cdao.getIdAbbonamentoMensile();
		
		//Carico anche gli eventuali abbonamenti
		cdao.caricaAbbonamentoClienteIscrittoDaDB();
		caricaAbbonamento(cdao);
				
	}
	
	
	/**
	 * Costruttore per caricamento da classe DAO attraverso la PK
	 * @param codiceCliente
	 */
	public ClienteIscrittoEntity(String codiceCliente) {
		
		ClienteIscrittoDAO cdao = new ClienteIscrittoDAO(codiceCliente);
		this.codiceCliente = cdao.getCodiceCliente();
		this.nome= cdao.getNome();
		this.cognome= cdao.getCognome();
		this.email= cdao.getEmail();
		this.idAbbonamentoAnnuale= cdao.getIdAbbonamentoAnnuale();
		this.idAbbonamentoMensile= cdao.getIdAbbonamentoMensile();
		
		//Carico anche gli eventuali abbonamenti
		cdao.caricaAbbonamentoClienteIscrittoDaDB();
		caricaAbbonamento(cdao);
		
	}
	
	
	/**
	 * Funzione di loading degli abbonamenti del cliente SE ESISTONO.
	 * @param cdao
	 */
	public void caricaAbbonamento(ClienteIscrittoDAO cdao) {
			
			AbbonamentoAnnualeEntity abbann = new AbbonamentoAnnualeEntity(cdao.getAbbonamentoAnnuale());
			this.abbonamentoAnnuale=abbann;
			AbbonamentoMensileEntity abbmen = new AbbonamentoMensileEntity(cdao.getAbbonamentoMensile());
			this.abbonamentoMensile=abbmen;
			
	}
	
	
	/**
	 * Metodo per rendere persistente il salvataggio di un cliente 
	 * @param nome
	 * @param cognome
	 * @param email
	 * @return esito
	 */
	public int scriviSuDB(String nome, String cognome, String email){
		
		//Variabile per il risultato della query
		int ret=0;
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		ClienteIscrittoDAO cliente  = new ClienteIscrittoDAO();
		
		//Chiamo la funzione prelevaIdMassimo del DAO a cui aggiungo 1 per ottenere il nuovo codiceCliente
		Integer maxcod = Integer.parseInt(cliente.prelevaIdmassimo().split("_")[1])+1;
		String codiceCliente = "Cliente_"+maxcod;
		
		ret = cliente.salvaSuDB(codiceCliente, nome, cognome, email);
		
		//Se la richiesta al DAO va a buon fine
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
	
	
	/**
	 * Metodo chiamato dal controller in seguito ad una richiesta di creazione di prenotazione per un corso
	 * @param idCorso
	 * @return esito
	 */
	public int controllerScriviPrenotazioneSuDB(int idCorso) {
		
		//Inizializzo un oggetto DAO per accedere ai suoi metodi
		PrenotazioneDAO pdao = new PrenotazioneDAO();
		
		//Setto l'id chiamando il metodo prelevaIdMassimo
		int idpren = pdao.prelevaIdMassimo()+1;
		
		//Setto la data alla data odierna, e chiamo il metodo toString
		Date dataod = new java.sql.Date(System.currentTimeMillis());
		String data= dataod.toString();
		
		//Chiamo il metodo dell'oggetto DAO per scrivere sul DB
		int val = pdao.salvaSuDB(idpren, data, this.codiceCliente, this.email, idCorso);
	
		return val;
		
	}
	
	
	/**
	 * Metodo che aggiorna le informazioni sull'abbonamento mensile del cliente dopo una sottoscrizione
	 * @param nomeMese
	 * @param dataSottoscrizione
	 * @param dataScadenza
	 * @return esito
	 */
	public int sottoscriviAbbonamentoMensile(String nomeMese, String dataSottoscrizione, String dataScadenza) {
	
		//Salvo sul database l'abbonamento
		int ret=this.abbonamentoMensile.scriviSuDB(nomeMese, dataSottoscrizione, dataScadenza);
		
		//Se il salvataggio va a buon fine, aggiorno le informazioni del cliente
		if (ret!=-1) {
			
			ClienteIscrittoDAO cliente=new ClienteIscrittoDAO(this.codiceCliente);
			System.out.println(this.abbonamentoMensile.getIdAbbonamentoMensile());
			ret=cliente.updateAbbonamentoMensileSuDB(this.abbonamentoMensile.getIdAbbonamentoMensile());
			
			
		}
		
		return ret;
	}
	
	
	/**
	 * Metodo che aggiorna le informazioni sull'abbonamento annuale del cliente dopo una sottoscrizione
	 * @param nomeMese
	 * @param dataSottoscrizione
	 * @param dataScadenza
	 * @return esito
	 */
	public int sottoscriviAbbonamentoAnnuale(String dataSottoscrizione, String dataScadenza) {
		
		//Salvo sul database l'abbonamento
		int ret=this.abbonamentoAnnuale.scriviSuDB(dataSottoscrizione,dataScadenza);
		
		//Se il salvataggio va a buon fine, aggiorno le informazioni del cliente
		if (ret!=-1) {
			
			ClienteIscrittoDAO cliente=new ClienteIscrittoDAO(this.codiceCliente);
			ret=cliente.updateAbbonamentoAnnualeSuDB(this.abbonamentoAnnuale.getIdAbbonamentoAnnuale());
			
		}
		
		return ret;
	}
	
	
	/**
	 * Funzionalità DUMMY che permette di settare una data di sospensione e una data di ripresa all'abbonamento annuale
	 * @param sospensione
	 * @param ripresa
	 * @return esito
	 */
	public int sospendiAbbonamentoAnnuale(String sospensione, String ripresa) { 
		
		System.out.println("[CLIENTE-ISCRITTO-ENTITY] Settata data di sospensione dell'abbonamento a "+sospensione+" e ripresa a "+ripresa);
		return 0;
		
	}
	
	
	/**
	 * Funzione di utility che controlla se il cliente dispone di un abbonamento mensile o annuale
	 * @return permessi cliente
	 */
	public int AccessoAlCentro() {
		
		if (this.idAbbonamentoAnnuale != 0) {
			
			return 1;
			
		} else if (this.idAbbonamentoMensile!= 0) {
			
			return 2;
			
		}
		
		return 0;
		
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return codiceCliente
	 */
	public String getCodiceCliente() {
		return codiceCliente;
	}

	/**
	 * Setter
	 * @param codiceCliente
	 */
	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	/**
	 * Getter
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Getter
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Setter
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Getter
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter
	 * @return idAbbonamentoMensile
	 */
	public int getIdAbbonamentoMensile() {
		return idAbbonamentoMensile;
	}

	/**
	 * Setter
	 * @param idAbbonamentoMensile
	 */
	public void setIdAbbonamentoMensile(int idAbbonamentoMensile) {
		this.idAbbonamentoMensile = idAbbonamentoMensile;
	}

	/**
	 * Getter
	 * @return idAbbonamentoAnnuale
	 */
	public int getIdAbbonamentoAnnuale() {
		return idAbbonamentoAnnuale;
	}

	/**
	 * Setter
	 * @param idAbbonamentoAnnuale
	 */
	public void setIdAbbonamentoAnnuale(int idAbbonamentoAnnuale) {
		this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
	}

	/**
	 * Getter
	 * @return abbonamentoMensile
	 */
	public AbbonamentoMensileEntity getAbbonamentoMensile() {
		return abbonamentoMensile;
	}

	/**
	 * Setter
	 * @param abbonamentoMensile
	 */
	public void setAbbonamentoMensile(AbbonamentoMensileEntity abbonamentoMensile) {
		this.abbonamentoMensile = abbonamentoMensile;
	}

	/**
	 * Getter
	 * @return abbonamentoAnnuale
	 */
	public AbbonamentoAnnualeEntity getAbbonamentoAnnuale() {
		return abbonamentoAnnuale;
	}

	/**
	 * Setter
	 * @param abbonamentoAnnuale
	 */
	public void setAbbonamentoAnnuale(AbbonamentoAnnualeEntity abbonamentoAnnuale) {
		this.abbonamentoAnnuale = abbonamentoAnnuale;
	}
	
	
	//TO - STRING
	@Override
	public String toString() {
		
		return "ClienteIscrittoEntity [codiceCliente=" + codiceCliente + ", nome=" + nome + ", cognome=" + cognome
				+ ", email=" + email + ", abbonamentoMensile=" + abbonamentoMensile + ", abbonamentoAnnuale="
				+ abbonamentoAnnuale + "]";
		
	}
	
}
