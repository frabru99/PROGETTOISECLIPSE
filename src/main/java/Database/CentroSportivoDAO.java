package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CentroSportivoDAO {
    
    //Variabili membro.
    private ArrayList<SalaperCorsiDAO> sale;
    
    
    //Costruttore vuoto per caricamento da DB
    public CentroSportivoDAO() {
        
        sale = new ArrayList<SalaperCorsiDAO>();
        caricaDaDB();
        
    }
    
    
    //Funzione di loading degli attributi del DAO attraverso una query di SELECT
    public void caricaDaDB() {
    
    	 //La query seleziona tutti i giorni
        String query = "SELECT idSalaperCorsi  FROM SalaperCorsi";
        
        try {
            
            ResultSet rs = DBConnectionManager.selectQuery(query);
            
            while (rs.next()) {
                
                SalaperCorsiDAO sala = new SalaperCorsiDAO(rs.getInt("idSalaperCorsi"));
                sale.add(sala);
                
            }
            
            
        }catch(SQLException | ClassNotFoundException e) {
            
        	System.err.println("[CENTRO-SPORTIVO-DAO] Errore nel metodo caricaDaDB");
            
        }
                
    }
    

    //GETTERS AND SETTERS
    public ArrayList<SalaperCorsiDAO> getSale() {
        return sale;
    }

    public void setSale(ArrayList<SalaperCorsiDAO> sale) {
        this.sale = sale;
    }
    
    
}