package Database;

import java.sql.Date;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbbonamentoAnnualeDAO {
	
	//Variabili membro. Data sospensione e ripresa inizializzate a null poichè opzionali.
	private int idAbbonamentoAnnuale;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String dataSopensione=null;
	private String dataRipresa=null;
	
	//Costruttore per caricamento da DB attraverso la PK
	public AbbonamentoAnnualeDAO(int id) {
		
		this.idAbbonamentoAnnuale = id;
		caricaDaDB(id);
		
	}
	
	//Costruttore vuoto per inizializzazione
	public AbbonamentoAnnualeDAO() {
		
		super();
		
	}
	

	//Funzione di loading degli attributi del DAO attraverso una query di SELECT
	public void caricaDaDB(int id) {
		
		String query = "SELECT * FROM AbbonamentoAnnuale WHERE idAbbonamentoAnnuale = '"+this.idAbbonamentoAnnuale+"';";
		
		try {
			
			ResultSet res = DBConnectionManager.selectQuery(query);
			
			if(res.next()) {
				
				this.idAbbonamentoAnnuale = res.getInt("idAbbonamentoAnnuale");
				this.dataSottoscrizione = res.getString("dataSottoscrizione");
				this.dataScadenza=res.getString("dataScadenza");
				this.prezzo = res.getInt("prezzo");
				this.dataSopensione = res.getString("dataSospensione");
				this.dataRipresa = res.getString("dataRipresa");
				
			}
			
		} catch(SQLException e) {
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Errore nel metodo caricaDaDB");
		} catch(ClassNotFoundException e) {
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Classe non trovata nel metodo caricaDaDB");		
		}
		
	}
	
	
	//Funzione di utility per incrementare automaticamente il counter degli id degli abbonamenti
	public int prelevaIdmassimo() {
		
		//Inizializzo il massimo a -1
		int max=-1;
		//Query di select innestata per prelevare l'attuale id maggiore
		String query = "SELECT idAbbonamentoAnnuale FROM AbbonamentoAnnuale WHERE idAbbonamentoAnnuale >=ALL(SELECT idAbbonamentoAnnuale FROM AbbonamentoAnnuale)";
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				max=rs.getInt("idAbbonamentoAnnuale");
				
			}
			
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Errore nel metodo prelevaIdMassimo");
		}
		
		return max;
		
	}
	
	
	//Metodo di CREATE del CRUD
	public int scriviAbbonamentoAnnuale(int id, String dataSottoscrizione, String dataScadenza, int prezzo){
		
		//Variabile per il risultato della query
		int ret =0;

		String query = "INSERT INTO AbbonamentoAnnuale(idAbbonamentoAnnuale, dataSottoscrizione, dataScadenza, prezzo)  VALUES( \'"+id+"\',"+"\'"+dataSottoscrizione+"\','"+dataScadenza+"\','"+prezzo+"\');";
	

		try {
			
			ret = DBConnectionManager.updateQuery(query);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Errore nella CREATE");
			ret = -1;	//In caso di esito negativo della query
			
		}
		
		return ret;
	
	}
	
	
	//Funzionalità che permette di sospendere un abbonamento annuale
	public int sospendiAbbonamentoAnnuale(int idAbbAnnuale, java.util.Date dataSospensione, java.util.Date dataRipresa) {
	
		//Variabile per il risultato della query
		int ret=0;
		
		
		//HARDCODED
		int anno =(dataSospensione.getYear())-100;
	
		String datasosp = "20"+anno+"-"+dataSospensione.getMonth()+"-"+dataSospensione.getDay(); //scusaci ingegner de luca.......
		
		String datarip = "20"+anno+"-"+dataRipresa.getMonth()+"-"+dataRipresa.getDay(); //scusaci ingegner de luca.......
		//HARDCODED
		
		
		try {
			
			//Query
            String upd = "UPDATE AbbonamentoAnnuale SET dataSospensione = '"+datasosp+"', dataRipresa = '"+datarip+"' WHERE idAbbonamentoAnnuale = "+idAbbAnnuale+";";
     			
 			ret=DBConnectionManager.updateQuery(upd);
         
		}catch(ClassNotFoundException | SQLException e) {
			
			System.out.println("[ABBONAMENTO-ANNUALE-DAO] Errore nel metodo sospendiAbbonamentoAnnuale");
			ret = -1;	//In caso di esito negativo della query
			
		}
		
		return ret;
		
	}
	
	
	//GETTERS AND SETTERS
	
	public int getIdAbbonamentoAnnuale() {
		return idAbbonamentoAnnuale;
	}

	public void setIdAbbonamentoAnnuale(int idAbbonamentoAnnuale) {
		this.idAbbonamentoAnnuale = idAbbonamentoAnnuale;
	}

	public String getDataSottoscrizione() {
		return dataSottoscrizione;
	}

	public void setDataSottoscrizione(String dataSottoscrizione) {
		this.dataSottoscrizione = dataSottoscrizione;
	}

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getDataSopensione() {
		return dataSopensione;
	}

	public void setDataSopensione(String dataSopensione) {
		this.dataSopensione = dataSopensione;
	}

	public String getDataRipresa() {
		return dataRipresa;
	}

	public void setDataRipresa(String dataRipresa) {
		this.dataRipresa = dataRipresa;
	}
	
	//TO - STRING

	@Override
	public String toString() {
		
		return "AbbonamentoAnnualeDAO [idAbbonamentoAnnuale=" + idAbbonamentoAnnuale + ", dataSottoscrizione="
				+ dataSottoscrizione + ", dataScadenza=" + dataScadenza + ", prezzo=" + prezzo + ", dataSopensione="
				+ dataSopensione + ", dataRipresa=" + dataRipresa + "]";
		
	}
		
		
}
