package Database;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AbbonamentoMensileDAO {
	
	private int idAbbonamentoMensile;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String nomeMese;
	
	//costruttore con funzione di carica da DB
	public AbbonamentoMensileDAO(int id) {
		
		this.idAbbonamentoMensile = id;
		caricaDaDB(id); //carico dal database un certo abbonamento con quella chiave primaria!
		
	}
	
	public AbbonamentoMensileDAO() {
		super();
	}
	
	//fcunzione carica da DB!
	public void caricaDaDB(int id) {
		
		String query = "SELECT * FROM AbbonamentoMensile WHERE idAbbonamentoMensile = '"+this.idAbbonamentoMensile+"';";
		
		try {
			ResultSet res = DBConnectionManager.selectQuery(query);
			
			if(res.next()) {
				this.idAbbonamentoMensile = res.getInt("idAbbonamentoMensile");
				this.dataSottoscrizione = res.getString("dataSottoscrizione");
				this.dataScadenza=res.getString("dataScadenza");
				this.prezzo = res.getInt("prezzo");
				this.nomeMese = res.getString("nomeMese");
			}
		} catch(SQLException e) {
			System.err.println("Errore nella query!"+e.getMessage());
		} catch(ClassNotFoundException e) {
			System.err.println("Classe non presente!");
		}
		
	}
	
	public int scriviAbbonamentoMensile(int id, String dataSottoscrizione, String dataScadenza, int prezzo, String nomeMese){
		int ret =0;
		
		//creazione id 
		
		String query = "INSERT INTO AbbonamentoMensile(idAbbonamentoMensile, dataSottoscrizione, dataScadenza, prezzo, nomeMese)  VALUES( \'"+id+"\',"+"\'"+dataSottoscrizione+"\','"+dataScadenza+"\','"+prezzo+"\','"+nomeMese+"\');";
	

		try {
			
			ret = DBConnectionManager.updateQuery(query);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		
		

		return ret;
	
	}

	
	
	//setters e getters
	public int getIdAbbonamentoMensile() {
		return idAbbonamentoMensile;
	}
	public void setIdAbbonamentoMensile(int idAbbonamentoMensile) {
		this.idAbbonamentoMensile = idAbbonamentoMensile;
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
	public String getNomeMese() {
		return nomeMese;
	}
	public void setNomeMese(String nomeMese) {
		this.nomeMese = nomeMese;
	}
	
	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	

}
