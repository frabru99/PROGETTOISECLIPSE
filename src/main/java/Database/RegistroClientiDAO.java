package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistroClientiDAO {
    
    private ArrayList<ClienteIscrittoDAO> clienti;
    
    
    public RegistroClientiDAO() {
        
        clienti = new ArrayList<ClienteIscrittoDAO>();
        caricaDaDB();
        
    }
    
    //Metodo carica da DB
    private void caricaDaDB() {
        
        //Devo caricare tutti i giorni
        String query = "SELECT codiceCliente FROM ClienteIscritto";
        
        try {
            
            //effettuo la query
            ResultSet rs = DBConnectionManager.selectQuery(query);
            
            while (rs.next()) {
                
                ClienteIscrittoDAO cliente = new ClienteIscrittoDAO(rs.getString("codiceCliente"));
                clienti.add(cliente);
                
            }
            
            
        }catch(SQLException | ClassNotFoundException e) {
            
            System.err.println("Non ci sono clienti");
            
        }
        
    }

    public void setClienti(ArrayList<ClienteIscrittoDAO> clienti) {
        this.clienti = clienti;
    }

    public ArrayList<ClienteIscrittoDAO> getClienti() {
        // TODO Auto-generated method stub
        return clienti;
    }
    
    
    
    
}