package Entity;

import java.util.ArrayList;
import Database.CalendarioDAO;
import Database.GiornoDAO;

public class CalendarioEntity {

    //Variabile uniqueistance per singleton
    private static CalendarioEntity uniqueInstance;
    
    private static ArrayList<GiornoEntity> giorni;
    
    //Costruttore
    private CalendarioEntity() {
        
        giorni = new ArrayList<GiornoEntity>();
        
        //Carico dal DAO
        CalendarioDAO cdao = new CalendarioDAO();
        ArrayList<GiornoDAO>giornidao = cdao.getGiorni();
        
        for (int i=0;i<giornidao.size();i++) {
            
            GiornoEntity giorno = new GiornoEntity(giornidao.get(i));
            giorni.add(giorno);
            
        }
        
        
    }
    
    //Metodo getistance che crea il singleton se l'istanza è null oppure la ritorna
    public static CalendarioEntity getInstance() {
        
        if (uniqueInstance == null) {
            
            uniqueInstance = new CalendarioEntity();
        
            
        }
        
        return uniqueInstance;
        
    }
    
    //Metodo per la ricerca del giorno all'interno del calendario
    public GiornoEntity cercaGiorno(String nomeGiorno) {
        
        
        GiornoEntity giorno = new GiornoEntity();
        
        for (int i=0;i<giorni.size();i++) {
            
            if (giorni.get(i).getNomeGiorno().compareTo(nomeGiorno)==0) {
                
                giorno = giorni.get(i);
                return giorno;
                
            }
            
        }
        
        return giorno;
        
    }

    
    //Getters and setters

    public static ArrayList<GiornoEntity> getGiorni() {
        return giorni;
    }

    public static void setGiorni(ArrayList<GiornoEntity> giorni) {
        CalendarioEntity.giorni = giorni;
    }
    
    
}