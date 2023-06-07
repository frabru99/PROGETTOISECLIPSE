package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistroClientiDAO {
    
	//Variabili membro.
    private ArrayList<ClienteIscrittoDAO> clienti;
    
    
    //Costruttore vuoto per inizializzazione
    public RegistroClientiDAO() {
        
        clienti = new ArrayList<ClienteIscrittoDAO>();
        caricaDaDB();
        
    }
    
    
    //Funzione di loading degli attributi del DAO attraverso una query di SELECT
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
    public void setClienti(ArrayList<ClienteIscrittoDAO> clienti) {
        this.clienti = clienti;
    }

    public ArrayList<ClienteIscrittoDAO> getClienti() {
        // TODO Auto-generated method stub
        return clienti;
    }
    
    
}