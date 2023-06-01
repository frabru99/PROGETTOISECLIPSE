package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalendarioDAO {

    private ArrayList<GiornoDAO> giorni;
    
    //Costruttore per caricare dal db
    public CalendarioDAO() {
        
    	giorni = new ArrayList<GiornoDAO>();
        caricaDaDB();
        
    }
    
    //Metodo carica da DB
    private void caricaDaDB() {
        
        //Devo caricare tutti i giorni
        String query = "SELECT nomeGiorno FROM Giorno";
        
        try {
            
            //effettuo la query
            ResultSet rs = DBConnectionManager.selectQuery(query);
            
            while (rs.next()) {
                
                GiornoDAO giorno = new GiornoDAO(rs.getString("nomeGiorno"));
                giorni.add(giorno);
                
            }
            
            
        }catch(SQLException | ClassNotFoundException e) {
            
            System.err.println("Non ci sono giorni");
            
        }
        
        
    }
    
    
    //Getters and setters

    public ArrayList<GiornoDAO> getGiorni() {
        return giorni;
    }

    public void setGiorni(ArrayList<GiornoDAO> giorni) {
        this.giorni = giorni;
    }
    
    
    
    
}