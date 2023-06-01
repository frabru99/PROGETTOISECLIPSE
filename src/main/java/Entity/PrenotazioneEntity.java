package Entity;

import java.sql.Date;

import Database.PrenotazioneDAO;

public class PrenotazioneEntity {

	private int idPrenotazione;
	private String dataPrenotazione;
	private String codiceCliente;
	private int codiceCorso;
	private String emailCliente;
	private ClienteIscrittoEntity cliente;
	private CorsoEntity corso;
	
	//Per creare una prenotazione dall'applicazione
	public PrenotazioneEntity(ClienteIscrittoEntity cl , CorsoEntity cr) throws NullPointerException {

		
		if (cl==null || cr==null) {
			
			throw new NullPointerException();
			
		}
		
	this.cliente=cl;
	this.corso=cr;
	Date dataod = new java.sql.Date(System.currentTimeMillis());
	this.dataPrenotazione = dataod.toString();
	this.codiceCliente = cl.getCodiceCliente();
	this.codiceCorso = cr.getCodiceCorso();
	this.emailCliente = cl.getEmail();
	
	//Setto l'id
	PrenotazioneDAO pdao = new PrenotazioneDAO();
	this.idPrenotazione = (pdao.prelevaIdMassimo()+1);
	
	//Scrivi su db del DAO   
		
	
	scrivisuDB(); //devo scrivere le informazioni della prenotazione nel Database
	
	}
	
	
	
	public PrenotazioneEntity(int idPren) {
		// TODO Auto-generated constructor stub
		
		PrenotazioneDAO pren = new PrenotazioneDAO(idPren);
		
		
		this.idPrenotazione = pren.getIdPrenotazione();
		this.dataPrenotazione = pren.getDataPrenotazione();
		this.codiceCliente = pren.getCodiceCliente();
		this.codiceCorso = pren.getCodiceCorso();
		this.emailCliente = pren.getEmailCliente();
		
		
		//mi devo caricare cliente e corso!
		pren.caricaClienteDaDB();
		caricaCliente(pren);
		
		pren.caricaCorsoDaDB();
		caricaCorso(pren);
		
		
		
	}
	
	public PrenotazioneEntity(PrenotazioneDAO pren) {
		
		this.idPrenotazione = pren.getIdPrenotazione();
		this.dataPrenotazione = pren.getDataPrenotazione();
		this.codiceCliente = pren.getCodiceCliente();
		this.codiceCorso = pren.getCodiceCorso();
		this.emailCliente = pren.getEmailCliente();
		
		//mi devo caricare cliente e corso!
		
		pren.caricaClienteDaDB();
		caricaCliente(pren);
		
		pren.caricaCorsoDaDB();
		caricaCorso(pren);
		
	}
	
	
	public int scrivisuDB() {
		
		PrenotazioneDAO pr = new PrenotazioneDAO();
		
		int ret = pr.scrivisuDB(this.idPrenotazione, this.dataPrenotazione, this.codiceCliente, this.emailCliente, this.codiceCorso);
		
		
		if(ret != -1) {
			System.out.println("Ho scritto la prenotazione!"); //gestione messaggio di conferma o meno!
		} else {
			System.out.println("Non ho scritto!");
		}
		
		
		return ret;
		
		
	}
	
	
	public void caricaCliente(PrenotazioneDAO pren) {
		ClienteIscrittoEntity cl = new ClienteIscrittoEntity(pren.getCliente());
		this.cliente=cl;
		
		
	}
	
	
	public void caricaCorso(PrenotazioneDAO pren ) {
		CorsoEntity cr = new CorsoEntity(pren.getCorso());
		this.corso = cr;
		
	}



	@Override
	public String toString() {
		return "PrenotazioneEntity [idPrenotazione=" + idPrenotazione + ", dataPrenotazione=" + dataPrenotazione
				+ ", codiceCliente=" + codiceCliente + ", codiceCorso=" + codiceCorso + ", emailCliente=" + emailCliente
				+ "]";
	}



	public int getIdPrenotazione() {
		return idPrenotazione;
	}



	public String getDataPrenotazione() {
		return dataPrenotazione;
	}



	public String getCodiceCliente() {
		return codiceCliente;
	}



	public int getCodiceCorso() {
		return codiceCorso;
	}



	public String getEmailCliente() {
		return emailCliente;
	}



	public ClienteIscrittoEntity getCliente() {
		return cliente;
	}



	public CorsoEntity getCorso() {
		return corso;
	}
	
	
	
	
	
}
