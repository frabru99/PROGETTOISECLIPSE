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
public class RegistroClientiDAO {
    
	//Variabili membro.
    private ArrayList<ClienteIscrittoDAO> clienti;
    
    
    /**
     * Costruttore vuoto per inizializzazione
     */
    public RegistroClientiDAO() {
        
        clienti = new ArrayList<ClienteIscrittoDAO>();
        caricaDaDB();
        
    }
    
    
    /**
     * Funzione di loading degli attributi del DAO attraverso una query di SELECT
     */
    private void caricaDaDB() {
        
        //Carico tutti i clienti
        String query = "SELECT codiceCliente FROM ClienteIscritto";
        
        try {
            
            ResultSet rs = DBConnectionManager.selectQuery(query);
            
            while (rs.next()) {
                
                ClienteIscrittoDAO cliente = new ClienteIscrittoDAO(rs.getString("codiceCliente"));
                clienti.add(cliente);
                
            }
            
        }catch(SQLException | ClassNotFoundException e) {
            
            System.err.println("[REGISTRO-CLIENTI-DAO] Errore nel metodo caricaDaDB");
            
        }
        
    }

    
    //GETTERS AND SETTERS
    
    /**
     * Setter
     * @param clienti
     */
    public void setClienti(ArrayList<ClienteIscrittoDAO> clienti) {
        this.clienti = clienti;
    }

    /**
     * Getter
     * @return clienti
     */
    public ArrayList<ClienteIscrittoDAO> getClienti() {
        // TODO Auto-generated method stub
        return clienti;
    }
    
    
}