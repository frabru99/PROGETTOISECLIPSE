package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CentroSportivoDAO {
    
    //riferimenti ai DAO di calendario e registro
    private ArrayList<SalaperCorsiDAO> sale;
    
    public CentroSportivoDAO() {
        
        sale = new ArrayList<SalaperCorsiDAO>();
        caricaDaDB();
        
    }
    
    public void caricaDaDB() {
    
        //Devo caricare tutti i giorni
        String query = "SELECT idSalaperCorsi  FROM SalaperCorsi";
        
        try {
            
            //effettuo la query
            ResultSet rs = DBConnectionManager.selectQuery(query);
            
            while (rs.next()) {
                
                SalaperCorsiDAO sala = new SalaperCorsiDAO(rs.getInt("idSalaperCorsi"));
                sale.add(sala);
                
            }
            
            
        }catch(SQLException | ClassNotFoundException e) {
            
            System.err.println("Non ci sono sale");
            
        }
                
    }

    public ArrayList<SalaperCorsiDAO> getSale() {
        return sale;
    }

    public void setSale(ArrayList<SalaperCorsiDAO> sale) {
        this.sale = sale;
    }
    
    
        
    
}