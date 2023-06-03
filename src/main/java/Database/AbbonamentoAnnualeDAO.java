package Database;

import java.sql.Date;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbbonamentoAnnualeDAO {
	private int idAbbonamentoAnnuale;
	private String dataSottoscrizione;
	private String dataScadenza;
	private int prezzo;
	private String dataSopensione=null;
	private String dataRipresa=null;
	
	//costruttore con funzione di carica da DB
		public AbbonamentoAnnualeDAO(int id) {
			
			this.idAbbonamentoAnnuale = id;
			caricaDaDB(id); //carico dal database un certo abbonamento con quella chiave primaria!
			
		}
		
		public AbbonamentoAnnualeDAO() {
			super();
		}
		

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
				System.err.println("Errore nella query!"+e.getMessage());
			} catch(ClassNotFoundException e) {
				System.err.println("Classe non presente!");		
			}
			
		}
		
		//questa funzione serve per incrementare gli id degli abbonamenti (se non ce ne sono, si parte dall'id 0)
		public int prelevaIdmassimo() {
			int max=-1;
			String query = "SELECT idAbbonamentoAnnuale FROM AbbonamentoAnnuale WHERE idAbbonamentoAnnuale >=ALL(SELECT idAbbonamentoAnnuale FROM AbbonamentoAnnuale)";
			
			try {
				
				ResultSet rs = DBConnectionManager.selectQuery(query);
				
				if(rs.next()) {
					max=rs.getInt("idAbbonamentoAnnuale");
				}
				
			}catch(ClassNotFoundException | SQLException e) {
				System.err.println("Non ci sono id");
			}
			return max;
		}
		
		public int scriviAbbonamentoAnnuale(int id, String dataSottoscrizione, String dataScadenza, int prezzo){
			int ret =0;
			
			//creazione id 
			
			String query = "INSERT INTO AbbonamentoAnnuale(idAbbonamentoAnnuale, dataSottoscrizione, dataScadenza, prezzo)  VALUES( \'"+id+"\',"+"\'"+dataSottoscrizione+"\','"+dataScadenza+"\','"+prezzo+"\');";
		

			try {
				
				ret = DBConnectionManager.updateQuery(query);
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = -1;
			}
			
			return ret;
		
		}
		
		
		public int sospendiAbbonamentoAnnuale(int idAbbAnnuale, java.util.Date dataSospensione, java.util.Date dataRipresa) {
		
			int ret=0;
			
			
			
			//faccio prima una selecty di controllo, per prenedere id Abbonamento!
			
			int anno =(dataSospensione.getYear())-100;
		
			
			String datasosp = "20"+anno+"-"+dataSospensione.getMonth()+"-"+dataSospensione.getDay(); //scusaci ingegner de luca.......
			
			String datarip = "20"+anno+"-"+dataRipresa.getMonth()+"-"+dataRipresa.getDay(); //scusaci ingegner de luca.......
			
			
			try {
                String upd = "UPDATE AbbonamentoAnnuale SET dataSospensione = '"+datasosp+"', dataRipresa = '"+datarip+"' WHERE idAbbonamentoAnnuale = "+idAbbAnnuale+";";
         			
     			ret=DBConnectionManager.updateQuery(upd);
             
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				ret = -1;
			}
			
			return ret;
		}
		

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

		@Override
		public String toString() {
			return "AbbonamentoAnnualeDAO [idAbbonamentoAnnuale=" + idAbbonamentoAnnuale + ", dataSottoscrizione="
					+ dataSottoscrizione + ", dataScadenza=" + dataScadenza + ", prezzo=" + prezzo + ", dataSopensione="
					+ dataSopensione + ", dataRipresa=" + dataRipresa + "]";
		}
		
		
		
		
		
		
	
}
