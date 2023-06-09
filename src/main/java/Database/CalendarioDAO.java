package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe DAO del package Database per la gestione della persistenza dei dati ed il loro retrieval evocando i propri metodi dalle classi del layer Entity
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class CalendarioDAO {

	//Variabili membro.
    private ArrayList<GiornoDAO> giorni;
    
    
    /**
     * Costruttore vuoto per caricamento da DB
     */
    public CalendarioDAO() {
        
    	giorni = new ArrayList<GiornoDAO>();
        caricaDaDB();
        
    }
    
    
    /**
     * Funzione di loading degli attributi del DAO attraverso una query di SELECT
     */
    private void caricaDaDB() {
        
        //La query seleziona tutti i giorni
        String query = "SELECT nomeGiorno FROM Giorno";
        
        try {
            
            ResultSet rs = DBConnectionManager.selectQuery(query);
            
            while (rs.next()) {
                
                GiornoDAO giorno = new GiornoDAO(rs.getString("nomeGiorno"));
                giorni.add(giorno);
                
            }
            
            
        }catch(SQLException | ClassNotFoundException e) {
            
            System.err.println("[CALENDARIO-DAO] Errore nel metodo caricaDaDB");
            
        }
        
    }
    
    
    //GETTERS AND SETTERS

    /**
     * Getter
     * @return giorni
     */
    public ArrayList<GiornoDAO> getGiorni() {
        return giorni;
    }

    /**
     * Setter
     * @param giorni
     */
    public void setGiorni(ArrayList<GiornoDAO> giorni) {
        this.giorni = giorni;
    }
    
   
}